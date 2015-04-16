package generationGraphes;

import java.util.Random;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Generation implements RandomGraphGenerator {

	/**
	 * Algorithme de g�n�ration de graphe
	 * 
	 * @param n
	 *            le nombre d'ar�te
	 * @param p
	 *            la probabilit� d'avoir une ar�te entre deux sommets
	 * @return un graphe al�atoire non valu�
	 */
	public GraphImpl generateErdosRenyiGraph(int n, double p)
			throws VertexAlreadyExistException, VertexNotFoundException {
		if (p < 0 || p > 1)
			throw new IllegalArgumentException();
		GraphImpl graphe = new GraphImpl();
		double alea;
		// On génère n sommets numérotés de 1 à n
		for (int i = 1; i <= n; i++) {
			graphe.addVertex(i);
		}
		// Puis on ajoute les arètes selon la probabilité p
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				alea = Math.random();
				if (alea < p) {
					graphe.addEdge(i, j);
				}
			}
		}
		return graphe;
	}

	/**
	 * Permet de g�n�rer un graphe valu�
	 * 
	 * @param n
	 *            le nombre de sommets du graphe
	 * @param p
	 *            la probabilit� d'avoir une ar�te entre deux sommets
	 * @return un graphe al�atoire valu�
	 * @throws VertexNotFoundException
	 * @throws VertexAlreadyExistException
	 */
	public GraphImpl generateValueGraph(int n, double p)
			throws VertexNotFoundException, VertexAlreadyExistException {
		GraphImpl graphe = generateErdosRenyiGraph(n, p);
		for (int i = 0; i < graphe.getEdges().size(); i++) {
			int aleaValue = (int) (new Random().nextDouble()
					* (Math.pow(n, 2) - 1) * Math.pow(10, 2)); // Changed Random
																// calcul
			graphe.getEdges().get(i).setValue(aleaValue);
		}
		return graphe;
	}

}
