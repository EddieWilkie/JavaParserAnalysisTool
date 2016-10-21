package tools.codesmells;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LongMethodVisitor extends VoidVisitorAdapter{
	private final int limit = 10;
	public void visit(MethodDeclaration m, Object arg){
		int size = (m.getEnd().line - m.getBegin().line);
		if(size < limit)
			System.out.println(m.getName() + " is a short method.");
		else
			System.out.println(m.getName() + " is a long method.");
	}
}
