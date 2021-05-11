package io.github.danielnaczo.python3parser.visitors.prettyprint;

import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;
import io.github.danielnaczo.python3parser.visitors.exceptions.UnsupportedASTException;
import io.github.danielnaczo.python3parser.model.AST;
import io.github.danielnaczo.python3parser.model.Identifier;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.And;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.boolops.Or;
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
import io.github.danielnaczo.python3parser.model.expr.datastructures.Dict;
import io.github.danielnaczo.python3parser.model.expr.datastructures.ListExpr;
import io.github.danielnaczo.python3parser.model.expr.datastructures.Set;
import io.github.danielnaczo.python3parser.model.expr.datastructures.Tuple;
import io.github.danielnaczo.python3parser.model.expr.generators.Generator;
import io.github.danielnaczo.python3parser.model.mods.ExpressionMod;
import io.github.danielnaczo.python3parser.model.mods.Interactive;
import io.github.danielnaczo.python3parser.model.mods.Mod;
import io.github.danielnaczo.python3parser.model.mods.Module;
import io.github.danielnaczo.python3parser.model.expr.operators.IfExpr;
import io.github.danielnaczo.python3parser.model.expr.operators.Lambda;
import io.github.danielnaczo.python3parser.model.expr.operators.Operator;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Add;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.At;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BinOp;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitAnd;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitOr;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.BitXor;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Div;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.FloorDiv;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.LShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mult;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Pow;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.RShift;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Sub;
import io.github.danielnaczo.python3parser.model.expr.operators.binaryops.comparisons.Cmpop;
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
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.Index;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.Subscript;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.ExtSlice;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.Slice;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.Comprehension;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.DictComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.ListComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.SetComp;
import io.github.danielnaczo.python3parser.model.stmts.Body;
import io.github.danielnaczo.python3parser.model.stmts.Statement;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.ClassDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.If;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.While;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.forStmts.AsyncFor;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.forStmts.For;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.AsyncFunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
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

public class GenericUnsupportedASTVisitor<R, P> implements Python3ASTVisitor<R, P> {

	@Override
	public R visitParameter(Parameter arg, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAwait(Await await, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDefaultParameter(DefaultParameter defaultArg, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAnd(And and, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitOr(Or or, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitCmpop(Cmpop cmpop, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitEq(Eq eq, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitGt(Gt gt, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitGtE(GtE gte, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIn(In in, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIs(Is is, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIsNot(IsNot isNot, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitLt(Lt lt, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitLtE(LtE lte, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNotEq(NotEq notEq, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNotIn(NotIn notIn, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitArguments(Arguments arguments, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitArgumentComp(ArgumentComp argumentComp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAtom(Atom atom, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAttribute(Attribute attribute, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBinOp(BinOp binOp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDecorator(Decorator decorator, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDict(Dict dict, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDictComp(DictComp dictComp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitEllipsis(Ellipsis ellipsis, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitExpression(Expression expression, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitExpressionList(ExpressionsList expressionList, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitFalse(False falseElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIfExpr(IfExpr ifExpr, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitJoinedStr(JoinedStr joinedStr, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitLambda(Lambda lambda, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitListComp(ListComp listComp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitListExpr(ListExpr listExpr, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitName(Name name, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNone(None none, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNum(Num num, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSet(Set set, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSetComp(SetComp setComp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitStarred(Starred starred, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitStr(Str arg, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSubscript(Subscript subscript, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitTrue(True trueElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitTuple(Tuple tuple, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitGenerator(Generator generator, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitUnaryOp(UnaryOp unaryOp, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitYield(Yield yield, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitYieldFrom(YieldFrom yieldFrom, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitExpressionMod(ExpressionMod expressionMod, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitInteractive(Interactive interactive, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitMod(Mod mod, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitModule(Module module, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAdd(Add add, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAt(At at, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBitAnd(BitAnd bitAnd, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBitOr(BitOr bitOr, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBitXor(BitXor bitXor, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDiv(Div div, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitFloorDiv(FloorDiv floorDiv, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitLShift(LShift lShift, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitModulo(io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mod modulo, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitMult(Mult mult, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitOperator(Operator operator, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitPow(Pow pow, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitRShift(RShift rShift, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSub(Sub sub, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitExtSlice(ExtSlice extSlice, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIndex(Index index, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSlice(Slice slice, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitSliceAbstract(SliceAbstract sliceAbstract, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAnnAssign(AnnAssign annAssign, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAssert(Assert assertElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAssign(Assign assign, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAsyncFor(AsyncFor asyncFor, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAsyncFunctionDef(AsyncFunctionDef asyncFunctionDef, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAsyncWith(AsyncWith asyncWith, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAugAssign(AugAssign augAssign, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBody(Body body, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitBreak(Break breakElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitClassDef(ClassDef classDef, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitContinue(Continue continueElements, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitDelete(Delete delete, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitFor(For forElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitFunctionDef(FunctionDef functionDef, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitGlobal(Global global, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIf(If ifElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitImport(Import importElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitImportFrom(ImportFrom importFrom, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNonlocal(Nonlocal nonlocal, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitParameters(Parameters parameters, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitPass(Pass pass, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitRaise(Raise raise, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitReturn(Return returnElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitStatement(Statement statement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitTry(Try tryElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitWhile(While whileElement, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitWith(With with, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitInvert(Invert invert, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitNot(Not not, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitUAdd(UAdd uAdd, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitUSub(USub uSub, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAlias(Alias alias, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitAST(AST ast, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitComprehension(Comprehension comprehension, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitExceptHandler(ExceptHandler exceptHandler, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitIdentifier(Identifier identifier, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitKeyword(Keyword keyword, P param) {
		throw new UnsupportedASTException();
	}

	@Override
	public R visitWithItem(WithItem withItem, P param) {
		throw new UnsupportedASTException();
	}
	
}
