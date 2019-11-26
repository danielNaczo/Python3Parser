package com.github.python3parser.manipulation;

import com.github.python3parser.model.mods.Module;
import com.github.python3parser.utilities.ASTParser;

public class ASTParserManipulation {
	public static void main(String[] args) throws Exception {
		Module module = ASTParser.parseModuleWithFile("examples/lib/abc.py");
		module.printInConsole();
	}
}
