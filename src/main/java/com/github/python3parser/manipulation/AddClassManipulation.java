package com.github.python3parser.manipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.expr.atoms.Num;
import com.github.python3parser.model.expr.atoms.True;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Arguments;
import com.github.python3parser.model.expr.operators.unaryops.Yield;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.compoundStmts.ClassDef;
import com.github.python3parser.model.stmts.compoundStmts.If;
import com.github.python3parser.model.stmts.compoundStmts.While;
import com.github.python3parser.model.stmts.compoundStmts.forStmts.For;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.ExceptHandler;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.Try;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.With;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.WithItem;
import com.github.python3parser.model.stmts.flowStmts.Break;
import com.github.python3parser.model.stmts.flowStmts.Return;

public class AddClassManipulation {
	public static void main(String[] args) {
		//createClass();
		//createForStmt();
		//createTryStmt();
		//createWithStmt();
		//createWhileStmt();
		createIfStmt();
	}

	private static void createClass() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazzzzz"));
		clazz.addStatement(new Name("attribute1"));
		clazz.addStatement(new Name("attribute2"));
		clazz.addStatement(new Name("attribute3"));
		
		ClassDef clazz2 = clazz.addClass(new ClassDef("Clazz2"));
		clazz2.addStatement(new Name("attr1"));
		clazz2.addStatement(new Name("attr2"));
		FunctionDef functionReturnZero = clazz2.addFunction(new FunctionDef("returnZero"));
		functionReturnZero.addStatement(new Return(new Num(0)));
		
		List<Expression> args = new ArrayList<>();
		args.add(new Name("arg"));
		clazz.setArguments(Optional.of(new Arguments(args, null, null, null)));
		
		FunctionDef functionFooo = clazz.addFunction(new FunctionDef("fooo"));
		functionFooo.addParameter(new Parameter("a"));
		functionFooo.addDefaultParameter(new DefaultParameter("b", new Num(1)));
		functionFooo.setVarParameter(new Parameter("c"));
		functionFooo.addStatement(new Return(null));
		
		module.printInConsole();
	}
	
	private static void createForStmt() {
		Module module = new Module();
		FunctionDef function = module.addFunction(new FunctionDef("createFor"));
		
		For forStmt = (For) function.addStatement(new For(new Name("element")));
		forStmt.setIter(new Name("list"));
		forStmt.addStatement(new Name("hello"));
		forStmt.addStatement(new Name("world"));
		forStmt.setOrElse(Optional.ofNullable(new Name("orElse")));
		function.addStatement(new Name("next"));
		
		module.printInConsole();
	}

	private static void createTryStmt() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazzzzz"));
		Try tryStmt = (Try) clazz.addStatement(new Try());
		tryStmt.addStatement(new Name("Hello"));
		tryStmt.addStatement(new Name("World"));
		ExceptHandler handler = tryStmt.addHandlerWithBody(new ExceptHandler(), new Name("body1"));
		tryStmt.addStatementToHandler(handler, new Name("body2"));
		tryStmt.setOrElse(Optional.ofNullable(new Name("else")));
		tryStmt.setFinalBody(Optional.ofNullable(new Name("finalBody")));
		
		module.printInConsole();
	}

	private static void createWithStmt() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazzzzz"));
		
		List<WithItem> itemList = new ArrayList<>();
		itemList.add(new WithItem("firstItem"));
		With withstmt = (With) clazz.addStatement(new With(itemList));
		withstmt.addWithItem(new WithItem("secondItem", new Name("item")));
		withstmt.addStatement(new Name("body"));
		
		module.printInConsole();
	}

	private static void createWhileStmt() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazzzzz"));
		
		While whileStmt = (While) clazz.addStatement(new While("test"));
		whileStmt.addStatement(new Name("body"));
		Body bodyOrElse = new Body();
		bodyOrElse.addStatement(new Name("orElseBody1"));
		bodyOrElse.addStatement(new Name("orElseBody2"));
		whileStmt.setOrElse(Optional.ofNullable(bodyOrElse));
		module.printInConsole();
	}

	private static void createIfStmt() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazzzzz"));
		
		If ifStmt = (If) clazz.addStatement(new If(new True()));
		ifStmt.addStatementToBody(new Name("body1.1"));
		ifStmt.addStatementToBody(new Name("body1.2"));
		
		Expression elifTest = ifStmt.addElifTestWithBody(new Name("testElif"), new Name("elifBody1.1"));
		ifStmt.addStatementToElif(elifTest, new Name("elifBody1.2"));
		ifStmt.addStatementToElif(elifTest, new Yield(null));
		
		Expression elifTest2 = ifStmt.addElifTestWithBody(new Name("testElif2"), new Name("elifBody2.1"));
		ifStmt.addStatementToElif(elifTest2, new Name("elifBody2.2"));
		
		ifStmt.setElseBody(Optional.ofNullable(new Name("elseBody")));
		
		clazz.addStatement(new Name("variable"));
		clazz.addStatement(new Yield(null));
		
		module.printInConsole();
	}
}
