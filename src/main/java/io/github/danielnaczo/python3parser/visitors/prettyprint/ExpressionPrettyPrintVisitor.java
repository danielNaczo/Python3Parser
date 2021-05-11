package io.github.danielnaczo.python3parser.visitors.prettyprint;

import java.util.List;
import java.util.Optional;

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
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.Subscript;
import io.github.danielnaczo.python3parser.model.expr.atoms.trailers.subscripts.slices.SliceAbstract;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.Comprehension;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.DictComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.ListComp;
import io.github.danielnaczo.python3parser.model.expr.comprehensions.SetComp;

public class ExpressionPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitArguments(Arguments arguments, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Expression> args = arguments.getArgs();
		List<Keyword> keywords = arguments.getKeywords();
		List<Expression> starredArgs = arguments.getStarredArgs();
		List<Keyword> doubleStarredArgs = arguments.getDoubleStarredArgs();

		if (args == null && keywords == null && starredArgs == null && doubleStarredArgs == null) {
			return "()";
		}
		
		string = string.concat("(");
		
		for (int i = 0; args != null && i < args.size(); i++) {
			if (!string.equals("(")) {
				string = string.concat(", ");
			}
			string = string.concat(args.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		for (int i = 0; keywords != null && i < keywords.size(); i++) {
			if (!string.equals("(")) {
				string = string.concat(", ");
			}
			string = string.concat(keywords.get(i).accept(new KeywordPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		for (int i = 0; starredArgs != null && i < starredArgs.size(); i++) {
			if (!string.equals("(")) {
				string = string.concat(", ");
			}
			string = string.concat("*");
			string = string.concat(starredArgs.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		for (int i = 0; doubleStarredArgs != null && i < doubleStarredArgs.size(); i++) {
			if (!string.equals("(")) {
				string = string.concat(", ");
			}
			string = string.concat("**");
			string = string.concat(doubleStarredArgs.get(i).accept(new KeywordPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		string = string.concat(")");
		return string;
	}
	
	@Override
	public String visitArgumentComp(ArgumentComp argumentComp, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression elt = argumentComp.getElt();
		List<Comprehension> comprehensions = argumentComp.getComprehensions();
		
		string = string.concat(elt.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < comprehensions.size(); i++) {
			string = string.concat(" ");
			string = string.concat(comprehensions.get(i).accept(new ComprehensionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		return string;
	}
	
	@Override
	public String visitAtom(Atom atom, IndentationPrettyPrint param) {
		String string = new String();
		Expression atomElement = atom.getAtomElement();
		List<Expression> trailers = atom.getTrailers();

		string = string.concat(atomElement.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < trailers.size(); i++) {
			string = string.concat(trailers.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
	
	@Override
	public String visitAttribute(Attribute attribute, IndentationPrettyPrint param) {
		String string = new String();
		String attr = attribute.getAttr().getName();
		string = string.concat(".");
		string = string.concat(attr);
		
		return string;
	}
	
	@Override
	public String visitBinOp(BinOp binOp, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression left = binOp.getLeft();
		Expression right = binOp.getRight();
		
		boolean inBrackets = checkPrecedence(binOp);
		
		if (inBrackets){
			string = string.concat("(");
		}
		string = string.concat(left.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" ");
		string = string.concat(binOp.accept(new OperatorPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" ");
		string = string.concat(right.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		if (inBrackets){
			string = string.concat(")");
		}
		
		return string;
	}
	
	private boolean checkPrecedence(Expression expression) {
		Expression parent = expression.getParent();
		if (parent == null) return false;
		boolean basicPrecedenceCheck = expression.getPrecedence() < parent.getPrecedence();
		boolean levelPrecedenceCheck = levelPrecedenceCheck(expression);
		
		return basicPrecedenceCheck || levelPrecedenceCheck;
	}

	private boolean levelPrecedenceCheck(Expression expression) {
		Expression parent = expression.getParent();
		if (!(parent instanceof BinOp)) return false;
		BinOp binOpParent = (BinOp) parent;
		if (!(binOpParent.getPrecedence() == expression.getPrecedence())) return false;
		if (parent instanceof Pow) { //only operator which is right associative
			return binOpParent.getLeft() == expression;
		} else {
			return binOpParent.getRight() == expression;
		}
	}
	
	@Override
	public String visitDict(Dict dict, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Expression> keys = dict.getKeys();
		List<Expression> values = dict.getValues();
		
		string = string.concat("{");
		
		if (keys != null) {
			for (int i = 0; i < keys.size(); i++) {
				
				if (keys.get(i) != null) {
					string = string.concat(keys.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
					string = string.concat(" : ");
					string = string.concat(values.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				} else {
					string = string.concat("**");
					string = string.concat(values.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				}
				
				if (i != keys.size() - 1) {
					string = string.concat(", ");
				}
			}
		}
		string = string.concat("}");
		return string;
	}
	
	@Override
	public String visitDictComp(DictComp dictComp, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression key = dictComp.getKey();
		Expression value = dictComp.getValue();
		List<Comprehension> comprehensions = dictComp.getComprehensions();
		
		string = string.concat("{");
		
		string = string.concat(key.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" : ");
		string = string.concat(value.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < comprehensions.size(); i++) {
			string = string.concat(" ");
			string = string.concat(comprehensions.get(i).accept(new ComprehensionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		string = string.concat("}");
		
		return string;
	}
	
	@Override
	public String visitEllipsis(Ellipsis ellipsis, IndentationPrettyPrint param) {
		return "...";
	}
	
	@Override
	public String visitExpressionList(ExpressionsList expressionList, IndentationPrettyPrint param) {
		String string = new String();
		List<Expression> expressions = expressionList.getExpressions();
		
		for (int i = 0; i < expressions.size(); i++) {
			string = string.concat(expressions.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			
			if (i != expressions.size() - 1) {
				string = string.concat(", ");
			}
		}
		return string;
	}
	
	@Override
	public String visitFalse(False falseElement, IndentationPrettyPrint param) {
		return "False";
	}
	
	@Override
	public String visitIfExpr(IfExpr ifExpr, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression test = ifExpr.getTest();
		Expression body = ifExpr.getBody();
		Expression orElse = ifExpr.getOrElse();
		
		boolean precedenceCheck = checkPrecedence(ifExpr);
		boolean ifExprPrecedenceCheck = checkIfExprPrecedence(ifExpr);
		boolean inBrackets = precedenceCheck || ifExprPrecedenceCheck;
		
		if (inBrackets) string = string.concat("(");
		string = string.concat(body.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" if ");
		string = string.concat(test.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" else ");
		string = string.concat(orElse.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		if (inBrackets) string = string.concat(")");
		
		return string;
	}
	
	private boolean checkIfExprPrecedence(IfExpr ifExpr) {
		Expression parent = ifExpr.getParent();
		if (!(parent instanceof IfExpr)) return false;
		IfExpr parentIfExpr = (IfExpr) parent;
		if (parentIfExpr.getBody() == ifExpr) return true;
		if (parentIfExpr.getTest() == ifExpr) return true;
		return false;
	}

	@Override
	public String visitJoinedStr(JoinedStr joinedStr, IndentationPrettyPrint param) {
		String string = new String();
		List<Expression> values = joinedStr.getValues();
		
		for (int i = 0; i < values.size(); i++) {
			if (i != 0) {
				string = string.concat(" ");
			}
			string = string.concat(values.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
	
	@Override
	public String visitLambda(Lambda lambda, IndentationPrettyPrint param) {
		String string = new String();
		
		Optional<Parameters> args = lambda.getArgs();
		Expression body = lambda.getBody();
		
		boolean precedenceCheck = checkPrecedence(lambda);
		boolean lambdaPrecedenceCheck = checkLambdaPrecedence(lambda);
		boolean inBrackets = precedenceCheck || lambdaPrecedenceCheck;
		
		if (inBrackets) string = string.concat("(");
		string = string.concat("lambda");
		if (args.isPresent()) {
			string = string.concat(" ");
			string = string.concat(args.get().accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		string = string.concat(": ");
		string = string.concat(body.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		if (inBrackets) string = string.concat(")");
		
		return string;
	}
	
	private boolean checkLambdaPrecedence(Lambda lambda) {
		Expression parent = lambda.getParent();
		if (!(parent instanceof Lambda)) return false;
		Lambda parentLambda = (Lambda) parent;
		if (parentLambda.getBody() == lambda) return true;
		return false;
	}

	@Override
	public String visitListComp(ListComp listComp, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression elt = listComp.getElt();
		List<Comprehension> comprehensions = listComp.getComprehensions();
		
		string = string.concat("[");
		
		string = string.concat(elt.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < comprehensions.size(); i++) {
			string = string.concat(" ");
			string = string.concat(comprehensions.get(i).accept(new ComprehensionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		string = string.concat("]");
		
		return string;
	}
	
	@Override
	public String visitListExpr(ListExpr listExpr, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Expression> elts = listExpr.getElts();
		
		string = string.concat("[");
		
		if (elts != null) {
			for (int i = 0; i < elts.size(); i++) {
				string = string.concat(elts.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				
				if (i != elts.size() - 1) {
					string = string.concat(", ");
				}
			}
		}
		string = string.concat("]");
		
		return string;
	}
	
	@Override
	public String visitName(Name name, IndentationPrettyPrint param) {
		return name.getId().getName();
	}
	
	@Override
	public String visitNone(None none, IndentationPrettyPrint param) {
		return "None";
	}
	
	@Override
	public String visitNum(Num num, IndentationPrettyPrint param) {
		return num.getN();
	}
	
	@Override
	public String visitSet(Set set, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Expression> elts = set.getElts();
		
		string = string.concat("{");
		
		if (elts != null) {
			for (int i = 0; i < elts.size(); i++) {
				string = string.concat(elts.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				
				if (i != elts.size() - 1) {
					string = string.concat(", ");
				}
			}
		}
		string = string.concat("}");
		
		return string;
	}
	
	@Override
	public String visitSetComp(SetComp setComp, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression elt = setComp.getElt();
		List<Comprehension> comprehensions = setComp.getComprehensions();
		
		string = string.concat("{");
		
		string = string.concat(elt.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		for (int i = 0; i < comprehensions.size(); i++) {
			string = string.concat(" ");
			string = string.concat(comprehensions.get(i).accept(new ComprehensionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		string = string.concat("}");
		
		return string;
	}
	
	@Override
	public String visitStr(Str arg, IndentationPrettyPrint param) {
		String string = arg.getS();
		if (!(string.startsWith(new Character('"').toString()) ||
				string.startsWith("'") ||
				string.startsWith("b'") ||
				string.startsWith("b" + '"') || 
				string.startsWith("r" + '"' + '"' + '"') ||
				string.startsWith("r" + "'") ||
				string.startsWith("r" + '"')
				)
			) {
			string = '"' + string;
			string = string + '"';
		}
		return string;
	}
	
	@Override
	public String visitSubscript(Subscript subscript, IndentationPrettyPrint param) {
		String string = new String();
		
		SliceAbstract slice = subscript.getSlice();
		
		string = string.concat("[");
		string = string.concat(slice.accept(new SlicePrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat("]");
		return string;
	}
	
	@Override
	public String visitTrue(True trueElement, IndentationPrettyPrint param) {
		return "True";
	}
	
	@Override
	public String visitTuple(Tuple tuple, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Expression> elts = tuple.getElts();
		
		string = string.concat("(");
		
		if (elts != null) {
			for (int i = 0; i < elts.size(); i++) {
				string = string.concat(elts.get(i).accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
				string = string.concat(", ");
			}
		}
		string = string.concat(")");
		
		return string;
	}
	
	@Override
	public String visitGenerator(Generator generator, IndentationPrettyPrint param) {
		String string = new String();
		
		Expression elt = generator.getElt();
		List<Comprehension> comprehensions = generator.getComprehensions();
		
		string = string.concat("(");
		
		string = string.concat(elt.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		
		if (comprehensions != null) {
			for (int i = 0; i < comprehensions.size(); i++) {
				string = string.concat(" ");
				string = string.concat(comprehensions.get(i).accept(new ComprehensionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		string = string.concat(")");
		
		return string;
	}
	
	@Override
	public String visitUnaryOp(UnaryOp unaryOp, IndentationPrettyPrint param) {
		String string = new String();
		Expression expression = unaryOp.getExpression();
		boolean inBrackets = checkPrecedence(unaryOp);
		
		if (inBrackets){
			string = string.concat("(");
		}
		string = string.concat(unaryOp.accept(new UnaryOpPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(expression.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		if (inBrackets){
			string = string.concat(")");
		}
		
		return string;
	}
	
	@Override
	public String visitYield(Yield yield, IndentationPrettyPrint param) {
		String string = new String();
		Optional<Expression> value = yield.getExpression();
		
		boolean inBrackets = checkPrecedence(yield);
		if (inBrackets) string = string.concat("(");
		string = string.concat("yield");
		if (value.isPresent()) {
			string = string.concat(" ");
			string = string.concat(value.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		if (inBrackets) string = string.concat(")");
		return string;
	}
	
	@Override
	public String visitYieldFrom(YieldFrom yieldFrom, IndentationPrettyPrint param) {
		String string = new String();
		Expression value = yieldFrom.getExpression();
		
		boolean inBrackets = checkPrecedence(yieldFrom);
		
		if (inBrackets) string = string.concat("(");
		string = string.concat("yield from ");
		string = string.concat(value.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		if (inBrackets) string = string.concat(")");
		return string;
	}
	
	//BINARY OPERATORS//
	@Override
	public String visitAdd(Add add, IndentationPrettyPrint param) {
		return visitBinOp(add, param);
	}
	
	@Override
	public String visitAt(At at, IndentationPrettyPrint param) {
		return visitBinOp(at, param);
	}
	
	@Override
	public String visitBitAnd(BitAnd bitAnd, IndentationPrettyPrint param) {
		return visitBinOp(bitAnd, param);
	}
	
	@Override
	public String visitBitOr(BitOr bitOr, IndentationPrettyPrint param) {
		return visitBinOp(bitOr, param);
	}
	
	@Override
	public String visitBitXor(BitXor bitXor, IndentationPrettyPrint param) {
		return visitBinOp(bitXor, param);
	}
	
	@Override
	public String visitDiv(Div div, IndentationPrettyPrint param) {
		return visitBinOp(div, param);
	}
	
	@Override
	public String visitFloorDiv(FloorDiv floorDiv, IndentationPrettyPrint param) {
		return visitBinOp(floorDiv, param);
	}
	
	@Override
	public String visitLShift(LShift lShift, IndentationPrettyPrint param) {
		return visitBinOp(lShift, param);
	}
	
	@Override
	public String visitModulo(Mod modulo, IndentationPrettyPrint param) {
		return visitBinOp(modulo, param);
	}
	
	@Override
	public String visitMult(Mult mult, IndentationPrettyPrint param) {
		return visitBinOp(mult, param);
	}
	
	@Override
	public String visitRShift(RShift rShift, IndentationPrettyPrint param) {
		return visitBinOp(rShift, param);
	}
	
	@Override
	public String visitSub(Sub sub, IndentationPrettyPrint param) {
		return visitBinOp(sub, param);
	}
	
	@Override
	public String visitPow(Pow pow, IndentationPrettyPrint param) {
		return visitBinOp(pow, param);
	}
	
	//COMPARISON OPERATORS//
	
	@Override
	public String visitEq(Eq eq, IndentationPrettyPrint param) {
		return visitBinOp(eq, param);
	}
	
	@Override
	public String visitGt(Gt gt, IndentationPrettyPrint param) {
		return visitBinOp(gt, param);
	}
	
	@Override
	public String visitGtE(GtE gte, IndentationPrettyPrint param) {
		return visitBinOp(gte, param);
	}
	
	@Override
	public String visitIn(In in, IndentationPrettyPrint param) {
		return visitBinOp(in, param);
	}
	
	@Override
	public String visitIs(Is is, IndentationPrettyPrint param) {
		return visitBinOp(is, param);
	}
	
	@Override
	public String visitIsNot(IsNot isNot, IndentationPrettyPrint param) {
		return visitBinOp(isNot, param);
	}
	
	@Override
	public String visitLt(Lt lt, IndentationPrettyPrint param) {
		return visitBinOp(lt, param);
	}
	
	@Override
	public String visitLtE(LtE lte, IndentationPrettyPrint param) {
		return visitBinOp(lte, param);
	}
	
	@Override
	public String visitNotEq(NotEq notEq, IndentationPrettyPrint param) {
		return visitBinOp(notEq, param);
	}
	
	@Override
	public String visitNotIn(NotIn notIn, IndentationPrettyPrint param) {
		return visitBinOp(notIn, param);
	}
	
	//BOOLOP OPERATORS//
	
	@Override
	public String visitAnd(And and, IndentationPrettyPrint param) {
		return visitBinOp(and, param);
	}
	
	@Override
	public String visitOr(Or or, IndentationPrettyPrint param) {
		return visitBinOp(or, param);
	}
	
	//UNARY OPERATORS//
	
	@Override
	public String visitAwait(Await await, IndentationPrettyPrint param) {
		return visitUnaryOp(await, param);
	}

	@Override
	public String visitNot(Not and, IndentationPrettyPrint param) {
		return visitUnaryOp(and, param);
	}
	
	@Override
	public String visitInvert(Invert invert, IndentationPrettyPrint param) {
		return visitUnaryOp(invert, param);
	}
	
	@Override
	public String visitUAdd(UAdd uAdd, IndentationPrettyPrint param) {
		return visitUnaryOp(uAdd, param);
	}
	
	@Override
	public String visitUSub(USub uSub, IndentationPrettyPrint param) {
		return visitUnaryOp(uSub, param);
	}
	
	@Override
	public String visitStarred(Starred starred, IndentationPrettyPrint param) {
		return visitUnaryOp(starred, param);
	}
}
