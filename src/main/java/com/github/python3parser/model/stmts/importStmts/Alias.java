package com.github.python3parser.model.stmts.importStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Alias implements AST {

	Identifier name;
	Optional<Identifier> asname;
	
	public Alias(Identifier name, Identifier asname) {
		this.name = name;
		this.asname = Optional.ofNullable(asname);
	}

	public Identifier getName() {
		return name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public Optional<Identifier> getAsName() {
		return asname;
	}

	public void setAsName(Optional<Identifier> asname) {
		this.asname = asname;
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
				Objects.equals(asname, alias.asname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, asname);
	}
}
