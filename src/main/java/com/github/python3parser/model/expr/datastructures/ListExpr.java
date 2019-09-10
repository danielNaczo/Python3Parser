package com.github.python3parser.model.expr.datastructures;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//actually "List" in abstract grammar
//e.g.: a = [1,2,3,4]
//			^^^^^^^^^
public class ListExpr extends Expression {
	static int PRECEDENCE = 170;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> elts;
	
	public ListExpr(List<Expression> elts) {
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
		return visitor.visitListExpr(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListExpr listExpr = (ListExpr) o;
		return Objects.equals(elts, listExpr.elts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elts);
	}
}
