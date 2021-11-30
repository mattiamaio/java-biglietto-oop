package it.biglietti;

import java.util.Scanner;

public class Biglietteria {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Inserire distanza del viaggio in km: ");
		int km = scan.nextInt();
		System.out.print("\nInserire età passeggero: ");
		int eta = scan.nextInt();

		Biglietto a = new Biglietto(km, eta);

		try {
			System.out.println("\nPrezzo del biglietto è: " + a.CalcolaPrezzo());
		} catch (Exception e) {
			System.out.println("\nErrore: " + e.getMessage());
		}

		scan.close();
	}

}
