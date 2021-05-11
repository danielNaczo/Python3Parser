package io.github.danielnaczo.python3parser.model.stmts.smallStmts;

import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

// e.g.:

// assert Temperature >= 0, "Colder than absolute zero!"
public class Assert extends Statement{
	Expression test;
	Optional<Expression> msg;
	
	public Assert(Expression test) {
		this(test, null);
	}
	
	public Assert(Expression test, Expression msg) {
		this.test = test;
		this.msg = Optional.ofNullable(msg);
	}

	public Expression getTest() {
		return test;
	}

	public void setTest(Expression test) {
		this.test = test;
	}

	public Optional<Expression> getMsg() {
		return msg;
	}

	public void setMsg(Optional<Expression> msg) {
		this.msg = msg;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAssert(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Assert anAssert = (Assert) o;
		return Objects.equals(test, anAssert.test) &&
				Objects.equals(msg, anAssert.msg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(test, msg);
	}
	
	@Override
	public String toString() {
		return "Assert";
	}
}
