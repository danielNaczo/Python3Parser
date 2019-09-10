package com.github.python3parser.model.stmts.compoundStmts.withStmts;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//differs from Original AST grammar


//e.g.:

// async with open("woerterbuch.txt", "r") as fobj:
//     body.element1
//     body.element2
public class AsyncWith extends Statement{
	
	List<WithItem> items;
	Statement body;
	
	public AsyncWith(List<WithItem> items, Statement body) {
		this.items = items;
		this.body = body;
		setParentToBody();
	}

	public List<WithItem> getItems() {
		return items;
	}

	public void setItems(List<WithItem> items) {
		this.items = items;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}
	
	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitAsyncWith(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AsyncWith asyncWith = (AsyncWith) o;
		return Objects.equals(items, asyncWith.items) &&
				Objects.equals(body, asyncWith.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, body);
	}
}
