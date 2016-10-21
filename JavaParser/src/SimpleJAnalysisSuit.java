import java.awt.List;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleJAnalysisSuit {
	
	static ArrayList<IfStmt> iffs = new ArrayList<>();
	static int switchNum = 0;
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
        System.out.println("Simple ClassDiagram Analysis: ");
        new  ClassDiagramVisitor().visit(cu,null);
        System.out.println();
        System.out.println("Simple OO Metric Analysis:");
        new CodeLineVisitor().visit(cu,null);
        new CommentRatioVisitor().visit(cu, null);
        new IMPLVisitor().visit(cu,null);
        new WMCVisitor().visit(cu,null);
        new WMCnpVisitor().visit(cu,null);
        new NOCVisitor().visit(cu,null);
        new VariableVisitor().visit(cu, null);
        new CyclomaticComplexityVisitor().visit(cu, null);
        System.out.println();
        System.out.println("Simple Bad Smells Analysis: ");
        new LongMethodVisitor().visit(cu, null);
        System.out.println();
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
	    		

	    		System.out.println("Class Methods: ");
	    		for(MethodDeclaration m : c.getMethods()){
	    			System.out.println(m.getDeclarationAsString());
	    		}
	    	
	    		super.visit(c, arg);
	    	}
	    	
	    }
	private static class CodeLineVisitor extends VoidVisitorAdapter{
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			System.out.println("(LOC) = " + (n.getEnd().line - n.getBegin().line));
		}
	}
	
	private static class IMPLVisitor extends VoidVisitorAdapter{
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			System.out.println("(IMPL) = " + n.getImplements().size());
		}
	}
	
	private static class WMCVisitor extends VoidVisitorAdapter{
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			System.out.println("(WMC) = " + n.getMethods().size());
		}
	}
	
	private static class WMCnpVisitor extends VoidVisitorAdapter{
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			int WMCnp = 0;
			for(MethodDeclaration m : n.getMethods()){
				for(Modifier mod : m.getModifiers()){
					if(!mod.getLib().equals(("private"))){
						WMCnp++;
					}
				}
			}
			System.out.println("(WMCnp) = " + WMCnp);
		}
		
	}
	
	private static class DITVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			System.out.println("NOC = " + n.getChildrenNodes().size());
		}
	}
	
	private static class NOCVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			System.out.println("(NOC) = " + n.getChildrenNodes().size());
		}
	}
	
	private static class CommentRatioVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg){
			int begin = n.getBegin().line;
			int end = n.getEnd().line;
			float numberOfLines = (end - begin);
			float numberOfComments = n.getAllContainedComments().size();
			float ratio = (numberOfComments/numberOfLines)*100;
			System.out.println("(CR) = " + Math.round(ratio) + "%");
		}
	}
	
	private static class CyclomaticComplexityVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(MethodDeclaration m, Object arg){
			VoidVisitorAdapter iff = new IfVisitor();
			VoidVisitorAdapter sw = new SwitchVisitor();
			iff.visit(m, arg);
			sw.visit(m, arg);
			
			//System.out.println("CC = " + iffs.size() + switchNum);
		}
	}
	
	private static class IfVisitor extends VoidVisitorAdapter {
		
		@Override
		public void visit(IfStmt iff, Object arg){
			iffs.add(iff);
		}
	}
	
	private static class SwitchVisitor extends VoidVisitorAdapter {
		
		@Override
		public void visit(SwitchStmt sw, Object arg){
			switchNum += (sw.getEntries().size() + 1);
		}
	}
	
	private static class VariableVisitor extends VoidVisitorAdapter {
	    
    	@Override
    	public void visit(VariableDeclarationExpr n, Object arg) {
    	System.out.println("VARS = " + n.getVars().size());
    	}
    }
	
	private static class LongMethodVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(ClassOrInterfaceDeclaration c, Object arg ){
			System.out.println("Long Method Check: ");
			for(MethodDeclaration m : c.getMethods()){
			int numberOfLines = (m.getEnd().line - m.getBegin().line);
			int limit = 10;
			if(numberOfLines < limit)
				System.out.println(m.getName() + " Clean");
			else
				System.out.println(m.getName() + " Dirty");
			}
			System.out.println();
			System.out.println("Large Class: ");
			if((c.getEnd().line - c.getBegin().line) < 100)
				System.out.println(c.getName() + ": this class is a good size");
			else 
				System.out.println(c.getName() + ": I think you can split this class up!");
			
			System.out.println("Long Parameter Check: ");
			for(MethodDeclaration m : c.getMethods()){
			int numberOfParams = m.getParameters().size();
			int limit = 4;
			if(numberOfParams < limit)
				System.out.println(m.getName() + ": Good");
			else
				System.out.println(m.getName() + ": Bad");
			}
		}
	}
	
	
}
