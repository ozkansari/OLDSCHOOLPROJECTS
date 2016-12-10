package tr.edu.yildiz.ce.kahvedukkani;

public abstract class Icecek {
	
	protected String aciklama = "Icecek";
	
	private boolean sutlu;
	
	private boolean karamelli;
	
	private boolean kremali;
	
	private boolean mochali;
  
	public String getAciklama() {
		return aciklama;
	}
 
	public double fiyatlandir(){
		int ekFiyat = 0;
		if(sutlu){
			ekFiyat += 0.10;
		}
		if(karamelli){
			ekFiyat += 0.10;
		}
		if(kremali){
			ekFiyat += 0.15;
		}
		if(mochali){
			ekFiyat += 0.20;
		}
		return ekFiyat;
	}

	public boolean isSutlu() {
		return sutlu;
	}

	public void setSutlu(boolean sutlu) {
		this.sutlu = sutlu;
	}

	public boolean isKaramelli() {
		return karamelli;
	}

	public void setKaramelli(boolean karamelli) {
		this.karamelli = karamelli;
	}

	public boolean isKremali() {
		return kremali;
	}

	public void setKremali(boolean kremali) {
		this.kremali = kremali;
	}

	public boolean isMochali() {
		return mochali;
	}

	public void setMochali(boolean mochali) {
		this.mochali = mochali;
	}
}
