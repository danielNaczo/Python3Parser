package com.github.python3parser.model.expr.operators.binaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class BitOr extends BinOp {
	static int PRECEDENCE = 70;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public BitOr(Expression left, Expression right) {
		super(left, right);
	}
	
	//just for AugAssigns with no expressions (e.g.: target += 5)
	public BitOr() {
		super();
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitBitOr(this, param);
	}
}
