package com.github.python3parser.visitors.ast;

import com.github.python3parser.Python3Parser.Dotted_nameContext;

public class StringVisitor extends GenericUnsupportedCSTVisitor<String> {
	
	@Override
	public String visitDotted_name(Dotted_nameContext ctx) {
		String name = "";
		for (int i = 0; i < ctx.NAME().size(); i++) {
			if (i == 0) {
				name = name.concat(ctx.NAME(i).getText());
			}
			else {
				name = name.concat(".");
				name = name.concat(ctx.NAME(i).getText());
			}
		}
		return name;
	}
}
