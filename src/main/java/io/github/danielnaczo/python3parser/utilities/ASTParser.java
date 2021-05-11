package io.github.danielnaczo.python3parser.utilities;

import static org.antlr.v4.runtime.CharStreams.fromFileName;
import static org.antlr.v4.runtime.CharStreams.fromString;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.mods.ExpressionMod;
import io.github.danielnaczo.python3parser.model.mods.Interactive;
import io.github.danielnaczo.python3parser.model.mods.Module;
import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import io.github.danielnaczo.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;

public class ASTParser {
	
	public static Module parseModuleWithCode(String python3Code) {
		CharStream charStream = fromString(python3Code);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Module module = (Module) moduleVisitor.visit(parser.file_input());
		return module;
	}
	
	public static ExpressionMod parseExpressionModWithCode(String python3Code) {
		CharStream charStream = fromString(python3Code);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		ExpressionMod expressionMod = (ExpressionMod) moduleVisitor.visit(parser.eval_input());
		return expressionMod;
	}
	
	public static Interactive parseInteractiveWithCode(String python3Code) {
		CharStream charStream = fromString(python3Code);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Interactive interactive = (Interactive) moduleVisitor.visit(parser.single_input());
		return interactive;
	}
	
	public static Module parseModuleWithFile(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Module module = (Module) moduleVisitor.visit(parser.file_input());
		return module;
	}
	
	public static ExpressionMod parseExpressionModWithFile(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		ExpressionMod expressionMod = (ExpressionMod) moduleVisitor.visit(parser.eval_input());
		return expressionMod;
	}
	
	public static Interactive parseInteractiveWithFile(String pathToPython3File) throws Exception {
		CharStream charStream = fromFileName(pathToPython3File);
		Python3Lexer lexer = new Python3Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ModuleVisitor moduleVisitor = new ModuleVisitor();
		Interactive interactive = (Interactive) moduleVisitor.visit(parser.single_input());
		return interactive;
	}
	
	public static Module cloneModule(Module ast) {
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String prettyprintedString = modulePrettyPrintVisitor.visitModule(ast, new IndentationPrettyPrint(0));
        CodePointCharStream charStream = fromString(prettyprintedString);
		Python3Lexer lexer = new Python3Lexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ModuleVisitor moduleVisitor = new ModuleVisitor();
        return (Module) moduleVisitor.visitFile_input(parser.file_input());
	}
	
	public static ExpressionMod cloneExpressionMod(ExpressionMod ast) {
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String prettyprintedString = modulePrettyPrintVisitor.visitExpressionMod(ast, new IndentationPrettyPrint(0));
        CodePointCharStream charStream = fromString(prettyprintedString);
		Python3Lexer lexer = new Python3Lexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ModuleVisitor moduleVisitor = new ModuleVisitor();
        return (ExpressionMod) moduleVisitor.visitEval_input(parser.eval_input());
	}
	
	public static Interactive cloneInteractive(Interactive ast) {
		ModulePrettyPrintVisitor modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        String prettyprintedString = modulePrettyPrintVisitor.visitInteractive(ast, new IndentationPrettyPrint(0));
        CodePointCharStream charStream = fromString(prettyprintedString);
		Python3Lexer lexer = new Python3Lexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ModuleVisitor moduleVisitor = new ModuleVisitor();
        return (Interactive) moduleVisitor.visitSingle_input(parser.single_input());
	}
}
