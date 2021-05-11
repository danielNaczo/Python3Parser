package io.github.danielnaczo.python3parser.model.expr.atoms.trailers.arguments;

import java.util.List;
import java.util.Objects;

import io.github.danielnaczo.python3parser.model.expr.Expression;
import io.github.danielnaczo.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.: (a, b, c=1, *d, **e)
public class Arguments extends Expression{
	static int PRECEDENCE = 160;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
									//from example:
	List<Expression> args; 			//a,b
	List<Keyword> keywords;			//c
	List<Expression> starredArgs;	//d
	List<Keyword> doubleStarredArgs;	//e
	
	public Arguments(List<Expression> args, List<Keyword> keywords, List<Expression> starredArgs,
			List<Keyword> doubleStarredArgs) {
		this.args = args;
		this.keywords = keywords;
		this.starredArgs = starredArgs;
		this.doubleStarredArgs = doubleStarredArgs;
	}

	public List<Expression> getArgs() {
		return args;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public List<Expression> getStarredArgs() {
		return starredArgs;
	}

	public void setStarredArgs(List<Expression> starredArgs) {
		this.starredArgs = starredArgs;
	}

	public List<Keyword> getDoubleStarredArgs() {
		return doubleStarredArgs;
	}

	public void setDoubleStarredArgs(List<Keyword> doubleStarredArgs) {
		this.doubleStarredArgs = doubleStarredArgs;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitArguments(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Arguments that = (Arguments) o;
		return Objects.equals(args, that.args) &&
				Objects.equals(keywords, that.keywords) &&
				Objects.equals(starredArgs, that.starredArgs) &&
				Objects.equals(doubleStarredArgs, that.doubleStarredArgs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(args, keywords, starredArgs, doubleStarredArgs);
	}
}
