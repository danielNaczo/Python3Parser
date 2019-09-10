package com.github.python3parser.model.stmts.importStmts;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class ImportFrom extends Statement{

	Optional<Identifier> module;
	List<Alias> names;
	Integer level;
	
	public ImportFrom(Identifier module, List<Alias> names, Integer level) {
		this.module = Optional.ofNullable(module);
		this.names = names;
		this.level = level;
	}
	
	public Optional<Identifier> getModule() {
		return module;
	}

	public void setModule(Optional<Identifier> module) {
		this.module = module;
	}

	public List<Alias> getNames() {
		return names;
	}

	public void setNames(List<Alias> names) {
		this.names = names;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitImportFrom(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImportFrom that = (ImportFrom) o;
		return Objects.equals(module, that.module) &&
				Objects.equals(names, that.names) &&
				Objects.equals(level, that.level);
	}

	@Override
	public int hashCode() {
		return Objects.hash(module, names, level);
	}
}
