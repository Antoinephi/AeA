package Tests;

public class Simulateur {

	public Simulateur() {

	}

	/* Génère un pré microARN sans boucle A METTRE DANS UN FICHIER GENOME */
	public char[] genPreMiARNSansBoucle() {
		char[] alphabet = { 'A', 'C', 'U', 'G' };
		char[] reverseAlphabet = { 'U', 'G', 'A', 'C' };
		int taille = (int) (Math.random() * (101 - 70)) + 70;
		System.out.println(taille);
		char[] preMiARN = new char[taille];
		int rdm;
		for (int i = 0; i < taille / 2 + 1; i++) {
			rdm = (int) (Math.random() * 3);
			preMiARN[i] = alphabet[rdm];
			preMiARN[taille - i - 1] = reverseAlphabet[rdm];
		}
		for (int j = 0; j < taille; j++) {
			System.out.print(preMiARN[j] + " ");
		}
		return preMiARN;
	}

	/* Génère un pré microARN avec des boucles */
	public char[] genPreMiARNBoucles() {
		char[] alphabet = { 'A', 'C', 'U', 'G' };
		char[] reverseAlphabet = { 'U', 'G', 'A', 'C' };
		/* Taille du preMiARN */
		int taille = (int) (Math.random() * (101 - 70)) + 70;
		System.out.println("Taille : " + taille);
		char[] preMiARN = new char[taille];
		for(int i=0;i<preMiARN.length;i++){
			preMiARN[i] = '.'; 
		}

		/* Calcul du nombre de nucléotides dans les boucles */
		int nucleotidesSansBoucle = (int) (Math.random() * (taille+1 - 48)) + 48;
		if (nucleotidesSansBoucle % 2 == 1) {
			nucleotidesSansBoucle--;
		}
		System.out.println("nucleotides appariés : " + nucleotidesSansBoucle);
		int nucleotidesDansBoucle = taille - nucleotidesSansBoucle;
		System.out
				.println("nucleotides dans boucle : " + nucleotidesDansBoucle);

		/* Calcul du nombre de nucléotides dans la boucle finale */
		int max = 8;
		int min = 0;
		if (nucleotidesDansBoucle < 8)
			max = nucleotidesDansBoucle;
		int nucleotidesBoucleFinale = (int) (Math.random() * (max+1 - min)) + min;
		System.out.println("boucle finale : " + nucleotidesBoucleFinale);

		/* Création du preMiARN */
		/* Insertion de la boucle finale */
		int i = taille / 2 - nucleotidesBoucleFinale / 2;
		int j = i + nucleotidesBoucleFinale;
		for (int k = i; k < j; k++) {
			int rdm = (int) (Math.random() * 4);
			preMiARN[k] = alphabet[rdm];
		}
		i--;
		j++;
		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l]+ " ");
		}
		/* Insertion du reste des nucléotides */
		/* Boucles à gauche de la boucle finale */
		System.out.println("\nNucléotides à gauche");
		int itmp = i;
		while(itmp>=13 && nucleotidesDansBoucle > 0){
			int placeBoucle = (int) (Math.random() * (11-3)+3);
			int nbNucleotides = (int)(Math.random() * (4-1)+1);
			int rdm = (int)(Math.random()*4);
			itmp-=placeBoucle;
			nucleotidesDansBoucle -= nbNucleotides;
			for(int m=1;m<=nbNucleotides;m++){
			preMiARN[itmp] = alphabet[rdm];
			itmp--;
			}
		}
		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l]+ " ");
		}
		/* Boucles à droite de la boucle finale */
		int jtmp = j;
		System.out.println("\nNucléotides à droite");
		while(jtmp<=taille-13 && nucleotidesDansBoucle > 0){
			int placeBoucle = (int) (Math.random() * (11-3)+3);
			int nbNucleotides = (int)(Math.random() * (4-1)+1);
			int rdm = (int)(Math.random()*4);
			jtmp+=placeBoucle;
			nucleotidesDansBoucle -= nbNucleotides;
			for(int m=1;m<=nbNucleotides;m++){
			preMiARN[jtmp] = alphabet[rdm];
			jtmp++;
			}
		}
		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l]+ " ");
		}
		
		/* Reste des appariements */
		j--;
		System.out.println("\nReste des appariements");
		while(i>=0 && j<taille){
			while((i>=0) && (preMiARN[i] != '.')){
				i--;
			}
			while((j<taille) && (preMiARN[j] != '.')){
				j++;
			}
			while(((i>=0) && (j<taille)) && (preMiARN[i] == '.') && (preMiARN[j] == '.')){
				int rdm = (int) (Math.random() * 4);
				preMiARN[i] = alphabet[rdm];
				preMiARN[j] = reverseAlphabet[rdm];
				i--;
				j++;
			}
		}
		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l]+ " ");
		}
		
		/* Remplissage des derniers restants */
		System.out.println("\nRemplissage des derniers");
		if(i>-1){
			while(i>=0){
				int rdm = (int) (Math.random() * 4);
				preMiARN[i] = alphabet[rdm];
				i--;
			}
		}
		
		if(j<taille){
			while(j<taille){
				int rdm = (int) (Math.random() * 4);
				preMiARN[j] = alphabet[rdm];
				j++;
			}
		}
		
		for (int l = 0; l < preMiARN.length; l++) {
			System.out.print(preMiARN[l]+ " ");
		}
		System.out.println();
		/*for (int l = 0; l < preMiARN.length; l++) {
			System.out.println(l + " : " + " " + preMiARN[l]);
		}*/

		return preMiARN;
	}
}
