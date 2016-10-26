package suite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

	private SimpleJAnalysisSuite() {
		analyzers = new JsonAnalyzerParser().getAnalyzersFromJson("C:\\Users\\eddie\\IdeaProjects\\JavaParserAnalysisTool\\JavaParser\\json\\analyzers.json");
	}

	private void analyzeClassDiagram(CompilationUnit cu, Object arg) {
		print("Class Diagram");
        analyzers.forEach(analyzer -> {
            if (analyzer.getClass().equals(SimpleClassDiagramAnalyzer.class))
                analyzer.analyze(cu, arg);
        });
	}

	private void analyzeBadSmells(CompilationUnit cu, Object arg) {
		print("Bad Smell");
        analyzers.forEach(analyzer -> {
            if (analyzer.getClass().equals(SimpleBadSmellsAnalyzer.class))
				analyzer.analyze(cu, arg);
        });
	}

	private void analyzeMetrics(CompilationUnit cu, Object arg) {
		print("Software Metrics");
        analyzers.forEach(analyzer -> {
            if (analyzer.getClass().equals(SimpleMetricAnalyzer.class))
                analyzer.analyze(cu, arg);
        });
	}

	private void analyzeAll(CompilationUnit cu, Object arg) {
		System.out.println("Running Multiple Analyzers...");
		analyzeClassDiagram(cu,arg);
		analyzeBadSmells(cu,arg);
		analyzeMetrics(cu,arg);
	}

	private void print(String type) {
		System.out.println();
		System.out.println("Running " + type + " Analyzer...");
		System.out.println("================================");
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SimpleJAnalysisSuite testSuit = new SimpleJAnalysisSuite();
		File dir = new File("C:\\Users\\eddie\\IdeaProjects\\JavaParserAnalysisTool\\JavaParser\\testsuite");
		System.out.println("Welcome to the SimpleJSuitAnalysisTool.");
		System.out.println("=======================================\n");
		System.out.println("Press (i) for instructions.");
		String input;
		int counter = 0;
		while ((input = br.readLine()) != null) {
			for (File f : dir.listFiles()) {

                CompilationUnit cu;
                try (FileInputStream in = new FileInputStream(f)) {
                    cu = JavaParser.parse(in);

                    switch (input.toLowerCase().charAt(0)) {
                        case 'a':
                            testSuit.analyzeAll(cu, null);
                            break;
                        case 'm':
                            testSuit.analyzeMetrics(cu, null);
                            break;
                        case 'b':
                            testSuit.analyzeBadSmells(cu, null);
                            break;
                        case 'c':
                            testSuit.analyzeClassDiagram(cu, null);
                            break;
                        case 'i':
                            if (counter == 0) {
                                System.out.println("These are the commands for This simple suite:\n");
                                System.out.println("(C)lass diagram analysis");
                                System.out.println("(B)ad Smell analysis");
                                System.out.println("(M)etrics analysis");
                                System.out.println("(A)nalyze All");
                                System.out.println("(I)nstructions");
                                System.out.println("(Q)uit");
                            }
                            counter++;
                            break;
                        case 'q':
                            System.out.println("Quit");
                            System.exit(0);
                            break;
                        default:
                            if (counter == 0)
                                System.out.println("Wrong Input");
                            counter++;
                            break;
                    }

                }
			}
			counter = 0;
			System.out.println("\nEnter Command:");
		}
	}
}
