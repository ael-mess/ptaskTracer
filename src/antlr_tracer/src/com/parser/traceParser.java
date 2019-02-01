// Generated from com/parser/traceParser.g4 by ANTLR 4.7.1

package com.parser;
import com.event.*;

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
		EQ=1, PLUS=2, DOT=3, COL=4, COM=5, LBRA=6, RBRA=7, LPAR=8, RPAR=9, LCBRA=10, 
		RCBRA=11, QUOT=12, PTASK_TRACEPOINT=13, SCHED_SWITCH=14, EXIT_CLONE=15, 
		ENTRY_CLONE=16, CPU_ID=17, PID=18, TID=19, RET=20, CLONE_FLAG=21, NEWSP=22, 
		PARENT_TID=23, CHILD_TID=24, PREV_COMM=25, PREV_TID=26, PREV_PRIO=27, 
		PREV_STATE=28, NEXT_COMM=29, NEXT_TID=30, NEXT_PRIO=31, PTASK_FLAG_F=32, 
		PTASK_STATE_F=33, PTASK_PID=34, PTASK_TID=35, PTASK_INDEX=36, PTASK_TIME=37, 
		PTASK_PRIORITY=38, PTASK_PERIOD=39, PTASK_DEADLINE=40, PTASK_STATE=41, 
		PTASK_FLAG=42, DELTA=43, INT=44, SINT=45, HEX=46, ID=47, HOSTNAME=48, 
		WS=49;
	public static final int
		RULE_start = 0, RULE_trace = 1, RULE_timestamp = 2, RULE_header = 3, RULE_context = 4, 
		RULE_comm = 5, RULE_sint = 6;
	public static final String[] ruleNames = {
		"start", "trace", "timestamp", "header", "context", "comm", "sint"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'+'", "'.'", "':'", "','", "'['", "']'", "'('", "')'", "'{'", 
		"'}'", "'\"'", "'ptask_provider:ptask_tracepoint'", "'sched_switch'", 
		"'syscall_exit_clone'", "'syscall_entry_clone'", "'cpu_id'", "'pid'", 
		"'tid'", "'ret'", "'clone_flags'", "'newsp'", "'parent_tid'", "'child_tid'", 
		"'prev_comm'", "'prev_tid'", "'prev_prio'", "'prev_state'", "'next_comm'", 
		"'next_tid'", "'next_prio'", "'ptask_flag'", "'ptask_state'", "'ptask_pid'", 
		"'ptask_tid'", "'ptask_index'", "'ptask_time'", "'ptask_priority'", "'ptask_period'", 
		"'ptask_deadline'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "EQ", "PLUS", "DOT", "COL", "COM", "LBRA", "RBRA", "LPAR", "RPAR", 
		"LCBRA", "RCBRA", "QUOT", "PTASK_TRACEPOINT", "SCHED_SWITCH", "EXIT_CLONE", 
		"ENTRY_CLONE", "CPU_ID", "PID", "TID", "RET", "CLONE_FLAG", "NEWSP", "PARENT_TID", 
		"CHILD_TID", "PREV_COMM", "PREV_TID", "PREV_PRIO", "PREV_STATE", "NEXT_COMM", 
		"NEXT_TID", "NEXT_PRIO", "PTASK_FLAG_F", "PTASK_STATE_F", "PTASK_PID", 
		"PTASK_TID", "PTASK_INDEX", "PTASK_TIME", "PTASK_PRIORITY", "PTASK_PERIOD", 
		"PTASK_DEADLINE", "PTASK_STATE", "PTASK_FLAG", "DELTA", "INT", "SINT", 
		"HEX", "ID", "HOSTNAME", "WS"
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

	public traceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public List<trace> traces;
		public TraceContext trace;
		public TerminalNode EOF() { return getToken(traceParser.EOF, 0); }
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
			 ((StartContext)_localctx).traces =  new ArrayList<>(); 
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRA) {
				{
				{
				setState(15);
				((StartContext)_localctx).trace = trace();
				 _localctx.traces.add(((StartContext)_localctx).trace.tr); 
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
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

	public static class TraceContext extends ParserRuleContext {
		public trace tr;
		public TimestampContext timestamp;
		public HeaderContext header;
		public ContextContext context;
		public Token clone_flag;
		public Token newsp;
		public Token ptid;
		public Token ctid;
		public Token ret;
		public CommContext prev_comm;
		public Token prev_tid;
		public SintContext prev_prio;
		public Token prev_state;
		public CommContext next_comm;
		public Token next_tid;
		public SintContext next_prio;
		public Token pid;
		public Token tid;
		public Token ptask_index;
		public Token ptask_flag;
		public Token ptask_state;
		public Token times;
		public Token ptask_prio;
		public Token ptask_peri;
		public Token ptask_dead;
		public TimestampContext timestamp() {
			return getRuleContext(TimestampContext.class,0);
		}
		public TerminalNode HOSTNAME() { return getToken(traceParser.HOSTNAME, 0); }
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
		public TerminalNode CLONE_FLAG() { return getToken(traceParser.CLONE_FLAG, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public TerminalNode NEWSP() { return getToken(traceParser.NEWSP, 0); }
		public TerminalNode PARENT_TID() { return getToken(traceParser.PARENT_TID, 0); }
		public TerminalNode CHILD_TID() { return getToken(traceParser.CHILD_TID, 0); }
		public List<TerminalNode> HEX() { return getTokens(traceParser.HEX); }
		public TerminalNode HEX(int i) {
			return getToken(traceParser.HEX, i);
		}
		public TerminalNode EXIT_CLONE() { return getToken(traceParser.EXIT_CLONE, 0); }
		public TerminalNode RET() { return getToken(traceParser.RET, 0); }
		public List<TerminalNode> INT() { return getTokens(traceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(traceParser.INT, i);
		}
		public TerminalNode SCHED_SWITCH() { return getToken(traceParser.SCHED_SWITCH, 0); }
		public TerminalNode PREV_COMM() { return getToken(traceParser.PREV_COMM, 0); }
		public TerminalNode PREV_TID() { return getToken(traceParser.PREV_TID, 0); }
		public TerminalNode PREV_PRIO() { return getToken(traceParser.PREV_PRIO, 0); }
		public TerminalNode PREV_STATE() { return getToken(traceParser.PREV_STATE, 0); }
		public TerminalNode NEXT_COMM() { return getToken(traceParser.NEXT_COMM, 0); }
		public TerminalNode NEXT_TID() { return getToken(traceParser.NEXT_TID, 0); }
		public TerminalNode NEXT_PRIO() { return getToken(traceParser.NEXT_PRIO, 0); }
		public List<CommContext> comm() {
			return getRuleContexts(CommContext.class);
		}
		public CommContext comm(int i) {
			return getRuleContext(CommContext.class,i);
		}
		public List<SintContext> sint() {
			return getRuleContexts(SintContext.class);
		}
		public SintContext sint(int i) {
			return getRuleContext(SintContext.class,i);
		}
		public TerminalNode PTASK_TRACEPOINT() { return getToken(traceParser.PTASK_TRACEPOINT, 0); }
		public TerminalNode PTASK_PID() { return getToken(traceParser.PTASK_PID, 0); }
		public TerminalNode PTASK_TID() { return getToken(traceParser.PTASK_TID, 0); }
		public TerminalNode PTASK_INDEX() { return getToken(traceParser.PTASK_INDEX, 0); }
		public TerminalNode PTASK_FLAG_F() { return getToken(traceParser.PTASK_FLAG_F, 0); }
		public TerminalNode PTASK_STATE_F() { return getToken(traceParser.PTASK_STATE_F, 0); }
		public TerminalNode PTASK_TIME() { return getToken(traceParser.PTASK_TIME, 0); }
		public TerminalNode PTASK_PRIORITY() { return getToken(traceParser.PTASK_PRIORITY, 0); }
		public TerminalNode PTASK_PERIOD() { return getToken(traceParser.PTASK_PERIOD, 0); }
		public TerminalNode PTASK_DEADLINE() { return getToken(traceParser.PTASK_DEADLINE, 0); }
		public TerminalNode PTASK_FLAG() { return getToken(traceParser.PTASK_FLAG, 0); }
		public TerminalNode PTASK_STATE() { return getToken(traceParser.PTASK_STATE, 0); }
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
		enterRule(_localctx, 2, RULE_trace);
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				((TraceContext)_localctx).timestamp = timestamp();
				setState(26);
				match(HOSTNAME);
				setState(27);
				match(ENTRY_CLONE);
				setState(28);
				match(COL);
				setState(29);
				match(LCBRA);
				setState(30);
				((TraceContext)_localctx).header = header();
				setState(31);
				match(RCBRA);
				setState(32);
				match(COM);
				setState(33);
				match(LCBRA);
				setState(34);
				((TraceContext)_localctx).context = context();
				setState(35);
				match(RCBRA);
				setState(36);
				match(COM);
				setState(37);
				match(LCBRA);
				setState(38);
				match(CLONE_FLAG);
				setState(39);
				match(EQ);
				setState(40);
				((TraceContext)_localctx).clone_flag = match(HEX);
				setState(41);
				match(COM);
				setState(42);
				match(NEWSP);
				setState(43);
				match(EQ);
				setState(44);
				((TraceContext)_localctx).newsp = match(HEX);
				setState(45);
				match(COM);
				setState(46);
				match(PARENT_TID);
				setState(47);
				match(EQ);
				setState(48);
				((TraceContext)_localctx).ptid = match(HEX);
				setState(49);
				match(COM);
				setState(50);
				match(CHILD_TID);
				setState(51);
				match(EQ);
				setState(52);
				((TraceContext)_localctx).ctid = match(HEX);
				setState(53);
				match(RCBRA);
				 ((TraceContext)_localctx).tr =  new clone_entry(((TraceContext)_localctx).timestamp.t,((TraceContext)_localctx).header.cpu_id,((TraceContext)_localctx).context.cntx.get(0),((TraceContext)_localctx).context.cntx.get(1),(((TraceContext)_localctx).clone_flag!=null?((TraceContext)_localctx).clone_flag.getText():null),(((TraceContext)_localctx).newsp!=null?((TraceContext)_localctx).newsp.getText():null),(((TraceContext)_localctx).ptid!=null?((TraceContext)_localctx).ptid.getText():null),(((TraceContext)_localctx).ctid!=null?((TraceContext)_localctx).ctid.getText():null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				((TraceContext)_localctx).timestamp = timestamp();
				setState(57);
				match(HOSTNAME);
				setState(58);
				match(EXIT_CLONE);
				setState(59);
				match(COL);
				setState(60);
				match(LCBRA);
				setState(61);
				((TraceContext)_localctx).header = header();
				setState(62);
				match(RCBRA);
				setState(63);
				match(COM);
				setState(64);
				match(LCBRA);
				setState(65);
				((TraceContext)_localctx).context = context();
				setState(66);
				match(RCBRA);
				setState(67);
				match(COM);
				setState(68);
				match(LCBRA);
				setState(69);
				match(RET);
				setState(70);
				match(EQ);
				setState(71);
				((TraceContext)_localctx).ret = match(INT);
				setState(72);
				match(RCBRA);
				 ((TraceContext)_localctx).tr =  new clone_exit(((TraceContext)_localctx).timestamp.t,((TraceContext)_localctx).header.cpu_id,((TraceContext)_localctx).context.cntx.get(0),((TraceContext)_localctx).context.cntx.get(1),(((TraceContext)_localctx).ret!=null?Integer.valueOf(((TraceContext)_localctx).ret.getText()):0)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				((TraceContext)_localctx).timestamp = timestamp();
				setState(76);
				match(HOSTNAME);
				setState(77);
				match(SCHED_SWITCH);
				setState(78);
				match(COL);
				setState(79);
				match(LCBRA);
				setState(80);
				((TraceContext)_localctx).header = header();
				setState(81);
				match(RCBRA);
				setState(82);
				match(COM);
				setState(83);
				match(LCBRA);
				setState(84);
				((TraceContext)_localctx).context = context();
				setState(85);
				match(RCBRA);
				setState(86);
				match(COM);
				setState(87);
				match(LCBRA);
				setState(88);
				match(PREV_COMM);
				setState(89);
				match(EQ);
				setState(90);
				((TraceContext)_localctx).prev_comm = comm();
				setState(91);
				match(COM);
				setState(92);
				match(PREV_TID);
				setState(93);
				match(EQ);
				setState(94);
				((TraceContext)_localctx).prev_tid = match(INT);
				setState(95);
				match(COM);
				setState(96);
				match(PREV_PRIO);
				setState(97);
				match(EQ);
				setState(98);
				((TraceContext)_localctx).prev_prio = sint();
				setState(99);
				match(COM);
				setState(100);
				match(PREV_STATE);
				setState(101);
				match(EQ);
				setState(102);
				((TraceContext)_localctx).prev_state = match(INT);
				setState(103);
				match(COM);
				setState(104);
				match(NEXT_COMM);
				setState(105);
				match(EQ);
				setState(106);
				((TraceContext)_localctx).next_comm = comm();
				setState(107);
				match(COM);
				setState(108);
				match(NEXT_TID);
				setState(109);
				match(EQ);
				setState(110);
				((TraceContext)_localctx).next_tid = match(INT);
				setState(111);
				match(COM);
				setState(112);
				match(NEXT_PRIO);
				setState(113);
				match(EQ);
				setState(114);
				((TraceContext)_localctx).next_prio = sint();
				setState(115);
				match(RCBRA);
				 ((TraceContext)_localctx).tr =  new sched_switch(((TraceContext)_localctx).timestamp.t,((TraceContext)_localctx).header.cpu_id,((TraceContext)_localctx).context.cntx.get(0),((TraceContext)_localctx).context.cntx.get(1),(((TraceContext)_localctx).prev_comm!=null?_input.getText(((TraceContext)_localctx).prev_comm.start,((TraceContext)_localctx).prev_comm.stop):null),(((TraceContext)_localctx).next_comm!=null?_input.getText(((TraceContext)_localctx).next_comm.start,((TraceContext)_localctx).next_comm.stop):null),(((TraceContext)_localctx).prev_tid!=null?Integer.valueOf(((TraceContext)_localctx).prev_tid.getText()):0),(((TraceContext)_localctx).next_tid!=null?Integer.valueOf(((TraceContext)_localctx).next_tid.getText()):0),(((TraceContext)_localctx).prev_prio!=null?_input.getText(((TraceContext)_localctx).prev_prio.start,((TraceContext)_localctx).prev_prio.stop):null),(((TraceContext)_localctx).next_prio!=null?_input.getText(((TraceContext)_localctx).next_prio.start,((TraceContext)_localctx).next_prio.stop):null),(((TraceContext)_localctx).prev_state!=null?Integer.valueOf(((TraceContext)_localctx).prev_state.getText()):0)); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(118);
				((TraceContext)_localctx).timestamp = timestamp();
				setState(119);
				match(HOSTNAME);
				setState(120);
				match(PTASK_TRACEPOINT);
				setState(121);
				match(COL);
				setState(122);
				match(LCBRA);
				setState(123);
				((TraceContext)_localctx).header = header();
				setState(124);
				match(RCBRA);
				setState(125);
				match(COM);
				setState(126);
				match(LCBRA);
				setState(127);
				match(PTASK_PID);
				setState(128);
				match(EQ);
				setState(129);
				((TraceContext)_localctx).pid = match(INT);
				setState(130);
				match(COM);
				setState(131);
				match(PTASK_TID);
				setState(132);
				match(EQ);
				setState(133);
				((TraceContext)_localctx).tid = match(INT);
				setState(134);
				match(COM);
				setState(135);
				match(PTASK_INDEX);
				setState(136);
				match(EQ);
				setState(137);
				((TraceContext)_localctx).ptask_index = match(INT);
				setState(138);
				match(COM);
				setState(139);
				match(PTASK_FLAG_F);
				setState(140);
				match(EQ);
				setState(141);
				((TraceContext)_localctx).ptask_flag = match(PTASK_FLAG);
				setState(142);
				match(COM);
				setState(143);
				match(PTASK_STATE_F);
				setState(144);
				match(EQ);
				setState(145);
				((TraceContext)_localctx).ptask_state = match(PTASK_STATE);
				setState(146);
				match(COM);
				setState(147);
				match(PTASK_TIME);
				setState(148);
				match(EQ);
				setState(149);
				((TraceContext)_localctx).times = match(INT);
				setState(150);
				match(COM);
				setState(151);
				match(PTASK_PRIORITY);
				setState(152);
				match(EQ);
				setState(153);
				((TraceContext)_localctx).ptask_prio = match(INT);
				setState(154);
				match(COM);
				setState(155);
				match(PTASK_PERIOD);
				setState(156);
				match(EQ);
				setState(157);
				((TraceContext)_localctx).ptask_peri = match(INT);
				setState(158);
				match(COM);
				setState(159);
				match(PTASK_DEADLINE);
				setState(160);
				match(EQ);
				setState(161);
				((TraceContext)_localctx).ptask_dead = match(INT);
				setState(162);
				match(RCBRA);
				 ((TraceContext)_localctx).tr =  new ptask_tracepoint(((TraceContext)_localctx).timestamp.t,((TraceContext)_localctx).header.cpu_id,(((TraceContext)_localctx).pid!=null?Integer.valueOf(((TraceContext)_localctx).pid.getText()):0),(((TraceContext)_localctx).tid!=null?Integer.valueOf(((TraceContext)_localctx).tid.getText()):0),(((TraceContext)_localctx).ptask_flag!=null?((TraceContext)_localctx).ptask_flag.getText():null),(((TraceContext)_localctx).ptask_state!=null?((TraceContext)_localctx).ptask_state.getText():null),(((TraceContext)_localctx).ptask_index!=null?Integer.valueOf(((TraceContext)_localctx).ptask_index.getText()):0),(((TraceContext)_localctx).times!=null?((TraceContext)_localctx).times.getText():null),(((TraceContext)_localctx).ptask_prio!=null?Integer.valueOf(((TraceContext)_localctx).ptask_prio.getText()):0),(((TraceContext)_localctx).ptask_peri!=null?Integer.valueOf(((TraceContext)_localctx).ptask_peri.getText()):0),(((TraceContext)_localctx).ptask_dead!=null?Integer.valueOf(((TraceContext)_localctx).ptask_dead.getText()):0)); 
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
		enterRule(_localctx, 4, RULE_timestamp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(LBRA);
			setState(168);
			((TimestampContext)_localctx).h = match(INT);
			setState(169);
			match(COL);
			setState(170);
			((TimestampContext)_localctx).m = match(INT);
			setState(171);
			match(COL);
			setState(172);
			((TimestampContext)_localctx).s = match(INT);
			setState(173);
			match(DOT);
			setState(174);
			((TimestampContext)_localctx).os = match(INT);
			setState(175);
			match(RBRA);
			setState(176);
			match(LPAR);
			setState(177);
			match(PLUS);
			setState(178);
			((TimestampContext)_localctx).delta = match(DELTA);
			setState(179);
			match(RPAR);
			 ((TimestampContext)_localctx).t =  new time_trace((((TimestampContext)_localctx).h!=null?Integer.valueOf(((TimestampContext)_localctx).h.getText()):0),(((TimestampContext)_localctx).m!=null?Integer.valueOf(((TimestampContext)_localctx).m.getText()):0),(((TimestampContext)_localctx).s!=null?Integer.valueOf(((TimestampContext)_localctx).s.getText()):0),(((TimestampContext)_localctx).os!=null?((TimestampContext)_localctx).os.getText():null),(((TimestampContext)_localctx).delta!=null?((TimestampContext)_localctx).delta.getText():null)); 
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
		public TerminalNode CPU_ID() { return getToken(traceParser.CPU_ID, 0); }
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
			setState(182);
			match(CPU_ID);
			setState(183);
			match(EQ);
			setState(184);
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
		public TerminalNode PID() { return getToken(traceParser.PID, 0); }
		public List<TerminalNode> EQ() { return getTokens(traceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(traceParser.EQ, i);
		}
		public TerminalNode COM() { return getToken(traceParser.COM, 0); }
		public TerminalNode TID() { return getToken(traceParser.TID, 0); }
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
			setState(187);
			match(PID);
			setState(188);
			match(EQ);
			setState(189);
			((ContextContext)_localctx).pid = match(INT);
			setState(190);
			match(COM);
			setState(191);
			match(TID);
			setState(192);
			match(EQ);
			setState(193);
			((ContextContext)_localctx).tid = match(INT);
			 ((ContextContext)_localctx).cntx =  new ArrayList<Integer>(2);
			        _localctx.cntx.add(0,(((ContextContext)_localctx).pid!=null?Integer.valueOf(((ContextContext)_localctx).pid.getText()):0));
			        _localctx.cntx.add(1,(((ContextContext)_localctx).tid!=null?Integer.valueOf(((ContextContext)_localctx).tid.getText()):0));
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

	public static class CommContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(traceParser.ID, 0); }
		public CommContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).enterComm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof traceParserListener ) ((traceParserListener)listener).exitComm(this);
		}
	}

	public final CommContext comm() throws RecognitionException {
		CommContext _localctx = new CommContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(ID);
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
		enterRule(_localctx, 12, RULE_sint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u00cb\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25"+
		"\n\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u00a8\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\3\3\2"+
		"./\2\u00c7\2\20\3\2\2\2\4\u00a7\3\2\2\2\6\u00a9\3\2\2\2\b\u00b8\3\2\2"+
		"\2\n\u00bd\3\2\2\2\f\u00c6\3\2\2\2\16\u00c8\3\2\2\2\20\26\b\2\1\2\21\22"+
		"\5\4\3\2\22\23\b\2\1\2\23\25\3\2\2\2\24\21\3\2\2\2\25\30\3\2\2\2\26\24"+
		"\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\2\2\3\32\3"+
		"\3\2\2\2\33\34\5\6\4\2\34\35\7\62\2\2\35\36\7\22\2\2\36\37\7\6\2\2\37"+
		" \7\f\2\2 !\5\b\5\2!\"\7\r\2\2\"#\7\7\2\2#$\7\f\2\2$%\5\n\6\2%&\7\r\2"+
		"\2&\'\7\7\2\2\'(\7\f\2\2()\7\27\2\2)*\7\3\2\2*+\7\60\2\2+,\7\7\2\2,-\7"+
		"\30\2\2-.\7\3\2\2./\7\60\2\2/\60\7\7\2\2\60\61\7\31\2\2\61\62\7\3\2\2"+
		"\62\63\7\60\2\2\63\64\7\7\2\2\64\65\7\32\2\2\65\66\7\3\2\2\66\67\7\60"+
		"\2\2\678\7\r\2\289\b\3\1\29\u00a8\3\2\2\2:;\5\6\4\2;<\7\62\2\2<=\7\21"+
		"\2\2=>\7\6\2\2>?\7\f\2\2?@\5\b\5\2@A\7\r\2\2AB\7\7\2\2BC\7\f\2\2CD\5\n"+
		"\6\2DE\7\r\2\2EF\7\7\2\2FG\7\f\2\2GH\7\26\2\2HI\7\3\2\2IJ\7.\2\2JK\7\r"+
		"\2\2KL\b\3\1\2L\u00a8\3\2\2\2MN\5\6\4\2NO\7\62\2\2OP\7\20\2\2PQ\7\6\2"+
		"\2QR\7\f\2\2RS\5\b\5\2ST\7\r\2\2TU\7\7\2\2UV\7\f\2\2VW\5\n\6\2WX\7\r\2"+
		"\2XY\7\7\2\2YZ\7\f\2\2Z[\7\33\2\2[\\\7\3\2\2\\]\5\f\7\2]^\7\7\2\2^_\7"+
		"\34\2\2_`\7\3\2\2`a\7.\2\2ab\7\7\2\2bc\7\35\2\2cd\7\3\2\2de\5\16\b\2e"+
		"f\7\7\2\2fg\7\36\2\2gh\7\3\2\2hi\7.\2\2ij\7\7\2\2jk\7\37\2\2kl\7\3\2\2"+
		"lm\5\f\7\2mn\7\7\2\2no\7 \2\2op\7\3\2\2pq\7.\2\2qr\7\7\2\2rs\7!\2\2st"+
		"\7\3\2\2tu\5\16\b\2uv\7\r\2\2vw\b\3\1\2w\u00a8\3\2\2\2xy\5\6\4\2yz\7\62"+
		"\2\2z{\7\17\2\2{|\7\6\2\2|}\7\f\2\2}~\5\b\5\2~\177\7\r\2\2\177\u0080\7"+
		"\7\2\2\u0080\u0081\7\f\2\2\u0081\u0082\7$\2\2\u0082\u0083\7\3\2\2\u0083"+
		"\u0084\7.\2\2\u0084\u0085\7\7\2\2\u0085\u0086\7%\2\2\u0086\u0087\7\3\2"+
		"\2\u0087\u0088\7.\2\2\u0088\u0089\7\7\2\2\u0089\u008a\7&\2\2\u008a\u008b"+
		"\7\3\2\2\u008b\u008c\7.\2\2\u008c\u008d\7\7\2\2\u008d\u008e\7\"\2\2\u008e"+
		"\u008f\7\3\2\2\u008f\u0090\7,\2\2\u0090\u0091\7\7\2\2\u0091\u0092\7#\2"+
		"\2\u0092\u0093\7\3\2\2\u0093\u0094\7+\2\2\u0094\u0095\7\7\2\2\u0095\u0096"+
		"\7\'\2\2\u0096\u0097\7\3\2\2\u0097\u0098\7.\2\2\u0098\u0099\7\7\2\2\u0099"+
		"\u009a\7(\2\2\u009a\u009b\7\3\2\2\u009b\u009c\7.\2\2\u009c\u009d\7\7\2"+
		"\2\u009d\u009e\7)\2\2\u009e\u009f\7\3\2\2\u009f\u00a0\7.\2\2\u00a0\u00a1"+
		"\7\7\2\2\u00a1\u00a2\7*\2\2\u00a2\u00a3\7\3\2\2\u00a3\u00a4\7.\2\2\u00a4"+
		"\u00a5\7\r\2\2\u00a5\u00a6\b\3\1\2\u00a6\u00a8\3\2\2\2\u00a7\33\3\2\2"+
		"\2\u00a7:\3\2\2\2\u00a7M\3\2\2\2\u00a7x\3\2\2\2\u00a8\5\3\2\2\2\u00a9"+
		"\u00aa\7\b\2\2\u00aa\u00ab\7.\2\2\u00ab\u00ac\7\6\2\2\u00ac\u00ad\7.\2"+
		"\2\u00ad\u00ae\7\6\2\2\u00ae\u00af\7.\2\2\u00af\u00b0\7\5\2\2\u00b0\u00b1"+
		"\7.\2\2\u00b1\u00b2\7\t\2\2\u00b2\u00b3\7\n\2\2\u00b3\u00b4\7\4\2\2\u00b4"+
		"\u00b5\7-\2\2\u00b5\u00b6\7\13\2\2\u00b6\u00b7\b\4\1\2\u00b7\7\3\2\2\2"+
		"\u00b8\u00b9\7\23\2\2\u00b9\u00ba\7\3\2\2\u00ba\u00bb\7.\2\2\u00bb\u00bc"+
		"\b\5\1\2\u00bc\t\3\2\2\2\u00bd\u00be\7\24\2\2\u00be\u00bf\7\3\2\2\u00bf"+
		"\u00c0\7.\2\2\u00c0\u00c1\7\7\2\2\u00c1\u00c2\7\25\2\2\u00c2\u00c3\7\3"+
		"\2\2\u00c3\u00c4\7.\2\2\u00c4\u00c5\b\6\1\2\u00c5\13\3\2\2\2\u00c6\u00c7"+
		"\7\61\2\2\u00c7\r\3\2\2\2\u00c8\u00c9\t\2\2\2\u00c9\17\3\2\2\2\4\26\u00a7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}