package io.github.danielnaczo.python3parser.visitors.ast;

import io.github.danielnaczo.python3parser.Python3Parser.DecoratorContext;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;

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
