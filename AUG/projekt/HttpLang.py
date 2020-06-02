import sys
import requests
from json import dumps

import antlr4
from gen.HttpLangLexer import HttpLangLexer
from gen.HttpLangParser import HttpLangParser
from gen.HttpLangListener import HttpLangListener

class HttpContext():
    __slots__ = 'method', 'body', 'url', 'headers'

    def __init__(self):
        self.headers = []

class Listener(HttpLangListener):
    def __init__(self):
        self.statements = []
        self.current_request = HttpContext()

    def enterHttp_methods(self, ctx: HttpLangParser.Http_methodsContext):
        self.current_request.method = ctx.getText()

    def enterHeader(self, ctx: HttpLangParser.HeaderContext):
        self.current_request.headers.append(ctx.getText())

    def enterData(self, ctx: HttpLangParser.DataContext):
        body = ctx.getText()
        print(f'DEBUG: body => {body}')
        body = body[len("data"):]           # skip data keyword
        print(f'DEBUG: body => {body}')
        body = body[1:-1]                   # skip quotes
        print(f'DEBUG: body => {body}')
        self.current_request.body = body

    def enterLink(self, ctx: HttpLangParser.LinkContext):
        self.current_request.url = ctx.getText()

    def exitStatement(self, ctx: HttpLangParser.StatementContext):
        self.statements.append(self.current_request)
        self.current_request = HttpContext()

def send_request(http_request: HttpContext) -> str:
    http_method_func = getattr(requests, http_request.method)
    headers = {}
    for header in http_request.headers:
        key, value = header.split(':')
        headers[key] = value
    body = getattr(http_request, 'body', None)
    result = {
        'url': http_request.url,
        'method': http_request.method
    }

    if http_request.method in ['post', 'put']:
        response = http_method_func(http_request.url, data=body, headers=headers).text
    else:
        response = http_method_func(http_request.url, headers=headers).text

    result['response'] = response
    return result

def main():
    test = '''send get url[http://127.0.0.1];
    send get with headers "X-Custom: Hello from python, User-Agent: HttpLang" url[http://127.0.0.1];
    send post with data "AUG takie fajne" url[http://127.0.0.1];
    '''
    result = []
    input_stream = antlr4.InputStream(test)
    lexer = HttpLangLexer(input_stream)
    stream = antlr4.CommonTokenStream(lexer)
    parser = HttpLangParser(stream)
    tree = parser.program()

    if parser.getNumberOfSyntaxErrors() > 0:
        return

    listener = Listener()
    walker = antlr4.ParseTreeWalker()
    walker.walk(listener, tree)
    for statement in listener.statements:
        result.append(send_request(statement))
    print(dumps(result, indent=2, sort_keys=True))

if __name__ == '__main__':
    main()
