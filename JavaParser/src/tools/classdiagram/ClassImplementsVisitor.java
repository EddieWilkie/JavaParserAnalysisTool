package tools.classdiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassImplementsVisitor extends VoidVisitorAdapter{

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		System.out.println(c.getName() + " Implements: ");
		for (ClassOrInterfaceType coi : c.getImplements())
			System.out.println(coi.getName());
	}
}
