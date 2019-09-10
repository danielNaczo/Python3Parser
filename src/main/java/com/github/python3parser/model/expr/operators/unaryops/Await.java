package com.github.python3parser.model.expr.operators.unaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Await extends UnaryOp {
	static int PRECEDENCE = 150;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Await(Expression expression) {
		super(expression);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAwait(this, param);
	}
}
