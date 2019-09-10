package com.github.python3parser.model.stmts.compoundStmts.withStmts;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//differs from Original AST grammar


//e.g.:

// with open("woerterbuch.txt", "r") as fobj:
//     body.element1
//     body.element2
public class With extends Statement{

	List<WithItem> items;
	Statement body;
	
	public With(List<WithItem> items, Statement body) {
		this.items = items;
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
	
	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
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
}
