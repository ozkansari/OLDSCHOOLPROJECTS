package tr.edu.yildiz.ce.kahvedukkani2;

public class Sut extends EkstraTatDecorator {

	public Sut(Icecek icecek) {
		super(icecek);
	}

	public String getAciklama() {
		return icecek.getAciklama() + ", Sut";
	}

	public double fiyatlandir() {
		return .10 + icecek.fiyatlandir();
	}
}
