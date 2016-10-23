package tools.visitors.classdiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassAssociationVisitor extends VoidVisitorAdapter {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		List<String> assocs = new ArrayList<>();
		Map<String, List<String>> cMap = new HashMap<>();
		for (MethodDeclaration m : c.getMethods()) {
			if (isCustomClass(m.getType().toString()) && !assocs.contains(m.getType().toString()))
				assocs.add(m.getType().toString());
			for (Parameter p : m.getParameters()) {
				if (isCustomClass(p.getType().toString()) && !assocs.contains(p.getType().toString())) {
					assocs.add(p.getType().toString());

				}
			}

			for (FieldDeclaration f : c.getFields()) {
				if (isCustomClass(f.getType().toString()) && !assocs.contains(f.getType().toString()))
					assocs.add(f.getType().toString());
			}

		}
		System.out.println("Association/Aggregation: " + assocs);
	}

	private boolean isCustomClass(String type) {
		String[] primitives = { "char", "int", "boolean", "long", "short", "double", "float", "void","String[]","String"};
		for (int i = 0; i < primitives.length; i++) {
			if (primitives[i].equals(type))
				return false;
		}
		return true;
	}
}
