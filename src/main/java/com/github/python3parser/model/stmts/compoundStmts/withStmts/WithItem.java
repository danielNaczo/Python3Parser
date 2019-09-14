package com.github.python3parser.model.stmts.compoundStmts.withStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// contextExpr as optionalVars
public class WithItem implements AST {

	Expression contextExpr;
	Optional<Expression> optionalVars;
	
	public WithItem(String contextExpression) {
		this(new Name(contextExpression), null);
	}
	
	public WithItem(Expression contextExpr) {
		this(contextExpr, null);
	}
	
	public WithItem(String contextExpr, Expression optionalVars) {
		this(new Name(contextExpr), optionalVars);
	}
	
	public WithItem(Expression contextExpr, Expression optionalVars) {
		this.contextExpr = contextExpr;
		this.optionalVars = Optional.ofNullable(optionalVars);
	}
	
	public Expression getContextExpr() {
		return contextExpr;
	}


	public void setContextExpr(Expression contextExpr) {
		this.contextExpr = contextExpr;
	}


	public Optional<Expression> getOptionalVars() {
		return optionalVars;
	}


	public void setOptionalVars(Optional<Expression> optionalVars) {
		this.optionalVars = optionalVars;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitWithItem(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WithItem withItem = (WithItem) o;
		return Objects.equals(contextExpr, withItem.contextExpr) &&
				Objects.equals(optionalVars, withItem.optionalVars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contextExpr, optionalVars);
	}
}
