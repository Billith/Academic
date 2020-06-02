# Generated from HttpLang.g4 by ANTLR 4.8
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31")
        buf.write("X\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\3\2\3\2\3\2\3\2")
        buf.write("\7\2\35\n\2\f\2\16\2 \13\2\3\3\3\3\3\3\7\3%\n\3\f\3\16")
        buf.write("\3(\13\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3")
        buf.write("\6\5\6\66\n\6\3\7\3\7\3\7\3\7\3\7\7\7=\n\7\f\7\16\7@\13")
        buf.write("\7\3\7\3\7\3\b\6\bE\n\b\r\b\16\bF\3\t\3\t\3\t\3\t\3\n")
        buf.write("\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\2\2\r")
        buf.write("\2\4\6\b\n\f\16\20\22\24\26\2\5\3\2\b\13\4\2\25\26\30")
        buf.write("\30\3\2\23\24\2Q\2\36\3\2\2\2\4!\3\2\2\2\6.\3\2\2\2\b")
        buf.write("\60\3\2\2\2\n\65\3\2\2\2\f\67\3\2\2\2\16D\3\2\2\2\20H")
        buf.write("\3\2\2\2\22L\3\2\2\2\24Q\3\2\2\2\26U\3\2\2\2\30\31\5\4")
        buf.write("\3\2\31\32\7\3\2\2\32\33\7\27\2\2\33\35\3\2\2\2\34\30")
        buf.write("\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\3")
        buf.write("\3\2\2\2 \36\3\2\2\2!\"\7\4\2\2\"&\5\6\4\2#%\5\b\5\2$")
        buf.write("#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&")
        buf.write("\3\2\2\2)*\7\5\2\2*+\7\6\2\2+,\5\24\13\2,-\7\7\2\2-\5")
        buf.write("\3\2\2\2./\t\2\2\2/\7\3\2\2\2\60\61\7\f\2\2\61\62\5\n")
        buf.write("\6\2\62\t\3\2\2\2\63\66\5\f\7\2\64\66\5\22\n\2\65\63\3")
        buf.write("\2\2\2\65\64\3\2\2\2\66\13\3\2\2\2\678\7\r\2\289\7\16")
        buf.write("\2\29>\5\20\t\2:;\7\17\2\2;=\5\20\t\2<:\3\2\2\2=@\3\2")
        buf.write("\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\16\2")
        buf.write("\2B\r\3\2\2\2CE\t\3\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2")
        buf.write("FG\3\2\2\2G\17\3\2\2\2HI\5\16\b\2IJ\7\20\2\2JK\5\16\b")
        buf.write("\2K\21\3\2\2\2LM\7\21\2\2MN\7\16\2\2NO\5\16\b\2OP\7\16")
        buf.write("\2\2P\23\3\2\2\2QR\5\26\f\2RS\7\22\2\2ST\5\16\b\2T\25")
        buf.write("\3\2\2\2UV\t\4\2\2V\27\3\2\2\2\7\36&\65>F")
        return buf.getvalue()


