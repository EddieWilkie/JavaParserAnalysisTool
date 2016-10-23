package tools.classdiagram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import tools.analyzer.Analyzer;
import tools.analyzer.JsonVisitorParser;

public class SimpleClassDiagramAnalyzer implements Analyzer{
	
	private List<VoidVisitorAdapter> visitors;
	private CompilationUnit cu;
	
	public SimpleClassDiagramAnalyzer(CompilationUnit cu){
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/classDiagram.json");
		this.cu = cu;
	}
	@Override
	public void analyze() {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, null);
	}
}
