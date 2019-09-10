package com.github.python3parser.model.stmts.flowStmts;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

// e.g.

// yield arg from test
public class YieldStmt extends Statement{
	Expression yield;
	
	public YieldStmt(Expression yield) {
		this.yield = yield;
	}

	public Expression getYield() {
		return yield;
	}

	public void setYield(Expression yield) {
		this.yield = yield;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitYieldStmt(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		YieldStmt yieldStmt = (YieldStmt) o;
		return Objects.equals(yield, yieldStmt.yield);
	}

	@Override
	public int hashCode() {
		return Objects.hash(yield);
	}
}
