package io.github.danielnaczo.python3parser.model.expr.comprehensions;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// async for target in iter if ifs.element1 if ifs.element2
public class Comprehension implements AST {
	
	Expression target;
	Expression iter;
	List<Expression> ifs;
	int isAsync;
	
	public Comprehension(Expression target, Expression iter, List<Expression> ifs, int isAsync) {
		this.target = target;
		this.iter = iter;
		this.ifs = ifs;
		this.isAsync = isAsync;
	}

	public Expression getTarget() {
		return target;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public Expression getIter() {
		return iter;
	}

	public void setIter(Expression iter) {
		this.iter = iter;
	}

	public List<Expression> getIfs() {
		return ifs;
	}

	public void setIfs(List<Expression> ifs) {
		this.ifs = ifs;
	}

	public int getIsAsync() {
		return isAsync;
	}

	public void setIsAsync(int isAsync) {
		this.isAsync = isAsync;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitComprehension(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comprehension that = (Comprehension) o;
		return isAsync == that.isAsync &&
				Objects.equals(target, that.target) &&
				Objects.equals(iter, that.iter) &&
				Objects.equals(ifs, that.ifs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, iter, ifs, isAsync);
	}
}
