package io.github.danielnaczo.python3parser.manipulation;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.github.danielnaczo.python3parser.Python3Lexer;
import io.github.danielnaczo.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.modifier.ModifierVisitor;
import io.github.danielnaczo.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import io.github.danielnaczo.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class RenameFunctionsManipulation {

	public static void main(String[] args) throws IOException {
		CharStream charStream = CharStreams.fromFileName("trunk/examples/Elevator.py");
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		AST ast = moduleVisitor.visit(parser.file_input());
		
		//modify specific functions with a prefix
		String functionPrefix = "go";
		RenameFunctionModifier renameFunctionModifier = new RenameFunctionModifier();
		AST modifiedAST = renameFunctionModifier.visitAST(ast, functionPrefix);
		
		// prettyprint the modified AST
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
		String string = modulePrettyPrintVisitor.visitAST(modifiedAST, new IndentationPrettyPrint(0));
		System.out.println(string);
	}
	
	private static class RenameFunctionModifier extends ModifierVisitor<String> {
		
		@Override
		public AST visitFunctionDef(FunctionDef functionDef, String functionPrefix) {
			super.visitFunctionDef(functionDef, functionPrefix);
			if (functionDef.getName().getName().equals("up")
					|| functionDef.getName().getName().equals("down")) {
				String currentFunctionName = functionDef.getName().getName();
				String newFunctionName = functionPrefix + Character.toUpperCase(
						currentFunctionName.charAt(0)) + currentFunctionName.substring(1);
				Identifier newIdentifier = new Identifier(newFunctionName);
				functionDef.setName(newIdentifier);
			}
			return functionDef;
		}
	}
}
