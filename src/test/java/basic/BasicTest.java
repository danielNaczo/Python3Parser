package basic;

import static org.antlr.v4.runtime.CharStreams.fromFileName;
import static org.junit.Assert.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import com.github.python3parser.visitors.ast.ModuleVisitor;
import com.github.python3parser.visitors.prettyprint.IndentationPrettyPrint;
import com.github.python3parser.visitors.prettyprint.ModulePrettyPrintVisitor;
import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import com.github.python3parser.model.AST;
import com.github.python3parser.model.mods.Module;

public abstract class BasicTest {

	//attributes for parsing the file;
	protected CharStream charStream;
	private Python3Lexer lexer;
	private CommonTokenStream tokens;
	private Python3Parser parser;
	private ModuleVisitor moduleVisitor;
	private AST ast;
	
	//attributes for prettyprinting
	private ModulePrettyPrintVisitor modulePrettyPrintVisitor;
	private String prettyprintedString;
	protected boolean showInConsole = false;
	
	//attribute for parsing the prettyprinted String
	private CharStream prettyPrintedCharStream;
	private Python3Lexer prettyPrintedLexer;
	private CommonTokenStream prettyPrintedTokens;
	private Python3Parser prettyPtrintedParser;
	private ModuleVisitor prettyPrinterModuleVisitor;
	private AST prettyPrintedAST;
	
	//prefixes for test files
	private String eclipseTestPrefix = "examples/";
	private String intelliJTestPrefix = "../examples/"; //TODO find a better solution for not used attributes
	
	//name of testfile
	protected String testFileName;
	
	@Before
	public void setUp() throws Exception {
		setTestFileName();
		charStream = fromFileName(eclipseTestPrefix + testFileName);
		
		lexer = new Python3Lexer(charStream);
        tokens = new CommonTokenStream(lexer);
        parser = new Python3Parser(tokens);
        moduleVisitor = new ModuleVisitor();
        ast = moduleVisitor.visit(parser.file_input());
        
        //PrettyPrinting
        modulePrettyPrintVisitor = new ModulePrettyPrintVisitor();
        prettyprintedString = modulePrettyPrintVisitor.visitModule((Module) ast, new IndentationPrettyPrint(0));
        
        printInConsole();
        if (showInConsole) {
            System.out.println(prettyprintedString);
            System.out.println();
        }
        
        prettyPrintedCharStream = CharStreams.fromString(prettyprintedString);
        prettyPrintedLexer = new Python3Lexer(prettyPrintedCharStream);
        prettyPrintedTokens = new CommonTokenStream(prettyPrintedLexer);
        prettyPtrintedParser = new Python3Parser(prettyPrintedTokens);
        prettyPrinterModuleVisitor = new ModuleVisitor();
        prettyPrintedAST = prettyPrinterModuleVisitor.visit(prettyPtrintedParser.file_input());
	}
	
	public abstract void setTestFileName();
	public abstract void printInConsole();

	@Test
	public void test() {
		assertTrue("ASTs not equal.", ast.equals(prettyPrintedAST));
	}
}
