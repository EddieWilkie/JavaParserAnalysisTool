package tools.metrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class WMCVisitor extends VoidVisitorAdapter {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		System.out.println("Weighted Method Count = " + n.getMethods().size());
	}
}
