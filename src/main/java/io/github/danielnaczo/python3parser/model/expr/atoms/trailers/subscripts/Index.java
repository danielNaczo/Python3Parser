package io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

public class Index extends SliceAbstract {
	static int PRECEDENCE = 160;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression value;
	
	public Index (Expression value) {
		this.value = value;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitIndex(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Index index = (Index) o;
		return Objects.equals(value, index.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
