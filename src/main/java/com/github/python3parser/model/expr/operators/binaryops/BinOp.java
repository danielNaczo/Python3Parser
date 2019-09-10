package com.github.python3parser.model.expr.operators.binaryops;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.expr.operators.Operator;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

public abstract class BinOp extends Operator{

	Expression left;
	Expression right;
	
	public BinOp(String left, String right) {
		this(new Name(new Identifier(left)), new Name(new Identifier(right)));
	}

	public BinOp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	//just for AugAssigns with no expressions (e.g.: target += 5)
	public BinOp() {}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitBinOp(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BinOp)) return false;
		BinOp binOp = (BinOp) o;
		return Objects.equals(left, binOp.left) &&
				Objects.equals(right, binOp.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}
}
