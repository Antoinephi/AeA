package algorithmes;

import generationGraphes.GrapheValue;
import generationGraphes.Vertex;

import java.util.LinkedList;
import java.util.List;

public class Prim {
	private Graphe graphe;
	private LinkedList<Vertex> Q;
	private Graphe MST;
	private int[] key;

	public Prim(Graphe graphe) {
		this.graphe = graphe;
		this.Q = graphe.getVertex();
		init_key();
	}

	public void init_key() {
		this.key = new int[graphe.getVertex().size()+1];
		for (int i = 0; i < key.length; i++) {
			this.key[i] = Integer.MAX_VALUE;
		}
		/*int alea = (int) (Math.random() * (this.key.length));
		key[alea] = 0;*/
		this.key[0] = 0;
	}
	
	public Vertex extract_min(){
		Vertex min = new Vertex(Integer.MAX_VALUE);
		for(Vertex v : Q){
			min = (min.getNumber() > v.getNumber() ? v : min);
		}
		
		this.Q.remove(min);
		return min;
	}	
	
	public void algo(){
		Vertex u;
		List<Vertex> neighbours;
		while(this.Q.size() > 0){
			u = extract_min();
			neighbours = this.graphe.getVertexNeighbours(u);
			for(Vertex v : neighbours){
				if(this.Q.contains(v) && (this.key[v.getNumber()] > this.graphe.getEdge(u, v).getValue()))
					this.key[v.getNumber()] = this.graphe.getEdge(u, v).getValue();
					
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		GrapheValue g = new GrapheValue();
		for(int i = 0; i < 9; i++){
			g.addVertex();
		}
		try {
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 6);
		g.addEdge(2, 3);
		g.addEdge(3,4);
		g.addEdge(4,5);
		g.addEdge(4,6);
		g.addEdge(6,7);
		g.addEdge(6,8);
		g.addEdge(8,1);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Prim p = new Prim(g);
		p.algo();
	}
	
}
