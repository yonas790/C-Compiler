# Compiler (Java implementation)

A small compiler front-end implemented in Java. This repository contains a compact teaching-style compiler that demonstrates core compiler concepts: lexical analysis (lexer), parsing, abstract syntax tree (AST) construction, and a simple symbol/type environment.

This project is intended for learning and experimentation rather than production use.

## Contents

- `src/lexer` — token definitions and lexical analyzer.
- `src/parser` — parser implementation (recursive-descent / simple parser).
- `src/inter` — intermediate representation and AST node classes.
	- `expr` contains expression nodes (`Id`, `Constant`, `Rel`, `arith`, `logic`, ...).
	- `stmt` contains statement nodes (`If`, `While`, `Set`, `StmtSeq`, etc.).
- `src/symbol` — symbol table and type information (`Env`, `Type`, `Array`).
- `src/javaCompiler` — driver (main) class `JavaCompiler.java` which demonstrates how to run the compiler front-end.

## Quick start — build & run

Requirements
- Java Development Kit (JDK) 11 or newer.

Build

1. From the repository root run (Linux/macOS):

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

This compiles all Java sources under `src` and places class files in `out`.

Run

The project includes a driver class `javaCompiler.JavaCompiler`. How this driver accepts input may vary (see source for specifics). A common way to run it is:

```bash
java -cp out javaCompiler.JavaCompiler <path-to-source-file>
# Example (if there is a test file `examples/test.src`):
java -cp out javaCompiler.JavaCompiler examples/test.src
```

If the driver expects input from stdin or other arguments, check the top of `src/javaCompiler/JavaCompiler.java` for usage details.

## Project structure (short)

Top-level source layout (under `src`):

- `inter/` — AST nodes and intermediate representation (expressions and statements).
- `lexer/` — `Lexer`, `Token`, and token classes (`Num`, `Real`, `Tag`, `Keyword`, ...).
- `parser/` — `Parser.java` — constructs the AST from token stream.
- `symbol/` — type system and environment.
- `javaCompiler/` — `JavaCompiler.java` — simple driver to glue lexer/parser and interpreter/translator.

## Typical workflow

1. Write or open a small source file in the language accepted by this compiler.
2. Build the Java sources (see Build section).
3. Run the driver to parse and (optionally) execute or translate the program.

## Tips for contributors

- Read through `src/javaCompiler/JavaCompiler.java` first to see how the pieces are wired.
- Add small example inputs in an `examples/` folder and document expected outputs.
- Keep changes small and focused — this repository is primarily educational.

## Next steps / ideas

- Add CLI options (help, verbose, write AST to file).
- Implement code generation (bytecode or C translation) or an interpreter for the AST.
- Add unit tests for lexer and parser.

## License

This repository currently has no license file. Add a `LICENSE` if you want to publish or share under a specific license.

## Contact

If you want help extending this project, open an issue or create a pull request with a clear description of the change.
