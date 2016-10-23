package tools.visitors.classdiagram;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.PrimitiveType.Primitive;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassMethodVisitor extends VoidVisitorAdapter {
	@Override
	public void visit(MethodDeclaration m, Object arg) {
		for(Parameter p : m.getParameters()){
			if(m.isPublic())
				System.out.println("-" + m.getName() + "(" + p.getName() + ":" + p.getType() +")" + ":" + m.getType().toString());
			else 
				System.out.println("+" + m.getName() + "(" + p.getName() + ":" + p.getType() +")" + ":" + m.getType().toString());
		}
	}
}
