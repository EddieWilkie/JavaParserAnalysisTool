import java.util.ArrayList;


public class Matrix implements DiGraphADT {			
	
		private boolean[][] arrayArray;				//initialise the matrix: an array of arrays of type boolean
		private int nodes;
		private int edges = 0;

	public Matrix(int n) {
		
		nodes= n;
		arrayArray = new boolean[nodes][nodes];
	}

	@Override
	public int nNodes() {
		
		return nodes;
	}

	@Override
	public int nEdges() {
		
		return edges;
	}

	@Override
	public boolean addEdge(int node1, int node2) {
		
		if(isEdge(node1,node2) == true){			//If the edge already exists in the graph then return false
			return false;
			}
		else
			arrayArray[node1][node2] = true;	
		edges++;
		return true;
	}

	@Override 
	public boolean isEdge(int node1, int node2) {
		return arrayArray[node1][node2];			//If the edge already exists in the graph then return true, if not return false
	}

	@Override
	public ArrayList<Integer> successors(int node) {
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		for(int i=0; i<nodes; i++){
			if(arrayArray[node][i] = true){			//If a successor node exists in the graph of the given 'node', 
				arrayList.add(i);					//then add the representation of the node to the ArrayList
			}
			
		}
		
		return arrayList;
	}

	@Override
	public ArrayList<Integer> predecessors(int node) {

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		for(int i=0; i<nodes; i++){
			if(arrayArray[i][node] = true){			//If a predecessor node exists in the graph of the given 'node', 
				arrayList.add(i);					//then add the representation of the node to the ArrayList
			}
			
		}
		
		return arrayList;
	}

	@Override
	public int outDegree(int node) {
		
		int outCounter = 0;
		for(int i=0; i<nodes; i++){
			if(arrayArray[node][i] = true){			//If a outDegree node exists in the graph of the given 'node', 
				outCounter++;						//then increment the counter and continue if there are any more
			}
			
		}
		return outCounter;

	}

	@Override
	public int inDegree(int node) {
		
		int inCounter = 0;
		for(int i=0; i<nodes; i++){
			if(arrayArray[i][node] = true){			//If a inDegree node exists in the graph of the given 'node', 
				inCounter++;						//then increment the counter and continue if there are any more
			}
			
		}
		return inCounter;
	
	
	    }
	
}
