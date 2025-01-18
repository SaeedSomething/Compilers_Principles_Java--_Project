package compiler;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.compiler.CodeGenerator.primary_return;

import SymbolTable.SymbolScope;
import SymbolTable.SymbolTable;
import SymbolTable.Symbols.ClassSymbol;
import SymbolTable.Symbols.LocalVarSymbol;
import SymbolTable.Symbols.MethodParamSymbol;
import SymbolTable.Symbols.MethodSymbol;
import gen.javaMinusMinusBaseListener;
import gen.javaMinusMinusBaseVisitor;
import gen.javaMinusMinusListener;
import gen.javaMinusMinusParser;
import gen.javaMinusMinusVisitor;
import compiler.*;

public class ProgramPrinter implements javaMinusMinusListener {
    private SymbolTable currentScope;
    private boolean importSeemn = false;
    private int indent = 0;
    private int forDeclaresCount = 0;
    // private Stack elseStack = new Stack<int>()
    private int elseCount = 0;
    private boolean elseBlockSeen = false;

    private void PrintIndents() {
        // System.out.print(indent + " ");
        for (int i = 0; i < indent; i++) {
            // System.out.print(indent);
            System.out.print(" ");
        }
        // System.out.print(" ");
    }

    @Override
    public void enterIfElseStatement(javaMinusMinusParser.IfElseStatementContext ctx) {
        PrintIndents();
        System.out.println("IF");
        indent++;
        PrintIndents();

        System.out.println("CONDITION " + ctx.getChild(2).getText());
        PrintIndents();

        System.out.println("BODY");
        indent++;

    }

