package com.github.python3parser.model.expr.operators.binaryops.comparisons;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class In extends Cmpop {

	public In(Expression left, Expression right) {
		super(left, right);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitIn(this, param);
	}
}
