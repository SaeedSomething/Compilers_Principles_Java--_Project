package SymbolTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.DuplicateDeclarationException;
import Exceptions.NullValueException;
import Exceptions.UndefinedVariableException;
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

    public SymbolTable addVal(String key, Symbol val) {
        try {
            this.checkBeforeAddingToTable(val);
        } catch (DuplicateDeclarationException e) {
            if (!(val instanceof LocalVarSymbol) || ((LocalVarSymbol) val).isReturnVar()) {
                System.out.println(e.getMessage());
                val.setName(val.getName() + "_" + val.getLine() + "_" + val.getCol());
            }
        }
        this.val.put(key, val);
        this.checkAfterAddingToTable(val);
        return this;
    }

    public void checkBeforeAddingToTable(Symbol newValue) {
        if (newValue instanceof LocalVarSymbol && ((LocalVarSymbol) newValue).isReturnVar()) {
            return;
        }
        CheckDuplicateDeclarations(newValue);
        CheckUndefinedVariable(newValue);
    }

    public void checkAfterAddingToTable(Symbol newValue) throws NullValueException {
        for (String key : this.val.keySet()) {
            if (this.val.get(key) == null) {
                throw new NullValueException("SymbolTable: " + this.key + " has a null value for key: " + key);
            }
        }
    }

    private void CheckDuplicateDeclarations(Symbol newValue) throws DuplicateDeclarationException {

        switch (this.scope) {
            case PROGRAM -> {
                // check for duplicate classes with same name in the program scope
                if (newValue instanceof ClassSymbol) {
                    for (Symbol symbol : this.val.values()) {
                        if (symbol instanceof ClassSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                // Error102 : in line [line:column] , class [name] has been defined already
                                throw new DuplicateDeclarationException(
                                        "Error102: in line " + newValue.getLine() + ":" + newValue.getCol() +
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
                                // Error103 : in line [line:column] , method [name] has been defined already
                                throw new DuplicateDeclarationException(
                                        "Error103: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                                ", method " + newValue.getName() + " has been defined already");
                            }
                        }
                    }
                } else
                    CheckDuplicateLocalVariables(newValue);
            }
            case METHOD -> {
                // check for duplicate local variables with same name in the method scope
                CheckDuplicateLocalVariables(newValue);

                // also check method parameters

            }

            case BLOCK -> {
                // check for duplicate fields with same name in the block scope
                CheckDuplicateLocalVariables(newValue);

            }
        }
    }

    private void CheckDuplicateLocalVariables(Symbol newValue) throws DuplicateDeclarationException {
        if (newValue instanceof LocalVarSymbol && ((LocalVarSymbol) newValue).getType() != null) {
            for (Symbol symbol : this.val.values()) {
                if (symbol instanceof LocalVarSymbol) {
                    if (symbol.getName().equals(newValue.getName())) {
                        throw new DuplicateDeclarationException(
                                "Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", field " + newValue.getName() + " has been defined already");
                    }
                }
            }
        }

        if (this.scope == SymbolScope.METHOD || this.scope == SymbolScope.BLOCK) {
            SymbolTable currentTable = this;
            if (this.scope != SymbolScope.METHOD) {

                while (currentTable.scope != SymbolScope.METHOD) {
                    currentTable = currentTable.parent;
                }
            }
            MethodSymbol methodTable = (MethodSymbol) currentTable.parent.getValByName(currentTable.name);

            for (MethodParamSymbol parameterSymbol : methodTable.getParamTypes()) {
                if (parameterSymbol != newValue && parameterSymbol.getName().equals(newValue.getName())) {
                    throw new DuplicateDeclarationException(
                            "Error104: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                    ", parameter " + newValue.getName() + " has been defined already");
                }
            }
        }
    }

    private void CheckUndefinedVariable(Symbol newValue) throws UndefinedVariableException {

        if (this.scope == SymbolScope.METHOD || this.scope == SymbolScope.BLOCK) {
            if (newValue instanceof LocalVarSymbol && ((LocalVarSymbol) newValue).getType() == null) {
                // check if the variable has been defined in current scope or any of the parent
                // scopes
                SymbolTable currentTable = this;
                boolean found = false;
                OuterLoop: while (currentTable != null) {
                    for (Symbol symbol : currentTable.val.values()) {
                        if (symbol instanceof LocalVarSymbol) {
                            if (symbol.getName().equals(newValue.getName())) {
                                found = true;
                                break OuterLoop;
                            }
                        }
                    }
                    currentTable = currentTable.parent;
                }
                if (!found) {
                    throw new UndefinedVariableException(
                            "Error105: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                    ", variable " + newValue.getName() + " has not been defined");
                }

                if (IsInteger(((LocalVarSymbol) newValue).getValue())) {
                    return;
                }

                String[] assignedVarNames = ((LocalVarSymbol) newValue).getValue().split("[+\\-*/%()]+");
                for (int i = 0; i < assignedVarNames.length; i++) {
                    if (assignedVarNames[i].contains("[")) {
                        int index = assignedVarNames[i].indexOf("[");
                        assignedVarNames[i] = assignedVarNames[i].substring(0, index);
                    }

                    currentTable = this;
                    found = false;

                    while (currentTable != null) {
                        for (Symbol symbol : currentTable.val.values()) {
                            if (symbol instanceof LocalVarSymbol) {
                                if (symbol.getName().equals(assignedVarNames[i])) {
                                    found = true;
                                }
                            }
                        }
                        currentTable = currentTable.parent;
                    }
                    if (!found) {
                        throw new UndefinedVariableException(
                                "Error105: in line " + newValue.getLine() + ":" + newValue.getCol() +
                                        ", variable " + ((LocalVarSymbol) newValue).getValue()
                                        + " has not been defined");
                    }
                }
            }
        }
    }

    private void CheckAssignmentMismatch(Symbol newValue) {

    }

    private boolean IsInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false; // Null or empty strings can't be integers
        }
        try {
            Integer.parseInt(str); // Try parsing the string
            return true; // If successful, it's an integer
        } catch (NumberFormatException e) {
            return false; // Exception indicates it's not an integer
        }
    }

    public String toString() {
        String str = "";
        // str += "SCOPE : " + key + "|" + "val : ";
        str += "# "; // for markdown formatting
        str += this.scope;
        str += "_";
        str += this.name;
        // str += this.ln;
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
    public Symbol getValByKey(String key) {
        return val.get(key);
    }

    public Map<String, Symbol> getVal() {
        return val;
    }

    public Symbol getValByNameGlobally(String name) {
        Symbol mSymbol = null;
        SymbolTable scope = this;
        while (scope != null) {
            // mSymbol = (MethodSymbol) scope.getValByKey(scope.getScope() + "_" +
            // ctx.Identifier().getText());

            for (Map.Entry<String, Symbol> smbl : scope.getVal().entrySet()) {
                String temp = smbl.getValue().getName();
                if (smbl.getValue().getName().equals(name)) {
                    return mSymbol = smbl.getValue();

                }

            }

            scope = scope.getParent();
        }
        return null;
    }

    public Symbol getValByName(String name) {
        for (Symbol symbol : val.values()) {
            if (symbol.getName().equalsIgnoreCase(name)) {
                return symbol;
            }
        }
        return null;
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
                // str += "## ";
                String str1 = entry.getKey();
                String str2 = entry.getValue().toString();
                str1 = entry.getValue().getKey();
                str += "## "; // for markdown formatting
                str += entry.getValue().getKey() + " : " + entry.getValue().toString() + "|\n";

            }
        }
        str += "\n+++++++++++++++++++++++++++++++++++++++++++\n";
        return str;
    }
}
