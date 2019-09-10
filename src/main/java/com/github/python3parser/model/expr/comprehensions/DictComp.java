package com.github.python3parser.model.expr.comprehensions;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:
//namen: ["Donald", "Dagobert", "Daisy"]
//{k:len(k) for k in namen}

//output: {'Donald' : 6, 'Dagobert' : 8, 'Daisy' : 5}
public class DictComp extends Expression {
	static int PRECEDENCE = 180;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Expression key;
	Expression value;
	List<Comprehension> comprehensions;
	
	public DictComp(Expression key, Expression value, List<Comprehension> comprehensions) {
		this.key = key;
		this.value = value;
		this.comprehensions = comprehensions;
	}

	public Expression getKey() {
		return key;
	}

	public void setKey(Expression key) {
		this.key = key;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	public List<Comprehension> getComprehensions() {
		return comprehensions;
	}

	public void setComprehensions(List<Comprehension> comprehensions) {
		this.comprehensions = comprehensions;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitDictComp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DictComp dictComp = (DictComp) o;
		return Objects.equals(key, dictComp.key) &&
				Objects.equals(value, dictComp.value) &&
				Objects.equals(comprehensions, dictComp.comprehensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value, comprehensions);
	}
}
