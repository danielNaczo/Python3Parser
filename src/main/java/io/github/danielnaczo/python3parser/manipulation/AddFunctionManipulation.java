package io.github.danielnaczo.python3parser.manipulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.modifier.ModifierVisitor;
import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.atoms.Str;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.flowStmts.Return;
import io.github.danielnaczo.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import io.github.danielnaczo.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class AddFunctionManipulation {

	public static void main(String[] args) throws IOException {
		CharStream charStream = CharStreams.fromFileName("trunk/examples/Elevator.py");
		
        Python3Lexer lexer = new Python3Lexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        ModuleVisitor moduleVisitor = new ModuleVisitor();
        AST ast = moduleVisitor.visit(parser.file_input());
        
        //creating a new function:   def getFloor(): return floor
        List<Statement> statements = new ArrayList<>();
        statements.add(new Return(new Str("floor")));
        Body body = new Body(statements);
        
        FunctionDef newFunctionDef = new FunctionDef(new Identifier("getFloor"), null,
        		body, null, null);
        
        AddFunctionModifier addFunctionModifier = new AddFunctionModifier();
        AST modifiedAST = addFunctionModifier.visitAST(ast, newFunctionDef);
        
        //pretty print the modified AST
        ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String string = modulePrettyPrintVisitor.visitAST(modifiedAST, new IndentationPrettyPrint(0));
        System.out.println(string);
	}
	
	private static class AddFunctionModifier extends ModifierVisitor<FunctionDef>{
		
		@Override
		public AST visitClassDef(ClassDef classDef, FunctionDef functionDef) {
			super.visitClassDef(classDef, functionDef);
			Body body = (Body) classDef.getBody();
			List<Statement> statements = body.getStatements();
			statements.add(functionDef);
			return classDef;
		}
	}
}
