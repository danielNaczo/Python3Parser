package io.github.danielnaczo.python3parser.model.expr.operators.binaryops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class FloorDiv extends BinOp {
	static int PRECEDENCE = 120;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public FloorDiv(Expression left, Expression right) {
		super(left, right);
	}
	
	//just for AugAssigns with no expressions (e.g.: target += 5)
	public FloorDiv() {
		super();
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitFloorDiv(this, param);
	}
}
