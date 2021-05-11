package io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

//e.g.

// ziffern[1:10:2]
//        ^^^^^^^^^
public class Subscript extends Expression {
	static int PRECEDENCE = 160;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	SliceAbstract slice;
	
	public Subscript(SliceAbstract slice) {
		this.slice = slice;
	}

	public SliceAbstract getSlice() {
		return slice;
	}

	public void setSlice(SliceAbstract slice) {
		this.slice = slice;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitSubscript(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Subscript subscript = (Subscript) o;
		return Objects.equals(slice, subscript.slice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(slice);
	}
}
