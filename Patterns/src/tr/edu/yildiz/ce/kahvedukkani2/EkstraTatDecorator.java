package tr.edu.yildiz.ce.kahvedukkani2;

public abstract class EkstraTatDecorator extends Icecek {
	
	protected Icecek icecek;

	public EkstraTatDecorator(Icecek icecek) {
		this.icecek = icecek;
	}
 
	public abstract String getAciklama();
}
