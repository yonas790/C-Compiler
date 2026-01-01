package javaCompiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import codegen.CodeGen;
import inter.stmt.Stmt;
import lexer.Lexer;
import parser.Parser;

public class JavaCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream(new File(args[0])));
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        parser.start();

        Stmt program = parser.block(); // or return root AST from parser
        CodeGen codeGen = new CodeGen();
        codeGen.gen(program);

        System.out.println("\n=== Generated Code ===");
        codeGen.getInstructions().forEach(System.out::println);
    }
    
}
