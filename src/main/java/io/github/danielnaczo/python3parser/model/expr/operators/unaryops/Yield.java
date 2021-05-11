package io.github.danielnaczo.python3parser.model.expr.operators.unaryops;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class Yield extends Operator {
	static int PRECEDENCE = 5;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Optional<Expression> expression;
	
	public Yield(Expression expression) {
		this.expression = Optional.ofNullable(expression);
	}

	public Optional<Expression> getExpression() {
		return expression;
	}

	public void setExpression(Optional<Expression> expression) {
		this.expression = expression;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitYield(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Yield)) return false;
		Yield yield = (Yield) o;
		return Objects.equals(expression, yield.expression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expression);
	}
}
