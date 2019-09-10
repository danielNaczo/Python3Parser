package com.github.python3parser.model.stmts.smallStmts;

import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Delete extends Statement{
	
	Expression expression;

	public Delete(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitDelete(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Delete delete = (Delete) o;
		return Objects.equals(expression, delete.expression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expression);
	}
}
