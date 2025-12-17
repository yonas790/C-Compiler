package symbol;

import inter.expr.Id;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class Env {

    protected Hashtable table;
    protected Env prev;
    private List<Env> children;  // nested scopes

    public Env(Env prev) {
        table = new Hashtable();
        this.prev = prev;
        children = new ArrayList<>();
        if (prev != null) {
            prev.addChild(this);
        }
    }

    public void push(String w, Id i) {
        table.put(w, i);
    }

    public Id get(String w) {
        for (Env e = this; e != null; e = e.prev) {
            Id found = (Id) e.table.get(w);
            if (found != null) return found;
        }
        return null;
    }

    public Id getLocal(String w) {
        return (Id) table.get(w);
    }

    public Hashtable getTable() {
        return table;
    }

    private void addChild(Env child) {
        children.add(child);
    }

    public List<Env> getChildren() {
        return children;
    }
}
