package com.github.python3parser.model.expr.comprehensions;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:
//lst=[1,2,3,4,5]
//{i**2 for i in lst}

//output: {4,1,25,9,16}  --> unsorted set
public class SetComp extends Expression {
	static int PRECEDENCE = 180;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression elt;
	List<Comprehension> comprehensions;
	
	public SetComp(Expression elt, List<Comprehension> comprehensions) {
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
		return visitor.visitSetComp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SetComp setComp = (SetComp) o;
		return Objects.equals(elt, setComp.elt) &&
				Objects.equals(comprehensions, setComp.comprehensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elt, comprehensions);
	}
}
