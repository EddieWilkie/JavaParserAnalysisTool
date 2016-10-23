package tools.analyzers;
import com.github.javaparser.ast.CompilationUnit;

public interface Analyzer {
public void analyze(CompilationUnit cu, Object arg);
}
