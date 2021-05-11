package io.github.danielnaczo.python3parser.model;

import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

public interface AST {
	
	<R, P> R accept(Python3ASTVisitor<R, P> visitor, P param);
}
