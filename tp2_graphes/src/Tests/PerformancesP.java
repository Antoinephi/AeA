package Tests;

import generationGraphes.Generation;
import generationGraphes.GraphImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import algorithmes.*;

public class PerformancesP {
	public static void main(String[] args) throws IOException, VertexNotFoundException, VertexAlreadyExistException {

		Float t1 = (float) 0;
		Float t2 = (float) 0;

		FileWriter fw = new FileWriter("comparaisonP.csv", true);
		BufferedWriter output = new BufferedWriter(fw);

		for (double i = 0.1; i <= 1; i += 0.1) {
			t1 = (float) 0;
			t2 = (float) 0;

			output.write(i + ";");
			output.flush();
			for (int j = 0; j < 50; j++) {

				Generation g = new Generation();
				GraphImpl graphe = g.generateValueGraph(300, i);
				System.out.println(graphe.getVertex().size());
				// graphe.affiche();

				Prim algo1 = new Prim(graphe);
				long tempsDebut = System.currentTimeMillis();
				algo1.algo();
				System.out.println("prim done");
				long tempsFin = System.currentTimeMillis();
				float seconds = (tempsFin - tempsDebut) / 1000F;
				System.out.println("Prim effectu� en :"
						+ Float.toString(seconds) + " secondes.");
				t1 += seconds;
				
				Kruskal algo2 = new Kruskal(graphe);
				tempsDebut = System.currentTimeMillis();
				algo2.algo();
				System.out.println("kruskal done");

				tempsFin = System.currentTimeMillis();
				seconds = (tempsFin - tempsDebut) / 1000F;
				System.out.println("Prim effectu� en :"
						+ Float.toString(seconds) + " secondes.");
				t2 += seconds;
			}
			float t1Moy = t1/50;
			float t2Moy = t2/50;
			
			output.write(t1Moy + ";");
			output.write(t2Moy + "\n");
			output.flush();
			
		}
		output.close();

	}
}
