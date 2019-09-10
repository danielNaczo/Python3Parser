package com.github.python3parser.model.mods;

import java.util.Objects;
import java.util.Optional;

import com.github.python3parser.model.stmts.Statement;
import com.github.python3parser.visitors.basic.Python3ASTVisitor;

//in grammar: single_input
public class Interactive extends Mod{
	Optional<Statement> body;

	public Interactive(Statement body) {
		this.body = Optional.ofNullable(body);
	}

	public Optional<Statement> getBody() {
		return body;
	}

	public void setBody(Optional<Statement> body) {
		this.body = body;
	}

	public <R, P> R accept(Python3ASTVisitor<R, P> visitor, P param) {
		return visitor.visitInteractive(this, param);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interactive that = (Interactive) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }
}
