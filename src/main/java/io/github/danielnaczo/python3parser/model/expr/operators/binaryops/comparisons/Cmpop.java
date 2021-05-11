package io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BinOp;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public abstract class Cmpop extends BinOp implements AST{
	static int PRECEDENCE = 60;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	public Cmpop(Expression left, Expression right) {
		super(left, right);
	}
		
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitCmpop(this, param);
	}
}
