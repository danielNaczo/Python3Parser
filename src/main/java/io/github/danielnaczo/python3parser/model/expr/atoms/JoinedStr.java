package io.github.danielnaczo.python3parser.model.expr.atoms;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class JoinedStr extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> values;

	public JoinedStr(List<Expression> values) {
		this.values = values;
	}

	public List<Expression> getValues() {
		return values;
	}

	public void setValues(List<Expression> values) {
		this.values = values;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitJoinedStr(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		JoinedStr joinedStr = (JoinedStr) o;
		return Objects.equals(values, joinedStr.values);
	}

	@Override
	public int hashCode() {
		return Objects.hash(values);
	}
}
