package Konyvek;


import java.util.List;
import java.util.Scanner;

import Konyvek.dao.KonyvListaDAO;
import Konyvek.dao.KonyvListaDAOCSV;
import Konyvek.dao.KonyvListaDAOXL;

public class KonyvMenu {
	
	boolean vege = false;
	private KonyvListaDAO beolvaso;
	private Scanner sc = new Scanner(System.in);
	
	public KonyvMenu()
	{
		
	}
	
	public KonyvMenu(KonyvListaDAO dao)
	{
	     beolvaso = dao;
	}
	
	public void kiir()
	{
		System.out.println("Menu pontok");
		System.out.println("1. Lista");
		System.out.println("2. Keres");
		System.out.println("3. Példány módosítás");
		System.out.println("4. Új példány felvevése");
		System.out.println("4. Példány törlés");
		System.out.println("0. Kilepes");
	}
	
	public int menuBeolvas()
	{
		
		int valasz = sc.nextInt();
		while(valasz < 0 || valasz > 4)
		{
			System.out.println("Hibas menupont!");
			valasz = sc.nextInt();
		}
		sc.nextLine();
		return valasz;
	}
	
	public void Fut()
	{	
		while(!vege)
		{
			kiir();
			int valasz=menuBeolvas();
			try {
				switch(valasz) {
				case 1:
					lista();
					break;
				case 2:
					keres();
					break;
				case 3:
					Modositmenu mod = new Modositmenu(beolvaso);
					mod.Fut();
					break;
				case 4:
					hozaadd();
					break;
				case 5:
					torol();
					break;
				case 0: 
					kiLep();
				}
			}
			catch(IllegalArgumentException | ArithmeticException e)
			{
				System.out.println("Hibas adat: " + e.getMessage());
			}
		}
	}
	
	public void lista()
	{	
		List<KonyvPeldany> peldanyok = beolvaso.findAll();
		System.out.printf("| %-30s| %-30s| %-10s| %-30s| %-30s|\n","Szerző", "Cím","Id", "Név", "Anya neve");
		for(int i = 0; i < 141; i++)
		{
			System.out.print("-");
		}
		System.out.println("");
		for(int i = 0; i < peldanyok.size(); i++)
		{
			System.out.printf("| %-30s| %-30s| %-10s| %-30s| %-30s|\n",peldanyok.get(i).getKonyv().getSzerzo(), peldanyok.get(i).getKonyv().getCim(), 
					peldanyok.get(i).getId(), peldanyok.get(i).getTulajdonos().getNev(), peldanyok.get(i).getTulajdonos().getAnyaNeve());
		}
		System.out.println("");
	}
	
	public void keres()
	{
		System.out.print("Keresett ID: ");
		String id = sc.nextLine();
		KonyvPeldany peldany = beolvaso.findById(id);
		if(peldany != null)
		{
			System.out.printf("| %-30s| %-30s| %-10s| %-30s| %-30s|\n","Szerző", "Cím","Id", "Név", "Anya neve");
			for(int i = 0; i < 141; i++)
			{
				System.out.print("-");
			}
			System.out.println("");
			System.out.printf("| %-30s| %-30s| %-10s| %-30s| %-30s|\n",peldany.getKonyv().getSzerzo(), peldany.getKonyv().getCim(), 
					peldany.getId(), peldany.getTulajdonos().getNev(), peldany.getTulajdonos().getAnyaNeve());
		}
		else
		{
			System.out.println("Nem létezik ilyen könyv ezzel az ID-val");
		}
		System.out.println("");
	}
	
	public void torol()
	{
		System.out.print("A törlendő példány ID: ");
		String id = sc.nextLine();
		beolvaso.deletebyID(id);
	}
	
	public void hozaadd()
	{
		
	}
	
	public void kiLep()
	{
		vege = true;
	}
	
	
	
	
}
