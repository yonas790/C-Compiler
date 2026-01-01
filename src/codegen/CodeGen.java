package codegen;

import inter.expr.*;
import inter.expr.arith.*;
import inter.expr.logic.*;
import inter.stmt.*;

import java.util.ArrayList;
import java.util.List;

public class CodeGen {

    private List<Instruction> instructions = new ArrayList<>();
    private int labelCount = 0;

    public List<Instruction> getInstructions() {
        return instructions;
    }

    private String newLabel() {
        return "L" + (labelCount++);
    }

    // ================= Expressions =================
    public void gen(Expr e) {
        if (e instanceof Constant c) {
            instructions.add(new Instruction("PUSH", String.valueOf(c.token)));
        } else if (e instanceof Id id) {
            instructions.add(new Instruction("LOAD", id.name));
        } else if (e instanceof Arith a) {
            gen(a.expr1);
            gen(a.expr2);
            switch (a.token.tag) {
                case '+': instructions.add(new Instruction("ADD", null)); break;
                case '-': instructions.add(new Instruction("SUB", null)); break;
                case '*': instructions.add(new Instruction("MUL", null)); break;
                case '/': instructions.add(new Instruction("DIV", null)); break;
            }
        } else if (e instanceof Rel r) {
            gen(r.expr1);
            gen(r.expr2);
            instructions.add(new Instruction("CMP", r.token.toString())); // e.g., "<", "=="
        } else if (e instanceof Not n) {
            gen(n.expr1);
            instructions.add(new Instruction("NOT", null));
        } else if (e instanceof Access a) {
            gen(a.index);
            instructions.add(new Instruction("LOADARR", a.arrayid.name));
        }
    }

    // ================= Statements =================
    public void gen(Stmt s) {
        if (s instanceof StmtSeq seq) {
            gen(seq.stmtl);
            gen(seq.stmt2);
        } else if (s instanceof Set set) {
            gen(set.expr);
            instructions.add(new Instruction("STORE", set.id.name));
        } else if (s instanceof SetArrayElem sa) {
            gen(sa.index);
            gen(sa.expr);
            instructions.add(new Instruction("STOREARR", sa.array.name));
        } else if (s instanceof If i) {
            String elseLabel = newLabel();
            gen(i.expr);
            instructions.add(new Instruction("JZ", elseLabel));
            gen(i.stmt);
            instructions.add(new Instruction("LABEL", elseLabel));
        } else if (s instanceof IfElse ie) {
            String elseLabel = newLabel();
            String endLabel = newLabel();
            gen(ie.expr);
            instructions.add(new Instruction("JZ", elseLabel));
            gen(ie.stmt);
            instructions.add(new Instruction("JMP", endLabel));
            instructions.add(new Instruction("LABEL", elseLabel));
            gen(ie.stmt2);
            instructions.add(new Instruction("LABEL", endLabel));
        } else if (s instanceof While w) {
            String startLabel = newLabel();
            String endLabel = newLabel();
            instructions.add(new Instruction("LABEL", startLabel));
            gen(w.expr);
            instructions.add(new Instruction("JZ", endLabel));
            gen(w.stmt);
            instructions.add(new Instruction("JMP", startLabel));
            instructions.add(new Instruction("LABEL", endLabel));
        } else if (s instanceof DoWhile dw) {
            String startLabel = newLabel();
            String endLabel = newLabel();
            instructions.add(new Instruction("LABEL", startLabel));
            gen(dw.expr);
            gen(dw.expr);
            instructions.add(new Instruction("JNZ", startLabel));
            instructions.add(new Instruction("LABEL", endLabel));
        } else if (s instanceof Break) {
            instructions.add(new Instruction("BREAK", null)); // you can resolve later during VM execution
        } else if (s == Stmt.Null) {
            // do nothing
        }
    }
}
