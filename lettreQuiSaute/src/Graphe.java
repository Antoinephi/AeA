public class Graphe {
	private String[] lesMots;
	private Liste[] listeSucc;
	private int nb;
	private boolean[] dejaVu;
	private int pere[];

	public Graphe(String[] lesMots) {
		this.lesMots = lesMots;
		this.nb = lesMots.length;
		this.listeSucc = new Liste[this.nb];
		this.initList();
		this.dejaVu = new boolean[this.nb];
		this.pere = new int[this.nb];
	}

	private void initList() {
		for (int i = 0; i < this.lesMots.length; i++) {
			this.listeSucc[i] = new Liste(i, null);
		}
	}

	/**
	 * Ajoute une arete entre S et D
	 * 
	 * @param s
	 *            l'indice du mot S dans lesMots
	 * @param d
	 *            l'indice du mot D dans lesMots
	 */
	public void ajouterArete(int s, int d) {
		// Création des aretes
		Liste nextS = new Liste(d, null);
		Liste nextD = new Liste(s, null);

		// Recherche du dernier élément de la liste
		Liste lastS = this.lastSucc(s);
		Liste lastD = this.lastSucc(d);
		
		// Ajout de l'arete entre S et D
		lastS.setNextElement(nextS);
		lastD.setNextElement(nextD);
	}

	/**
	 * Permet de chercher le dernier successeur d'un mot
	 * 
	 * @param s
	 * @return
	 */
	private Liste lastSucc(int s) {
		Liste current = this.listeSucc[s];
			while (current.getNextElement() != null) {
				current = current.getNextElement();
			}
		//System.out.println(s  + " ---> " + current.getElement() + ":" + this.lesMots[current.getElement()]);
		return current;
	}

	public void lettreQuiSaute() {
		for (int i = 0; i < this.nb; i++) {
			for (int j = i+1; j < this.nb; j++) {
				if (diffUneLettre(this.lesMots[i], this.lesMots[j])){
					System.out.println("ajouter : " + i + " " + j + " : " + this.lesMots[i] + " : " + this.lesMots[j]);
					this.ajouterArete(i, j);
				}
			}
		}

	}

	public void afficher() {
		for (int i = 0; i < this.listeSucc.length; i++) {
			Liste l = this.listeSucc[i];
			System.out.print(this.lesMots[l.getElement()] + " -> ");
			while((l = l.getNextElement()) != null){
			}
			System.out.println();
		}
	}

	public boolean diffUneLettre(String a, String b) {
		// a et b supposees de meme longueur
		int i = 0;
		// parcouru du mot a la recherche d'une difference
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		// on a parcouru le mot complet sans trouver de difference
		if (i == a.length())
			return false;
		++i;
		// une difference a ete trouve, recherche d'une seconde difference
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		// le mot complet a ete parcouru et pas de seconde difference trouvee
		if (i == a.length())
			return true;
		// une deuxieme difference a ete trouvee
		return false;
	}
	
	public void DFS(int x){
		//Si dejaVu, on sort
		if(this.dejaVu[x])
			return;
		
		//Sinon on le met a true
		this.dejaVu[x] = true;
				
		//Element correspondant a x
		Liste current = this.listeSucc[x];
		//Parcours de ses successeurs
		while((current = current.getNextElement()) != null){
			//parcours du tableau des dejaVu
			for(int i = 0; i < this.dejaVu.length; i++){
				if(!this.dejaVu[current.getElement()]) {//si le mot n'a pas ete rencontre
					System.out.print(this.lesMots[current.getElement()] + " ");
					this.DFS(current.getElement()); //on le parcours
				}
			}
		}
	}
	
	public void visit(){
		int cpt = 0;
		for(int i = 0; i < this.dejaVu.length; i++){
			if(!this.dejaVu[i]){
				cpt++;
				System.out.print("\n" + cpt  + " : " + this.lesMots[i] + " ");
				this.DFS(i);
			}
		}		
	}

	public static void main(String[] args) {

		String[] dico3court = { "gag", "gai", "gaz", "gel", "gks", "gin",
				"gnu", "glu", "gui", "guy", "gre", "gue", "ace", "acm", "agi",
				"ait", "aie", "ail", "air", "and", "alu", "ami", "arc", "are",
				"art", "apr", "avr", "sur", "mat", "mur" };
		String[] dicocourt = { "gag", "gai", "gaz", 
				"gnu", "glu", "gui", "guy"};
		Graphe g = new Graphe(dico3court);
		/*g.lettreQuiSaute();
		g.visit();*/
		g = new Graphe(Dicos.dico4);
		g.lettreQuiSaute();
		g.visit();
		
	}
}
