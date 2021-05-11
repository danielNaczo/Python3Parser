package io.github.danielnaczo.python3parser.model.expr.operators;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// lambda args : body
public class Lambda extends Operator {
	static int PRECEDENCE = 10;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	Optional<Parameters> args;
	Expression body;
	
	public Lambda(Parameters args, Expression body) {
		this.args = Optional.ofNullable(args);
		this.body = body;
	}

	public Optional<Parameters> getArgs() {
		return args;
	}

	public void setArgs(Optional<Parameters> args) {
		this.args = args;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitLambda(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Lambda)) return false;
		Lambda lambda = (Lambda) o;
		return Objects.equals(args, lambda.args) &&
				Objects.equals(body, lambda.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(args, body);
	}
}
