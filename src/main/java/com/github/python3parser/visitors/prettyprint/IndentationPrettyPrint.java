package com.github.python3parser.visitors.prettyprint;

public class IndentationPrettyPrint {

	//class for managing indentationLevel
	
	private int indentationLevel;
	
	public IndentationPrettyPrint(int indentationLevel) {
		this.indentationLevel = indentationLevel;
	}
	
	public int getIndentationLevel() {
		return indentationLevel;
	}
	
	public String getIndentationString() {
		String string = new String();
		for (int i = 0; i < indentationLevel; i++) {
			string = string.concat("    ");
		}
		
		return string;
	}
}
