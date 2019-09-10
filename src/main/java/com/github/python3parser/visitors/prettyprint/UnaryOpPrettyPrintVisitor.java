package com.github.python3parser.visitors.prettyprint;

import com.github.python3parser.model.expr.operators.unaryops.Await;
import com.github.python3parser.model.expr.operators.unaryops.Invert;
import com.github.python3parser.model.expr.operators.unaryops.Not;
import com.github.python3parser.model.expr.operators.unaryops.Starred;
import com.github.python3parser.model.expr.operators.unaryops.UAdd;
import com.github.python3parser.model.expr.operators.unaryops.USub;

public class UnaryOpPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitAwait(Await await, IndentationPrettyPrint param) {
		return "await ";
	}
	
	@Override
	public String visitInvert(Invert invert, IndentationPrettyPrint param) {
		return "~";
	}
	
	@Override
	public String visitNot(Not not, IndentationPrettyPrint param) {
		return "not ";
	}
	
	@Override
	public String visitUAdd(UAdd uAdd, IndentationPrettyPrint param) {
		return "+";
	}
	
	@Override
	public String visitUSub(USub uSub, IndentationPrettyPrint param) {
		return "-";
	}
	
	public String visitStarred(Starred starred, IndentationPrettyPrint param) {
		return "*";
	}
}
