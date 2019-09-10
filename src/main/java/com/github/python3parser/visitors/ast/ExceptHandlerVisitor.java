package com.github.python3parser.visitors.ast;

import com.github.python3parser.Python3Parser.Except_clauseContext;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.ExceptHandler;

public class ExceptHandlerVisitor extends GenericUnsupportedCSTVisitor<ExceptHandler> {

	@Override
	public ExceptHandler visitExcept_clause(Except_clauseContext ctx) {
		Expression test = null;
		Identifier name = null;
		
		if (ctx.test() != null) {
			test = ctx.test().accept(new ExpressionVisitor());
		}
		if (ctx.NAME() != null) {
			name = new Identifier(ctx.NAME().getText());
		}
		
		return new ExceptHandler(test, name);
	}
}
