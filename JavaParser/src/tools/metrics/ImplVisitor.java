package tools.metrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ImplVisitor extends VoidVisitorAdapter {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		System.out.println("IMPL = " + n.getImplements().size());
	}
}