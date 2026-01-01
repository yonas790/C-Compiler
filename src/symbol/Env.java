package symbol;

import inter.expr.Id;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class Env {

    // Map: identifier name -> symbol (Id)
    protected Hashtable<String, Id> table;

    // Link to outer scope
    protected Env prev;

    // Nested scopes (for symbol table tree printing)
    private List<Env> children;

    public Env(Env prev) {
        this.table = new Hashtable<>();
        this.prev = prev;
        this.children = new ArrayList<>();

        if (prev != null) {
            prev.addChild(this);
        }
    }

    // Insert into current scope only
    public void push(String name, Id id) {
        table.put(name, id);
    }

    // Lookup through current scope and all outer scopes
    public Id get(String name) {
        for (Env e = this; e != null; e = e.prev) {
            Id found = e.table.get(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // Lookup only in current scope
    public Id getLocal(String name) {
        return table.get(name);
    }

    // Expose table for printing/debugging
    public Hashtable<String, Id> getTable() {
        return table;
    }

    private void addChild(Env child) {
        children.add(child);
    }

    public List<Env> getChildren() {
        return children;
    }
}
