package com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//differs from original AST grammar

// e.g.:

// except test as name:
public class ExceptHandler implements AST {
	
	Optional<Expression> test;
	Optional<Identifier> name;
	
	public ExceptHandler(Expression test, Identifier name) {
		this.test = Optional.ofNullable(test);
		this.name = Optional.ofNullable(name);
	}
	
	public Optional<Expression> getTest() {
		return test;
	}
	
	public void setTest(Optional<Expression>test) {
		this.test = test;
	}
	
	public Optional<Identifier> getName() {
		return name;
	}
	
	public void setName(Optional<Identifier>name) {
		this.name = name;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExceptHandler(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExceptHandler that = (ExceptHandler) o;
		return Objects.equals(test, that.test) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(test, name);
	}
}
