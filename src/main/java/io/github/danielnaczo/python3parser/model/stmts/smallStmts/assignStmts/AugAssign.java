package io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.Name;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

//e.g.:

// a += 1
public class AugAssign extends Statement{
	
	Expression target;
	Operator op;
	Expression value;
	
	public AugAssign(String target, Operator op, String value) {
		this(new Name(target), op, new Name(value));
	}

	public AugAssign(Expression target, Operator op, Expression value) {
		this.target = target;
		this.op = op;
		this.value = value;
	}

	public Expression getTarget() {
		return target;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAugAssign(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AugAssign augAssign = (AugAssign) o;
		return Objects.equals(target, augAssign.target) &&
				Objects.equals(op, augAssign.op) &&
				Objects.equals(value, augAssign.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, op, value);
	}
	
	@Override
	public String toString() {
		return "AugAssign";
	}
}
