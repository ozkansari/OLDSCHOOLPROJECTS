package tr.edu.yildiz.ce.kahvedukkani;

public class BuzluCay extends Icecek {
  
	public BuzluCay() {
		aciklama = "Buzlu Cay";
	}
  
	public double fiyatlandir() {
		return 2.99 + super.fiyatlandir();
	}
}

