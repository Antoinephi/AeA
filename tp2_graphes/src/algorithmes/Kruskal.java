package algorithmes;

import generationGraphes.Edge;
import generationGraphes.Generation;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.LinkedList;
import java.util.List;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Kruskal {
	private GraphImpl graph;
	private static int partMin = 0;

	public Kruskal(GraphImpl graph) {
		this.graph = graph;
	}

	public GraphImpl algo() throws VertexAlreadyExistException {
		// Sortie : nouveau graphe
		GraphImpl mst = new GraphImpl();

		// Qui contient les mï¿½mes sommets
		for (int i = 0; i < this.graph.getVertex().size(); i++) {
			mst.addVertexNumber(this.graph.getVertex().get(i).getNumber());
		}

		// Initialiser F ï¿½ vide
		LinkedList<Edge> F = new LinkedList<Edge>();

		// e est l'ensemble des arï¿½tes du graphe de dï¿½part
		Edge[] e = new Edge[this.graph.getEdges().size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = this.graph.getEdges().get(i);
		}
		// Trier les arï¿½tes de e par poids croissant
		e = sortEdge(e);

		// Pour 1=1 ï¿½ |E| Faire
		for (int j = 0; j < e.length; j++) {
			// Si F U {e[i]} est acyclique Alors
			if (!isCyclic(e[j], F)) {
				System.out.println(e[j].getStart().getNumber()+" "+e[j].getEnd().getNumber());
				// F := F U {e[i]}
				F.add(e[j]);
				updatePart(F, e[j]);
				partToString(F);
			}
		}
		mst.setEdges(F);
		return mst;
	}
	
	public void partToString(LinkedList<Edge> F){
		System.out.println("\n\ndébut part");
		for(Edge e: F){
			System.out.println(e.getStart().getNumber() + " part : "+e.getStart().getPart() + " , "+e.getEnd().getNumber()+" part : "+e.getEnd().getPart());
		}
		System.out.println("fin part \n\n");
	}

	private void updatePart(LinkedList<Edge> F, Edge e) {
		//System.out.println(e.getStart().getNumber()+" "+e.getEnd().getNumber());
		//partToString(graphe);
		if(e.getStart().getPart() == -1 && e.getEnd().getPart() == -1){
			System.out.println("case -1 -1");
			System.out.println(e.getStart().getNumber());
			e.getStart().setPart(partMin);
			e.getEnd().setPart(partMin);
			partMin++;
			return;
		}
		if(e.getStart().getPart() == -1 && e.getEnd().getPart() != -1){
			System.out.println("e.getEnd() : "+e.getEnd().getPart());
			e.getStart().setPart(e.getEnd().getPart());
			return;
		}
		if(e.getEnd().getPart() == -1 && e.getStart().getPart() != -1){
			System.out.println("e.getStart() : "+e.getStart().getPart());
			e.getEnd().setPart(e.getStart().getPart());
			return;
		}
		System.out.println("other case");
		for (Edge e1 : F) {
			if(e1.getStart().getPart() == e.getEnd().getPart()){
				e1.getStart().setPart(e.getStart().getPart());
			}
			if(e1.getEnd().getPart() == e.getEnd().getPart()){
				e1.getEnd().setPart(e.getStart().getPart());
			}
		}
	}

	public Edge[] sortEdge(Edge[] e) {
		for (int i = 0; i < e.length; i++) {
			int min = e[i].getValue();
			int indMin = i;
			for (int j = i; j < e.length; j++) {
				if (e[j].getValue() < min) {
					min = e[j].getValue();
					indMin = j;
				}
			}
			Edge tmp = e[i];
			e[i] = e[indMin];
			e[indMin] = tmp;
		}
		return e;
	}
	
	public boolean isCyclic(Edge e, LinkedList<Edge> F){
		if(e.getStart().getPart() == -1 || e.getEnd().getPart() == -1)
			return false;
		if(e.getStart().getPart() == e.getEnd().getPart()){
			System.out.println("iscyclic");
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws VertexNotFoundException,
			VertexAlreadyExistException {
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
		Kruskal algo = new Kruskal(g);
		g.affiche();
		long debut = System.currentTimeMillis();
		GraphImpl mst = algo.algo();
		long fin = System.currentTimeMillis();
		float seconds = (fin - debut) / 1000F;
		System.out.println("Kruskal effectué en :"
				+ Float.toString(seconds) + " secondes.");
		mst.affiche();
	}
}
