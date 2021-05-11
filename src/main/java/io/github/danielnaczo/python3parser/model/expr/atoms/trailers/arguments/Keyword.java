package io.github.danielnaczo.python3parser.model.expr.atoms.trailers.arguments;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// " b=c "
//  b is "arg" and c is "value" 

// " **e "
// null in "arg" and e in "value"
public class Keyword implements AST {

	Optional<Expression> arg;
	Expression value;
	
	public Keyword (Expression arg, Expression value) {
		this.arg = Optional.ofNullable(arg);
		this.value = value;
	}

	public Optional<Expression> getArg() {
		return arg;
	}

	public void setArg(Optional<Expression> arg) {
		this.arg = arg;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitKeyword(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Keyword keyword = (Keyword) o;
		return Objects.equals(arg, keyword.arg) &&
				Objects.equals(value, keyword.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(arg, value);
	}
}
