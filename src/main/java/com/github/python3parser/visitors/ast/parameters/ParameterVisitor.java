package com.github.python3parser.visitors.ast.parameters;

import com.github.python3parser.Python3Parser.KwlistArgs1Context;
import com.github.python3parser.Python3Parser.KwlistArgs2Context;
import com.github.python3parser.Python3Parser.PositionalListContext;
import com.github.python3parser.Python3Parser.TfpdefContext;
import com.github.python3parser.Python3Parser.VarKwlistArgs1Context;
import com.github.python3parser.Python3Parser.VarKwlistArgs2Context;
import com.github.python3parser.Python3Parser.VarPositionalListContext;
import com.github.python3parser.Python3Parser.VfpdefContext;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.visitors.ast.ExpressionVisitor;
import com.github.python3parser.visitors.ast.GenericUnsupportedCSTVisitor;

public class ParameterVisitor extends GenericUnsupportedCSTVisitor<Parameter>{

	@Override
	public Parameter visitTfpdef(TfpdefContext ctx) {
		Identifier arg = new Identifier(ctx.NAME().getText());
		Expression annotation = null;
		
		if (ctx.test() != null) {
			annotation = ctx.test().accept(new ExpressionVisitor());
		}
		
		return new Parameter(arg, annotation);
	}
	
	@Override
	public Parameter visitPositionalList(PositionalListContext ctx) {
		if (ctx.tfpdef() != null) {
			return ctx.tfpdef().accept(new ParameterVisitor());
		}
		
		return new Parameter(new Identifier("*"), null);
	}
	
	@Override
	public Parameter visitKwlistArgs1(KwlistArgs1Context ctx) {
		if (ctx.tfpdef() != null) {
			return ctx.tfpdef().accept(new ParameterVisitor());
		}
		return null;
	}
	
	@Override
	public Parameter visitKwlistArgs2(KwlistArgs2Context ctx) {
		if (ctx.tfpdef() != null) {
			return ctx.tfpdef().accept(new ParameterVisitor());
		}
		return null;
	}
	
	@Override
	public Parameter visitVfpdef(VfpdefContext ctx) {
		Identifier arg = new Identifier(ctx.NAME().getText());
		
		return new Parameter(arg, null);
	}
	
	@Override
	public Parameter visitVarPositionalList(VarPositionalListContext ctx) {
		if (ctx.vfpdef() != null) {
			return ctx.vfpdef().accept(new ParameterVisitor());
		}
		
		return new Parameter(new Identifier("*"), null);
	}
	
	@Override
	public Parameter visitVarKwlistArgs1(VarKwlistArgs1Context ctx) {
		if (ctx.vfpdef() != null) {
			return ctx.vfpdef().accept(new ParameterVisitor());
		}
		return null;
	}
	
	@Override
	public Parameter visitVarKwlistArgs2(VarKwlistArgs2Context ctx) {
		if (ctx.vfpdef() != null) {
			return ctx.vfpdef().accept(new ParameterVisitor());
		}
		return null;
	}
}
