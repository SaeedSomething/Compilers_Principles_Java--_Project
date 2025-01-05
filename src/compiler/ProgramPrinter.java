package compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import gen.javaMinusMinusBaseListener;
import gen.javaMinusMinusListener;
import gen.javaMinusMinusParser;

public class ProgramPrinter implements javaMinusMinusListener {

    private int indent = 0;

    private void PrintIndents() {
        for (int i = 0; i < indent; i++) {
            System.out.print("    ");
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
        System.out.println("METHOD " + ctx.);
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
    }

    @Override
    public void exitPrintStatement(javaMinusMinusParser.PrintStatementContext ctx) {
    }

    @Override
    public void exitForStatement(javaMinusMinusParser.ForStatementContext ctx) {
    }

    @Override
    public void exitVariableAssignmentStatement(javaMinusMinusParser.VariableAssignmentStatementContext ctx) {
    }

    @Override
    public void enterPrintStatement(javaMinusMinusParser.PrintStatementContext ctx) {
    }

    @Override
    public void enterParameter(javaMinusMinusParser.ParameterContext ctx) {
    }

    @Override
    public void exitAbstractMethodDeclaration(javaMinusMinusParser.AbstractMethodDeclarationContext ctx) {
    }

    @Override
    public void enterMainClass(javaMinusMinusParser.MainClassContext ctx) {
        System.out.println("CLASS Main");
        indent++;
    }

    @Override
    public void enterInterfaceMethodDeclaration(javaMinusMinusParser.InterfaceMethodDeclarationContext ctx) {
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
    }

    @Override
    public void exitInterfaceFieldDeclaration(javaMinusMinusParser.InterfaceFieldDeclarationContext ctx) {
    }

    @Override
    public void exitMainClass(javaMinusMinusParser.MainClassContext ctx) {
        indent--;
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
    }

    @Override
    public void enterClassDeclaration(javaMinusMinusParser.ClassDeclarationContext ctx) {
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
