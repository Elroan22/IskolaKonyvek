package Konyvek.dao;

import java.util.List;

import Konyvek.KonyvPeldany;

public interface KonyvListaDAO {

	public KonyvPeldany findById(String id);	
	
	public void deletebyID(String id);
	
	public List<KonyvPeldany> findAll();
	
	public void save(KonyvPeldany peldany);
}
