package io.github.danielnaczo.python3parser.visitors.prettyprint;

import java.util.List;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.mods.ExpressionMod;
import io.github.danielnaczo.python3parser.model.mods.Interactive;
import io.github.danielnaczo.python3parser.model.mods.Module;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.visitors.exceptions.Python3ParserException;

public class ModulePrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitAST(AST ast, IndentationPrettyPrint param) {
		return ast.accept(new ModulePrettyPrintVisitor(), param);
	}
	
	@Override
	public String visitModule(Module module, IndentationPrettyPrint param) {
		String string = new String();
		if (!(param instanceof IndentationPrettyPrint)) {
			int initialIndentationLevel = 0;
			param = new IndentationPrettyPrint(initialIndentationLevel);
		}
		
		List<Statement> statements = module.getStatements();
		if (statements == null || statements.isEmpty()) {
			throw new Python3ParserException("'Module' has no statements.");
		}
		
		for (int i = 0; i < statements.size(); i++) {
			Statement statement = statements.get(i);
			if (statement instanceof Expression) {
				string = string.concat(param.getIndentationString());
				string = string.concat(statement.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				string = string.concat("\n");
			} else {
				string = string.concat(statement.accept(new StatementPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		
		return string;
	}
	
	@Override
	public String visitExpressionMod(ExpressionMod expressionMod, IndentationPrettyPrint param) {
		String string= new String();
		if (!(param instanceof IndentationPrettyPrint)) {
			int initialIndentationLevel = 0;
			param = new IndentationPrettyPrint(initialIndentationLevel);
		}
		
		Expression body = expressionMod.getBody();
		
		string = string.concat(body.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		return string;
	}
	
	@Override
	public String visitInteractive(Interactive interactive, IndentationPrettyPrint param) {
		String string = new String();
		if (!(param instanceof IndentationPrettyPrint)) {
			int initialIndentationLevel = 0;
			param = new IndentationPrettyPrint(initialIndentationLevel);
		}
		
		Optional<Statement> body = interactive.getBody();
		
		if (body.isPresent()) {
			string = string.concat(body.get().accept(new StatementPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
}
