package com.github.python3parser.visitors.prettyprint;

import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;

public class DecoratorPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint> {
	@Override
	public String visitDecorator(Decorator decorator, IndentationPrettyPrint param) {
		String string = new String();
		
		String name = decorator.getName().getName();
		Optional<Expression> arguments = decorator.getArguments();
		
		string = string.concat("@");
		string = string.concat(name);

		if (arguments.isPresent()) {
			string = string.concat(arguments.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
}
