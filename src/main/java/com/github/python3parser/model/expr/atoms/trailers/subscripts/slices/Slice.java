package com.github.python3parser.model.expr.atoms.trailers.subscripts.slices;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// ziffern = "0123456789"

// ziffern[2] 		--> output: 2
//		  ^^^
// ziffern[2:4]		--> output: 2, 3
// ziffern[:4]		--> output: 0, 1, 2, 3
// ziffern[2:]		--> output: 3, 4, 5, 6, 7, 8, 9
// ziffern[:]		--> output: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
// ziffern[2:8:2]	--> output: 2, 4, 6

public class Slice extends SliceAbstract {

	Optional<Expression> lower;
	Optional<Expression> upper;
	Optional<Expression> step;
	
	public Slice (Expression lower, Expression upper, Expression step) {
		this.lower = Optional.ofNullable(lower);
		this.upper = Optional.ofNullable(upper);
		this.step = Optional.ofNullable(step);
	}

	public Optional<Expression> getLower() {
		return lower;
	}

	public void setLower(Optional<Expression> lower) {
		this.lower = lower;
	}

	public Optional<Expression> getUpper() {
		return upper;
	}

	public void setUpper(Optional<Expression> upper) {
		this.upper = upper;
	}

	public Optional<Expression> getStep() {
		return step;
	}

	public void setStep(Optional<Expression> step) {
		this.step = step;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitSlice(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Slice slice = (Slice) o;
		return Objects.equals(lower, slice.lower) &&
				Objects.equals(upper, slice.upper) &&
				Objects.equals(step, slice.step);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lower, upper, step);
	}
}
