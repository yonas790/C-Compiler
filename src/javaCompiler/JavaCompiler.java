package javaCompiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import codegen.CodeGen;
import inter.stmt.Stmt;
import lexer.Lexer;
import parser.Parser;

public class JavaCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Get input file path
        String inputFilePath = args[0];
        File inputFile = new File(inputFilePath);
        
        System.setIn(new FileInputStream(inputFile));
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        parser.start(); // parse the program and print symbol table

        Stmt program = parser.getProgramStmt(); // get the parsed program
        CodeGen codeGen = new CodeGen();
        codeGen.gen(program);

        System.out.println("\n=== Generated Code ===");
        codeGen.getInstructions().forEach(System.out::println);
        
        // Save generated code to file
        saveGeneratedCode(inputFile, codeGen);
    }
    
    private static void saveGeneratedCode(File inputFile, CodeGen codeGen) throws IOException {
        // Create compiled directory if it doesn't exist
        Path compiledDir = Paths.get("compiled");
        if (!Files.exists(compiledDir)) {
            Files.createDirectories(compiledDir);
        }
        
        // Get the base filename without extension
        String inputFileName = inputFile.getName();
        String baseName = inputFileName.substring(0, inputFileName.lastIndexOf('.'));
        
        // Create output file path (e.g., compiled/09_mixed_operations.asm)
        Path outputPath = compiledDir.resolve(baseName + ".asm");
        
        // Write generated code to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
            codeGen.getInstructions().forEach(writer::println);
        }
        
        System.out.println("\n=== Output saved to: " + outputPath + " ===");
    }
    
}
