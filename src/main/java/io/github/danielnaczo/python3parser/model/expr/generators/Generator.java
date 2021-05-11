package io.github.danielnaczo.python3parser.model.expr.generators;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.Comprehension;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public class Generator extends Expression {
	static int PRECEDENCE = 180;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression elt;
	List<Comprehension> comprehensions;
	
	public Generator(Expression elt, List<Comprehension> comprehensions) {
		this.elt = elt;
		this.comprehensions = comprehensions;
	}

	public Expression getElt() {
		return elt;
	}

	public void setElt(Expression elt) {
		this.elt = elt;
	}

	public List<Comprehension> getComprehensions() {
		return comprehensions;
	}

	public void setComprehensions(List<Comprehension> comprehensions) {
		this.comprehensions = comprehensions;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitGenerator(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Generator tupleComp = (Generator) o;
		return Objects.equals(elt, tupleComp.elt) &&
				Objects.equals(comprehensions, tupleComp.comprehensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elt, comprehensions);
	}
}
