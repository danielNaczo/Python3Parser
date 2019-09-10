package com.github.python3parser.visitors.prettyprint;

import java.util.List;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.Index;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.ExtSlice;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.Slice;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;

public class SlicePrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitExtSlice(ExtSlice extSlice, IndentationPrettyPrint param) {
		String string =  new String();
		
		List<SliceAbstract> dims = extSlice.getDims();
		
		for (int i = 0; i < dims.size(); i++) {
			string = string.concat(dims.get(i).accept(new SlicePrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			
			if (i != dims.size() - 1) {
				string = string.concat(", ");
			}
		}
		return string;
	}
	
	@Override
	public String visitIndex(Index index, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression value = index.getValue();
		string = string.concat(value.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		return string;
	}
	
	@Override
	public String visitSlice(Slice slice, IndentationPrettyPrint param) {
		String string = new String();
		
		Optional<Expression> lower = slice.getLower();
		Optional<Expression> upper = slice.getUpper();
		Optional<Expression> step = slice.getStep();
		
		if (lower.isPresent()) {
			string = string.concat(lower.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		string = string.concat(":");
		
		if (upper.isPresent()) {
			string = string.concat(upper.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		if (step.isPresent()) {
			string = string.concat(":");
			string = string.concat(step.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
}
