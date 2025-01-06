package compiler;

import java.lang.reflect.Modifier;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import gen.javaMinusMinusBaseListener;
import gen.javaMinusMinusBaseVisitor;
import gen.javaMinusMinusListener;
import gen.javaMinusMinusParser;
import gen.javaMinusMinusVisitor;

public class ProgramPrinter implements javaMinusMinusListener {

    private int indent = 0;
    private int forDeclaresCount = 0;

    private void PrintIndents() {
        for (int i = 0; i < indent; i++) {
            // System.out.print(indent);
            System.out.print("  ");
        }
    }

    @Override
    public void enterIfElseStatement(javaMinusMinusParser.IfElseStatementContext ctx) {
    }

    @Override
    public void enterNestedStatement(javaMinusMinusParser.NestedStatementContext ctx) {
    }

    @Override
    public void enterArrayInstantiationExpression(javaMinusMinusParser.ArrayInstantiationExpressionContext ctx) {
    }

    @Override
    public void exitLtExpression(javaMinusMinusParser.LtExpressionContext ctx) {
    }

    @Override
    public void enterInterfaceFieldDeclaration(javaMinusMinusParser.InterfaceFieldDeclarationContext ctx) {
        PrintIndents();
    }

    @Override
    public void exitImportClass(javaMinusMinusParser.ImportClassContext ctx) {
    }

    @Override
    public void enterProgram(javaMinusMinusParser.ProgramContext ctx) {
    }

    @Override
    public void exitProgram(javaMinusMinusParser.ProgramContext ctx) {
    }

    @Override
    public void enterMethodBody(javaMinusMinusParser.MethodBodyContext ctx) {
        PrintIndents();

        System.out.println("BODY ");
        indent++;

        // ctx.RETURN()

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

    }

    @Override
    public void enterConstructorDeclaration(javaMinusMinusParser.ConstructorDeclarationContext ctx) {
        PrintIndents();
        System.out.println("CONSTRUCTOR " + ctx.Identifier().getText());
        indent++;
    }

    @Override
    public void exitPrintStatement(javaMinusMinusParser.PrintStatementContext ctx) {
        indent--;
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
        indent--;
        System.out.println();
    }

    @Override
    public void enterMainClass(javaMinusMinusParser.MainClassContext ctx) {
        System.out.println("CLASS " + ctx.className.getText());
        indent++;
        PrintIndents();
        System.out.println("METHOD main");
        indent++;
    }

    @Override
    public void enterInterfaceMethodDeclaration(javaMinusMinusParser.InterfaceMethodDeclarationContext ctx) {

        PrintIndents();
        System.out.println("INTERFACE METHOD " + ctx.Identifier().getText());
        indent++;

        // check for @Overriden method
        if (ctx.getChild(0).getText().equals("@Override")) {
            PrintIndents();
            System.out.println("OVERRIDE");
        }
        // checking return type shuold be indented
        if (ctx.type() != null) {

            PrintIndents();
            System.out.println("RETURN " + ctx.type().getText());
        }
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
    }

    @Override
    public void exitInterfaceDeclaration(javaMinusMinusParser.InterfaceDeclarationContext ctx) {
    }

    @Override
    public void enterAbstractMethodDeclaration(javaMinusMinusParser.AbstractMethodDeclarationContext ctx) {
        PrintIndents();
        System.out.println("ABSTRACT METHOD " + ctx.Identifier().getText());
        indent++;
        // check for @Overriden method
        if (ctx.getChild(0).getText().equals("@Override")) {
            PrintIndents();
            System.out.println("OVERRIDE");
        }
        // checking return type shuold be indented
        if (ctx.type() != null) {

            PrintIndents();
            System.out.println("RETURN " + ctx.type().getText());
        }
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
    }

    @Override
    public void exitIntLitExpression(javaMinusMinusParser.IntLitExpressionContext ctx) {
    }

    @Override
    public void enterMethodDeclaration(javaMinusMinusParser.MethodDeclarationContext ctx) {
        PrintIndents();
        System.out.println("METHOD " + ctx.Identifier().getText());
        indent++;
        // check for @Overriden method
        if (ctx.getChild(0).getText().equals("@Override")) {
            PrintIndents();
            System.out.println("OVERRIDE");
        }
        // checking return type shuold be indented
        if (ctx.type() != null) {

            PrintIndents();
            System.out.println("RETURN " + ctx.type().getText());
        }

    }

    @Override
    public void enterMethodCallExpression(javaMinusMinusParser.MethodCallExpressionContext ctx) {
    }

    @Override
    public void enterSubExpression(javaMinusMinusParser.SubExpressionContext ctx) {
    }

    @Override
    public void exitArrayLengthExpression(javaMinusMinusParser.ArrayLengthExpressionContext ctx) {
    }

    @Override
    public void enterImportClass(javaMinusMinusParser.ImportClassContext ctx) {
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
        indent--;
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

        PrintIndents();
        System.out.print("FIELD ");
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().contains("public")) {
                System.out.print("PUBLIC ");
            }

            else if (ctx.getChild(i).getText().contains("private")) {
                System.out.print("PRIVATE ");
            } else if (ctx.getChild(i).getText().contains("protected")) {
                System.out.print("PROTECTED ");
            }

            else {

                System.out.print(ctx.getChild(i).getText() + " ");
            }
        }

        // System.out.print(ctx.getChild(i).getText() + " ");
        // break;
        // default:
        // break;
        // }
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
        PrintIndents();

        for (int i = 0; i < ctx.getChildCount(); i++) {

            // get all the detail of the class
            switch (ctx.getChild(i).getText()) {

                case "abstract":
                    System.out.print("ABSTRACT ");
                    break;
                case "class":
                    System.out.print("CLASS ");
                    break;
                case "extends":
                    System.out.print("EXTENDS ");
                    break;

                case "implements":
                    System.out.print("IMPLEMENTS ");
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
    }

    @Override
    public void exitExpressionOrString(javaMinusMinusParser.ExpressionOrStringContext ctx) {
    }
}
