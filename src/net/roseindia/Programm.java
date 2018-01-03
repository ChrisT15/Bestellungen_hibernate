
package net.roseindia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Filter;
import net.roseindia.model.*;



public class Programm {
	
	public static void person_speichern(Session session, org.hibernate.Transaction tr) throws IOException
	{
		
		
		String daten;
		Person p = new Person();
			
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
		
		System.out.println("Vorname: ");
		daten = eingabe.readLine();
			
		p.setVorname(daten);
			
		System.out.println("Nachname: ");
		daten = eingabe.readLine();
		p.setNachname(daten);
			
		System.out.println("Strasse: ");
		daten = eingabe.readLine();
		p.setStrasse(daten);
			
			
		System.out.println("Wohnort: ");
		daten = eingabe.readLine();
		p.setWohnort(daten);
			
		System.out.println("Kundennummer: ");
		daten = eingabe.readLine();
		p.setKundennummer(daten);
			
		session.save(p);
		//tr.commit();
		System.out.println("Successfully inserted");
			
	
		
	}
	
	public static void artikel_speichern(Session session, org.hibernate.Transaction tr) throws IOException
	{
		
		
		String daten;
		Artikel a = new Artikel();
			
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
		
		System.out.println("Artikelname: ");
		daten = eingabe.readLine();
			
		a.setArtikelName(daten);
			
		System.out.println("Artikelnummer: ");
		daten = eingabe.readLine();
		a.setArtikelnummer(daten);
			
		System.out.println("Preis: ");
		daten = eingabe.readLine();
		a.setPreis(daten);
			
			
		session.save(a);
		System.out.println("Successfully inserted");
			
		
	}
	
	public static void bestellung_speichern(Session session, org.hibernate.Transaction tr) throws IOException
	{
		
		String daten;
		Bestellung b = new Bestellung();
			
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
		
		System.out.println("Kundennummer: ");
		daten = eingabe.readLine();
			
		b.setKundennummer(daten);
			
		System.out.println("Artikelnummer: ");
		daten = eingabe.readLine();
		b.setArtikelnummer(daten);
			
		System.out.println("Anzahl: ");
		daten = eingabe.readLine();
		b.setAnzahl(daten);
			
			
		session.save(b);
		System.out.println("Successfully inserted");
			
		
		
	}
	
	public static void person_suchen(Session session) throws IOException
	{
		String vorname,nachname;
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
			
		System.out.println("Vorname: ");
		vorname = eingabe.readLine();
			
		System.out.println("Nachname: ");
		nachname = eingabe.readLine();
			
		if(!vorname.equals("") && nachname.equals(""))
		{
			Filter filter = session.enableFilter("VornameFilter");
			filter.setParameter("vorname", vorname);
			Query query = session.createQuery("from Person e");
			List<Person> list = query.list();
			Iterator<Person> it =list.iterator();
			while (it.hasNext()) 
			{
				Person p = (Person) it.next();
				System.out.println(p.toString());
			}    
			session.disableFilter("VornameFilter");
		}
		if(vorname.equals("") && !nachname.equals(""))
		{
			Filter filter = session.enableFilter("NachnameFilter");
			filter.setParameter("nachname", nachname);
			Query query = session.createQuery("from Person e");
			List<Person> list = query.list();
			Iterator<Person> it =list.iterator();
			while (it.hasNext()) 
			{
				Person p = (Person) it.next();
				System.out.println(p.toString());
			}    
			session.disableFilter("NachnameFilter");
		}
		if(!vorname.equals("") && !nachname.equals(""))
		{
			Filter filter = session.enableFilter("NameFilter");
			filter.setParameter("nachname", nachname);
			filter.setParameter("vorname", vorname);
			Query query = session.createQuery("from Person e");
			List<Person> list = query.list();
			Iterator<Person> it =list.iterator();
			while (it.hasNext()) 
			{
				Person p = (Person) it.next();
				System.out.println(p.toString());
			}    
			session.disableFilter("NameFilter");
		}
		if(vorname.equals("") && nachname.equals(""))
		{
			Query query = session.createQuery("from Person e");
			List<Person> list = query.list();
			Iterator<Person> it =list.iterator();
			while (it.hasNext()) 
			{
				Person p = (Person) it.next();
				System.out.println(p.toString());
			}    
		}
	}
	
