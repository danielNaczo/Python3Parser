package com.github.python3parser.visitors.prettyprint;

import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.WithItem;

public class WithItemPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{
	
	@Override
	public String visitWithItem(WithItem withItem, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression contextExpr = withItem.getContextExpr();
		Optional<Expression> optionalVars = withItem.getOptionalVars();
		
		string = string.concat(contextExpr.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		if (optionalVars.isPresent()) {
			string = string.concat(" as ");
			string = string.concat(optionalVars.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
}
