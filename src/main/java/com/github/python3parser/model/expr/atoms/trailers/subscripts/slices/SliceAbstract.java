package com.github.python3parser.model.expr.atoms.trailers.subscripts.slices;

import com.github.python3parser.model.AST;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//actually "slices" in abstract grammar
public abstract class SliceAbstract implements AST {

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitSliceAbstract(this, param);
	}
}
