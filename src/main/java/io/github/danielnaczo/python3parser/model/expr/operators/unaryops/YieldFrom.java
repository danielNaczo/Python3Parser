package io.github.danielnaczo.python3parser.model.expr.operators.unaryops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class YieldFrom extends UnaryOp {
	static int PRECEDENCE = 5;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public YieldFrom(Expression expression) {
		super(expression);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitYieldFrom(this, param);
	}
}
