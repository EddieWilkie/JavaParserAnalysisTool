package tools.codesmells;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LongParameterVisitor extends VoidVisitorAdapter{
	private final int limit = 3;
	public void visit(MethodDeclaration m, Object arg){
		int size = (m.getEnd().line - m.getBegin().line);
		if(size < limit)
			System.out.println(m.getName() + " has less than three parameters.");
		else
			System.out.println(m.getName() + " has too many parameters.");
	}
}
