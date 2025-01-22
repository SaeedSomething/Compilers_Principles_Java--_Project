package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

public class MethodParamSymbol extends Symbol {
    protected String type;

    public MethodParamSymbol setType(String type) {
        this.checkBeforeSettingVal();
        this.type = type;
        this.checkAfterSettingVal();
        return this;
    }

    public String getType() {
        return type;
    }

    public MethodParamSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);

    }

    public MethodParamSymbol(String name, SymbolTable scope) {
        super(name, scope);

    }

    public void checkBeforeSettingVal() {
    }

    public void checkAfterSettingVal() {
    }

    public String toString() {

        return "MethodParamSymbol{" +
                "name='" + name + '\'' +
                ", line=" + line +
                ", col=" + col +
                ", type='" + type + '\'' +
                '}';
        // this.ln;
    }

}
