package com.github.python3parser.model.stmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//class is used for "suite" because it could have multiple statements

//e.g.:

// body.element1
// body.element2
// .......
public class Body extends Statement {

	List<Statement> statements;
	Statement parentStmt;
	
	public Body() {
		this.statements = new ArrayList<>();
	}

	public Body(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	
	public Statement getParentStmt() {
		return parentStmt;
	}
	
	public void setParentStmt(Statement parentStmt) {
		this.parentStmt = parentStmt;
	}
	
	public void addStatement(Statement statement) {
		this.statements.add(statement);
		
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitBody(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Body body = (Body) o;
		return Objects.equals(statements, body.statements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statements);
	}
}
