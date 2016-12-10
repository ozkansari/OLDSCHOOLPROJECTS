package tr.edu.yildiz.ce.kahvedukkani;

public class YtuKahveDukkani {
 
	public static void main(String args[]) {
		Icecek icecek1 = new Espresso();
		System.out.println(icecek1.getAciklama() 
				+ " $" + icecek1.fiyatlandir());
 
		Icecek icecek2 = new Neskafe();
		icecek2.setMochali(true);
		icecek2.setKaramelli(true);
		System.out.println(icecek2.getAciklama() 
				+ " $" + icecek2.fiyatlandir());
 
		Icecek icecek3 = new FiltreKahve();
		icecek3.setMochali(true);
		icecek3.setKaramelli(true);
		icecek3.setSutlu(true);
		System.out.println(icecek3.getAciklama() 
				+ " $" + icecek3.fiyatlandir());
	}
}
