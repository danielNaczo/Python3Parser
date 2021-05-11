package io.github.danielnaczo.python3parser.manipulation;

import io.github.danielnaczo.python3parser.model.mods.Module;
import io.github.danielnaczo.python3parser.utilities.ASTParser;

public class ASTParserManipulation {
	public static void main(String[] args) throws Exception {
		Module module = ASTParser.parseModuleWithFile("examples/lib/abc.py");
		module.printInConsole();
	}
}
