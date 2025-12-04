package inter.stmt;

public class Break extends Stmt {

    Stmt stmt;

    public Break() {
        if (!(Stmt.Enclosing instanceof While) || !(Stmt.Enclosing instanceof DoWhile)) {
            error("unenclosed break  ");
        }
        stmt = Stmt.Enclosing;
    }

}