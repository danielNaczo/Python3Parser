package io.github.danielnaczo.python3parser.visitors.prettyprint;

import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Add;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.At;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitAnd;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitOr;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitXor;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Div;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.FloorDiv;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.LShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mult;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Pow;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.RShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Sub;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.And;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.Or;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Eq;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Gt;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.GtE;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.In;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Is;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.IsNot;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Lt;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.LtE;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.NotEq;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.NotIn;

public class OperatorPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitAdd(Add add, IndentationPrettyPrint param) {
		return "+";
	}
	
	@Override
	public String visitAt(At at, IndentationPrettyPrint param) {
		return "@";
	}
	
	@Override
	public String visitBitAnd(BitAnd bitAnd, IndentationPrettyPrint param) {
		return "&";
	}
	
	@Override
	public String visitBitOr(BitOr bitOr, IndentationPrettyPrint param) {
		return "|";
	}
	
	@Override
	public String visitBitXor(BitXor bitXor, IndentationPrettyPrint param) {
		return "^";
	}
	
	@Override
	public String visitDiv(Div div, IndentationPrettyPrint param) {
		return "/";
	}
	
	@Override
	public String visitFloorDiv(FloorDiv floorDiv, IndentationPrettyPrint param) {
		return "/" + "/"; //maybe "//" could be wrong string
	}
	
	@Override
	public String visitLShift(LShift lShift, IndentationPrettyPrint param) {
		return "<<";
	}
	
	@Override
	public String visitModulo(io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mod modulo, IndentationPrettyPrint param) {
		return "%";
	}
	
	@Override
	public String visitMult(Mult mult, IndentationPrettyPrint param) {
		return "*";
	}
	
	@Override
	public String visitPow(Pow pow, IndentationPrettyPrint param) {
		return "**";
	}
	
	@Override
	public String visitRShift(RShift rShift, IndentationPrettyPrint param) {
		return ">>";
	}
	
	@Override
	public String visitSub(Sub sub, IndentationPrettyPrint param) {
		return "-";
	}
	
	@Override
	public String visitEq(Eq eq, IndentationPrettyPrint param) {
		return "==";
	}
	
	@Override
	public String visitGt(Gt gt, IndentationPrettyPrint param) {
		return ">";
	}
	
	@Override
	public String visitGtE(GtE gte, IndentationPrettyPrint param) {
		return ">=";
	}
	
	@Override
	public String visitIn(In in, IndentationPrettyPrint param) {
		return "in";
	}
	
	@Override
	public String visitIs(Is is, IndentationPrettyPrint param) {
		return "is";
	}
	
	@Override
	public String visitIsNot(IsNot isNot, IndentationPrettyPrint param) {
		return "is not";
	}
	
	@Override
	public String visitLt(Lt lt, IndentationPrettyPrint param) {
		return "<";
	}
	
	@Override
	public String visitLtE(LtE lte, IndentationPrettyPrint param) {
		return "<=";
	}
	
	@Override
	public String visitNotEq(NotEq notEq, IndentationPrettyPrint param) {
		return "!=";
	}
	
	@Override
	public String visitNotIn(NotIn notIn, IndentationPrettyPrint param) {
		return "not in";
	}
	
	@Override
	public String visitOr(Or notIn, IndentationPrettyPrint param) {
		return "or";
	}
	
	@Override
	public String visitAnd(And notIn, IndentationPrettyPrint param) {
		return "and";
	}
}
