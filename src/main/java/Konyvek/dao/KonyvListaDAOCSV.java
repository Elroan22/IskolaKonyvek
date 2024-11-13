package Konyvek.dao;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.io.*; 

import Konyvek.KonyvPeldany;
import Konyvek.Konyv;
import Konyvek.Diak;

public class KonyvListaDAOCSV implements KonyvListaDAO{
	
	private List<KonyvPeldany> konyvek;
	private String filename;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy. MM. dd");
	
	public KonyvListaDAOCSV(String filename)
	{
		this.filename = filename;
		konyvek = new ArrayList<>();
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			String sor;
			while((sor = buffer.readLine()) != null)
			{
				String[] adatok = sor.split(";");
				Konyv konyv = new Konyv(adatok[0], adatok[1], Integer.parseInt(adatok[2]));
				Diak diak = new Diak(adatok[4], LocalDate.parse(adatok[5], dtf), Integer.parseInt(adatok[6]), adatok[7], adatok[8]);
				KonyvPeldany peldany = new KonyvPeldany(konyv, adatok[3], diak);
				
				konyvek.add(peldany);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public KonyvPeldany findById(String id)
	{
		for(KonyvPeldany peldany: konyvek)
		{
			if(peldany.getId().equals(id))
			{
				return peldany;
			}
		}
		return null;
	}
	
	public void deletebyID(String id) {
		KonyvPeldany torlendo = null;
		for(KonyvPeldany peldany: konyvek)
		{
			if(peldany.getId().equals(id))
			{
				torlendo = peldany;
				break;
			}
		}
		if(torlendo != null)
		{
			konyvek.remove(torlendo);
		}
		writeFile();
	}
	
	private void writeFile()
	{
		try (PrintWriter pw = new PrintWriter(new File(filename))){
			for(int i = 0; i < konyvek.size(); i++)
			{
				Konyv ktmp = konyvek.get(i).getKonyv();
				String konyv = ktmp.getCim() + ";" + ktmp.getSzerzo() + ";" + ktmp.getKiadasEv();
				String id = konyvek.get(i).getId();
				Diak ttmp = konyvek.get(i).getTulajdonos();
				String tulaj = ttmp.getNev() + ";" + dtf.format(ttmp.getSzulev()) + ";" + ttmp.getEvfolyam() + ";" + ttmp.getAnyaNeve() + ";" + ttmp.getLakohely();
				String adat = konyv + ";" + id + ";" + tulaj;
				pw.println(adat);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void save(KonyvPeldany peldany)
	{
		writeFile();
	}
	
	public List<KonyvPeldany> findAll()
	{
		return konyvek;
	}
}
