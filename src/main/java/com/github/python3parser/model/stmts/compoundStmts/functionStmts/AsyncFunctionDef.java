package com.github.python3parser.model.stmts.compoundStmts.functionStmts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.model.stmts.compoundStmts.ClassDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;


// e.g.:

// @decorator
// async def FunctionName(param1, param2, ...) -> returns :
//     bodyElement1;
//     bodyElement2;
public class AsyncFunctionDef extends Statement {
	
	Identifier name;
	Optional<Parameters> parameters;
	Statement body; //differs from original AST grammar
	List<Decorator> decoratorList;
	Optional<Expression> returns;
	
	public AsyncFunctionDef(String name) {
		this(new Identifier(name), null, null, null, null);
	}
	
	public AsyncFunctionDef(String name, Parameters parameters) {
		this(new Identifier(name), parameters, null, null, null);
	}
	
	public AsyncFunctionDef(String name, Statement body) {
		this(new Identifier(name), null, body, null, null);
	}
	
	public AsyncFunctionDef(String name, Parameters parameters, Statement body) {
		this(new Identifier(name), parameters, body, null, null);
	}
	
	public AsyncFunctionDef(String name, Parameters parameters, Statement body, Expression returns) {
		this(new Identifier(name), parameters, body, null, returns);
	}
	
	public AsyncFunctionDef(String name, Parameters parameters, Statement body, List<Decorator> decaratorList, Expression returns) {
		this(new Identifier(name), parameters, body, decaratorList, returns);
	}
	
	public AsyncFunctionDef(Identifier name, Parameters parameters, Statement body, List<Decorator> decaratorList, Expression returns) {
		this.name = name;
		this.parameters = Optional.ofNullable(parameters);
		this.body = body;
		this.decoratorList = decaratorList;
		this.returns = Optional.ofNullable(returns);
		setParentToBody();
	}

	public Identifier getName() {
		return name;
	}

	public Optional <Parameters> getParameters() {
		return parameters;
	}

	public Statement getBody() {
		return body;
	}

	public List<Decorator> getDecoratorList() {
		return decoratorList;
	}

	public Optional<Expression> getReturns() {
		return returns;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public void setParameters(Optional<Parameters> parameters) {
		this.parameters = parameters;
	}

	public void setBody(Statement body) {
		this.body = body;
		setParentToBody();
	}

	public void setDecoratorList(List<Decorator> decoratorList) {
		this.decoratorList = decoratorList;
	}

	public void setReturns(Optional<Expression> returns) {
		this.returns = returns;
	}
	
	public Parameter addParameter(Parameter param) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		Parameters parameters = this.parameters.get();
		if (parameters.getParams() == null) parameters.setParams(new ArrayList<>());
		parameters.getParams().add(param);
		return param;
	}
	
	public DefaultParameter addDefaultParameter(DefaultParameter defaultParam) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		Parameters parameters = this.parameters.get();
		if (parameters.getDefaultParams() == null) parameters.setDefaultParams(new ArrayList<>());
		parameters.getDefaultParams().add(defaultParam);
		return defaultParam;
	}
	
	public Parameter setVarParameter(Parameter varParam) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		this.parameters.get().setVarParam(Optional.ofNullable(varParam));
		return varParam;
	}
	
	public Parameter addKeyWordParameter(Parameter param) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		Parameters parameters = this.parameters.get();
		if (parameters.getKwonlyParams() == null) parameters.setKwonlyParams(new ArrayList<>());
		parameters.getKwonlyParams().add(param);
		return param;
	}
	
	public DefaultParameter addKeyWordDefaultParameter(DefaultParameter defaultParam) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		Parameters parameters = this.parameters.get();
		if (parameters.getKwDefaultParams() == null) parameters.setKwDefaultParams(new ArrayList<>());
		parameters.getKwDefaultParams().add(defaultParam);
		return defaultParam;
	}
	
	public Parameter setVarDefaultParameter(Parameter varKwParam) {
		if (!this.parameters.isPresent()) this.parameters = Optional.ofNullable(new Parameters());
		this.parameters.get().setKwParam(Optional.ofNullable(varKwParam));
		return varKwParam;
	}
	
	public FunctionDef addFunction(FunctionDef function) {
		this.addStatement(function);
		return function;
	}
	
	public ClassDef addFunction(ClassDef clazz) {
		this.addStatement(clazz);
		return clazz;
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
	
	public Decorator addDecorator(Decorator decorator) {
		if (decoratorList == null) decoratorList = new ArrayList<>();
		this.decoratorList.add(decorator);
		return decorator;
	}
	
	public List<Decorator> getDecorators() {
		return getDecoratorList();
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
		return visitor.visitAsyncFunctionDef(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AsyncFunctionDef that = (AsyncFunctionDef) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(parameters, that.parameters) &&
				Objects.equals(body, that.body) &&
				Objects.equals(decoratorList, that.decoratorList) &&
				Objects.equals(returns, that.returns);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, parameters, body, decoratorList, returns);
	}
	
	@Override
	public String toString() {
		return "AsyncFunctionDef '" + name.getName() + "'";
	}
}
