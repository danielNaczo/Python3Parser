package io.github.danielnaczo.python3parser.visitors.prettyprint;

import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Await;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Invert;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Not;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Starred;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.UAdd;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.USub;

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
