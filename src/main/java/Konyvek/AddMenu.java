package Konyvek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddMenu {
	
	private Scanner sc = new Scanner(System.in);
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy. MM. dd");
	
	public void fut()
	{
		System.out.println("Uj konvypeldany felvetele!");
		String ID = id();
		Diak ujdiak = diak();
		Konyv ujkonyv = konyv();
		KonyvPeldany ujpeldany = new KonyvPeldany(ujkonyv, ID, ujdiak);
	}
	
	public Diak diak()
	{
		System.out.println("A diak adatai!");
		System.out.print("A diak neve: ");
		String nev = sc.nextLine();
		boolean hiba = true;
		LocalDate szulev = null;
		while(hiba) {
			System.out.print("A szuletesi ev (yyyy. MM. dd): ");
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
		System.out.print("Evfolyam: ");
		int evfolyam = sc.nextInt();
		System.out.print("Anya neve: ");
		String anyanev = sc.nextLine();
		System.out.print("Lakohely: ");
		String lakohely = sc.nextLine();
		return new Diak(nev,szulev,evfolyam,anyanev,lakohely);
	}
	
	public String id()
	{
		System.out.print("A konyvpeldany id-ja: ");
		String id = sc.nextLine();
		return id;
	}
	
	public Konyv konyv()
	{
		System.out.println("A konyv adatai!");
		System.out.print("Cim: ");
		String cim = sc.nextLine();
		System.out.print("Szerzo: ");
		String szerzo = sc.nextLine();
		System.out.print("Kiadas ev: ");
		int kiadev = sc.nextInt();
		return new Konyv(cim,szerzo,kiadev);
	}

}
