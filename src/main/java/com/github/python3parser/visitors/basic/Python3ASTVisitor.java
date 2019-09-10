package com.github.python3parser.visitors.basic;

import com.github.python3parser.model.AST;
import com.github.python3parser.model.Identifier;
import com.github.python3parser.model.expr.operators.binaryops.boolops.And;
import com.github.python3parser.model.expr.operators.binaryops.boolops.Or;
import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.model.expr.ExpressionsList;
import com.github.python3parser.model.expr.atoms.Atom;
import com.github.python3parser.model.expr.atoms.Ellipsis;
import com.github.python3parser.model.expr.atoms.False;
import com.github.python3parser.model.expr.atoms.JoinedStr;
import com.github.python3parser.model.expr.atoms.Name;
import com.github.python3parser.model.expr.atoms.None;
import com.github.python3parser.model.expr.atoms.Num;
import com.github.python3parser.model.expr.atoms.Str;
import com.github.python3parser.model.expr.atoms.True;
import com.github.python3parser.model.expr.atoms.trailers.Attribute;
import com.github.python3parser.model.expr.atoms.trailers.arguments.ArgumentComp;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Arguments;
import com.github.python3parser.model.expr.atoms.trailers.arguments.Keyword;
import com.github.python3parser.model.expr.datastructures.Dict;
import com.github.python3parser.model.expr.datastructures.ListExpr;
import com.github.python3parser.model.expr.datastructures.Set;
import com.github.python3parser.model.expr.datastructures.Tuple;
import com.github.python3parser.model.expr.generators.Generator;
import com.github.python3parser.model.mods.ExpressionMod;
import com.github.python3parser.model.mods.Interactive;
import com.github.python3parser.model.mods.Mod;
import com.github.python3parser.model.mods.Module;
import com.github.python3parser.model.expr.operators.IfExpr;
import com.github.python3parser.model.expr.operators.Lambda;
import com.github.python3parser.model.expr.operators.Operator;
import com.github.python3parser.model.expr.operators.binaryops.Add;
import com.github.python3parser.model.expr.operators.binaryops.At;
import com.github.python3parser.model.expr.operators.binaryops.BinOp;
import com.github.python3parser.model.expr.operators.binaryops.BitAnd;
import com.github.python3parser.model.expr.operators.binaryops.BitOr;
import com.github.python3parser.model.expr.operators.binaryops.BitXor;
import com.github.python3parser.model.expr.operators.binaryops.Div;
import com.github.python3parser.model.expr.operators.binaryops.FloorDiv;
import com.github.python3parser.model.expr.operators.binaryops.LShift;
import com.github.python3parser.model.expr.operators.binaryops.Mult;
import com.github.python3parser.model.expr.operators.binaryops.Pow;
import com.github.python3parser.model.expr.operators.binaryops.RShift;
import com.github.python3parser.model.expr.operators.binaryops.Sub;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.Cmpop;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.Eq;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.Gt;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.GtE;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.In;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.Is;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.IsNot;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.Lt;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.LtE;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.NotEq;
import com.github.python3parser.model.expr.operators.binaryops.comparisons.NotIn;
import com.github.python3parser.model.expr.operators.unaryops.Await;
import com.github.python3parser.model.expr.operators.unaryops.Invert;
import com.github.python3parser.model.expr.operators.unaryops.Not;
import com.github.python3parser.model.expr.operators.unaryops.Starred;
import com.github.python3parser.model.expr.operators.unaryops.UAdd;
import com.github.python3parser.model.expr.operators.unaryops.USub;
import com.github.python3parser.model.expr.operators.unaryops.UnaryOp;
import com.github.python3parser.model.expr.operators.unaryops.Yield;
import com.github.python3parser.model.expr.operators.unaryops.YieldFrom;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.Index;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.Subscript;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.ExtSlice;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.Slice;
import com.github.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import com.github.python3parser.model.expr.comprehensions.Comprehension;
import com.github.python3parser.model.expr.comprehensions.DictComp;
import com.github.python3parser.model.expr.comprehensions.ListComp;
import com.github.python3parser.model.expr.comprehensions.SetComp;
import com.github.python3parser.model.stmts.Body;
import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.model.stmts.compoundStmts.ClassDef;
import com.github.python3parser.model.stmts.compoundStmts.If;
import com.github.python3parser.model.stmts.compoundStmts.While;
import com.github.python3parser.model.stmts.compoundStmts.forStmts.AsyncFor;
import com.github.python3parser.model.stmts.compoundStmts.forStmts.For;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.AsyncFunctionDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.Decorator;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.FunctionDef;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.ExceptHandler;
import com.github.python3parser.model.stmts.compoundStmts.tryExceptStmts.Try;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.AsyncWith;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.With;
import com.github.python3parser.model.stmts.compoundStmts.withStmts.WithItem;
import com.github.python3parser.model.stmts.flowStmts.Break;
import com.github.python3parser.model.stmts.flowStmts.Continue;
import com.github.python3parser.model.stmts.flowStmts.Raise;
import com.github.python3parser.model.stmts.flowStmts.Return;
import com.github.python3parser.model.stmts.flowStmts.YieldStmt;
import com.github.python3parser.model.stmts.importStmts.Alias;
import com.github.python3parser.model.stmts.importStmts.Import;
import com.github.python3parser.model.stmts.importStmts.ImportFrom;
import com.github.python3parser.model.stmts.smallStmts.Assert;
import com.github.python3parser.model.stmts.smallStmts.Delete;
import com.github.python3parser.model.stmts.smallStmts.Global;
import com.github.python3parser.model.stmts.smallStmts.Nonlocal;
import com.github.python3parser.model.stmts.smallStmts.Pass;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.AnnAssign;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.Assign;
import com.github.python3parser.model.stmts.smallStmts.assignStmts.AugAssign;

