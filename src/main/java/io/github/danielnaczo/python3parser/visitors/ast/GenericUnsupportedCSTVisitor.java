package io.github.danielnaczo.python3parser.visitors.ast;

import io.github.danielnaczo.python3parser.visitors.exceptions.UnsupportedANTLRMethodException;
import com.github.python3parser.Python3BaseVisitor;
import com.github.python3parser.Python3Parser.*;


public class GenericUnsupportedCSTVisitor<T> extends Python3BaseVisitor<T>{

	@Override
	public T visitTestlist(TestlistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAssert_stmt(Assert_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArgument(ArgumentContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitNot_test(Not_testContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFile_input(File_inputContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitXor_expr(Xor_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitImport_from(Import_fromContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSingle_input(Single_inputContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitWith_item(With_itemContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitRaise_stmt(Raise_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitImport_as_name(Import_as_nameContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExcept_clause(Except_clauseContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitCompound_stmt(Compound_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAnd_expr(And_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitLambdef_nocond(Lambdef_nocondContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDictorsetmaker(DictorsetmakerContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitReturn_stmt(Return_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDotted_name(Dotted_nameContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFlow_stmt(Flow_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitWhile_stmt(While_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitOr_test(Or_testContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitComparison(ComparisonContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTest(TestContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSubscript(SubscriptContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitComp_for(Comp_forContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitYield_arg(Yield_argContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitYield_expr(Yield_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitImport_stmt(Import_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitShift_expr(Shift_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitLambdef(LambdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAnd_test(And_testContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitGlobal_stmt(Global_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitImport_as_names(Import_as_namesContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTry_stmt(Try_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitComp_op(Comp_opContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitStar_expr(Star_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitBreak_stmt(Break_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitParameters(ParametersContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDecorator(DecoratorContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTfpdef(TfpdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitIf_stmt(If_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitWith_stmt(With_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitClassdef(ClassdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExprlist(ExprlistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSmall_stmt(Small_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTrailer(TrailerContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDotted_as_names(Dotted_as_namesContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArith_expr(Arith_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArglist(ArglistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSimple_stmt(Simple_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTypedargslist(TypedargslistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExpr(ExprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTerm(TermContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDotted_as_name(Dotted_as_nameContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFactor(FactorContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFuncdef(FuncdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSubscriptlist(SubscriptlistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTest_nocond(Test_nocondContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitNonlocal_stmt(Nonlocal_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitEval_input(Eval_inputContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVfpdef(VfpdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitImport_name(Import_nameContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitComp_if(Comp_ifContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAugassign(AugassignContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitPass_stmt(Pass_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExpr_stmt(Expr_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitYield_stmt(Yield_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSuite(SuiteContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitContinue_stmt(Continue_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTestlist_star_expr(Testlist_star_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarargslist(VarargslistContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFor_stmt(For_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDel_stmt(Del_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAtom(AtomContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitStmt(StmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAtom_expr(Atom_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAnnassign(AnnassignContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAsync_stmt(Async_stmtContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitNormalOrDefaultTfpDef(NormalOrDefaultTfpDefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDefaultTfpdef(DefaultTfpdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitCompleteArgs(CompleteArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitAfterPositionalArgs(AfterPositionalArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitPositionalList(PositionalListContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitKwlistArgs1(KwlistArgs1Context ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitJustKeywordsArgs(JustKeywordsArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitKwlistArgs2(KwlistArgs2Context ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitKwOnlyListArgs(KwOnlyListArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarNormalOrDefaultTfpDef(VarNormalOrDefaultTfpDefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarDefaultVfpdef(VarDefaultVfpdefContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarCompleteArgs(VarCompleteArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarAfterPositionalArgs(VarAfterPositionalArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarPositionalList(VarPositionalListContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarKwlistArgs1(VarKwlistArgs1Context ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarJustKeywordsArgs(VarJustKeywordsArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarKwlistArgs2(VarKwlistArgs2Context ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitVarKwOnlyListArgs(VarKwOnlyListArgsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExpr_stmtIndividualAssign(Expr_stmtIndividualAssignContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExpr_stmtNormalAssign(Expr_stmtNormalAssignContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitShift_op(Shift_opContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArith_op(Arith_opContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTerm_op(Term_opContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitFactor_op(Factor_opContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}
	
	@Override
	public T visitPower(PowerContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExpr_NormalAssignList(Expr_NormalAssignListContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTestOrStar_expr(TestOrStar_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTestOrStarList_expr(TestOrStarList_exprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTrailerCall(TrailerCallContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTrailerSubscript(TrailerSubscriptContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTrailerName(TrailerNameContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTuple(TupleContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTestlist_compTuple(Testlist_compTupleContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitList(ListContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitTestlist_compList(Testlist_compListContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDictionaryOrSet(DictionaryOrSetContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDictFirst(DictFirstContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitDicts(DictsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSetFirst(SetFirstContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSets(SetsContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArgumentNormal(ArgumentNormalContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArgumentKeyword(ArgumentKeywordContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArgumentStar(ArgumentStarContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitArgumentDoubleStar(ArgumentDoubleStarContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSubscriptIndex(SubscriptIndexContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSubscriptSlice(SubscriptSliceContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSlicelLower(SlicelLowerContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSliceUpper(SliceUpperContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitSliceStep(SliceStepContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}

	@Override
	public T visitExprOrStarExpr(ExprOrStarExprContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}
	
	public T visitAwait(AwaitContext ctx) {
		throw new UnsupportedANTLRMethodException();
	}
}
