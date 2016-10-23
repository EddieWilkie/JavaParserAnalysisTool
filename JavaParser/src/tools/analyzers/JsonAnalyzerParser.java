package tools.analyzers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonAnalyzerParser {
	public List<Analyzer> getAnalyzersFromJson(String path) {
		List<Analyzer> a = new ArrayList<>();
		try {
			JsonElement jelement = new JsonParser().parse(new FileReader(path));
			JsonArray jarray = jelement.getAsJsonArray();
			for (int i = 0; i < jarray.size(); i++) {
				a.add((Analyzer)Class.forName(jarray.get(i).toString().replaceAll("\"", "")).newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}
}
