package generationGraphes;

import java.util.Random;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Generation implements RandomGraphGenerator {

	/**
	 * Algorithme de génération de graphe
	 * 
	 * @param n
	 *            le nombre d'arète
	 * @param p
	 *            la probabilité d'avoir une arète entre deux sommets
	 * @return un graphe aléatoire non valué
	 */
	public GraphImpl generateErdosRenyiGraph(int n, double p)
			throws VertexAlreadyExistException, VertexNotFoundException {
		if (p < 0 || p > 1)
			throw new IllegalArgumentException();
		GraphImpl graphe = new GraphImpl();
		double alea;
		// On gÃ©nÃ¨re n sommets numÃ©rotÃ©s de 1 Ã  n
		for (int i = 1; i <= n; i++) {
			graphe.addVertex(i);
		}
		// Puis on ajoute les arÃ¨tes selon la probabilitÃ© p
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
	 * Permet de générer un graphe valué
	 * 
	 * @param n
	 *            le nombre de sommets du graphe
	 * @param p
	 *            la probabilité d'avoir une arète entre deux sommets
	 * @return un graphe aléatoire valué
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
