package symbol;

import inter.expr.Id;

public class SymbolTreePrinter {

    public static void printEnv(Env env) {
        printEnv(env, 0);
    }

    private static void printEnv(Env env, int level) {
        if (env == null) return;

        String indent = "  ".repeat(level);
        System.out.println(indent + "Scope level " + level + ":");

        // print all identifiers in this scope
        for (Object key : env.getTable().keySet()) {
            Id id = (Id) env.getTable().get(key);
            String type = id.type != null ? id.type.toString() : "null";
            System.out.println(indent + "  " + id.name + " : " + type + " (offset=" + id.offset + ")");
        }

        // recursively print children scopes
        for (Env child : env.getChildren()) {
            printEnv(child, level + 1);
        }
    }
}
