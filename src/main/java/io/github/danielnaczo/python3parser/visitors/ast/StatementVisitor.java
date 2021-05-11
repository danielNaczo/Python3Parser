package io.github.danielnaczo.python3parser.visitors.ast;

import java.util.ArrayList;
import java.util.List;

import io.github.danielnaczo.python3parser.visitors.ast.parameters.ParametersVisitor;
import io.github.danielnaczo.python3parser.visitors.exceptions.RuleException;
import io.github.danielnaczo.python3parser.visitors.exceptions.UnsupportedANTLRMethodException;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.If;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.While;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.forStmts.AsyncFor;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.forStmts.For;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.AsyncFunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.tryExceptStmts.ExceptHandler;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.tryExceptStmts.Try;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.withStmts.AsyncWith;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.withStmts.With;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.withStmts.WithItem;
import io.github.danielnaczo.python3parser.model.stmts.flowStmts.Break;
import io.github.danielnaczo.python3parser.model.stmts.flowStmts.Continue;
import io.github.danielnaczo.python3parser.model.stmts.flowStmts.Raise;
import io.github.danielnaczo.python3parser.model.stmts.flowStmts.Return;
import io.github.danielnaczo.python3parser.model.stmts.importStmts.Alias;
import io.github.danielnaczo.python3parser.model.stmts.importStmts.Import;
import io.github.danielnaczo.python3parser.model.stmts.importStmts.ImportFrom;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.Assert;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.Delete;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.Global;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.Nonlocal;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.Pass;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts.AnnAssign;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts.Assign;
import io.github.danielnaczo.python3parser.model.stmts.smallStmts.assignStmts.AugAssign;
import com.github.python3parser.Python3Parser.*;

public class StatementVisitor extends GenericUnsupportedCSTVisitor<Statement>{

	@Override
	public Statement visitFuncdef(FuncdefContext ctx) {
		Identifier name = new Identifier(ctx.NAME().toString());
		Parameters args = ctx.parameters().accept(new ParametersVisitor());
		Statement body = ctx.suite().accept(new StatementVisitor());
		
		List<Decorator> decoratorList = new ArrayList<>();
		for (int i = 0; i < ctx.decorator().size(); i++) {
			decoratorList.add(ctx.decorator(i).accept(new DecoratorVisitor()));
		}
		
		Expression returns = null;
		if (ctx.test() != null) {
			returns = ctx.test().accept(new ExpressionVisitor());
		}

		if (ctx.ASYNC() != null) {
			AsyncFunctionDef asyncFunctionDef = new AsyncFunctionDef(name, args, body, decoratorList, returns);
			return asyncFunctionDef;
		} else {
			FunctionDef functionDef = new FunctionDef(name, args, body, decoratorList, returns);
			return functionDef;
		}
	}

