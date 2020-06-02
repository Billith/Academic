# Generated from HttpLang.g4 by ANTLR 4.8
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .HttpLangParser import HttpLangParser
else:
    from HttpLangParser import HttpLangParser

# This class defines a complete listener for a parse tree produced by HttpLangParser.
class HttpLangListener(ParseTreeListener):

    # Enter a parse tree produced by HttpLangParser#program.
    def enterProgram(self, ctx:HttpLangParser.ProgramContext):
        pass

    # Exit a parse tree produced by HttpLangParser#program.
    def exitProgram(self, ctx:HttpLangParser.ProgramContext):
        pass


    # Enter a parse tree produced by HttpLangParser#statement.
    def enterStatement(self, ctx:HttpLangParser.StatementContext):
        pass

    # Exit a parse tree produced by HttpLangParser#statement.
    def exitStatement(self, ctx:HttpLangParser.StatementContext):
        pass


    # Enter a parse tree produced by HttpLangParser#http_methods.
    def enterHttp_methods(self, ctx:HttpLangParser.Http_methodsContext):
        pass

    # Exit a parse tree produced by HttpLangParser#http_methods.
    def exitHttp_methods(self, ctx:HttpLangParser.Http_methodsContext):
        pass


    # Enter a parse tree produced by HttpLangParser#with_syntax.
    def enterWith_syntax(self, ctx:HttpLangParser.With_syntaxContext):
        pass

    # Exit a parse tree produced by HttpLangParser#with_syntax.
    def exitWith_syntax(self, ctx:HttpLangParser.With_syntaxContext):
        pass


    # Enter a parse tree produced by HttpLangParser#possibility.
    def enterPossibility(self, ctx:HttpLangParser.PossibilityContext):
        pass

    # Exit a parse tree produced by HttpLangParser#possibility.
    def exitPossibility(self, ctx:HttpLangParser.PossibilityContext):
        pass


    # Enter a parse tree produced by HttpLangParser#headers.
    def enterHeaders(self, ctx:HttpLangParser.HeadersContext):
        pass

    # Exit a parse tree produced by HttpLangParser#headers.
    def exitHeaders(self, ctx:HttpLangParser.HeadersContext):
        pass


    # Enter a parse tree produced by HttpLangParser#text.
    def enterText(self, ctx:HttpLangParser.TextContext):
        pass

    # Exit a parse tree produced by HttpLangParser#text.
    def exitText(self, ctx:HttpLangParser.TextContext):
        pass


    # Enter a parse tree produced by HttpLangParser#header.
    def enterHeader(self, ctx:HttpLangParser.HeaderContext):
        pass

    # Exit a parse tree produced by HttpLangParser#header.
    def exitHeader(self, ctx:HttpLangParser.HeaderContext):
        pass


    # Enter a parse tree produced by HttpLangParser#data.
    def enterData(self, ctx:HttpLangParser.DataContext):
        pass

    # Exit a parse tree produced by HttpLangParser#data.
    def exitData(self, ctx:HttpLangParser.DataContext):
        pass


    # Enter a parse tree produced by HttpLangParser#link.
    def enterLink(self, ctx:HttpLangParser.LinkContext):
        pass

    # Exit a parse tree produced by HttpLangParser#link.
    def exitLink(self, ctx:HttpLangParser.LinkContext):
        pass


    # Enter a parse tree produced by HttpLangParser#schema.
    def enterSchema(self, ctx:HttpLangParser.SchemaContext):
        pass

    # Exit a parse tree produced by HttpLangParser#schema.
    def exitSchema(self, ctx:HttpLangParser.SchemaContext):
        pass



del HttpLangParser