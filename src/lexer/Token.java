package lexer;


public class Token {

    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }
    
    @Override
    public String toString() {
        // For single character tokens, return the character itself
        if (tag < 256) {
            return String.valueOf((char) tag);
        }
        return super.toString();
    }

}