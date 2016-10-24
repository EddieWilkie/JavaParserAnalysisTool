package tools.analyzers;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import tools.jsonparsers.JsonVisitorParser;

public class SimpleBadSmellsAnalyzer implements Analyzer {
	private List<VoidVisitorAdapter> visitors;

	public SimpleBadSmellsAnalyzer() {
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/smells.json");
	}

	public void analyze(CompilationUnit cu, Object arg) {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, arg);
	}
}
