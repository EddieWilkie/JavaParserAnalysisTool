

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
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
       
        new ClassDiagramVisitor().visit(cu, null);
        new ConstructorDeclarationVisitor().visit(cu, null);
       // new VariableVisitor().visit(cu, null);
       //new BlockCommentVisitor().visit(cu, null);
       
    }
    
    private static class MethodVisitor extends VoidVisitorAdapter {
    	
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
        	System.out.println("Class Method: ");
        	System.out.println(n.getDeclarationAsString());
            super.visit(n, arg);
        }
    }
    
    private static class ClassLineCommentVisitor extends VoidVisitorAdapter { 
    	
    	@Override 
    	public void visit(LineComment lc, Object arg){
    		System.out.println("Class Comments: ");
    		for(Comment c : lc.getAllContainedComments())
    			System.out.println(c.getContent());
    		super.visit(lc, arg);
    	}
    }
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
    	@Override
    	public void visit(ClassOrInterfaceDeclaration c, Object arg){
    		//Prints the classes name.
    		System.out.println("Class name: " + c.getName());
    		//Prints the inheritance
    		System.out.println("Class Implements: ");
    		for(ClassOrInterfaceType coi : c.getImplements())
    			System.out.println(coi.getName());
    		
    		System.out.println("Class Extends: ");
    		for(ClassOrInterfaceType coi : c.getExtends()){
    			
    				System.out.println(coi.getName());
    		}
    		
    		System.out.println("Class Fields: ");
    		for(FieldDeclaration f : c.getFields()){
    			System.out.println(f.toString());
    		}
    		

//    		System.out.println("Class Methods: ");
//    		for(MethodDeclaration m : c.getMethods()){
//    			System.out.println(m.getDeclarationAsString());
//    		}
//    		
    		VoidVisitorAdapter method = new MethodVisitor();
    		method.visit(c, arg);
    		
    		super.visit(c, arg);
    	}
    	
    }
    
    private static class BlockCommentVisitor extends VoidVisitorAdapter {
    	
    	@Override
    	public void visit(final BlockStmt n, Object arg){
    		System.out.println(n.getStmts());
    		super.visit(n, arg);
    	}
    }
    
    private static class ConstructorDeclarationVisitor extends VoidVisitorAdapter{
    	
    	@Override
    	public void visit(ConstructorDeclaration c, Object arg){
    		System.out.println("Class Constructor: " + c.getName());
    		System.out.println("Class Constructor Parameters: ");
    		for(Parameter p : c.getParameters()){
    			System.out.println(p.getType() + " " + p.getName());
    		}
    		
    	super.visit(c, arg);
    	}
    }
    
    private static class VariableVisitor extends VoidVisitorAdapter {
    
    	@Override
    	public void visit(VariableDeclarationExpr n, Object arg) {
    		//System.out.println("Local Variables:");
    		for (VariableDeclarator var : n.getVars()) {
    		System.out.println(var.getId().getName());
    		}
    	super.visit(n, arg);
    	}
    }
    
}
