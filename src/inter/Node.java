package inter;

import lexer.Lexer;

public class Node {
    
    private int lexline;

    public Node() {
        lexline = Lexer.line;
    }

    public void error(String s) {
        throw new Error("near line " + lexline + ": " + s);
    }
}