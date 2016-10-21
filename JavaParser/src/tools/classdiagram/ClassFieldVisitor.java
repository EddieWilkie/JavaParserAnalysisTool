package tools.classdiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassFieldVisitor extends VoidVisitorAdapter{
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		System.out.println(c.getName() + "'s Fields: ");
		for (FieldDeclaration f : c.getFields()) {
			System.out.println(f.toString());
		}
	}
}
