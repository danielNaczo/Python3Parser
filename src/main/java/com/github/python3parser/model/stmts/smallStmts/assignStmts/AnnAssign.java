package com.github.python3parser.model.stmts.smallStmts.assignStmts;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g:

// a: int = 1
public class AnnAssign extends Statement{
	
	Expression target;
	Expression annotation;
	Optional<Expression> value;
	
	public AnnAssign(Expression target, Expression annotation, Expression value) {
		this.target = target;
		this.annotation = annotation;
		this.value = Optional.ofNullable(value);
	}

	public Expression getTarget() {
		return target;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public Expression getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Expression annotation) {
		this.annotation = annotation;
	}

	public Optional<Expression> getValue() {
		return value;
	}

	public void setValue(Optional<Expression> value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAnnAssign(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AnnAssign annAssign = (AnnAssign) o;
		return Objects.equals(target, annAssign.target) &&
				Objects.equals(annotation, annAssign.annotation) &&
				Objects.equals(value, annAssign.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, annotation, value);
	}
}
