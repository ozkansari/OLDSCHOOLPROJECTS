package tr.edu.yildiz.ce.kahvedukkani;

public class Neskafe extends Icecek {
	public Neskafe() {
		aciklama = "Neskafe";
	}
 
	public double fiyatlandir() {
		return .99 + super.fiyatlandir();
	}
}

