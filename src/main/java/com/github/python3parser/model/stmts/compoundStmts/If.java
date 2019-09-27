package com.github.python3parser.model.stmts.compoundStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//this class varies from the original AST class

//e.g.:

// if ifTest:
//     ifSuite.element1
//     ifSuite.element2
// elif elifTests(0):
//     elifSuite(0).element1
//     elifSuite(0).element2
// else:
//     elseSuite.element1
//     elseSuite.element2
public class If extends Statement{
	
	Expression ifTest;
	Statement ifBody;
	List<Expression> elifTests;
	List<Statement> elifBodies;
	Optional<Statement> elseBody;
	
	public If(Expression ifTest) {
		this(ifTest, null, null, null, null);
	}
	
	public If(Expression ifTest, Statement ifBody) {
		this(ifTest, ifBody, null, null, null);
	}
	
	public If(Expression ifTest, Statement ifBody, 
			List<Expression> elifTests, List<Statement> elifBodies) {
		this(ifTest, ifBody, elifTests, elifBodies, null);
	}
	
	public If(Expression ifTest, Statement ifBody, 
			Statement elseBody) {
		this(ifTest, ifBody, null, null, elseBody);
	}
	
	public If(Expression ifTest, Statement ifBody, 
			List<Expression> elifTests, List<Statement> elifBodies,
			Statement elseBody) {
		this.ifTest = ifTest;
		this.ifBody = ifBody;
		this.elifTests = (elifTests != null) ? elifTests : new ArrayList<>();
		this.elifBodies = (elifBodies != null) ? elifBodies : new ArrayList<>();
		this.elseBody = Optional.ofNullable(elseBody);
		setParentToBodies();
	}

	public Expression getIfTest() {
		return ifTest;
	}

	public void setIfTest(Expression ifTest) {
		this.ifTest = ifTest;
	}

	public Statement getIfBody() {
		return ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
		setParentToIfBody();
	}

	public List<Expression> getElifTests() {
		return elifTests;
	}

	public void setElifTests(List<Expression> elifTests) {
		this.elifTests = elifTests;
	}

	public List<Statement> getElifBodies() {
		return elifBodies;
	}

	public void setElifBodies(List<Statement> elifBodies) {
		this.elifBodies = elifBodies;
		setParentToElifBodies();
	}

	public Optional<Statement> getElseBody() {
		return elseBody;
	}

	public void setElseBody(Optional<Statement> elseBody) {
		this.elseBody = elseBody;
		setParentToElseBody();
	}
	
	public Statement addStatementToBody(Statement statement) {
		if (this.ifBody == null) {
			this.ifBody= statement;
			return statement;
		}
		this.ifBody = transformStmtToBody();
		Body body = (Body) this.ifBody;
		body.addStatement(statement);
		return statement;
	}
	
	public Expression addElifTestWithBody(Expression elifTest, Statement elifBody) {
		this.elifTests.add(elifTest);
		this.elifBodies.add(elifBody);
		setParentToElifBodies();
		return elifTest;
	}
	
	public Statement addStatementToElif(Expression elifTest, Statement elifStatement) {
		if (!this.elifTests.contains(elifTest)) throw new NoSuchElementException("ElifTest does not exist in attribute 'elifTests'.");
		int indexOfElifTest = this.elifTests.indexOf(elifTest);
		if (this.elifBodies.get(indexOfElifTest) instanceof Body) {
			Body body = (Body) this.elifBodies.get(indexOfElifTest);
			body.addStatement(elifStatement);
		} else {
			List<Statement> statements = new ArrayList<>();
			statements.add(this.elifBodies.get(indexOfElifTest));
			statements.add(elifStatement);
			Body body = new Body(statements);
			body.setParentStmt(this);
			this.elifBodies.remove(indexOfElifTest);
			this.elifBodies.add(indexOfElifTest, body);
		}
		
		return elifStatement;
	}
	
	private Body transformStmtToBody() {
		if (this.ifBody instanceof Body) return (Body) this.ifBody;
		Statement statement = this.ifBody;
		List<Statement> statements = new ArrayList<>();
		statements.add(statement);
		Body body = new Body(statements);
		body.setParentStmt(this);
		return body;
	}
	
	private void setParentToBodies() {
		setParentToIfBody();
		setParentToElifBodies();
		setParentToElseBody();
	}

	private void setParentToIfBody() {
		if (ifBody instanceof Body) ((Body) ifBody).setParentStmt(this);
	}

	private void setParentToElifBodies() {
		for (Statement statement : elifBodies) {
			if (statement instanceof Body) ((Body) statement).setParentStmt(this);
		}
	}

	private void setParentToElseBody() {
		if (elseBody.isPresent()) {
			Statement elseSuiteStmt = elseBody.get();
			if (elseSuiteStmt instanceof Body) ((Body) elseSuiteStmt).setParentStmt(this);
		}
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitIf(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		If anIf = (If) o;
		return Objects.equals(ifTest, anIf.ifTest) &&
				Objects.equals(ifBody, anIf.ifBody) &&
				Objects.equals(elifTests, anIf.elifTests) &&
				Objects.equals(elifBodies, anIf.elifBodies) &&
				Objects.equals(elseBody, anIf.elseBody);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ifTest, ifBody, elifTests, elifBodies, elseBody);
	}
	
	@Override
	public String toString() {
		return "If";
	}
}
