package io.github.danielnaczo.python3parser.model.expr;

import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public abstract class Expression extends Statement{
	Expression parent;
	
	public abstract int getPrecedence();
	
	public Expression getParent() {
		return parent;
	}
	
	public void setParent(Expression parent) {
		this.parent = parent;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExpression(this, param);
	}
}
