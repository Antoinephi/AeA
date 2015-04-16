package algorithmes;

import generationGraphes.Edge;
import generationGraphes.Generation;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import VertexExceptions.EdgeNotFoundException;
import VertexExceptions.VertexAlreadyExistException;
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
		this.key = new int[graphe.getVertex().size() + 1];
		for (int i = 0; i < key.length; i++) {
			this.key[i] = Integer.MAX_VALUE;
		}
		/*
		 * int alea = (int) (Math.random() * (this.key.length)); key[alea] = 0;
		 */
		this.key[0] = 0;
	}

	public Vertex extract_min() {
		Vertex min = new Vertex(Integer.MAX_VALUE);
		for (Vertex v : Q) {
			min = (min.getNumber() > v.getNumber() ? v : min);
		}
		this.Q.remove(min);
		return min;
	}

	public void afficher_graphe() {
		System.out
				.println("Nombre de sommets : " + this.MST.getVertex().size());
		System.out.println("Nombre d'arrêtes : " + this.MST.getEdges().size());
		for (Edge e : this.MST.getEdges())
			System.out.println(e);
	}

	public GraphImpl algo() throws VertexNotFoundException, EdgeNotFoundException {
		List<Vertex> liste_vertex = new LinkedList<Vertex>();
		List<Vertex> sommetsMarques = this.graphe.getVertex();
		List<Integer> edgesValues = new LinkedList<Integer>();
		Vertex y = null, x = null;// ,v2 = null;
		int poidsMin = Integer.MAX_VALUE;

		for (int i = 0; i < this.graphe.getEdges().size(); i++)
			edgesValues.add(this.graphe.getEdges().get(i).getValue());

		Collections.sort(edgesValues);
		/*
		 * Vertex[][] listeVoisins = new
		 * Vertex[this.graphe.getVertex().size()][];
		 * //System.out.println(listeVoisins.length); for (int i = 0; i <
		 * this.graphe.getVertex().size(); i++) { liste_vertex =
		 * this.graphe.getVertexNeighbours(new Vertex(i));
		 * //System.out.println(liste_vertex.size()); listeVoisins[i] = new
		 * Vertex[liste_vertex.size()]; for(int j = 0; j < liste_vertex.size();
		 * j++){ listeVoisins[i][j] = liste_vertex.get(j);
		 * //System.out.println(liste_vertex.get(j)); } }
		 * 
		 * for (int i = 0; i < listeVoisins.length; i++) { for (int j = 0; j <
		 * listeVoisins[i].length; j++) { //System.out.println("Vertex : " + i +
		 * " voisins : " + listeVoisins[i][j]); } }
		 */
		System.out.println("ok");
		sommetsMarques.remove(0);
		while (!sommetsMarques.isEmpty()) { // n-1 tours
			// System.out.println("while");
			poidsMin = Integer.MAX_VALUE;
			for (Vertex v1 : sommetsMarques) { // parcours de l'ensemble des
												// sommets non marqués (les
												// autres étant supprimés) :
												// max n-1 tours
				// System.out.println("v1 :" + v1);
				liste_vertex = this.graphe.getVertexNeighbours(v1);// m tours
				for (Vertex v2 : liste_vertex) { // max n-1 tours
					// for(int i = 0; i < listeVoisins[v1.getNumber()-1].length;
					// i++){
					// System.out.println("v2 : " + v2);
					// v2 = listeVoisins[v1.getNumber()-1][i];
					if (!sommetsMarques.contains(v2)
							&& this.graphe.getEdge(v2, v1).getValue() < poidsMin) {
						poidsMin = this.graphe.getEdge(v2, v1).getValue();
						y = v2;
						x = v1;
						// System.out.println("sommet adjacent marqué : " + v2
						// + " poids : " + poidsMin);
					}

				}
			}

			if (x != null && y != null) {
				// System.out.println("ajout edge : " + x + " " + y +
				// " de poids : " + poidsMin);
				this.MST.addEdge(x, y, poidsMin);
				sommetsMarques.remove(x);
			}
		}

		return this.MST;
	}

	public GraphImpl algo_bis() {
		List<Edge> edgeList = this.graphe.getEdges();
		List<Integer> edgesValues = new LinkedList<Integer>();
		List<Vertex> sommetsMarques = this.graphe.getVertex();
		int poidsMin = Integer.MAX_VALUE;
		Edge edgeMin = null;
		boolean v1;
		boolean v2;

		for (int i = 0; i < this.graphe.getEdges().size(); i++)
			edgesValues.add(this.graphe.getEdges().get(i).getValue());
		Collections.sort(edgeList, new Comparator<Edge>() {

			public int compare(Edge e1, Edge e2) {
				return e1.getValue() - e2.getValue();
			}
		});

		sommetsMarques.remove(0);
		while (!sommetsMarques.isEmpty()) { // n-1 tours
			poidsMin = Integer.MAX_VALUE;
			edgeMin = null;
			for (Edge e : edgeList) { // m
				if (e.getValue() > poidsMin)
					break;
				v1 = sommetsMarques.contains(e.getStart()); // max n-1
				v2 = sommetsMarques.contains(e.getEnd()); // max n-1
				if ((v1 ^ v2) && e.getValue() < poidsMin) {
					poidsMin = e.getValue();
					edgeMin = e;
				}
			}
			if (edgeMin != null) {
				this.MST.addEdge(edgeMin);
				edgeList.remove(edgeMin);
				sommetsMarques.remove(edgeMin.getStart());
				sommetsMarques.remove(edgeMin.getEnd());

			}
		}

		return this.MST;
	}

	public static void main(String[] args) {

		GraphImpl g = new GraphImpl();
		try {
			g = new Generation().generateValueGraph(500, 0.5);
			System.out.println("yolo");
		} catch (VertexNotFoundException | VertexAlreadyExistException e) {
			e.printStackTrace();
		}

		/*
		 * for(int i = 0; i < 8; i++){ g.addVertex();
		 * //System.out.println(g.getVertex()); } try { g.addEdge(0,1, 14);
		 * g.addEdge(0,2, 8); g.addEdge(0,5, 5); g.addEdge(0,7, 6);
		 * g.addEdge(1,2, 3); g.addEdge(2,3, 10); g.addEdge(3,4, 15);
		 * g.addEdge(3,5, 7); g.addEdge(5,6, 9); g.addEdge(5,7, 12); }
		 * catch(Exception e){ e.printStackTrace(); }
		 */
		// g.affiche();
		System.out
				.println("================= Début prim ====================");
		Prim p = new Prim(g);
		long startTime = System.currentTimeMillis();
		p.algo_bis();
		System.out.println("total time : "
				+ (float) (System.currentTimeMillis() - startTime) / 1000.0);
		p.MST.affiche();
	}

}
