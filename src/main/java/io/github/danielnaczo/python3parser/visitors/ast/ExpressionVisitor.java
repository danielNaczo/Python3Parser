package io.github.danielnaczo.python3parser.visitors.ast;

import java.util.ArrayList;
import java.util.List;

import io.github.danielnaczo.python3parser.Python3Parser.And_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.And_testContext;
import io.github.danielnaczo.python3parser.Python3Parser.ArglistContext;
import io.github.danielnaczo.python3parser.Python3Parser.ArgumentContext;
import io.github.danielnaczo.python3parser.Python3Parser.ArgumentNormalContext;
import io.github.danielnaczo.python3parser.Python3Parser.ArgumentStarContext;
import io.github.danielnaczo.python3parser.Python3Parser.Arith_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.Arith_opContext;
import io.github.danielnaczo.python3parser.Python3Parser.AtomContext;
import io.github.danielnaczo.python3parser.Python3Parser.Atom_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.AwaitContext;
import io.github.danielnaczo.python3parser.Python3Parser.Comp_ifContext;
import io.github.danielnaczo.python3parser.Python3Parser.Comp_opContext;
import io.github.danielnaczo.python3parser.Python3Parser.ComparisonContext;
import io.github.danielnaczo.python3parser.Python3Parser.DictFirstContext;
import io.github.danielnaczo.python3parser.Python3Parser.DictionaryOrSetContext;
import io.github.danielnaczo.python3parser.Python3Parser.DictorsetmakerContext;
import io.github.danielnaczo.python3parser.Python3Parser.ExprContext;
import io.github.danielnaczo.python3parser.Python3Parser.ExprOrStarExprContext;
import io.github.danielnaczo.python3parser.Python3Parser.Expr_NormalAssignListContext;
import io.github.danielnaczo.python3parser.Python3Parser.ExprlistContext;
import io.github.danielnaczo.python3parser.Python3Parser.FactorContext;
import io.github.danielnaczo.python3parser.Python3Parser.Factor_opContext;
import io.github.danielnaczo.python3parser.Python3Parser.LambdefContext;
import io.github.danielnaczo.python3parser.Python3Parser.Lambdef_nocondContext;
import io.github.danielnaczo.python3parser.Python3Parser.ListContext;
import io.github.danielnaczo.python3parser.Python3Parser.Not_testContext;
import io.github.danielnaczo.python3parser.Python3Parser.Or_testContext;
import io.github.danielnaczo.python3parser.Python3Parser.PowerContext;
import io.github.danielnaczo.python3parser.Python3Parser.SetFirstContext;
import io.github.danielnaczo.python3parser.Python3Parser.Shift_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.Shift_opContext;
import io.github.danielnaczo.python3parser.Python3Parser.SliceStepContext;
import io.github.danielnaczo.python3parser.Python3Parser.SliceUpperContext;
import io.github.danielnaczo.python3parser.Python3Parser.SlicelLowerContext;
import io.github.danielnaczo.python3parser.Python3Parser.Star_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.SubscriptlistContext;
import io.github.danielnaczo.python3parser.Python3Parser.TermContext;
import io.github.danielnaczo.python3parser.Python3Parser.Term_opContext;
import io.github.danielnaczo.python3parser.Python3Parser.TestContext;
import io.github.danielnaczo.python3parser.Python3Parser.TestOrStarList_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.TestOrStar_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.Test_nocondContext;
import io.github.danielnaczo.python3parser.Python3Parser.TestlistContext;
import io.github.danielnaczo.python3parser.Python3Parser.Testlist_compListContext;
import io.github.danielnaczo.python3parser.Python3Parser.Testlist_compTupleContext;
import io.github.danielnaczo.python3parser.Python3Parser.Testlist_star_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.TrailerCallContext;
import io.github.danielnaczo.python3parser.Python3Parser.TrailerContext;
import io.github.danielnaczo.python3parser.Python3Parser.TrailerNameContext;
import io.github.danielnaczo.python3parser.Python3Parser.TrailerSubscriptContext;
import io.github.danielnaczo.python3parser.Python3Parser.TupleContext;
import io.github.danielnaczo.python3parser.Python3Parser.Xor_exprContext;
import io.github.danielnaczo.python3parser.Python3Parser.Yield_argContext;
import io.github.danielnaczo.python3parser.Python3Parser.Yield_exprContext;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.expr.ExpressionsList;
import io.github.danielnaczo.python3parser.model.expr.atoms.Atom;
import io.github.danielnaczo.python3parser.model.expr.atoms.Ellipsis;
import io.github.danielnaczo.python3parser.model.expr.atoms.False;
import io.github.danielnaczo.python3parser.model.expr.atoms.JoinedStr;
import io.github.danielnaczo.python3parser.model.expr.atoms.Name;
import io.github.danielnaczo.python3parser.model.expr.atoms.None;
import io.github.danielnaczo.python3parser.model.expr.atoms.Num;
import io.github.danielnaczo.python3parser.model.expr.atoms.Str;
import io.github.danielnaczo.python3parser.model.expr.atoms.True;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.Attribute;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.arguments.ArgumentComp;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.arguments.Arguments;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.arguments.Keyword;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.Subscript;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.ExtSlice;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.Comprehension;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.DictComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.ListComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.SetComp;
import io.github.danielnaczo.python3parser.model.expr.datastructures.Dict;
import io.github.danielnaczo.python3parser.model.expr.datastructures.ListExpr;
import io.github.danielnaczo.python3parser.model.expr.datastructures.Set;
import io.github.danielnaczo.python3parser.model.expr.datastructures.Tuple;
import io.github.danielnaczo.python3parser.model.expr.generators.Generator;
import io.github.danielnaczo.python3parser.model.expr.operators.IfExpr;
import io.github.danielnaczo.python3parser.model.expr.operators.Lambda;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Add;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.At;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BinOp;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitAnd;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitOr;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitXor;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Div;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.FloorDiv;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.LShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mod;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mult;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Pow;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.RShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Sub;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.And;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.Or;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Eq;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Gt;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.GtE;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.In;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Is;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.IsNot;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Lt;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.LtE;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.NotEq;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.NotIn;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Await;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Invert;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Not;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Starred;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.UAdd;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.USub;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.UnaryOp;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.Yield;
import io.github.danielnaczo.python3parser.model.expr.operators.unaryops.YieldFrom;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import io.github.danielnaczo.python3parser.visitors.ast.parameters.ParametersVisitor;
import io.github.danielnaczo.python3parser.visitors.exceptions.RuleException;

