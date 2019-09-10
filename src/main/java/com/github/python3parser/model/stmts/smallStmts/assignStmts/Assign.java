package com.github.python3parser.model.stmts.smallStmts.assignStmts;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g:

// a = b = 1

//if "value" is null, then the only element in the "targets"-list is a DocString of a function or al class
public class Assign extends Statement{
	
	List<Expression> targets;
	Optional<Expression> value;
	
	public Assign(List<Expression> targets, Expression value) {
		this.targets = targets;
		this.value = Optional.ofNullable(value);
	}

	public List<Expression> getTargets() {
		return targets;
	}

	public void setTargets(List<Expression> targets) {
		this.targets = targets;
	}

	public Optional<Expression> getValue() {
		return value;
	}

	public void setValue(Optional<Expression> value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAssign(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Assign assign = (Assign) o;
		return Objects.equals(targets, assign.targets) &&
				Objects.equals(value, assign.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(targets, value);
	}
}
