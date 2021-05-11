package io.github.danielnaczo.python3parser.model.expr.atoms.trailers;

import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

//e.g.:

// .attr
public class Attribute extends Expression {
	static int PRECEDENCE = 160;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Identifier attr;
	
	public Attribute (Identifier attr) {
		this.attr = attr;
	}

	public Identifier getAttr() {
		return attr;
	}

	public void setAttr(Identifier attr) {
		this.attr = attr;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAttribute(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Attribute attribute = (Attribute) o;
		return Objects.equals(attr, attribute.attr);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attr);
	}
}
