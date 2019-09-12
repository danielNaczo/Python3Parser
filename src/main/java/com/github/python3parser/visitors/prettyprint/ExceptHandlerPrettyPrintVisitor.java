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
		
		Optional<Expression> error = exceptHandler.getError();
		Optional<Identifier> errorAsName = exceptHandler.getErrorAsName();
		
		string = string.concat("except");
		
		if (error.isPresent()) {
			string = string.concat(" ");
			string = string.concat(error.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			
			if (errorAsName.isPresent()) {
				string = string.concat(" as ");
				string = string.concat(errorAsName.get().getName());
			}
		}
		
		string = string.concat(":");
		
		string = string.concat("\n");
		return string;
	}
}
