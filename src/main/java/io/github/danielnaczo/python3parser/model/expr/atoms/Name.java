package io.github.danielnaczo.python3parser.model.expr.atoms;

import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

public class Name extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Identifier id;
	
	public Name(Identifier id) {
		this.id = id;
	}
	
	public Name(String name) {
		this.id = new Identifier(name);
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitName(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Name name = (Name) o;
		return Objects.equals(id, name.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
