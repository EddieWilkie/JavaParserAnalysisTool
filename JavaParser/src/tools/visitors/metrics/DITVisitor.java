package tools.visitors.metrics;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DITVisitor extends VoidVisitorAdapter {
	
	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		List<String> s = new ArrayList<>();
	
		for(ClassOrInterfaceType coi : c.getExtends()){
			
		}
			
		
		
	}
}