public interface Python3ASTVisitor<R, P> {
	
	R visitParameter(Parameter parameter, P param);
	
	R visitParameters(Parameters parameters, P param);
	
	R visitAwait(Await await, P param);
	
	R visitDefaultParameter(DefaultParameter defaultParameter, P param);
	
	R visitAnd(And and, P param);
	
	R visitOr(Or or, P param);
	
	R visitCmpop(Cmpop cmpop, P param);
	
	R visitEq(Eq eq, P param);
	
	R visitGt(Gt gt, P param);
	
	R visitGtE(GtE gte, P param);
	
	R visitIn(In in, P param);
	
	R visitIs(Is is, P param);
	
	R visitIsNot(IsNot isNot, P param);
	
	R visitLt(Lt lt, P param);
	
	R visitLtE(LtE lte, P param);
	
	R visitNotEq(NotEq notEq, P param);
	
	R visitNotIn(NotIn notIn, P param);
	
	R visitArguments(Arguments arguments, P param);
	
	R visitArgumentComp(ArgumentComp argumentComp, P param);
	
	R visitAtom(Atom atom, P param);
	
	R visitAttribute(Attribute attribute, P param);
	
	R visitBinOp(BinOp binOp, P param);
	
	R visitDecorator(Decorator decorator, P param);
	
	R visitDict(Dict dict, P param);
	
	R visitDictComp(DictComp dictComp, P param);
	
	R visitEllipsis(Ellipsis ellipsis, P param);
	
	R visitExpression(Expression expression, P param);
	
	R visitExpressionList(ExpressionsList expressionList, P param);
	
	R visitFalse(False falseElement, P param);
	
	R visitIfExpr(IfExpr ifExpr, P param);
	
	R visitJoinedStr(JoinedStr joinedStr, P param);
	
	R visitLambda(Lambda lambda, P param);
	
	R visitListComp(ListComp listComp, P param);
	
	R visitListExpr(ListExpr listExpr, P param);
	
	R visitName(Name name, P param);
	
	R visitNone(None none, P param);
	
	R visitNum(Num num, P param);
	
	R visitSet(Set set, P param);
	
