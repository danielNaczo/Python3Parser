package com.github.python3parser.model.expr.operators.binaryops.boolops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.operators.binaryops.BinOp;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Or extends BinOp {
	static int PRECEDENCE = 30;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Or(Expression left, Expression right) {
		super(left, right);
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitOr(this, param);
	}
}
