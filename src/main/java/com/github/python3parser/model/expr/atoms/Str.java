package com.github.python3parser.model.expr.atoms;

import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Str extends Expression {
	static int PRECEDENCE = 190;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	String s;
	
	public Str (String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitStr(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Str str = (Str) o;
		return Objects.equals(s, str.s);
	}

	@Override
	public int hashCode() {
		return Objects.hash(s);
	}
}