	R visitSetComp(SetComp setComp, P param);
	
	R visitStarred(Starred starred, P param);
	
	R visitStr(Str arg, P param);
	
	R visitSubscript(Subscript subscript, P param);
	
	R visitTrue(True trueElement, P param);
	
	R visitTuple(Tuple tuple, P param);
	
	R visitGenerator(Generator generator, P param);
	
	R visitUnaryOp(UnaryOp unaryOp, P param);
	
	R visitYield(Yield yield, P param);
	
	R visitYieldFrom(YieldFrom yieldFrom, P param);
	
	R visitExpressionMod(ExpressionMod expressionMod, P param);
	
	R visitInteractive(Interactive interactive, P param);
	
	R visitMod(Mod mod, P param);
	
	R visitModule (Module module, P param);
	
	R visitAdd(Add add, P param);
	
	R visitAt(At at, P param);
	
	R visitBitAnd(BitAnd bitAnd, P param);
	
	R visitBitOr(BitOr bitOr, P param);
	
	R visitBitXor(BitXor bitXor, P param);
	
	R visitDiv(Div div, P param);
	
	R visitFloorDiv(FloorDiv floorDiv, P param);
	
	R visitLShift(LShift lShift, P param);
	
	R visitModulo(com.github.python3parser.model.expr.operators.binaryops.Mod modulo, P param);
	
	R visitMult(Mult mult, P param);
	
	R visitOperator(Operator operator, P param);
	
	R visitPow(Pow pow, P param);
	
	R visitRShift(RShift rShift, P param);
	
	R visitSub(Sub sub, P param);
	
	R visitExtSlice(ExtSlice extSlice, P param);
	
	R visitIndex(Index index, P param);
	
	R visitSlice(Slice slice, P param);
	
	R visitSliceAbstract(SliceAbstract sliceAbstract, P param);
	
	R visitAnnAssign(AnnAssign annAssign, P param);
	
	R visitAssert(Assert assertElement, P param);
	
	R visitAssign(Assign assign, P param);
	
	R visitAsyncFor(AsyncFor asyncFor, P param);
	
	R visitAsyncFunctionDef(AsyncFunctionDef asyncFunctionDef, P param);
	
	R visitAsyncWith(AsyncWith asyncWith, P param);
	
	R visitAugAssign(AugAssign augAssign, P param);
	
	R visitBody(Body body, P param);
	
	R visitBreak(Break breakElement, P param);
	
	R visitClassDef(ClassDef classDef, P param);
	
	R visitContinue(Continue continueElements, P param);
	
	R visitDelete(Delete delete, P param);
	
	R visitFor(For forElement, P param);
	
	R visitFunctionDef(FunctionDef functionDef, P param);
	
	R visitGlobal(Global global, P param);
	
	R visitIf(If ifElement, P param);
	
	R visitImport (Import importElement, P param);
	
	R visitImportFrom (ImportFrom importFrom, P param);
	
	R visitNonlocal(Nonlocal nonlocal, P param);
	
	R visitPass(Pass pass, P param);
	
	R visitRaise(Raise raise, P param);
	
	R visitReturn(Return returnElement, P param);
	
	R visitStatement(Statement statement, P param);
	
	R visitTry(Try tryElement, P param);
	
	R visitWhile(While whileElement, P param);
	
	R visitWith(With with, P param);
	
	R visitYieldStmt(YieldStmt yieldStmt, P param);
	
	R visitInvert(Invert invert, P param);
	
	R visitNot(Not not, P param);
	
	R visitUAdd(UAdd uAdd, P param);
	
	R visitUSub(USub uSub, P param);
	
	R visitAlias (Alias alias, P param);
	
	R visitAST (AST ast, P param);
	
	R visitComprehension(Comprehension comprehension, P param);
	
	R visitExceptHandler(ExceptHandler exceptHandler, P param);
	
	R visitIdentifier(Identifier identifier, P param);
	
	R visitKeyword(Keyword keyword, P param);
	
	R visitWithItem(WithItem withItem, P param);
	
}
