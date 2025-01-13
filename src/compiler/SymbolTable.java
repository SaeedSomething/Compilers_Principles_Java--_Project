package compiler;

import java.util.Dictionary;

public abstract class SymbolTable {

    String name;
    int line;
    int col;
    SymbolScope scope;
    SymbolTable[] children;
    SymbolTable parent;
    String key;
    Dictionary<String, Symbol> val;

    public SymbolTable(String name, SymbolScope scope, SymbolTable parent, int line, int col) {
        this.name = name;
        this.scope = scope;
        this.parent = parent;
        this.line = line;
        this.col = col;
        this.key = genKey(name, scope, line, col);

    }

    private String genKey(String name, SymbolScope scope, int line, int col) {
        return scope + "_" + name + "_" + "_" + line + "_" + col;

    }

    abstract public String checkBeforeAddingToTable();

    abstract public String checkAfterAddingToTable();

    public String addVal(String key, Symbol val) {

        String errorPrompt = null;

        if (this.checkBeforeAddingToTable() != null) {
            errorPrompt += "\n" + this.checkBeforeAddingToTable();
        } else {
            this.val.put(key, val);
            errorPrompt = "\n" + this.checkAfterAddingToTable();
        }
        return errorPrompt;
    }

    public String toString() {
        String str = "-" + "\n";
        str += "Symbol : " + key + "|" + "val : ";
        while (val.keys().asIterator().hasNext()) {
            key = val.keys().asIterator().next();
            str += "\n" + key + " : " + val.get(key).toString() + "|";
        }
        str += "\n" + "-";
        return str;
    }

    public Symbol getSymbolFromTable(String key) {
        return val.get(key);
    }

    public void setChildSymbolTable(SymbolTable child) {
        this.children[this.children.length] = child;
    }
}
