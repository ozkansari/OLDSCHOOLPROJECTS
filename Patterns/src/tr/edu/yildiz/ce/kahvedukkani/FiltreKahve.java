package tr.edu.yildiz.ce.kahvedukkani;

public class FiltreKahve extends Icecek {
	public FiltreKahve() {
		aciklama = "Filtre Kahve";
	}
 
	public double fiyatlandir() {
		return .89 + super.fiyatlandir();
	}
}

