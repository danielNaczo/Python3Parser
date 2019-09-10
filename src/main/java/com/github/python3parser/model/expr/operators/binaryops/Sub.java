package com.github.python3parser.model.expr.operators.binaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Sub extends BinOp {
	static int PRECEDENCE = 110;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Sub(Expression left, Expression right) {
		super(left, right);
	}

	public Sub() {
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitSub(this, param);
	}
}
