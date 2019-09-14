package com.github.python3parser.model.stmts.flowStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// return 1
public class Return extends Statement{
	Optional<Expression> value;
	
	public Return() {
		this(null);
	}
	
	public Return(Expression value) {
		this.value = Optional.ofNullable(value);
	}

	public Optional<Expression> getValue() {
		return value;
	}

	public void setValue(Optional<Expression> value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitReturn(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Return aReturn = (Return) o;
		return Objects.equals(value, aReturn.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
