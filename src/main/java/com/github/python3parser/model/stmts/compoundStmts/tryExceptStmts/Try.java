package com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//differs from original AST grammar

// e.g.:

// try:
//     body.element1
//     body.element2
// except handlers(0):
//     handlersBody(0).element1
//     handlersBody(0).element2
// else:
//     orElse.element1
//     orElse.element2
// finally:
//     finalBody.element1
//     finalBody.element2
public class Try extends Statement{

	Statement body;
	List<ExceptHandler> handlers;
	List<Statement> handlersBody;
	Optional<Statement> orElse;
	Optional<Statement> finalBody;
	
	public Try() {
		this(null, null, null, null, null);
	}
	
	public Try(Statement body) {
		this(body, null, null, null, null);
	}

	public Try(Statement body, List<ExceptHandler> handlers) {
		this(body, handlers, null, null, null);
	}
	
	public Try(Statement body, List<ExceptHandler> handlers, List<Statement> handlersBody) {
		this(body, handlers, handlersBody, null, null);
	}
	
	public Try(Statement body, List<ExceptHandler> handlers, List<Statement> handlersBody,
			Statement orElse) {
		this(body, handlers, handlersBody, orElse, null);
	}
	
	public Try(Statement body, List<ExceptHandler> handlers, List<Statement> handlersBody,
			Statement orElse, Statement finalBody) {
		this.body = body;
		this.handlers = (handlers != null) ? handlers : new ArrayList<>();
		this.handlersBody = (handlersBody != null) ? handlersBody : new ArrayList<>();
		this.orElse = Optional.ofNullable(orElse);
		this.finalBody = Optional.ofNullable(finalBody);
		setParentToBodies();
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}

	public List<ExceptHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<ExceptHandler> handlers) {
		this.handlers = handlers;
	}

	public List<Statement> getHandlersBody() {
		return handlersBody;
	}

	public void setHandlersBody(List<Statement> handlersBody) {
		this.handlersBody = handlersBody;
		setParentToHandlersBody();
	}

	public Optional<Statement> getOrElse() {
		return orElse;
	}

	public void setOrElse(Optional<Statement> orElse) {
		this.orElse = orElse;
		setParentToOrElse();
	}

	public Optional<Statement> getFinalBody() {
		return finalBody;
	}

	public void setFinalBody(Optional<Statement> finalBody) {
		this.finalBody = finalBody;
		setParentToFinalBody();
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
	
	public ExceptHandler addHandlerWithBody(ExceptHandler handler, Statement handlerBody) {
		this.handlers.add(handler);
		this.handlersBody.add(handlerBody);
		setParentToHandlersBody();
		return handler;
	}
	
	public Statement addStatementToHandler(ExceptHandler handler, Statement statement) {
		if (!this.handlers.contains(handler)) throw new NoSuchElementException("Handler does not exist in attribute 'handlers'.");
		int indexOfHandler = this.handlers.indexOf(handler);
		if (this.handlersBody.get(indexOfHandler) instanceof Body) {
			Body body = (Body) this.handlersBody.get(indexOfHandler);
			body.addStatement(statement);
		} else {
			List<Statement> statements = new ArrayList<>();
			statements.add(this.handlersBody.get(indexOfHandler));
			statements.add(statement);
			Body body = new Body(statements);
			body.setParentStmt(this);
			this.handlersBody.remove(indexOfHandler);
			this.handlersBody.add(indexOfHandler, body);
		}
		
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
		setParentToHandlersBody();
		setParentToOrElse();
		setParentToFinalBody();
	}

	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
	}

	private void setParentToHandlersBody() {
		if (handlersBody == null) return;
		for (Statement body : handlersBody) {
			if (body instanceof Body) ((Body) body).setParentStmt(this);
		}
	}

	private void setParentToOrElse() {
		if (orElse.isPresent()) {
			Statement orElseStmt = orElse.get();
			if (orElseStmt instanceof Body) ((Body) orElseStmt).setParentStmt(this);
		}
	}

	private void setParentToFinalBody() {
		if (finalBody.isPresent()) {
			Statement finalStmt = finalBody.get();
			if (finalStmt instanceof Body) ((Body) finalStmt).setParentStmt(this);
		}
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitTry(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Try aTry = (Try) o;
		return Objects.equals(body, aTry.body) &&
				Objects.equals(handlers, aTry.handlers) &&
				Objects.equals(handlersBody, aTry.handlersBody) &&
				Objects.equals(orElse, aTry.orElse) &&
				Objects.equals(finalBody, aTry.finalBody);
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, handlers, handlersBody, orElse, finalBody);
	}
	
	@Override
	public String toString() {
		return "Try";
	}
}
