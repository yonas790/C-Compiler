package inter.expr;
import lexer.Keyword;
import symbol.Type;


public class Id extends Expr{
    
    public int offset;
    
    public Id(Keyword token, Type type, int address) {
        super(token, type);
        this.offset= address;
    }
    
}