package com.github.python3parser.model.stmts.compoundStmts.withStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// with open("woerterbuch.txt", "r") as fobj:
//     body.element1
//     body.element2
public class With extends Statement{

	List<WithItem> items;
	Statement body;
	
	public With() {
		this(null, null);
	}
	
	public With(List<WithItem> items) {
		this(items, null);
	}
	
	public With(List<WithItem> items, Statement body) {
		this.items = (items != null) ? items : new ArrayList<>();
		this.body = body;
		setParentToBody();
	}

	public List<WithItem> getItems() {
		return items;
	}

	public Statement getBody() {
		return body;
	}

	public void setItems(List<WithItem> items) {
		this.items = items;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}
	
	public Statement addStatement(Statement statement) {
		if (this.body == null) {
			this.body = statement instanceof Expression ? (Expression) statement : statement;
			return statement;
		}
		this.body = transformStmtToBody();
		Body body = (Body) this.body;
		body.addStatement(statement);
		return statement;
	}
	
	public WithItem addWithItem(WithItem item) {
		this.items.add(item);
		return item;
	}
	
	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
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
		return visitor.visitWith(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		With with = (With) o;
		return Objects.equals(items, with.items) &&
				Objects.equals(body, with.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, body);
	}
	
	@Override
	public String toString() {
		return "With";
	}
}
