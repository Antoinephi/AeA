package Tests;

import generationGraphes.Generation;
import generationGraphes.GraphImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import algorithmes.*;

public class PerformancesN {
	public static void main(String[] args) throws IOException, VertexNotFoundException, VertexAlreadyExistException {

		Float t1 = (float) 0;
		Float t2 = (float) 0;

		FileWriter fw = new FileWriter("comparaisonN.csv", true);
		BufferedWriter output = new BufferedWriter(fw);

		for (int i = 5; i <= 500; i += 5) {
			t1 = (float) 0;
			t2 = (float) 0;

			output.write(i + ";");
			output.flush();
			for (int j = 0; j < 50; j++) {

				Generation g = new Generation();
				GraphImpl graphe = g.generateValueGraph(i, 0.5);
				System.out.println("nb sommets : " + graphe.getVertex().size());
				System.out.println("nb arretes : " + graphe.getEdges().size());
				graphe.affiche();

				Prim algo1 = new Prim(graphe);
				long tempsDebut = System.currentTimeMillis();
				algo1.algo_bis();
				System.out.println("prim done");
				long tempsFin = System.currentTimeMillis();
				float seconds = (tempsFin - tempsDebut) / 1000F;
				t1 += seconds;
				
				Kruskal algo2 = new Kruskal(graphe);
				tempsDebut = System.currentTimeMillis();
				algo2.algo();
				System.out.println("kruskal done");

				tempsFin = System.currentTimeMillis();
				seconds = (tempsFin - tempsDebut) / 1000F;
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
