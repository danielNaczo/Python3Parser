package io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BinOp;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class And extends BinOp{
	static int PRECEDENCE = 40;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public And(Expression left, Expression right) {
		super(left, right);
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAnd(this, param);
	}
}
