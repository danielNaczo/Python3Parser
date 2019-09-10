package com.github.python3parser.model.expr.atoms.trailers.arguments;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.comprehensions.Comprehension;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;


//e.g.: self._from_iterable(value for value in other if value in self)
public class ArgumentComp extends Expression {
	static int PRECEDENCE = 180;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression elt;
	List<Comprehension> comprehensions;
	
	public ArgumentComp(Expression elt, List<Comprehension> comprehensions) {
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
		return visitor.visitArgumentComp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArgumentComp that = (ArgumentComp) o;
		return Objects.equals(elt, that.elt) &&
				Objects.equals(comprehensions, that.comprehensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elt, comprehensions);
	}
}
