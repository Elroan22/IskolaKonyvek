package Konyvek;

public class Konyv {
		private String cim;
		private String szerzo;
		private int kiadasev;
		
		public Konyv()
		{
			
		}
		
		public Konyv(String cim, String szerzo, int kiadasev)
		{
			this.cim = cim;
			this.szerzo = szerzo;
			this.kiadasev = kiadasev;
		}
		
		public String getCim()
		{
			return cim;
		}
		public String getSzerzo()
		{
			return szerzo;
		}
		public int getKiadasEv()
		{
			return kiadasev;
		}
		
		public void setKiadasEv(int kiadasev)
		{
			this.kiadasev = kiadasev;
		}
		
		public void setCim(String cim)
		{
			this.cim = cim;
		}
		
		public void setSzerzo(String szerzo)
		{
			this.szerzo = szerzo;
		}
		
		@Override
		public String toString()
		{
			return cim + " " + szerzo + " " + kiadasev;
		}
}
