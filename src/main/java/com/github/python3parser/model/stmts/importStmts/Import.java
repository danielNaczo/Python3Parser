package com.github.python3parser.model.stmts.importStmts;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Import extends Statement{

	List<Alias> names;
	
	public Import(List<Alias> names) {
		this.names = names;
	}

	public List<Alias> getNames() {
		return names;
	}

	public void setNames(List<Alias> names) {
		this.names = names;
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
}
