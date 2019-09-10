package com.github.python3parser.model.expr.operators.binaryops.comparisons;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.operators.binaryops.BinOp;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

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
