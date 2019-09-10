package com.github.python3parser.model.stmts.flowStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// raise exc from cause
public class Raise extends Statement{

	Optional<Expression> exc;
	Optional<Expression> cause;
	
	public Raise(Expression exc, Expression cause) {
		this.exc = Optional.ofNullable(exc);
		this.cause = Optional.ofNullable(cause);
	}

	public Optional<Expression> getExc() {
		return exc;
	}

	public void setExc(Optional<Expression> exc) {
		this.exc = exc;
	}

	public Optional<Expression> getCause() {
		return cause;
	}

	public void setCause(Optional<Expression> cause) {
		this.cause = cause;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitRaise(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Raise raise = (Raise) o;
		return Objects.equals(exc, raise.exc) &&
				Objects.equals(cause, raise.cause);
	}

	@Override
	public int hashCode() {
		return Objects.hash(exc, cause);
	}
}
