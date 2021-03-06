package io.github.danielnaczo.python3parser.model.stmts.importStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class Import extends Statement{

	List<Alias> names;
	
	public Import() {
		this((List<Alias>) null);
	}
	
	public Import(List<Alias> names) {
		this.names = (names != null) ? names : new ArrayList<>();
	}

	public List<Alias> getNames() {
		return names;
	}

	public void setNames(List<Alias> names) {
		this.names = names;
	}
	
	public Alias addAlias(Alias alias) {
		this.names.add(alias);
		return alias;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitImport(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Import anImport = (Import) o;
		return Objects.equals(names, anImport.names);
	}

	@Override
	public int hashCode() {
		return Objects.hash(names);
	}
	
	@Override
	public String toString() {
		return "Import";
	}
}
