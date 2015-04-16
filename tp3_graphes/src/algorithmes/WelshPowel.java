package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

public class WelshPowel {
	private GraphImpl graphe;
	private int[] degreesDec;

	public WelshPowel(GraphImpl graphe) throws VertexNotFoundException {
		this.graphe = graphe;
		this.degreesDec = initDegrees();
	}

	/**
	 * Retourne le nombre de couleurs utilisées dans la coloration du graphe
	 * 
	 * @return le nombre de couleurs utilisées dans la coloration du graphe
	 */
	public int getMaxColor() {
		int max = 0;
		for (Vertex v : graphe.getVertex()) {
			if (v.getColor() > max)
				max = v.getColor();
		}
		return max;
	}

	/**
	 * Retourne un tableau contenant les sommets triés dans l'ordre décroissant
	 * 
	 * @return un tableau contenant les sommets triés dans l'ordre décroissant
	 * @throws VertexNotFoundException 
	 */
	public int[] initDegrees() throws VertexNotFoundException {
		int[] degrees = new int[this.graphe.getVertex().size()];
		int[] degreesDec = new int[this.graphe.getVertex().size()];
		for (int i = 0; i < this.graphe.getVertex().size(); i++) {
			degrees[i] = this.graphe.getVertexDegree(this.graphe.getVertex(i));
		}
		for (int j = 0; j < degreesDec.length; j++) {
			int max = degrees[0];
			int maxInd = 0;
			for (int k = 1; k < degrees.length; k++) {
				if (max < degrees[k]) {
					max = degrees[k];
					maxInd = k;
				}
			}
			degrees[maxInd] = -1;
			degreesDec[j] = maxInd;

		}
		return degreesDec;
	}

	/**
	 * Retourne le tableau degreesDec
	 * 
	 * @return degreesDec
	 */
	public int[] getDegreesDec() {
		return this.degreesDec;
	}

	/**
	 * Retourne le graphe à colorer
	 * 
	 * @return le graphe à colorer
	 */
	public GraphImpl getGraphe() {
		return this.graphe;
	}

	/**
	 * Algorithme de coloration de graphe
	 * @throws VertexNotFoundException 
	 */
	public void algoWelshPowel() throws VertexNotFoundException {
		int colorMax = 0;
		for (int i = 0; i < this.degreesDec.length; i++) {
			if (this.graphe.getVertex(this.degreesDec[i]).getColor() == -1) {
				this.graphe.getVertex(this.degreesDec[i]).setColor(colorMax);
				LinkedList<Vertex> neighboors = this.graphe
						.getVertexNeighboors(this.graphe
								.getVertex(this.degreesDec[i]));
				for (Vertex vertex : this.graphe.getVertex()) {
					if ((!neighboors.contains(vertex))
							&& (vertex.getColor() == -1))
						vertex.setColor(colorMax);
					neighboors.addAll(this.graphe.getVertexNeighboors(vertex));
				}
				colorMax++;
			}
		}
	}
}
