package suite;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import tools.analyzers.Analyzer;
import tools.analyzers.SimpleBadSmellsAnalyzer;
import tools.analyzers.SimpleClassDiagramAnalyzer;
import tools.analyzers.SimpleMetricAnalyzer;
import tools.jsonparsers.JsonAnalyzerParser;

public class SimpleJAnalysisSuite {
	private List<Analyzer> analyzers;
	private CompilationUnit cu;

	public SimpleJAnalysisSuite(CompilationUnit cu) {
		this.cu = cu;
		analyzers = new JsonAnalyzerParser().getAnalyzersFromJson("json/analyzers.json");
	}

	public void analyzeClassDiagram() {
		print("Class Diagram");
		for (Analyzer a : analyzers)
			if (a.equals(SimpleClassDiagramAnalyzer.class))
				a.analyze(cu, null);
	}

	public void analyzeBadSmells() {
		print("Bad Smell");
		for (Analyzer a : analyzers)
			if (a.equals(SimpleBadSmellsAnalyzer.class))
				a.analyze(cu, null);

	}

	public void analyzeMetrics() {
		print("Software Metrics");
		for (Analyzer a : analyzers)
			if (a.equals(SimpleMetricAnalyzer.class))
				a.analyze(cu, null);
	}

	public void analyzeAll() {
		System.out.println("Running Multiple Analyzers...");
		print("Class Diagram");
		for (Analyzer a : analyzers)
			a.analyze(cu, null);

	}

	private void print(String type) {
		System.out.println();
		System.out.println("Running " + type + " Analyzer...");
		System.out.println("================================");
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		File dir = new File("testsuite");
		for (File f : dir.listFiles()) {
			FileInputStream in = new FileInputStream(f);

			CompilationUnit cu;
			try {
				cu = JavaParser.parse(in);
				SimpleJAnalysisSuite testSuit = new SimpleJAnalysisSuite(cu);
				testSuit.analyzeAll();
			} finally {
				in.close();
			}
		}
	}

}
