package io.github.danielnaczo.python3parser.visitors.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.github.python3parser.Python3Parser.Dotted_as_nameContext;
import com.github.python3parser.Python3Parser.Dotted_as_namesContext;
import com.github.python3parser.Python3Parser.Dotted_nameContext;
import com.github.python3parser.Python3Parser.Import_as_nameContext;
import com.github.python3parser.Python3Parser.Import_as_namesContext;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.stmts.importStmts.Alias;

public class AliasVisitor extends GenericUnsupportedCSTVisitor<List<Alias>>{

	//this method includes these visitor-methods: dotted_as_name, dotted_name
	@Override
	public List<Alias> visitDotted_as_names(Dotted_as_namesContext ctx) {
		
		List<Dotted_as_nameContext> dottedAsNameList = ctx.dotted_as_name();
		List<Alias> aliases = new ArrayList<>();
		
		for (int i = 0; i < dottedAsNameList.size(); i++) {
			Dotted_as_nameContext dottedAsName = dottedAsNameList.get(i);
			
			Dotted_nameContext dottedName = dottedAsName.dotted_name();
			List<TerminalNode> terminals = dottedName.NAME();
			
			String name = "";
			for (int j = 0; j < terminals.size(); j++) {
				TerminalNode terminal = terminals.get(j);
				if (j == 0) {
					name = name.concat(terminal.getText());
				}
				else {
					name = name.concat(".");
					name = name.concat(terminal.getText());
				}
			}
			
			String asName;
			Alias alias;
			if (dottedAsName.NAME() != null) {
				asName = dottedAsName.NAME().getText();
				alias = new Alias(new Identifier(name), new Identifier(asName));
			}
			else {
				alias = new Alias(new Identifier(name), null);
			}
			
			aliases.add(alias);
		}
		
		return aliases;
		
	}
	
	@Override
	public List<Alias> visitImport_as_names(Import_as_namesContext ctx) {
		List<Import_as_nameContext> importAsNamesContext = ctx.import_as_name();
		List<Alias> aliases = new ArrayList<>();
		
		for (int i = 0; i < importAsNamesContext.size(); i++) {
			Import_as_nameContext importAsNameContext = importAsNamesContext.get(i);
			Alias alias;
			
			String identifier;
			identifier = importAsNameContext.NAME(0).getText();
			
			String asName = null;
			if (importAsNameContext.NAME().size() == 2) {
				asName = importAsNameContext.NAME(1).getText();
				alias = new Alias(new Identifier(identifier), new Identifier(asName));
			}
			else {
				alias = new Alias(new Identifier(identifier), null);
			}
			aliases.add(alias);
		}
		
		return aliases;
	}
	
}
