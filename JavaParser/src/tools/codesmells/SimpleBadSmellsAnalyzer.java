package tools.codesmells;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import tools.analyzer.Analyzer;
import tools.analyzer.JsonVisitorParser;

public class SimpleBadSmellsAnalyzer implements Analyzer {
	private CompilationUnit cu;
	private List<VoidVisitorAdapter> visitors;

	public SimpleBadSmellsAnalyzer(CompilationUnit cu) {
		this.cu = cu;
		visitors = new JsonVisitorParser().getVisitorsFromJson("json/smells.json");
	}
	
	public void analyze() {
		for (VoidVisitorAdapter v : visitors)
			v.visit(cu, null);
	}
}
