package algorithmes;

import generationGraphes.Edge;
import generationGraphes.Generation;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.LinkedList;
import java.util.List;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Kruskal2 {
	private GraphImpl graph;
	private static int partMin = 0;

	public Kruskal2(GraphImpl graph) {
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
				updatePart(mst, e[j]);
				System.out.println("here "+e[j].getStart().getNumber()+"---"+e[j].getEnd().getNumber());
				System.out.println(F.size());
			}
		}
		mst.setEdges(F);
		return mst;
	}

	private void updatePart(GraphImpl graphe, Edge e) {
		if(e.getStart().getPart() == -1 && e.getEnd().getPart() == -1){
			e.getStart().setPart(partMin);
			e.getEnd().setPart(partMin);
			partMin++;
			return;
		}
		if(e.getStart().getPart() == -1 && e.getEnd().getPart() != -1){
			e.getStart().setPart(e.getEnd().getPart());
			return;
		}
		if(e.getEnd().getPart() == -1 && e.getStart().getPart() != -1){
			e.getEnd().setPart(e.getStart().getPart());
			return;
		}
		for (Vertex v : graphe.getVertex()) {
			if(v.getPart() == e.getEnd().getPart()){
				v.setPart(e.getStart().getPart());
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
		System.out.println("Tri de e");
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i].getValue());
		}
		System.out.println("Fin du tri");
		return e;
	}
	
	public boolean isCyclic(Edge e, LinkedList<Edge> F){
		if(e.getStart().getPart() == -1 || e.getEnd().getPart() == -1)
			return false;
		if(e.getStart().getPart() == e.getEnd().getPart())
			return true;
		return false;
	}

	public static void main(String[] args) throws VertexNotFoundException,
			VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(5, 0.5);
		Kruskal algo = new Kruskal(graphe);
		graphe.affiche();
		GraphImpl mst = algo.algo();
		mst.affiche();
	}
}
