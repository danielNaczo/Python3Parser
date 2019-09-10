package com.github.python3parser.visitors.prettyprint;

import java.util.Optional;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.ExceptHandler;

public class ExceptHandlerPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitExceptHandler(ExceptHandler exceptHandler, IndentationPrettyPrint param) {
		String string = new String();
		string = string.concat(param.getIndentationString());
		
		Optional<Expression> test = exceptHandler.getTest();
		Optional<Identifier> name = exceptHandler.getName();
		
		string = string.concat("except");
		
		if (test.isPresent()) {
			string = string.concat(" ");
			string = string.concat(test.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			
			if (name.isPresent()) {
				string = string.concat(" as ");
				string = string.concat(name.get().getName());
			}
		}
		
		string = string.concat(":");
		
		string = string.concat("\n");
		return string;
	}
}
