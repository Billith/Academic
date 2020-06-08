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
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33")
        buf.write("`\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\3\2\3\2\3\2\7\2")
        buf.write("\34\n\2\f\2\16\2\37\13\2\7\2!\n\2\f\2\16\2$\13\2\3\3\3")
        buf.write("\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\3\3\3\3\3\3\3\3\3\3\4")
        buf.write("\3\4\3\5\3\5\3\5\3\6\3\6\5\6:\n\6\3\7\3\7\3\7\3\7\3\7")
        buf.write("\7\7A\n\7\f\7\16\7D\13\7\3\7\3\7\3\b\6\bI\n\b\r\b\16\b")
        buf.write("J\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3")
        buf.write("\13\5\13Z\n\13\3\13\3\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f")
        buf.write("\16\20\22\24\26\2\5\3\2\b\r\4\2\27\30\32\32\3\2\25\26")
        buf.write("\2[\2\"\3\2\2\2\4%\3\2\2\2\6\62\3\2\2\2\b\64\3\2\2\2\n")
        buf.write("9\3\2\2\2\f;\3\2\2\2\16H\3\2\2\2\20L\3\2\2\2\22P\3\2\2")
        buf.write("\2\24U\3\2\2\2\26]\3\2\2\2\30\31\5\4\3\2\31\35\7\3\2\2")
        buf.write("\32\34\7\31\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2")
        buf.write("\2\2\35\36\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2 \30\3\2\2")
        buf.write("\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\3\3\2\2\2$\"\3\2\2")
        buf.write("\2%&\7\4\2\2&*\5\6\4\2\')\5\b\5\2(\'\3\2\2\2),\3\2\2\2")
        buf.write("*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-.\7\5\2\2./\7")
        buf.write("\6\2\2/\60\5\24\13\2\60\61\7\7\2\2\61\5\3\2\2\2\62\63")
        buf.write("\t\2\2\2\63\7\3\2\2\2\64\65\7\16\2\2\65\66\5\n\6\2\66")
        buf.write("\t\3\2\2\2\67:\5\f\7\28:\5\22\n\29\67\3\2\2\298\3\2\2")
        buf.write("\2:\13\3\2\2\2;<\7\17\2\2<=\7\20\2\2=B\5\20\t\2>?\7\21")
        buf.write("\2\2?A\5\20\t\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2")
        buf.write("\2CE\3\2\2\2DB\3\2\2\2EF\7\20\2\2F\r\3\2\2\2GI\t\3\2\2")
        buf.write("HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\17\3\2\2\2L")
        buf.write("M\5\16\b\2MN\7\22\2\2NO\5\16\b\2O\21\3\2\2\2PQ\7\23\2")
        buf.write("\2QR\7\20\2\2RS\5\16\b\2ST\7\20\2\2T\23\3\2\2\2UV\5\26")
        buf.write("\f\2VW\7\24\2\2WY\5\16\b\2XZ\7\22\2\2YX\3\2\2\2YZ\3\2")
        buf.write("\2\2Z[\3\2\2\2[\\\5\16\b\2\\\25\3\2\2\2]^\t\4\2\2^\27")
        buf.write("\3\2\2\2\t\35\"*9BJY")
        return buf.getvalue()


class HttpLangParser ( Parser ):

    grammarFileName = "HttpLang.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "';'", "'send'", "'url'", "'['", "']'", 
                     "'get'", "'post'", "'put'", "'delete'", "'head'", "'patch'", 
                     "'with'", "'headers'", "'\"'", "','", "':'", "'data'", 
                     "'://'", "'http'", "'https'", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "' '" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "VALID_CHAR", "LETTER", "NEWLINE", "DIGIT", 
                      "WHITESPACE" ]

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
    T__18=19
    T__19=20
    VALID_CHAR=21
    LETTER=22
    NEWLINE=23
    DIGIT=24
    WHITESPACE=25

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
            self.state = 32
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__1:
                self.state = 22
                self.statement()
                self.state = 23
                self.match(HttpLangParser.T__0)
                self.state = 27
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==HttpLangParser.NEWLINE:
                    self.state = 24
                    self.match(HttpLangParser.NEWLINE)
                    self.state = 29
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 34
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
            self.state = 35
            self.match(HttpLangParser.T__1)
            self.state = 36
            self.http_methods()
            self.state = 40
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__11:
                self.state = 37
                self.with_syntax()
                self.state = 42
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 43
            self.match(HttpLangParser.T__2)
            self.state = 44
            self.match(HttpLangParser.T__3)
            self.state = 45
            self.link()
            self.state = 46
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
            self.state = 48
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << HttpLangParser.T__5) | (1 << HttpLangParser.T__6) | (1 << HttpLangParser.T__7) | (1 << HttpLangParser.T__8) | (1 << HttpLangParser.T__9) | (1 << HttpLangParser.T__10))) != 0)):
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
            self.state = 50
            self.match(HttpLangParser.T__11)
            self.state = 51
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
            self.state = 55
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [HttpLangParser.T__12]:
                self.enterOuterAlt(localctx, 1)
                self.state = 53
                self.headers()
                pass
            elif token in [HttpLangParser.T__16]:
                self.enterOuterAlt(localctx, 2)
                self.state = 54
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
            self.state = 57
            self.match(HttpLangParser.T__12)
            self.state = 58
            self.match(HttpLangParser.T__13)
            self.state = 59
            self.header()
            self.state = 64
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==HttpLangParser.T__14:
                self.state = 60
                self.match(HttpLangParser.T__14)
                self.state = 61
                self.header()
                self.state = 66
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 67
            self.match(HttpLangParser.T__13)
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
            self.state = 70 
            self._errHandler.sync(self)
            _alt = 1
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt == 1:
                    self.state = 69
                    _la = self._input.LA(1)
                    if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << HttpLangParser.VALID_CHAR) | (1 << HttpLangParser.LETTER) | (1 << HttpLangParser.DIGIT))) != 0)):
                        self._errHandler.recoverInline(self)
                    else:
                        self._errHandler.reportMatch(self)
                        self.consume()

                else:
                    raise NoViableAltException(self)
                self.state = 72 
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,5,self._ctx)

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
            self.state = 74
            self.text()
            self.state = 75
            self.match(HttpLangParser.T__15)
            self.state = 76
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
            self.state = 78
            self.match(HttpLangParser.T__16)
            self.state = 79
            self.match(HttpLangParser.T__13)
            self.state = 80
            self.text()
            self.state = 81
            self.match(HttpLangParser.T__13)
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


        def text(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(HttpLangParser.TextContext)
            else:
                return self.getTypedRuleContext(HttpLangParser.TextContext,i)


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
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 83
            self.schema()
            self.state = 84
            self.match(HttpLangParser.T__17)
            self.state = 85
            self.text()
            self.state = 87
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            if _la==HttpLangParser.T__15:
                self.state = 86
                self.match(HttpLangParser.T__15)


            self.state = 89
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
            self.state = 91
            _la = self._input.LA(1)
            if not(_la==HttpLangParser.T__18 or _la==HttpLangParser.T__19):
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





