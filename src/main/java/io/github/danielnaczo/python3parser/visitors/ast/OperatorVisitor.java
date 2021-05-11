package io.github.danielnaczo.python3parser.visitors.ast;

import io.github.danielnaczo.python3parser.visitors.exceptions.RuleException;
import com.github.python3parser.Python3Parser.AugassignContext;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Add;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.At;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitAnd;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitOr;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitXor;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Div;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.FloorDiv;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.LShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mod;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mult;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Pow;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.RShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Sub;

//TODO this class is just for AugAssigns: rename this class
public class OperatorVisitor extends GenericUnsupportedCSTVisitor<Operator> {
	@Override
	public Operator visitAugassign(AugassignContext ctx) {
		if (ctx.ADD_ASSIGN() != null) {
			return new Add();
		}
		if (ctx.SUB_ASSIGN() != null) {
			return new Sub();
		}
		if (ctx.MULT_ASSIGN() != null) {
			return new Mult();
		}
		if (ctx.AT_ASSIGN() != null) {
			return new At();
		}
		if (ctx.DIV_ASSIGN() != null) {
			return new Div();
		}
		if (ctx.MOD_ASSIGN() != null) {
			return new Mod();
		}
		if (ctx.AND_ASSIGN() != null) {
			return new BitAnd();
		}
		if (ctx.OR_ASSIGN() != null) {
			return new BitOr();
		}
		if (ctx.XOR_ASSIGN() != null) {
			return new BitXor();
		}
		if (ctx.LEFT_SHIFT_ASSIGN() != null) {
			return new LShift();
		}
		if (ctx.RIGHT_SHIFT_ASSIGN() != null) {
			return new RShift();
		}
		if (ctx.POWER_ASSIGN() != null) {
			return new Pow();
		}
		if (ctx.IDIV_ASSIGN() != null) {
			return new FloorDiv();
		}
		throw new RuleException();
	}
	
}
