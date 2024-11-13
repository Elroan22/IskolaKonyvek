package Konyvek;

import java.util.List;
import java.util.Scanner;

import Konyvek.dao.KonyvListaDAO;
import Konyvek.dao.KonyvListaDAOCSV;
import Konyvek.dao.KonyvListaDAOXL;

public class Fomenu {
	
	private boolean vege = false;
	private Scanner sc = new Scanner(System.in);
	private KonyvMenu menu = null;
	
	public Fomenu()
	{
		
	}
	
	public void forrasBeker()
	{
		System.out.println("Milyen típusu adatforrást szeretne használni?");
		System.out.println("1. CSV");
		System.out.println("2. XLSX");
		System.out.println("3. Kilepes");
		int valasz=menuBeolvas(sc);
		KonyvListaDAO dao = null;
		try {
			switch(valasz) {
			case 1:
				dao = csvBeker();
				break;
			case 2:
				dao = xlsxBeker();
				break;
			case 3: 
				return;
			}
		}
		catch(IllegalArgumentException | ArithmeticException e)
		{
			System.out.println("Hibas adat: " + e.getMessage());
		}
		menu = new KonyvMenu(dao);
		
	}
	
	public int menuBeolvas(Scanner sc)
	{
		
		int valasz = sc.nextInt();
		while(valasz < 1 || valasz > 3)
		{
			System.out.println("Hibas menupont!");
			valasz = sc.nextInt();
		}
		sc.nextLine();
		return valasz;
	}
	
	public void Fut()
	{	
		forrasBeker();
		if(menu != null)
			menu.Fut();
	}
	
	public KonyvListaDAO csvBeker()
	{	
		System.out.print("Adja meg a csv fájl nevét: ");
		String fajlnev = sc.nextLine();
		KonyvListaDAOCSV csvbeolvas = new KonyvListaDAOCSV(fajlnev);
		return csvbeolvas;
	}
	
	public KonyvListaDAO xlsxBeker()
	{
		System.out.print("Adja meg a xlsx fájl nevét: ");
		String fajlnev = sc.nextLine();
		KonyvListaDAOXL csvbeolvas = new KonyvListaDAOXL(fajlnev);
		return csvbeolvas;
	}
	
	public void kiLep()
	{
		vege = true;
	}

}
