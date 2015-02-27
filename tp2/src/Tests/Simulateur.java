package Tests;

public class Simulateur {
	
	public Simulateur(){

	}
	
	/* Génère un pré microARN sans boucle A METTRE DANS UN FICHIER GENOME */
	public char[] genPreMiARNSansBoucle(){
		char[] alphabet = {'A','C','U','G'};
		char[] reverseAlphabet = {'U','G','A','C'};
		int taille = (int)(Math.random() * (100-70)) + 70;
		System.out.println(taille);
		char[] preMiARN = new char[taille]; 
		int rdm;
		for(int i=0;i<taille/2+1;i++){
			rdm = (int)(Math.random() * 3);
			preMiARN[i] = alphabet[rdm];
			preMiARN[taille-i-1] = reverseAlphabet[rdm];
		}
		for(int j=0;j<taille;j++){
			System.out.print(preMiARN[j]+" ");
		}
		return preMiARN;
	}
	
	/* Génère un pré microARN avec des boucles */
	public char[] genPreMiARNBoucles(){
		char[] alphabet = {'A','C','U','G'};
		char[] reverseAlphabet = {'U','G','A','C'};
		/* Taille du preMiARN */
		int taille = (int)(Math.random() * (100 - 70))+70;
		System.out.println("Taille : "+taille);
		char[] preMiARN = new char[taille];
		
		/* Calcul du nombre de nucléotides dans les boucles */
		int nucleotidesSansBoucle = (int)(Math.random()*(taille - 48))+48;
		if(nucleotidesSansBoucle%2 == 1){
			nucleotidesSansBoucle--;
		}
		System.out.println("nucleotides appariés : "+nucleotidesSansBoucle);
		int nucleotidesDansBoucle = taille - nucleotidesSansBoucle;
		System.out.println("nucleotides dans boucle : "+nucleotidesDansBoucle);
		
		/* Calcul du nombre de nucléotides dans la boucle finale */
		int max = 8;
		int min = 0;
		if(nucleotidesDansBoucle < 8)
			max = nucleotidesDansBoucle;
		int nucleotidesBoucleFinale = (int)(Math.random() * (max - min))+min;
		System.out.println("boucle finale : "+nucleotidesBoucleFinale);
		
		/* Création du preMiARN */
		/* Insertion de la boucle finale */
		int i = taille/2-nucleotidesBoucleFinale/2;
		int j = i+nucleotidesBoucleFinale;
		for(int k = i;k<j;k++){
			int rdm = (int)(Math.random() * 3);
			preMiARN[k] = alphabet[rdm];
		}
		i--;
		j++;
		
		/* Insertion du reste des nucléotides */
		while(i!=0 && j!=taille-1){
			int rdm = (int)(Math.random() * (nucleotidesSansBoucle - 3))+3;
			for(int m=0;m<rdm;m++){
				int nucleoRdm = (int)(Math.random() * 3);
				preMiARN[i] = alphabet[nucleoRdm];
				preMiARN[j] = reverseAlphabet[nucleoRdm];
				nucleotidesSansBoucle -= 2;
			}
		}
		for(int l=0;l<preMiARN.length;l++){
			System.out.println(l+" : "+" "+preMiARN[l]);
		}
		
		return preMiARN;
	}
}
