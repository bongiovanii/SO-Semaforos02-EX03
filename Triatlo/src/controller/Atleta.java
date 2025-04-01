package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Atleta extends Thread {

	private static final Random random = new Random();
    private static final Semaphore armas = new Semaphore(5);
    private static final Semaphore mutex = new Semaphore(1);
    private static int posicaoCorrida = 1;
    private static int pontosCorrida = 250;
    public static Atleta[] ranking = new Atleta[25];
    public static int atletasFinalizados = 0;
    
    private int id;
    private int pontos;
    
    public Atleta(int id) {
        this.id = id;
        this.pontos = 0;
    }
    
    private void correr() throws InterruptedException {
        int distancia = 0;
        while (distancia < 3000) {
            distancia += random.nextInt(6) + 20;
            Thread.sleep(30);
        }
        mutex.acquire();
        pontos += pontosCorrida;
        pontosCorrida -= 10;
        mutex.release();
        System.out.println("Atleta " + id + " terminou a corrida.");
    }
    
    private void atirar() throws InterruptedException {
        armas.acquire();
        System.out.println("Atleta " + id + " pegou uma arma para atirar.");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(random.nextInt(2501) + 500);
            int pontuacaoTiro = random.nextInt(11);
            pontos += pontuacaoTiro;
            System.out.println("Atleta " + id + " fez um tiro e marcou " + pontuacaoTiro + " pontos.");
        }
        armas.release();
        System.out.println("Atleta " + id + " terminou os tiros e liberou a arma.");
    }
    
    private void pedalar() throws InterruptedException {
        int distancia = 0;
        while (distancia < 5000) {
            distancia += random.nextInt(11) + 30;
            Thread.sleep(40);
        }
        mutex.acquire();
        ranking[atletasFinalizados] = this;
        atletasFinalizados++;
        mutex.release();
        System.out.println("Atleta " + id + " terminou a prova.");
    }
    
    public void run() {
        try {
            correr();
            atirar();
            pedalar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getPontos() {
        return pontos;
    }
    
    public long getId() {
        return id;
    }

}
