package io.github.danielnaczo.python3parser.model.stmts.importStmts;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class Alias implements AST {

	Identifier name;
	Optional<Identifier> asName;
	
	public Alias(String name) {
		this(new Identifier(name), null);
	}
	
	public Alias(Identifier name) {
		this(name, null);
	}
	
	public Alias(String name, Identifier asName) {
		this(new Identifier(name), asName);
	}
	
	public Alias(String name, String asName) {
		this(new Identifier(name), new Identifier(asName));
	}
	
	public Alias(Identifier name, Identifier asName) {
		this.name = name;
		this.asName = Optional.ofNullable(asName);
	}

	public Identifier getName() {
		return name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public Optional<Identifier> getAsName() {
		return asName;
	}

	public void setAsName(Optional<Identifier> asname) {
		this.asName = asname;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAlias(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Alias alias = (Alias) o;
		return Objects.equals(name, alias.name) &&
				Objects.equals(asName, alias.asName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, asName);
	}
}
