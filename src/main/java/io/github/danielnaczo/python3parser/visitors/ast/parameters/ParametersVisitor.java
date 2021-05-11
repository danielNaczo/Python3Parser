package io.github.danielnaczo.python3parser.visitors.ast.parameters;

import java.util.ArrayList;
import java.util.List;

import io.github.danielnaczo.python3parser.Python3Parser.AfterPositionalArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.CompleteArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.JustKeywordsArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.KwOnlyListArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.NormalOrDefaultTfpDefContext;
import io.github.danielnaczo.python3parser.Python3Parser.ParametersContext;
import io.github.danielnaczo.python3parser.Python3Parser.TypedargslistContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarAfterPositionalArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarCompleteArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarJustKeywordsArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarKwOnlyListArgsContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarNormalOrDefaultTfpDefContext;
import io.github.danielnaczo.python3parser.Python3Parser.VarargslistContext;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import io.github.danielnaczo.python3parser.visitors.ast.GenericUnsupportedCSTVisitor;
import io.github.danielnaczo.python3parser.visitors.exceptions.RuleException;

public class ParametersVisitor extends GenericUnsupportedCSTVisitor<Parameters> {

	
	@Override
	public Parameters visitParameters(ParametersContext ctx) {
		if (ctx.typedargslist() != null) {
			return ctx.typedargslist().accept(new ParametersVisitor());			
		}
		
		return null;
	}
	