class HttpLangParser ( Parser ):

    grammarFileName = "HttpLang.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "';'", "'send'", "'url'", "'['", "']'", 
                     "'get'", "'post'", "'put'", "'delete'", "'with'", "'headers'", 
                     "'\"'", "','", "':'", "'data'", "'://'", "'http'", 
                     "'https'", "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "' '" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "VALID_CHAR", 
                      "LETTER", "NEWLINE", "DIGIT", "WHITESPACE" ]

    RULE_program = 0
    RULE_statement = 1
    RULE_http_methods = 2
    RULE_with_syntax = 3
    RULE_possibility = 4
    RULE_headers = 5
    RULE_text = 6
    RULE_header = 7
    RULE_data = 8
    RULE_link = 9
    RULE_schema = 10

    ruleNames =  [ "program", "statement", "http_methods", "with_syntax", 
                   "possibility", "headers", "text", "header", "data", "link", 
                   "schema" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    T__6=7
    T__7=8
    T__8=9
    T__9=10
    T__10=11
    T__11=12
    T__12=13
    T__13=14
    T__14=15
    T__15=16
    T__16=17
    T__17=18
    VALID_CHAR=19
    LETTER=20
    NEWLINE=21
    DIGIT=22
    WHITESPACE=23

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.8")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class ProgramContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def statement(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(HttpLangParser.StatementContext)
            else:
                return self.getTypedRuleContext(HttpLangParser.StatementContext,i)


        def NEWLINE(self, i:int=None):
            if i is None:
                return self.getTokens(HttpLangParser.NEWLINE)
            else:
                return self.getToken(HttpLangParser.NEWLINE, i)

        def getRuleIndex(self):
            return HttpLangParser.RULE_program

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterProgram" ):
                listener.enterProgram(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitProgram" ):
                listener.exitProgram(self)




    def program(self):

        localctx = HttpLangParser.ProgramContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_program)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 28
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__1:
                self.state = 22
                self.statement()
                self.state = 23
                self.match(HttpLangParser.T__0)
                self.state = 24
                self.match(HttpLangParser.NEWLINE)
                self.state = 30
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class StatementContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def http_methods(self):
            return self.getTypedRuleContext(HttpLangParser.Http_methodsContext,0)


        def link(self):
            return self.getTypedRuleContext(HttpLangParser.LinkContext,0)


        def with_syntax(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(HttpLangParser.With_syntaxContext)
            else:
                return self.getTypedRuleContext(HttpLangParser.With_syntaxContext,i)


        def getRuleIndex(self):
            return HttpLangParser.RULE_statement

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStatement" ):
                listener.enterStatement(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStatement" ):
                listener.exitStatement(self)




    def statement(self):

        localctx = HttpLangParser.StatementContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_statement)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 31
            self.match(HttpLangParser.T__1)
            self.state = 32
            self.http_methods()
            self.state = 36
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__9:
                self.state = 33
                self.with_syntax()
                self.state = 38
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 39
            self.match(HttpLangParser.T__2)
            self.state = 40
            self.match(HttpLangParser.T__3)
            self.state = 41
            self.link()
            self.state = 42
            self.match(HttpLangParser.T__4)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class Http_methodsContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return HttpLangParser.RULE_http_methods

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHttp_methods" ):
                listener.enterHttp_methods(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHttp_methods" ):
                listener.exitHttp_methods(self)




    def http_methods(self):

        localctx = HttpLangParser.Http_methodsContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_http_methods)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 44
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << HttpLangParser.T__5) | (1 << HttpLangParser.T__6) | (1 << HttpLangParser.T__7) | (1 << HttpLangParser.T__8))) != 0)):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class With_syntaxContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def possibility(self):
            return self.getTypedRuleContext(HttpLangParser.PossibilityContext,0)


        def getRuleIndex(self):
            return HttpLangParser.RULE_with_syntax

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterWith_syntax" ):
                listener.enterWith_syntax(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitWith_syntax" ):
                listener.exitWith_syntax(self)




    def with_syntax(self):

        localctx = HttpLangParser.With_syntaxContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_with_syntax)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 46
            self.match(HttpLangParser.T__9)
            self.state = 47
            self.possibility()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PossibilityContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def headers(self):
            return self.getTypedRuleContext(HttpLangParser.HeadersContext,0)


        def data(self):
            return self.getTypedRuleContext(HttpLangParser.DataContext,0)


        def getRuleIndex(self):
            return HttpLangParser.RULE_possibility

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPossibility" ):
                listener.enterPossibility(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPossibility" ):
                listener.exitPossibility(self)




    def possibility(self):

        localctx = HttpLangParser.PossibilityContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_possibility)
        try:
            self.state = 51
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [HttpLangParser.T__10]:
                self.enterOuterAlt(localctx, 1)
                self.state = 49
                self.headers()
                pass
            elif token in [HttpLangParser.T__14]:
                self.enterOuterAlt(localctx, 2)
                self.state = 50
                self.data()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class HeadersContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def header(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(HttpLangParser.HeaderContext)
            else:
                return self.getTypedRuleContext(HttpLangParser.HeaderContext,i)


        def getRuleIndex(self):
            return HttpLangParser.RULE_headers

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHeaders" ):
                listener.enterHeaders(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHeaders" ):
                listener.exitHeaders(self)




    def headers(self):

        localctx = HttpLangParser.HeadersContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_headers)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 53
            self.match(HttpLangParser.T__10)
            self.state = 54
            self.match(HttpLangParser.T__11)
            self.state = 55
            self.header()
            self.state = 60
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__12:
                self.state = 56
                self.match(HttpLangParser.T__12)
                self.state = 57
                self.header()
                self.state = 62
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 63
            self.match(HttpLangParser.T__11)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class TextContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def VALID_CHAR(self, i:int=None):
            if i is None:
                return self.getTokens(HttpLangParser.VALID_CHAR)
            else:
                return self.getToken(HttpLangParser.VALID_CHAR, i)

        def DIGIT(self, i:int=None):
            if i is None:
                return self.getTokens(HttpLangParser.DIGIT)
            else:
                return self.getToken(HttpLangParser.DIGIT, i)

        def LETTER(self, i:int=None):
            if i is None:
                return self.getTokens(HttpLangParser.LETTER)
            else:
                return self.getToken(HttpLangParser.LETTER, i)

        def getRuleIndex(self):
            return HttpLangParser.RULE_text

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterText" ):
                listener.enterText(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitText" ):
                listener.exitText(self)




    def text(self):

        localctx = HttpLangParser.TextContext(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_text)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 66 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 65
                _la = self._input.LA(1)
                if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << HttpLangParser.VALID_CHAR) | (1 << HttpLangParser.LETTER) | (1 << HttpLangParser.DIGIT))) != 0)):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 68 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not ((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << HttpLangParser.VALID_CHAR) | (1 << HttpLangParser.LETTER) | (1 << HttpLangParser.DIGIT))) != 0)):
                    break

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class HeaderContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def text(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(HttpLangParser.TextContext)
            else:
                return self.getTypedRuleContext(HttpLangParser.TextContext,i)


        def getRuleIndex(self):
            return HttpLangParser.RULE_header

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHeader" ):
                listener.enterHeader(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHeader" ):
                listener.exitHeader(self)




    def header(self):

        localctx = HttpLangParser.HeaderContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_header)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 70
            self.text()
            self.state = 71
            self.match(HttpLangParser.T__13)
            self.state = 72
            self.text()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DataContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def text(self):
            return self.getTypedRuleContext(HttpLangParser.TextContext,0)


        def getRuleIndex(self):
            return HttpLangParser.RULE_data

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterData" ):
                listener.enterData(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitData" ):
                listener.exitData(self)




    def data(self):

        localctx = HttpLangParser.DataContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_data)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 74
            self.match(HttpLangParser.T__14)
            self.state = 75
            self.match(HttpLangParser.T__11)
            self.state = 76
            self.text()
            self.state = 77
            self.match(HttpLangParser.T__11)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class LinkContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def schema(self):
            return self.getTypedRuleContext(HttpLangParser.SchemaContext,0)


        def text(self):
            return self.getTypedRuleContext(HttpLangParser.TextContext,0)


        def getRuleIndex(self):
            return HttpLangParser.RULE_link

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterLink" ):
                listener.enterLink(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitLink" ):
                listener.exitLink(self)




    def link(self):

        localctx = HttpLangParser.LinkContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_link)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 79
            self.schema()
            self.state = 80
            self.match(HttpLangParser.T__15)
            self.state = 81
            self.text()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class SchemaContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return HttpLangParser.RULE_schema

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSchema" ):
                listener.enterSchema(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSchema" ):
                listener.exitSchema(self)




    def schema(self):

        localctx = HttpLangParser.SchemaContext(self, self._ctx, self.state)
        self.enterRule(localctx, 20, self.RULE_schema)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 83
            _la = self._input.LA(1)
            if not(_la==HttpLangParser.T__16 or _la==HttpLangParser.T__17):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





