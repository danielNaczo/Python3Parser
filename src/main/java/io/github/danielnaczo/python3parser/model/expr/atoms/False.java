package io.github.danielnaczo.python3parser.model.expr.atoms;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class False extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof False) {
			return true;
		} else {
			return false;
		}
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitFalse(this, param);
	}
}
