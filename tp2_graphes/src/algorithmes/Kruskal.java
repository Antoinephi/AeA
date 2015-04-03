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
			if (!isCyclic(e[j], F, mst)) {
				// F := F U {e[i]}
				F.add(e[j]);
				System.out.println("here "+e[j].getStart().getNumber()+"---"+e[j].getEnd().getNumber());
				System.out.println(F.size());
			}
		}
		mst.setEdges(F);
		return mst;
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

	public boolean isCyclic(Edge e, List<Edge> F1, GraphImpl graphe) {
		int[] isVisited = new int[graphe.getVertex().size()];
		int[] comeFrom = new int[graphe.getVertex().size()];
		List<Edge> F = new LinkedList<Edge>();
		F.addAll(F1);
		F.add(e);
		for (int i = 0; i < isVisited.length; i++) {
			isVisited[i] = 0;
			comeFrom[i] = -1;
		}
		isVisited[e.getStart().getNumber() - 1] = 1;
		int lastVisited = e.getStart().getNumber();
		LinkedList<Vertex> toBeVisited = new LinkedList<Vertex>();
		for (int i = 0; i < F.size(); i++) {
			if (F.get(i).getStart().getNumber() == lastVisited) {
				toBeVisited.add(F.get(i).getEnd());
				comeFrom[F.get(i).getEnd().getNumber() - 1] = lastVisited - 1;
			}
			if (F.get(i).getEnd().getNumber() == lastVisited) {
				toBeVisited.add(F.get(i).getStart());
				comeFrom[F.get(i).getStart().getNumber() - 1] = lastVisited - 1;
				System.out
						.println("start : " + F.get(i).getStart().getNumber());
			}
		}

		while (!toBeVisited.isEmpty()) {
			lastVisited = toBeVisited.getFirst().getNumber();
			isVisited[lastVisited - 1] = 1;
			toBeVisited.removeFirst();
			for (int i = 0; i < F.size(); i++) {
				if (F.get(i).getStart().getNumber() == lastVisited) {
					if ((isVisited[F.get(i).getEnd().getNumber() - 1] == 1)
							&& (comeFrom[lastVisited - 1] != F.get(i).getEnd()
									.getNumber() - 1))
						return true;
					if (isVisited[F.get(i).getEnd().getNumber() - 1] == 0) {
						toBeVisited.add(F.get(i).getEnd());
						comeFrom[F.get(i).getEnd().getNumber() - 1] = lastVisited - 1;
					}

				}
				if (F.get(i).getEnd().getNumber() == lastVisited) {
					if ((isVisited[F.get(i).getStart().getNumber() - 1] == 1)
							&& (comeFrom[lastVisited - 1] != F.get(i)
									.getStart().getNumber() - 1))
						return true;
					if (isVisited[F.get(i).getStart().getNumber() - 1] == 0) {
						toBeVisited.add(F.get(i).getStart());
						comeFrom[F.get(i).getStart().getNumber() - 1] = lastVisited - 1;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws VertexNotFoundException,
			VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(5, 0.5);
		Kruskal algo = new Kruskal(graphe);
		graphe.affiche();
		Edge e = new Edge(graphe.getVertexFromNumber(1),
				graphe.getVertexFromNumber(2));
		List<Edge> F = graphe.getEdges();
		GraphImpl mst = algo.algo();
		mst.affiche();
	}
}
