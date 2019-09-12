package com.github.python3parser.manipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.expr.atoms.Num;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Arguments;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.stmts.compoundStmts.ClassDef;
import com.github.python3parser.model.stmts.compoundStmts.forStmts.For;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.flowStmts.Return;

public class AddClassManipulation {
	public static void main(String[] args) {
		createClass();
		//createForStmt();
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
		module.printInConsole();
	}
}
