package it.biglietti;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

// ROOM 4: Jerome Branchetti, Mattia Loiacono, Mattia Maio, Rosario Mazzocca, Gianluca Scarnicci.

public class Biglietto {
	// costanti
	private final BigDecimal COSTO_PER_KM = new BigDecimal("0.21");
	private final BigDecimal SCONTO_OVER_65 = new BigDecimal("0.4");
	private final BigDecimal SCONTO_UNDER_18 = new BigDecimal("0.2");
	private final int DURATA_NORMALE = 30;
	private final int DURATA_FLESSIBILE = 90;
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// attributi
	private int km;
	private int eta;
	private LocalDate data;
	private boolean flessibile;
	private String dataFormattata;

	// costruttore
	public Biglietto(int km, int eta, boolean flessibile) {
		this.km = km;
		this.eta = eta;
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		data = LocalDate.now();
		dataFormattata = data.format(FORMATTER);
		this.flessibile = flessibile;
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

	public boolean isFlessibile() {
		return flessibile;
	}

	public void setFlessibile(boolean flessibile) {
		this.flessibile = flessibile;
	}
	
	public String getDataFormattata() {
		return dataFormattata;
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
			BigDecimal flex = BigDecimal.valueOf(1.1);
			BigDecimal sconto = COSTO_PER_KM.multiply(CalcolaSconto().multiply(kmBd));

			if (flessibile) {
				return (COSTO_PER_KM.multiply(kmBd).subtract(sconto)).multiply(flex);
			} else {
				return COSTO_PER_KM.multiply(kmBd).subtract(sconto);
			}
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

	public String calcolaDataScadenza() {
		LocalDate dataScadenza = null;
		if (flessibile) {
			dataScadenza = data.plusDays(DURATA_FLESSIBILE);
		} else if(!flessibile){
			dataScadenza = data.plusDays(DURATA_NORMALE);
		}
		return dataScadenza.format(FORMATTER);
	}
	
	public boolean isValidFlessibile() throws InputMismatchException{ //inserita exception precisa perché errore dato dal compilatore 
		if(flessibile) {
			return true;
		} else if(!flessibile) {
			return false;
		} else {
			throw new InputMismatchException("Errore: scrivere true per flessibile o false per normale");
		}
	}

}
