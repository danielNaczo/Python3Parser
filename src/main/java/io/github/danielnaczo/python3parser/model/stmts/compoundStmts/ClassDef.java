package io.github.danielnaczo.python3parser.model.stmts.compoundStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.:

// @dec1
// @dec2
// class foo(base1, base2, metaclass=meta):
//     pass
public class ClassDef extends Statement {

	Identifier name;
	Optional<Expression> arguments;
	Statement body;
	List<Decorator> decoratorList;
	
	public ClassDef(String name) {
		this(name, null, null, new ArrayList<>());
	}
	
	public ClassDef(String name, Expression arguments) {
		this(name, arguments, null, new ArrayList<>());
	}
	
	public ClassDef(String name, Statement body) {
		this(name, null, body , new ArrayList<>());
	}
	
	public ClassDef(String name, Expression arguments, Statement body) {
		this(name, arguments, body, new ArrayList<>());
	}
	
	public ClassDef(String name, Statement body, List<Decorator> decoratorList) {
		this(name, null, body, decoratorList);
	}
	
	public ClassDef(String name, Expression arguments, Statement body, List<Decorator> decoratorList) {
		this(new Identifier(name), arguments, body, decoratorList);
	}
	
	public ClassDef(Identifier name, Expression arguments, Statement body, List<Decorator> decoratorList) {
		this.name = name;
		this.arguments = Optional.ofNullable(arguments);
		this.body = body;
		setParentToBody();
		this.decoratorList = decoratorList;
	}

	public Identifier getName() {
		return name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public Optional<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(Optional<Expression> arguments) {
		this.arguments = arguments;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}

	public List<Decorator> getDecoratorList() {
		return decoratorList;
	}

	public void setDecoratorList(List<Decorator> decoratorList) {
		this.decoratorList = decoratorList;
	}
	
	public ClassDef addClass(ClassDef clazz) {
		this.addStatement(clazz);
		return clazz;
	}
	
	public FunctionDef addFunction(FunctionDef function) {
		this.addStatement(function);
		return function;
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
	
	public List<ClassDef> getClassDefs() {
		Body body = transformStmtToBody();
		List<Statement> statements = body.getStatements();
		List<ClassDef> classes = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement instanceof ClassDef) classes.add((ClassDef) statement);
		}
		return classes;
	}
	
	public List<FunctionDef> getFunctionDefs() {
		Body body = transformStmtToBody();
		List<Statement> statements = body.getStatements();
		List<FunctionDef> functions = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement instanceof FunctionDef) functions.add((FunctionDef) statement);
		}
		return functions;
	}
	
	public Decorator addDecorator(Decorator decorator) {
		if (decoratorList == null) decoratorList = new ArrayList<>();
		this.decoratorList.add(decorator);
		return decorator;
	}
	
	public List<Decorator> getDecorators() {
		return getDecoratorList();
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

	private void setParentToBody() {
		if (body instanceof Body) ((Body) body).setParentStmt(this);
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitClassDef(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ClassDef classDef = (ClassDef) o;
		return Objects.equals(name, classDef.name) &&
				Objects.equals(arguments, classDef.arguments) &&
				Objects.equals(body, classDef.body) &&
				Objects.equals(decoratorList, classDef.decoratorList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, arguments, body, decoratorList);
	}
	
	@Override
	public String toString() {
		return "ClassDef '" + name.getName() + "'";
	}
}
