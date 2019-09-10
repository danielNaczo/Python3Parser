package com.github.python3parser.visitors.prettyprint;

import java.util.Optional;

import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.stmts.importStmts.Alias;

public class AliasPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitAlias(Alias alias, IndentationPrettyPrint param) {
		String string = new String();
		String name = alias.getName().getName();
		Optional<Identifier> asname = alias.getAsName();
		
		string = string.concat(name);
		
		if (asname.isPresent()) {
			string = string.concat(" as " + asname.get().getName());
		}
		
		return string;
	}
}
