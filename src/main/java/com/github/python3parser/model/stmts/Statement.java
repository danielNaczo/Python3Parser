package com.github.python3parser.model.stmts;

import com.github.python3parser.model.AST;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public abstract class Statement implements AST {

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitStatement(this, param);
	}
}
