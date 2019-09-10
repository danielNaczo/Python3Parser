package com.github.python3parser.model.expr.atoms;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class True extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof True) {
			return true;
		} else {
			return false;
		}
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitTrue(this, param);
	}
}
