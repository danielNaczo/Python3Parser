package com.github.python3parser.manipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.expr.atoms.Num;
import com.github.python3parser.model.expr.atoms.True;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Arguments;
import com.github.python3parser.model.expr.operators.binaryops.Add;
import com.github.python3parser.model.expr.operators.unaryops.Yield;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
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
import com.github.python3parser.model.stmts.importStmts.Alias;
import com.github.python3parser.model.stmts.importStmts.Import;
import com.github.python3parser.model.stmts.importStmts.ImportFrom;
import com.github.python3parser.model.stmts.smallStmts.Global;
import com.github.python3parser.model.stmts.smallStmts.Nonlocal;
import com.github.python3parser.model.stmts.smallStmts.Pass;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.AnnAssign;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.Assign;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.AugAssign;

public class AddClassManipulation {
	public static void main(String[] args) {
		//createFunction();
		//createClass();
		//createForStmt();
		//createTryStmt();
		//createWithStmt();
		//createWhileStmt();
		//createIfStmt();
		//createImportStmt();
		//createAssignStmts();
		generateCode();
	}

	private static void createFunction() {
		Module module = new Module();
		FunctionDef functionDef = module.addFunction(new FunctionDef("add"));
		functionDef.addParameter(new Parameter("a"));
		functionDef.addParameter(new Parameter("b"));
		functionDef.addStatement(new Return(new Add("a", "b")));
		module.printInConsole();
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
		functionFooo.addStatement(new Return());
		
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

	private static void createImportStmt() {
		Module module = new Module();
		
		Import importStmt = (Import) module.addStatement(new Import());
		importStmt.addAlias(new Alias("mathplotbib"));
		
		ImportFrom importStmt2 = (ImportFrom) module.addStatement(new ImportFrom("bmp"));
		importStmt2.setLevel(2);
		importStmt2.addAlias(new Alias("someBib", "myBib"));
		
		Global global = (Global) module.addStatement(new Global());
		global.addIdentiferAsString("foo");
		global.addIdentifier(new Identifier("boo"));
		
		Nonlocal nonlocal = (Nonlocal) module.addStatement(new Nonlocal());
		nonlocal.addIdentifier(new Identifier("fooNon"));
		nonlocal.addIdentiferAsString("booNon");
		
		module.addStatement(new Pass());
		
		module.printInConsole();
	}

	private static void createAssignStmts() {
		Module module = new Module();
		
		Assign assign = (Assign) module.addStatement(new Assign());
		assign.addTargetAsString("target1");
		assign.addTargetAsString("target2");
		assign.setValue(Optional.ofNullable(new Name("value")));
		
		AnnAssign annAssign = (AnnAssign) module.addStatement(new AnnAssign("target", "annotation"));
		annAssign.setValue(Optional.ofNullable(new Name("value")));
		
		module.addStatement(new AugAssign("target", new Add(), "value"));
		
		module.printInConsole();
	}

	private static void generateCode() {
		Module module = new Module();
		ClassDef clazz = module.addClass(new ClassDef("Clazz"));
		clazz.addStatement(new Name("attribute1"));
		clazz.addStatement(new Name("attribute2"));
		FunctionDef function = clazz.addFunction(new FunctionDef("function"));
		Parameter param1 = function.addParameter(new Parameter("a"));
		Parameter param2 = function.addParameter(new Parameter("b"));
		Return returnStmt = (Return) function.addStatement(new Return());
		returnStmt.setValue(Optional.ofNullable(new Add(param1.getParameterNameAsString(), param2.getParameterNameAsString())));
		module.printInConsole();
	}
}
