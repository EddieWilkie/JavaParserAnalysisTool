package tools.analyzer;

import java.io.File;
import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import tools.classdiagram.SimpleClassDiagramAnalyzer;
import tools.codesmells.SimpleBadSmellsAnalyzer;
import tools.metrics.SimpleMetricAnalyzer;

public class SimpleJAnalysisSuit {
	private Analyzer metrics, diagram, smells;

	public SimpleJAnalysisSuit(CompilationUnit cu) {
		metrics = new SimpleMetricAnalyzer(cu);
		diagram = new SimpleClassDiagramAnalyzer(cu);
		smells = new SimpleBadSmellsAnalyzer(cu);
	}

	public void analyzeMetrics() {
		metrics.analyze();
	}

	public void analyzeBadSmells() {
		smells.analyze();
	}

	public void analyzeClassDiagram() {
		diagram.analyze();
	}

	public void analyzeAll() {
		metrics.analyze();
		smells.analyze();
		diagram.analyze();
	}

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		File dir = new File("suit");
		for (File f : dir.listFiles()) {
			FileInputStream in = new FileInputStream(f);

			CompilationUnit cu;
			try {
				cu = JavaParser.parse(in);
				SimpleJAnalysisSuit testSuit = new SimpleJAnalysisSuit(cu);
				testSuit.analyzeAll();
			} finally {
				in.close();
			}
			
		}
	}

}
