package tr.edu.yildiz.ce.kahvedukkani2;

public class Krema extends EkstraTatDecorator {
	
	public Krema(Icecek icecek) {
		super(icecek);
	}

	public String getAciklama() {
		return icecek.getAciklama() + ", Krema";
	}

	public double fiyatlandir() {
		return .15 + icecek.fiyatlandir();
	}
}
