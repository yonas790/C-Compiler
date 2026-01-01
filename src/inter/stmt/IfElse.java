package inter.stmt;

import inter.expr.Expr;
import symbol.Type;

public class IfElse extends Stmt {

    public Expr expr;
    public Stmt stmt;
    public Stmt stmt2;

    public IfElse(Expr e, Stmt s1, Stmt s2) {
        this.expr = e;
        this.stmt = s1;
        this.stmt2 = s2;
        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in if");
        }
    }

}