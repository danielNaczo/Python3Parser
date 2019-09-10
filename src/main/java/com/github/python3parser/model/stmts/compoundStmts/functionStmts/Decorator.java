package com.github.python3parser.model.stmts.compoundStmts.functionStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//TODO move this class to package "com.github.python3parser.model"

//e.g.:

// @name(arguments.element1, arguments.element2)
public class Decorator implements AST{

	Identifier name;
	Optional<Expression> arguments;
	
	public Decorator(String name) {
		this(name, null);
	}
	
	public Decorator(String name, Expression arguments) {
		this(new Identifier(name), arguments);
	}
	
	public Decorator(Identifier name, Expression arguments) {
		this.name = name;
		this.arguments = Optional.ofNullable(arguments);
	}

	public Identifier getName() {
		return name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public Optional<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(Optional<Expression> arguments) {
		this.arguments = arguments;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitDecorator(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Decorator decorator = (Decorator) o;
		return Objects.equals(name, decorator.name) &&
				Objects.equals(arguments, decorator.arguments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, arguments);
	}
}
