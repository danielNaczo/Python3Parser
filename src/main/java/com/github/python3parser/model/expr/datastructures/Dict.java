package com.github.python3parser.model.expr.datastructures;

import java.util.List;
import java.util.Objects;

import com.github.python3parser.model.expr.Expression;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//e.g.: d = {"key1" : "value1", "key2" : "value2"}
// 			^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

//or

//e.g.: s = {"key1" : "value1", **d}   --> expand one dictionary into another
//			^^^^^^^^^^^^^^^^^^^^^^^^
//									  --> "d" would be stored in values and the corresponding key would be "null"
public class Dict extends Expression {
	static int PRECEDENCE = 170;

	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	List<Expression> keys;
	List<Expression> values;
	
	public Dict(List<Expression> keys, List<Expression> values) {
		this.keys = keys;
		this.values = values;
		setParent();
	}

	private void setParent() {
		if (keys != null) {
			for (Expression key : keys) {
				if (key != null) key.setParent(this);
			}
		}
		if (values != null) {
			for (Expression value : values) {
				if (value != null) value.setParent(this);
			}
		}
	}

	public List<Expression> getKeys() {
		return keys;
	}

	public void setKeys(List<Expression> keys) {
		this.keys = keys;
	}

	public List<Expression> getValues() {
		return values;
	}

	public void setValues(List<Expression> values) {
		this.values = values;
	}
	
	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitDict(this, param);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dict dict = (Dict) o;
		return Objects.equals(keys, dict.keys) &&
				Objects.equals(values, dict.values);
	}

	@Override
	public int hashCode() {
		return Objects.hash(keys, values);
	}
}
