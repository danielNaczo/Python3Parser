package io.github.danielnaczo.python3parser.model.mods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;
import io.github.danielnaczo.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import io.github.danielnaczo.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

//in grammar: file_input
public class Module extends Mod {
	private List<Statement> statements;
	
	public Module() {
		statements = new ArrayList<>();
	}
	
	public Module(List<Statement> body) {
		this.statements = body;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	
	public Statement addStatement(Statement statement) {
		statements.add(statement);
		return statement;
	}
	
	public void addStatements(List<Statement> statements) {
		for (Statement statement : statements) {
			statements.add(statement);
		}
	}
	
	public ClassDef addClass(ClassDef clazz) {
		this.addStatement(clazz);
		return clazz;
	}
	
	public FunctionDef addFunction(FunctionDef function) {
		this.addStatement(function);
		return function;
	}
	
	public List<ClassDef> getClassDefs() {
		List<ClassDef> classes = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement instanceof ClassDef) classes.add((ClassDef) statement);
		}
		return classes;
	}
	
	public List<FunctionDef> getFunctionDefs() {
		List<FunctionDef> functions = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement instanceof FunctionDef) functions.add((FunctionDef) statement);
		}
		return functions;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitModule(this, param);
	}
	
	public void printInConsole() {
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String prettyprintedString = modulePrettyPrintVisitor.visitModule(this, new IndentationPrettyPrint(0));
        System.out.println(prettyprintedString);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Module module = (Module) o;
		return Objects.equals(statements, module.statements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statements);
	}
}
