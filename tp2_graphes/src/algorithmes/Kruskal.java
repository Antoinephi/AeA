package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;

import generationGraphes.*;

public class Kruskal {
	private GraphImpl graph;

	public Kruskal(GraphImpl graph) {
		this.graph = graph;
	}

	public GraphImpl algo() throws VertexAlreadyExistException {
		// Sortie : nouveau graphe
		GraphImpl mst = new GraphImpl();
		
		// Qui contient les m�mes sommets
		for (int i = 0; i < this.graph.getVertex().size(); i++) {
			mst.addVertexNumber(this.graph.getVertex().get(i).getNumber());
		}

		// Initialiser F � vide
		LinkedList<Edge> F = new LinkedList<Edge>();
		
		// e est l'ensemble des ar�tes du graphe de d�part
		Edge[] e = new Edge[this.graph.getEdges().size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = this.graph.getEdges().get(i);
		}
		// Trier les ar�tes de e par poids croissant
		e = sortEdge(e);
		
		// Pour 1=1 � |E| Faire
		for (int j = 0; j < e.length; j++) {
			// Si F U {e[i]} est acyclique Alors
			if (!isCyclic(e[j], F)) {
				// F := F U {e[i]}
				F.add(e[j]);
			}
		}
		return mst;
	}

	public Edge[] sortEdge(Edge[] e) {
		for(int i=0;i<e.length;i++){
			int min = e[i].getValue();
			int indMin = i;
			for(int j=i;j<e.length;j++){
				if(e[j].getValue() < min){
					min = e[j].getValue();
					indMin = j;
				}
			}
			Edge tmp = e[i];
			e[i] = e[indMin];
			e[indMin] = tmp; 
		}
		System.out.println("Tri de e");
		for(int i=0;i<e.length;i++){
			System.out.println(e[i].getValue());
		}
		System.out.println("Fin du tri");
		return e;
	}

	// cf algo li�vre et la tortue
	public boolean isCyclic(Edge e, LinkedList<Edge> F) {
		Vertex start = e.getStart();
		Vertex end = e.getEnd();
		Vertex chemin = start;
		
		for(int i=0;i<F.size();i++){
			chemin = start;
			if(F.get(i).getStart()==start || F.get(i).getEnd()==start){
				
			}
		}
		return false;
	}

}
