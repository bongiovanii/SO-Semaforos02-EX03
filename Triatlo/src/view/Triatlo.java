package view;

import controller.Atleta;

public class Triatlo {

	public static void main(String[] args) throws InterruptedException{
		Atleta[] atletas = new Atleta[25];
        for (int i = 0; i < 25; i++) {
            atletas[i] = new Atleta(i + 1);
            atletas[i].start();
        }
        for (int i = 0; i < 25; i++) {
            atletas[i].join();
        }
        
        for (int i = 0; i < Atleta.atletasFinalizados - 1; i++) {
            for (int j = i + 1; j < Atleta.atletasFinalizados; j++) {
                if (Atleta.ranking[j].getPontos() > Atleta.ranking[i].getPontos()) {
                    Atleta temp = Atleta.ranking[i];
                    Atleta.ranking[i] = Atleta.ranking[j];
                    Atleta.ranking[j] = temp;
                }
            }
        }
        
        System.out.println("\nRanking Final:");
        for (int i = 0; i < Atleta.atletasFinalizados; i++) {
            System.out.println((i + 1) + "º lugar: Atleta " + Atleta.ranking[i].getId() + " - " + Atleta.ranking[i].getPontos() + " pontos");
        }
	}

}
