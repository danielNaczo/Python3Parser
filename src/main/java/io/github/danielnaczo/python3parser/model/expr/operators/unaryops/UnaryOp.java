package io.github.danielnaczo.python3parser.model.expr.operators.unaryops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

public abstract class UnaryOp extends Operator {
	
	Expression expression;
	
	public UnaryOp(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitUnaryOp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UnaryOp)) return false;
		UnaryOp unaryOp = (UnaryOp) o;
		return Objects.equals(expression, unaryOp.expression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expression);
	}
}
