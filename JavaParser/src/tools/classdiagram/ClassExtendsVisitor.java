package tools.classdiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassExtendsVisitor extends VoidVisitorAdapter {
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		System.out.println(c.getName() + " Extends: ");
		for (ClassOrInterfaceType coi : c.getExtends()) {
			System.out.println(coi.getName());
		}
	}
}
