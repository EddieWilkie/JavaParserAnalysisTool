import java.awt.List;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SoftwareMetricAnalyser {
	
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
        new CodeLineVisitor().visit(cu,null);
        new CommentRatioVisitor().visit(cu, null);
        new IMPLVisitor().visit(cu,null);
        new WMCVisitor().visit(cu,null);
        new WMCnpVisitor().visit(cu,null);
        new NOCVisitor().visit(cu,null);
        new VariableVisitor().visit(cu, null);
        new CyclomaticComplexityVisitor().visit(cu, null);
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
			
			System.out.println("Number of iffs = " + iffs.size());
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
	
}
