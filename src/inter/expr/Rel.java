package inter.expr;

import inter.expr.logic.Logical;
import lexer.Token;
import symbol.Array;
import symbol.Type;

public class Rel extends Logical {

    public Rel(Token token, Expr x1, Expr x2) {
        super(token, x1, x2);
    }

    @Override
    public Type check(Type p1, Type p2) {
        if (p1 instanceof Array || p2 instanceof Array) {
            return null;
        } else if (p1 == p2) {
            return Type.BOOLEAN;
        } else {
            return null;
        }
    }

}