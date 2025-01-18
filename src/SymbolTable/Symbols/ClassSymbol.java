package SymbolTable.Symbols;

import SymbolTable.SymbolScope;
import SymbolTable.SymbolTable;

public class ClassSymbol extends Symbol {
    protected boolean isAbstract = false;
    protected boolean isInterface = false;
    protected boolean isMain = false;
    protected boolean isImported = false;
    protected String accessModifier = "public";
    protected String parentClass = "Object";
    
    protected SymbolTable classScope;


    public ClassSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);
        // this.classScope = new SymbolTable(this.name, SymbolScope.CLASS, scope,
        // this.line, this.col);
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

    public String getAccessModifier() {
        return accessModifier;
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

    public ClassSymbol setAccessModifier(String accessModifier) throws Exception {
        this.checkBeforeSettingVal();
        this.accessModifier = accessModifier;
        this.checkAfterSettingVal();
        return this;
    }

    /**
     * method scope refers to the scope of the class (this class thats being
     * created)
     * and parent (what we specify in the constructor)refers to the parent program
     * scope ( the program that this method is declared in )
     * 
     * @param classScope
     * @return
     * @throws Exception
     */
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
        return this.ln +
                "\n" + "ClassSymbol{" +
                "\n" + "name='" + name + '\'' +
                "\n" + ", isAbstract=" + isAbstract +
                "\n" + ", isInterface=" + isInterface +
                "\n" + ", isMain=" + isMain +
                "\n" + ", isImported=" + isImported +
                "\n" + ", parentClass='" + parentClass + '\'' +
                "\n" + // " + ", classScope=" + classScope +
                "\n" + ", line=" + line +
                "\n" + ", col=" + col +
                "\n" + ", scope=" + scope.getName() +
                "\n" + '}' +
                this.ln;
    }

}
