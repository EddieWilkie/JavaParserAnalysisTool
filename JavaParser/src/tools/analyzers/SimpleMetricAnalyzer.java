package tools.analyzers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SimpleMetricAnalyzer implements Analyzer {
	private List<VoidVisitorAdapter> visitors;

	public SimpleMetricAnalyzer() {
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/metrics.json");
	
	}

	@Override
	public void analyze(CompilationUnit cu, Object arg) {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, arg);
	}
}
