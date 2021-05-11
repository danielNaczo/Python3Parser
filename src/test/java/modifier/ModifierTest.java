package modifier;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import io.github.danielnaczo.python3parser.visitors.ast.ModuleVisitor;
import io.github.danielnaczo.python3parser.visitors.modifier.ModifierVisitor;
import com.github.python3parser.Python3Lexer;
import com.github.python3parser.Python3Parser;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.mods.Module;

public class ModifierTest {

	//attributes for parsing the file;
	private CharStream charStream;
	private Python3Lexer lexer;
	private CommonTokenStream tokens;
	private Python3Parser parser;
	private ModuleVisitor moduleVisitor;
	private AST ast;
	
	private AST modifierAST;

	private String eclipseTestPrefix = "examples/";
	private String intelliJTestPrefix = "../examples/"; //TODO find a better solution for not used attributes
	
	@Before
	public void setUp() throws Exception {
		//"../examples/ImportExample.py" in Intellij
		charStream = CharStreams.fromFileName(eclipseTestPrefix + "lib/_collections_abc.py");
		
        lexer = new Python3Lexer(charStream);

        tokens = new CommonTokenStream(lexer);

        parser = new Python3Parser(tokens);

        moduleVisitor = new ModuleVisitor();
        ast = moduleVisitor.visit(parser.file_input());
        
        // going through the ModifierVisitor to check if ASTs are equal
        ModifierVisitor<Void> modifierVisitor = new ModifierVisitor<>();
        modifierAST = modifierVisitor.visitModule((Module) ast, null);
	}

	@Test
	public void test() {
		assertTrue("ASTs not equal.", ast.equals(modifierAST));
	}
}
