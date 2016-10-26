package tools.analyzers;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import tools.jsonparsers.JsonVisitorParser;

public class SimpleMetricAnalyzer implements Analyzer {
	private List<VoidVisitorAdapter> visitors;

	public SimpleMetricAnalyzer() {
		visitors = new JsonVisitorParser().getVisitorsFromJson("C:\\Users\\eddie\\IdeaProjects\\JavaParserAnalysisTool\\JavaParser\\json\\metrics.json");

	}

	@Override
	public void analyze(CompilationUnit cu, Object arg) {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, arg);
	}
}
