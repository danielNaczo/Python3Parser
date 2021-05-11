package io.github.danielnaczo.python3parser.visitors.prettyprint;

import java.util.List;
import java.util.Optional;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameter;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.DefaultParameter;
import io.github.danielnaczo.python3parser.model.stmts.compoundStmts.functionStmts.parameters.Parameters;

public class ParametersPrettyPrintVisitor extends GenericUnsupportedASTVisitor<String, IndentationPrettyPrint>{

	@Override
	public String visitParameters(Parameters parametersClass, IndentationPrettyPrint param) {
		String string = new String();
		
		List<Parameter> parameters = parametersClass.getParams();
		List<DefaultParameter> defaultParams = parametersClass.getDefaultParams();
		Optional<Parameter> varParam = parametersClass.getVarParam();

		List<Parameter> kwonlyParams = parametersClass.getKwonlyParams();
		List<DefaultParameter> kwDefaultParams = parametersClass.getKwDefaultParams();
		Optional<Parameter> kwParam = parametersClass.getKwParam();
		
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				if (!string.isEmpty()) {
					string = string.concat(", ");
				}
				
				string = string.concat(parameters.get(i).accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		
		if (defaultParams != null) {
			for (int i = 0; i < defaultParams.size(); i++	) {
				if (!string.isEmpty()) {
					string = string.concat(", ");
				}
				
				string = string.concat(defaultParams.get(i).accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		
		if (varParam.isPresent()) {
			if (!string.isEmpty()) {
				string = string.concat(", ");
			}
			string = string.concat("*");
			string = string.concat(varParam.get().accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		if (kwonlyParams != null) {
			for (int i = 0; i < kwonlyParams.size(); i++) {
				if (!string.isEmpty()) {
					string = string.concat(", ");
				}
				string = string.concat(kwonlyParams.get(i).accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		
		if (kwDefaultParams != null) {
			for (int i = 0; i < kwDefaultParams.size(); i++) {
				if (!string.isEmpty()) {
					string = string.concat(", ");
				}
				string = string.concat(kwDefaultParams.get(i).accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
			}
		}
		
		if (kwParam.isPresent()) {
			if (!string.isEmpty()) {
				string = string.concat(", ");
			}
			string = string.concat("**");
			string = string.concat(kwParam.get().accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
	
	@Override
	public String visitParameter(Parameter parameterClass, IndentationPrettyPrint param) {
		String string = new String();
		
		String parameterName = parameterClass.getParameterName().getName(); //when parameter is '*' then it's this case: def sum(a, b, * , c, d)
		Optional<Expression> annotation = parameterClass.getAnnotation();
		
		if (!parameterName.equals("*")) {
			string = string.concat(parameterName);			
		}
		
		if (annotation.isPresent()) {
			string = string.concat(": ");
			string = string.concat(annotation.get().accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		}
		
		return string;
	}
	
	@Override
	public String visitDefaultParameter(DefaultParameter defaultParameter, IndentationPrettyPrint param) {
		String string = new String();
		
		Parameter parameter = defaultParameter.getParameter();
		Expression value = defaultParameter.getValue();
		
		string = string.concat(parameter.accept(new ParametersPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		string = string.concat(" = ");
		string = string.concat(value.accept(new ExpressionPrettyPrintVisitor(), new IndentationPrettyPrint(param.getIndentationLevel())));
		return string;
	}
}
