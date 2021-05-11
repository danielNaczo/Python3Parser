package io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class IsNot extends Cmpop {

	public IsNot(Expression left, Expression right) {
		super(left, right);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitIsNot(this, param);
	}
}
