package SymbolTable.Symbols;

import java.util.Dictionary;

import SymbolTable.SymbolTable;

public class GenericSymbol extends Symbol {

    protected String name;
    protected int line;
    protected int col;
    protected SymbolTable scope;
    protected String key;
    protected Dictionary<String, String> val;

    public GenericSymbol(String name, SymbolTable scope, int line, int col) {
        super(name, scope, line, col);

    }

    public void checkBeforeSettingVal() {

    }

    public void checkAfterSettingVal() {

    }

    public String getVal(String key) {
        return val.get(key);
    }

    public void setVal(String key, String val) throws Exception {

        this.checkBeforeSettingVal();
        this.val.put(key, val);
        this.checkAfterSettingVal();
    }

    public String toString() {
        String str = this.ln;
        str += "key : " + key + "|" + "val : ";
        while (val.keys().asIterator().hasNext()) {
            key = val.keys().asIterator().next();
            str += "\n" + key + " : " + val.get(key) + "|";
        }
        str += this.ln;
        return str;
    }
}
