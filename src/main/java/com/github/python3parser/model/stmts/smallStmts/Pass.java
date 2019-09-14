package com.github.python3parser.model.stmts.smallStmts;

import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// pass
public class Pass extends Statement{
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pass) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitPass(this, param);
	}
	
	@Override
	public String toString() {
		return "Pass";
	}
}
