package Konyvek;

public class KonyvPeldany {
	
	private Konyv konyv;
	private String id;
	private Diak tulajdonos;
	
	public KonyvPeldany()
	{
		
	}
	
	public KonyvPeldany(Konyv konyv, String id, Diak tulajdonos)
	{
		this.konyv = konyv;
		this.id = id;
		this.tulajdonos = tulajdonos;
	}
	
	public Konyv getKonyv()
	{
		return konyv;
	}
	
	public String getId()
	{
		return id;
	}
	
	public Diak getTulajdonos()
	{
		return tulajdonos;
	}
	
	@Override
	public String toString()
	{
		return konyv.getSzerzo() + " " + konyv.getCim() + " " + id + " " + tulajdonos.getNev();
	}
	
}
