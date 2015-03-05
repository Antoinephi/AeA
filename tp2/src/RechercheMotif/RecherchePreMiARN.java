package RechercheMotif;

public class RecherchePreMiARN {

	char[] sequence;

	public RecherchePreMiARN(char[] sequence) {
		this.sequence = sequence;
	}

	/* A retirer avant de rendre ? */
	public void calcul() {
		for (int i = this.sequence.length - (this.sequence.length / 2 + 1), j = this.sequence.length / 2; i >= 0
				&& j <= this.sequence.length; i--, j++) {

			System.out.println(this.sequence[i] + ":" + i + " "
					+ this.sequence[j] + ":" + j);
			if (this.sequence[j] == this.sequence[i])
				System.out.println("paire");

		}
	}

	/**
	 * Verifie si les 2 nucléotides sont des complémentaires ou non via un
	 * parcours de tableau à 2 dimensions chaque dimension contenant un des
	 * complémentaires à l'indice i
	 * 
	 * @param a
	 *            représente le premier nucléotide
	 * @param b
	 *            représente le second nucléotide
	 * @return true si a & b se retrouvent au même indice i de leurs tableaux
	 *         respectifs, false sinon
	 */
	public boolean estUnAppariement(char a, char b) {
		char[][] appariements = { { 'A', 'C', 'G', 'U', 'G', 'U' },
				{ 'U', 'G', 'U', 'A', 'C', 'G' } };
		for (int i = 0; i < appariements[0].length; i++) {
			if (appariements[0][i] == a && appariements[1][i] == b)
				return true;
		}
		return false;
	}

