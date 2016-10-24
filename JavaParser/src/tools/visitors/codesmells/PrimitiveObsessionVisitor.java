package tools.visitors.codesmells;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class PrimitiveObsessionVisitor extends VoidVisitorAdapter {
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		int limit = 5;
		int count = 0;
		String[] primitives = { "int", "float", "double", "long", "short", "boolean", "void" };
		for (FieldDeclaration v : c.getFields()) {
			for (int i = 0; i < primitives.length; i++) {
				if (primitives[i].equals(v.getType().toString()))
					count++;
			}
			for (MethodDeclaration m : c.getMethods()) {
				for (int i = 0; i < primitives.length; i++) {
					if (primitives[i].equals(m.getType().toString()))
						count++;
				}
				for(Parameter p : m.getParameters())
					for (int i = 0; i < primitives.length; i++) {
						if (primitives[i].equals(p.getType().toString()))
							count++;
					}
			}
			
		}
		System.out.println("Checking the number of primitives: " );
		if (count < limit) {
			System.out.println("No Primitive Obsession" + count);
		} else {
			System.out.println("High use of primitive types shows primitive obsession.");
		}
		count = 0;
	}
}
