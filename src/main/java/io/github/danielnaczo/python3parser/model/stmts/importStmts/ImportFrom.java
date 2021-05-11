package io.github.danielnaczo.python3parser.model.stmts.importStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class ImportFrom extends Statement{

	Optional<Identifier> module;
	List<Alias> names;
	Integer level;
	
	public ImportFrom(String module) {
		this(new Identifier(module), new ArrayList<>(), 0);
	}
	
	public ImportFrom(String module, Integer level) {
		this(new Identifier(module), new ArrayList<>(), level);
	}
	
	public ImportFrom(String module, List<Alias> names) {
		this(new Identifier(module), names, 0);
	}
	
	public ImportFrom(String module, List<Alias> names, Integer level) {
		this(new Identifier(module), names, level);
	}
	
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
	
	public Alias addAlias(Alias alias) {
		this.names.add(alias);
		return alias;
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
	
	@Override
	public String toString() {
		return "ImportFrom";
	}
}
