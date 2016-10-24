package tools.visitors.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NOCVisitor extends VoidVisitorAdapter {
 static Map<String, List<String>> childMap = new HashMap<>();

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		for (ClassOrInterfaceType coi : c.getExtends()) {
			if(!childMap.containsKey(coi.getName())){
			childMap.put(coi.getName(), new ArrayList<String>());
			}
			childMap.get(coi.getName()).add(c.getName());
			
			if (childMap.containsKey(c.getName())) {
				System.out.println("Number of children = " + childMap.get(c.getName()).size());
		} else
			System.out.println("Number of children = 0");
			
		}
		
	}

}
