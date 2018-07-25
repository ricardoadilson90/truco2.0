package truco;

public class Jogada {
	private Carta carta;
	private Jogador dono;
	private boolean manilha=false;
	
	public Jogada () {
		carta = new Carta(0, 0);
	}
	Jogada (Carta carta, Jogador dono){
		this.carta = carta;
		this.dono = dono;
	}
	Jogada (Carta carta, Jogador dono, boolean isManilha){
		this.carta = carta;
		this.dono = dono;
		manilha = isManilha;
	}
	
	
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public Jogador getDono() {
		return dono;
	}
	public void setDono(Jogador dono) {
		this.dono = dono;
	}

	public boolean isManilha() {
		return manilha;
	}

	public void setManilha(boolean manilha) {
		this.manilha = manilha;
	}
	
}
