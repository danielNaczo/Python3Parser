package com.github.python3parser.visitors.ast;

import com.github.python3parser.Python3Parser.With_itemContext;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.WithItem;

public class WithItemVisitor extends GenericUnsupportedCSTVisitor<WithItem>{

	@Override
	public WithItem visitWith_item(With_itemContext ctx) {
		Expression contextExpr = ctx.test().accept(new ExpressionVisitor());
		Expression optionalVars = null;
		
		if (ctx.expr() != null) {
			optionalVars = ctx.expr().accept(new ExpressionVisitor());
		}
		
		return new WithItem(contextExpr, optionalVars);
	}
}
