package com.github.python3parser.model.stmts.compoundStmts;

import java.util.List;
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
	Statement ifSuite;
	List<Expression> elifTests;
	List<Statement> elifSuites;
	Optional<Statement> elseSuite;
	
	public If(Expression ifTest, Statement ifSuite, List<Expression> elifTest, List<Statement> elifSuite,
			Statement elseSuite) {
		this.ifTest = ifTest;
		this.ifSuite = ifSuite;
		this.elifTests = elifTest;
		this.elifSuites = elifSuite;
		this.elseSuite = Optional.ofNullable(elseSuite);
		setParentToBodies();
	}

	public Expression getIfTest() {
		return ifTest;
	}

	public void setIfTest(Expression ifTest) {
		this.ifTest = ifTest;
	}

	public Statement getIfSuite() {
		return ifSuite;
	}

	public void setIfSuite(Statement ifSuite) {
		this.ifSuite = ifSuite;
		setParentToIfSuite();
	}

	public List<Expression> getElifTests() {
		return elifTests;
	}

	public void setElifTests(List<Expression> elifTests) {
		this.elifTests = elifTests;
	}

	public List<Statement> getElifSuites() {
		return elifSuites;
	}

	public void setElifSuites(List<Statement> elifSuites) {
		this.elifSuites = elifSuites;
		setParentToElifSuites();
	}

	public Optional<Statement> getElseSuite() {
		return elseSuite;
	}

	public void setElseSuite(Optional<Statement> elseSuite) {
		this.elseSuite = elseSuite;
		setParentToElseSuites();
	}
	
	private void setParentToBodies() {
		setParentToIfSuite();
		setParentToElifSuites();
		setParentToElseSuites();
	}

	private void setParentToIfSuite() {
		if (ifSuite instanceof Body) ((Body) ifSuite).setParentStmt(this);
	}

	private void setParentToElifSuites() {
		for (Statement statement : elifSuites) {
			if (statement instanceof Body) ((Body) statement).setParentStmt(this);
		}
	}

	private void setParentToElseSuites() {
		if (elseSuite.isPresent()) {
			Statement elseSuiteStmt = elseSuite.get();
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
				Objects.equals(ifSuite, anIf.ifSuite) &&
				Objects.equals(elifTests, anIf.elifTests) &&
				Objects.equals(elifSuites, anIf.elifSuites) &&
				Objects.equals(elseSuite, anIf.elseSuite);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ifTest, ifSuite, elifTests, elifSuites, elseSuite);
	}
}
