package tools.visitors.metrics;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
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
