import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import graphe.Generation;
import graphe.GraphImpl;
import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import algorithmes.Dsatur;
import algorithmes.Naif;
import algorithmes.WelshPowel;

public class testsPerf {
	public static void main(String[] args) throws VertexNotFoundException,
			VertexAlreadyExistException, IOException {
		Float t1 = (float) 0;
		Float t2 = (float) 0;
		Float t3 = (float) 0;

		int nbColor1 = 0;
		int nbColor2 = 0;
		int nbColor3 = 0;
		
		FileWriter fw = new FileWriter("comparaisonPtime.csv", true);
		BufferedWriter output = new BufferedWriter(fw);
		
		for (double i = 0.1; i <=1; i += 0.1) {
			t1 = (float) 0;
			t2 = (float) 0;
			t3 = (float) 0;

			nbColor1 = 0;
			nbColor2 = 0;
			nbColor3 = 0;
			
			output.write(i+";");
			output.flush();
			for (int j = 0; j < 50; j++) {

				Generation g = new Generation();
				GraphImpl graphe = g.generateValueGraph(1000, i);
				System.out.println(graphe.getVertex().size());
				// graphe.affiche();

				Naif algo1 = new Naif(graphe);
				long tempsDebut = System.currentTimeMillis();
				algo1.algoNaif();
				long tempsFin = System.currentTimeMillis();
				float seconds = (tempsFin - tempsDebut) / 1000F;
				System.out.println("Naif effectué en :"
						+ Float.toString(seconds) + " secondes.");
				System.out.println("Naif nous donne : " + algo1.getMaxColor()
						+ " couleurs");
				t1 += seconds;
				nbColor1 += algo1.getMaxColor();

				WelshPowel algo2 = new WelshPowel(graphe);
				tempsDebut = System.currentTimeMillis();
				algo2.algoWelshPowel();
				tempsFin = System.currentTimeMillis();
				System.out.println("\n\nWelshPowel effectué en :"
						+ Float.toString(seconds) + " secondes.");
				System.out.println("WelshPowel nous donne : "
						+ algo2.getMaxColor() + " couleurs");
				t2 += seconds;
				nbColor2 += algo2.getMaxColor();

				tempsDebut = System.currentTimeMillis();
				Dsatur algo3 = new Dsatur(graphe);
				algo3.algoDSATUR();

				tempsFin = System.currentTimeMillis();
				seconds = (tempsFin - tempsDebut) / 1000F;
				System.out.println("\n\nDsatur effectué en :"
						+ Float.toString(seconds) + " secondes.");
				System.out.println("Dsatur nous donne : " + algo3.getMaxColor()
						+ " couleurs");
				t3 += seconds;
				nbColor3 += algo3.getMaxColor();
			}
			float t1Moy = t1 / 50;
			output.write(t1Moy+";");
			output.flush();

			System.out.println("algo naif prend en moyenne : " + t1Moy
					+ " secondes");
			float t2Moy = t2 / 50;
			output.write(t2Moy+";");
			output.flush();

			System.out.println("algo Welsh Powel prend en moyenne : " + t2Moy
					+ " secondes");

			float t3Moy = t3 / 50;
			output.write(t3Moy+"\n");
			output.flush();

			System.out.println("algo Dsatur prend en moyenne : " + t3Moy
					+ " secondes");

			int nbColor1Moy = nbColor1 / 50;
			System.out.println("\n algo naif a en moyenne : " + nbColor1Moy
					+ " couleurs");


			int nbColor2Moy = nbColor2 / 50;
			System.out.println("algo Welsh Powel a en moyenne : " + nbColor2Moy
					+ " couleurs");


			int nbColor3Moy = nbColor3 / 50;
			System.out.println("algo dsatur a en moyenne : " + nbColor3Moy
					+ " couleurs");

		}
	}
}
