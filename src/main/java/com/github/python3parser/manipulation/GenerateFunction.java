package com.github.python3parser.manipulation;

import com.github.python3parser.model.expr.operators.binaryops.Add;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.flowStmts.Return;
import com.github.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import com.github.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class GenerateFunction {

	public static void main(String[] args) {
		//def sum(a, b):
		//    return a + b
		
		Module ast = new Module();
		FunctionDef sumFunction = new FunctionDef("sum");
		ast.addStatement(sumFunction);
		
		sumFunction.addParameter(new Parameter("a"));
		sumFunction.addParameter(new Parameter("b"));
		
		Return returnStatement = new Return(new Add("a", "b"));
		sumFunction.addStatement(returnStatement);
		
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String string = modulePrettyPrintVisitor.visitAST(ast, new IndentationPrettyPrint(0));
        System.out.println(string);
	}
}
