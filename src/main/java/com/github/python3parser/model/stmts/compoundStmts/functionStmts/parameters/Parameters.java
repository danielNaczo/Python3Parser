package com.github.python3parser.model.stmts.compoundStmts.functionStmts.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.AST;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

public class Parameters implements AST {

	List<Parameter> params; 					// e.g. def sum(a, b, c, d)
	List<DefaultParameter> defaultParams; 		// e.g def sum(a, b, c=0, d=0)			--> c and d are defaults
	Optional<Parameter> varParam;			// e.g. def sum(a, b, *c)				--> *c is varParam

	List<Parameter> kwonlyParams;			// e.g. def sum(a, b, *c, d, e)			--> d and e are kwonlyParams
	List<DefaultParameter> kwDefaultParams;		// e.g. def sum(a, b, *c, d=4, e=5)		--> d and e are kwDefaults
	Optional<Parameter> varKwParam;				// e.g. def sum (a, b, **c) 				--> **c is kwParam
	// also possible: def sum(a, b, *, c, d)	--> positional params separated from kwParams
	//				  def sum(a, b, *c, d, e, **f)
	
	
	public Parameters() {
		this(null, null, null, null, null, null);
	}
	
	public Parameters(List<Parameter> params) {
		this(params, null, null, null, null, null);
	}
	
	public Parameters(List<Parameter> params, List<DefaultParameter> defaultParams) {
		this(params, defaultParams, null, null, null, null);
	}
	
	public Parameters(List<Parameter> params, List<DefaultParameter> defaultParams, Parameter varParam) {
		this(params, defaultParams, varParam, null, null, null);
	}
	
	public Parameters(List<Parameter> params, List<DefaultParameter> defaultParams, Parameter varParam, List<Parameter> kwonlyParam) {
		this(params, defaultParams, varParam, kwonlyParam, null, null);
	}
	
	public Parameters(List<Parameter> params, List<DefaultParameter> defaultParams, Parameter varParam, List<Parameter> kwonlyParam,
			List<DefaultParameter> kwDefaultParams) {
		this(params, defaultParams, varParam, kwonlyParam, kwDefaultParams, null);
	}
	
	public Parameters(List<Parameter> params, List<DefaultParameter> defaultParams, Parameter varParam, List<Parameter> kwonlyParam,
			List<DefaultParameter> kwDefaultParams, Parameter kwParam) {
		this.params = params;
		this.defaultParams = defaultParams;
		this.varParam = Optional.ofNullable(varParam);
		this.kwonlyParams = kwonlyParam;
		this.kwDefaultParams = kwDefaultParams;
		this.varKwParam = Optional.ofNullable(kwParam);
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitParameters(this, param);
	}
	
	public List<Parameter> getParams() {
		return params;
	}

	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	public List<DefaultParameter> getDefaultParams() {
		return defaultParams;
	}

	public void setDefaultParams(List<DefaultParameter> defaultParams) {
		this.defaultParams = defaultParams;
	}

	public Optional<Parameter> getVarParam() {
		return varParam;
	}

	public void setVarParam(Optional<Parameter> varParam) {
		this.varParam = varParam;
	}

	public List<Parameter> getKwonlyParams() {
		return kwonlyParams;
	}

	public void setKwonlyParams(List<Parameter> kwonlyParams) {
		this.kwonlyParams = kwonlyParams;
	}

	public List<DefaultParameter> getKwDefaultParams() {
		return kwDefaultParams;
	}

	public void setKwDefaultParams(List<DefaultParameter> kwDefaultParams) {
		this.kwDefaultParams = kwDefaultParams;
	}

	public Optional<Parameter> getKwParam() {
		return varKwParam;
	}

	public void setKwParam(Optional<Parameter> kwParam) {
		this.varKwParam = kwParam;
	}
	
	public Parameter addParameter(Parameter parameter) {
		if (params == null) params = new ArrayList<>();
		params.add(parameter);
		return parameter;
	}
	
	public DefaultParameter addDefaultParameter(DefaultParameter defaultParam) {
		if (defaultParams == null) defaultParams = new ArrayList<>();
		defaultParams.add(defaultParam);
		return defaultParam;
	}
	
	public Parameter addKeyWordParameter(Parameter kwParam) {
		if (kwonlyParams == null) kwonlyParams = new ArrayList<>();
		kwonlyParams.add(kwParam);
		return kwParam;
	}
	
	public DefaultParameter addKeyWordDefaultParameter(DefaultParameter kwDefaultParam) {
		if (kwDefaultParams == null) kwDefaultParams = new ArrayList<>();
		kwDefaultParams.add(kwDefaultParam);
		return kwDefaultParam;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Parameters parameters = (Parameters) o;
		return Objects.equals(params, parameters.params) &&
				Objects.equals(defaultParams, parameters.defaultParams) &&
				Objects.equals(varParam, parameters.varParam) &&
				Objects.equals(kwonlyParams, parameters.kwonlyParams) &&
				Objects.equals(kwDefaultParams, parameters.kwDefaultParams) &&
				Objects.equals(varKwParam, parameters.varKwParam);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(params, defaultParams, varParam, kwonlyParams, kwDefaultParams, varKwParam);
	}
}
