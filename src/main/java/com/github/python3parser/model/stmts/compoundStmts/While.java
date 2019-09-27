package com.github.python3parser.model.stmts.compoundStmts;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// e.g.:

// while test:
//     body.element1
//     body.element2
// else:
//     orElse.element1
//     orElse.element2
public class While extends Statement{
	
	Expression test;
	Statement body;
	Optional<Statement> orElse;
	
	public While() {
		this(null, null, null);
	}
	
	public While(String test) {
		this(new Name(test), null, null);
	}
	
	public While(Expression test) {
		this(test, null, null);
	}
	
	public While(String test, Statement body) {
		this(new Name(test), body, null);
	}
	
	public While(Expression test, Statement body) {
		this(test, body, null);
	}
	
	public While(Expression test, Statement body, Statement orElse) {
		this.test = test;
		this.body = body;
		this.orElse = Optional.ofNullable(orElse);
		setParentToBodies();
	}

	public Expression getTest() {
		return test;
	}

	public void setTest(Expression test) {
		this.test = test;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}

	public Optional<Statement> getOrElse() {
		return orElse;
	}

	public void setOrElse(Optional<Statement> orElse) {
		this.orElse = orElse;
		setParentToOrElse();
	}
	
	public Statement addStatement(Statement statement) {
		if (this.body == null) {
			this.body = statement;
			return statement;
		}
		this.body = transformStmtToBody();
		Body body = (Body) this.body;
		body.addStatement(statement);
		return statement;
	}
	
	private Body transformStmtToBody() {
		if (this.body instanceof Body) return (Body) this.body;
		Statement statement = this.body;
		List<Statement> statements = new ArrayList<>();
		statements.add(statement);
		Body body = new Body(statements);
		body.setParentStmt(this);
		return body;
	}
	
	private void setParentToBodies() {
		setParentToBody();
		setParentToOrElse();
	}

	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
	}

	private void setParentToOrElse() {
		if (orElse.isPresent()) {
			Statement orElseStmt = orElse.get();
			if (orElseStmt instanceof Body) ((Body) orElseStmt).setParentStmt(this);
		}
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitWhile(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		While aWhile = (While) o;
		return Objects.equals(test, aWhile.test) &&
				Objects.equals(body, aWhile.body) &&
				Objects.equals(orElse, aWhile.orElse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(test, body, orElse);
	}
	
	@Override
	public String toString() {
		return "While";
	}
}
