package Tests;

public class Simulateur {
	
	private char[] microARN;
	
	public Simulateur(){

	}
	
	/* Génère un pré microARN sans boucle A METTRE DANS UN FICHIER GENOME */
	public char[] genPreMiARNSansBoucle(){
		char[] alphabet = {'A','C','U','G'};
		char[] reverseAlphabet = {'U','G','A','C'};
		int taille = (int)(Math.random() * (100-48)) + 48;
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
		char[] preMiARN = genPreMiARNSansBoucle();
		int nb = (int)(Math.random() * 100 - preMiARN.length);
		for(int i=0;i<preMiARN.length;i++){
			
		}
		return preMiARN;
	}
}
