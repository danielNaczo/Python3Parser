package com.github.python3parser.model.expr.datastructures;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//example: a = (1,2,3,4)
//             ^^^^^^^^^
//works like a list, but immutable
public class Tuple extends Expression {
	static int PRECEDENCE = 170;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> elts;

	public Tuple(List<Expression> elts) {
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
		return visitor.visitTuple(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tuple tuple = (Tuple) o;
		return Objects.equals(elts, tuple.elts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elts);
	}
}
