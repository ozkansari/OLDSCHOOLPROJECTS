package tr.edu.yildiz.ce.kahvedukkani;

public class Espresso extends Icecek {
  
	public Espresso() {
		aciklama = "Espresso";
	}
  
	public double fiyatlandir() {
		return 1.99 + super.fiyatlandir();
	}
}

