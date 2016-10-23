package tools.visitors.codesmells;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LargeClassVisitor extends VoidVisitorAdapter {
	private final int limit = 100;

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		int size = (c.getEnd().line - c.getBegin().line);
		System.out.println("Checking the size of the class: ");

		if (size < limit)
			System.out.println("Welldone, " + c.getName() + " is a small class.");
		else if (size > limit)
			System.out.println("Uh oh, " + c.getName() + " is a very large class.\nSee if you can divide it up into smaller classes!");
		
	}
}
