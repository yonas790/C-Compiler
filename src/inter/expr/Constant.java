package inter.expr;

import lexer.Keyword;
import lexer.Num;
import lexer.Real;
import lexer.Token;
import symbol.Type;

public class Constant extends Expr {

    public Constant(Token token, Type type) {
        super(token, type);
    }

    public Constant(int i) {
        super(new Num(i), Type.INT);
    }
    
    public Constant(float i) {
        super(new Real(i), Type.FLOAT);
    }

    public static final Constant True = new Constant(Keyword.TRUE, Type.BOOLEAN),
            False = new Constant(Keyword.FALSE, Type.BOOLEAN);

}