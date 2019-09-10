package com.github.python3parser.visitors.prettyprint;

import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Keyword;

public class KeywordPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitKeyword(Keyword keyword, IndentationPrettyPrint param) {
		String string = new String();
		
		Optional<Expression> arg = keyword.getArg();
		Expression value = keyword.getValue();
		
		if (arg.isPresent()) {
			string = string.concat(arg.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			string = string.concat("=");
		}
		string = string.concat(value.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		return string;
	}
}
