package SymbolTable.Symbols;

import java.lang.reflect.Method;
import java.util.ArrayList;

import SymbolTable.SymbolTable;

public class MethodSymbol extends Symbol {
    protected boolean isAbstract = false;
    protected boolean isInterface = false;
    protected boolean isMain = false;
    protected boolean isConstructor = false;
    protected boolean overrides = false;

    protected String accessModifier = "public";
    protected String returnType = "void";
    protected ArrayList<MethodParamSymbol> paramTypes = new ArrayList<MethodParamSymbol>();
    protected SymbolTable methodScope;

    public MethodSymbol setAbstract(boolean isAbstract) throws Exception {
        this.checkBeforeSettingVal();
        this.isAbstract = isAbstract;
        this.checkAfterSettingVal();
        return this;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public MethodSymbol setAccessModifier(String accessModifier) throws Exception {
        this.checkBeforeSettingVal();
        this.accessModifier = accessModifier;
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

    public MethodSymbol addParamType(MethodParamSymbol paramType) throws Exception {
        this.checkBeforeSettingVal();
        this.paramTypes.add(paramType);
        this.checkAfterSettingVal();
        return this;
    }

    public MethodSymbol setParamType(ArrayList<MethodParamSymbol> paramTypes) throws Exception {
        this.checkBeforeSettingVal();
        this.paramTypes = paramTypes;
        this.checkAfterSettingVal();
        return this;
    }

    /**
     * method scope refers to the scope of the method (this method thats being
     * created)
     * and parent (what we specify in the constructor)refers to the parent class
     * scope ( the class that this method is declared in )
     * 
     * @param methodScope
     * @return
     * @throws Exception
     */
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

    public ArrayList<MethodParamSymbol> getParamType() {
        return paramTypes;
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
        return this.ln +
                "MethodSymbol{" +
                "name='" + name + '\'' +
                ", isAbstract=" + isAbstract +
                ", isInterface=" + isInterface +
                ", isMain=" + isMain +
                ", isConstructor=" + isConstructor +
                ", overrides=" + overrides +
                ", returnType='" + returnType + '\'' +
                ", paramType='" + paramTypes + '\'' +
                // ", methodScope=" + methodScope +
                ", line=" + line +
                ", col=" + col +
                ", scope=" + scope.getName() +
                '}' +
                this.ln;

    }
}
