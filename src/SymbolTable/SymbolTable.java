package SymbolTable;

import java.util.Dictionary;

import SymbolTable.*;
import SymbolTable.Symbols.*;

public class SymbolTable {
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
        return scope + "_" + name + "_" + line + "_" + col;

    }

    public void checkBeforeAddingToTable() throws Exception {
    }

    public void checkAfterAddingToTable() throws Exception {
    }

    public SymbolTable addVal(String key, Symbol val) throws Exception {

        this.checkBeforeAddingToTable();
        this.val.put(key, val);
        this.checkAfterAddingToTable();
        return this;

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

    public SymbolTable setChildSymbolTable(SymbolTable child) {
        this.children[this.children.length] = child;
        return this;
    }
}
