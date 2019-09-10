package com.github.python3parser.visitors.prettyprint;

import java.util.List;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.comprehensions.Comprehension;

public class ComprehensionPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitComprehension(Comprehension comprehension, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression target = comprehension.getTarget();
		Expression iter = comprehension.getIter();
		List<Expression> ifs = comprehension.getIfs();
		int isAsync = comprehension.getIsAsync();
		
		if (isAsync == 1) {
			string = string.concat("async ");
		}
		
		string = string.concat("for ");
		
		string = string.concat(target.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" in ");
		string = string.concat(iter.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < ifs.size(); i++) {
			string = string.concat(" if ");
			string = string.concat(ifs.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
}
