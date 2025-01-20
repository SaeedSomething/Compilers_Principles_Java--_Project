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

    public void checkBeforeAddingToTable(Symbol newValue) throws Exception {

        CheckDuplicateDeclarations(newValue);

    }

    public void checkAfterAddingToTable() throws Exception {
        for (String key : this.val.keySet()) {
            if (this.val.get(key) == null) {
                throw new Exception("SymbolTable: " + this.key + " has a null value for key: " + key);
            }
        }
    }

    private void CheckDuplicateDeclarations(Symbol newValue) throws Exception {

        switch (this.scope){
            case PROGRAM -> {
                // check for duplicate classes with same name in the program scope
                if (newValue instanceof ClassSymbol) {
                    for (Symbol symbol : this.val.values()) {
                        if (symbol instanceof ClassSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                // Error102 : in line [line:column] , class [name] has been defined already
                                throw new Exception("Error102: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", class " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                }
            }
            case CLASS -> {
                // check for duplicate method with same name in the class scope
                if (newValue instanceof MethodSymbol) {
                    for (Symbol symbol : this.val.values()) {
                        if (symbol instanceof MethodSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                //Error103 : in line [line:column] , method [name] has been defined already
                                throw new Exception("Error103: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", method " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                }
                else if (newValue instanceof LocalVarSymbol){
                    for (Symbol symbol : this.val.values()) {
                        if (symbol instanceof LocalVarSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                //Error104 : in line [line:column] , field [name] has been defined already
                                throw new Exception("Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", field " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                }
            }
            case METHOD -> {
                // check for duplicate local variables with same name in the method scope
                if (newValue instanceof LocalVarSymbol) {
                    for (Symbol symbol : this.val.values()) {
                        if (symbol instanceof LocalVarSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                throw new Exception("Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", field " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                    for (Symbol symbol : this.parent.val.values()) {
                        if (symbol instanceof LocalVarSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                throw new Exception("Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", field " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                }
            }

            case BLOCK -> {
                // check for duplicate fields with same name in the block scope and all parent scopes
                SymbolTable parent = this;
                while (parent != null) {
                    for (Symbol symbol : parent.val.values()) {
                        if (symbol instanceof LocalVarSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                throw new Exception("Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", field " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                    parent = parent.parent;
                }
            }
        }
    }

    public SymbolTable addVal(String key, Symbol val) throws Exception {

        this.checkBeforeAddingToTable(val);
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
        String str = "\n+++++++++++++++++++++++++++++++++++++++++++\n";
        if (val.size() == 0) {
            str += "No symbols in this table";
        } else {
            for (Map.Entry<String, Symbol> entry : val.entrySet()) {

                str += entry.getKey() + " : " + entry.getValue().toString() + "|\n";

            }
        }
        str += "\n+++++++++++++++++++++++++++++++++++++++++++\n";
        return str;
    }
}
