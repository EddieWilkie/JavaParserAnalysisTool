package tools.codesmells;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import tools.analyzer.Analyzer;

public class SimpleBadSmellsAnalyzer implements Analyzer{
	private CompilationUnit cu;
	private VoidVisitorAdapter longMethod, longParameter, largeClass;

	public SimpleBadSmellsAnalyzer(CompilationUnit cu) {
		longMethod = new LongMethodVisitor();
		longParameter = new LongParameterVisitor();
		largeClass = new LargeClassVisitor();
		this.cu = cu;
	}

	public void analyze() {
		longMethod.visit(cu, null);
		longParameter.visit(cu, null);
		largeClass.visit(cu, null);
	}
}
