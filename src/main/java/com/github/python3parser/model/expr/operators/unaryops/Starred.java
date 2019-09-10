package com.github.python3parser.model.expr.operators.unaryops;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// *value
public class Starred extends UnaryOp {
	static int PRECEDENCE = 65;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Starred(Expression expression) {
		super(expression);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitStarred(this, param);
	}
}
