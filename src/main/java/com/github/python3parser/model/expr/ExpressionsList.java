package com.github.python3parser.model.expr;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// expression, expression, expression
public class ExpressionsList extends Expression{
	static int PRECEDENCE = 0;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> expressions;
	
	public ExpressionsList(List<Expression> expressions) {
		this.expressions = expressions;
		setParents();
	}

	private void setParents() {
		if (expressions != null) {
			for (Expression expr : expressions) {
				if (expr != null) expr.setParent(this);
			}
		}
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExpressionList(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ExpressionsList)) return false;
		ExpressionsList that = (ExpressionsList) o;
		return Objects.equals(expressions, that.expressions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expressions);
	}
}
