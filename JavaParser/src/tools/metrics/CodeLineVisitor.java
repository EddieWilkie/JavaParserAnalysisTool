package tools.metrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CodeLineVisitor extends VoidVisitorAdapter {
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		System.out.println("Total lines of code = " + (n.getEnd().line - n.getBegin().line));
	}
}
