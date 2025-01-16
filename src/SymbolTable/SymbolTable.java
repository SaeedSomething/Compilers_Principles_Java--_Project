package SymbolTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SymbolTable.Symbols.*;

public class SymbolTable {
    public String ln = "\n********************************\n";
    protected String name;
    protected int line;
    protected int col;
    protected SymbolScope scope;
    protected ArrayList<SymbolTable> children = new ArrayList<SymbolTable>();
    protected SymbolTable parent;
    protected String key;
    protected Map<String, Symbol> val = new HashMap<String, Symbol>();

    /**
     * 
     * @param name
     * @param scope
     * @param parent
     * @param line
     * @param col
     */
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
        for (String key : this.val.keySet()) {
            if (this.val.get(key) == null) {
                throw new Exception("SymbolTable: " + this.key + " has a null value for key: " + key);
            }
        }
    }

    public SymbolTable addVal(String key, Symbol val) throws Exception {

        this.checkBeforeAddingToTable();
        this.val.put(key, val);
        this.checkAfterAddingToTable();
        return this;

    }

    public String toString() {
        String str = this.ln;
        str += "SCOPE : " + key + "|" + "val : ";

        str += this.ln;
        return str;
    }

    public Symbol getSymbolFromTable(String key) {
        return val.get(key);
    }

    /**
     * same as getSymbolFromTable
     * 
     * @param key
     * @return
     */
    public Symbol getVal(String key) {
        return val.get(key);
    }

    public SymbolTable setChildSymbolTable(SymbolTable child) {
        this.children.add(child);
        return this;
    }

    public ArrayList<SymbolTable> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public SymbolTable getParent() {
        return parent;
    }

    public SymbolScope getScope() {
        return scope;
    }

    public String getAllSymbols() {
        String str = "\n";
        for (Map.Entry<String, Symbol> entry : val.entrySet()) {
            str += entry.getKey() + " : " + entry.getValue().toString() + "|\n";
        }
        str += "\n";
        return str;
    }
}
