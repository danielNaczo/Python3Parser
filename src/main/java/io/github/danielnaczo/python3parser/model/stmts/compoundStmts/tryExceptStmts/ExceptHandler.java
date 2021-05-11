package io.github.danielnaczo.python3parser.model.stmts.compoundStmts.tryExceptStmts;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.Name;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//differs from original AST grammar

// e.g.:

// except test as name:
public class ExceptHandler implements AST {
	
	Optional<Expression> error;
	Optional<Identifier> errorAsName;
	
	public ExceptHandler() {
		this(null, (Identifier) null);
	}
	
	public ExceptHandler(String error) {
		this(new Name(error), (Identifier) null);
	}
	
	public ExceptHandler(Expression error) {
		this(error, (Identifier) null);
	}
	
	public ExceptHandler(String error, String errorAsName) {
		this(new Name(error), new Identifier(errorAsName));
	}
	
	public ExceptHandler(Expression error, String errorAsName) {
		this(error, new Identifier(errorAsName));
	}
	
	public ExceptHandler(Expression error, Identifier errorAsName) {
		this.error = Optional.ofNullable(error);
		this.errorAsName = Optional.ofNullable(errorAsName);
	}
	
	public Optional<Expression> getError() {
		return error;
	}
	
	public void setError(Optional<Expression> error) {
		this.error = error;
	}
	
	public Optional<Identifier> getErrorAsName() {
		return errorAsName;
	}
	
	public void setErrorAsName(Optional<Identifier> errorAsName) {
		this.errorAsName = errorAsName;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExceptHandler(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExceptHandler that = (ExceptHandler) o;
		return Objects.equals(error, that.error) &&
				Objects.equals(errorAsName, that.errorAsName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(error, errorAsName);
	}
}
