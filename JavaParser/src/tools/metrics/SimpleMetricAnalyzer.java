package tools.metrics;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import tools.analyzer.Analyzer;
import tools.analyzer.JsonVisitorParser;

public class SimpleMetricAnalyzer implements Analyzer {
	private List<VoidVisitorAdapter> visitors;
	private CompilationUnit cu;

	public SimpleMetricAnalyzer(CompilationUnit cu) {
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/metrics.json");
		this.cu = cu;
	}

	@Override
	public void analyze() {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, null);
	}
}
