package tools.classdiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassMethodVisitor extends VoidVisitorAdapter {
	@Override
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		System.out.println(c.getName() + "'s methods: ");
		for (MethodDeclaration m : c.getMethods()) {
			System.out.println(m.getDeclarationAsString());
		}
	}
}
