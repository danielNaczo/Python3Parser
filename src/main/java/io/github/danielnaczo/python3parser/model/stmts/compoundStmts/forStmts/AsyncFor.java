package io.github.danielnaczo.python3parser.model.stmts.compoundStmts.forStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//differs from Original AST grammar

//e.g:

// async for target in iter:
//     body.element1
//     body.element2
// else:
//     orElse.element1
//     orElse.element2
public class AsyncFor extends Statement{

	Expression target;
	Expression iter;
	Statement body;
	Optional<Statement> orElse;
	
	public AsyncFor(){
		this(null, null, null, null);
	}
	
	public AsyncFor(Expression target) {
		this(target, null, null, null);
	}
	
	public AsyncFor(Expression target, Expression iter){
		this(target, iter, null, null);
	}
	
	public AsyncFor(Expression target, Expression iter, Statement body) {
		this(target, iter, body, null);
	}
	
	public AsyncFor(Expression target, Expression iter, Statement body, Statement orElse) {
		this.target = target;
		this.iter = iter;
		this.body = body;
		this.orElse = Optional.ofNullable(orElse);
		setParentToBody();
		setParentToOrElse();
	}

	public Expression getTarget() {
		return target;
	}

	public Expression getIter() {
		return iter;
	}

	public Statement getBody() {
		return body;
	}

	public Optional<Statement> getOrElse() {
		return orElse;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public void setIter(Expression iter) {
		this.iter = iter;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
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
	
	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
	}

	private void setParentToOrElse() {
		if (orElse.isPresent()) {
			Statement orElseStmt = orElse.get();
			if (orElseStmt instanceof Body) ((Body) orElseStmt).setParentStmt(this);
		}
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
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAsyncFor(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AsyncFor asyncFor = (AsyncFor) o;
		return Objects.equals(target, asyncFor.target) &&
				Objects.equals(iter, asyncFor.iter) &&
				Objects.equals(body, asyncFor.body) &&
				Objects.equals(orElse, asyncFor.orElse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, iter, body, orElse);
	}
	
	@Override
	public String toString() {
		return "AsyncFor";
	}
}
