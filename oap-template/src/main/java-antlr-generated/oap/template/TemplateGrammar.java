// Generated from TemplateGrammar.g4 by ANTLR 4.8

package oap.template;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TemplateGrammar extends TemplateGrammarAdaptor {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STARTEXPR=1, TEXT=2, LBRACE=3, RBRACE=4, PIPE=5, DOT=6, LPAREN=7, RPAREN=8, 
		LBRACK=9, RBRACK=10, DQUESTION=11, SEMI=12, COMMA=13, STAR=14, SLASH=15, 
		PERCENT=16, PLUS=17, MINUS=18, ID=19, DSTRING=20, SSTRING=21, DECDIGITS=22, 
		FLOAT=23, ERR_CHAR=24, CERR_CHAR=25;
	public static final int
		RULE_template = 0, RULE_elements = 1, RULE_element = 2, RULE_text = 3, 
		RULE_expression = 4, RULE_defaultValue = 5, RULE_defaultValueType = 6, 
		RULE_function = 7, RULE_functionArgs = 8, RULE_functionArg = 9, RULE_orExps = 10, 
		RULE_exps = 11, RULE_exp = 12, RULE_concatenation = 13, RULE_citems = 14, 
		RULE_citem = 15, RULE_math = 16, RULE_number = 17, RULE_mathOperation = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "elements", "element", "text", "expression", "defaultValue", 
			"defaultValueType", "function", "functionArgs", "functionArg", "orExps", 
			"exps", "exp", "concatenation", "citems", "citem", "math", "number", 
			"mathOperation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STARTEXPR", "TEXT", "LBRACE", "RBRACE", "PIPE", "DOT", "LPAREN", 
			"RPAREN", "LBRACK", "RBRACK", "DQUESTION", "SEMI", "COMMA", "STAR", "SLASH", 
			"PERCENT", "PLUS", "MINUS", "ID", "DSTRING", "SSTRING", "DECDIGITS", 
			"FLOAT", "ERR_CHAR", "CERR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TemplateGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		public TemplateGrammar(TokenStream input, Map<String, List<Method>> builtInFunction, ErrorStrategy errorStrategy) {
			this(input);
			
			this.builtInFunction = builtInFunction;
			this.errorStrategy = errorStrategy;
		}


	public TemplateGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TemplateContext extends ParserRuleContext {
		public TemplateType parentType;
		public AstRoot rootAst;
		public ElementsContext elements;
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TemplateGrammar.EOF, 0); }
		public TemplateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TemplateContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitTemplate(this);
		}
	}

	public final TemplateContext template(TemplateType parentType) throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState(), parentType);
		enterRule(_localctx, 0, RULE_template);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((TemplateContext)_localctx).elements = elements(parentType);
			 ((TemplateContext)_localctx).rootAst =  new AstRoot(_localctx.parentType); _localctx.rootAst.addChildren(((TemplateContext)_localctx).elements.list); 
			setState(40);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementsContext extends ParserRuleContext {
		public TemplateType parentType;
		public ArrayList<Ast> list = new ArrayList<>();
		public ElementContext element;
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ElementsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ElementsContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_elements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitElements(this);
		}
	}

	public final ElementsContext elements(TemplateType parentType) throws RecognitionException {
		ElementsContext _localctx = new ElementsContext(_ctx, getState(), parentType);
		enterRule(_localctx, 2, RULE_elements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STARTEXPR || _la==TEXT) {
				{
				{
				setState(42);
				((ElementsContext)_localctx).element = element(parentType);
				 _localctx.list.add(((ElementsContext)_localctx).element.ast); 
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public TemplateType parentType;
		public Ast ast;
		public TextContext t;
		public ExpressionContext expression;
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ElementContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitElement(this);
		}
	}

	public final ElementContext element(TemplateType parentType) throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState(), parentType);
		enterRule(_localctx, 4, RULE_element);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((ElementContext)_localctx).t = text();
				 ((ElementContext)_localctx).ast =  new AstText((((ElementContext)_localctx).t!=null?_input.getText(((ElementContext)_localctx).t.start,((ElementContext)_localctx).t.stop):null)); _localctx.ast.addChild(new AstPrint(_localctx.ast.type, null)); 
				}
				break;
			case STARTEXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				((ElementContext)_localctx).expression = expression(parentType);
				 ((ElementContext)_localctx).ast =  ((ElementContext)_localctx).expression.ast.top; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(TemplateGrammar.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(TemplateGrammar.TEXT, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitText(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_text);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(58);
					match(TEXT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(61); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin ast;
		public ExpsContext exps;
		public OrExpsContext orExps;
		public DefaultValueContext defaultValue;
		public FunctionContext function;
		public TerminalNode STARTEXPR() { return getToken(TemplateGrammar.STARTEXPR, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public OrExpsContext orExps() {
			return getRuleContext(OrExpsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TemplateGrammar.RBRACE, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression(TemplateType parentType) throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState(), parentType);
		enterRule(_localctx, 8, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(STARTEXPR);
			setState(64);
			((ExpressionContext)_localctx).exps = exps(parentType);
			 ((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).exps.ast; 
			setState(66);
			((ExpressionContext)_localctx).orExps = orExps(parentType, _localctx.ast);
			 ((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).orExps.ast; 
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DQUESTION) {
				{
				setState(68);
				((ExpressionContext)_localctx).defaultValue = defaultValue();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(71);
				((ExpressionContext)_localctx).function = function();
				}
			}


			        if( ((ExpressionContext)_localctx).function != null ) {
			          _localctx.ast.addToBottomChildrenAndSet( ((ExpressionContext)_localctx).function.func );
			        }

			        _localctx.ast.addLeafs( () -> getAst(_localctx.ast.bottom.type, null, false, ((ExpressionContext)_localctx).defaultValue != null ? ((ExpressionContext)_localctx).defaultValue.v : null) );
			      
			setState(75);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public String v;
		public DefaultValueTypeContext defaultValueType;
		public TerminalNode DQUESTION() { return getToken(TemplateGrammar.DQUESTION, 0); }
		public DefaultValueTypeContext defaultValueType() {
			return getRuleContext(DefaultValueTypeContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(DQUESTION);
			setState(78);
			((DefaultValueContext)_localctx).defaultValueType = defaultValueType();
			 ((DefaultValueContext)_localctx).v =  ((DefaultValueContext)_localctx).defaultValueType.v; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueTypeContext extends ParserRuleContext {
		public String v;
		public Token SSTRING;
		public Token DSTRING;
		public Token DECDIGITS;
		public Token FLOAT;
		public TerminalNode SSTRING() { return getToken(TemplateGrammar.SSTRING, 0); }
		public TerminalNode DSTRING() { return getToken(TemplateGrammar.DSTRING, 0); }
		public TerminalNode DECDIGITS() { return getToken(TemplateGrammar.DECDIGITS, 0); }
		public TerminalNode FLOAT() { return getToken(TemplateGrammar.FLOAT, 0); }
		public DefaultValueTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValueType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterDefaultValueType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitDefaultValueType(this);
		}
	}

	public final DefaultValueTypeContext defaultValueType() throws RecognitionException {
		DefaultValueTypeContext _localctx = new DefaultValueTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_defaultValueType);
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SSTRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				((DefaultValueTypeContext)_localctx).SSTRING = match(SSTRING);
				 ((DefaultValueTypeContext)_localctx).v =  sStringToDString( (((DefaultValueTypeContext)_localctx).SSTRING!=null?((DefaultValueTypeContext)_localctx).SSTRING.getText():null) ); 
				}
				break;
			case DSTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				((DefaultValueTypeContext)_localctx).DSTRING = match(DSTRING);
				 ((DefaultValueTypeContext)_localctx).v =  (((DefaultValueTypeContext)_localctx).DSTRING!=null?((DefaultValueTypeContext)_localctx).DSTRING.getText():null); 
				}
				break;
			case DECDIGITS:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				((DefaultValueTypeContext)_localctx).DECDIGITS = match(DECDIGITS);
				 ((DefaultValueTypeContext)_localctx).v =  (((DefaultValueTypeContext)_localctx).DECDIGITS!=null?((DefaultValueTypeContext)_localctx).DECDIGITS.getText():null); 
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(87);
				((DefaultValueTypeContext)_localctx).FLOAT = match(FLOAT);
				 ((DefaultValueTypeContext)_localctx).v =  (((DefaultValueTypeContext)_localctx).FLOAT!=null?((DefaultValueTypeContext)_localctx).FLOAT.getText():null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Ast func;
		public Token ID;
		public FunctionArgsContext functionArgs;
		public TerminalNode SEMI() { return getToken(TemplateGrammar.SEMI, 0); }
		public TerminalNode ID() { return getToken(TemplateGrammar.ID, 0); }
		public TerminalNode LPAREN() { return getToken(TemplateGrammar.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TemplateGrammar.RPAREN, 0); }
		public FunctionArgsContext functionArgs() {
			return getRuleContext(FunctionArgsContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(SEMI);
			setState(92);
			((FunctionContext)_localctx).ID = match(ID);
			setState(93);
			match(LPAREN);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DSTRING) | (1L << SSTRING) | (1L << DECDIGITS))) != 0)) {
				{
				setState(94);
				((FunctionContext)_localctx).functionArgs = functionArgs();
				}
			}

			setState(97);
			match(RPAREN);
			 ((FunctionContext)_localctx).func =  getFunction( (((FunctionContext)_localctx).ID!=null?((FunctionContext)_localctx).ID.getText():null), ((FunctionContext)_localctx).functionArgs != null ? ((FunctionContext)_localctx).functionArgs.ret : List.of() ); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgsContext extends ParserRuleContext {
		public ArrayList<String> ret = new ArrayList<>();
		public FunctionArgContext functionArg;
		public List<FunctionArgContext> functionArg() {
			return getRuleContexts(FunctionArgContext.class);
		}
		public FunctionArgContext functionArg(int i) {
			return getRuleContext(FunctionArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammar.COMMA, i);
		}
		public FunctionArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterFunctionArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitFunctionArgs(this);
		}
	}

	public final FunctionArgsContext functionArgs() throws RecognitionException {
		FunctionArgsContext _localctx = new FunctionArgsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			((FunctionArgsContext)_localctx).functionArg = functionArg();
			 _localctx.ret.add( ((FunctionArgsContext)_localctx).functionArg.ret ); 
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(102);
				match(COMMA);
				setState(103);
				((FunctionArgsContext)_localctx).functionArg = functionArg();
				 _localctx.ret.add( ((FunctionArgsContext)_localctx).functionArg.ret ); 
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgContext extends ParserRuleContext {
		public String ret;
		public Token DECDIGITS;
		public Token SSTRING;
		public Token DSTRING;
		public TerminalNode DECDIGITS() { return getToken(TemplateGrammar.DECDIGITS, 0); }
		public TerminalNode SSTRING() { return getToken(TemplateGrammar.SSTRING, 0); }
		public TerminalNode DSTRING() { return getToken(TemplateGrammar.DSTRING, 0); }
		public FunctionArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterFunctionArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitFunctionArg(this);
		}
	}

	public final FunctionArgContext functionArg() throws RecognitionException {
		FunctionArgContext _localctx = new FunctionArgContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_functionArg);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECDIGITS:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				((FunctionArgContext)_localctx).DECDIGITS = match(DECDIGITS);
				 ((FunctionArgContext)_localctx).ret =  (((FunctionArgContext)_localctx).DECDIGITS!=null?((FunctionArgContext)_localctx).DECDIGITS.getText():null); 
				}
				break;
			case SSTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				((FunctionArgContext)_localctx).SSTRING = match(SSTRING);
				 ((FunctionArgContext)_localctx).ret =  sStringToDString( (((FunctionArgContext)_localctx).SSTRING!=null?((FunctionArgContext)_localctx).SSTRING.getText():null) ); 
				}
				break;
			case DSTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				((FunctionArgContext)_localctx).DSTRING = match(DSTRING);
				 ((FunctionArgContext)_localctx).ret =  (((FunctionArgContext)_localctx).DSTRING!=null?((FunctionArgContext)_localctx).DSTRING.getText():null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpsContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin firstAst;
		public MaxMin ast;
		public ArrayList<MaxMin> list = new ArrayList<>();;
		public ExpsContext exps;
		public List<TerminalNode> PIPE() { return getTokens(TemplateGrammar.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(TemplateGrammar.PIPE, i);
		}
		public List<ExpsContext> exps() {
			return getRuleContexts(ExpsContext.class);
		}
		public ExpsContext exps(int i) {
			return getRuleContext(ExpsContext.class,i);
		}
		public OrExpsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public OrExpsContext(ParserRuleContext parent, int invokingState, TemplateType parentType, MaxMin firstAst) {
			super(parent, invokingState);
			this.parentType = parentType;
			this.firstAst = firstAst;
		}
		@Override public int getRuleIndex() { return RULE_orExps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterOrExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitOrExps(this);
		}
	}

	public final OrExpsContext orExps(TemplateType parentType,MaxMin firstAst) throws RecognitionException {
		OrExpsContext _localctx = new OrExpsContext(_ctx, getState(), parentType, firstAst);
		enterRule(_localctx, 20, RULE_orExps);
		int _la;
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PIPE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(119);
				match(PIPE);
				setState(120);
				((OrExpsContext)_localctx).exps = exps(parentType);
				 _localctx.list.add(firstAst); _localctx.list.add(((OrExpsContext)_localctx).exps.ast); 
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PIPE) {
					{
					{
					setState(122);
					match(PIPE);
					setState(123);
					((OrExpsContext)_localctx).exps = exps(parentType);
					_localctx.list.add(((OrExpsContext)_localctx).exps.ast);
					}
					}
					setState(130);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}

				        if( _localctx.list.isEmpty() ) ((OrExpsContext)_localctx).ast =  firstAst;
				        else {
				            var or = new AstOr(parentType);
				            for( var item : _localctx.list) {
				              item.addToBottomChildrenAndSet(getAst(item.bottom.type, null, false));
				            }
				            or.addTry(_localctx.list);
				            ((OrExpsContext)_localctx).ast =  new MaxMin(or);
				        }    
				    
				}
				break;
			case RBRACE:
			case DQUESTION:
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				 ((OrExpsContext)_localctx).ast =  firstAst; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpsContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin ast;
		public ExpContext exp;
		public ConcatenationContext concatenation;
		public MathContext math;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(TemplateGrammar.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TemplateGrammar.DOT, i);
		}
		public ConcatenationContext concatenation() {
			return getRuleContext(ConcatenationContext.class,0);
		}
		public MathContext math() {
			return getRuleContext(MathContext.class,0);
		}
		public ExpsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpsContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitExps(this);
		}
	}

	public final ExpsContext exps(TemplateType parentType) throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState(), parentType);
		enterRule(_localctx, 22, RULE_exps);
		int _la;
		try {
			int _alt;
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(136);
				((ExpsContext)_localctx).exp = exp(parentType);
				 ((ExpsContext)_localctx).ast =  ((ExpsContext)_localctx).exp.ast; 
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(138);
						match(DOT);
						setState(139);
						((ExpsContext)_localctx).exp = exp(_localctx.ast.bottom.type);
						_localctx.ast.addToBottomChildrenAndSet(((ExpsContext)_localctx).exp.ast);
						}
						} 
					}
					setState(146);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(147);
					match(DOT);
					}
				}

				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACE) {
					{
					setState(150);
					((ExpsContext)_localctx).concatenation = concatenation(parentType);
					}
				}

				 if( ((ExpsContext)_localctx).concatenation != null ) _localctx.ast.addToBottomChildrenAndSet( ((ExpsContext)_localctx).concatenation.ast ); 
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << SLASH) | (1L << PERCENT) | (1L << PLUS) | (1L << MINUS))) != 0)) {
					{
					setState(154);
					((ExpsContext)_localctx).math = math(parentType);
					}
				}

				 if( ((ExpsContext)_localctx).math != null ) _localctx.ast.addToBottomChildrenAndSet( ((ExpsContext)_localctx).math.ast ); 
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				((ExpsContext)_localctx).concatenation = concatenation(parentType);
				 ((ExpsContext)_localctx).ast =  new MaxMin( ((ExpsContext)_localctx).concatenation.ast ); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin ast;
		public Token ID;
		public TerminalNode ID() { return getToken(TemplateGrammar.ID, 0); }
		public TerminalNode LPAREN() { return getToken(TemplateGrammar.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TemplateGrammar.RPAREN, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp(TemplateType parentType) throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState(), parentType);
		enterRule(_localctx, 24, RULE_exp);
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(164);
				((ExpContext)_localctx).ID = match(ID);
				setState(165);
				match(LPAREN);
				setState(166);
				match(RPAREN);
				}
				 ((ExpContext)_localctx).ast =  getAst(_localctx.parentType, (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), true); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				((ExpContext)_localctx).ID = match(ID);
				 ((ExpContext)_localctx).ast =  getAst(_localctx.parentType, (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), false); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConcatenationContext extends ParserRuleContext {
		public TemplateType parentType;
		public AstConcatenation ast;
		public CitemsContext citems;
		public TerminalNode LBRACE() { return getToken(TemplateGrammar.LBRACE, 0); }
		public CitemsContext citems() {
			return getRuleContext(CitemsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TemplateGrammar.RBRACE, 0); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_concatenation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterConcatenation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitConcatenation(this);
		}
	}

	public final ConcatenationContext concatenation(TemplateType parentType) throws RecognitionException {
		ConcatenationContext _localctx = new ConcatenationContext(_ctx, getState(), parentType);
		enterRule(_localctx, 26, RULE_concatenation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(LBRACE);
			setState(174);
			((ConcatenationContext)_localctx).citems = citems(parentType);
			 ((ConcatenationContext)_localctx).ast =  new AstConcatenation(parentType, ((ConcatenationContext)_localctx).citems.list); 
			setState(176);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CitemsContext extends ParserRuleContext {
		public TemplateType parentType;
		public ArrayList<Ast> list = new ArrayList<Ast>();
		public CitemContext citem;
		public List<CitemContext> citem() {
			return getRuleContexts(CitemContext.class);
		}
		public CitemContext citem(int i) {
			return getRuleContext(CitemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammar.COMMA, i);
		}
		public CitemsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CitemsContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_citems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterCitems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitCitems(this);
		}
	}

	public final CitemsContext citems(TemplateType parentType) throws RecognitionException {
		CitemsContext _localctx = new CitemsContext(_ctx, getState(), parentType);
		enterRule(_localctx, 28, RULE_citems);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			((CitemsContext)_localctx).citem = citem(parentType);
			 _localctx.list.add(((CitemsContext)_localctx).citem.ast.top); ((CitemsContext)_localctx).citem.ast.addToBottomChildrenAndSet(getAst(((CitemsContext)_localctx).citem.ast.bottom.type, null, false)); 
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(180);
				match(COMMA);
				setState(181);
				((CitemsContext)_localctx).citem = citem(parentType);
				 _localctx.list.add(((CitemsContext)_localctx).citem.ast.top); ((CitemsContext)_localctx).citem.ast.addToBottomChildrenAndSet(getAst(((CitemsContext)_localctx).citem.ast.bottom.type, null, false)); 
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CitemContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin ast;
		public Token ID;
		public Token DSTRING;
		public Token SSTRING;
		public Token DECDIGITS;
		public Token FLOAT;
		public TerminalNode ID() { return getToken(TemplateGrammar.ID, 0); }
		public TerminalNode DSTRING() { return getToken(TemplateGrammar.DSTRING, 0); }
		public TerminalNode SSTRING() { return getToken(TemplateGrammar.SSTRING, 0); }
		public TerminalNode DECDIGITS() { return getToken(TemplateGrammar.DECDIGITS, 0); }
		public TerminalNode FLOAT() { return getToken(TemplateGrammar.FLOAT, 0); }
		public CitemContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CitemContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_citem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterCitem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitCitem(this);
		}
	}

	public final CitemContext citem(TemplateType parentType) throws RecognitionException {
		CitemContext _localctx = new CitemContext(_ctx, getState(), parentType);
		enterRule(_localctx, 30, RULE_citem);
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				((CitemContext)_localctx).ID = match(ID);
				 ((CitemContext)_localctx).ast =  getAst(_localctx.parentType, (((CitemContext)_localctx).ID!=null?((CitemContext)_localctx).ID.getText():null), false); 
				}
				break;
			case DSTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				((CitemContext)_localctx).DSTRING = match(DSTRING);
				 ((CitemContext)_localctx).ast =  new MaxMin(new AstText(sdStringToString((((CitemContext)_localctx).DSTRING!=null?((CitemContext)_localctx).DSTRING.getText():null)))); 
				}
				break;
			case SSTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				((CitemContext)_localctx).SSTRING = match(SSTRING);
				 ((CitemContext)_localctx).ast =  new MaxMin(new AstText(sdStringToString((((CitemContext)_localctx).SSTRING!=null?((CitemContext)_localctx).SSTRING.getText():null)))); 
				}
				break;
			case DECDIGITS:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				((CitemContext)_localctx).DECDIGITS = match(DECDIGITS);
				 ((CitemContext)_localctx).ast =  new MaxMin(new AstText(String.valueOf((((CitemContext)_localctx).DECDIGITS!=null?((CitemContext)_localctx).DECDIGITS.getText():null)))); 
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				((CitemContext)_localctx).FLOAT = match(FLOAT);
				 ((CitemContext)_localctx).ast =  new MaxMin(new AstText(String.valueOf((((CitemContext)_localctx).FLOAT!=null?((CitemContext)_localctx).FLOAT.getText():null)))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MathContext extends ParserRuleContext {
		public TemplateType parentType;
		public MaxMin ast;
		public MathOperationContext mathOperation;
		public NumberContext number;
		public MathOperationContext mathOperation() {
			return getRuleContext(MathOperationContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public MathContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MathContext(ParserRuleContext parent, int invokingState, TemplateType parentType) {
			super(parent, invokingState);
			this.parentType = parentType;
		}
		@Override public int getRuleIndex() { return RULE_math; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterMath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitMath(this);
		}
	}

	public final MathContext math(TemplateType parentType) throws RecognitionException {
		MathContext _localctx = new MathContext(_ctx, getState(), parentType);
		enterRule(_localctx, 32, RULE_math);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			((MathContext)_localctx).mathOperation = mathOperation();
			setState(202);
			((MathContext)_localctx).number = number();
			 ((MathContext)_localctx).ast =  new MaxMin(new AstMath(_localctx.parentType, (((MathContext)_localctx).mathOperation!=null?_input.getText(((MathContext)_localctx).mathOperation.start,((MathContext)_localctx).mathOperation.stop):null), (((MathContext)_localctx).number!=null?_input.getText(((MathContext)_localctx).number.start,((MathContext)_localctx).number.stop):null))); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DECDIGITS() { return getToken(TemplateGrammar.DECDIGITS, 0); }
		public TerminalNode FLOAT() { return getToken(TemplateGrammar.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_number);
		try {
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RBRACE:
			case PIPE:
			case DQUESTION:
			case SEMI:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case DECDIGITS:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(DECDIGITS);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(207);
				match(FLOAT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MathOperationContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(TemplateGrammar.STAR, 0); }
		public TerminalNode SLASH() { return getToken(TemplateGrammar.SLASH, 0); }
		public TerminalNode PERCENT() { return getToken(TemplateGrammar.PERCENT, 0); }
		public TerminalNode PLUS() { return getToken(TemplateGrammar.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(TemplateGrammar.MINUS, 0); }
		public MathOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).enterMathOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammarListener ) ((TemplateGrammarListener)listener).exitMathOperation(this);
		}
	}

	public final MathOperationContext mathOperation() throws RecognitionException {
		MathOperationContext _localctx = new MathOperationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_mathOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << SLASH) | (1L << PERCENT) | (1L << PLUS) | (1L << MINUS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00d7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63"+
		"\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4;\n\4\3\5\6\5>\n\5\r\5\16\5?\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6H\n\6\3\6\5\6K\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\\\n\b\3\t\3\t\3\t\3\t\5\tb\n\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\nm\n\n\f\n\16\np\13\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\5\13x\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0081\n\f\f"+
		"\f\16\f\u0084\13\f\3\f\3\f\3\f\5\f\u0089\n\f\3\r\3\r\3\r\3\r\3\r\3\r\7"+
		"\r\u0091\n\r\f\r\16\r\u0094\13\r\3\r\5\r\u0097\n\r\3\r\5\r\u009a\n\r\3"+
		"\r\3\r\5\r\u009e\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a5\n\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00ae\n\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u00bb\n\20\f\20\16\20\u00be\13\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ca\n\21\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\5\23\u00d3\n\23\3\24\3\24\3\24\2\2\25\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&\2\3\3\2\20\24\2\u00de\2(\3\2\2\2\4\61"+
		"\3\2\2\2\6:\3\2\2\2\b=\3\2\2\2\nA\3\2\2\2\fO\3\2\2\2\16[\3\2\2\2\20]\3"+
		"\2\2\2\22f\3\2\2\2\24w\3\2\2\2\26\u0088\3\2\2\2\30\u00a4\3\2\2\2\32\u00ad"+
		"\3\2\2\2\34\u00af\3\2\2\2\36\u00b4\3\2\2\2 \u00c9\3\2\2\2\"\u00cb\3\2"+
		"\2\2$\u00d2\3\2\2\2&\u00d4\3\2\2\2()\5\4\3\2)*\b\2\1\2*+\7\2\2\3+\3\3"+
		"\2\2\2,-\5\6\4\2-.\b\3\1\2.\60\3\2\2\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2"+
		"\2\2\61\62\3\2\2\2\62\5\3\2\2\2\63\61\3\2\2\2\64\65\5\b\5\2\65\66\b\4"+
		"\1\2\66;\3\2\2\2\678\5\n\6\289\b\4\1\29;\3\2\2\2:\64\3\2\2\2:\67\3\2\2"+
		"\2;\7\3\2\2\2<>\7\4\2\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\t\3\2"+
		"\2\2AB\7\3\2\2BC\5\30\r\2CD\b\6\1\2DE\5\26\f\2EG\b\6\1\2FH\5\f\7\2GF\3"+
		"\2\2\2GH\3\2\2\2HJ\3\2\2\2IK\5\20\t\2JI\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM"+
		"\b\6\1\2MN\7\6\2\2N\13\3\2\2\2OP\7\r\2\2PQ\5\16\b\2QR\b\7\1\2R\r\3\2\2"+
		"\2ST\7\27\2\2T\\\b\b\1\2UV\7\26\2\2V\\\b\b\1\2WX\7\30\2\2X\\\b\b\1\2Y"+
		"Z\7\31\2\2Z\\\b\b\1\2[S\3\2\2\2[U\3\2\2\2[W\3\2\2\2[Y\3\2\2\2\\\17\3\2"+
		"\2\2]^\7\16\2\2^_\7\25\2\2_a\7\t\2\2`b\5\22\n\2a`\3\2\2\2ab\3\2\2\2bc"+
		"\3\2\2\2cd\7\n\2\2de\b\t\1\2e\21\3\2\2\2fg\5\24\13\2gn\b\n\1\2hi\7\17"+
		"\2\2ij\5\24\13\2jk\b\n\1\2km\3\2\2\2lh\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3"+
		"\2\2\2o\23\3\2\2\2pn\3\2\2\2qr\7\30\2\2rx\b\13\1\2st\7\27\2\2tx\b\13\1"+
		"\2uv\7\26\2\2vx\b\13\1\2wq\3\2\2\2ws\3\2\2\2wu\3\2\2\2x\25\3\2\2\2yz\7"+
		"\7\2\2z{\5\30\r\2{\u0082\b\f\1\2|}\7\7\2\2}~\5\30\r\2~\177\b\f\1\2\177"+
		"\u0081\3\2\2\2\u0080|\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2"+
		"\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086"+
		"\b\f\1\2\u0086\u0089\3\2\2\2\u0087\u0089\b\f\1\2\u0088y\3\2\2\2\u0088"+
		"\u0087\3\2\2\2\u0089\27\3\2\2\2\u008a\u008b\5\32\16\2\u008b\u0092\b\r"+
		"\1\2\u008c\u008d\7\b\2\2\u008d\u008e\5\32\16\2\u008e\u008f\b\r\1\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008c\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0095"+
		"\u0097\7\b\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2"+
		"\2\2\u0098\u009a\5\34\17\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009d\b\r\1\2\u009c\u009e\5\"\22\2\u009d\u009c\3"+
		"\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\b\r\1\2\u00a0"+
		"\u00a5\3\2\2\2\u00a1\u00a2\5\34\17\2\u00a2\u00a3\b\r\1\2\u00a3\u00a5\3"+
		"\2\2\2\u00a4\u008a\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a5\31\3\2\2\2\u00a6"+
		"\u00a7\7\25\2\2\u00a7\u00a8\7\t\2\2\u00a8\u00a9\7\n\2\2\u00a9\u00aa\3"+
		"\2\2\2\u00aa\u00ae\b\16\1\2\u00ab\u00ac\7\25\2\2\u00ac\u00ae\b\16\1\2"+
		"\u00ad\u00a6\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\33\3\2\2\2\u00af\u00b0"+
		"\7\5\2\2\u00b0\u00b1\5\36\20\2\u00b1\u00b2\b\17\1\2\u00b2\u00b3\7\6\2"+
		"\2\u00b3\35\3\2\2\2\u00b4\u00b5\5 \21\2\u00b5\u00bc\b\20\1\2\u00b6\u00b7"+
		"\7\17\2\2\u00b7\u00b8\5 \21\2\u00b8\u00b9\b\20\1\2\u00b9\u00bb\3\2\2\2"+
		"\u00ba\u00b6\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\37\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\25\2\2\u00c0"+
		"\u00ca\b\21\1\2\u00c1\u00c2\7\26\2\2\u00c2\u00ca\b\21\1\2\u00c3\u00c4"+
		"\7\27\2\2\u00c4\u00ca\b\21\1\2\u00c5\u00c6\7\30\2\2\u00c6\u00ca\b\21\1"+
		"\2\u00c7\u00c8\7\31\2\2\u00c8\u00ca\b\21\1\2\u00c9\u00bf\3\2\2\2\u00c9"+
		"\u00c1\3\2\2\2\u00c9\u00c3\3\2\2\2\u00c9\u00c5\3\2\2\2\u00c9\u00c7\3\2"+
		"\2\2\u00ca!\3\2\2\2\u00cb\u00cc\5&\24\2\u00cc\u00cd\5$\23\2\u00cd\u00ce"+
		"\b\22\1\2\u00ce#\3\2\2\2\u00cf\u00d3\3\2\2\2\u00d0\u00d3\7\30\2\2\u00d1"+
		"\u00d3\7\31\2\2\u00d2\u00cf\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3"+
		"\2\2\2\u00d3%\3\2\2\2\u00d4\u00d5\t\2\2\2\u00d5\'\3\2\2\2\26\61:?GJ[a"+
		"nw\u0082\u0088\u0092\u0096\u0099\u009d\u00a4\u00ad\u00bc\u00c9\u00d2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
