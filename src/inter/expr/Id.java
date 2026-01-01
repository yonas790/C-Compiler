package inter.expr;

import lexer.Token;
import symbol.Type;

public class Id extends Expr {
    public String name;
    public int offset;

    public Id(Token token, String name, Type type, int offset) {
        super(token, type);
        this.name = name;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return name;
    }
}
