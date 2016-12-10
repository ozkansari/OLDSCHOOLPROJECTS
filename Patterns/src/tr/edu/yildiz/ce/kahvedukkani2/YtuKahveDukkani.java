package tr.edu.yildiz.ce.kahvedukkani2;

public class YtuKahveDukkani {
 
	public static void main(String args[]) {
		Icecek icecek1 = new Espresso();
		System.out.println(icecek1.getAciklama() 
				+ " $" + icecek1.fiyatlandir());
 
		Icecek icecek2 = new Neskafe();
		icecek2 = new Mocha(icecek2);
		icecek2 = new Mocha(icecek2);
		icecek2 = new Karamel(icecek2);
		System.out.println(icecek2.getAciklama() 
				+ " $" + icecek2.fiyatlandir());
 
		Icecek icecek3 = new FiltreKahve();
		icecek3 = new Krema(icecek3);
		icecek3 = new Mocha(icecek3);
		icecek3 = new Karamel(icecek3);
		System.out.println(icecek3.getAciklama() 
				+ " $" + icecek3.fiyatlandir());
	}
}
