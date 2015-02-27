public class Graphe {
	private String[] lesMots;
	private Liste[] listeSucc;
	private int nb;

	public Graphe(String[] lesMots) {
		this.lesMots = lesMots;
		this.nb = lesMots.length;
		this.listeSucc = new Liste[this.nb];
	}

	public void ajouterArete(int s, int d) {
		
	}

	public void lettreQuiSaute() {
		for (int i = 0; i < this.nb; i++) {
			for (int j = 0; j < this.nb; j++) {
				if (diffUneLettre(this.lesMots[i], this.lesMots[j]))
					this.ajouterArete(i,j);
			}
		}

	}

	private boolean diffUneLettre(String a, String b) {
		// a et b supposees de meme longueur
		int i = 0;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return false;
		++i;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return true;
		return false;
	}

	public static void main(String[] args) {
	}
}
