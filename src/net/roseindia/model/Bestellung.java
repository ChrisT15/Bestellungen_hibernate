package net.roseindia.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

//Tabelle bestellungen_db wird ausgewaehlt
@Entity
@Table(name = "bestellungen_db")
public class Bestellung implements Serializable{

	/* Die Klasse Bestellung enthaelt drei Attribute kundennummer, artikelnummer, anzahl,
	 * welche in den gleichnamigen Spalten der Tabelle bestellungen_db gespeichert werden*/
	@Id
	@GeneratedValue
	@Column(name="nr")
	private int nr;	

	@Column(name="kundennummer")
	private String kundennummer;

	@Column(name="artikelnummer")
	private String artikelnummer;	  

	@Column(name="anzahl")
	private String anzahl;
	

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(String kundennummer) {
		this.kundennummer = kundennummer;
	}

	public String getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(String artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public String getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(String anzahl) {
		this.anzahl = anzahl;
	}
	

}