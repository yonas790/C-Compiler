package codegen;

public class Instruction {
    public final String op;
    public final String arg;

    public Instruction(String op, String arg) {
        this.op = op;
        this.arg = arg;
    }

    @Override
    public String toString() {
        return arg == null ? op : op + " " + arg;
    }
}
