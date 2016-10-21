package tools.classdiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassNameVisitor extends VoidVisitorAdapter {
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		System.out.println("Class name: " + c.getName());
	}
}
