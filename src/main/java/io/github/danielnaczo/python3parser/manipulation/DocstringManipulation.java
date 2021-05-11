package io.github.danielnaczo.python3parser.manipulation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.modifier.ModifierVisitor;
import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.atoms.Atom;
import io.github.danielnaczo.python3parser.model.expr.atoms.Str;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.AsyncFunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts.Assign;

public class DocstringManipulation {

	public static void main(String[] args) throws IOException {
		CharStream charStream = CharStreams.fromFileName("trunk/examples/lib/_collections_abc.py");
		
        Python3Lexer lexer = new Python3Lexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        ModuleVisitor moduleVisitor = new ModuleVisitor();
        AST ast = moduleVisitor.visit(parser.file_input());
        
        HashMap<String, String> docstringCollector = new HashMap<>();
        DocstringModifier docstringModifier = new DocstringModifier();
        docstringModifier.visitAST(ast, docstringCollector);
        
        //the following iteration prints all Docstrings with the corresponding function or class
        for (Map.Entry<String, String> entry : docstringCollector.entrySet()) {
            String functionDefName = entry.getKey();
            String docstring = entry.getValue();
            
            System.out.println("FunctionDef " + functionDefName + ":");
            System.out.println(docstring);
            System.out.println();
        }
	}
	
	private static class DocstringModifier extends ModifierVisitor<HashMap<String, String>> {
		
		
		@Override
		public AST visitFunctionDef(FunctionDef functionDef, HashMap<String, String> docstringCollector) {
			super.visitFunctionDef(functionDef, docstringCollector);
			
			Body body = (Body) functionDef.getBody();
			List<Statement> statements = body.getStatements();
			
			if (statements != null && !statements.isEmpty()) {
				Statement firstStmt = statements.get(0);
				if (firstStmt instanceof Assign) {
					Assign firstAssign = (Assign) firstStmt;
					if (!firstAssign.getValue().isPresent()) {
						Atom atom = (Atom) firstAssign.getTargets().get(0);
						Expression atomElement = atom.getAtomElement();
						if (atomElement instanceof Str) {
							Str string = (Str) atomElement;
							String docstring = string.getS();
							docstringCollector.put(functionDef.getName().getName(), docstring);							
						}
					}
				}
			}
			
			return functionDef;
		}
		
		@Override
		public AST visitAsyncFunctionDef(AsyncFunctionDef asyncFunctionDef, HashMap<String, String> docstringCollector) {
			super.visitAsyncFunctionDef(asyncFunctionDef, docstringCollector);
			
			Body body = (Body) asyncFunctionDef.getBody();
			List<Statement> statements = body.getStatements();
			
			if (statements != null && !statements.isEmpty()) {
				Statement firstStmt = statements.get(0);
				if (firstStmt instanceof Assign) {
					Assign firstAssign = (Assign) firstStmt;
					if (!firstAssign.getValue().isPresent()) {
						Atom atom = (Atom) firstAssign.getTargets().get(0);
						Expression atomElement = atom.getAtomElement();
						if (atomElement instanceof Str) {
							Str string = (Str) atomElement;
							String docstring = string.getS();
							docstringCollector.put(asyncFunctionDef.getName().getName(), docstring);							
						}
					}
				}
			}
			
			return asyncFunctionDef;
		}
		
		@Override
		public AST visitClassDef(ClassDef classDef, HashMap<String, String> docstringCollector) {
			super.visitClassDef(classDef, docstringCollector);
			
			Body body = (Body) classDef.getBody();
			List<Statement> statements = body.getStatements();
			
			if (statements != null && !statements.isEmpty()) {
				Statement firstStmt = statements.get(0);
				if (firstStmt instanceof Assign) {
					Assign firstAssign = (Assign) firstStmt;
					if (!firstAssign.getValue().isPresent()) {
						Atom atom = (Atom) firstAssign.getTargets().get(0);
						Expression atomElement = atom.getAtomElement();
						if (atomElement instanceof Str) {
							Str string = (Str) atomElement;
							String docstring = string.getS();
							docstringCollector.put(classDef.getName().getName(), docstring);							
						}
					}
				}
			}
			
			return classDef;
		}
	}
}
