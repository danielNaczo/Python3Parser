package io.github.danielnaczo.python3parser.visitors.modifier;

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
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModifierVisitor<P> implements Python3ASTVisitor<AST, P> {

	@Override
	public AST visitParameter(Parameter parameterClass, P param) {
		Identifier parameterName = (Identifier) parameterClass.getParameterName().accept(this, param);;
		Optional<Expression> annotation = modifyOptional(parameterClass.getAnnotation(), param);
		parameterClass.setParameterName(parameterName);
		parameterClass.setAnnotation(annotation);
		return parameterClass;
	}

	@Override
	public AST visitParameters(Parameters parametersClass, P param) {
		List<Parameter> parameters = modifyList(parametersClass.getParams(), param);
		List<DefaultParameter> defaultParameters = modifyList(parametersClass.getDefaultParams(), param);
		Optional<Parameter> varParam = modifyOptional(parametersClass.getVarParam(), param);
		List<Parameter> kwonlyParams = modifyList(parametersClass.getKwonlyParams(), param);
		List<DefaultParameter> kwDefaultParams = modifyList(parametersClass.getKwDefaultParams(), param);
		Optional<Parameter> kwParam = modifyOptional(parametersClass.getKwParam(), param);
		parametersClass.setParams(parameters);
		parametersClass.setDefaultParams(defaultParameters);
		parametersClass.setVarParam(varParam);
		parametersClass.setKwonlyParams(kwonlyParams);
		parametersClass.setKwDefaultParams(kwDefaultParams);
		parametersClass.setKwParam(kwParam);
		return parametersClass;
	}
	
	@Override
	public AST visitAwait(Await await, P param) {
		return visitUnaryOp(await, param);
	}

	@Override
	public AST visitDefaultParameter(DefaultParameter defaultParameter, P param) {
		Parameter parameter = (Parameter) defaultParameter.getParameter().accept(this, param);
		Expression value = (Expression) defaultParameter.getValue().accept(this, param);
		defaultParameter.setParameter(parameter);
		defaultParameter.setValue(value);
		return defaultParameter;
	}

	@Override
	public AST visitAnd(And and, P param) {
		return visitBinOp(and, param);
	}

	@Override
	public AST visitOr(Or or, P param) {
		return visitBinOp(or, param);
	}

	@Override
	public AST visitCmpop(Cmpop cmpop, P param) {
		return visitBinOp(cmpop, param);
	}

	@Override
	public AST visitEq(Eq eq, P param) {
		return visitCmpop(eq, param);
	}

	@Override
	public AST visitGt(Gt gt, P param) {
		return visitCmpop(gt, param);
	}

	@Override
	public AST visitGtE(GtE gte, P param) {
		return visitCmpop(gte, param);
	}

	@Override
	public AST visitIn(In in, P param) {
		return visitCmpop(in, param);
	}

	@Override
	public AST visitIs(Is is, P param) {
		return visitCmpop(is, param);
	}

	@Override
	public AST visitIsNot(IsNot isNot, P param) {
		return visitCmpop(isNot, param);
	}

	@Override
	public AST visitLt(Lt lt, P param) {
		return visitCmpop(lt, param);
	}

	@Override
	public AST visitLtE(LtE lte, P param) {
		return visitCmpop(lte, param);
	}

	@Override
	public AST visitNotEq(NotEq notEq, P param) {
		return visitCmpop(notEq, param);
	}

	@Override
	public AST visitNotIn(NotIn notIn, P param) {
		return visitCmpop(notIn, param);
	}

	@Override
	public AST visitArguments(Arguments arguments, P param) {
		List<Expression> args = modifyList(arguments.getArgs(), param);
		List<Keyword> keywords = modifyList(arguments.getKeywords(), param);
		List<Expression> starredArgs = modifyList(arguments.getStarredArgs(), param);	
		List<Keyword> doubleStarredArgs = modifyList(arguments.getDoubleStarredArgs(), param);
		arguments.setArgs(args);
		arguments.setKeywords(keywords);
		arguments.setStarredArgs(starredArgs);
		arguments.setDoubleStarredArgs(doubleStarredArgs);
		return arguments;
	}

	@Override
	public AST visitArgumentComp(ArgumentComp argumentComp, P param) {
		Expression elt = (Expression) argumentComp.getElt().accept(this, param);
		List<Comprehension> comprehensions = modifyList(argumentComp.getComprehensions(), param);
		argumentComp.setElt(elt);
		argumentComp.setComprehensions(comprehensions);
		return argumentComp;
	}

	@Override
	public AST visitAtom(Atom atom, P param) {
		Expression atomElement = (Expression) atom.getAtomElement().accept(this, param);
		List<Expression> trailers = modifyList(atom.getTrailers(), param);
		atom.setAtomElement(atomElement);
		atom.setTrailers(trailers);
		return atom;
	}

	@Override
	public AST visitAttribute(Attribute attribute, P param) {
		Identifier attr = (Identifier) attribute.getAttr().accept(this, param);
		attribute.setAttr(attr);
		return attribute;
	}

	@Override
	public AST visitBinOp(BinOp binOp, P param) {
		//"left" and "right" are null, if "binOp" comes from a an AugAssign (e.g. target += 5)
		Expression left = (binOp.getLeft() != null) ? 
				(Expression) binOp.getLeft().accept(this, param) : null;
		Expression right = (binOp.getRight() != null) ? 
				(Expression) binOp.getRight().accept(this, param) : null;
		Expression parent = (binOp.getParent() != null) ? 
				(Expression) binOp.getParent() : null;
		binOp.setLeft(left);
		binOp.setRight(right);
		binOp.setParent(parent);
		return binOp;
	}

	@Override
	public AST visitDecorator(Decorator decorator, P param) {
		Identifier name = (Identifier) decorator.getName().accept(this, param);
		Optional<Expression> arguments = modifyOptional(decorator.getArguments(), param);
		decorator.setName(name);
		decorator.setArguments(arguments);
		return decorator;
	}

	@Override
	public AST visitDict(Dict dict, P param) {
		List<Expression> keys = modifyList(dict.getKeys(), param);
		List<Expression> values = modifyList(dict.getValues(), param);
		dict.setKeys(keys);
		dict.setValues(values);
		return dict;
	}

	@Override
	public AST visitDictComp(DictComp dictComp, P param) {
		Expression key = (Expression) dictComp.getKey().accept(this, param);
		Expression value = (Expression) dictComp.getValue().accept(this, param);
		List<Comprehension> comprehensions = modifyList(dictComp.getComprehensions(), param);
		dictComp.setKey(key);
		dictComp.setValue(value);
		dictComp.setComprehensions(comprehensions);
		return dictComp;
	}

	@Override
	public AST visitEllipsis(Ellipsis ellipsis, P param) {
		return ellipsis;
	}

	@Override
	public AST visitExpression(Expression expression, P param) {
		return expression.accept(this, param);
	}

	@Override
	public AST visitExpressionList(ExpressionsList expressionList, P param) {
		List<Expression> expressions = modifyList(expressionList.getExpressions(), param);
		expressionList.setExpressions(expressions);
		return expressionList;
	}

	@Override
	public AST visitFalse(False falseElement, P param) {
		return falseElement;
	}

	@Override
	public AST visitIfExpr(IfExpr ifExpr, P param) {
		Expression test = (Expression) ifExpr.getTest().accept(this, param);
		Expression body = (Expression) ifExpr.getBody().accept(this, param);
		Expression orElse = (Expression) ifExpr.getOrElse().accept(this, param);
		Expression parent = (ifExpr.getParent() != null) ? 
				(Expression) ifExpr.getParent() : null;
		ifExpr.setTest(test);
		ifExpr.setBody(body);
		ifExpr.setOrElse(orElse);
		ifExpr.setParent(parent);
		return ifExpr;
	}

	@Override
	public AST visitJoinedStr(JoinedStr joinedStr, P param) {
		List<Expression> values = modifyList(joinedStr.getValues(), param);
		joinedStr.setValues(values);
		return joinedStr;
	}

	@Override
	public AST visitLambda(Lambda lambda, P param) {
		Optional<Parameters> args = modifyOptional(lambda.getArgs(), param);
		Expression body = (Expression) lambda.getBody().accept(this, param);
		Expression parent = (lambda.getParent() != null) ? 
				(Expression) lambda.getParent() : null;
		lambda.setArgs(args);
		lambda.setBody(body);
		lambda.setParent(parent);
		return lambda;
	}

	@Override
	public AST visitListComp(ListComp listComp, P param) {
		Expression elt = (Expression) listComp.getElt().accept(this, param);
		List<Comprehension> comprehensions = modifyList(listComp.getComprehensions(), param);
		listComp.setElt(elt);
		listComp.setComprehensions(comprehensions);
		return listComp;
	}

	@Override
	public AST visitListExpr(ListExpr listExpr, P param) {
		List<Expression> elts = modifyList(listExpr.getElts(), param);
		listExpr.setElts(elts);
		return listExpr;
	}

	@Override
	public AST visitName(Name name, P param) {
		Identifier id = (Identifier) name.getId().accept(this, param);
		name.setId(id);
		return name;
	}

	@Override
	public AST visitNone(None none, P param) {
		return none;
	}

	@Override
	public AST visitNum(Num num, P param) {
		String n = num.getN();
		num.setN(n);
		return num;
	}

	@Override
	public AST visitSet(Set set, P param) {
		List<Expression> elts = modifyList(set.getElts(), param);
		set.setElts(elts);
		return set;
	}

	@Override
	public AST visitSetComp(SetComp setComp, P param) {
		Expression elt = (Expression) setComp.getElt().accept(this, param);
		List<Comprehension> comprehensions = modifyList(setComp.getComprehensions(), param);
		setComp.setElt(elt);
		setComp.setComprehensions(comprehensions);
		return setComp;
	}

	@Override
	public AST visitStarred(Starred starred, P param) {
		return visitUnaryOp(starred, param);
	}

	@Override
	public AST visitStr(Str str, P param) {
		String s = str.getS();
		str.setS(s);
		return str;
	}

	@Override
	public AST visitSubscript(Subscript subscript, P param) {
		SliceAbstract slice = (SliceAbstract) subscript.getSlice().accept(this, param);
		subscript.setSlice(slice);
		return subscript;
	}

	@Override
	public AST visitTrue(True trueElement, P param) {
		return trueElement;
	}

	@Override
	public AST visitTuple(Tuple tuple, P param) {
		List<Expression> elts = modifyList(tuple.getElts(), param);
		tuple.setElts(elts);
		return tuple;
	}

	@Override
	public AST visitGenerator(Generator generator, P param) {
		Expression elt = (Expression) generator.getElt().accept(this, param);
		List<Comprehension> comprehensions = modifyList(generator.getComprehensions(), param);
		generator.setElt(elt);
		generator.setComprehensions(comprehensions);
		return generator;
	}

	@Override
	public AST visitUnaryOp(UnaryOp unaryOp, P param) {
		Expression expression = (Expression) unaryOp.getExpression().accept(this, param);
		Expression parent = unaryOp.getParent();
		unaryOp.setExpression(expression);
		unaryOp.setParent(parent);
		return unaryOp;
	}

	@Override
	public AST visitYield(Yield yield, P param) {
		Optional<Expression> value = modifyOptional(yield.getExpression(), param);
		Expression parent = (yield.getParent() != null) ? 
				(Expression) yield.getParent() : null;
		yield.setExpression(value);
		yield.setParent(parent);
		return yield;
	}

	@Override
	public AST visitYieldFrom(YieldFrom yieldFrom, P param) {
		return visitUnaryOp(yieldFrom, param);
	}

	@Override
	public AST visitExpressionMod(ExpressionMod expressionMod, P param) {
		Expression body = (Expression) expressionMod.getBody().accept(this, param);
		expressionMod.setBody(body);
		return expressionMod;
	}

	@Override
	public AST visitInteractive(Interactive interactive, P param) {
		Optional<Statement> body = modifyOptional(interactive.getBody(), param);
		interactive.setBody(body);
		return interactive;
	}

	@Override
	public AST visitMod(Mod mod, P param) {
		return mod.accept(this, param);
	}

	@Override
	public AST visitModule(Module module, P param) {
		List<Statement> statements = modifyList(module.getStatements(), param);
		module.setStatements(statements);
		return module;
	}

	@Override
	public AST visitAdd(Add add, P param) {
		return visitBinOp(add, param);
	}

	@Override
	public AST visitAt(At at, P param) {
		return visitBinOp(at, param);
	}

	@Override
	public AST visitBitAnd(BitAnd bitAnd, P param) {
		return visitBinOp(bitAnd, param);
	}

	@Override
	public AST visitBitOr(BitOr bitOr, P param) {
		return visitBinOp(bitOr, param);
	}

	@Override
	public AST visitBitXor(BitXor bitXor, P param) {
		return visitBinOp(bitXor, param);
	}

	@Override
	public AST visitDiv(Div div, P param) {
		return visitBinOp(div, param);
	}

	@Override
	public AST visitFloorDiv(FloorDiv floorDiv, P param) {
		return visitBinOp(floorDiv, param);
	}

	@Override
	public AST visitLShift(LShift lShift, P param) {
		return visitBinOp(lShift, param);
	}

	@Override
	public AST visitModulo(io.github.danielnaczo.python3parser.model.expr.operators.binaryops.Mod modulo, P param) {
		return visitBinOp(modulo, param);
	}

	@Override
	public AST visitMult(Mult mult, P param) {
		return visitBinOp(mult, param);
	}

	@Override
	public AST visitOperator(Operator operator, P param) {
		return operator.accept(this, param);
	}

	@Override
	public AST visitPow(Pow pow, P param) {
		return visitBinOp(pow, param);
	}

	@Override
	public AST visitRShift(RShift rShift, P param) {
		return visitBinOp(rShift, param);
	}

	@Override
	public AST visitSub(Sub sub, P param) {
		return visitBinOp(sub, param);
	}

	@Override
	public AST visitExtSlice(ExtSlice extSlice, P param) {
		List<SliceAbstract> dims = modifyList(extSlice.getDims(), param);
		extSlice.setDims(dims);
		return extSlice;
	}

	@Override
	public AST visitIndex(Index index, P param) {
		Expression value = (Expression) index.getValue().accept(this, param);
		index.setValue(value);
		return index;
	}

	@Override
	public AST visitSlice(Slice slice, P param) {
		Optional<Expression> lower = modifyOptional(slice.getLower(), param);
		Optional<Expression> upper = modifyOptional(slice.getUpper(), param);
		Optional<Expression> step = modifyOptional(slice.getStep(), param);
		slice.setLower(lower);
		slice.setUpper(upper);
		slice.setStep(step);
		return slice;
	}

	@Override
	public AST visitSliceAbstract(SliceAbstract sliceAbstract, P param) {
		return sliceAbstract.accept(this, param);
	}

	@Override
	public AST visitAnnAssign(AnnAssign annAssign, P param) {
		Expression target = (Expression) annAssign.getTarget().accept(this, param);
		Expression annotation = (Expression) annAssign.getAnnotation().accept(this, param);
		Optional<Expression> value = modifyOptional(annAssign.getValue(), param);
		annAssign.setTarget(target);
		annAssign.setAnnotation(annotation);
		annAssign.setValue(value);
		return annAssign;
	}

	@Override
	public AST visitAssert(Assert assertElement, P param) {
		Expression test = (Expression) assertElement.getTest().accept(this, param);
		Optional<Expression> msg = modifyOptional(assertElement.getMsg(), param);
		assertElement.setTest(test);
		assertElement.setMsg(msg);
		return assertElement;
	}

	@Override
	public AST visitAssign(Assign assign, P param) {
		List<Expression> targets = modifyList(assign.getTargets(), param);
		Optional<Expression> value = modifyOptional(assign.getValue(), param);
		assign.setTargets(targets);
		assign.setValue(value);
		return assign;
	}

	@Override
	public AST visitAsyncFor(AsyncFor asyncFor, P param) {
		Expression target = (Expression) asyncFor.getTarget().accept(this, param);
		Expression iter = (Expression) asyncFor.getIter().accept(this, param);
		Statement body = (Statement) asyncFor.getBody().accept(this, param);
		Optional<Statement> orElse = modifyOptional(asyncFor.getOrElse(), param);
		asyncFor.setTarget(target);
		asyncFor.setIter(iter);
		asyncFor.setBody(body);
		asyncFor.setOrElse(orElse);
		return asyncFor;
	}

	@Override
	public AST visitAsyncFunctionDef(AsyncFunctionDef asyncFunctionDef, P param) {
		Identifier name = (Identifier) asyncFunctionDef.getName().accept(this, param);
		Optional<Parameters> parameters = modifyOptional(asyncFunctionDef.getParameters(), param);
		Statement body = (Statement) asyncFunctionDef.getBody().accept(this, param);
		List<Decorator> decoratorList = modifyList(asyncFunctionDef.getDecoratorList(), param);
		Optional<Expression> returns = modifyOptional(asyncFunctionDef.getReturns(), param);
		asyncFunctionDef.setName(name);
		asyncFunctionDef.setParameters(parameters);
		asyncFunctionDef.setBody(body);
		asyncFunctionDef.setDecoratorList(decoratorList);
		asyncFunctionDef.setReturns(returns);
		return asyncFunctionDef;
	}

	@Override
	public AST visitAsyncWith(AsyncWith asyncWith, P param) {
		List<WithItem> items = modifyList(asyncWith.getItems(), param);
		Statement body = (Statement) asyncWith.getBody().accept(this, param);
		asyncWith.setItems(items);
		asyncWith.setBody(body);
		return asyncWith;
	}

	@Override
	public AST visitAugAssign(AugAssign augAssign, P param) {
		Expression target = (Expression) augAssign.getTarget().accept(this, param);
		Operator op = (Operator) augAssign.getOp().accept(this, param);
		Expression value = (Expression) augAssign.getValue().accept(this, param);
		augAssign.setTarget(target);
		augAssign.setOp(op);
		augAssign.setValue(value);
		return augAssign;
	}

	@Override
	public AST visitBody(Body body, P param) {
		List<Statement> statements = modifyList(body.getStatements(), param);
		Statement parentStmt = body.getParentStmt();
		body.setStatements(statements);
		body.setParentStmt(parentStmt);
		return body;
	}

	@Override
	public AST visitBreak(Break breakElement, P param) {
		return breakElement;
	}

	@Override
	public AST visitClassDef(ClassDef classDef, P param) {
		Identifier name = (Identifier) classDef.getName().accept(this, param);
		Optional<Expression> arguments = modifyOptional(classDef.getArguments(), param);
		Statement body = (Statement) classDef.getBody().accept(this, param);
		List<Decorator> decoratorList = modifyList(classDef.getDecoratorList(), param);
		classDef.setName(name);
		classDef.setArguments(arguments);
		classDef.setBody(body);
		classDef.setDecoratorList(decoratorList);
		return classDef;
	}

	@Override
	public AST visitContinue(Continue continueElement, P param) {
		return continueElement;
	}

	@Override
	public AST visitDelete(Delete delete, P param) {
		Expression expression = delete.getExpression();
		delete.setExpression(expression);
		return delete;
	}

	@Override
	public AST visitFor(For forElement, P param) {
		Expression target = (Expression) forElement.getTarget().accept(this, param);
		Expression iter = (Expression) forElement.getIter().accept(this, param);
		Statement body = (Statement) forElement.getBody().accept(this, param);
		Optional<Statement> orElse = modifyOptional(forElement.getOrElse(), param);
		forElement.setTarget(target);
		forElement.setIter(iter);
		forElement.setBody(body);
		forElement.setOrElse(orElse);
		return forElement;
	}

	@Override
	public AST visitFunctionDef(FunctionDef functionDef, P param) {
		Identifier name = (Identifier) functionDef.getName().accept(this, param);
		Optional<Parameters> parameters = modifyOptional(functionDef.getParameters(), param);
		Statement body = (Statement) functionDef.getBody().accept(this, param);
		List<Decorator> decoratorList = modifyList(functionDef.getDecoratorList(), param);
		Optional<Expression> returns = modifyOptional(functionDef.getReturns(), param);
		functionDef.setName(name);
		functionDef.setParameters(parameters);
		functionDef.setBody(body);
		functionDef.setDecoratorList(decoratorList);
		functionDef.setReturns(returns);
		return functionDef;
	}

	@Override
	public AST visitGlobal(Global global, P param) {
		List<Identifier> names = modifyList(global.getNames(), param);
		global.setNames(names);
		return global;
	}

	@Override
	public AST visitIf(If ifElement, P param) {
		Expression ifTest = (Expression) ifElement.getIfTest().accept(this, param);
		Statement ifBody = (Statement) ifElement.getIfBody().accept(this, param);
		List<Expression> elifTests = modifyList(ifElement.getElifTests(), param);
		List<Statement> elifBodies = modifyList(ifElement.getElifBodies(), param);
		Optional<Statement> elseBody = modifyOptional(ifElement.getElseBody(), param);
		ifElement.setIfTest(ifTest);
		ifElement.setIfBody(ifBody);
		ifElement.setElifTests(elifTests);
		ifElement.setElifBodies(elifBodies);
		ifElement.setElseBody(elseBody);
		return ifElement;
	}

	@Override
	public AST visitImport(Import importElement, P param) {
		List<Alias> names = modifyList(importElement.getNames(), param);
		importElement.setNames(names);
		return importElement;
	}

	@Override
	public AST visitImportFrom(ImportFrom importFrom, P param) {
		Optional<Identifier> module = modifyOptional(importFrom.getModule(), param);
		List<Alias> names = modifyList(importFrom.getNames(), param);
		Integer level = importFrom.getLevel();
		importFrom.setModule(module);
		importFrom.setNames(names);
		importFrom.setLevel(level);
		return importFrom;
	}

	@Override
	public AST visitNonlocal(Nonlocal nonlocal, P param) {
		List<Identifier> names = modifyList(nonlocal.getNames(), param);
		nonlocal.setNames(names);
		return nonlocal;
	}

	@Override
	public AST visitPass(Pass pass, P param) {
		return pass;
	}

	@Override
	public AST visitRaise(Raise raise, P param) {
		Optional<Expression> exc = modifyOptional(raise.getExc(), param);
		Optional<Expression> cause = modifyOptional(raise.getCause(), param);
		raise.setExc(exc);
		raise.setCause(cause);
		return raise;
	}

	@Override
	public AST visitReturn(Return returnElement, P param) {
		Optional<Expression> value = modifyOptional(returnElement.getValue(), param);
		returnElement.setValue(value);
		return returnElement;
	}

	@Override
	public AST visitStatement(Statement statement, P param) {
		return statement.accept(this, param);
	}

	@Override
	public AST visitTry(Try tryElement, P param) {
		Statement body = (Statement) tryElement.getBody().accept(this, param);
		List<ExceptHandler> handlers = modifyList(tryElement.getHandlers(), param);
		List<Statement> handlersBody = modifyList(tryElement.getHandlersBody(), param);
		Optional<Statement> orElse = modifyOptional(tryElement.getOrElse(), param);
		Optional<Statement> finalBody = modifyOptional(tryElement.getFinalBody(), param);
		tryElement.setBody(body);
		tryElement.setHandlers(handlers);
		tryElement.setHandlersBody(handlersBody);
		tryElement.setOrElse(orElse);
		tryElement.setFinalBody(finalBody);
		return tryElement;
	}

	@Override
	public AST visitWhile(While whileElement, P param) {
		Expression test = (Expression) whileElement.getTest().accept(this, param);
		Statement body = (Statement) whileElement.getBody().accept(this, param);
		Optional<Statement> orElse = modifyOptional(whileElement.getOrElse(), param);
		whileElement.setTest(test);
		whileElement.setBody(body);
		whileElement.setOrElse(orElse);
		return whileElement;
	}

	@Override
	public AST visitWith(With with, P param) {
		List<WithItem> items = modifyList(with.getItems(), param);
		Statement body = (Statement) with.getBody().accept(this, param);
		with.setItems(items);
		with.setBody(body);
		return with;
	}

	@Override
	public AST visitInvert(Invert invert, P param) {
		return visitUnaryOp(invert, param);
	}

	@Override
	public AST visitNot(Not not, P param) {
		return visitUnaryOp(not, param);
	}

	@Override
	public AST visitUAdd(UAdd uAdd, P param) {
		return visitUnaryOp(uAdd, param);
	}

	@Override
	public AST visitUSub(USub uSub, P param) {
		return visitUnaryOp(uSub, param);
	}

	@Override
	public AST visitAlias(Alias alias, P param) {
		Identifier name = (Identifier) alias.getName().accept(this, param);
		Optional<Identifier> asname = modifyOptional(alias.getAsName(), param);
		alias.setName(name);
		alias.setAsName(asname);
		return alias;
	}

	@Override
	public AST visitAST(AST ast, P param) {
		return ast.accept(this, param);
	}

	@Override
	public AST visitComprehension(Comprehension comprehension, P param) {
		Expression target = (Expression) comprehension.getTarget().accept(this, param);
		Expression iter = (Expression) comprehension.getIter().accept(this, param);
		List<Expression> ifs = modifyList(comprehension.getIfs(), param);
		int isAsync = comprehension.getIsAsync();
		comprehension.setTarget(target);
		comprehension.setIter(iter);
		comprehension.setIfs(ifs);
		comprehension.setIsAsync(isAsync);
		return comprehension;
	}

	@Override
	public AST visitExceptHandler(ExceptHandler exceptHandler, P param) {
		Optional<Expression> error = modifyOptional(exceptHandler.getError(), param);
		Optional<Identifier> errorAsName = modifyOptional(exceptHandler.getErrorAsName(), param);
		exceptHandler.setError(error);
		exceptHandler.setErrorAsName(errorAsName);
		return exceptHandler;
	}

	@Override
	public AST visitIdentifier(Identifier identifier, P param) {
		String name = identifier.getName();
		identifier.setName(name);
		return identifier;
	}

	@Override
	public AST visitKeyword(Keyword keyword, P param) {
		Optional<Expression> arg = modifyOptional(keyword.getArg(), param);
		Expression value = (Expression) keyword.getValue().accept(this, param);
		keyword.setArg(arg);
		keyword.setValue(value);
		return keyword;
	}

	@Override
	public AST visitWithItem(WithItem withItem, P param) {
		Expression contextExpr = (Expression) withItem.getContextExpr().accept(this, param);
		Optional<Expression> optionalVars = modifyOptional(withItem.getOptionalVars(), param);
		withItem.setContextExpr(contextExpr);
		withItem.setOptionalVars(optionalVars);
		return withItem;
	}

	private <N extends AST> Optional<N> modifyOptional(Optional<N> optional, P param) {
		AST newValue = null;
		
		if (optional.isPresent()) {
			AST oldValue = optional.get();
			newValue = oldValue.accept(this, param);
		}
		
		return (Optional<N>) Optional.ofNullable(newValue);
	}
	
	private <N extends AST> List<N> modifyList(List<N> list, P param) {
		List<N> modifiedList = new ArrayList<>();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				modifiedList.add((N) list.get(i).accept(this, param));
			}
		}
		
		return modifiedList;
	}
}
