package io.github.danielnaczo.python3parser.model.expr.datastructures;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.: s = {"key1", "key2", "key3"}
//			^^^^^^^^^^^^^^^^^^^^^^^^
public class Set extends Expression{
	static int PRECEDENCE = 170;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> elts;
	
	public Set(List<Expression> elts) {
		this.elts = elts;
		setParents();
	}

	private void setParents() {
		if (elts != null) {
			for (Expression expr : elts) {
				if (expr != null) expr.setParent(this);
			}
		}
	}

	public List<Expression> getElts() {
		return elts;
	}

	public void setElts(List<Expression> elts) {
		this.elts = elts;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitSet(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Set set = (Set) o;
		return Objects.equals(elts, set.elts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elts);
	}
}
