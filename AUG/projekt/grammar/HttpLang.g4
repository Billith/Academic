grammar HttpLang;

program
    : (statement ';' NEWLINE)*
    ;

statement
    : 'send' http_methods with_syntax* 'url' '[' link ']'
    ;

http_methods
    : 'get'
    | 'post'
    | 'put'
    | 'delete'
    ;

with_syntax
    : 'with' possibility
    ;

possibility
    : headers
    | data
    ;

headers
    : 'headers' '"' header (',' header)* '"'
    ;

text
    : (VALID_CHAR|DIGIT|LETTER)+
    ;

header
    : text ':' text
    ;

data
    : 'data' '"' text '"'
    ;

link
    : schema '://' text
    ;

schema
    : 'http'
    | 'https'
    ;

VALID_CHAR
    : ('-' | '_' | '.' | '/')
    ;

LETTER
    : [a-zA-Z]
    ;

NEWLINE
    : [\r\n]
    ;

DIGIT
    : [0-9]
    ;

WHITESPACE
    : ' ' -> skip
    ;