	public static void artikel_suchen(Session session) throws IOException
	{
		String aname;
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
			
		System.out.println("Artikelname: ");
		aname = eingabe.readLine();
			
			
		if(!aname.equals(""))
		{
			Filter filter = session.enableFilter("NameFilter");
			filter.setParameter("artikelname", aname);
			Query query = session.createQuery("from Artikel e");
			List<Artikel> list = query.list();
			Iterator<Artikel> it =list.iterator();
			while (it.hasNext()) 
			{
				Artikel a = (Artikel) it.next();
				System.out.println(a.toString());
			}    
			session.disableFilter("NameFilter");
		}
		if(aname.equals(""))
		{
			Query query = session.createQuery("from Artikel e");
			List<Artikel> list = query.list();
			Iterator<Artikel> it =list.iterator();
			while (it.hasNext()) 
			{
				Artikel a = (Artikel) it.next();
				System.out.println(a.toString());
			}    
		}
		
		
			
	}
	
	public static void bestellung_person(Session session) throws IOException
	{
		String k_nr;
		float sum=0;
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
			
		System.out.println("Kundennummer: ");
		k_nr = eingabe.readLine();
		String query = "select a.artikelname, a.preis, b.anzahl, " 
				+ " round(a.preis*b.anzahl,2) as gesamtpreis "
				+ "from Artikel a, Bestellung b " 
				+ " where b.artikelnummer = a.artikelnummer and b.kundennummer =" + k_nr;
		
		List<Object[]> bestellungen_person = session.createQuery(query).list();
		
		for(Object[] string_l : bestellungen_person)
		{
			sum += Float.parseFloat((String)string_l[3]);
			System.out.println("Artikelname: " + string_l[0] + " Preis: " + string_l[1] +
					" Anzahl: " + string_l[2] + " Gesamtpreis: " + string_l[3] );
			
		}
		System.out.println("Summe: " + sum);
	}
	
	public static void bestellung_artikel(Session session) throws IOException
	{
		String a_nr;
		float sum = 0;
		BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
			
		System.out.println("Artikelnummer: ");
		a_nr = eingabe.readLine();
		String query = "select p.nachname, p.vorname, b.anzahl "
				+ "from Person p, Bestellung b "
			    + "where p.kundennummer = b.kundennummer and b.artikelnummer = " + a_nr;
			   
		
		List<Object[]> bestellungen_artikel = session.createQuery(query).list();
		
		query = "select a.preis from Artikel a where a.artikelnummer = " + a_nr;
		List<String> preis = session.createQuery(query).list();
		
		
		for(Object[] string_l : bestellungen_artikel)
		{
			float gesamtpreis = Float.parseFloat(preis.get(0))*Float.parseFloat((String)string_l[2]);
			sum += gesamtpreis;
			System.out.println(string_l[0] + " " +string_l[1] +
					" Anzahl: " + string_l[2] + " Gesamtpreis" + gesamtpreis );
			
		}
		System.out.println("Summe: " + sum);
	}
	
	
	
	public static void main(String[] args) throws Exception {

		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();
		
		String befehl;
		while(true)
		{
			System.out.println("Befehle: ");
			System.out.println("exit: Programm beenden");
			System.out.println("sp: Person speichern");
			System.out.println("sa: Artikel speichern");
			System.out.println("sb: Bestellung speichern");
			System.out.println("sup: Person suchen");
			System.out.println("sua: Artikel suchen");
			System.out.println("bp: Bestellungen einer Person anzeigen");
			System.out.println("ba: Bestellung eines Artikels anzeigen");
			System.out.println("Befehl eingeben: ");
			BufferedReader eingabe = new BufferedReader( new InputStreamReader(System.in));
			befehl= eingabe.readLine();
			if(befehl.equals("exit"))
			{
				break;
			}
			if(befehl.equals("sp"))
			{
				try
				{
					person_speichern(session,tr);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion person_speichern");
				}
			}
			if(befehl.equals("sa"))
			{
				try
				{
					artikel_speichern(session,tr);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion artikel_speichern");
				}
			}
			
			if(befehl.equals("sb"))
			{
				try
				{
					bestellung_speichern(session, tr);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion bestellung_speichern");
				}
			}
			
			if(befehl.equals("sup"))
			{
				try
				{
					person_suchen(session);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion person_suchen");
				}

			}
			if(befehl.equals("sua"))
			{
				try
				{
					artikel_suchen(session);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion artikel_suchen");
				}
			}
			if(befehl.equals("bp"))
			{
				try
				{
					bestellung_person(session);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion bestellung_person");
				}
			}
			if(befehl.equals("ba"))
			{
				try
				{
					bestellung_artikel(session);
				}
				catch(IOException e)
				{
					System.out.println("Fehler in der Funktion bestellung_person");
				}
			}
			
					
				
			
		}
		tr.commit();
		sessFact.close();
	}

}
