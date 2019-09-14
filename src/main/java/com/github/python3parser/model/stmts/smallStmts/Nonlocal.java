package com.github.python3parser.model.stmts.smallStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// nonlocal name1, name2
public class Nonlocal extends Statement{
	
	List<Identifier> names;
	
	public Nonlocal() {
		this(new ArrayList<>());
	}
	
	public Nonlocal(List<Identifier> names) {
		this.names = names;
	}

	public List<Identifier> getNames() {
		return names;
	}

	public void setNames(List<Identifier> names) {
		this.names = names;
	}
	
	public void addIdentifier(Identifier name) {
		this.names.add(name);
	}
	
	public void addIdentiferAsString(String name) {
		this.names.add(new Identifier(name));
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitNonlocal(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Nonlocal nonlocal = (Nonlocal) o;
		return Objects.equals(names, nonlocal.names);
	}

	@Override
	public int hashCode() {
		return Objects.hash(names);
	}
	
	@Override
	public String toString() {
		return "Nonlocal";
	}
}
