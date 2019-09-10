package com.github.python3parser.visitors.ast.parameters;

import com.github.python3parser.Python3Parser.DefaultTfpdefContext;
import com.github.python3parser.Python3Parser.VarDefaultVfpdefContext;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import com.github.python3parser.visitors.ast.ExpressionVisitor;
import com.github.python3parser.visitors.ast.GenericUnsupportedCSTVisitor;

public class DefaultParameterVisitor extends GenericUnsupportedCSTVisitor<DefaultParameter>{

	@Override
	public DefaultParameter visitDefaultTfpdef(DefaultTfpdefContext ctx) {
		Parameter arg = ctx.tfpdef().accept(new ParameterVisitor());
		Expression test = ctx.test().accept(new ExpressionVisitor());
		
		return new DefaultParameter(arg, test);
	}
	
	@Override
	public DefaultParameter visitVarDefaultVfpdef(VarDefaultVfpdefContext ctx) {
		Parameter arg = ctx.vfpdef().accept(new ParameterVisitor());
		Expression test = ctx.test().accept(new ExpressionVisitor());
		
		return new DefaultParameter(arg, test);
	}
}
