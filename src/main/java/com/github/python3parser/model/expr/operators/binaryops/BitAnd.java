package com.github.python3parser.model.expr.operators.binaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class BitAnd extends BinOp {
	static int PRECEDENCE = 90;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public BitAnd(Expression left, Expression right) {
		super(left, right);
	}
	
	//just for AugAssigns with no expressions (e.g.: target += 5)
	public BitAnd() {
		super();
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitBitAnd(this, param);
	}
}
