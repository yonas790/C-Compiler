# Simple Java Compiler

[![License: Unlicensed](https://img.shields.io/badge/License-Unlicensed-red.svg)](https://choosealicense.com/no-permission/)
[![Language: Java](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com/)

A compact, teaching-style compiler front-end implemented entirely in **Java**. This project is designed to demonstrate the core concepts of compiler construction in a clear and accessible manner, making it an ideal resource for students and enthusiasts.

> **Disclaimer:** This project is intended for **learning and experimentation** rather than production use.

## Core Compiler Concepts

This implementation covers the fundamental stages of a compiler front-end:

| Component | Description | Location |
| :--- | :--- | :--- |
| **Lexical Analysis (Lexer)** | Converts the source code into a stream of tokens. | `src/lexer` |
| **Parsing** | Uses a recursive-descent approach to build the Abstract Syntax Tree (AST) from the token stream. | `src/parser` |
| **Abstract Syntax Tree (AST)** | The intermediate representation of the source code's structure. | `src/inter` |
| **Symbol/Type Environment** | Manages variable and type information for semantic analysis. | `src/symbol` |

The AST nodes are further divided into:
*   **Expressions (`expr`):** Nodes for identifiers (`Id`), constants (`Constant`), relational (`Rel`), arithmetic, and logical operations.
*   **Statements (`stmt`):** Nodes for control flow and assignment (`If`, `While`, `Set`, `StmtSeq`, etc.).

## Quick Start: Build & Run

### Requirements

You must have the **Java Development Kit (JDK) 11 or newer** installed on your system.

### Build

From the repository root, execute the following commands to compile all Java sources. This will create an `out` directory and place all class files inside it.

```bash
# Create the output directory
mkdir -p out

# Compile all Java files and place class files in 'out'
javac -d out $(find src -name "*.java") // already created
```

### Run

The main driver class is `javaCompiler.JavaCompiler`. You can run the compiler with a source file as an argument:

```bash
# General command structure
java -cp out javaCompiler.JavaCompiler <path-to-source-file>

# Example: Running a sample test file
java -cp out javaCompiler.JavaCompiler src/test/01_empty_block.tst
```

### Running All Tests

The repository includes a suite of sample test files in `src/test/`. You can run them all using a simple shell loop.

**Linux/macOS (Bash/Zsh):**

```bash
for f in src/test/*.tst; do
    echo "--- Running test: $f ---"
    java -cp out javaCompiler.JavaCompiler "$f"
    echo "---------------------------"
done
```

**Windows (PowerShell):**

```powershell
Get-ChildItem src\test\*.tst | ForEach-Object {
    Write-Host "--- Running test: $($_.FullName) ---"
    java -cp out javaCompiler.JavaCompiler $_.FullName
    Write-Host "---------------------------"
}
```

This loop will execute each test file, display the output (which may include syntax errors or semantic results), and separate the results for clarity.

## Project Structure

The top-level source layout under the `src/` directory is organized as follows:

*   `inter/`: Abstract Syntax Tree (AST) nodes and intermediate representation classes.
*   `lexer/`: Token definitions, `Lexer`, and token classes (`Num`, `Real`, `Tag`, `Keyword`, etc.).
*   `parser/`: `Parser.java`, which constructs the AST from the token stream.
*   `symbol/`: Type system and environment management classes.
*   `javaCompiler/`: `JavaCompiler.java`, the simple driver class that glues the lexer, parser, and interpreter/translator together.
*   `test/`: Sample input programs (`.tst` files) to test the compiler's functionality.

## Contributing & Next Steps

Contributions and suggestions are welcome! Since this is an educational project, please keep changes small and focused.

### Tips for Contributors

1.  Start by reviewing `src/javaCompiler/JavaCompiler.java` to understand how the components are integrated.
2.  Add small, focused example inputs to the `src/test/` folder and document the expected output.

### Ideas for Extension

The following features would be excellent additions for learning and expanding the project:

*   **Command Line Interface (CLI):** Add options for help, verbose output, and writing the AST to a file.
*   **Back-End Implementation:** Implement code generation (e.g., Java bytecode or C translation) or an interpreter to execute the AST.
*   **Testing:** Add dedicated unit tests for the lexer and parser components.
*   **Test Suite:** Extend the test suite with more complex syntax and edge-case examples.

## 📞 Contact

If you encounter issues, have questions, or want to suggest an extension, please feel free to **open an issue** or **create a pull request** with a clear description of the change.