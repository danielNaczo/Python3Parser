package io.github.danielnaczo.python3parser.visitors.ast;

import io.github.danielnaczo.python3parser.Python3Parser.SubscriptContext;
import io.github.danielnaczo.python3parser.Python3Parser.SubscriptIndexContext;
import io.github.danielnaczo.python3parser.Python3Parser.SubscriptSliceContext;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.Index;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.Slice;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.visitors.exceptions.RuleException;

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
