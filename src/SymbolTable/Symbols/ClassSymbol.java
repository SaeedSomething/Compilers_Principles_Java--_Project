package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

public class ClassSymbol extends Symbol {

    public ClassSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);

    }

    public ClassSymbol(String name, SymbolTable scope) {
        super(name, scope);

    }

    public String checkBeforeSettingVal() {
        return null;
    }

    public String checkAfterSettingVal() {
        return null;
    }

    public String toString() {
        return "ClassSymbol{" +
                "name='" + name + '\'' +
                ", line=" + line +
                ", col=" + col +
                ", scope=" + scope +
                ", key='" + key + '\'' +
                '}';
    }

}
