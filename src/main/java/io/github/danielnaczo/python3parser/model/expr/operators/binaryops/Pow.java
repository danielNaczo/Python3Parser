package io.github.danielnaczo.python3parser.model.expr.operators.binaryops;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//TODO after restructe of rule "atom_expr" in grammar, adapt this class to the others
public class Pow extends BinOp {
	static int PRECEDENCE = 140;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Pow(Expression left, Expression right) {
		super(left, right);
	}
	
	public Pow() {
		super();
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitPow(this, param);
	}
}
