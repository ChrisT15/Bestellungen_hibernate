package net.roseindia.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;


//Tabelle artikel_db wird ausgewaehlt
@Entity
@Table(name = "artikel_db")
@FilterDef(name = "NameFilter", parameters = @ParamDef(name = "artikelname", type = "string"))
@Filter(name = "NameFilter", condition = "name =:artikelname")
public class Artikel implements Serializable{
	
	/* Die Klasse Artikel enthaelt drei Attribute artikelname, artikelnummer, preis,
	 * welche in den gleichnamigen Spalten der Tabelle artikel_db gespeichert werden*/
	@Id
	@GeneratedValue
	@Column(name="nr")
	private int nr;	

	@Column(name="name")
	private String artikelname;

	@Column(name="nummer")
	private String artikelnummer;	  

	@Column(name="preis")
	private String preis;
	

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getArtikelName() {
		return artikelname;
	}

	public void setArtikelName(String artikelname) {
		this.artikelname = artikelname;
	}

	public String getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(String artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}
	
	public String toString()
	{
		String ausgabe="";
		if(!this.artikelname.equals(""))
		{
			ausgabe = ausgabe + "Artikelname: " + this.artikelname;
		}
		if(!this.artikelname.equals(""))
		{
			ausgabe = ausgabe + " Artikelnummer: " + this.artikelnummer;
		}
		if(!this.artikelname.equals(""))
		{
			ausgabe = ausgabe + " Preis: " + this.preis;
		}
		
		return ausgabe;
	}

}