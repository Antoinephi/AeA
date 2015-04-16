package generationGraphes;

public class Vertex {
	private int number;
	private int part;

	public Vertex(int number) {
		this.number = number;
		this.part = -1;
	}

	/**
	 * Set la part pour Kruskal (iscyclic)
	 * 
	 * @param part
	 *            la partie à laquelle fait partie le sommet
	 */
	public void setPart(int part) {
		this.part = part;
	}

	/**
	 * Retourne la partie à laquelle fait partie le sommet
	 * 
	 * @return part la partie à laquelle fait partie le sommet
	 */
	public int getPart() {
		return this.part;
	}

	/**
	 * Retourne le numéro du sommet
	 * 
	 * @return le numéro du sommet
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Vérifie que l'objet o est bien le sommet boolean
	 */
	public boolean equals(Object o) {
		return this.getNumber() == ((Vertex) o).getNumber();
	}

}
