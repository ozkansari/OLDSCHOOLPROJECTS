package tr.edu.yildiz.ce.kahvedukkani;

public class KafeinsizKahve extends Icecek {
	public KafeinsizKahve() {
		aciklama = "Kafeinsiz Kahve";
	}
 
	public double fiyatlandir() {
		return 1.05 + super.fiyatlandir();
	}
}

