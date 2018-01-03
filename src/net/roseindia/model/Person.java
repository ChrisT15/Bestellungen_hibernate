package net.roseindia.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

//Tabelle personen_db wird ausgewaehlt
@Entity
@Table(name = "personen_db")
@FilterDefs 
({
@FilterDef(name = "VornameFilter", parameters = @ParamDef(name = "vorname", type = "string")),
@FilterDef(name = "NachnameFilter", parameters = @ParamDef(name = "nachname", type = "string")),
@FilterDef(name = "NameFilter", parameters ={ 
		@ParamDef(name="vorname",type="string"),
		@ParamDef(name="nachname",type="string") })

})
@Filters
({
@Filter(name = "VornameFilter", condition = "vorname =:vorname"),
@Filter(name = "NachnameFilter", condition = "nachname =:nachname"),
@Filter(name = "NameFilter", condition = "vorname =:vorname and nachname =:nachname")
})

public class Person implements Serializable{

	/* Die Klasse Person hat die Attribute vorname, nachname, strasse, wohnort,
	 * und kundennummer, welche in den gleichnamigen Spalten der Tabelle personen_db
	 * gespeichert werden
	 */
	//nr wird automatisch erzeugt
	@Id
	@GeneratedValue
	@Column(name="nr")
	private int nr;	

	@Column(name="vorname")
	private String vorname;

	@Column(name="nachname")
	private String nachname;	  

	@Column(name="strasse")
	private String strasse;
	
	@Column(name="wohnort")
	private String wohnort;
	
	@Column(name="kundennummer")
	private String kundennummer;

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}
	
	public String getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(String kundennummer) {
		this.kundennummer = kundennummer;
	}
	
	public String toString()
	{
		String ausgabe="";
		if(!this.vorname.equals(""))
			ausgabe += this.vorname;
		if(!this.nachname.equals(""))
			ausgabe = ausgabe + " " + this.nachname;
		if(!this.strasse.equals(""))
			ausgabe = ausgabe + " Strasse: " + this.strasse;
		if(!this.wohnort.equals(""))
			ausgabe = ausgabe + " Wohnort: " + this.wohnort;
		if(!this.kundennummer.equals(""))
			ausgabe = ausgabe + " Kundennummer: " + this.kundennummer;
		
		return ausgabe;
	}

}