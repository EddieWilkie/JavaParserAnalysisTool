package tools.classdiagram;

import java.util.HashMap;
import java.util.Map;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassImplementsVisitor extends VoidVisitorAdapter {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		for (ClassOrInterfaceType coi : c.getImplements())
			System.out.println("Realisation: " + coi.getName());
	}
}