    @Override
    public void enterNestedStatement(javaMinusMinusParser.NestedStatementContext ctx) {

        if (ctx.parent instanceof javaMinusMinusParser.IfElseStatementContext
                || ctx.parent instanceof javaMinusMinusParser.WhileStatementContext
                || ctx.parent instanceof javaMinusMinusParser.ForStatementContext) {

            // if its the else part of an if statement , then the scope should be else block
            // , else it should be the name of the statement type

            String name = ctx.parent.getChild(0).getText();
            if ((ctx.parent.getChild(0).getText().equals("if")
                    && ctx.parent.getChild(ctx.parent.getChildCount() - 1).equals(ctx))) {
                name = "else";
            }

            SymbolTable newScope = new SymbolTable(name, SymbolScope.BLOCK, currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;
        }

        if (ctx.parent != null && ctx.parent.getChild(ctx.parent.getChildCount() - 2) != null &&
                ctx.parent.getChild(ctx.parent.getChildCount() - 2).getText().contains("else") &&
                ctx.parent.getChild(ctx.parent.getChildCount() - 1) != null
                &&
                ctx.parent.getChild(ctx.parent.getChildCount() - 1).equals(ctx)) {

            // only if we have an else block , then the exit function should reduce the
            // indent
            elseBlockSeen = true;

            // else should be aligned with the BODY of the if statement
            indent--;

            PrintIndents();
            System.out.println("ELSE");
            indent++;
            PrintIndents();
            System.out.println("BODY");
            indent++;
        }

    }

    @Override
    public void enterArrayInstantiationExpression(javaMinusMinusParser.ArrayInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitLtExpression(javaMinusMinusParser.LtExpressionContext ctx) {
    }

    @Override
    public void enterInterfaceFieldDeclaration(javaMinusMinusParser.InterfaceFieldDeclarationContext ctx) {

        LocalVarSymbol localVarSymbol = null;
        try {
            localVarSymbol = new LocalVarSymbol(ctx.Identifier().getText(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
            currentScope.addVal(localVarSymbol.getName(), localVarSymbol);
            if (ctx.type().LSB() != null) {
                localVarSymbol.setType("array of " + ctx.type().getChild(0).getText());
            }
            if (ctx.EQ() != null) {
                localVarSymbol.setVal(ctx.expression().getText());
                localVarSymbol.setInitialized(true);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        PrintIndents();

        // same as DECLARE in classes
        System.out.print("INTERFACE DECLARE ");

        if (ctx.type() != null) {
            System.out.print(ctx.type().getText() + " ");
            ctx.type().localVarSymbol.setType(ctx.type().getText());
        }
        localVarSymbol.setVal(ctx.expression().getText());
        System.out.println(ctx.Identifier().getText() + " = " + ctx.expression().getText());

    }

    @Override
    public void exitImportClass(javaMinusMinusParser.ImportClassContext ctx) {
        // IMPORT and LIBRARY each have an indent
        indent -= 2;
        System.out.println();
    }

    @Override
    public void enterProgram(javaMinusMinusParser.ProgramContext ctx) {

        if (currentScope == null) {
            currentScope = new SymbolTable("program", SymbolScope.PROGRAM, null, ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
        } else {
            SymbolTable newScope = new SymbolTable("program", SymbolScope.PROGRAM, null, ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;
        }
    }

    @Override
    public void exitProgram(javaMinusMinusParser.ProgramContext ctx) {
        if (currentScope != null) {
            Stack<SymbolTable> stack = new Stack<>();
            stack.push(currentScope);

            while (!stack.isEmpty()) {
                SymbolTable scope = stack.pop();
                try {

                    compiler.Compiler.file.write(scope.toString() + "\n");
                    compiler.Compiler.file.write("  " + scope.getAllSymbols() + "\n");

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                ArrayList<SymbolTable> children = scope.getChildren();
                if (children != null) {
                    // List<SymbolTable> revChildren = children.reversed();
                    // for (int i = 0; i < children.size(); i++) {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stack.push(children.get(i));
                    }
                }
            }
        }
        // if (currentScope != null) {
        // Queue<SymbolTable> queue = new LinkedList<>();
        // queue.add(currentScope);

        // while (!queue.isEmpty()) {
        // SymbolTable scope = queue.poll();
        // try {

        // compiler.Compiler.file.write("Visited SymbolTable: " + scope.getName() +
        // scope.getScope() + "\n");
        // // System.out.println("Visited SymbolTable: " + scope.getName());
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // ArrayList<SymbolTable> children = scope.getChildren();
        // if (children != null) {
        // queue.addAll(children.reversed());
        // }
        // }
        // }

        currentScope = currentScope.getParent();
    }

    @Override
    public void enterMethodBody(javaMinusMinusParser.MethodBodyContext ctx) {
        PrintIndents();

        System.out.println("BODY ");
        indent++;
        if (ctx.RETURN() != null) {

            PrintIndents();

            // return is always followed by an expression

            System.out.print("RETURN ");
            if (ctx.expression().getText().contains(".")) {
                System.out.print("CALL ");
            }
            System.out.println(ctx.expression().getText());
        }

    }

    @Override
    public void enterArrayAccessExpression(javaMinusMinusParser.ArrayAccessExpressionContext ctx) {
    }

    @Override
    public void enterNotExpression(javaMinusMinusParser.NotExpressionContext ctx) {
    }

    @Override
    public void enterElseBlock(javaMinusMinusParser.ElseBlockContext ctx) {
    }

    @Override
    public void exitWhileBlock(javaMinusMinusParser.WhileBlockContext ctx) {
    }

    @Override
    public void exitConstructorDeclaration(javaMinusMinusParser.ConstructorDeclarationContext ctx) {
        currentScope = currentScope.getParent();
        indent--;
        System.out.println();
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitPowExpression(javaMinusMinusParser.PowExpressionContext ctx) {
    }

    @Override
    public void enterExpressionOrString(javaMinusMinusParser.ExpressionOrStringContext ctx) {
        // System.out.println(ctx.getText());
    }

    @Override
    public void enterConstructorDeclaration(javaMinusMinusParser.ConstructorDeclarationContext ctx) {

        try {
            MethodSymbol methodSymbol = new MethodSymbol(ctx.Identifier().toString(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()).setConstructor(true);

            currentScope.addVal(methodSymbol.getName(), methodSymbol);

            SymbolTable newScope = new SymbolTable(ctx.Identifier().toString(), SymbolScope.METHOD,
                    currentScope,
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;

            methodSymbol.setMethodScope(currentScope);

            if (ctx.accessModifier() != null) {
                methodSymbol.setAccessModifier(ctx.accessModifier().getText());
                PrintIndents();
                System.out.println("ACCESS_MODIFIER " + ctx.accessModifier().getText());
            }
            if (ctx.parameterList() != null) {
                for (int i = 0; i < ctx.parameterList(0).parameter().size(); i++) {
                    String type = ctx.parameterList(0).parameter(i).type().LSB() != null
                            ? "array of " + ctx.parameterList(0).parameter(i).type().getChild(0).getText()
                            : ctx.parameterList(0).parameter(i).type().getChild(0).getText();

                    String name = ctx.parameterList(0).parameter(i).Identifier().getText();
                    MethodParamSymbol methodParamSymbol = new MethodParamSymbol(name, currentScope, ctx.start.getLine(),
                            ctx.start.getCharPositionInLine()).setType(type);
                    // method param is a value in the method symbol
                    methodSymbol.addParamType(methodParamSymbol);
                    // method param is a symbol for a variable int the method scope
                    methodSymbol.getScope().addVal(methodParamSymbol.getName(), methodParamSymbol);

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        System.out.println("CONSTRUCTOR " + ctx.Identifier().getText());
        indent++;
    }

    @Override
    public void exitPrintStatement(javaMinusMinusParser.PrintStatementContext ctx) {
        // indent--;
        System.out.println();
    }

    @Override
    public void exitForStatement(javaMinusMinusParser.ForStatementContext ctx) {
        indent -= 2;
    }

    @Override
    public void exitVariableAssignmentStatement(javaMinusMinusParser.VariableAssignmentStatementContext ctx) {
        PrintIndents();
        System.out.println("ASSIGN " + ctx.Identifier().getText() + " = " + ctx.expression().getText());
    }

    @Override
    public void enterPrintStatement(javaMinusMinusParser.PrintStatementContext ctx) {

        PrintIndents();

        System.out.print("PRINT ");

        // //trying to use the enterCallExpression method to print the expression
        if (ctx.expressionOrString().getText().contains(".")) {
            System.out.print("CALL ");
        }
        System.out.println(ctx.expressionOrString().getText());

    }

    @Override
    public void enterParameter(javaMinusMinusParser.ParameterContext ctx) {

        PrintIndents();
        System.out.println("PARAMETER " + ctx.type().getText() + " " + ctx.Identifier().getText());

    }

    @Override
    public void exitAbstractMethodDeclaration(javaMinusMinusParser.AbstractMethodDeclarationContext ctx) {
        currentScope = currentScope.getParent();
        indent--;
        System.out.println();
    }

    @Override
    public void enterMainClass(javaMinusMinusParser.MainClassContext ctx) {

        // first we add the classSymbol to the program scope
        ClassSymbol classSymbol = null;
        try {

            classSymbol = new ClassSymbol("Main", currentScope, ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()).setMain(true);
            currentScope.addVal(classSymbol.getName(), classSymbol);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        // then we make a table for the class level scpoe
        SymbolTable newScope = new SymbolTable(ctx.className.getText(), SymbolScope.CLASS, currentScope,
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
        currentScope.setChildSymbolTable(newScope);
        currentScope = newScope;

        PrintIndents();
        System.out.println("CLASS " + ctx.className.getText());
        indent++;

        // each class has its methods as its symbol

        try {
            MethodSymbol methodSymbol = new MethodSymbol("main", currentScope, ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()).setMain(true);

            currentScope.addVal(methodSymbol.getName(), methodSymbol);

            // since the main method isnot called like any other , we should manually create
            // and go into its scope and set the line and col manully too
            newScope = new SymbolTable(ctx.className.getText(), SymbolScope.METHOD, currentScope,
                    ctx.start.getLine() + 1, ctx.start.getCharPositionInLine() + 23);
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;
            methodSymbol.setMethodScope(currentScope);

            MethodParamSymbol methodParamSymbol = new MethodParamSymbol("args", currentScope, ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()).setType("array of String");
            methodSymbol.addParamType(methodParamSymbol);
            currentScope.addVal(methodParamSymbol.getName(), methodParamSymbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintIndents();
        System.out.println("METHOD main");
        indent++;

        // if the behaviour of the main method needed clarifiacation , un-comment below
        // PrintIndents();
        // System.out.println("RETURN_TYPE void");
        // PrintIndents();
        // System.out.println("ACCESS_MODIFIER public");

    }

    @Override
    public void enterInterfaceMethodDeclaration(javaMinusMinusParser.InterfaceMethodDeclarationContext ctx) {

        try {

            // making the method symbol for the interface
            MethodSymbol methodSymbol = new MethodSymbol(ctx.Identifier().toString(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine())
                    .setInterface(true); // specifying its an interface
            SymbolTable newScope = new SymbolTable(ctx.Identifier().toString(), SymbolScope.METHOD,
                    currentScope,
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;
            methodSymbol.setMethodScope(currentScope);
        } catch (Exception e) {
            // TODO: handle exception
        }

        PrintIndents();
        System.out.println("INTERFACE METHOD " + ctx.Identifier().getText());
        indent++;

        // checking return type shuold be indented

        String returnType = ctx.type() != null ? ctx.type().getText() : "void";
        PrintIndents();
        System.out.println("RETURN " + returnType);
    }

    @Override
    public void enterAddExpression(javaMinusMinusParser.AddExpressionContext ctx) {
    }

    @Override
    public void enterIntArrayInstantiationExpression(javaMinusMinusParser.IntArrayInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitJavaType(javaMinusMinusParser.JavaTypeContext ctx) {
    }

    @Override
    public void exitThisExpression(javaMinusMinusParser.ThisExpressionContext ctx) {
    }

    @Override
    public void enterThisExpression(javaMinusMinusParser.ThisExpressionContext ctx) {
    }

    @Override
    public void exitParenExpression(javaMinusMinusParser.ParenExpressionContext ctx) {
    }

    @Override
    public void exitAddExpression(javaMinusMinusParser.AddExpressionContext ctx) {
    }

    @Override
    public void enterPowExpression(javaMinusMinusParser.PowExpressionContext ctx) {
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

    @Override
    public void exitObjectInstantiationExpression(javaMinusMinusParser.ObjectInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitNestedStatement(javaMinusMinusParser.NestedStatementContext ctx) {

        if (ctx.parent instanceof javaMinusMinusParser.IfElseStatementContext
                || ctx.parent instanceof javaMinusMinusParser.WhileStatementContext
                || ctx.parent instanceof javaMinusMinusParser.ForStatementContext) {
            currentScope = currentScope.getParent();

        }
        if (elseBlockSeen) {
            indent -= 1;
            elseBlockSeen = false;
        }
    }

    @Override
    public void exitInterfaceDeclaration(javaMinusMinusParser.InterfaceDeclarationContext ctx) {
        currentScope = currentScope.getParent();
        indent--;
        System.out.println();

    }

    @Override
    public void enterAbstractMethodDeclaration(javaMinusMinusParser.AbstractMethodDeclarationContext ctx) {

        try {
            MethodSymbol methodSymbol = new MethodSymbol(ctx.Identifier().toString(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()).setAbstract(true);

            SymbolTable newScope = new SymbolTable(ctx.Identifier().toString(), SymbolScope.METHOD,
                    currentScope,
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
            currentScope.setChildSymbolTable(newScope);
            currentScope = newScope;

            methodSymbol.setMethodScope(currentScope);

        } catch (Exception e) {
            // TODO: handle exception
        }
        PrintIndents();
        System.out.println("ABSTRACT METHOD " + ctx.Identifier().getText());
        indent++;
        // check for @Overriden method
        if (ctx.getChild(0).getText().equals("@Override")) {
            PrintIndents();
            System.out.println("OVERRIDE");
        }
        // checking return type shuold be indented and if null it means return type is
        // void
        PrintIndents();

        String returnType = ctx.type() != null ? ctx.type().getText() : "void";
        System.out.println("RETURN_TYPE " + returnType);

    }

    @Override
    public void exitLocalDeclaration(javaMinusMinusParser.LocalDeclarationContext ctx) {
    }

    @Override
    public void enterArrayAssignmentStatement(javaMinusMinusParser.ArrayAssignmentStatementContext ctx) {
    }

    @Override
    public void enterSet_type(javaMinusMinusParser.Set_typeContext ctx) {
    }

    @Override
    public void exitSet_type(javaMinusMinusParser.Set_typeContext ctx) {
    }

    @Override
    public void enterType(javaMinusMinusParser.TypeContext ctx) {

    }

    @Override
    public void exitLocalDeclarationStatement(javaMinusMinusParser.LocalDeclarationStatementContext ctx) {
    }

    @Override
    public void exitFieldDeclaration(javaMinusMinusParser.FieldDeclarationContext ctx) {
    }

    @Override
    public void enterInterfaceDeclaration(javaMinusMinusParser.InterfaceDeclarationContext ctx) {

        SymbolTable newScope = new SymbolTable(ctx.Identifier().toString(), SymbolScope.CLASS, currentScope,
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
        currentScope.setChildSymbolTable(newScope);
        currentScope = newScope;

        PrintIndents();
        System.out.println("INTERFACE " + ctx.Identifier().getText());
        indent++;
    }

    @Override
    public void exitIntLitExpression(javaMinusMinusParser.IntLitExpressionContext ctx) {
    }

    @Override
    public void enterMethodDeclaration(javaMinusMinusParser.MethodDeclarationContext ctx) {

        MethodSymbol methodSymbol = new MethodSymbol(ctx.Identifier().getText(), currentScope,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine());

        try {
            currentScope.addVal(methodSymbol.getName(), methodSymbol);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SymbolTable newScope = new SymbolTable(ctx.Identifier().toString(), SymbolScope.METHOD,
                currentScope,
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
        currentScope.setChildSymbolTable(newScope);
        currentScope = newScope;
        try {
            methodSymbol.setMethodScope(currentScope);

            if (ctx.parameterList() != null) {
                for (int i = 0; i < ctx.parameterList(0).parameter().size(); i++) {
                    String type = ctx.parameterList(0).parameter(i).type().LSB() != null
                            ? "array of " + ctx.parameterList(0).parameter(i).type().getChild(0).getText()
                            : ctx.parameterList(0).parameter(i).type().getChild(0).getText();

                    String name = ctx.parameterList(0).parameter(i).Identifier().getText();
                    MethodParamSymbol methodParamSymbol = new MethodParamSymbol(name, currentScope, ctx.start.getLine(),
                            ctx.start.getCharPositionInLine()).setType(type);
                    // method param is a value in the method symbol
                    methodSymbol.addParamType(methodParamSymbol);
                    // method param is a symbol for a variable int the method scope
                    methodSymbol.getScope().addVal(methodParamSymbol.getName(), methodParamSymbol);

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        PrintIndents();
        System.out.println("METHOD " + ctx.Identifier().getText());
        indent++;
        // check for @Overriden method
        if (ctx.getChild(0).getText().equals("@Override")) {
            PrintIndents();
            System.out.println("OVERRIDE");
            try {
                methodSymbol.setOverrides(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // checking return type shuold be indented
        PrintIndents();

        // when return type is void the ctx.type() is null
        String returnType = ctx.type() != null ? ctx.type().getText() : "void";
        if (!returnType.equals("void")) {
            try {
                methodSymbol.setReturnType(returnType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("RETURN_TYPE " + returnType);

    }

    @Override
    public void enterMethodCallExpression(javaMinusMinusParser.MethodCallExpressionContext ctx) {
        // seems you can call a function in another method body with this grammer , so
        // no need
        // if ((ctx.parent instanceof javaMinusMinusParser.NestedStatementContext)) {

        // PrintIndents();
        // System.out.println("CALL ");
        // }

    }

    @Override
    public void enterSubExpression(javaMinusMinusParser.SubExpressionContext ctx) {
    }

    @Override
    public void exitArrayLengthExpression(javaMinusMinusParser.ArrayLengthExpressionContext ctx) {
    }

    @Override
    public void enterImportClass(javaMinusMinusParser.ImportClassContext ctx) {
        if (!importSeemn) {
            importSeemn = true;
            PrintIndents();

            System.out.println("IMPORT ");

            indent++;
            PrintIndents();
            System.out.println("LIBRARY ");

            indent++;
        } else {
            // exitImport clears the 2 indents for IMPORT and LIBRARY after each call
            indent += 2;
        }

        PrintIndents();
        System.out.println("- " + ctx.getChild(1).getText());

        ClassSymbol classSymbol = new ClassSymbol(ctx.Identifier().toString(), currentScope,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine());
        try {
            currentScope.addVal(classSymbol.getName(), classSymbol);
            classSymbol.setImported(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exitNullLitExpression(javaMinusMinusParser.NullLitExpressionContext ctx) {
    }

    @Override
    public void exitArrayInstantiationExpression(javaMinusMinusParser.ArrayInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitVariableDeclaration(javaMinusMinusParser.VariableDeclarationContext ctx) {
    }

    @Override
    public void exitIfElseStatement(javaMinusMinusParser.IfElseStatementContext ctx) {
        indent -= 2;
        System.out.println();
    }

    @Override
    public void exitMethodCallExpression(javaMinusMinusParser.MethodCallExpressionContext ctx) {
    }

    @Override
    public void exitSubExpression(javaMinusMinusParser.SubExpressionContext ctx) {
    }

    @Override
    public void exitIfBlock(javaMinusMinusParser.IfBlockContext ctx) {
    }

    @Override
    public void exitNotExpression(javaMinusMinusParser.NotExpressionContext ctx) {
    }

    @Override
    public void enterVariableAssignmentStatement(javaMinusMinusParser.VariableAssignmentStatementContext ctx) {
    }

    @Override
    public void enterLocalDeclaration(javaMinusMinusParser.LocalDeclarationContext ctx) {
        LocalVarSymbol localVarSymbol = null;
        try {
            localVarSymbol = new LocalVarSymbol(ctx.Identifier().getText(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
            currentScope.addVal(localVarSymbol.getName(), localVarSymbol);
            if (ctx.type().LSB() != null) {
                localVarSymbol.setType("array of " + ctx.type().getChild(0).getText());
            }
            if (ctx.EQ() != null) {
                localVarSymbol.setVal(ctx.expression().getText());
                localVarSymbol.setInitialized(true);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        if (forDeclaresCount > 0) {
            forDeclaresCount--;
            return;
        }

        PrintIndents();
        System.out.println("DECLARE " + ctx.type().getText() + " " + ctx.Identifier() + " = "
                + ctx.expression().getStart().getText() +
                " " + ctx.expression().getText().replace(ctx.expression().getStart().getText(), ""));
    }

    @Override
    public void enterAccessModifier(javaMinusMinusParser.AccessModifierContext ctx) {
        PrintIndents();
        System.out.println("ACCESS_MODIFIER " + ctx.getText());

    }

    @Override
    public void enterLtExpression(javaMinusMinusParser.LtExpressionContext ctx) {
    }

    @Override
    public void exitMulExpression(javaMinusMinusParser.MulExpressionContext ctx) {
    }

    @Override
    public void enterFieldDeclaration(javaMinusMinusParser.FieldDeclarationContext ctx) {
        // field declaration calls variable declaration directly
        // so no need to print anything here
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void enterIntLitExpression(javaMinusMinusParser.IntLitExpressionContext ctx) {
    }

    @Override
    public void exitAccessModifier(javaMinusMinusParser.AccessModifierContext ctx) {
    }

    @Override
    public void exitInterfaceMethodDeclaration(javaMinusMinusParser.InterfaceMethodDeclarationContext ctx) {
        currentScope = currentScope.getParent();
        indent--;
        System.out.println();
    }

    @Override
    public void enterLocalDeclarationStatement(javaMinusMinusParser.LocalDeclarationStatementContext ctx) {
    }

    @Override
    public void exitBooleanLitExpression(javaMinusMinusParser.BooleanLitExpressionContext ctx) {
    }

    @Override
    public void exitElseBlock(javaMinusMinusParser.ElseBlockContext ctx) {
    }

    @Override
    public void exitMethodDeclaration(javaMinusMinusParser.MethodDeclarationContext ctx) {
        currentScope = currentScope.getParent();
        indent--;
        System.out.println();
    }

    @Override
    public void enterParenExpression(javaMinusMinusParser.ParenExpressionContext ctx) {
    }

    @Override
    public void exitParameter(javaMinusMinusParser.ParameterContext ctx) {
    }

    @Override
    public void enterBooleanLitExpression(javaMinusMinusParser.BooleanLitExpressionContext ctx) {
    }

    @Override
    public void exitClassDeclaration(javaMinusMinusParser.ClassDeclarationContext ctx) {

        currentScope = currentScope.getParent();

        indent--;
        System.out.println();
    }

    @Override
    public void enterMulExpression(javaMinusMinusParser.MulExpressionContext ctx) {
    }

    @Override
    public void exitArrayAccessExpression(javaMinusMinusParser.ArrayAccessExpressionContext ctx) {
    }

    @Override
    public void enterParameterList(javaMinusMinusParser.ParameterListContext ctx) {
    }

    @Override
    public void enterVariableDeclaration(javaMinusMinusParser.VariableDeclarationContext ctx) {
    }

    @Override
    public void enterWhileBlock(javaMinusMinusParser.WhileBlockContext ctx) {
    }

    @Override
    public void exitMethodBody(javaMinusMinusParser.MethodBodyContext ctx) {

        indent--;
        System.out.println();
    }

    @Override
    public void exitInterfaceFieldDeclaration(javaMinusMinusParser.InterfaceFieldDeclarationContext ctx) {
    }

    @Override
    public void exitMainClass(javaMinusMinusParser.MainClassContext ctx) {
        currentScope = currentScope.getParent().getParent();
        indent -= 2;
        System.out.println();
    }

    @Override
    public void exitArrayAssignmentStatement(javaMinusMinusParser.ArrayAssignmentStatementContext ctx) {
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
    }

    @Override
    public void exitVarDeclaration(javaMinusMinusParser.VarDeclarationContext ctx) {
    }

    @Override
    public void enterVarDeclaration(javaMinusMinusParser.VarDeclarationContext ctx) {
        LocalVarSymbol localVarSymbol = null;
        try {
            localVarSymbol = new LocalVarSymbol(ctx.Identifier().getText(), currentScope,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine());
            currentScope.addVal(localVarSymbol.getName(), localVarSymbol);
            if (ctx.type().LSB() != null) {
                localVarSymbol.setType("array of " + ctx.type().getChild(0).getText());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        PrintIndents();
        System.out.print("FIELD ");
        if (ctx.accessModifier() != null) {
            localVarSymbol.setAccessModifier(ctx.accessModifier().getText());
            if (ctx.accessModifier().getText().equals("public")) {
                System.out.print("PUBLIC ");
            } else if (ctx.accessModifier().getText().equals("private")) {
                System.out.print("PRIVATE ");
            } else if (ctx.accessModifier().getText().equals("protected")) {
                System.out.print("PROTECTED ");
            }
        }
        if (ctx.type() != null) {
            System.out.print(ctx.type().getText() + " ");
        }
        System.out.println(ctx.Identifier().getText());

        System.out.println();

    }

    @Override
    public void exitAndExpression(javaMinusMinusParser.AndExpressionContext ctx) {
    }

    @Override
    public void exitType(javaMinusMinusParser.TypeContext ctx) {
    }

    @Override
    public void enterArrayLengthExpression(javaMinusMinusParser.ArrayLengthExpressionContext ctx) {
    }

    @Override
    public void enterIfBlock(javaMinusMinusParser.IfBlockContext ctx) {
    }

    @Override
    public void exitIntArrayInstantiationExpression(javaMinusMinusParser.IntArrayInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitWhileStatement(javaMinusMinusParser.WhileStatementContext ctx) {
    }

    @Override
    public void enterWhileStatement(javaMinusMinusParser.WhileStatementContext ctx) {
    }

    @Override
    public void enterJavaType(javaMinusMinusParser.JavaTypeContext ctx) {
    }

    @Override
    public void enterNullLitExpression(javaMinusMinusParser.NullLitExpressionContext ctx) {
    }

    @Override
    public void enterForStatement(javaMinusMinusParser.ForStatementContext ctx) {

        PrintIndents();
        System.out.println("FOR");
        indent++;

        forDeclaresCount++;

        PrintIndents();
        System.out.println("DECLARE " + ctx.localDeclaration().type().getText() + " "
                + ctx.localDeclaration().Identifier() + " = " + ctx.localDeclaration().expression().getText());

        PrintIndents();
        System.out.println("CONDITION " + ctx.conditionExp.getText());

        PrintIndents();
        System.out.println("INCREMENT " + ctx.incrementExp.getText());

        PrintIndents();
        System.out.println("BODY");
        indent++;
    }

    @Override
    public void enterClassDeclaration(javaMinusMinusParser.ClassDeclarationContext ctx) {

        ClassSymbol classSymbol = new ClassSymbol(ctx.Identifier().get(0).toString(), currentScope, ctx.start.getLine(),
                ctx.start.getCharPositionInLine());

        PrintIndents();

        for (int i = 0; i < ctx.getChildCount(); i++) {

            // get all the detail of the class
            switch (ctx.getChild(i).getText()) {

                case "abstract":
                    System.out.print("ABSTRACT ");
                    try {

                        classSymbol.setAbstract(true);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    break;
                case "class":
                    System.out.print("CLASS ");
                    break;
                case "extends":
                    System.out.print("EXTENDS ");
                    try {
                        classSymbol.setParentClass(ctx.getChild(i + 1).getText());

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    break;

                case "implements":
                    System.out.print("IMPLEMENTS ");
                    try {
                        classSymbol.setParentClass(ctx.getChild(i + 1).getText());

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }

                    break;

                case "{":
                    System.out.println();
                    // break out of the for loop when we reach the class body
                    i = ctx.getChildCount();
                    // indent++;
                    break;
                default:
                    System.out.print(ctx.getChild(i) + " ");
                    break;
            }

        }
        try {
            currentScope.addVal(classSymbol.getName(), classSymbol);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        SymbolTable newScope = new SymbolTable(ctx.Identifier().get(0).toString(), SymbolScope.CLASS, currentScope,
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
        currentScope.setChildSymbolTable(newScope);
        currentScope = newScope;

        indent++;

    }

    @Override
    public void enterAndExpression(javaMinusMinusParser.AndExpressionContext ctx) {
    }

    @Override
    public void exitParameterList(javaMinusMinusParser.ParameterListContext ctx) {
    }

    @Override
    public void exitIdentifierExpression(javaMinusMinusParser.IdentifierExpressionContext ctx) {
    }

    @Override
    public void enterObjectInstantiationExpression(javaMinusMinusParser.ObjectInstantiationExpressionContext ctx) {
    }

    @Override
    public void enterIdentifierExpression(javaMinusMinusParser.IdentifierExpressionContext ctx) {
        // System.out.print(ctx.getText());
    }

    @Override
    public void exitExpressionOrString(javaMinusMinusParser.ExpressionOrStringContext ctx) {
    }
}
