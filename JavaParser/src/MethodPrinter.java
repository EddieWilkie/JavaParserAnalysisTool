

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodPrinter {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
        new CommentVisitor().visit(cu, null);
        new ClassVisitor().visit(cu, null);
        new ConditionVisitor().visit(cu,null);
    }
    
    private static class MethodVisitor extends VoidVisitorAdapter {
    	
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
    
    private static class CommentVisitor extends VoidVisitorAdapter { 
    	
    	@Override 
    	public void visit(LineComment lc, Object arg){
    		System.out.println(lc.getContent());
    		super.visit(lc, arg);
    	}
    }
    private static class ClassVisitor extends VoidVisitorAdapter {
    	
    	@Override
    	public void visit(ClassOrInterfaceDeclaration c, Object arg){
    		System.out.println(c.getData());
    		super.visit(c, arg);
    	}
    }
    private static class ConditionVisitor extends VoidVisitorAdapter {
    	
    	@Override
    	public void visit(final ConditionalExpr n, Object arg){
    		System.out.println(n.getData());
    		super.visit(n, arg);
    	}
    }
}
