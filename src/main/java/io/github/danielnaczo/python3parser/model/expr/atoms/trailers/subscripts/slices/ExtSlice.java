package io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// l[1:2, 3]
public class ExtSlice extends SliceAbstract {

	List<SliceAbstract> dims;
	
	public ExtSlice (List<SliceAbstract> dims) {
		this.dims = dims;
	}

	public List<SliceAbstract> getDims() {
		return dims;
	}

	public void setDims(List<SliceAbstract> dims) {
		this.dims = dims;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExtSlice(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExtSlice extSlice = (ExtSlice) o;
		return Objects.equals(dims, extSlice.dims);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dims);
	}
}
