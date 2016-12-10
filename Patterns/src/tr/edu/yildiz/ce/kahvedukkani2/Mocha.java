package tr.edu.yildiz.ce.kahvedukkani2;

public class Mocha extends EkstraTatDecorator {

	public Mocha(Icecek icecek) {
		super(icecek);
	}

	public String getAciklama() {
		return icecek.getAciklama() + ", Mocha";
	}
 
	public double fiyatlandir() {
		return .20 + icecek.fiyatlandir();
	}
}
