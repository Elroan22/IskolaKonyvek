package Konyvek;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Diak {

	private String nev;
	private LocalDate szulev;
	private int evfolyam;
	private String anyaNeve;
	private String lakohely;
	
	private List<KonyvPeldany> konyvek = new ArrayList();
	
	public Diak()
	{
		
	}
	
	public Diak(String nev, LocalDate szulev, int evfolyam, String anyaNeve, String lakohely)
	{
		this.nev = nev;
		this.szulev = szulev;
		this.evfolyam = evfolyam;
		this.anyaNeve = anyaNeve;
		this.lakohely = lakohely;
	}
	
	public String getNev()
	{
		return nev;
	}
	
	public LocalDate getSzulev()
	{
		return szulev;
	}
	
	public int getEvfolyam()
	{
		return evfolyam;
	}
	
	public String getAnyaNeve()
	{
		return anyaNeve;
	}
	
	public String getLakohely()
	{
		return lakohely;
	}
	
	public void setNev(String nev)
	{
		this.nev = nev;
	}
	
	public void setSzulev(LocalDate szulev)
	{
		this.szulev = szulev;
	}
	
	public void setEvfolyam(int evfolyam)
	{
		this.evfolyam = evfolyam;
	}
	
	public void setAnyaNeve(String anyaNeve)
	{
		this.anyaNeve = anyaNeve;
	}
	
	public void setLakohely(String lakohely)
	{
		this.lakohely = lakohely;
	}
	
	public List<KonyvPeldany> getKonyvek()
	{
		return konyvek;
	}
	
	public void addKonyv(KonyvPeldany peldany)
	{
		konyvek.add(peldany);
	}
	
	@Override
	public String toString()
	{
		return nev + " " + szulev + " " + evfolyam + " " + anyaNeve + " " + lakohely;
	}
	
}
