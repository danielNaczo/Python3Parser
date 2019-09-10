package com.github.python3parser.visitors.ast;

import com.github.python3parser.Python3Parser.ArgumentDoubleStarContext;
import com.github.python3parser.Python3Parser.ArgumentKeywordContext;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Keyword;

public class KeywordVisitor extends GenericUnsupportedCSTVisitor<Keyword> {

	@Override
	public Keyword visitArgumentKeyword(ArgumentKeywordContext ctx) {
		Expression arg = ctx.test(0).accept(new ExpressionVisitor());
		Expression value = ctx.test(1).accept(new ExpressionVisitor());
		
		return new Keyword(arg, value);
	}
	
	@Override
	public Keyword visitArgumentDoubleStar(ArgumentDoubleStarContext ctx) {
		Expression arg = null;
		Expression value = ctx.test().accept(new ExpressionVisitor());
		
		return new Keyword(arg, value);
	}
}
