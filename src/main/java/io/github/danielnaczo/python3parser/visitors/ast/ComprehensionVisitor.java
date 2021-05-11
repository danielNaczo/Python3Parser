package io.github.danielnaczo.python3parser.visitors.ast;

import java.util.ArrayList;
import java.util.List;

import io.github.danielnaczo.python3parser.Python3Parser.Comp_forContext;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.Comprehension;

public class ComprehensionVisitor extends GenericUnsupportedCSTVisitor<Comprehension>{
	
	@Override
	public Comprehension visitComp_for(Comp_forContext ctx) {
		Expression target = ctx.exprlist().accept(new ExpressionVisitor());
		Expression iter = ctx.or_test().accept(new ExpressionVisitor());
		List<Expression> ifs = new ArrayList<>();
		int isAsync = (ctx.ASYNC() == null) ? 0 : 1;
		
		for (int i = 0; i < ctx.comp_if().size(); i++) {
			ifs.add(ctx.comp_if(i).accept(new ExpressionVisitor()));
		}
		
		return new Comprehension(target, iter, ifs, isAsync);
	}
}
