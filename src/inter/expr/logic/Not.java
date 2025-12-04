package inter.expr.logic;

import inter.expr.Expr;
import lexer.Token;

public class Not extends Logical
{

    public Not(Token token, Expr x1) {
        super(token, x1, x1);
    }
    
}