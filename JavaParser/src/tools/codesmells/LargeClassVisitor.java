package tools.codesmells;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LargeClassVisitor extends VoidVisitorAdapter {
	private final int limit = 100;
	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		int size = (c.getEnd().line - c.getBegin().line);
		if(size < limit)
			System.out.println(c.getName() + " is a small class.");
		else
			System.out.println(c.getName() + " is a large class.");
	}
}
