package tools.visitors.metrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DITVisitor extends VoidVisitorAdapter {
	
	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		if(c.getExtends().isEmpty())
				System.out.println("Depth of Inheritance = 1");
			else{
				System.out.println("Depth of Inheritance = 2");
			}
		
	}
}
