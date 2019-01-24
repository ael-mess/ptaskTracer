// Generated from com/parser/traceParser.g4 by ANTLR 4.7.1

  package com.parser;
  import com.event.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link traceParser}.
 */
public interface traceParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link traceParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(traceParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(traceParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#trace}.
	 * @param ctx the parse tree
	 */
	void enterTrace(traceParser.TraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#trace}.
	 * @param ctx the parse tree
	 */
	void exitTrace(traceParser.TraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void enterTimestamp(traceParser.TimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void exitTimestamp(traceParser.TimestampContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(traceParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(traceParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#context}.
	 * @param ctx the parse tree
	 */
	void enterContext(traceParser.ContextContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#context}.
	 * @param ctx the parse tree
	 */
	void exitContext(traceParser.ContextContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#fields_exit_clone}.
	 * @param ctx the parse tree
	 */
	void enterFields_exit_clone(traceParser.Fields_exit_cloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#fields_exit_clone}.
	 * @param ctx the parse tree
	 */
	void exitFields_exit_clone(traceParser.Fields_exit_cloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#fields_entry_clone}.
	 * @param ctx the parse tree
	 */
	void enterFields_entry_clone(traceParser.Fields_entry_cloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#fields_entry_clone}.
	 * @param ctx the parse tree
	 */
	void exitFields_entry_clone(traceParser.Fields_entry_cloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#fields_sched_switch}.
	 * @param ctx the parse tree
	 */
	void enterFields_sched_switch(traceParser.Fields_sched_switchContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#fields_sched_switch}.
	 * @param ctx the parse tree
	 */
	void exitFields_sched_switch(traceParser.Fields_sched_switchContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#fields_ptask_tracepoint}.
	 * @param ctx the parse tree
	 */
	void enterFields_ptask_tracepoint(traceParser.Fields_ptask_tracepointContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#fields_ptask_tracepoint}.
	 * @param ctx the parse tree
	 */
	void exitFields_ptask_tracepoint(traceParser.Fields_ptask_tracepointContext ctx);
	/**
	 * Enter a parse tree produced by {@link traceParser#sint}.
	 * @param ctx the parse tree
	 */
	void enterSint(traceParser.SintContext ctx);
	/**
	 * Exit a parse tree produced by {@link traceParser#sint}.
	 * @param ctx the parse tree
	 */
	void exitSint(traceParser.SintContext ctx);
}