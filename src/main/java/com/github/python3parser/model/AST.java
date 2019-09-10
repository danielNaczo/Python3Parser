package com.github.python3parser.model;

import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public interface AST {
	
	<R, P> R accept(Python3ASTVisitor<R, P> visitor, P param);
}