	@Override
	public Parameters visitTypedargslist(TypedargslistContext ctx) {
		if (ctx.completeArgs() != null)	{
			return ctx.completeArgs().accept(new ParametersVisitor());
		}
		if (ctx.justKeywordsArgs() != null) {
			return ctx.justKeywordsArgs().accept(new ParametersVisitor());
		}
		if (ctx.kwOnlyListArgs() != null) {
			return ctx.kwOnlyListArgs().accept(new ParametersVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Parameters visitCompleteArgs(CompleteArgsContext ctx) {
		List<Parameter> args = new ArrayList<>();
		List<DefaultParameter> defaults = new ArrayList<>();
		Parameter vararg = null;

		List<Parameter> kwonlyargs = new ArrayList<>();
		List<DefaultParameter> kwDefaults = new ArrayList<>();
		Parameter kwarg = null;

		if (ctx.tfpdef() != null) {
			args.add(ctx.tfpdef().accept(new ParameterVisitor()));
		}
		else {
			defaults.add(ctx.defaultTfpdef().accept(new DefaultParameterVisitor()));
		}
		
		if (ctx.normalOrDefaultTfpDef() != null && !ctx.normalOrDefaultTfpDef().isEmpty()) {
			for (int i = 0; i < ctx.normalOrDefaultTfpDef().size(); i++) {
				NormalOrDefaultTfpDefContext normalOrDefault = ctx.normalOrDefaultTfpDef(i);
				if (normalOrDefault.tfpdef() != null) {
					args.add(normalOrDefault.tfpdef().accept(new ParameterVisitor()));
				}
				else {
					defaults.add(normalOrDefault.defaultTfpdef().accept(new DefaultParameterVisitor()));
				}
			}
		}
		
		if (ctx.afterPositionalArgs() != null) {
			AfterPositionalArgsContext afterPositionalArgs = ctx.afterPositionalArgs();
			
			if (afterPositionalArgs.positionalList() != null && !afterPositionalArgs.positionalList().isEmpty()) {
				vararg = afterPositionalArgs.positionalList().accept(new ParameterVisitor());
				
				for (int i = 0; i < afterPositionalArgs.normalOrDefaultTfpDef().size(); i++) {
					NormalOrDefaultTfpDefContext normalOrDefault = afterPositionalArgs.normalOrDefaultTfpDef(i);
					if (normalOrDefault.tfpdef() != null) {
						kwonlyargs.add(normalOrDefault.tfpdef().accept(new ParameterVisitor()));
					}
					else {
						kwDefaults.add(normalOrDefault.defaultTfpdef().accept(new DefaultParameterVisitor()));
					}
				}
			}
			
			if (afterPositionalArgs.kwlistArgs1() != null) {
				kwarg = afterPositionalArgs.kwlistArgs1().accept(new ParameterVisitor());
			}
		}
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
	@Override
	public Parameters visitJustKeywordsArgs(JustKeywordsArgsContext ctx) {
		List<Parameter> args = null;
		List<DefaultParameter> defaults = null;
		Parameter vararg = null;

		List<Parameter> kwonlyargs = null;
		List<DefaultParameter> kwDefaults = null;
		Parameter kwarg = null;
		
		vararg = ctx.positionalList().accept(new ParameterVisitor());
		
		if (ctx.normalOrDefaultTfpDef() != null && !ctx.normalOrDefaultTfpDef().isEmpty()) {
			kwonlyargs = new ArrayList<>();
			kwDefaults = new ArrayList<>();
			
			for (int i = 0; i < ctx.normalOrDefaultTfpDef().size(); i++) {
				NormalOrDefaultTfpDefContext normalOrDefault = ctx.normalOrDefaultTfpDef(i);
				if (normalOrDefault.tfpdef() != null) {
					kwonlyargs.add(normalOrDefault.tfpdef().accept(new ParameterVisitor()));
				}
				else {
					kwDefaults.add(normalOrDefault.defaultTfpdef().accept(new DefaultParameterVisitor()));
				}
			}
		}
		
		if (ctx.kwlistArgs2() != null) {
			kwarg = ctx.kwlistArgs2().accept(new ParameterVisitor());	
		}
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
	@Override
	public Parameters visitKwOnlyListArgs(KwOnlyListArgsContext ctx) {
		List<Parameter> args = null;
		List<DefaultParameter> defaults = null;
		Parameter vararg = null;

		List<Parameter> kwonlyargs = null;
		List<DefaultParameter> kwDefaults = null;
		Parameter kwarg = ctx.tfpdef().accept(new ParameterVisitor());
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
	@Override
	public Parameters visitVarargslist(VarargslistContext ctx) {
		if (ctx.varCompleteArgs() != null)	{
			return ctx.varCompleteArgs().accept(new ParametersVisitor());
		}
		if (ctx.varJustKeywordsArgs() != null) {
			return ctx.varJustKeywordsArgs().accept(new ParametersVisitor());
		}
		if (ctx.varKwOnlyListArgs() != null) {
			return ctx.varKwOnlyListArgs().accept(new ParametersVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Parameters visitVarCompleteArgs(VarCompleteArgsContext ctx) {
		List<Parameter> args = new ArrayList<>();
		List<DefaultParameter> defaults = new ArrayList<>();
		Parameter vararg = null;

		List<Parameter> kwonlyargs = new ArrayList<>();
		List<DefaultParameter> kwDefaults = new ArrayList<>();
		Parameter kwarg = null;

		if (ctx.vfpdef() != null) {
			args.add(ctx.vfpdef().accept(new ParameterVisitor()));
		}
		else {
			defaults.add(ctx.varDefaultVfpdef().accept(new DefaultParameterVisitor()));
		}
		
		if (ctx.varNormalOrDefaultTfpDef() != null && !ctx.varNormalOrDefaultTfpDef().isEmpty()) {
			for (int i = 0; i < ctx.varNormalOrDefaultTfpDef().size(); i++) {
				VarNormalOrDefaultTfpDefContext normalOrDefault = ctx.varNormalOrDefaultTfpDef(i);
				if (normalOrDefault.vfpdef() != null) {
					args.add(normalOrDefault.vfpdef().accept(new ParameterVisitor()));
				}
				else {
					defaults.add(normalOrDefault.varDefaultVfpdef().accept(new DefaultParameterVisitor()));
				}
			}
		}
		
		if (ctx.varAfterPositionalArgs() != null) {
			VarAfterPositionalArgsContext afterPositionalArgs = ctx.varAfterPositionalArgs();
			
			if (afterPositionalArgs.varPositionalList() != null && !afterPositionalArgs.varPositionalList().isEmpty()) {
				vararg = afterPositionalArgs.varPositionalList().accept(new ParameterVisitor());
				
				for (int i = 0; i < afterPositionalArgs.varNormalOrDefaultTfpDef().size(); i++) {
					VarNormalOrDefaultTfpDefContext normalOrDefault = afterPositionalArgs.varNormalOrDefaultTfpDef(i);
					if (normalOrDefault.vfpdef() != null) {
						kwonlyargs.add(normalOrDefault.vfpdef().accept(new ParameterVisitor()));
					}
					else {
						kwDefaults.add(normalOrDefault.varDefaultVfpdef().accept(new DefaultParameterVisitor()));
					}
				}
			}
			
			if (afterPositionalArgs.varKwlistArgs1() != null) {
				kwarg = afterPositionalArgs.varKwlistArgs1().accept(new ParameterVisitor());
			}
		}
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
	@Override
	public Parameters visitVarJustKeywordsArgs(VarJustKeywordsArgsContext ctx) {
		List<Parameter> args = null;
		List<DefaultParameter> defaults = null;
		Parameter vararg = null;

		List<Parameter> kwonlyargs = null;
		List<DefaultParameter> kwDefaults = null;
		Parameter kwarg = null;
		
		vararg = ctx.varPositionalList().accept(new ParameterVisitor());
		
		if (ctx.varNormalOrDefaultTfpDef() != null && !ctx.varNormalOrDefaultTfpDef().isEmpty()) {
			kwonlyargs = new ArrayList<>();
			kwDefaults = new ArrayList<>();
			
			for (int i = 0; i < ctx.varNormalOrDefaultTfpDef().size(); i++) {
				VarNormalOrDefaultTfpDefContext normalOrDefault = ctx.varNormalOrDefaultTfpDef(i);
				if (normalOrDefault.vfpdef() != null) {
					kwonlyargs.add(normalOrDefault.vfpdef().accept(new ParameterVisitor()));
				}
				else {
					kwDefaults.add(normalOrDefault.varDefaultVfpdef().accept(new DefaultParameterVisitor()));
				}
			}
		}
		
		if (ctx.varKwlistArgs2() != null) {
			kwarg = ctx.varKwlistArgs2().accept(new ParameterVisitor());	
		}
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
	@Override
	public Parameters visitVarKwOnlyListArgs(VarKwOnlyListArgsContext ctx) {
		List<Parameter> args = null;
		List<DefaultParameter> defaults = null;
		Parameter vararg = null;

		List<Parameter> kwonlyargs = null;
		List<DefaultParameter> kwDefaults = null;
		Parameter kwarg = ctx.vfpdef().accept(new ParameterVisitor());
		
		return new Parameters(args, defaults, vararg, kwonlyargs, kwDefaults, kwarg);
	}
	
}
