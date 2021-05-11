package io.github.danielnaczo.python3parser.model.mods;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.Objects;

//in grammar: eval_input
public class ExpressionMod extends Mod{
	Expression body;

	public ExpressionMod(Expression body) {
		this.body = body;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitExpressionMod(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExpressionMod that = (ExpressionMod) o;
		return Objects.equals(body, that.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(body);
	}
}
