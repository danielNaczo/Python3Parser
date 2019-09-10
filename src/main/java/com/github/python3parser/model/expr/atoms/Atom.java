package com.github.python3parser.model.expr.atoms;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//look at rule "atom_expr"
public class Atom extends Expression{
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression atomElement;
	List<Expression> trailers;
	
	public Atom(Expression atomElement, List<Expression> trailers) {
		this.atomElement = atomElement;
		this.trailers = trailers;
		setParent();
	}

	private void setParent() {
		if (trailers != null) {
			for (Expression trailer : trailers) {
				trailer.setParent(this);
			}
		}
	}

	public Expression getAtomElement() {
		return atomElement;
	}

	public void setAtomElement(Expression atomElement) {
		this.atomElement = atomElement;
	}

	public List<Expression> getTrailers() {
		return trailers;
	}

	public void setTrailers(List<Expression> trailers) {
		this.trailers = trailers;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAtom(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Atom atom = (Atom) o;
		return Objects.equals(atomElement, atom.atomElement) &&
				Objects.equals(trailers, atom.trailers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(atomElement, trailers);
	}
}
