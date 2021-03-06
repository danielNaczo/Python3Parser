package io.github.danielnaczo.python3parser.model.expr.operators.unaryops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class Not extends UnaryOp {
	static int PRECEDENCE = 50;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Not(Expression expression) {
		super(expression);
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitNot(this, param);
	}
}
