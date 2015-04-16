package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

public class Naif {

	private GraphImpl graphe;

	public Naif(GraphImpl graphe) {
		this.graphe = graphe;
	}

	/**
	 * Retourne le nombre de couleurs utilisées dans le graphe
	 * 
	 * @return le nombre de couleurs utilisées dans le graphe
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
	 * Algorithme naif de coloration de graphe
	 * @throws VertexNotFoundException 
	 */
	public void algoNaif() throws VertexNotFoundException {
		int min = -1;
		for (int i = 0; i < this.graphe.getVertex().size(); i++) {
			int j = 0;
			LinkedList<Vertex> neighboors = this.graphe
					.getVertexNeighboors(this.graphe.getVertex(i));
			LinkedList<Integer> alreadyUsedColor = new LinkedList<Integer>();
			for (Vertex vertex : neighboors) {
				if ((vertex.getColor() != -1)
						&& (!alreadyUsedColor.contains(vertex.getColor()))) {
					alreadyUsedColor.add(vertex.getColor());
				}
			}
			while (alreadyUsedColor.contains(j)) {
				j++;
			}
			this.graphe.getVertex(i).setColor(j);
		}

	}

	/**
	 * Retourne le graphe à colorer
	 * 
	 * @return le graphe à colorer
	 */
	public GraphImpl getGraphe() {
		return this.graphe;
	}
}
