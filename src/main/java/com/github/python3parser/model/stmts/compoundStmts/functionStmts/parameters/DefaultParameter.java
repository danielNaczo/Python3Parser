package com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

//e.g.:

// parameter = value
public class DefaultParameter implements AST{

	Parameter parameter;
	Expression value;
	
	public DefaultParameter(String string, Expression value) {
		this(new Parameter(string), value);
	}

	public DefaultParameter(Parameter parameter, Expression value){
		this.parameter = parameter;
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitDefaultParameter(this, param);
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DefaultParameter that = (DefaultParameter) o;
		return Objects.equals(parameter, that.parameter) &&
				Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parameter, value);
	}
}
