package com.github.python3parser.visitors.ast;

import com.github.python3parser.visitors.exceptions.RuleException;
import com.github.python3parser.Python3Parser.SubscriptContext;
import com.github.python3parser.Python3Parser.SubscriptIndexContext;
import com.github.python3parser.Python3Parser.SubscriptSliceContext;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.Index;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.Slice;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;

public class SliceVisitor extends GenericUnsupportedCSTVisitor<SliceAbstract>{

	@Override
	public SliceAbstract visitSubscript(SubscriptContext ctx) {
		if (ctx.subscriptIndex() != null) {
			return ctx.subscriptIndex().accept(new SliceVisitor());
		}
		if (ctx.subscriptSlice() != null) {
			return ctx.subscriptSlice().accept(new SliceVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public SliceAbstract visitSubscriptIndex(SubscriptIndexContext ctx) {
		Expression value = ctx.test().accept(new ExpressionVisitor());
		return new Index(value);
	}
	
	@Override
	public SliceAbstract visitSubscriptSlice(SubscriptSliceContext ctx) {
		Expression lower = ctx.slicelLower().accept(new ExpressionVisitor());
		Expression upper = ctx.sliceUpper().accept(new ExpressionVisitor());
		Expression step = null;
		
		if (ctx.sliceStep() != null) {
			step = ctx.sliceStep().accept(new ExpressionVisitor());
		}
		
		return new Slice(lower, upper, step);
	}
}
