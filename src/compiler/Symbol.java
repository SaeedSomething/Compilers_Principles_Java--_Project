package compiler;

import java.util.Dictionary;
import java.util.Vector;

public abstract class Symbol {

    String name;
    int line;
    int col;
    SymbolTable scope;
    String key;
    Dictionary<String, String> val;

    public Symbol(String name, SymbolTable scope, int line, int col) {
        this.name = name;
        this.scope = scope;
        this.line = line;
        this.col = col;
        this.key = genKey(name, scope, line, col);

    }

    private String genKey(String name, SymbolTable scope, int line, int col) {
        return scope + name + "_" + "_" + line + "_" + col;

    }

    abstract public String checkBeforeAdding();

    abstract public String checkAfterAdding();

    public String addVal(String key, String val) {

        String errorPrompt = null;

        if (this.checkBeforeAdding() != null) {
            errorPrompt += "\n" + this.checkBeforeAdding();
        } else {
            this.val.put(key, val);
            errorPrompt = "\n" + this.checkAfterAdding();
        }
        return errorPrompt;
    }

    public String toString() {
        String str = "-" + "\n";
        str += "key : " + key + "|" + "val : ";
        while (val.keys().asIterator().hasNext()) {
            key = val.keys().asIterator().next();
            str += "\n" + key + " : " + val.get(key) + "|";
        }
        str += "\n" + "-";
        return str;
    }

    public String getVal(String key) {
        return val.get(key);
    }
}
