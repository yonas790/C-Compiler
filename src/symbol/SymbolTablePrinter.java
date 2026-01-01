package symbol;

import inter.expr.Id;
import java.util.Map;

public class SymbolTablePrinter {

    public static void printEnv(Env env, int level) {
        printIndent(level);
        System.out.println("Scope Level " + level);

        if (!env.getTable().isEmpty()) {
            printIndent(level);
            System.out.println("+------------+----------+--------+");
            printIndent(level);
            System.out.printf("| %-10s | %-8s | %-6s |\n",
                    "Identifier", "Type", "Offset");
            printIndent(level);
            System.out.println("+------------+----------+--------+");

            for (Map.Entry<String, Id> entry : env.getTable().entrySet()) {
                Id id = entry.getValue();
                printIndent(level);
                System.out.printf("| %-10s | %-8s | %-6d |\n",
                        entry.getKey(),
                        id.type,
                        id.offset
                );
            }

            printIndent(level);
            System.out.println("+------------+----------+--------+");
        }

        for (Env child : env.getChildren()) {
            printEnv(child, level + 1);
        }
    }

    private static void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }
}
