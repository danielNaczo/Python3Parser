package com.github.python3parser.visitors.ast;

import com.github.python3parser.Python3Parser.DecoratorContext;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;

public class DecoratorVisitor extends GenericUnsupportedCSTVisitor<Decorator>{
	
	@Override
	public Decorator visitDecorator(DecoratorContext ctx) {
		Identifier name = new Identifier(ctx.dotted_name().accept(new StringVisitor()));
		Expression arguments = null;
		
		if (ctx.arglist() != null) {
			arguments = ctx.arglist().accept(new ExpressionVisitor());
		}
		
		return new Decorator(name, arguments);
	}
}
