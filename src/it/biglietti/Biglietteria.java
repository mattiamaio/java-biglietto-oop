package it.biglietti;

import java.util.Scanner;

public class Biglietteria {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean flessibile;

		System.out.print("Inserire distanza del viaggio in km: ");
		int km = scan.nextInt();
		System.out.print("\nInserire età passeggero: ");
		int eta = scan.nextInt();

		do {
			System.out.print("\nScegliere se biglietto flessibile (durata 90 giorni)");
			System.out.print(" o normale (30 giorni) \n(scrivere true per " + "flessibile e false per normale): ");
			flessibile = scan.nextBoolean();
		} while (flessibile != true && flessibile != false);

		Biglietto a = new Biglietto(km, eta, flessibile);

		try {
			System.out.println("\nPrezzo del biglietto è di " + a.CalcolaPrezzo() + "\u20ac" + " e scade in data "
					+ a.calcolaDataScadenza());
		} catch (Exception e) {
			System.out.println("\nErrore: " + e.getMessage());
		}

		scan.close();
	}

}
