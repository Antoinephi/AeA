package algorithmes;

import generationGraphes.Edge;
import generationGraphes.GraphImpl;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Kruskal {
	private GraphImpl graph;
	private static int partMin = 0;

	/**
	 * Constructeur Kruskal
	 * 
	 * @param graph
	 *            le graphe pour lequel on souhaite le mst
	 */
	public Kruskal(GraphImpl graph) {
		this.graph = graph;
	}

	/**
	 * L'algorithme de calcul du mst
	 * 
	 * @return le graphe mst correspondant au graphe de départ
	 * @throws VertexAlreadyExistException
	 */
	public GraphImpl algo() throws VertexAlreadyExistException {
		// Sortie : nouveau graphe
		GraphImpl mst = new GraphImpl();

		// Qui contient les mï¿½mes sommets
		for (int i = 0; i < this.graph.getVertex().size(); i++) {
			mst.addVertex(this.graph.getVertex().get(i).getNumber());
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
				// F := F U {e[i]}
				F.add(e[j]);
				updatePart(F, e[j]);
				//partToString(F);
			}
		}
		mst.setEdges(F);
		return mst;
	}

	/**
	 * Permet d'afficher dans quelle partie sont les sommets pour la cyclicité
	 * 
	 * @param F
	 *            l'ensemble des arètes
	 */
	public void partToString(LinkedList<Edge> F) {
		System.out.println("\n\ndébut part");
		for (Edge e : F) {
			System.out.println(e.getStart().getNumber() + " part : "
					+ e.getStart().getPart() + " , " + e.getEnd().getNumber()
					+ " part : " + e.getEnd().getPart());
		}
		System.out.println("fin part \n\n");
	}

	/**
	 * Met à jour les attributs part lors de l'ajout de l'arète e
	 * 
	 * @param F
	 *            l'ensemble des arète de départ
	 * @param e
	 *            l'arète à ajouter
	 */
	private void updatePart(LinkedList<Edge> F, Edge e) {
		// Si les sommets de la nouvelle arète ne font pas encore partie du
		// graphe, on ajoute une nouvelle partie
		if (e.getStart().getPart() == -1 && e.getEnd().getPart() == -1) {
			System.out.println("case -1 -1");
			System.out.println(e.getStart().getNumber());
			e.getStart().setPart(partMin);
			e.getEnd().setPart(partMin);
			partMin++;
			return;
		}
		// Si le sommet de départ ne fait pas encore partie du graphe, il prend
		// le numéro de partie du sommet de fin de l'arète
		if (e.getStart().getPart() == -1 && e.getEnd().getPart() != -1) {
			e.getStart().setPart(e.getEnd().getPart());
			return;
		}
		// Si le sommet de fin ne fait pas encore partie du graphe, il prend le
		// numéro de partie du sommet de départ de l'arète
		if (e.getEnd().getPart() == -1 && e.getStart().getPart() != -1) {
			e.getEnd().setPart(e.getStart().getPart());
			return;
		}
		// Sinon, on donne à tous les sommets de la même partie que le sommet de
		// fin de l'arète la partie du sommet de départ de l'arète
		for (Edge e1 : F) {
			if (e1.getStart().getPart() == e.getEnd().getPart()) {
				e1.getStart().setPart(e.getStart().getPart());
			}
			if (e1.getEnd().getPart() == e.getEnd().getPart()) {
				e1.getEnd().setPart(e.getStart().getPart());
			}
		}
	}

	/**
	 * Tri le tableau d'arète selon les valeurs décroissantes
	 * 
	 * @param e
	 *            le tableau d'arètes
	 * @return ce même tableau trié
	 */
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

	/**
	 * Vérifie si l'ajout de l'arète e rend le graphe dont les arètes sont F
	 * cyclique
	 * 
	 * @param e
	 *            l'arète à ajouter
	 * @param F
	 *            l'ensemble des arètes déjà présentes
	 * @return true si le graphe est cyclique, false sinon
	 */
	public boolean isCyclic(Edge e, LinkedList<Edge> F) {
		if (e.getStart().getPart() == -1 || e.getEnd().getPart() == -1)
			return false;
		if (e.getStart().getPart() == e.getEnd().getPart()) {
			//System.out.println("iscyclic");
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws VertexNotFoundException,
			VertexAlreadyExistException {
		GraphImpl g = new GraphImpl();
		for (int i = 0; i < 8; i++) {
			g.addVertex();
			// System.out.println(g.getVertex());
		}
		try {
			g.addEdge(0, 1, 14);
			g.addEdge(0, 2, 8);
			g.addEdge(0, 5, 5);
			g.addEdge(0, 7, 6);
			g.addEdge(1, 2, 3);
			g.addEdge(2, 3, 10);
			g.addEdge(3, 4, 15);
			g.addEdge(3, 5, 7);
			g.addEdge(5, 6, 9);
			g.addEdge(5, 7, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Kruskal algo = new Kruskal(g);
		g.affiche();
		long debut = System.currentTimeMillis();
		GraphImpl mst = algo.algo();
		long fin = System.currentTimeMillis();
		float seconds = (fin - debut) / 1000F;
		System.out.println("Kruskal effectué en :" + Float.toString(seconds)
				+ " secondes.");
		mst.affiche();
	}
}
