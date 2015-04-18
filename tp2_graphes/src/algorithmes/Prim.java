package algorithmes;

import generationGraphes.Edge;
import generationGraphes.Generation;
import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Prim {
	private GraphImpl graphe;
	private GraphImpl MST;

	public Prim(GraphImpl graphe) {
		this.graphe = graphe;
		this.MST = new GraphImpl();
	}

	/**
	 * Algorithme de prim : calcul d'un arbre de recouvrement minimum
	 * 
	 * @return MST : Minimum Spanning Tree
	 */
	public GraphImpl algo() {
		List<Edge> edgeList = this.graphe.getEdges();
		List<Vertex> sommetsMarques = this.graphe.getVertex();
		int poidsMin = Integer.MAX_VALUE;
		Edge edgeMin = null;
		boolean v1;
		boolean v2;
		// Si le graphe ne contient qu'une seule arrête, le MST est lui même
		if (edgeList.size() < 2)
			return this.graphe;

		/**
		 * On trie la liste des arrêtes par ordre croissant de valeur, permet
		 * par la suite d'accélérer le calcul
		 */
		Collections.sort(edgeList, new Comparator<Edge>() {

			public int compare(Edge e1, Edge e2) {
				return e1.getValue() - e2.getValue();
			}
		});
		// On marque un sommet arbitraire
		sommetsMarques.remove(0);
		// Tant que tous les sommets ne sont pas marqués
		while (!sommetsMarques.isEmpty()) { // n-1 tours
			poidsMin = Integer.MAX_VALUE;
			edgeMin = null;
			// On parcours chaque arrête
			for (Edge e : edgeList) { // m tours max
				/**
				 * Si la valeur de l'arrête courante est > à la val min
				 * enregristrée ça ne sert à rien d'aller plus loin, puisque
				 * la liste est triée.
				 */
				if (e.getValue() > poidsMin)
					break;

				/**
				 * On regarde si les sommets de l'arrête sont marqués
				 */
				v1 = sommetsMarques.contains(e.getStart()); // max n-1
				v2 = sommetsMarques.contains(e.getEnd()); // max n-1
				// Un seul des sommets doit être marqué et la val de l'arrête
				// < poidsMin
				if ((v1 ^ v2) && e.getValue() < poidsMin) {
					poidsMin = e.getValue();
					edgeMin = e;
				}
			}
			// Si on a trouvé une arrete correspondant
			if (edgeMin != null) {

				this.MST.addEdge(edgeMin); // on ajoute
				// On retire de la liste, moins de tours de boucles pour la
				// suite
				edgeList.remove(edgeMin);
				// On marque les sommets de l'arrête en les retirant de la
				// liste
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
			g.affiche();
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
		p.algo();
		System.out.println("total time : "
				+ (float) (System.currentTimeMillis() - startTime) / 1000.0
				+ " secondes");
		p.MST.affiche();
	}

}