public class ExpressionVisitor extends GenericUnsupportedCSTVisitor<Expression> {

	@Override
	public Expression visitExpr_NormalAssignList(Expr_NormalAssignListContext ctx) {
		if (ctx.yield_expr() != null) {
			return ctx.yield_expr().accept(new ExpressionVisitor());
		}
		if (ctx.testlist_star_expr() != null) {
			return ctx.testlist_star_expr().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTestlist_star_expr(Testlist_star_exprContext ctx) {
		if (ctx.testOrStarList_expr() == null || ctx.testOrStarList_expr().isEmpty()) {
			return ctx.testOrStar_expr().accept(new ExpressionVisitor());
		}

		List<Expression> expressions = new ArrayList<>();
		
		expressions.add(ctx.testOrStar_expr().accept(new ExpressionVisitor()));
		
		for (int i = 0; i < ctx.testOrStarList_expr().size(); i++) {
			expressions.add(ctx.testOrStarList_expr(i).accept(new ExpressionVisitor()));
		}
		
		return new ExpressionsList(expressions);
	}
	
	@Override
	public Expression visitTestOrStar_expr(TestOrStar_exprContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		if (ctx.star_expr() != null) {
			return ctx.star_expr().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTestOrStarList_expr(TestOrStarList_exprContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		if (ctx.star_expr() != null) {
			return ctx.star_expr().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTest(TestContext ctx) {
		if (ctx.or_test() != null && !ctx.or_test().isEmpty()) {
			if (ctx.or_test().size() == 1) {
				return ctx.or_test(0).accept(new ExpressionVisitor());
			}
			else if (ctx.or_test().size() == 2) {
				
				Expression test = ctx.or_test(1).accept(new ExpressionVisitor());
				Expression body = ctx.or_test(0).accept(new ExpressionVisitor());
				Expression orElse = ctx.test().accept(new ExpressionVisitor());
				IfExpr ifExpr = new IfExpr(test, body, orElse);
				test.setParent(ifExpr);
				body.setParent(ifExpr);
				orElse.setParent(ifExpr);
				return ifExpr;
			}
		}
		if (!ctx.lambdef().isEmpty()) {
			return ctx.lambdef().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTest_nocond(Test_nocondContext ctx) {
		if (ctx.or_test() != null) {
			return ctx.or_test().accept(new ExpressionVisitor());
		}
		if (ctx.lambdef_nocond() != null) {
			return ctx.lambdef_nocond().accept(new ExpressionVisitor());
		}
		throw new RuleException();
	}
	
	@Override
	public Expression visitLambdef(LambdefContext ctx) {
		Parameters args = null;
		Expression body;
		
		if (ctx.varargslist() != null) {
			args = ctx.varargslist().accept(new ParametersVisitor());
		}
		body = ctx.test().accept(new ExpressionVisitor());
		Lambda lambda = new Lambda(args, body);
		body.setParent(lambda);
		return lambda;
	}
	
	@Override
	public Expression visitLambdef_nocond(Lambdef_nocondContext ctx) {
		Parameters args = null;
		Expression body;
		
		if (ctx.varargslist() != null) {
			args = ctx.varargslist().accept(new ParametersVisitor());
		}
		
		body = ctx.test_nocond().accept(new ExpressionVisitor());
		
		return new Lambda(args, body);
	}

	@Override
	public Expression visitOr_test(Or_testContext ctx) {
		if (ctx.and_test().size() == 1 ) {
			return ctx.and_test(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.and_test(0).accept(new ExpressionVisitor());
		Expression right = ctx.and_test(1).accept(new ExpressionVisitor());
		BinOp binOp = new Or(left, right);
		left.setParent(binOp);
		right.setParent(binOp);
		
		for (int i = 2; i < ctx.and_test().size(); i++) {
			left = binOp;
			right = ctx.and_test(i).accept(new ExpressionVisitor());
			binOp = new Or(left, right);
			left.setParent(binOp);
			right.setParent(binOp);
		}
		
		return binOp;
	}
	
	@Override
	public Expression visitAnd_test(And_testContext ctx) {
		if (ctx.not_test().size() == 1 ) {
			return ctx.not_test(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.not_test(0).accept(new ExpressionVisitor());
		Expression right = ctx.not_test(1).accept(new ExpressionVisitor());
		BinOp binOp = new And(left, right);
		left.setParent(binOp);
		right.setParent(binOp);
		
		for (int i = 2; i < ctx.not_test().size(); i++) {
			left = binOp;
			right = ctx.not_test(i).accept(new ExpressionVisitor());
			binOp = new And(left, right);
			left.setParent(binOp);
			right.setParent(binOp);
		}
		
		return binOp;
	}
	
	@Override
	public Expression visitNot_test(Not_testContext ctx) {
		if (ctx.not_test() != null) {
			Expression expression = ctx.not_test().accept(new ExpressionVisitor());
			UnaryOp unaryOp = new Not(expression);
			expression.setParent(unaryOp);
			return unaryOp;
		}
		else if (ctx.comparison() != null) {
			return ctx.comparison().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitComparison(ComparisonContext ctx) {
		if (ctx.expr().size() == 1 ) {
			return ctx.expr(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.expr(0).accept(new ExpressionVisitor());
		Expression right = ctx.expr(1).accept(new ExpressionVisitor());
		BinOp binOp = createComparisonExpr(left, ctx.comp_op(0), right);
		
		for (int i = 2; i < ctx.expr().size(); i++) {
			left = binOp;
			right = ctx.expr(i).accept(new ExpressionVisitor());
			binOp = createComparisonExpr(left, ctx.comp_op(i-1), right);
		}
		
		return binOp;
	}
	
	private BinOp createComparisonExpr(Expression left, Comp_opContext compOp, Expression right) {
		if (compOp.NOT() != null && compOp.IN() != null) {
			NotIn notIn =  new NotIn(left, right);
			left.setParent(notIn);
			right.setParent(notIn);
			return notIn;
		}
		if (compOp.IS() != null && compOp.NOT() != null) {
			IsNot isNot =  new IsNot(left, right);
			left.setParent(isNot);
			right.setParent(isNot);
			return isNot;
		}
		if (compOp.LESS_THAN() != null) {
			Lt lt =  new Lt(left, right);
			left.setParent(lt);
			right.setParent(lt);
			return lt;
		}
		if (compOp.GREATER_THAN() != null) {
			Gt gt =  new Gt(left, right);
			left.setParent(gt);
			right.setParent(gt);
			return gt;
		}
		if (compOp.EQUALS() != null) {
			Eq eq =  new Eq(left, right);
			left.setParent(eq);
			right.setParent(eq);
			return eq;
		}
		if (compOp.GT_EQ() != null) {
			GtE gte =  new GtE(left, right);
			left.setParent(gte);
			right.setParent(gte);
			return gte;
		}
		if (compOp.LT_EQ() != null) {
			LtE lte =  new LtE(left, right);
			left.setParent(lte);
			right.setParent(lte);
			return lte;
		}
		if (compOp.NOT_EQ_1() != null) {
			NotEq notEq =  new NotEq(left, right);
			left.setParent(notEq);
			right.setParent(notEq);
			return notEq;
		}
		if (compOp.NOT_EQ_2() != null) {
			NotEq notEq =  new NotEq(left, right);
			left.setParent(notEq);
			right.setParent(notEq);
			return notEq;
		}
		if (compOp.IN() != null) {
			In in =  new In(left, right);
			left.setParent(in);
			right.setParent(in);
			return in;
		}
		if (compOp.IS() != null) {
			Is is =  new Is(left, right);
			left.setParent(is);
			right.setParent(is);
			return is;
		}
		throw new RuleException();
	}

	@Override
	public Expression visitStar_expr(Star_exprContext ctx) {
		Expression expression = ctx.expr().accept(new ExpressionVisitor());
		UnaryOp unaryOp = new Starred(expression);
		expression.setParent(unaryOp);
		return unaryOp;
	}
	
	@Override
	public Expression visitExpr(ExprContext ctx) {
		if (ctx.xor_expr().size() == 1 ) {
			return ctx.xor_expr(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.xor_expr(0).accept(new ExpressionVisitor());
		Expression right = ctx.xor_expr(1).accept(new ExpressionVisitor());
		BinOp binOp = new BitOr(left, right);
		left.setParent(binOp);
		right.setParent(binOp);
		
		for (int i = 2; i < ctx.xor_expr().size(); i++) {
			left = binOp;
			right = ctx.xor_expr(i).accept(new ExpressionVisitor());
			binOp = new BitOr(left, right);
			left.setParent(binOp);
			right.setParent(binOp);
		}
		
		return binOp;
	}
	
	@Override
	public Expression visitXor_expr(Xor_exprContext ctx) {
		if (ctx.and_expr().size() == 1 ) {
			return ctx.and_expr(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.and_expr(0).accept(new ExpressionVisitor());
		Expression right = ctx.and_expr(1).accept(new ExpressionVisitor());
		BinOp binOp = new BitXor(left, right);
		left.setParent(binOp);
		right.setParent(binOp);
		
		for (int i = 2; i < ctx.and_expr().size(); i++) {
			left = binOp;
			right = ctx.and_expr(i).accept(new ExpressionVisitor());
			binOp = new BitXor(left, right);
			left.setParent(binOp);
			right.setParent(binOp);
		}
		
		return binOp;
	}
	
	@Override
	public Expression visitAnd_expr(And_exprContext ctx) {
		if (ctx.shift_expr().size() == 1 ) {
			return ctx.shift_expr(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.shift_expr(0).accept(new ExpressionVisitor());
		Expression right = ctx.shift_expr(1).accept(new ExpressionVisitor());
		BinOp binOp = new BitAnd(left, right);
		left.setParent(binOp);
		right.setParent(binOp);
		
		for (int i = 2; i < ctx.shift_expr().size(); i++) {
			left = binOp;
			right = ctx.shift_expr(i).accept(new ExpressionVisitor());
			binOp = new BitAnd(left, right);
			left.setParent(binOp);
			right.setParent(binOp);
		}
		
		return binOp;
	}
	
	@Override
	public Expression visitShift_expr(Shift_exprContext ctx) {
		if (ctx.arith_expr().size() == 1 ) {
			return ctx.arith_expr(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.arith_expr(0).accept(new ExpressionVisitor());
		Expression right = ctx.arith_expr(1).accept(new ExpressionVisitor());
		BinOp binOp = createShiftExpr(left, ctx.shift_op(0), right);
		
		for (int i = 2; i < ctx.arith_expr().size(); i++) {
			left = binOp;
			right = ctx.arith_expr(i).accept(new ExpressionVisitor());
			binOp = createShiftExpr(left, ctx.shift_op(i-1), right);
		}
		
		return binOp;
	}
	
	private BinOp createShiftExpr(Expression left, Shift_opContext shiftOp, Expression right) {
		if (shiftOp.LEFT_SHIFT() != null) {
			LShift lShift = new LShift(left, right);
			left.setParent(lShift);
			right.setParent(lShift);
			return lShift;
		}
		if (shiftOp.RIGHT_SHIFT() != null) {
			RShift rShift = new RShift(left, right);
			left.setParent(rShift);
			right.setParent(rShift);
			return rShift;
		}
		throw new RuleException();
	}

	@Override
	public Expression visitArith_expr(Arith_exprContext ctx) {
		if (ctx.term().size() == 1 ) {
			return ctx.term(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.term(0).accept(new ExpressionVisitor());
		Expression right = ctx.term(1).accept(new ExpressionVisitor());
		BinOp binOp = createArithExpr(left, ctx.arith_op(0), right);
		
		for (int i = 2; i < ctx.term().size(); i++) {
			left = binOp;
			right = ctx.term(i).accept(new ExpressionVisitor());
			binOp = createArithExpr(left, ctx.arith_op(i-1), right);
		}
		
		return binOp;
	}
	
	private BinOp createArithExpr(Expression left, Arith_opContext arithOp, Expression right) {
		if (arithOp.ADD() != null) {
			Add add = new Add(left, right);
			left.setParent(add);
			right.setParent(add);
			return add;
		}
		if (arithOp.MINUS() != null) {
			Sub sub = new Sub(left, right);
			left.setParent(sub);
			right.setParent(sub);
			return sub;
		}
		throw new RuleException();
	}

	@Override
	public Expression visitTerm(TermContext ctx) {
		if (ctx.factor().size() == 1 ) {
			return ctx.factor(0).accept(new ExpressionVisitor());
		}
		
		Expression left = ctx.factor(0).accept(new ExpressionVisitor());
		Expression right = ctx.factor(1).accept(new ExpressionVisitor());
		BinOp binOp = createTerm(left, ctx.term_op(0), right);
		
		for (int i = 2; i < ctx.factor().size(); i++) {
			left = binOp;
			right = ctx.factor(i).accept(new ExpressionVisitor());
			binOp = createTerm(left, ctx.term_op(i-1), right);
		}
		
		return binOp;
	}
	
	private BinOp createTerm(Expression left, Term_opContext termOp, Expression right) {
		if (termOp.STAR() != null) {
			Mult mult = new Mult(left, right);
			left.setParent(mult);
			right.setParent(mult);
			return mult;
		}
		if (termOp.AT() != null) {
			At at = new At(left, right);
			left.setParent(at);
			right.setParent(at);
			return at;
		}
		if (termOp.DIV() != null) {
			Div div = new Div(left, right);
			left.setParent(div);
			right.setParent(div);
			return div;
		}
		if (termOp.MOD() != null) {
			Mod mod = new Mod(left, right);
			left.setParent(mod);
			right.setParent(mod);
			return mod;
		}
		if (termOp.IDIV() != null) {
			FloorDiv floorDiv = new FloorDiv(left, right);
			left.setParent(floorDiv);
			right.setParent(floorDiv);
			return floorDiv;
		}
		throw new RuleException();
	}

	@Override
	public Expression visitFactor(FactorContext ctx) {
		if (ctx.factor_op() != null && ctx.factor() != null) {
			Expression expression = ctx.factor().accept(new ExpressionVisitor());
			UnaryOp unaryOp = createUnaryOpExpr(expression, ctx.factor_op());
			return unaryOp;
		}
		
		if (ctx.power() != null) {
			return ctx.power().accept(new ExpressionVisitor());
		}
		throw new RuleException();
	}
	
	private UnaryOp createUnaryOpExpr(Expression expression, Factor_opContext factorOp) {
		if (factorOp.ADD() != null) {
			UAdd uAdd = new UAdd(expression);
			expression.setParent(uAdd);
			return uAdd;
		}
		if (factorOp.MINUS() != null) {
			USub uSub = new USub(expression);
			expression.setParent(uSub);
			return uSub;
		}
		if (factorOp.NOT_OP() != null) {
			Invert invert = new Invert(expression);
			expression.setParent(invert);
			return invert;
		}
		throw new RuleException();
	}
	
	@Override
	public Expression visitPower(PowerContext ctx) {
		if (ctx.factor() != null) {
			Expression left = ctx.await().accept(new ExpressionVisitor());
			Expression right = ctx.factor().accept(new ExpressionVisitor());
			BinOp pow = new Pow(left, right);
			left.setParent(pow);
			right.setParent(pow);
			return pow;
		}
		return ctx.await().accept(new ExpressionVisitor());
	}
	
	@Override
	public Expression visitAwait(AwaitContext ctx) {
		if (ctx.AWAIT() != null) {
			Expression expression = ctx.atom_expr().accept(new ExpressionVisitor());
			UnaryOp await = new Await(expression);
			expression.setParent(await);
			return await;
		}
		return ctx.atom_expr().accept(new ExpressionVisitor());
	}

	@Override
	public Expression visitAtom_expr(Atom_exprContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		
		if (ctx.trailer() == null || ctx.trailer().isEmpty()) {
			Expression expression = ctx.atom().accept(new ExpressionVisitor());
			return expression;
		}
		
		Expression atomElement = ctx.atom().accept(new ExpressionVisitor());
		List<Expression> trailers = new ArrayList<>();
		
		for (int i = 0; i < ctx.trailer().size(); i++) {
			trailers.add(ctx.trailer(i).accept(new ExpressionVisitor()));
		}
		return new Atom(atomElement, trailers);
	}
	
	@Override
	public Expression visitTrailer(TrailerContext ctx) {
		if (ctx.trailerCall() != null) {
			return ctx.trailerCall().accept(new ExpressionVisitor());
		}
		if (ctx.trailerSubscript() != null) {
			return ctx.trailerSubscript().accept(new ExpressionVisitor());
		}
		if (ctx.trailerName() != null) {
			return ctx.trailerName().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTrailerCall(TrailerCallContext ctx) {
		if (ctx.arglist() != null) {
			return ctx.arglist().accept(new ExpressionVisitor());
		}
		
		return new Arguments(null, null, null, null);
	}
	
	@Override
	public Expression visitTrailerSubscript(TrailerSubscriptContext ctx) {
		return ctx.subscriptlist().accept(new ExpressionVisitor());
	}
	
	@Override
	public Expression visitTrailerName(TrailerNameContext ctx) {
		Identifier attr = new Identifier(ctx.NAME().getText());
		return new Attribute(attr);
	}
	
	@Override
	public Expression visitAtom(AtomContext ctx) {
		if (ctx.tuple() != null) {
			return ctx.tuple().accept(new ExpressionVisitor());
		}
		if (ctx.list() != null) {
			return ctx.list().accept(new ExpressionVisitor());
		}
		if (ctx.dictionaryOrSet() != null) {
			return ctx.dictionaryOrSet().accept(new ExpressionVisitor());
		}
		if (ctx.NAME() != null) {
			return new Name(new Identifier(ctx.NAME().getText()));
		}
		if (ctx.NUMBER() != null) {
			return new Num(ctx.NUMBER().getText());
		}
		if (ctx.STRING() != null && !ctx.STRING().isEmpty()) {
			if (ctx.STRING().size() == 1) {
				return new Str(ctx.STRING(0).getText());
			}
			else {
				List<Expression> strings = new ArrayList<>();
				
				for (int i = 0; i < ctx.STRING().size(); i++) {
					strings.add(new Str(ctx.STRING(i).getText()));
				}
				
				return new JoinedStr(strings);
			}
		}
		if (ctx.ELLIPSIS() != null) {
			return new Ellipsis();
		}
		if (ctx.NONE() != null) {
			return new None();
		}
		if (ctx.TRUE() != null) {
			return new True();
		}
		if (ctx.FALSE() != null) {
			return new False();
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTuple(TupleContext ctx) {
		if (ctx.testlist_compTuple() == null) {
			return new Tuple(null);
		}
		
		if (ctx.testlist_compTuple() != null) {
			return ctx.testlist_compTuple().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTestlist_compTuple(Testlist_compTupleContext ctx) {
		if (ctx.yield_expr() != null) {
			Expression elt = ctx.yield_expr().accept(new ExpressionVisitor());
			return new Generator(elt, null);
		}
		
		if (ctx.comp_for() != null && !ctx.comp_for().isEmpty()) {
			Expression elt = ctx.testOrStar_expr().accept(new ExpressionVisitor());
			List<Comprehension> generators = new ArrayList<>();
			
			for (int i = 0; i < ctx.comp_for().size(); i++) {
				generators.add(ctx.comp_for(i).accept(new ComprehensionVisitor()));
			}
			
			return new Generator(elt, generators);
		}
		else {
			List<Expression> elts = new ArrayList<>();
			
			elts.add(ctx.testOrStar_expr().accept(new ExpressionVisitor()));
			
			for (int i = 0; i < ctx.testOrStarList_expr().size(); i++) {
				elts.add(ctx.testOrStarList_expr(i).accept(new ExpressionVisitor()));
			}
			
			return new Tuple(elts);
		}
	}
	
	@Override
	public Expression visitList(ListContext ctx) {
		if (ctx.testlist_compList() == null) {
			return new ListExpr(null);
		}
		else {
			return ctx.testlist_compList().accept(new ExpressionVisitor());
		}
	}
	
	@Override
	public Expression visitTestlist_compList(Testlist_compListContext ctx) {
		if (ctx.comp_for() != null && !ctx.comp_for().isEmpty()) {
			Expression elt = ctx.testOrStar_expr().accept(new ExpressionVisitor());
			List<Comprehension> comprehensions = new ArrayList<>();
			
			for (int i = 0; i < ctx.comp_for().size(); i++) {
				comprehensions.add(ctx.comp_for(i).accept(new ComprehensionVisitor()));
			}
			
			return new ListComp(elt, comprehensions);
		}
		else {
			List<Expression> elts = new ArrayList<>();
			
			elts.add(ctx.testOrStar_expr().accept(new ExpressionVisitor()));
			
			for (int i = 0; i < ctx.testOrStarList_expr().size(); i++) {
				elts.add(ctx.testOrStarList_expr(i).accept(new ExpressionVisitor()));
			}
			
			return new ListExpr(elts);
		}
	}
	
	@Override
	public Expression visitDictionaryOrSet(DictionaryOrSetContext ctx) {
		if (ctx.dictorsetmaker() == null) {
			return new Dict(null, null);
		}
		else {
			return ctx.dictorsetmaker().accept(new ExpressionVisitor());
		}
	}
	
	@Override
	public Expression visitDictorsetmaker(DictorsetmakerContext ctx) {
		if (ctx.dictFirst() != null) {
			
			if ((ctx.comp_for() == null || ctx.comp_for().isEmpty())
					&& (ctx.dicts() == null || ctx.dicts().isEmpty())) {
				return ctx.dictFirst().accept(new ExpressionVisitor());
			}
			
			if (ctx.comp_for() != null && !ctx.comp_for().isEmpty()) {
				Expression key = null;
				Expression value = null;
				List<Comprehension> comprehensions = new ArrayList<>();
				
				if (ctx.dictFirst().test() != null && !ctx.dictFirst().test().isEmpty()) {
					key = ctx.dictFirst().test(0).accept(new ExpressionVisitor());
					value = ctx.dictFirst().test(1).accept(new ExpressionVisitor());
				}
				else if (ctx.dictFirst().expr() != null) {
					key = null;
					value = ctx.dictFirst().expr().accept(new ExpressionVisitor());
				}
				
				for (int i = 0; i < ctx.comp_for().size(); i++) {
					comprehensions.add(ctx.comp_for(i).accept(new ComprehensionVisitor()));
				}
				
				return new DictComp(key, value, comprehensions);
			}
			
			if (ctx.dicts() != null && !ctx.dicts().isEmpty()) {
				List<Expression> keys = new ArrayList<>();
				List<Expression> values = new ArrayList<>();
				
				if (ctx.dictFirst().test() != null && !ctx.dictFirst().test().isEmpty()) {
					keys.add(ctx.dictFirst().test(0).accept(new ExpressionVisitor()));
					values.add(ctx.dictFirst().test(1).accept(new ExpressionVisitor()));
				}
				if (ctx.dictFirst().expr() != null) {
					keys.add(null);
					values.add(ctx.dictFirst().expr().accept(new ExpressionVisitor()));
				}
				
				for (int i = 0; i < ctx.dicts().size(); i++) {
					if (ctx.dicts(i).test() != null && !ctx.dicts(i).test().isEmpty()) {
						keys.add(ctx.dicts(i).test(0).accept(new ExpressionVisitor()));
						values.add(ctx.dicts(i).test(1).accept(new ExpressionVisitor()));
					}
					else if (ctx.dicts(i).expr() != null) {
						keys.add(null);
						values.add(ctx.dicts(i).expr().accept(new ExpressionVisitor()));
					}
				}
				
				return new Dict(keys, values);
			}
		}
		
		if (ctx.setFirst() != null) {
			
			if ((ctx.comp_for() == null || ctx.comp_for().isEmpty()) && 
					(ctx.sets() == null || ctx.sets().isEmpty())) {
				return ctx.setFirst().accept(new ExpressionVisitor());
			}
			
			if (ctx.comp_for() != null && !ctx.comp_for().isEmpty()) {
				Expression elt = null;
				List<Comprehension> comprehensions = new ArrayList<>();
				
				if (ctx.setFirst().test() != null) {
					elt = ctx.setFirst().test().accept(new ExpressionVisitor());
				}
				else if (ctx.setFirst().star_expr() != null) {
					elt = ctx.setFirst().star_expr().accept(new ExpressionVisitor());
				}
				
				for (int i = 0; i < ctx.comp_for().size(); i++) {
					comprehensions.add(ctx.comp_for(i).accept(new ComprehensionVisitor()));
				}
				
				return new SetComp(elt, comprehensions);
			}
			
			if (ctx.sets() != null && !ctx.sets().isEmpty()) {
				List<Expression> elts = new ArrayList<>();
				if (ctx.setFirst().test() != null) {
					elts.add(ctx.setFirst().test().accept(new ExpressionVisitor()));
				}
				if (ctx.setFirst().star_expr() != null) {
					elts.add(ctx.setFirst().star_expr().accept(new ExpressionVisitor()));
				}
				
				for (int i = 0; i < ctx.sets().size(); i++) {
					if (ctx.sets(i).test() != null) {
						elts.add(ctx.sets(i).test().accept(new ExpressionVisitor()));
					}
					else if (ctx.sets(i).star_expr() != null) {
						elts.add(ctx.sets(i).star_expr().accept(new ExpressionVisitor()));
					}
				}
				return new Set(elts);
			}
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitDictFirst(DictFirstContext ctx) {
		List<Expression> keys = new ArrayList<>();
		List<Expression> values = new ArrayList<>();
		
		if (ctx.test() != null && !ctx.test().isEmpty()) {
			keys.add(ctx.test(0).accept(new ExpressionVisitor()));
			values.add(ctx.test(1).accept(new ExpressionVisitor()));
		}
		if (ctx.expr() != null) {
			keys.add(null);
			values.add(ctx.expr().accept(new ExpressionVisitor()));
		}

		return new Dict(keys, values);
	}
	
	@Override
	public Expression visitSetFirst(SetFirstContext ctx) {
		List<Expression> elts = new ArrayList<>();
		if (ctx.test() != null) {
			elts.add(ctx.test().accept(new ExpressionVisitor()));
		}
		if (ctx.star_expr() != null) {
			elts.add(ctx.star_expr().accept(new ExpressionVisitor()));
		}
		return new Set(elts);
	}
	
	@Override
	public Expression visitExprlist(ExprlistContext ctx) {
		if (ctx.exprOrStarExpr().size() == 1) {
			return ctx.exprOrStarExpr(0).accept(new ExpressionVisitor());
		}
		
		List<Expression> expressions = new ArrayList<>();
		for (int i = 0; i < ctx.exprOrStarExpr().size(); i++) {
			expressions.add(ctx.exprOrStarExpr(i).accept(new ExpressionVisitor()));
		}
		
		return new ExpressionsList(expressions);
	}
	
	@Override
	public Expression visitExprOrStarExpr(ExprOrStarExprContext ctx) {
		if (ctx.expr() != null) {
			return ctx.expr().accept(new ExpressionVisitor());
		}
		if (ctx.star_expr() != null) {
			return ctx.star_expr().accept(new ExpressionVisitor());
		}
		
		throw new RuleException();
	}
	
	@Override
	public Expression visitTestlist(TestlistContext ctx) {
		if (ctx.test().size() == 1) {
			return ctx.test(0).accept(new ExpressionVisitor());
		}
		
		List<Expression> expressions = new ArrayList<>();
		for (int i = 0; i < ctx.test().size(); i++) {
			expressions.add(ctx.test(i).accept(new ExpressionVisitor()));
		}
		
		return new ExpressionsList(expressions);
	}
	
	@Override
	public Expression visitSubscriptlist(SubscriptlistContext ctx) {
		SliceAbstract slice;
		
		if (ctx.subscript().size() == 1) {
			slice = ctx.subscript(0).accept(new SliceVisitor());
		}
		else {
			List<SliceAbstract> dims = new ArrayList<>();
			
			for (int i = 0; i < ctx.subscript().size(); i++) {
				dims.add(ctx.subscript(i).accept(new SliceVisitor()));
			}
			
			slice = new ExtSlice(dims);
		}
		
		return new Subscript(slice);
	}
	
	@Override
	public Expression visitSlicelLower(SlicelLowerContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		
		return null;
	}
	
	@Override
	public Expression visitSliceUpper(SliceUpperContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		
		return null;
	}
	
	@Override
	public Expression visitSliceStep(SliceStepContext ctx) {
		if (ctx.test() != null) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		
		return null;
	}
	
	@Override
	public Expression visitArglist(ArglistContext ctx) {
		List<Expression> args = new ArrayList<>();
		List<Keyword> keywords = new ArrayList<>();
		List<Expression> starredArgs = new ArrayList<>();
		List<Keyword> doubleStarredArgs = new ArrayList<>();
		
		for (int i = 0; i < ctx.argument().size(); i++) {
			ArgumentContext argumentContext = ctx.argument(i);
			
			if (argumentContext.argumentNormal() != null) {
				args.add(argumentContext.argumentNormal().accept(new ExpressionVisitor()));
			}
			if (argumentContext.argumentKeyword() != null) {
				keywords.add(argumentContext.argumentKeyword().accept(new KeywordVisitor()));
			}
			if (argumentContext.argumentStar() != null) {
				starredArgs.add(argumentContext.argumentStar().accept(new ExpressionVisitor()));
			}
			if (argumentContext.argumentDoubleStar() != null) {
				doubleStarredArgs.add(argumentContext.argumentDoubleStar().accept(new KeywordVisitor()));
			}
		}
		
		return new Arguments(args, keywords, starredArgs, doubleStarredArgs);
	}
	
	@Override
	public Expression visitArgumentNormal(ArgumentNormalContext ctx) {
		if (ctx.comp_for() == null || ctx.comp_for().isEmpty()) {
			return ctx.test().accept(new ExpressionVisitor());
		}
		
		Expression elt = ctx.test().accept(new ExpressionVisitor());
		List<Comprehension> comprehensions = new ArrayList<>();
		
		for (int i = 0; i < ctx.comp_for().size(); i++) {
			comprehensions.add(ctx.comp_for(i).accept(new ComprehensionVisitor()));
		}
		
		return new ArgumentComp(elt, comprehensions);
	}
	
	@Override
	public Expression visitArgumentStar(ArgumentStarContext ctx) {
		return ctx.test().accept(new ExpressionVisitor());
	}

	@Override
	public Expression visitComp_if(Comp_ifContext ctx) {
		return ctx.test_nocond().accept(new ExpressionVisitor());
	}

	@Override
	public Expression visitYield_expr(Yield_exprContext ctx) {
		if (ctx.yield_arg() != null) {
			return ctx.yield_arg().accept(new ExpressionVisitor());
		}
		return new Yield(null);
	}

	@Override
	public Expression visitYield_arg(Yield_argContext ctx) {
		if (ctx.test() != null) {
			Expression expression = ctx.test().accept(new ExpressionVisitor());
			UnaryOp yieldFrom = new YieldFrom(expression);
			expression.setParent(yieldFrom);
			return yieldFrom;
		}
		if (ctx.testlist() != null) {
			Expression expression = ctx.testlist().accept(new ExpressionVisitor());
			Yield yield = new Yield(expression);
			expression.setParent(yield);
			return yield;
		}
		
		throw new RuleException();
	}
}
