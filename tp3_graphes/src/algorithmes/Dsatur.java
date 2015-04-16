package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

/**
 * Dsatur est un algorithme de coloration de graphe utilisant le degr� de chaque
 * sommet et son degr� de saturation
 *
 */
public class Dsatur {
	private GraphImpl graphe;
	private int[] degrees;
	private int[] dsatur;

	/**
	 * L'initialisation de l'algorithme Dsatur prend en param�tre un graphe et
	 * cr�e les tableaux de degr�s et de degr�s de saturation, qui sont au d�but
	 * identiques
	 * 
	 * @param graphe
	 * @throws VertexNotFoundException 
	 */
	public Dsatur(GraphImpl graphe) throws VertexNotFoundException {
		this.graphe = graphe;
		this.degrees = initDegrees();
		this.dsatur = initDsatur();
	}

	/**
	 * Permet d'obtenir le nombre de couleurs obtenu apr�s l'algorithme
	 * 
	 * @return le nombre de couleurs du graphe, -1 si le graphe n'est pas encore
	 *         color�
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
	 * R�cup�re la valeur maximale d'un tableau d'entier
	 * 
	 * @param tab
	 *            un tableau d'entier
	 * @return l'indice de la valeur maximale
	 */
	public LinkedList<Integer> getMax(int[] tab) {
		LinkedList<Integer> indMax = new LinkedList<Integer>();
		int max = tab[0];
		indMax.add(0);
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > max) {
				indMax.clear();
				max = tab[i];
				indMax.add(i);
			} else if (tab[i] == max) {
				indMax.add(i);
			}
		}
		return indMax;
	}

	/**
	 * Initialisation du tableau de degr�e de saturation des sommets, au d�part
	 * initialis�s avec le degr� du sommet
	 * 
	 * @return le tableau de degr�e de saturation initialis� avec les degr�s des
	 *         sommets
	 * @throws VertexNotFoundException 
	 */
	public int[] initDsatur() throws VertexNotFoundException {
		int[] dsatur = new int[this.graphe.getVertex().size()];
		for (int i = 0; i < dsatur.length; i++) {
			dsatur[i] = this.graphe.getVertexDegree(this.graphe.getVertex()
					.get(i));
		}
		return dsatur;
	}

	/**
	 * Initialise le tableau de degr�s des sommets
	 * 
	 * @return le tableau de degr�s des sommets
	 * @throws VertexNotFoundException 
	 */
	public int[] initDegrees() throws VertexNotFoundException {
		int[] degrees = new int[this.graphe.getVertex().size()];
		for (int i = 0; i < this.graphe.getVertex().size(); i++) {
			degrees[i] = this.graphe.getVertexDegree(this.graphe.getVertex(i));
		}
		return degrees;
	}

	/**
	 * Retourne le tableau des degr�s des sommets
	 * 
	 * @return le tableau des degr�s des sommets
	 */
	public int[] getDegrees() {
		return this.degrees;
	}

	/**
	 * Retourne le graphe � colorer
	 * 
	 * @return le graphe � colorer
	 */
	public GraphImpl getGraphe() {
		return this.graphe;
	}

	/**
	 * Calcule la couleur minimum possible pour un sommet en fonction de ses
	 * voisins
	 * 
	 * @param le
	 *            num�ro du sommet � colorer
	 * @return number la couleur minimum que l'on peut affecter � un sommet
	 * @throws VertexNotFoundException 
	 */
	public int getMinColor(int number) throws VertexNotFoundException {
		LinkedList<Integer> alreadyUsedColor = new LinkedList<Integer>();
		LinkedList<Vertex> neighboors = this.graphe
				.getVertexNeighboors(this.graphe.getVertex().get(number));
		for (Vertex vertex : neighboors) {
			if ((vertex.getColor() != -1)
					&& (!alreadyUsedColor.contains(vertex.getColor()))) {
				alreadyUsedColor.add(vertex.getColor());
			}
		}
		int min = 0;
		while (alreadyUsedColor.contains(min)) {
			min++;
		}
		return min;
	}

	/**
	 * Affiche le tableau de degr�s de saturation
	 */
	public void dsaturToString() {
		for (int i = 0; i < this.dsatur.length; i++) {
			System.out.println(i + " " + this.dsatur[i]);
		}
	}

	/**
	 * Algorithme de coloration de graphe Dsatur
	 * @throws VertexNotFoundException 
	 */
	public void algoDSATUR() throws VertexNotFoundException {
		int max = this.getMax(this.degrees).get(0);
		this.graphe.getVertex().get(max).setColor(0);
		LinkedList<Vertex> neighboors = this.graphe
				.getVertexNeighboors(this.graphe.getVertex().get(max));
		this.dsatur[max] = -1;
		for (Vertex vertex : neighboors) {
			this.dsatur[vertex.getNumber() - 1] = 1;
		}
		for (int i = 1; i < this.degrees.length; i++) {
			LinkedList<Integer> maxDsatur = this.getMax(dsatur);
			int num;
			if (maxDsatur.size() == 1) {
				num = maxDsatur.get(0);
			} else {
				num = getMax(maxDsatur);
			}
			this.graphe.getVertex().get(num).setColor(this.getMinColor(num));
			this.dsatur[num] = -1;
			// this.dsaturToString();
			neighboors = this.graphe.getVertexNeighboors(this.graphe
					.getVertex().get(num));
			for (Vertex vertex : neighboors) {
				if ((vertex.getColor() == -1)
						&& (this.dsatur[vertex.getNumber() - 1] < this.graphe
								.getVertexDegree(vertex))) {
					this.dsatur[vertex.getNumber() - 1]++;
				}
			}
		}
	}

	/**
	 * Retourne l'�l�ment maximal d'une liste
	 * 
	 * @param list
	 *            une liste d'entier
	 * @return l'indice de l'�l�ment maximal du graphe
	 * @throws VertexNotFoundException 
	 */
	private int getMax(LinkedList<Integer> list) throws VertexNotFoundException {
		int max = list.get(0);
		int degreeMax = this.graphe.getVertexDegree(this.graphe.getVertex(list
				.get(0)));
		for (Integer number : list) {
			if (this.graphe.getVertexDegree(this.graphe.getVertex(number)) > degreeMax) {
				max = number;
				degreeMax = this.graphe.getVertexDegree(this.graphe
						.getVertex(number));
			}
		}
		return max;
	}

}
