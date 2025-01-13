package SymbolTable.Symbols;

import SymbolTable.SymbolTable;

/**
 * Fuck Bazi nigga
 * 
 * Symbol class is the parent class for all the symbols in the symbol table.
 */
public abstract class Symbol {

    protected String name;
    protected int line;
    protected int col;
    protected SymbolTable scope;
    protected String key;

    public Symbol(String name, SymbolTable scope) {

        this.name = name;
        this.scope = scope;
        this.key = genKey(name, scope, line, col);
    }

    public Symbol(String name, SymbolTable scope, int line, int col) {
        this.name = name;
        this.scope = scope;
        this.line = line;
        this.col = col;
        this.key = genKey(name, scope, line, col);
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    public SymbolTable getScope() {
        return scope;
    }

    public String getKey() {
        return key;
    }

    /**
     * 
     * returns this to chain assignments
     * 
     * all setters should check the neccessary conditions before setting the value
     * and throw an exception if the conditions are not met.
     */
    protected Symbol setKey(String key) throws Exception {

        this.checkBeforeSettingVal();
        this.key = key;

        this.checkAfterSettingVal();
        return this;

    }

    /**
     * 
     * returns this to chain assignments
     * 
     * all setters should check the neccessary conditions before setting the value
     * and throw an exception if the conditions are not met.
     */
    public Symbol setLine(int line) throws Exception {

        this.checkBeforeSettingVal();
        this.line = line;
        setKey(genKey(name, scope, line, line));
        this.checkAfterSettingVal();
        return this;

    }

    /**
     * 
     * returns this to chain assignments
     * 
     * all setters should check the neccessary conditions before setting the value
     * and throw an exception if the conditions are not met.
     */
    public Symbol setCol(int col) throws Exception {

        this.checkBeforeSettingVal();
        this.col = col;
        setKey(genKey(name, scope, line, line));
        this.checkAfterSettingVal();
        return this;

    }

    /**
     * 
     * returns this to chain assignments
     * 
     * all setters should check the neccessary conditions before setting the value
     * and throw an exception if the conditions are not met.
     */
    public Symbol setName(String name) throws Exception {

        this.checkBeforeSettingVal();
        this.name = name;
        setKey(genKey(name, scope, line, line));
        this.checkAfterSettingVal();
        return this;

    }

    /**
     * key generation signature for the symbol
     */
    private String genKey(String name, SymbolTable scope, int line, int col) {
        return scope + "_" + name + "_" + line + "_" + col;

    }

    /**
     * gets called before setting the value
     * implement the functions necessary to check the conditions and call them in
     * this function
     * 
     * @throws Exception the semantic/compilation error message
     */
    abstract void checkBeforeSettingVal() throws Exception;

    /**
     * gets called before setting the value
     * implement the functions necessary to check the conditions and call them in
     * this function
     * 
     * @throws Exception the semantic/compilation error message
     */
    abstract void checkAfterSettingVal() throws Exception;

    abstract public String toString();
}
