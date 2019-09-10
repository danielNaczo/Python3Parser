package com.github.python3parser.model.expr.operators;

import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// body if test else orElse
public class IfExpr extends Operator {
	static int PRECEDENCE = 20;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression test;
	Expression body;
	Expression orElse;

	public IfExpr(Expression test, Expression body, Expression orElse) {
		this.test = test;
		this.body = body;
		this.orElse = orElse;
	}

	public Expression getTest() {
		return test;
	}

	public void setTest(Expression test) {
		this.test = test;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}

	public Expression getOrElse() {
		return orElse;
	}

	public void setOrElse(Expression orElse) {
		this.orElse = orElse;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitIfExpr(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IfExpr)) return false;
		IfExpr ifExpr = (IfExpr) o;
		return Objects.equals(test, ifExpr.test) &&
				Objects.equals(body, ifExpr.body) &&
				Objects.equals(orElse, ifExpr.orElse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(test, body, orElse);
	}
}
