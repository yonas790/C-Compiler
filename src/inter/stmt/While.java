package inter.stmt;

import inter.expr.Expr;
import symbol.Type;

public class While extends Stmt{
    public Expr expr; public Stmt stmt;

    public While() {
        expr= null;
        stmt=null;
    }

    public void init(Expr e, Stmt s) {
        this.expr = e;
        this.stmt = s;
        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in while");
        }
    }
    
    
}