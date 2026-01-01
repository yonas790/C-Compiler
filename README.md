# Simple C-Style Compiler

[![License: Unlicensed](https://img.shields.io/badge/License-Unlicensed-red.svg)](https://choosealicense.com/no-permission/)
[![Language: Java](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com/)

A complete, teaching-oriented compiler implementation in **Java** that compiles C-style source code into assembly-like intermediate code. This project demonstrates all major stages of compilation: lexical analysis, parsing, semantic analysis, symbol table management, and code generation.

> **Disclaimer:** This project is designed for **educational purposes** and learning compiler construction concepts.

## 🌟 Features

- ✅ **Complete Compilation Pipeline**: From source code to generated assembly instructions
- ✅ **Lexical Analysis**: Tokenizes source code with support for keywords, operators, numbers, and identifiers
- ✅ **Recursive Descent Parser**: Builds Abstract Syntax Tree (AST) from token stream
- ✅ **Symbol Table Management**: Hierarchical scope-based symbol tables with offset tracking
- ✅ **Type System**: Support for `int`, `float`, `boolean`, and array types
- ✅ **Code Generation**: Generates stack-based assembly-like intermediate code
- ✅ **File Output**: Saves compiled code to `.asm` files in a `compiled/` directory
- ✅ **Comprehensive Test Suite**: 10 test files covering various language features

## 📋 Table of Contents

- [Quick Start](#quick-start)
- [Compiler Architecture](#compiler-architecture)
- [Language Features](#language-features)
- [Project Structure](#project-structure)
- [Usage Examples](#usage-examples)
- [Output Format](#output-format)
- [Test Suite](#test-suite)
- [Development](#development)

## 🚀 Quick Start

### Requirements

- **Java Development Kit (JDK) 11 or newer**

### Build

From the project root directory:

```bash
# Create output directory
mkdir -p out

# Compile all Java source files
javac -d out src/**/*.java
```

### Run

Navigate to the `out` directory and run the compiler:

```bash
cd out

# Compile a test file
java javaCompiler.JavaCompiler ../src/test/09_mixed_operations.tst
```

This will:
1. Parse the source code and build the AST
2. Display the symbol table tree
3. Generate and display intermediate code
4. Save the compiled code to `compiled/09_mixed_operations.asm`

## 🏗️ Compiler Architecture

This compiler implements a complete compilation pipeline with the following stages:

### 1. Lexical Analysis (Lexer)
**Location:** `src/lexer/`

The lexer tokenizes source code into a stream of tokens. It recognizes:
- **Keywords**: `if`, `else`, `while`, `do`, `break`, `true`, `false`
- **Data Types**: `int`, `float`, `boolean`
- **Operators**: `+`, `-`, `*`, `/`, `<`, `>`, `<=`, `>=`, `==`, `!=`, `&&`, `||`, `!`
- **Literals**: Integer numbers, floating-point numbers, boolean values
- **Identifiers**: Variable names
- **Delimiters**: `{`, `}`, `(`, `)`, `[`, `]`, `;`, `=`

**Key Classes:**
- `Lexer.java` - Main tokenizer
- `Token.java` - Base token class
- `Keyword.java` - Keyword tokens
- `Num.java` - Integer literals
- `Real.java` - Float literals
- `Tag.java` - Token type constants

### 2. Syntax Analysis (Parser)
**Location:** `src/parser/`

Uses **recursive descent parsing** to build an Abstract Syntax Tree (AST). The parser implements the following grammar:

```
PROG     → BLOCK
BLOCK    → { DECLS STMTS }
DECLS    → DECLS DECL | ε
DECL     → TYPE id ;
TYPE     → TYPE [num] | int | float | boolean
STMTS    → STMTS STMT | ε
STMT     → id = BOOL ; | if (BOOL) STMT | if (BOOL) STMT else STMT 
         | while (BOOL) STMT | do STMT while (BOOL) ;
         | break ; | BLOCK
BOOL     → BOOL || JOIN | JOIN
JOIN     → JOIN && EQUALITY | EQUALITY
EQUALITY → EQUALITY == REL | EQUALITY != REL | REL
REL      → EXPR < EXPR | EXPR <= EXPR | EXPR >= EXPR | EXPR > EXPR | EXPR
EXPR     → EXPR + TERM | EXPR - TERM | TERM
TERM     → TERM * UNARY | TERM / UNARY | UNARY
UNARY    → !UNARY | -UNARY | FACTOR
FACTOR   → (BOOL) | id | id[BOOL] | num | real | true | false
```

**Key Features:**
- Handles operator precedence correctly
- Supports nested scopes with proper symbol table management
- Error reporting with line numbers

### 3. Intermediate Representation (AST)
**Location:** `src/inter/`

The AST is composed of two main categories:

#### Expressions (`inter/expr/`)
- `Constant` - Literal values (numbers, booleans)
- `Id` - Variable identifiers
- `Arith` - Arithmetic operations (+, -, *, /)
- `Rel` - Relational operations (<, >, <=, >=, ==, !=, &&, ||)
- `Not` - Logical negation
- `Unary` - Unary minus
- `Access` - Array element access

#### Statements (`inter/stmt/`)
- `Set` - Variable assignment
- `SetArrayElem` - Array element assignment
- `If` - Conditional statement
- `IfElse` - Conditional with else branch
- `While` - While loop
- `DoWhile` - Do-while loop
- `Break` - Break statement
- `StmtSeq` - Statement sequence

### 4. Symbol Table & Type System
**Location:** `src/symbol/`

Manages variable declarations and type information with:
- **Hierarchical Scopes**: Nested block scopes with parent-child relationships
- **Type Checking**: Support for int, float, boolean, and array types
- **Offset Tracking**: Calculates memory offsets for variables
- **Scope Resolution**: Proper variable lookup through scope chain

**Key Classes:**
- `Env.java` - Symbol table environment (one per scope)
- `Type.java` - Type representations
- `Array.java` - Array type with dimensions
- `SymbolTablePrinter.java` - Pretty-prints the symbol table tree

### 5. Code Generation
**Location:** `src/codegen/`

Generates stack-based assembly-like intermediate code with instructions:

**Instructions:**
- `PUSH <value>` - Push value onto stack
- `LOAD <var>` - Load variable value onto stack
- `STORE <var>` - Store top of stack to variable
- `ADD`, `SUB`, `MUL`, `DIV` - Arithmetic operations
- `CMP <op>` - Compare operation
- `NOT` - Logical negation
- `JZ <label>` - Jump if zero
- `JNZ <label>` - Jump if not zero
- `JMP <label>` - Unconditional jump
- `LABEL <name>` - Label definition
- `LOADARR <array>` - Load array element
- `STOREARR <array>` - Store to array element

**Key Classes:**
- `CodeGen.java` - Code generator (visitor pattern)
- `Instruction.java` - Instruction representation

## 💻 Language Features

### Supported Features

#### Data Types
```c
int i;
float x;
boolean done;
int arr[10];
```

#### Arithmetic Operations
```c
x = a + b * c;
y = (a - b) / 2.0;
z = -x;
```

#### Boolean Expressions
```c
if (x > 5 && y < 10) { ... }
while (i < 100 || !done) { ... }
if (a == b) { ... }
```

#### Control Flow
```c
// If-else
if (condition) {
    statement;
} else {
    statement;
}

// While loop
while (condition) {
    statement;
}

// Do-while loop
do {
    statement;
} while (condition);

// Break
while (true) {
    if (condition) break;
}
```

#### Nested Scopes
```c
{
    int x;
    x = 5;
    {
        float y;
        y = 2.5;
        {
            boolean flag;
            flag = true;
        }
    }
}
```

## 📁 Project Structure

```
C-Compiler/
├── README.md
├── src/
│   ├── codegen/              # Code generation
│   │   ├── CodeGen.java      # Main code generator
│   │   └── Instruction.java  # Instruction representation
│   ├── inter/                # Intermediate representation (AST)
│   │   ├── Node.java         # Base AST node
│   │   ├── expr/             # Expression nodes
│   │   │   ├── Constant.java
│   │   │   ├── Id.java
│   │   │   ├── Access.java
│   │   │   ├── Expr.java
│   │   │   ├── Rel.java
│   │   │   ├── arith/
│   │   │   │   ├── Arith.java
│   │   │   │   └── Unary.java
│   │   │   └── logic/
│   │   │       └── Not.java
│   │   └── stmt/             # Statement nodes
│   │       ├── Stmt.java
│   │       ├── Set.java
│   │       ├── SetArrayElem.java
│   │       ├── If.java
│   │       ├── IfElse.java
│   │       ├── While.java
│   │       ├── DoWhile.java
│   │       ├── Break.java
│   │       └── StmtSeq.java
│   ├── parser/               # Parser
│   │   └── Parser.java       # Recursive descent parser
│   ├── lexer/                # Lexical analyzer
│   │   ├── Lexer.java        # Tokenizer
│   │   ├── Token.java        # Base token
│   │   ├── Keyword.java      # Keywords
│   │   ├── Num.java          # Integer literals
│   │   ├── Real.java         # Float literals
│   │   └── Tag.java          # Token tags
│   ├── symbol/               # Symbol table & types
│   │   ├── Env.java          # Environment/scope
│   │   ├── Type.java         # Type system
│   │   ├── Array.java        # Array types
│   │   └── SymbolTablePrinter.java
│   ├── javaCompiler/         # Main driver
│   │   └── JavaCompiler.java # Entry point
│   └── test/                 # Test files
│       ├── 01_empty_block.tst
│       ├── 02_variable_declarations.tst
│       ├── 03_simple_assignment.tst
│       ├── 04_arithmetic_ops.tst
│       ├── 05_boolean_expressions.tst
│       ├── 06_if_else.tst
│       ├── 07_while_loop.tst
│       ├── 08_do_while_break.tst
│       ├── 09_mixed_operations.tst
│       └── 10_complex_boolean.tst
└── out/
    ├── [compiled class files]
    └── compiled/             # Generated assembly files
        └── *.asm
```

## 📝 Usage Examples

### Example 1: Simple Arithmetic

**Input** (`test.tst`):
```c
{
    int x;
    int y;
    int result;
    
    x = 10;
    y = 20;
    result = x + y * 2;
}
```

**Run:**
```bash
java javaCompiler.JavaCompiler test.tst
```

**Output:**
```
=== Symbol Table Tree ===
Scope Level 0
  Scope Level 1
  +------------+----------+--------+
  | Identifier | Type     | Offset |
  +------------+----------+--------+
  | result     | int      | 8      |
  | y          | int      | 4      |
  | x          | int      | 0      |
  +------------+----------+--------+

=== Generated Code ===
PUSH 10
STORE x
PUSH 20
STORE y
LOAD x
LOAD y
PUSH 2
MUL
ADD
STORE result

=== Output saved to: compiled/test.asm ===
```

### Example 2: Control Flow

**Input:**
```c
{
    int i;
    i = 0;
    
    while (i < 5) {
        i = i + 1;
    }
}
```

**Generated Code:**
```
PUSH 0
STORE i
LABEL L0
LOAD i
PUSH 5
CMP <
JZ L1
LOAD i
PUSH 1
ADD
STORE i
JMP L0
LABEL L1
```

## 🧪 Test Suite

The project includes 10 comprehensive test files:

| Test File | Description |
|-----------|-------------|
| `01_empty_block.tst` | Empty block |
| `02_variable_declarations.tst` | Variable declarations with different types |
| `03_simple_assignment.tst` | Basic assignment statements |
| `04_arithmetic_ops.tst` | Arithmetic operations and precedence |
| `05_boolean_expressions.tst` | Boolean logic and comparisons |
| `06_if_else.tst` | Conditional statements |
| `07_while_loop.tst` | While loop structures |
| `08_do_while_break.tst` | Do-while loops with break |
| `09_mixed_operations.tst` | Complex nested structures |
| `10_complex_boolean.tst` | Advanced boolean expressions |

### Running All Tests

**Linux/macOS:**
```bash
cd out
for f in ../src/test/*.tst; do
    echo "--- Running: $f ---"
    java javaCompiler.JavaCompiler "$f"
    echo ""
done
```

**Windows (PowerShell):**
```powershell
cd out
Get-ChildItem ..\src\test\*.tst | ForEach-Object {
    Write-Host "--- Running: $($_.Name) ---"
    java javaCompiler.JavaCompiler $_.FullName
    Write-Host ""
}
```

## 🔧 Development

### Building from Source

```bash
# Clean previous build
rm -rf out

# Create output directory
mkdir -p out

# Compile
javac -d out src/**/*.java

# Navigate to output
cd out
```

### Adding New Features

The codebase is designed to be extensible:

1. **New Operators**: Add to `Lexer.java` and create corresponding AST nodes in `inter/expr/`
2. **New Statements**: Create new statement classes in `inter/stmt/` and update `Parser.java`
3. **New Data Types**: Extend `Type.java` and update the parser's `type()` method
4. **Code Generation**: Add new instruction types in `Instruction.java` and update `CodeGen.java`

### Code Organization

- **Single Responsibility**: Each class has a clear, focused purpose
- **Visitor Pattern**: Code generation uses visitor pattern for AST traversal
- **Recursive Descent**: Parser mirrors the grammar structure
- **Hierarchical Scopes**: Symbol table uses parent pointers for scope chain

## 🎓 Learning Resources

This compiler is organized to demonstrate key compiler concepts:

1. **Lexical Analysis**: See `Lexer.java` for tokenization techniques
2. **Parsing**: Study `Parser.java` for recursive descent implementation
3. **AST Construction**: Review `inter/` for tree node design
4. **Symbol Tables**: Examine `Env.java` for scope management
5. **Code Generation**: Analyze `CodeGen.java` for instruction generation

## 📈 Future Enhancements

Potential improvements and extensions:

- [ ] **Optimization**: Constant folding, dead code elimination
- [ ] **Arrays**: Full multi-dimensional array support
- [ ] **Functions**: Function declarations and calls
- [ ] **Structs**: User-defined types
- [ ] **Error Recovery**: Better error handling and recovery
- [ ] **Target Code**: Generate actual machine code or bytecode
- [ ] **Virtual Machine**: Implement an interpreter for the generated code
- [ ] **Debugger**: Add debugging support with breakpoints

## 🤝 Contributing

This is an educational project. Contributions that enhance learning value are welcome!

### Guidelines

1. Keep changes focused and well-documented
2. Add test cases for new features
3. Maintain the teaching-oriented style
4. Comment complex algorithms
5. Follow existing code conventions

## 📞 Contact

For questions, issues, or suggestions:
- Open an issue on the repository
- Create a pull request with clear descriptions
- Reach out for collaboration opportunities

## 📄 License

This project is unlicensed and provided as-is for educational purposes.

---

**Happy Compiling! 🚀**