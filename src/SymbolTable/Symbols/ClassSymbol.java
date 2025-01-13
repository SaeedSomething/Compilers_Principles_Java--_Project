package SymbolTable.Symbols;

import SymbolTable.SymbolScope;
import SymbolTable.SymbolTable;

public class ClassSymbol extends Symbol {
    protected boolean isAbstract = false;
    protected boolean isInterface = false;
    protected boolean isMain = false;
    protected boolean isImported = false;
    protected String parentClass = "Object";
    protected SymbolTable classScope;

    public ClassSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);
        this.classScope = new SymbolTable(this.name, SymbolScope.CLASS, scope, this.line, this.col);
    }

    public ClassSymbol(String name, SymbolTable scope) {
        super(name, scope);

    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isMain() {
        return isMain;
    }

    public boolean isImported() {
        return isImported;
    }

    public String getParentClass() {
        return parentClass;
    }

    public SymbolTable getClassScope() {
        return classScope;
    }

    public ClassSymbol setAbstract(boolean isAbstract) throws Exception {
        this.checkBeforeSettingVal();
        this.isAbstract = isAbstract;
        this.checkAfterSettingVal();
        return this;
    }

    public ClassSymbol setInterface(boolean isInterface) throws Exception {
        this.checkBeforeSettingVal();
        this.isInterface = isInterface;
        this.checkAfterSettingVal();
        return this;
    }

    public ClassSymbol setMain(boolean isMain) throws Exception {
        this.checkBeforeSettingVal();
        this.isMain = isMain;
        this.checkAfterSettingVal();
        return this;
    }

    public ClassSymbol setImported(boolean isImported) throws Exception {
        this.checkBeforeSettingVal();
        this.isImported = isImported;
        this.checkAfterSettingVal();
        return this;
    }

    public ClassSymbol setParentClass(String parentClass) throws Exception {
        this.checkBeforeSettingVal();
        this.parentClass = parentClass;
        this.checkAfterSettingVal();
        return this;
    }

    public ClassSymbol setClassScope(SymbolTable classScope) throws Exception {
        this.checkBeforeSettingVal();
        this.classScope = classScope;
        this.checkAfterSettingVal();
        return this;
    }

    public void checkBeforeSettingVal() {
    }

    public void checkAfterSettingVal() {
    }

    public String toString() {
        return "ClassSymbol{" +
                "name='" + name + '\'' +
                ", isAbstract=" + isAbstract +
                ", isInterface=" + isInterface +
                ", isMain=" + isMain +
                ", isImported=" + isImported +
                ", parentClass='" + parentClass + '\'' +
                ", classScope=" + classScope +
                ", line=" + line +
                ", col=" + col +
                ", scope=" + scope +
                '}';
    }

}