	/**
	 * Algorithme d'alignement de séquence Needleman and Wunsch adapté pour la
	 * recherche de pré micro ARN
	 * 
	 * @return l'alignement de la séquence
	 */
	public char[] algoNeedlemanAndWunsch() {
		int[][] table;
		System.out.println(this.sequence.length / 2 + 1);
		table = new int[(this.sequence.length / 2) + 1][(this.sequence.length / 2) + 1];
		int[][] tableI = new int[(this.sequence.length / 2 + 1)][(this.sequence.length / 2) + 1];
		int[][] tableJ = new int[(this.sequence.length / 2 + 1)][(this.sequence.length / 2) + 1];
		int sub, del, ins;
		table[0][0] = 0;
		/* Initialisation du tableau */
		for (int i = 1; i < table.length; i++) {
			table[i][0] = -2 * i;
			table[0][i] = -2 * i;
		}
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table.length; j++) {
				// sub = table[i-1][j-1] +
				// (this.estUnAppariement(this.sequence[i],
				// this.sequence[this.sequence.length-j]) ? 2 : -1);
				sub = table[i - 1][j - 1]
						+ (this.estUnAppariement(this.sequence[table.length - i
								- 1], this.sequence[table.length + j - 2]) ? 2
								: -1);
				del = table[i - 1][j] - 2;
				ins = table[i][j - 1] - 2;

				if (sub > del && sub > ins) {
					table[i][j] = sub;
					tableI[i][j] = i - 1;
					tableJ[i][j] = j - 1;
				} else if (del > sub && del > ins) {
					table[i][j] = del;
					tableI[i][j] = i - 1;
					tableJ[i][j] = j;
				} else {
					table[i][j] = ins;
					tableI[i][j] = i;
					tableJ[i][j] = j - 1;

				}
			}
		}
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + " | ");
			}
			System.out.println();

		}
		System.out.println();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print("i:" + tableI[i][j] + " j:" + tableJ[i][j]
						+ " | ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print("i:" + i + " j:" + j + " | ");
			}
			System.out.println();
		}

		/* Remontée */
		int iMax = 0;
		int jMax = 0;
		int max = -2;
		char[] alignement = new char[this.sequence.length];
		for (int i = 1; i < table.length; i++) {
			if (table[i][table[0].length - 1] > max) {
				max = table[i][table[0].length - 1];
				iMax = i;
				jMax = table[0].length - 1;
			}
		}
		for (int j = 1; j < table[0].length; j++) {
			if (table[table[0].length - 1][j] > max) {
				max = table[table[0].length - 1][j];
				iMax = table[0].length - 1;
				jMax = j;
			}
		}
		System.out.println("max : " + max);
		System.out.println("i : " + iMax);
		System.out.println("j : " + jMax);
		for (int k = 0; k < this.sequence.length; k++) {
			alignement[k] = this.sequence[k];
		}
		while (iMax != 0 || jMax != 0) {
			if (tableI[iMax][jMax] == iMax) {
				alignement[table.length + 1 - iMax] = '_';
				jMax--;
			} else if (tableJ[iMax][jMax] == jMax) {
				alignement[table.length / 2 + jMax] = '_';
				iMax--;
			} else {
				if (table[iMax][jMax] == table[iMax - 1][jMax - 1] - 1) {
					alignement[alignement.length / 2 - iMax] = '_';
					alignement[alignement.length / 2 + jMax - 1] = '_';
				}
				iMax--;
				jMax--;
			}
		}
		for (int l = 0; l < alignement.length; l++) {
			System.out.print(alignement[l] + " ");
		}
		System.out.println();
		return alignement;
	}

	public char[] alignement(int[][] NandWresult) {
		int iMax = 0;
		int jMax = 0;
		int max = 0;
		char[] alignement = new char[this.sequence.length];
		for (int i = 0; i < NandWresult.length; i++) {
			if (NandWresult[i][NandWresult[0].length - 1] > max) {
				max = NandWresult[i][NandWresult[0].length - 1];
				iMax = i;
				jMax = NandWresult[0].length - 1;
			}
		}
		for (int j = 0; j < NandWresult[0].length; j++) {
			if (NandWresult[NandWresult[0].length - 1][j] > max) {
				max = NandWresult[NandWresult[0].length - 1][j];
				iMax = NandWresult[0].length - 1;
				jMax = j;
			}
		}
		System.out.println("max : " + max);
		System.out.println("i : " + iMax);
		System.out.println("j : " + jMax);
		return alignement;
	}

	/**
	 * Permet de vérifier si l'alignement trouvée par Needleman et Wunsch peut
	 * être un préMicro ARN
	 * 
	 * @param alignement
	 * @return true or false si l'alignement peut être un préMiARN ou non
	 */
	public boolean isPreMiARN(char[] alignement) {
		/* Bonne taille */
		if ((alignement.length < 70) || (alignement.length > 100)){
			System.out.println("KO : Mauvaise taille");
			return false;
		}
		System.out.println("OK : Bonne taille");

		/* Bon nombre d'appariements */
		int appariements = 0;
		for (int i = 0; i < alignement.length; i++) {
			if (alignement[i] != '_')
				appariements++;
		}
		if (appariements < 48){
			System.out.println("KO : Pas assez de nucléotides appariés");
			return false;
		}
		System.out.println("OK : Bon nombre d'appariement");

		/*
		 * Appariements apparaissent dans des groupes d'au moins trois
		 * nucléotides successifs
		 */
		int i = 0;
		while (i < alignement.length) {
			if (alignement[i] != '_') {
				if ((alignement[i + 1] == '_') || (alignement[i + 2] == '_')){
					System.out.println("KO : Mauvais groupes pour les appariements");
					return false;
				}
				while ((i<alignement.length) && (alignement[i] != '_'))
					i++;
			} else {
				i++;
			}
		}
		System.out.println("OK : Bons groupes pour les appariements");

		/* Vérifie que la boucle finale a au plus 8 nucléotides */
		i = alignement.length/2;
		int j = alignement.length/2+1;
		int nbFinale = 0;
		while(alignement[i] == '_'){
			nbFinale++;
			i--;
		}
		while(alignement[j] == '_'){
			nbFinale++;
			j++;
		}
		
		if(nbFinale > 8){
			System.out.println("KO : Trop de nucléotides dans la boucle finale");
			return false;
		}
		System.out.println("OK : Bon nombre de nucléotides dans la boucle finale");
		/* Vérifie que les autres boucles ont au plus 3 nucléotides */
		/* A gauche de la boucle */
		while(i>=0){
			if(alignement[i] == '_'){
				int nb = 0;
				while(alignement[i] == '_'){
					nb++;
					i--;
				}
				if(nb>3){
					System.out.println("KO : Trop de nucléotides dans une boucle à gauche");
					return false;
				}
			} else {
				i--;
			}
		}
		System.out.println("OK : Bon nombre de nucléotides dans les boucles à gauche");
		/* Et à droite */
		while(j<alignement.length){
			if(alignement[j] == '_'){
				int nb = 0;
				while(alignement[j] == '_'){
					nb++;
					j++;
				}
				if(nb>3){
					System.out.println("KO : Trop de nucléotides dans une boucle à droite");
					return false;
				}
			} else {
				j++;
			}
		}
		System.out.println("OK : Bon nombre de nucléotides dans les boucles à droite");
		return true;
	}

	public static void main(String[] args) {
		char[] sequence = { 'U', 'A', 'U', 'C' };
		char[] sequence1 = { 'C', 'U', 'A', 'C', 'A', 'G', 'U', 'A', 'G', 'C',
				'C', 'G', 'A', 'U', 'A' };
		char[] sequence2 = { 'A', 'U', 'A', 'G', 'C', 'C', 'G', 'U', 'A', 'C',
				'U', 'G', 'U', 'A', 'G', 'G' };
		char[] sequence3 = { 'A', 'A' };
		RecherchePreMiARN algo = new RecherchePreMiARN(sequence1);
		char[] result = algo.algoNeedlemanAndWunsch();
		System.out.println(algo.isPreMiARN(result));
	}

}
