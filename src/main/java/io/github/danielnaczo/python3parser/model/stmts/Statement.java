package io.github.danielnaczo.python3parser.model.stmts;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public abstract class Statement implements AST {

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitStatement(this, param);
	}
}
