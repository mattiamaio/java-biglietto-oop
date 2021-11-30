package it.biglietti;

import java.math.BigDecimal;

// ROOM 4: Jerome Branchetti, Mattia Loiacono, Mattia Maio, Rosario Mazzocca, Gianluca Scarnicci.

public class Biglietto {
	// costanti
	private final BigDecimal COSTO_PER_KM = new BigDecimal("0.21");
	private final BigDecimal SCONTO_OVER_65 = new BigDecimal("0.4");
	private final BigDecimal SCONTO_UNDER_18 = new BigDecimal("0.2");

	// attributi
	private int km;
	private int eta;

	// costruttore
	public Biglietto(int km, int eta) {
		this.km = km;
		this.eta = eta;
	}

	// getter and setter
	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	// metodi
	public BigDecimal CalcolaSconto() throws Exception {
		if (isValidEta()) {

			if (eta >= 65) {
				return SCONTO_OVER_65;
			} else if (eta < 18) {
				return SCONTO_UNDER_18;
			} else {
				return new BigDecimal("0");
			}
		} else {
			throw new Exception("Età deve essere compresa tra 0 e 120");
		}
	}

	public BigDecimal CalcolaPrezzo() throws Exception {
		if (isValidKm()) {
			BigDecimal kmBd = BigDecimal.valueOf(km); // traduce int in bigdecimal
			BigDecimal sconto = COSTO_PER_KM.multiply(CalcolaSconto().multiply(kmBd));
			return COSTO_PER_KM.multiply(kmBd).subtract(sconto);
		} else {
			throw new Exception("Km devono essere maggiori di 0");
		}

	}

	private boolean isValidKm() {
		if (km <= 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isValidEta() {
		if (eta < 0 || eta > 120) {
			return false;
		} else {
			return true;
		}

	}

}
