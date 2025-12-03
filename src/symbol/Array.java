package Symbol;
import lexer.Tag;

public class Array extends Type {

    public final Type of;
    public final int size;

    public Array(int size, Type of) {
        super("[]", Tag.ARRAY_TYPE, size * of.width);
        this.of = of;
        this.size = size;

    }

}