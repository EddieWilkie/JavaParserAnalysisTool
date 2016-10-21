package tools.metrics;

import com.github.javaparser.ast.CompilationUnit;

import tools.analyzer.Analyzer;

public class SimpleMetricAnalyzer implements Analyzer {
	private CompilationUnit cu;
	public SimpleMetricAnalyzer(CompilationUnit cu){
		this.cu = cu;
	}
	@Override
	public void analyze() {
		// TODO Auto-generated method stub
		
	}

}
