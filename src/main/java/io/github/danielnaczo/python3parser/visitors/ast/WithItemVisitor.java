package io.github.danielnaczo.python3parser.visitors.ast;

import io.github.danielnaczo.python3parser.Python3Parser.With_itemContext;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.withStmts.WithItem;

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
