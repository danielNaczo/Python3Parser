package com.github.python3parser.utilities;

import static org.antlr.v4.runtime.CharStreams.fromString;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import com.github.python3parser.model.AST;
import com.github.python3parser.model.mods.ExpressionMod;
import com.github.python3parser.model.mods.Interactive;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.visitors.ast.ModuleVisitor;
import com.github.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import com.github.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class CloneAST {

	private static ModulePrettyPrintVisitor modulePrettyPrintVisitor;
	private static String prettyprintedString;
	private static CharStream charStream;
	private static Python3Lexer lexer;
	private static CommonTokenStream tokens;
	private static Python3Parser parser;
	private static ModuleVisitor moduleVisitor;

	public static Module cloneModule(Module ast) {
		modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        prettyprintedString = modulePrettyPrintVisitor.visitModule(ast, new IndentationPrettyPrint(0));
        charStream = fromString(prettyprintedString);
		lexer = new Python3Lexer(charStream);
        tokens = new CommonTokenStream(lexer);
        parser = new Python3Parser(tokens);
        moduleVisitor = new ModuleVisitor();
        return (Module) moduleVisitor.visitFile_input(parser.file_input());
	}
	
	public static ExpressionMod cloneExpressionMod(ExpressionMod ast) {
		modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        prettyprintedString = modulePrettyPrintVisitor.visitExpressionMod(ast, new IndentationPrettyPrint(0));
        charStream = fromString(prettyprintedString);
		lexer = new Python3Lexer(charStream);
        tokens = new CommonTokenStream(lexer);
        parser = new Python3Parser(tokens);
        moduleVisitor = new ModuleVisitor();
        return (ExpressionMod) moduleVisitor.visitEval_input(parser.eval_input());
	}
	
	public static Interactive cloneInteractive(Interactive ast) {
		modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        prettyprintedString = modulePrettyPrintVisitor.visitInteractive(ast, new IndentationPrettyPrint(0));
        charStream = fromString(prettyprintedString);
		lexer = new Python3Lexer(charStream);
        tokens = new CommonTokenStream(lexer);
        parser = new Python3Parser(tokens);
        moduleVisitor = new ModuleVisitor();
        return (Interactive) moduleVisitor.visitSingle_input(parser.single_input());
	}
}
