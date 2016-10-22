package tools.analyzer;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonVisitorParser {

	public List<VoidVisitorAdapter> getVisitorsFromJson(String path) {
		List<VoidVisitorAdapter> v = new ArrayList<>();
		try {
			JsonElement jelement = new JsonParser().parse(new FileReader(path));
			JsonArray jarray = jelement.getAsJsonArray();
			for (int i = 0; i < jarray.size(); i++) {
				v.add((VoidVisitorAdapter) Class.forName(jarray.get(i).toString().replaceAll("\"", "")).newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return v;
	}
}
