package inter.stmt;

public class StmtSeq extends Stmt{
    public Stmt stmtl; public Stmt stmt2;

    public StmtSeq(Stmt stmtl, Stmt stmt2) {
        this.stmtl = stmtl;
        this.stmt2 = stmt2;
    }
    
}