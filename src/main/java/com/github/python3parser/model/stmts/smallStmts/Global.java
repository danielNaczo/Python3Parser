package com.github.python3parser.model.stmts.smallStmts;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// global name1, name2
public class Global extends Statement{

	List<Identifier> names;
	
	public Global (List<Identifier> names) {
		this.names = names;
	}

	public List<Identifier> getNames() {
		return names;
	}

	public void setNames(List<Identifier> names) {
		this.names = names;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitGlobal(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Global global = (Global) o;
		return Objects.equals(names, global.names);
	}

	@Override
	public int hashCode() {
		return Objects.hash(names);
	}
}
