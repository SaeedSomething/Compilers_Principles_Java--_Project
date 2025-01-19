package compiler;

import java.io.FileWriter;

import org.antlr.v4.runtime.CharStream;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
// import org.antlr.v4.runtime.tree.ParseTreeProperty;
import gen.javaMinusMinusLexer;
import gen.javaMinusMinusParser;
import gen.javaMinusMinusListener;
import gen.javaMinusMinusBaseListener;
import compiler.ProgramPrinter;

public class Compiler {
    public static FileWriter file = null;

    public static void main(String[] args) throws Exception {
        int i = 6;
        for (; i <= 6; i++) {

            file = new FileWriter("src/compiler/examples/test" + i + ".symbolTable.txt");
            CharStream stream = CharStreams.fromFileName("src/compiler/examples/test" + i + ".txt");

            javaMinusMinusLexer lexer = new javaMinusMinusLexer(stream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            javaMinusMinusParser parser = new javaMinusMinusParser(tokenStream);
            parser.setBuildParseTree(true);
            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            javaMinusMinusListener listener = new ProgramPrinter();
            System.out.println("Compiling # " + i + "...");
            walker.walk(listener, tree);
            file.close();
        }
    }

}
