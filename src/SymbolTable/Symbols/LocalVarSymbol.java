package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

public class LocalVarSymbol extends Symbol {

    protected String type;
    protected String value;

    protected boolean isInitlialized = false;

    public LocalVarSymbol setVal(String value) {
        this.checkBeforeSettingVal();
        this.value = value;
        this.checkAfterSettingVal();
        return this;
    }
    public LocalVarSymbol setType(String type) {
        this.checkBeforeSettingVal();
        this.type = type;
        this.checkAfterSettingVal();
        return this;
    }

    public LocalVarSymbol setInitlialized(boolean initlialized) {
        this.checkBeforeSettingVal();
        isInitlialized = initlialized;
        this.checkAfterSettingVal();
        return this;
    }


    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isInitlialized() {
        return isInitlialized;
    }

    public LocalVarSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);

    }

    public LocalVarSymbol(String name, SymbolTable scope) {
        super(name, scope);

    }

    public void checkBeforeSettingVal() {
    }

    public void checkAfterSettingVal() {
    }

    public String toString() {
        return "LocalVarSymbol{" +
                "name='" + name + '\'' +
                ", line=" + line +
                ", col=" + col +
                ", type=" + type +
                ", isInitlialized=" + isInitlialized +
                ", value='" + value + '\'' +
                '}';
    }
}
