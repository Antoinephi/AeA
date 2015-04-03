package algorithmes;

import generationGraphes.Edge;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.List;

public class Prim {
	private GraphImpl graphe;
	private List<Vertex> Q;
	private GraphImpl MST;
	private int[] key;

	public Prim(GraphImpl graphe) {
		this.graphe = graphe;
		this.Q = graphe.getVertex();
		this.MST = new GraphImpl();
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
	
	public void afficher_graphe(){
		System.out.println("Nombre de sommets : " + this.MST.getVertex().size());
		System.out.println("Nombre d'arrêtes : " + this.MST.getEdges().size());
		for(Edge e : this.MST.getEdges())
			System.out.println(e);
	}
	
	public void algo(){
		Vertex u;
		List<Vertex> neighbours;
		while(this.Q.size() > 0){
			u = extract_min();
			neighbours = this.graphe.getVertexNeighbours(u);
			for(Vertex v : neighbours){
				System.out.println(" this.key[" + v + "] : " + this.key[v.getNumber()] + "\n this.graphe.getEdge(" + u + ","+ v+").getValue() : " + this.graphe.getEdge(u, v).getValue());
				if(this.Q.contains(v) && (this.key[v.getNumber()] > this.graphe.getEdge(u, v).getValue())){
					v.setPere(u);
					if(!this.MST.getVertex().contains(v))
						this.MST.addVertex(v);
					if(!this.MST.getVertex().contains(u))
						this.MST.addVertex(u);
					if(!this.MST.getEdges().contains(this.graphe.getEdge(u, v))){
						this.MST.addEdge(this.graphe.getEdge(v.getNumber(), u.getNumber()));
						this.key[v.getNumber()] = this.graphe.getEdge(u, v).getValue();
					}
				}
					
			}
			
		}
	}
		
	public static void main(String[] args) {
		
		GraphImpl g = new GraphImpl();
		for(int i = 0; i < 8; i++){
			g.addVertex();
			System.out.println(g.getVertex());
		}
		try {
			g.addEdge(0,1, 14);
			g.addEdge(0,2, 8);
			g.addEdge(0,5, 5);
			g.addEdge(0,7, 6);
			g.addEdge(1,2, 3);
			g.addEdge(2,3, 10);
			g.addEdge(3,4, 15);
			g.addEdge(3,5, 7);
			g.addEdge(5,6, 9);
			g.addEdge(5,7, 12);
		} catch(Exception e){
			e.printStackTrace();
		}

		Prim p = new Prim(g);
		p.algo();
		p.afficher_graphe();
	}
	
}
