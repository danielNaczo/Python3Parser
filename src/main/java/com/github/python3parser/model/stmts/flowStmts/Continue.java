package com.github.python3parser.model.stmts.flowStmts;

import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// continue
public class Continue extends Statement{

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Continue) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitContinue(this, param);
	}
}
