
package Symbol;
import lexer.Keyword;
import lexer.Tag;

public class Type extends Keyword {

    public final int width;

    public Type(String lexeme, int tag, int width) {
        super(lexeme, tag);
        this.width = width;
    }

    public static Type INT = new Type("int", Tag.BASIC_TYPE, 4);
    public static Type FLOAT = new Type("float", Tag.BASIC_TYPE, 8);
    public static Type CHAR = new Type("char", Tag.BASIC_TYPE, 1);
    public static Type BOOLEAN = new Type("boolean", Tag.BASIC_TYPE, 1);

    
    public static boolean isNumeric(Type p) {
        return p == Type.CHAR || p == Type.INT || p == Type.FLOAT;
    }

    public static Type maxNumericType(Type t1, Type t2) {
        if (!isNumeric(t1) || !isNumeric(t2)) {
            return null;
        }
        if (t1 == Type.FLOAT || t2 == Type.FLOAT) {
            return Type.FLOAT;
        }
        if (t1 == Type.INT || t2 == Type.INT) {
            return Type.INT;
        }
        return Type.CHAR;
    }

}