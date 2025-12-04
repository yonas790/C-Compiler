package inter.expr;

import lexer.Keyword;
import symbol.Type;
import lexer.Tag;

public class Access extends Expr {
    
    public Id arrayid;
    public Expr index;
    
    public Access(Id a, Expr i, Type p) {
        super(new Keyword("[]", Tag.ARRAY_TYPE), p);
        arrayid = a;        
        index = i;
        if (index.type != Type.INT) {
            error("array index expected to be int");
        }
    }
    
}