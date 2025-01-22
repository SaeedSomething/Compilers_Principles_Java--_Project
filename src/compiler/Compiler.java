package compiler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
    static String basepath = "src/compiler/examples/";

    static String npxPath = "C:\\Program Files\\nodejs\\npx";
    static String npmPath = "C:\\Program Files\\nodejs\\npm";
    /*
     * should have nodejs and this installed and in env path
     * if not try running
     * windows
     * choco install -g nodejs
     * 
     * linux
     * sudo apt install nodejs
     * sudo apt install npm
     * 
     */

    public static void main(String[] args) throws Exception {
        int i = 1;
        installMarkupCli();
        for (; i <= 6; i++) {
            file = new FileWriter(basepath + "test" + i +
                    ".symbolTable1.md");
            CharStream stream = CharStreams.fromFileName(basepath + "test" + i
                    + ".txt");

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
            callMarkupOnFile("test" + i + ".symbolTable1.md");
        }
    }

    public static int callMarkupOnFile(String filename) throws Exception {
        int exitCode = 0;

        try {
            String[] command = {
                    "cmd.exe", "/c", "npx", "markmap-cli", basepath + filename, "-o", basepath + filename + ".html"
            };

            // Create a ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to finish
            exitCode = process.waitFor();

            // Check the exit code
            if (exitCode == 0) {
                System.out.println("Displaying markmap representation was successful.");
            } else {
                System.out.println("Displaying markmap representation was not successful.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return exitCode;
    }

    public static int installMarkupCli() throws Exception {

        int exitCode = 0;

        try {
            String[] command = {
                    "cmd.exe", "/c", "npm", "install", "-g", "markmap-cli"
            };

            // Create a ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to finish
            exitCode = process.waitFor();

            // Check the exit code
            if (exitCode == 0) {
                System.out.println("Installing markmap-cli executed successfully.");
            } else {
                System.out.println("Installing markmap-cli execution failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return exitCode;
    }
}