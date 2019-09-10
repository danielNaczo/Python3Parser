package com.github.python3parser.model.expr.atoms;

import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Num extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	String n;
	
	public Num(String n) {
		this.n =n;
	}
	
	public Num(int number) {
		this.n = Integer.toString(number);
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitNum(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Num num = (Num) o;
		return Objects.equals(n, num.n);
	}

	@Override
	public int hashCode() {
		return Objects.hash(n);
	}
}
