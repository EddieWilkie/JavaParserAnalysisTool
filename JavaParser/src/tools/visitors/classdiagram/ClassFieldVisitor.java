package tools.visitors.classdiagram;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassFieldVisitor extends VoidVisitorAdapter{
	public void visit(FieldDeclaration f, Object arg) {
			
		for(VariableDeclarator v : f.getVariables()){
			if(f.isPrivate())
			System.out.println("-" + v.getId().getName() + ":" + f.getType().toString());
		}
			
	}
}
