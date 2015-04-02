package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import generationGraphes.*;

public class Kruskal {
	private GraphImpl graph;

	public Kruskal(GraphImpl graph) {
		this.graph = graph;
	}

	public GraphImpl algo() throws VertexAlreadyExistException {
		// Sortie : nouveau graphe
		GraphImpl mst = new GraphImpl();

		// Qui contient les mêmes sommets
		for (int i = 0; i < this.graph.getVertex().size(); i++) {
			mst.addVertexNumber(this.graph.getVertex().get(i).getNumber());
		}

		// Initialiser F à vide
		LinkedList<Edge> F = new LinkedList<Edge>();

		// e est l'ensemble des arêtes du graphe de départ
		Edge[] e = new Edge[this.graph.getEdges().size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = this.graph.getEdges().get(i);
		}
		// Trier les arêtes de e par poids croissant
		e = sortEdge(e);

		// Pour 1=1 à |E| Faire
		for (int j = 0; j < e.length; j++) {
			// Si F U {e[i]} est acyclique Alors
			if (!isCyclic(e[j], F, mst)) {
				// F := F U {e[i]}
				F.add(e[j]);
			}
		}
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

	// cf algo lièvre et la tortue
	public boolean isCyclic2(Edge e, LinkedList<Edge> F) {
		int cpt = 0;
		Vertex start = e.getStart();
		Vertex end = e.getEnd();
		Edge chemin;
		LinkedList<Edge> neighboors = new LinkedList<Edge>();
		LinkedList<Edge> firstNeighboors = new LinkedList<Edge>();
		for (int i = 0; i < F.size(); i++) {
			if (F.get(i).getStart() == start || F.get(i).getEnd() == start) {
				neighboors.add(F.get(i));
				firstNeighboors.add(F.get(i));
				System.out.println(F.get(i).getStart().getNumber() + "--->"
						+ F.get(i).getEnd().getNumber());
			}
		}
		System.out.println("fin first neighboors");
		if (neighboors.isEmpty())
			return false;
		chemin = neighboors.get(cpt);
		while (cpt < neighboors.size()
				&& ((firstNeighboors.contains(chemin)) || ((chemin.getEnd() != start) && (chemin
						.getStart() != start)))) {
			chemin = neighboors.get(cpt);
			cpt++;
			System.out.println("chemin : " + chemin.getStart().getNumber()
					+ "---" + chemin.getEnd().getNumber());
			for (int i = 0; i < F.size(); i++) {
				if (((F.get(i).getStart() == chemin.getEnd())
						|| (F.get(i).getEnd() == chemin.getEnd())
						|| (F.get(i).getStart() == chemin.getStart()) || (F
						.get(i).getStart() == chemin.getEnd()))
						&& !neighboors.contains(F.get(i))) {
					neighboors.add(F.get(i));
					System.out.println(F.get(i).getStart().getNumber() + "---"
							+ F.get(i).getEnd().getNumber());
				}
			}
		}
		System.out.println("" + (cpt < neighboors.size()) + " cpt");
		System.out.println(firstNeighboors.contains(chemin));
		System.out
				.println(((chemin.getEnd() != start) && (chemin.getStart() != start)));
		System.out.println(((firstNeighboors.contains(chemin)) || ((chemin
				.getEnd() != start) && (chemin.getStart() != start))));
		if (chemin.getEnd() == end) {
			return true;
		}
		return false;
	}

	public boolean isCyclic(Edge e, LinkedList<Edge> F, GraphImpl graphe) {
		int[] isVisited = new int[graphe.getVertex().size()];
		int[] comeFrom = new int[graphe.getVertex().size()];
		F.add(e);
		for (int i = 0; i < isVisited.length; i++) {
			isVisited[i] = 0;
			comeFrom[i] = -1;
		}
		isVisited[e.getStart().getNumber() - 1] = 1;
		int lastVisited = e.getStart().getNumber();
		LinkedList<Integer> toBeVisited = new LinkedList<Integer>();
		for (int i = 0; i < F.size(); i++) {
			if (F.get(i).getStart().getNumber() == lastVisited) {
				isVisited[F.get(i).getEnd().getNumber() - 1]++;
				toBeVisited.add(F.get(i).getEnd().getNumber());
				comeFrom[F.get(i).getEnd().getNumber()-1] = lastVisited;
			}
			if (F.get(i).getEnd().getNumber() == lastVisited) {
				isVisited[F.get(i).getStart().getNumber() - 1]++;
				toBeVisited.add(F.get(i).getStart().getNumber());
			}
		}
		while (!toBeVisited.isEmpty()) {
			lastVisited = toBeVisited.getFirst();
			isVisited[lastVisited - 1] = 1;
			toBeVisited.removeFirst();
			for (int i = 0; i < F.size(); i++) {
				if (F.get(i).getStart().getNumber() == lastVisited) {
					if (isVisited[F.get(i).getEnd().getNumber()-1] == 1)
						return true;
					toBeVisited.add(F.get(i).getEnd().getNumber());
				}
				if (F.get(i).getEnd().getNumber() == lastVisited) {
					if (isVisited[F.get(i).getStart().getNumber()-1] == 1)
						return true;
					toBeVisited.add(F.get(i).getStart().getNumber());
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
		Edge e = new Edge(graphe.getVertex(1), graphe.getVertex(2));
		System.out.println(graphe.getVertex(1).getNumber());
		System.out.println(graphe.getVertex(2).getNumber());
		System.out.println(algo.isCyclic(e, graphe.getEdges(), graphe));
	}
}
