package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

public class MethodSymbol extends Symbol {
    protected boolean isAbstract = false;
    protected boolean isInterface = false;
    protected boolean isMain = false;
    protected boolean isConstructor = false;
    protected boolean overrides = false;

    protected String returnType = "void";
    protected String paramType = "void";
    protected SymbolTable methodScope;

    public MethodSymbol setAbstract(boolean isAbstract) throws Exception {
        this.checkBeforeSettingVal();
        this.isAbstract = isAbstract;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setInterface(boolean isInterface) throws Exception {
        this.checkBeforeSettingVal();
        this.isInterface = isInterface;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setMain(boolean isMain) throws Exception {
        this.checkBeforeSettingVal();
        this.isMain = isMain;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setConstructor(boolean isConstructor) throws Exception {
        this.checkBeforeSettingVal();
        this.isConstructor = isConstructor;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setOverrides(boolean overrides) throws Exception {
        this.checkBeforeSettingVal();
        this.overrides = overrides;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setReturnType(String returnType) throws Exception {
        this.checkBeforeSettingVal();
        this.returnType = returnType;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setParamType(String paramType) throws Exception {
        this.checkBeforeSettingVal();
        this.paramType = paramType;
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setMethodScope(SymbolTable methodScope) throws Exception {
        this.checkBeforeSettingVal();
        this.methodScope = methodScope;
        this.checkAfterSettingVal();
        return this;
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

    public boolean isConstructor() {
        return isConstructor;
    }

    public boolean overrides() {
        return overrides;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getParamType() {
        return paramType;
    }

    public SymbolTable getMethodScope() {
        return methodScope;
    }

    public MethodSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);

    }

    public MethodSymbol(String name, SymbolTable scope) {
        super(name, scope);

    }

    public void checkBeforeSettingVal() throws Exception {

    }

    public void checkAfterSettingVal() {

    }

    public String toString() {
        return "MethodSymbol{" +
                "name='" + name + '\'' +
                ", isAbstract=" + isAbstract +
                ", isInterface=" + isInterface +
                ", isMain=" + isMain +
                ", isConstructor=" + isConstructor +
                ", overrides=" + overrides +
                ", returnType='" + returnType + '\'' +
                ", paramType='" + paramType + '\'' +
                ", methodScope=" + methodScope +
                ", line=" + line +
                ", col=" + col +
                ", scope=" + scope +
                '}';
    }
}
