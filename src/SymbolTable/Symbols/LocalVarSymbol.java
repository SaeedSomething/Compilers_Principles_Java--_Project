package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

public class LocalVarSymbol extends Symbol {

    protected String type;
    protected String value;
    protected String accessModifier = "public";
    protected boolean isInitialized = false;
    protected boolean isReturnVar = false;

    public LocalVarSymbol setVal(String value) {
        this.checkBeforeSettingVal();
        this.value = value;
        this.checkAfterSettingVal();
        return this;
    }

    public LocalVarSymbol setAccessModifier(String accessModifier) {
        this.checkBeforeSettingVal();
        this.accessModifier = accessModifier;
        this.checkAfterSettingVal();
        return this;
    }

    public LocalVarSymbol setType(String type) {
        this.checkBeforeSettingVal();
        this.type = type;
        this.checkAfterSettingVal();
        return this;
    }

    public LocalVarSymbol setInitialized(boolean initialized) {
        this.checkBeforeSettingVal();
        this.isInitialized = initialized;
        this.checkAfterSettingVal();
        return this;
    }

    public LocalVarSymbol setReturnVar(boolean isReturnVar) {
        this.checkBeforeSettingVal();
        this.isReturnVar = isReturnVar;
        this.checkAfterSettingVal();
        return this;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isReturnVar() {
        return isReturnVar;
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
                ", accessModifier=" + accessModifier +
                ", col=" + col +
                ", type=" + type +
                ", isInitialized=" + isInitialized +
                ", value='" + value + '\'' +
                '}';
    }
}
