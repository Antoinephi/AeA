package graphe;

public class Vertex {
	private int number;
	private int color;

	/**
	 * Constructeur de sommet
	 * 
	 * @param number
	 *            le num�ro du sommet
	 */
	public Vertex(int number) {
		this.number = number;
		this.color = -1;
	}

	/**
	 * Retourne le num�ro du sommet
	 * 
	 * @return le num�ro du sommet
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Permet de v�rifier que l'objet est �gal au sommet
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		return this.getNumber() == ((Vertex) o).getNumber();
	}

	/**
	 * Retourne la couleur attribu�e au sommet
	 * 
	 * @return la couleur attribu�e au sommet
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * Attribue une couleur au sommet
	 * 
	 * @param color
	 *            la couleur � attribuer au sommet
	 */
	public void setColor(int color) {
		this.color = color;
	}
}
