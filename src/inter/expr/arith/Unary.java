package inter.expr.arith;

import inter.expr.Expr;
import lexer.Token;
import symbol.Type;

public class Unary extends Expr {

    public Expr expr;

    public Unary(Token token, Expr x) {
        super(token, null);
        this.expr = x;
        type = Type.maxNumericType(Type.INT, expr.type);
        if (type == null) {
            error("type error");
        }
    }

}