package tools.analyzer;

import java.io.File;
import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import tools.classdiagram.SimpleClassDiagramAnalyzer;
import tools.codesmells.SimpleBadSmellsAnalyzer;
import tools.metrics.SimpleMetricAnalyzer;

public class SimpleJAnalysisSuite {
	private Analyzer metrics, diagram, smells;

	public SimpleJAnalysisSuite(CompilationUnit cu) {
		metrics = new SimpleMetricAnalyzer(cu);
		diagram = new SimpleClassDiagramAnalyzer(cu);
		smells = new SimpleBadSmellsAnalyzer(cu);
	}

	public void analyzeMetrics() {
		print("Software Metrics");
		metrics.analyze();
	}

	public void analyzeBadSmells() {
		print("Bad Smell");
		smells.analyze();
	}

	public void analyzeClassDiagram() {
		print("Class Diagram");
		diagram.analyze();
	}

	public void analyzeAll() {
		System.out.println("Running Multiple Analyzers...");
		print("Class Diagram");
		diagram.analyze();
		print("Bad Smell");
		smells.analyze();
		print("Software Metrics");
		metrics.analyze();
		
	}
	
	private void print(String type){
		System.out.println();
		System.out.println("Running " + type + " Analyzer...");
		System.out.println("================================");
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		File dir = new File("suite");
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
