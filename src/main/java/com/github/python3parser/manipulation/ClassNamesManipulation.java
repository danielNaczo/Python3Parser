package com.github.python3parser.manipulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.python3parser.visitors.ast.ModuleVisitor;
import com.github.python3parser.visitors.modifier.ModifierVisitor;
import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import com.github.python3parser.model.AST;
import com.github.python3parser.model.stmts.compoundStmts.ClassDef;

public class ClassNamesManipulation {
	
	public static void main(String[] args) throws IOException {

		CharStream charStream = CharStreams.fromFileName("trunk/examples/lib/_collections_abc.py");
		
        Python3Lexer lexer = new Python3Lexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        ModuleVisitor moduleVisitor = new ModuleVisitor();
        AST ast = moduleVisitor.visit(parser.file_input());
        
        List<String> classNames = new ArrayList<>();
        ClassNamesOutputModifier classNamesOutputModifier = new ClassNamesOutputModifier();
        classNamesOutputModifier.visitAST(ast, classNames);
        classNames.forEach(n -> System.out.println(n));
	}
	
	private static class ClassNamesOutputModifier extends ModifierVisitor<List<String>>{
		
		@Override
		public AST visitClassDef(ClassDef classDef, List<String> collector) {
			super.visitClassDef(classDef, collector);
			collector.add(classDef.getName().getName());
			return classDef;
		}
	}
}
