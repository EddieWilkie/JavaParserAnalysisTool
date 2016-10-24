package tools.visitors.codesmells;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LongMethodVisitor extends VoidVisitorAdapter {
	private final int limit = 10;

	public void visit(MethodDeclaration m, Object arg) {
		int size = (m.getEnd().line - m.getBegin().line);
		System.out.println();
		System.out.println("Checking the length of " + m.getName() + "(): ");

		if (size < limit)
			System.out.println("Welldone, " + m.getName() + " is a small method.");
		else if (size > limit)
			System.out.println("Uh oh, " + m.getName()
					+ "() is a very long method.\nSee if you can divide it up into smaller methods!");
	}
}
