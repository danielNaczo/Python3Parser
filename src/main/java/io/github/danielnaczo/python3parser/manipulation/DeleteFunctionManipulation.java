package io.github.danielnaczo.python3parser.manipulation;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.github.danielnaczo.python3parser.Python3Lexer;
import io.github.danielnaczo.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.modifier.ModifierVisitor;
import io.github.danielnaczo.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import io.github.danielnaczo.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class DeleteFunctionManipulation {

	public static void main(String[] args) throws IOException {
		CharStream charStream = CharStreams.fromFileName("trunk/examples/Elevator.py");
		
        Python3Lexer lexer = new Python3Lexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        ModuleVisitor moduleVisitor = new ModuleVisitor();
        AST ast = moduleVisitor.visit(parser.file_input());
        
        //delete specific Function in a class
        String removeFunction = "down";
        DeleteFunctionModifier deleteFunctionModifier = new DeleteFunctionModifier();
        AST modifiedAST = deleteFunctionModifier.visitAST(ast, removeFunction);
        
        //prettyprint the modified AST
        ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String string = modulePrettyPrintVisitor.visitAST(modifiedAST, new IndentationPrettyPrint(0));
        System.out.println(string);
	}
	
	private static class DeleteFunctionModifier extends ModifierVisitor<String>{
		
		@Override
		public AST visitClassDef(ClassDef classDef, String deleteFunction) {
			super.visitClassDef(classDef, deleteFunction);
			Body body = (Body) classDef.getBody();
			
			List<Statement> statements = body.getStatements();
			for (int i = 0; i < statements.size(); i++) {
				Statement currentStatement = statements.get(i);
				if (currentStatement instanceof FunctionDef) {
					FunctionDef functionStatement = (FunctionDef) currentStatement;
					if (functionStatement.getName().getName().equals(deleteFunction)) {
						statements.remove(functionStatement);
					}
				}
			}
			
			return classDef;
		}
	}
}
