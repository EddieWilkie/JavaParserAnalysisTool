package tools.metrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CommentRatioVisitor extends VoidVisitorAdapter{
	public void visit(ClassOrInterfaceDeclaration n, Object arg){
		float numberOfLines = (n.getEnd().line - n.getBegin().line);
		float numberOfComments = n.getAllContainedComments().size();
		float ratio = (numberOfComments/numberOfLines)*100;
		System.out.println("Comment to line ratio = " + Math.round(ratio) + "%");
	}
}
