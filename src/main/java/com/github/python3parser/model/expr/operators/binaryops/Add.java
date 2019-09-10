package com.github.python3parser.model.expr.operators.binaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Add extends BinOp {
	static int PRECEDENCE = 110;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Add(Expression left, Expression right) {
		super(left, right);
	}
	
	public Add(String left, String right) {
		super(left, right);
	}
	
	//just for AugAssigns with no expressions (e.g.: target += 5)
	public Add() {
		super();
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAdd(this, param);
	}
}
