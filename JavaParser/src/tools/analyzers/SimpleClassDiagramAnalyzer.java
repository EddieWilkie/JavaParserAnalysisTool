package tools.analyzers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleClassDiagramAnalyzer implements Analyzer{
	
	private List<VoidVisitorAdapter> visitors;
	
	public SimpleClassDiagramAnalyzer(){
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/classDiagram.json");
	}
	@Override
	public void analyze(CompilationUnit cu, Object arg) {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, arg);
	}
}
