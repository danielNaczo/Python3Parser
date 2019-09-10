package com.github.python3parser.model.expr.operators;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public abstract class Operator extends Expression implements AST {

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitOperator(this, param);
	}
}
