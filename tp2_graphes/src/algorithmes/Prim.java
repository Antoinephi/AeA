package algorithmes;

import generationGraphes.Edge;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import VertexExceptions.VertexNotFoundException;

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
	
	/*public List<Vertex> algo_bis(){
		List<Vertex> vertex = new LinkedList<Vertex>();
		List<Boolean> sommetsMarques = new LinkedList<Boolean>();
		for(Vertex v : this.graphe.getVertex())
			sommetsMarques.add(false);
		sommetsMarques.set(0, true);
		System.out.println("pre while");
		while(sommetsMarques.contains(false)){
			List<Vertex> liste = null;
			int maxEdgeValue = Integer.MAX_VALUE;
			Edge e;
			int numSommet = 0;
			for(int i = 0; i < sommetsMarques.size(); i++){
				if(sommetsMarques.get(i) == false){
					System.out.println(i + " v");
					liste = this.graphe.getVertexNeighbours(this.graphe.getVertex(i)); //A REGARDER : valeur du i
					for(int j = 0; j < liste.size(); j++){
						System.out.println("num edge value : " + this.graphe.getEdge(i, j));
						if(liste.get(j).getNumber() == Math.max(i-1,0) && this.graphe.getEdge(i, j).getValue() < maxEdgeValue){
							System.out.println("if");
							maxEdgeValue = this.graphe.getEdge(i, j).getValue() ;
							e = this.graphe.getEdge(i, j);
							numSommet = j;
						}
					}
				}
				
			}
			vertex.add(new Vertex(numSommet));
			sommetsMarques.set(numSommet, true);
		}
		return vertex;
	}*/
	
	public GraphImpl algo_bis(){
		List<Vertex> liste_vertex = new LinkedList<Vertex>();
		boolean[] sommetsMarques = new boolean[this.graphe.getVertex().size()];
		List<Integer> edgesValues = new LinkedList<Integer>();
		Vertex y = null,  x = null, v1 = null;
		int nbSommetsMarques = 1;
		int poidsMin = Integer.MAX_VALUE;

		for (int i = 0; i < this.graphe.getEdges().size(); i++) {
			edgesValues.add(this.graphe.getEdges().get(i).getValue());
		}
		Collections.sort(edgesValues);
		
		sommetsMarques[0] = true;
		while(nbSommetsMarques < sommetsMarques.length){
			System.out.println("while");
			poidsMin = Integer.MAX_VALUE;
			for(int i = 0; i < sommetsMarques.length; i++){
				if(sommetsMarques[i]){
					v1 = new Vertex(i);
					liste_vertex = this.graphe.getVertexNeighbours(v1);
					for(Vertex v2 : liste_vertex){
						System.out.println("v2 : " + v2);
						if(!sommetsMarques[v2.getNumber()] && this.graphe.getEdge(v2, v1).getValue() < poidsMin){
							poidsMin = this.graphe.getEdge(v2, v1).getValue();
							y = v2;
							x = v1;
							System.out.println("sommet adjacent marqué : " + v2 + " poids : " + poidsMin);
						}
						
					}
				}
			}
			
			if(v1 != null && y != null){
				System.out.println("ajout edge : " + x + " " + y);
				this.MST.addEdge(x, y, poidsMin);
				sommetsMarques[y.getNumber()] = true;
				nbSommetsMarques++;
			}
		}
		
		return this.MST;
	}
		
	public static void main(String[] args) {
		
		GraphImpl g = new GraphImpl();
		for(int i = 0; i < 8; i++){
			g.addVertex();
			//System.out.println(g.getVertex());
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
		//g.affiche();
		Prim p = new Prim(g);
		p.algo_bis();
		p.afficher_graphe();
	}
	
}
