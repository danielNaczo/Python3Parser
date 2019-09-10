package com.github.python3parser.model.expr.comprehensions;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;


//e.g.: [x**2 for x in list if x%2 == 0]

//input in program: [1,2,3,4,5,6,7,8,9]
//output in program: [4,16,36,64]
public class ListComp extends Expression{
	static int PRECEDENCE = 180;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression elt;
	List<Comprehension> comprehensions;
	
	public ListComp(Expression elt, List<Comprehension> comprehensions) {
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
		return visitor.visitListComp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListComp listComp = (ListComp) o;
		return Objects.equals(elt, listComp.elt) &&
				Objects.equals(comprehensions, listComp.comprehensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elt, comprehensions);
	}
}
