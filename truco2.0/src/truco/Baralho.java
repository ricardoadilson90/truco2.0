package truco;


import javax.swing.ImageIcon;

public class Baralho {
	private int topo = 40;
	private Carta[] monte = new Carta[topo];
	
	public Carta distribuirUmaCarta() {
		return monte[--topo];

	}

	public Baralho() {
		int contador = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (i > 6) {
					monte[contador] = criarCarta(i + 3, j);
				} else {
					monte[contador] = criarCarta(i + 1, j);
				}
				contador++;
			}
		}
		embaralhar();
	}

	private Carta criarCarta(int i, int naipe) {
		Carta carta = new Carta(i, naipe % 4);
		switch (i) {
		case 1:
			carta.setNome("Ás");
			break;
		case 12:
			carta.setNome("Rei");
			break;
		default:
			carta.setNome(i + "");
			break;
		}
		switch (naipe) {
		case 0:
			carta.setNome(carta.getNome() + " de moles");
			break;
		case 1:
			carta.setNome(carta.getNome() + " de espadas");
			break;
		case 2:
			carta.setNome(carta.getNome() + " de copas");
			break;
		default:
			carta.setNome(carta.getNome() + " de paus");
			break;
		}
		try {
			carta.setImage(new ImageIcon(this.getClass().getResource("/"+carta.getNome()+".jpg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (carta.getValor()<4) {
			carta.setValor(carta.getValor()+12);
		}
		return carta;

	}

	public void embaralhar() {
		topo = 40;
		int j = (int) (Math.random() * 40);
		Carta aux;
		for (int i = 0; i < monte.length; i++) {
			aux = monte[i];
			monte[i] = monte[j];
			monte[j] = aux;
			j = (int) (Math.random() * 40);
		}
	}

	

	public Carta[] getMonte() {
		return monte;
	}

	public void setMonte(Carta[] monte) {
		this.monte = monte;
	}

}