	@Override
	public Statement visitStmt(StmtContext ctx) {
		if (ctx.simple_stmt() != null) {
			return ctx.simple_stmt().accept(new StatementVisitor());
		}
		else if (ctx.compound_stmt() != null) {
			return ctx.compound_stmt().accept(new StatementVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitSimple_stmt(Simple_stmtContext ctx) {
		List<Small_stmtContext> smallStatementsContext = ctx.small_stmt();
		ArrayList<Statement> statements = new ArrayList<>();
		
		for (Small_stmtContext small_stmtContext : smallStatementsContext) {
			statements.add(small_stmtContext.accept(new StatementVisitor()));
		}

		if (statements.size() == 1) {
			return statements.get(0);
		}
		else {
			return new Body(statements);			
		}
		
	}
	
	@Override
	public Statement visitSmall_stmt(Small_stmtContext ctx) {
		if (ctx.expr_stmt() != null) {
			return ctx.expr_stmt().accept(new StatementVisitor());
		}
		if (ctx.del_stmt() != null) {
			return ctx.del_stmt().accept(new StatementVisitor());
		}
		if (ctx.pass_stmt() != null) {
			return ctx.pass_stmt().accept(new StatementVisitor());
		}
		if (ctx.flow_stmt() != null) {
			return ctx.flow_stmt().accept(new StatementVisitor());
		}
		if (ctx.import_stmt() != null) {
			return ctx.import_stmt().accept(new StatementVisitor());
		}
		if (ctx.global_stmt() != null) {
			return ctx.global_stmt().accept(new StatementVisitor());
		}
		if (ctx.nonlocal_stmt() != null) {
			return ctx.nonlocal_stmt().accept(new StatementVisitor());
		}
		if (ctx.assert_stmt() != null) {
			return ctx.assert_stmt().accept(new StatementVisitor());
		}
		throw new RuleException();
		
	}
	
	@Override
	public Statement visitExpr_stmt(Expr_stmtContext ctx) {
		if (ctx.testlist_star_expr() != null) {
			return ctx.testlist_star_expr().accept(new ExpressionVisitor());
		}
		if (ctx.expr_stmtIndividualAssign() != null) {
			return ctx.expr_stmtIndividualAssign().accept(new StatementVisitor());
		}
		if (ctx.expr_stmtNormalAssign() != null) {
			return ctx.expr_stmtNormalAssign().accept(new StatementVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitExpr_stmtIndividualAssign(Expr_stmtIndividualAssignContext ctx) {
		if (ctx.annassign() != null) {
			Expression target = ctx.testlist_star_expr().accept(new ExpressionVisitor());
			Expression annotation = ctx.annassign().test(0).accept(new ExpressionVisitor());
			Expression value = null;
			
			if (ctx.annassign().test().size() == 2 ) {
				value = ctx.annassign().test(1).accept(new ExpressionVisitor());
			}
			
			return new AnnAssign(target, annotation, value);
		}
		if (ctx.augassign() != null) {
			Expression target = ctx.testlist_star_expr().accept(new ExpressionVisitor());
			Operator op = ctx.augassign().accept(new OperatorVisitor());
			Expression value;
			if (ctx.yield_expr() != null) {
				value = ctx.yield_expr().accept(new ExpressionVisitor());
			}
			else {
				value = ctx.testlist().accept(new ExpressionVisitor());
			}
			return new AugAssign(target, op, value);
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitExpr_stmtNormalAssign(Expr_stmtNormalAssignContext ctx) {
		List<Expression> targets = new ArrayList<>();
		Expression value = null;
		
		targets.add(ctx.testlist_star_expr().accept(new ExpressionVisitor()));
		
		for (int i = 0; i < ctx.expr_NormalAssignList().size(); i++) {
			if (i == ctx.expr_NormalAssignList().size()-1) {
				value = ctx.expr_NormalAssignList(i).accept(new ExpressionVisitor());
			}
			else {
				targets.add(ctx.expr_NormalAssignList(i).accept(new ExpressionVisitor()));
			}
		}
		
		return new Assign(targets, value);
	}
	
	@Override
	public Statement visitDel_stmt(Del_stmtContext ctx) {
		Expression expression = ctx.exprlist().accept(new ExpressionVisitor());
		return new Delete(expression);
	}
	
	@Override
	public Statement visitPass_stmt(Pass_stmtContext ctx) {
		return new Pass();
	}

	@Override
	public Statement visitFlow_stmt(Flow_stmtContext ctx) {
		if (ctx.break_stmt() != null) {
			return ctx.break_stmt().accept(new StatementVisitor());
		}
		if (ctx.continue_stmt() != null) {
			return ctx.continue_stmt().accept(new StatementVisitor());
		}
		if (ctx.return_stmt() != null) {
			return ctx.return_stmt().accept(new StatementVisitor());
		}
		if (ctx.raise_stmt() != null) {
			return ctx.raise_stmt().accept(new StatementVisitor());
		}
		if (ctx.yield_stmt() != null) {
			return ctx.yield_stmt().accept(new StatementVisitor());
		}
		throw new RuleException();
	}
	
	@Override
	public Statement visitBreak_stmt(Break_stmtContext ctx) {
		return new Break();
	}
	
	@Override
	public Statement visitContinue_stmt(Continue_stmtContext ctx) {
		return new Continue();
	}
	
	@Override
	public Statement visitReturn_stmt(Return_stmtContext ctx) {
		Expression value = null;
		if (ctx.testlist() != null) {
			value = ctx.testlist().accept(new ExpressionVisitor());			
		}
		Return returnObj = new Return(value);
		
		return returnObj;
	}

	@Override
	public Statement visitYield_stmt(Yield_stmtContext ctx) {
		return ctx.yield_expr().accept(new ExpressionVisitor());
	}
	
	@Override
	public Statement visitRaise_stmt(Raise_stmtContext ctx) {
		if (ctx.test() == null || ctx.test().isEmpty()) {
			return new Raise(null, null);
		}
		if (ctx.test().size() == 1) {
			Expression exc = ctx.test(0).accept(new ExpressionVisitor());
			return new Raise(exc, null);
		}
		if (ctx.test().size() == 2) {
			Expression exc = ctx.test(0).accept(new ExpressionVisitor());
			Expression cause = ctx.test(1).accept(new ExpressionVisitor());
			return new Raise(exc, cause);
		}
		throw new RuleException();
	}
	
	@Override
	public Statement visitImport_stmt(Import_stmtContext ctx) {
		if (ctx.import_name() != null) {
			return ctx.import_name().accept(new StatementVisitor());
		}
		
		if (ctx.import_from() != null) {
			return ctx.import_from().accept(new StatementVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitImport_name(Import_nameContext ctx) {
		List<Alias> names = ctx.dotted_as_names().accept(new AliasVisitor());
		
		return new Import(names);
	}
	
	@Override
	public Statement visitImport_from(Import_fromContext ctx) {
		Identifier module = null;
		List<Alias> names = new ArrayList<>();
		int level = 0;
		
		if (ctx.dotted_name() != null) {
			module = new Identifier(ctx.dotted_name().accept(new StringVisitor()));
		}
		
		if (ctx.STAR() != null) {
			names.add(new Alias(new Identifier("*"), null));
		}
		else {
			names = ctx.import_as_names().accept(new AliasVisitor());
		}
		
		
		for (int i = 0; i < ctx.DOT().size(); i++) {
			level = level + 1;
		}
		for (int i = 0; i < ctx.ELLIPSIS().size(); i++) {
			level = level + 3;
		}
		
		return new ImportFrom(module, names, level);
	}
	
	@Override
	public Statement visitGlobal_stmt(Global_stmtContext ctx) {
		List<Identifier> names = new ArrayList<>();
		
		for (int i = 0; i < ctx.NAME().size(); i++) {
			names.add(new Identifier(ctx.NAME(i).getText()));
		}
		
		return new Global(names);
	}
	
	@Override
	public Statement visitNonlocal_stmt(Nonlocal_stmtContext ctx) {
		List<Identifier> names = new ArrayList<>();
		
		for (int i = 0; i < ctx.NAME().size(); i++) {
			names.add(new Identifier(ctx.NAME(i).getText()));
		}
		
		return new Nonlocal(names);
	}
	
	@Override
	public Statement visitAssert_stmt(Assert_stmtContext ctx) {
		Expression test;
		Expression msg = null;
		
		test = ctx.test(0).accept(new ExpressionVisitor());
		
		if (ctx.test().size() == 2) {
			msg = ctx.test(1).accept(new ExpressionVisitor());
		}
		
		return new Assert(test, msg);
	}
	
	@Override
	public Statement visitCompound_stmt(Compound_stmtContext ctx) {
		if (ctx.if_stmt() != null) {
			return ctx.if_stmt().accept(new StatementVisitor());
		}
		if (ctx.while_stmt() != null) {
			return ctx.while_stmt().accept(new StatementVisitor());
		}
		if (ctx.for_stmt() != null) {
			return ctx.for_stmt().accept(new StatementVisitor());
		}
		if (ctx.try_stmt() != null) {
			return ctx.try_stmt().accept(new StatementVisitor());
		}
		if (ctx.with_stmt() != null) {
			return ctx.with_stmt().accept(new StatementVisitor());
		}
		if (ctx.funcdef()!= null) {
			return ctx.funcdef().accept(new StatementVisitor());
		}
		if (ctx.classdef() != null) {
			return ctx.classdef().accept(new StatementVisitor());
		}
		if (ctx.async_stmt() != null) {
			return ctx.async_stmt().accept(new StatementVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitAsync_stmt(Async_stmtContext ctx) {
		if (ctx.funcdef() != null) {
			FunctionDef functionDef = (FunctionDef) ctx.funcdef().accept(new StatementVisitor());
			return new AsyncFunctionDef(functionDef.getName(),
					functionDef.getParameters().isPresent() ? functionDef.getParameters().get() : null,
					functionDef.getBody(), functionDef.getDecoratorList(),
					functionDef.getReturns().isPresent() ? functionDef.getReturns().get() : null);
		}
		if (ctx.with_stmt() != null) {
			With with = (With) ctx.with_stmt().accept(new StatementVisitor());
			return new AsyncWith(with.getItems(), with.getBody());
		}
		if (ctx.for_stmt() != null) {
			For forStmt = (For) ctx.for_stmt().accept(new StatementVisitor());
			return new AsyncFor(forStmt.getTarget(), forStmt.getIter(), forStmt.getBody(),
					forStmt.getOrElse().isPresent() ? forStmt.getOrElse().get() : null);
		}
		
		throw new RuleException();
	}
	
	@Override
	public Statement visitIf_stmt(If_stmtContext ctx) {
		Expression ifTest;
		Statement ifSuite;
		List<Expression> elifTests = new ArrayList<>();
		List<Statement> elifSuites = new ArrayList<>();
		Statement elseSuite = null;
		
		ifTest = ctx.test(0).accept(new ExpressionVisitor());
		ifSuite = ctx.suite(0).accept(new StatementVisitor());
		
		int countSuites = 1;
		for (int i = 1; i < ctx.test().size(); i++) {
			Expression test = ctx.test(i).accept(new ExpressionVisitor());
			elifTests.add(test);
			Statement suite = ctx.suite(i).accept(new StatementVisitor());
			elifSuites.add(suite);
			countSuites++;
		}
		
		if (countSuites < ctx.suite().size()) {
			elseSuite = ctx.suite(countSuites).accept(new StatementVisitor());
		}
		
		return new If(ifTest, ifSuite, elifTests, elifSuites, elseSuite);
		
	}
	
	@Override
	public Statement visitWhile_stmt(While_stmtContext ctx) {
		
		Expression test = ctx.test().accept(new ExpressionVisitor());
		Statement body = ctx.suite(0).accept(new StatementVisitor());
		Statement orElse = null;
		
		if (ctx.suite().size() == 2) {
			orElse = ctx.suite(1).accept(new StatementVisitor());
		}
		
		return new While(test, body, orElse);
	}
	
	@Override
	public Statement visitFor_stmt(For_stmtContext ctx) {
		Expression target = ctx.exprlist().accept(new ExpressionVisitor());
		Expression iter = ctx.testlist().accept(new ExpressionVisitor());
		Statement body = ctx.suite(0).accept(new StatementVisitor());
		Statement orElse = null;
		
		if (ctx.suite().size() == 2) {
			orElse = ctx.suite(1).accept(new StatementVisitor());
		}
		
		return new For(target, iter, body, orElse);
	}
	
	@Override
	public Statement visitTry_stmt(Try_stmtContext ctx) {
		Statement body = ctx.suite(0).accept(new StatementVisitor());
		List<ExceptHandler> handlers = null;
		List<Statement> handlersBody = null;
		Statement orElse = null;
		Statement finalBody = null;
		
		handlers = new ArrayList<>();
		handlersBody = new ArrayList<>();
		if (ctx.except_clause() != null && !ctx.except_clause().isEmpty()) {
			int suiteCounter = 1;
			
			for (int i = 0; i < ctx.except_clause().size(); i++) {
				handlers.add(ctx.except_clause(i).accept(new ExceptHandlerVisitor()));
				handlersBody.add(ctx.suite(i+1).accept(new StatementVisitor()));
				suiteCounter++;
			}
			
			if (suiteCounter < ctx.suite().size() && ctx.ELSE() != null) {
				orElse = ctx.suite(suiteCounter).accept(new StatementVisitor());
				suiteCounter++;
			}
			
			if (suiteCounter < ctx.suite().size() && ctx.FINALLY() != null) {
				finalBody = ctx.suite(suiteCounter).accept(new StatementVisitor());
			}
			
			return new Try(body, handlers, handlersBody, orElse, finalBody);
		}
		else {
			finalBody = ctx.suite(1).accept(new StatementVisitor());
			return new Try(body, handlers, handlersBody, orElse, finalBody);
		}
	}
	
	@Override
	public Statement visitWith_stmt(With_stmtContext ctx) {
		List<WithItem> items = new ArrayList<>();
		
		for (int i = 0; i < ctx.with_item().size(); i++) {
			items.add(ctx.with_item(i).accept(new WithItemVisitor()));
		}
		
		Statement body = ctx.suite().accept(new StatementVisitor());
		
		return new With(items, body);
	}
	
	@Override
	public Statement visitSuite(SuiteContext ctx) {
		
		Simple_stmtContext simpleStatementContext = ctx.simple_stmt();
		List<StmtContext> statementsContext = ctx.stmt();
		
		if (simpleStatementContext != null) {
			return simpleStatementContext.accept(new StatementVisitor());
		} 
		if (!statementsContext.isEmpty()) {
			ArrayList<Statement> statements = new ArrayList<>();
			for (StmtContext stmtContext : statementsContext) {
				Statement stmtOrExpr = null;
				try {
					stmtOrExpr = stmtContext.accept(new StatementVisitor());
				} catch (UnsupportedANTLRMethodException e) {
					stmtOrExpr = stmtContext.accept(new ExpressionVisitor());
				}
				statements.add(stmtOrExpr);
			}
			Body body = new Body(statements);
			return body;
		}
		
		throw new RuleException();
	}
	
	@Override
		public Statement visitClassdef(ClassdefContext ctx) {
			Identifier name = new Identifier(ctx.NAME().getText());
			Expression arguments = null;
			Statement body = ctx.suite().accept(new StatementVisitor());
			List<Decorator> decoratorList = new ArrayList<>();
			
			if (ctx.arglist() != null) {
				arguments = ctx.arglist().accept(new ExpressionVisitor());
			}
			
			for (int i = 0; i < ctx.decorator().size(); i++) {
				decoratorList.add(ctx.decorator(i).accept(new DecoratorVisitor()));
			}

			return new ClassDef(name, arguments, body, decoratorList);
	}
}
