package inter.expr;

import inter.Node;
import lexer.Token;
import symbol.Type;

public class Expr extends Node{
    
    public Token token;
    public Type type;

    public Expr(Token token, Type type) {
        this.token = token;
        this.type = type;
    }
    
}
