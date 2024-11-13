package Konyvek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Konyvek.dao.KonyvListaDAO;

public class Modositmenu {
	
	boolean vege = false;
	private KonyvListaDAO beolvaso;
	private Scanner sc = new Scanner(System.in);
	private KonyvPeldany konyv;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy. MM. dd");
	
	public Modositmenu()
	{
		
	}
	
	public Modositmenu(KonyvListaDAO dao)
	{
		beolvaso = dao;
	}
	
	public KonyvPeldany meir()
	{
		System.out.print("Melyik példányt szeretnéd módosítani? ");
		String id = sc.nextLine();
		return beolvaso.findById(id);
	}
	
	public void kiir()
	{
		System.out.println("Mit szeretne módosítani");
		System.out.println("1. Nev");
		System.out.println("2. SzulEv");
		System.out.println("3. Anya neve");
		System.out.println("4. Evfolyam");
		System.out.println("5. Lakohely");
		System.out.println("6. Cim");
		System.out.println("7. KiadasEv");
		System.out.println("8. Szerzo");
		System.out.println("0. Kilepes");
	}
	
	public int menuBeolvas()
	{
		
		int valasz = sc.nextInt();
		while(valasz < 0 || valasz > 8)
		{
			System.out.println("Hibas menupont!");
			valasz = sc.nextInt();
		}
		sc.nextLine();
		return valasz;
	}
	
	public void Fut()
	{	
		konyv = meir();
		if(konyv == null)
		{
			System.out.println("Hibas id");
			return;
		}
		kiir();
		int valasz=menuBeolvas();
		try {
			switch(valasz) {
			case 1:
				modNev();
				break;
			case 2:
				modSzulev();
				break;
			case 3:
				modAnyaNeve();
				break;
			case 4:
				modEvFolyam();
				break;
			case 5:
				modLakohely();
				break;
			case 6:
				modCim();
				break;
			case 7:
				modKiadev();
				break;
			case 8:
				modSzerzo();
				break;
			case 0: 
				return;
			}
		}
		catch(IllegalArgumentException | ArithmeticException e)
		{
			System.out.println("Hibas adat: " + e.getMessage());
		}
		beolvaso.save(konyv);
	}
	
	public void modNev()
	{
		System.out.print("Ad meg az új nevet: ");
		String nev = sc.nextLine();
		konyv.getTulajdonos().setNev(nev);
	}
	
	public void modSzulev()
	{
		boolean hiba = true;
		LocalDate szulev = null;
		while(hiba) {
			System.out.print("Ad meg az új szuletesi evet (yyyy. MM. dd): ");
			String tmp = sc.nextLine();		
			try
			{
				 szulev = LocalDate.parse(tmp, dtf);
				 hiba = false;
			}
			catch(DateTimeParseException  e)
			{
				System.out.println("Hibás a dátum formátum!");
			}
		}
		konyv.getTulajdonos().setSzulev(szulev);
	}
	
	public void modAnyaNeve()
	{
		System.out.print("Ad meg az új anya nevét: ");
		String anyaneve = sc.nextLine();
		konyv.getTulajdonos().setAnyaNeve(anyaneve);
	}
	
	public void modEvFolyam()
	{
		System.out.print("Ad meg az új évfolyamot: ");
		int evfolyam = sc.nextInt();
		konyv.getTulajdonos().setEvfolyam(evfolyam);
	}
	
	public void modLakohely()
	{
		System.out.print("Ad meg az új lakohelyet: ");
		String lakohely = sc.nextLine();
		konyv.getTulajdonos().setLakohely(lakohely);
	}
	
	public void modCim()
	{
		System.out.print("Ad meg az új könyv cimet: ");
		String cim = sc.nextLine();
		konyv.getKonyv().setCim(cim);
	}
	
	public void modKiadev()
	{
		System.out.print("Ad meg az új kiadasi evet: ");
		String ev = sc.nextLine();
		konyv.getKonyv().setKiadasEv(Integer.parseInt(ev));
	}
	
	public void modSzerzo()
	{
		System.out.print("Ad meg az új szerzo nevet: ");
		String szerzo = sc.nextLine();
		konyv.getKonyv().setSzerzo(szerzo);
	}
}