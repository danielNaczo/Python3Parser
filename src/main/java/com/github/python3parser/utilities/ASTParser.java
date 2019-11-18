package com.github.python3parser.utilities;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import com.github.python3parser.model.mods.ExpressionMod;
import com.github.python3parser.model.mods.Interactive;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.visitors.ast.ModuleVisitor;

public class ASTParser {
	public static Module parseModule(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Module module = (Module) moduleVisitor.visit(parser.file_input());
		return module;
	}
	
	public static ExpressionMod parseExpressionMod(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		ExpressionMod expressionMod = (ExpressionMod) moduleVisitor.visit(parser.eval_input());
		return expressionMod;
	}
	
	public static Interactive parseInteractive(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Interactive interactive = (Interactive) moduleVisitor.visit(parser.single_input());
		return interactive;
	}
}
