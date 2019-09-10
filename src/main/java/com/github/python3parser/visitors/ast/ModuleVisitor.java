package com.github.python3parser.visitors.ast;


import java.util.ArrayList;
import java.util.List;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.mods.ExpressionMod;
import com.github.python3parser.model.mods.Interactive;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.Python3Parser.*;

public class ModuleVisitor extends GenericUnsupportedCSTVisitor<AST>{

	//Module
	@Override
	public AST visitFile_input(File_inputContext ctx) {		
		
		if (ctx.stmt() != null && !ctx.stmt().isEmpty()) {
			List<Statement> body = new ArrayList<>();
			for (int i = 0; i < ctx.stmt().size(); i++) {
				body.add(ctx.stmt(i).accept(new StatementVisitor()));
			}
			
			return new Module(body);
		}
		return new Module(null);
	}
	
	//Interactive
	@Override
	public AST visitSingle_input(Single_inputContext ctx) {
		if (ctx.simple_stmt() != null) {
			Statement body = ctx.simple_stmt().accept(new StatementVisitor());
			return new Interactive(body);
		}
		if (ctx.compound_stmt() != null) {
			Statement body = ctx.compound_stmt().accept(new StatementVisitor());
			return new Interactive(body);
		} else {
			return new Interactive(null);
		}
	}
	
	//ExpressionMod
	@Override
	public AST visitEval_input(Eval_inputContext ctx) {
		Expression body = ctx.testlist().accept(new ExpressionVisitor());
		return new ExpressionMod(body);
	}
}
