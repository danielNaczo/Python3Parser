package com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

//parameter: annotation
public class Parameter implements AST {
	
	Identifier parameterName; //when parameter is '*' then it's this case: def sum(a, b, * , c, d)
	Optional<Expression> annotation;
	
	public Parameter(String parameterName) {
		this(parameterName, null);
	}
	
	public Parameter(String parameterName, Expression annotation) {
		this(new Identifier(parameterName), annotation);
	}
	
	public Parameter(Identifier parameterName, Expression annotation) {
		this.parameterName = parameterName;
		this.annotation = Optional.ofNullable(annotation);
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitParameter(this, param);
	}

	public Identifier getParameterName() {
		return parameterName;
	}

	public void setParameterName(Identifier parameterName) {
		this.parameterName = parameterName;
	}

	public Optional<Expression> getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Optional<Expression> annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Parameter arg1 = (Parameter) o;
		return Objects.equals(parameterName, arg1.parameterName) &&
				Objects.equals(annotation, arg1.annotation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parameterName, annotation);
	}
}
