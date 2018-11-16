// Generated from traceParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class traceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PTASK_TRACEPOINT=1, SCHED_SWITCH=2, EXIT_CLONE=3, ENTRY_CLONE=4, FIELD1=5, 
		FIELD2=6, FIELD3=7, FIELD4=8, FIELD5=9, FIELD6=10, FIELD7=11, FIELD8=12, 
		FIELD9=13, FIELD10=14, FIELD11=15, FIELD12=16, FIELD13=17, FIELD14=18, 
		FIELD15=19, FIELD16=20, FIELD18=21, FIELD19=22, FIELD20=23, FIELD21=24, 
		FIELD22=25, FIELD23=26, FIELD24=27, FIELD25=28, PTASK_STATE=29, PTASK_FLAG=30, 
		COMM=31, EQ=32, PLUS=33, DOT=34, COL=35, COM=36, LBRA=37, RBRA=38, LPAR=39, 
		RPAR=40, LCBRA=41, RCBRA=42, DELTA=43, INT=44, SINT=45, HEX=46, HOSTNAME=47, 
		WS=48;
	public static final int
		RULE_start = 0, RULE_timestamp = 1, RULE_trace = 2, RULE_header = 3, RULE_context = 4, 
		RULE_fields_exit_clone = 5, RULE_fields_entry_clone = 6, RULE_fields_sched_switch = 7, 
		RULE_fields_ptask_tracepoint = 8, RULE_sint = 9;
	public static final String[] ruleNames = {
		"start", "timestamp", "trace", "header", "context", "fields_exit_clone", 
		"fields_entry_clone", "fields_sched_switch", "fields_ptask_tracepoint", 
		"sint"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ptask_provider:ptask_tracepoint'", "'sched_switch'", "'syscall_exit_clone'", 
		"'syscall_entry_clone'", "'cpu_id'", "'pid'", "'tid'", "'ret'", "'clone_flags'", 
		"'newsp'", "'parent_tid'", "'child_tid'", "'prev_comm'", "'prev_tid'", 
		"'prev_prio'", "'prev_state'", "'next_comm'", "'next_tid'", "'next_prio'", 
		"'ptask_flag'", "'ptask_state'", "'ptask_pid'", "'ptask_tid'", "'ptask_index'", 
		"'ptask_time'", "'ptask_priority'", "'ptask_period'", "'ptask_deadline'", 
		null, null, "'\"ball\"'", "'='", "'+'", "'.'", "':'", "','", "'['", "']'", 
		"'('", "')'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PTASK_TRACEPOINT", "SCHED_SWITCH", "EXIT_CLONE", "ENTRY_CLONE", 
		"FIELD1", "FIELD2", "FIELD3", "FIELD4", "FIELD5", "FIELD6", "FIELD7", 
		"FIELD8", "FIELD9", "FIELD10", "FIELD11", "FIELD12", "FIELD13", "FIELD14", 
		"FIELD15", "FIELD16", "FIELD18", "FIELD19", "FIELD20", "FIELD21", "FIELD22", 
		"FIELD23", "FIELD24", "FIELD25", "PTASK_STATE", "PTASK_FLAG", "COMM", 
		"EQ", "PLUS", "DOT", "COL", "COM", "LBRA", "RBRA", "LPAR", "RPAR", "LCBRA", 
		"RCBRA", "DELTA", "INT", "SINT", "HEX", "HOSTNAME", "WS"
	};
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
	public String getGrammarFileName() { return "traceParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	 List<trace> traces = new ArrayList<>(); 
	public traceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(traceParser.EOF, 0); }
		public List<TimestampContext> timestamp() {
			return getRuleContexts(TimestampContext.class);
		}
		public TimestampContext timestamp(int i) {
			return getRuleContext(TimestampContext.class,i);
		}
		public List<TerminalNode> HOSTNAME() { return getTokens(traceParser.HOSTNAME); }
		public TerminalNode HOSTNAME(int i) {
			return getToken(traceParser.HOSTNAME, i);
		}
		public List<TraceContext> trace() {
			return getRuleContexts(TraceContext.class);
		}
		public TraceContext trace(int i) {
			return getRuleContext(TraceContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRA) {
				{
				{
				setState(20);
				timestamp();
				setState(21);
				match(HOSTNAME);
				setState(22);
				trace();
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
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

	public static class TimestampContext extends ParserRuleContext {
		public time_trace t;
		public Token h;
		public Token m;
		public Token s;
		public Token os;
		public Token delta;
		public TerminalNode LBRA() { return getToken(traceParser.LBRA, 0); }
		public List<TerminalNode> COL() { return getTokens(traceParser.COL); }
		public TerminalNode COL(int i) {
			return getToken(traceParser.COL, i);
		}
		public TerminalNode DOT() { return getToken(traceParser.DOT, 0); }
		public TerminalNode RBRA() { return getToken(traceParser.RBRA, 0); }
		public TerminalNode LPAR() { return getToken(traceParser.LPAR, 0); }
		public TerminalNode PLUS() { return getToken(traceParser.PLUS, 0); }
		public TerminalNode RPAR() { return getToken(traceParser.RPAR, 0); }
		public List<TerminalNode> INT() { return getTokens(traceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(traceParser.INT, i);
		}
		public TerminalNode DELTA() { return getToken(traceParser.DELTA, 0); }
		public TimestampContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timestamp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterTimestamp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitTimestamp(this);
		}
	}

	public final TimestampContext timestamp() throws RecognitionException {
		TimestampContext _localctx = new TimestampContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_timestamp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(31);
			match(LBRA);
			setState(32);
			((TimestampContext)_localctx).h = match(INT);
			setState(33);
			match(COL);
			setState(34);
			((TimestampContext)_localctx).m = match(INT);
			setState(35);
			match(COL);
			setState(36);
			((TimestampContext)_localctx).s = match(INT);
			setState(37);
			match(DOT);
			setState(38);
			((TimestampContext)_localctx).os = match(INT);
			setState(39);
			match(RBRA);
			setState(40);
			match(LPAR);
			setState(41);
			match(PLUS);
			setState(42);
			((TimestampContext)_localctx).delta = match(DELTA);
			setState(43);
			match(RPAR);
			}
			 ((TimestampContext)_localctx).t =  new time_trace((((TimestampContext)_localctx).h!=null?Integer.valueOf(((TimestampContext)_localctx).h.getText()):0),(((TimestampContext)_localctx).m!=null?Integer.valueOf(((TimestampContext)_localctx).m.getText()):0),(((TimestampContext)_localctx).s!=null?Integer.valueOf(((TimestampContext)_localctx).s.getText()):0),(((TimestampContext)_localctx).os!=null?Integer.valueOf(((TimestampContext)_localctx).os.getText()):0),(((TimestampContext)_localctx).delta!=null?((TimestampContext)_localctx).delta.getText():null)); 
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

	public static class TraceContext extends ParserRuleContext {
		public trace tr;
		public TerminalNode ENTRY_CLONE() { return getToken(traceParser.ENTRY_CLONE, 0); }
		public TerminalNode COL() { return getToken(traceParser.COL, 0); }
		public List<TerminalNode> LCBRA() { return getTokens(traceParser.LCBRA); }
		public TerminalNode LCBRA(int i) {
			return getToken(traceParser.LCBRA, i);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<TerminalNode> RCBRA() { return getTokens(traceParser.RCBRA); }
		public TerminalNode RCBRA(int i) {
			return getToken(traceParser.RCBRA, i);
		}
		public List<TerminalNode> COM() { return getTokens(traceParser.COM); }
		public TerminalNode COM(int i) {
			return getToken(traceParser.COM, i);
		}
		public ContextContext context() {
			return getRuleContext(ContextContext.class,0);
		}
		public Fields_entry_cloneContext fields_entry_clone() {
			return getRuleContext(Fields_entry_cloneContext.class,0);
		}
		public TerminalNode EXIT_CLONE() { return getToken(traceParser.EXIT_CLONE, 0); }
		public Fields_exit_cloneContext fields_exit_clone() {
			return getRuleContext(Fields_exit_cloneContext.class,0);
		}
		public TerminalNode SCHED_SWITCH() { return getToken(traceParser.SCHED_SWITCH, 0); }
		public Fields_sched_switchContext fields_sched_switch() {
			return getRuleContext(Fields_sched_switchContext.class,0);
		}
		public TerminalNode PTASK_TRACEPOINT() { return getToken(traceParser.PTASK_TRACEPOINT, 0); }
		public Fields_ptask_tracepointContext fields_ptask_tracepoint() {
			return getRuleContext(Fields_ptask_tracepointContext.class,0);
		}
		public TraceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterTrace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitTrace(this);
		}
	}

	public final TraceContext trace() throws RecognitionException {
		TraceContext _localctx = new TraceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_trace);
		try {
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENTRY_CLONE:
				enterOuterAlt(_localctx, 1);
				{
				 ((TraceContext)_localctx).tr =  new clone_entry(); 
				setState(48);
				match(ENTRY_CLONE);
				setState(49);
				match(COL);
				setState(50);
				match(LCBRA);
				setState(51);
				header();
				setState(52);
				match(RCBRA);
				setState(53);
				match(COM);
				setState(54);
				match(LCBRA);
				setState(55);
				context();
				setState(56);
				match(RCBRA);
				setState(57);
				match(COM);
				setState(58);
				match(LCBRA);
				setState(59);
				fields_entry_clone();
				setState(60);
				match(RCBRA);
				}
				break;
			case EXIT_CLONE:
				enterOuterAlt(_localctx, 2);
				{
				 ((TraceContext)_localctx).tr =  new clone_exit(); 
				setState(63);
				match(EXIT_CLONE);
				setState(64);
				match(COL);
				setState(65);
				match(LCBRA);
				setState(66);
				header();
				setState(67);
				match(RCBRA);
				setState(68);
				match(COM);
				setState(69);
				match(LCBRA);
				setState(70);
				context();
				setState(71);
				match(RCBRA);
				setState(72);
				match(COM);
				setState(73);
				match(LCBRA);
				setState(74);
				fields_exit_clone();
				setState(75);
				match(RCBRA);
				}
				break;
			case SCHED_SWITCH:
				enterOuterAlt(_localctx, 3);
				{
				 ((TraceContext)_localctx).tr =  new sched_switch(); 
				setState(78);
				match(SCHED_SWITCH);
				setState(79);
				match(COL);
				setState(80);
				match(LCBRA);
				setState(81);
				header();
				setState(82);
				match(RCBRA);
				setState(83);
				match(COM);
				setState(84);
				match(LCBRA);
				setState(85);
				context();
				setState(86);
				match(RCBRA);
				setState(87);
				match(COM);
				setState(88);
				match(LCBRA);
				setState(89);
				fields_sched_switch();
				setState(90);
				match(RCBRA);
				}
				break;
			case PTASK_TRACEPOINT:
				enterOuterAlt(_localctx, 4);
				{
				 ((TraceContext)_localctx).tr =  new ptask_tracepoint(); 
				setState(93);
				match(PTASK_TRACEPOINT);
				setState(94);
				match(COL);
				setState(95);
				match(LCBRA);
				setState(96);
				header();
				setState(97);
				match(RCBRA);
				setState(98);
				match(COM);
				setState(99);
				match(LCBRA);
				setState(100);
				fields_ptask_tracepoint();
				setState(101);
				match(RCBRA);
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

	public static class HeaderContext extends ParserRuleContext {
		public Integer cpu_id;
		public Token cpuid;
		public TerminalNode FIELD1() { return getToken(traceParser.FIELD1, 0); }
		public TerminalNode EQ() { return getToken(traceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(traceParser.INT, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(FIELD1);
			setState(106);
			match(EQ);
			setState(107);
			((HeaderContext)_localctx).cpuid = match(INT);
			 ((HeaderContext)_localctx).cpu_id =  (((HeaderContext)_localctx).cpuid!=null?Integer.valueOf(((HeaderContext)_localctx).cpuid.getText()):0); 
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

	public static class ContextContext extends ParserRuleContext {
		public List<Integer> cntx;
		public Token pid;
		public Token tid;
		public TerminalNode FIELD2() { return getToken(traceParser.FIELD2, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public TerminalNode COM() { return getToken(traceParser.COM, 0); }
		public TerminalNode FIELD3() { return getToken(traceParser.FIELD3, 0); }
		public List<TerminalNode> INT() { return getTokens(traceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(traceParser.INT, i);
		}
		public ContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_context; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterContext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitContext(this);
		}
	}

	public final ContextContext context() throws RecognitionException {
		ContextContext _localctx = new ContextContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_context);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(FIELD2);
			setState(111);
			match(EQ);
			setState(112);
			((ContextContext)_localctx).pid = match(INT);
			setState(113);
			match(COM);
			setState(114);
			match(FIELD3);
			setState(115);
			match(EQ);
			setState(116);
			((ContextContext)_localctx).tid = match(INT);
			 ((ContextContext)_localctx).cntx =  new ArrayList(2);
					_localctx.cntx.add((((ContextContext)_localctx).pid!=null?Integer.valueOf(((ContextContext)_localctx).pid.getText()):0));
					_localctx.cntx.add((((ContextContext)_localctx).tid!=null?Integer.valueOf(((ContextContext)_localctx).tid.getText()):0));
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

	public static class Fields_exit_cloneContext extends ParserRuleContext {
		public Integer field_exc;
		public Token ret;
		public TerminalNode FIELD4() { return getToken(traceParser.FIELD4, 0); }
		public TerminalNode EQ() { return getToken(traceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(traceParser.INT, 0); }
		public Fields_exit_cloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields_exit_clone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterFields_exit_clone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitFields_exit_clone(this);
		}
	}

	public final Fields_exit_cloneContext fields_exit_clone() throws RecognitionException {
		Fields_exit_cloneContext _localctx = new Fields_exit_cloneContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fields_exit_clone);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(FIELD4);
			setState(120);
			match(EQ);
			setState(121);
			((Fields_exit_cloneContext)_localctx).ret = match(INT);
			((Fields_exit_cloneContext)_localctx).field_exc =  (((Fields_exit_cloneContext)_localctx).ret!=null?Integer.valueOf(((Fields_exit_cloneContext)_localctx).ret.getText()):0); 
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

	public static class Fields_entry_cloneContext extends ParserRuleContext {
		public List<String> field_enc;
		public Token clone_flag;
		public Token newsp;
		public Token ptid;
		public Token ctid;
		public TerminalNode FIELD5() { return getToken(traceParser.FIELD5, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public List<TerminalNode> COM() { return getTokens(traceParser.COM); }
		public TerminalNode COM(int i) {
			return getToken(traceParser.COM, i);
		}
		public TerminalNode FIELD6() { return getToken(traceParser.FIELD6, 0); }
		public TerminalNode FIELD7() { return getToken(traceParser.FIELD7, 0); }
		public TerminalNode FIELD8() { return getToken(traceParser.FIELD8, 0); }
		public List<TerminalNode> HEX() { return getTokens(traceParser.HEX); }
		public TerminalNode HEX(int i) {
			return getToken(traceParser.HEX, i);
		}
		public Fields_entry_cloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields_entry_clone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterFields_entry_clone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitFields_entry_clone(this);
		}
	}

	public final Fields_entry_cloneContext fields_entry_clone() throws RecognitionException {
		Fields_entry_cloneContext _localctx = new Fields_entry_cloneContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fields_entry_clone);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(FIELD5);
			setState(125);
			match(EQ);
			setState(126);
			((Fields_entry_cloneContext)_localctx).clone_flag = match(HEX);
			setState(127);
			match(COM);
			setState(128);
			match(FIELD6);
			setState(129);
			match(EQ);
			setState(130);
			((Fields_entry_cloneContext)_localctx).newsp = match(HEX);
			setState(131);
			match(COM);
			setState(132);
			match(FIELD7);
			setState(133);
			match(EQ);
			setState(134);
			((Fields_entry_cloneContext)_localctx).ptid = match(HEX);
			setState(135);
			match(COM);
			setState(136);
			match(FIELD8);
			setState(137);
			match(EQ);
			setState(138);
			((Fields_entry_cloneContext)_localctx).ctid = match(HEX);
			 ((Fields_entry_cloneContext)_localctx).field_enc =  new ArrayList(4);
					_localctx.field_enc.add((((Fields_entry_cloneContext)_localctx).clone_flag!=null?((Fields_entry_cloneContext)_localctx).clone_flag.getText():null));
					_localctx.field_enc.add((((Fields_entry_cloneContext)_localctx).newsp!=null?((Fields_entry_cloneContext)_localctx).newsp.getText():null));
					_localctx.field_enc.add((((Fields_entry_cloneContext)_localctx).ptid!=null?((Fields_entry_cloneContext)_localctx).ptid.getText():null));
					_localctx.field_enc.add((((Fields_entry_cloneContext)_localctx).ctid!=null?((Fields_entry_cloneContext)_localctx).ctid.getText():null)); 
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

	public static class Fields_sched_switchContext extends ParserRuleContext {
		public List<Object> field_ss;
		public Token comm;
		public Token prev_tid;
		public SintContext prev_prio;
		public Token prev_state;
		public Token next_tid;
		public SintContext next_prio;
		public TerminalNode FIELD9() { return getToken(traceParser.FIELD9, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public List<TerminalNode> COM() { return getTokens(traceParser.COM); }
		public TerminalNode COM(int i) {
			return getToken(traceParser.COM, i);
		}
		public TerminalNode FIELD10() { return getToken(traceParser.FIELD10, 0); }
		public TerminalNode FIELD11() { return getToken(traceParser.FIELD11, 0); }
		public TerminalNode FIELD12() { return getToken(traceParser.FIELD12, 0); }
		public TerminalNode FIELD13() { return getToken(traceParser.FIELD13, 0); }
		public TerminalNode FIELD14() { return getToken(traceParser.FIELD14, 0); }
		public TerminalNode FIELD15() { return getToken(traceParser.FIELD15, 0); }
		public List<TerminalNode> COMM() { return getTokens(traceParser.COMM); }
		public TerminalNode COMM(int i) {
			return getToken(traceParser.COMM, i);
		}
		public List<TerminalNode> INT() { return getTokens(traceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(traceParser.INT, i);
		}
		public List<SintContext> sint() {
			return getRuleContexts(SintContext.class);
		}
		public SintContext sint(int i) {
			return getRuleContext(SintContext.class,i);
		}
		public Fields_sched_switchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields_sched_switch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterFields_sched_switch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitFields_sched_switch(this);
		}
	}

	public final Fields_sched_switchContext fields_sched_switch() throws RecognitionException {
		Fields_sched_switchContext _localctx = new Fields_sched_switchContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fields_sched_switch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(FIELD9);
			setState(142);
			match(EQ);
			setState(143);
			((Fields_sched_switchContext)_localctx).comm = match(COMM);
			setState(144);
			match(COM);
			setState(145);
			match(FIELD10);
			setState(146);
			match(EQ);
			setState(147);
			((Fields_sched_switchContext)_localctx).prev_tid = match(INT);
			setState(148);
			match(COM);
			setState(149);
			match(FIELD11);
			setState(150);
			match(EQ);
			setState(151);
			((Fields_sched_switchContext)_localctx).prev_prio = sint();
			setState(152);
			match(COM);
			setState(153);
			match(FIELD12);
			setState(154);
			match(EQ);
			setState(155);
			((Fields_sched_switchContext)_localctx).prev_state = match(INT);
			setState(156);
			match(COM);
			setState(157);
			match(FIELD13);
			setState(158);
			match(EQ);
			setState(159);
			((Fields_sched_switchContext)_localctx).comm = match(COMM);
			setState(160);
			match(COM);
			setState(161);
			match(FIELD14);
			setState(162);
			match(EQ);
			setState(163);
			((Fields_sched_switchContext)_localctx).next_tid = match(INT);
			setState(164);
			match(COM);
			setState(165);
			match(FIELD15);
			setState(166);
			match(EQ);
			setState(167);
			((Fields_sched_switchContext)_localctx).next_prio = sint();
			 ((Fields_sched_switchContext)_localctx).field_ss =  new ArrayList(7); 
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).comm!=null?((Fields_sched_switchContext)_localctx).comm.getText():null));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).prev_tid!=null?Integer.valueOf(((Fields_sched_switchContext)_localctx).prev_tid.getText()):0));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).prev_prio!=null?_input.getText(((Fields_sched_switchContext)_localctx).prev_prio.start,((Fields_sched_switchContext)_localctx).prev_prio.stop):null));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).prev_state!=null?Integer.valueOf(((Fields_sched_switchContext)_localctx).prev_state.getText()):0));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).comm!=null?((Fields_sched_switchContext)_localctx).comm.getText():null));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).next_tid!=null?Integer.valueOf(((Fields_sched_switchContext)_localctx).next_tid.getText()):0));
					_localctx.field_ss.add((Object)(((Fields_sched_switchContext)_localctx).next_prio!=null?_input.getText(((Fields_sched_switchContext)_localctx).next_prio.start,((Fields_sched_switchContext)_localctx).next_prio.stop):null)); 
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

	public static class Fields_ptask_tracepointContext extends ParserRuleContext {
		public List<Object> field_pt;
		public Token ptask_flag;
		public Token ptask_state;
		public Token pid;
		public Token tid;
		public Token ptask_index;
		public Token times;
		public Token ptask_prio;
		public Token ptask_peri;
		public Token ptask_dead;
		public TerminalNode FIELD16() { return getToken(traceParser.FIELD16, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public List<TerminalNode> COM() { return getTokens(traceParser.COM); }
		public TerminalNode COM(int i) {
			return getToken(traceParser.COM, i);
		}
		public TerminalNode FIELD18() { return getToken(traceParser.FIELD18, 0); }
		public TerminalNode FIELD19() { return getToken(traceParser.FIELD19, 0); }
		public TerminalNode FIELD20() { return getToken(traceParser.FIELD20, 0); }
		public TerminalNode FIELD21() { return getToken(traceParser.FIELD21, 0); }
		public TerminalNode FIELD22() { return getToken(traceParser.FIELD22, 0); }
		public TerminalNode FIELD23() { return getToken(traceParser.FIELD23, 0); }
		public TerminalNode FIELD24() { return getToken(traceParser.FIELD24, 0); }
		public TerminalNode FIELD25() { return getToken(traceParser.FIELD25, 0); }
		public TerminalNode PTASK_FLAG() { return getToken(traceParser.PTASK_FLAG, 0); }
		public TerminalNode PTASK_STATE() { return getToken(traceParser.PTASK_STATE, 0); }
		public List<TerminalNode> INT() { return getTokens(traceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(traceParser.INT, i);
		}
		public Fields_ptask_tracepointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields_ptask_tracepoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterFields_ptask_tracepoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitFields_ptask_tracepoint(this);
		}
	}

	public final Fields_ptask_tracepointContext fields_ptask_tracepoint() throws RecognitionException {
		Fields_ptask_tracepointContext _localctx = new Fields_ptask_tracepointContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fields_ptask_tracepoint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(FIELD16);
			setState(171);
			match(EQ);
			setState(172);
			((Fields_ptask_tracepointContext)_localctx).ptask_flag = match(PTASK_FLAG);
			setState(173);
			match(COM);
			setState(174);
			match(FIELD18);
			setState(175);
			match(EQ);
			setState(176);
			((Fields_ptask_tracepointContext)_localctx).ptask_state = match(PTASK_STATE);
			setState(177);
			match(COM);
			setState(178);
			match(FIELD19);
			setState(179);
			match(EQ);
			setState(180);
			((Fields_ptask_tracepointContext)_localctx).pid = match(INT);
			setState(181);
			match(COM);
			setState(182);
			match(FIELD20);
			setState(183);
			match(EQ);
			setState(184);
			((Fields_ptask_tracepointContext)_localctx).tid = match(INT);
			setState(185);
			match(COM);
			setState(186);
			match(FIELD21);
			setState(187);
			match(EQ);
			setState(188);
			((Fields_ptask_tracepointContext)_localctx).ptask_index = match(INT);
			setState(189);
			match(COM);
			setState(190);
			match(FIELD22);
			setState(191);
			match(EQ);
			setState(192);
			((Fields_ptask_tracepointContext)_localctx).times = match(INT);
			setState(193);
			match(COM);
			setState(194);
			match(FIELD23);
			setState(195);
			match(EQ);
			setState(196);
			((Fields_ptask_tracepointContext)_localctx).ptask_prio = match(INT);
			setState(197);
			match(COM);
			setState(198);
			match(FIELD24);
			setState(199);
			match(EQ);
			setState(200);
			((Fields_ptask_tracepointContext)_localctx).ptask_peri = match(INT);
			setState(201);
			match(COM);
			setState(202);
			match(FIELD25);
			setState(203);
			match(EQ);
			setState(204);
			((Fields_ptask_tracepointContext)_localctx).ptask_dead = match(INT);
			 ((Fields_ptask_tracepointContext)_localctx).field_pt =  new ArrayList(7); 
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_flag!=null?((Fields_ptask_tracepointContext)_localctx).ptask_flag.getText():null));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_state!=null?((Fields_ptask_tracepointContext)_localctx).ptask_state.getText():null));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).pid!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).pid.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).tid!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).tid.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_index!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).ptask_index.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).times!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).times.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_prio!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).ptask_prio.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_peri!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).ptask_peri.getText()):0));
					_localctx.field_pt.add((Object)(((Fields_ptask_tracepointContext)_localctx).ptask_dead!=null?Integer.valueOf(((Fields_ptask_tracepointContext)_localctx).ptask_dead.getText()):0)); 
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

	public static class SintContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(traceParser.INT, 0); }
		public TerminalNode SINT() { return getToken(traceParser.SINT, 0); }
		public SintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterSint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitSint(this);
		}
	}

	public final SintContext sint() throws RecognitionException {
		SintContext _localctx = new SintContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==SINT) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u00d4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"j\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\3\3\2./\2\u00cd\2\34\3\2\2"+
		"\2\4!\3\2\2\2\6i\3\2\2\2\bk\3\2\2\2\np\3\2\2\2\fy\3\2\2\2\16~\3\2\2\2"+
		"\20\u008f\3\2\2\2\22\u00ac\3\2\2\2\24\u00d1\3\2\2\2\26\27\5\4\3\2\27\30"+
		"\7\61\2\2\30\31\5\6\4\2\31\33\3\2\2\2\32\26\3\2\2\2\33\36\3\2\2\2\34\32"+
		"\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\34\3\2\2\2\37 \7\2\2\3 \3\3\2"+
		"\2\2!\"\7\'\2\2\"#\7.\2\2#$\7%\2\2$%\7.\2\2%&\7%\2\2&\'\7.\2\2\'(\7$\2"+
		"\2()\7.\2\2)*\7(\2\2*+\7)\2\2+,\7#\2\2,-\7-\2\2-.\7*\2\2./\3\2\2\2/\60"+
		"\b\3\1\2\60\5\3\2\2\2\61\62\b\4\1\2\62\63\7\6\2\2\63\64\7%\2\2\64\65\7"+
		"+\2\2\65\66\5\b\5\2\66\67\7,\2\2\678\7&\2\289\7+\2\29:\5\n\6\2:;\7,\2"+
		"\2;<\7&\2\2<=\7+\2\2=>\5\16\b\2>?\7,\2\2?j\3\2\2\2@A\b\4\1\2AB\7\5\2\2"+
		"BC\7%\2\2CD\7+\2\2DE\5\b\5\2EF\7,\2\2FG\7&\2\2GH\7+\2\2HI\5\n\6\2IJ\7"+
		",\2\2JK\7&\2\2KL\7+\2\2LM\5\f\7\2MN\7,\2\2Nj\3\2\2\2OP\b\4\1\2PQ\7\4\2"+
		"\2QR\7%\2\2RS\7+\2\2ST\5\b\5\2TU\7,\2\2UV\7&\2\2VW\7+\2\2WX\5\n\6\2XY"+
		"\7,\2\2YZ\7&\2\2Z[\7+\2\2[\\\5\20\t\2\\]\7,\2\2]j\3\2\2\2^_\b\4\1\2_`"+
		"\7\3\2\2`a\7%\2\2ab\7+\2\2bc\5\b\5\2cd\7,\2\2de\7&\2\2ef\7+\2\2fg\5\22"+
		"\n\2gh\7,\2\2hj\3\2\2\2i\61\3\2\2\2i@\3\2\2\2iO\3\2\2\2i^\3\2\2\2j\7\3"+
		"\2\2\2kl\7\7\2\2lm\7\"\2\2mn\7.\2\2no\b\5\1\2o\t\3\2\2\2pq\7\b\2\2qr\7"+
		"\"\2\2rs\7.\2\2st\7&\2\2tu\7\t\2\2uv\7\"\2\2vw\7.\2\2wx\b\6\1\2x\13\3"+
		"\2\2\2yz\7\n\2\2z{\7\"\2\2{|\7.\2\2|}\b\7\1\2}\r\3\2\2\2~\177\7\13\2\2"+
		"\177\u0080\7\"\2\2\u0080\u0081\7\60\2\2\u0081\u0082\7&\2\2\u0082\u0083"+
		"\7\f\2\2\u0083\u0084\7\"\2\2\u0084\u0085\7\60\2\2\u0085\u0086\7&\2\2\u0086"+
		"\u0087\7\r\2\2\u0087\u0088\7\"\2\2\u0088\u0089\7\60\2\2\u0089\u008a\7"+
		"&\2\2\u008a\u008b\7\16\2\2\u008b\u008c\7\"\2\2\u008c\u008d\7\60\2\2\u008d"+
		"\u008e\b\b\1\2\u008e\17\3\2\2\2\u008f\u0090\7\17\2\2\u0090\u0091\7\"\2"+
		"\2\u0091\u0092\7!\2\2\u0092\u0093\7&\2\2\u0093\u0094\7\20\2\2\u0094\u0095"+
		"\7\"\2\2\u0095\u0096\7.\2\2\u0096\u0097\7&\2\2\u0097\u0098\7\21\2\2\u0098"+
		"\u0099\7\"\2\2\u0099\u009a\5\24\13\2\u009a\u009b\7&\2\2\u009b\u009c\7"+
		"\22\2\2\u009c\u009d\7\"\2\2\u009d\u009e\7.\2\2\u009e\u009f\7&\2\2\u009f"+
		"\u00a0\7\23\2\2\u00a0\u00a1\7\"\2\2\u00a1\u00a2\7!\2\2\u00a2\u00a3\7&"+
		"\2\2\u00a3\u00a4\7\24\2\2\u00a4\u00a5\7\"\2\2\u00a5\u00a6\7.\2\2\u00a6"+
		"\u00a7\7&\2\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\7\"\2\2\u00a9\u00aa\5\24"+
		"\13\2\u00aa\u00ab\b\t\1\2\u00ab\21\3\2\2\2\u00ac\u00ad\7\26\2\2\u00ad"+
		"\u00ae\7\"\2\2\u00ae\u00af\7 \2\2\u00af\u00b0\7&\2\2\u00b0\u00b1\7\27"+
		"\2\2\u00b1\u00b2\7\"\2\2\u00b2\u00b3\7\37\2\2\u00b3\u00b4\7&\2\2\u00b4"+
		"\u00b5\7\30\2\2\u00b5\u00b6\7\"\2\2\u00b6\u00b7\7.\2\2\u00b7\u00b8\7&"+
		"\2\2\u00b8\u00b9\7\31\2\2\u00b9\u00ba\7\"\2\2\u00ba\u00bb\7.\2\2\u00bb"+
		"\u00bc\7&\2\2\u00bc\u00bd\7\32\2\2\u00bd\u00be\7\"\2\2\u00be\u00bf\7."+
		"\2\2\u00bf\u00c0\7&\2\2\u00c0\u00c1\7\33\2\2\u00c1\u00c2\7\"\2\2\u00c2"+
		"\u00c3\7.\2\2\u00c3\u00c4\7&\2\2\u00c4\u00c5\7\34\2\2\u00c5\u00c6\7\""+
		"\2\2\u00c6\u00c7\7.\2\2\u00c7\u00c8\7&\2\2\u00c8\u00c9\7\35\2\2\u00c9"+
		"\u00ca\7\"\2\2\u00ca\u00cb\7.\2\2\u00cb\u00cc\7&\2\2\u00cc\u00cd\7\36"+
		"\2\2\u00cd\u00ce\7\"\2\2\u00ce\u00cf\7.\2\2\u00cf\u00d0\b\n\1\2\u00d0"+
		"\23\3\2\2\2\u00d1\u00d2\t\2\2\2\u00d2\25\3\2\2\2\4\34i";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}