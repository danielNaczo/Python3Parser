package io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.Name;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

// e.g:

// a = b = 1

public class Assign extends Statement{
	
	List<Expression> targets;
	Optional<Expression> value;
	
	public Assign() {
		this(new ArrayList<>(), null);
	}
	
	public Assign(Expression value) {
		this(new ArrayList<>(), value);
	}
	
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
	
	public Expression addTarget(Expression expression) {
		this.targets.add(expression);
		return expression;
	}
	
	public Expression addTargetAsString(String expressionAsString) {
		Name name = new Name(expressionAsString);
		this.targets.add(name);
		return name;
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
	
	@Override
	public String toString() {
		return "Assign";
	}
}
