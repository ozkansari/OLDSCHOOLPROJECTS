package tr.edu.yildiz.ce.kahvedukkani2;
 
public class Karamel extends EkstraTatDecorator {

	public Karamel(Icecek icecek) {
		super(icecek);
	}

	public String getAciklama() {
		return icecek.getAciklama() + ", Karamel";
	}
 
	public double fiyatlandir() {
		return .10 + icecek.fiyatlandir();
	}
}
