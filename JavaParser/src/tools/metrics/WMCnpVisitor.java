package tools.metrics;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class WMCnpVisitor extends VoidVisitorAdapter {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		int WMCnp = 0;
		for (MethodDeclaration m : n.getMethods()) {
			for (Modifier mod : m.getModifiers()) {
				if (!mod.getLib().equals(("private"))) {
					WMCnp++;
				}
			}
		}
		System.out.println("Weighted Method Countnp = " + WMCnp);
	}

}
