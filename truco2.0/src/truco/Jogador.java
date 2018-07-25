package truco;

import java.util.ArrayList;

public class Jogador {
	private ArrayList<Carta> mao = new ArrayList<Carta>();
	private int pontucaoGeral;
	private int pontuacaoRodada;
	ArrayList<Carta> manilhas;

	public void jogarCarta(Carta carta, Partida partida) {
		Jogada jogada = new Jogada(carta, this, Partida.isManilha(carta));
		if (mao.contains(carta)) {
			mao.remove(carta);
		}
		partida.registrarJogada(jogada);
	}

	public void ordenarMao() {

		manilhas = new ArrayList<Carta>();
		encontrarManilhas();

		if (tenhoManilha()) {
			ArrayList<Carta> aux = new ArrayList<Carta>();
			for (int i = 0; i < manilhas.size(); i++) {
				aux.add(manilhas.get(i));
			}
			for (int i = 0; i < getMao().size(); i++) {
				if (aux.contains(getMao().get(i))) {

				} else {
					aux.add(getMao().get(i));
				}
			}
			setMao(aux);
		} else {
			ordenarCartas();
		}
	}

	public boolean tenhoManilha() {
		return !manilhas.isEmpty();
	}

	private void ordenarCartas() {
		Carta aux;
		for (int i = 0; i < mao.size(); i++) {
			// bubble sort que ordena um arraylist de cartas do maior para o menor.
			for (int j = 0; j < mao.size(); j++) {

				if (mao.get(i).getValor() > mao.get(j).getValor()) {
					aux = mao.get(i);
					mao.set(i, mao.get(j));
					mao.set(j, aux);
				}
			}
		}

	}

	public static void ordenarManilhas(ArrayList<Carta> manilhas) {
		Carta aux;
		for (int i = 0; i < manilhas.size(); i++) {
			// bubble sort que ordena um arraylist de cartas do maior para o menor.
			for (int j = 0; j < manilhas.size(); j++) {

				if (manilhas.get(i).getNaipe() > manilhas.get(j).getNaipe()) {
					aux = manilhas.get(i);
					manilhas.set(i, manilhas.get(j));
					manilhas.set(j, aux);
				}
			}
		}
	}

	private void encontrarManilhas() {
		for (int i = 0; i < getMao().size(); i++) {
			if (Partida.isManilha(getMao().get(i))) {
				manilhas.add(getMao().get(i));
			}
		}
		Jogador.ordenarManilhas(manilhas);
	}

	public ArrayList<Carta> getMao() {
		return mao;
	}

	public void setMao(ArrayList<Carta> mao) {
		this.mao = mao;
	}

	public int getPontuacaoRodada() {
		return pontuacaoRodada;
	}

	public void setPontuacaoRodada(int pontuacaoRodada) {
		this.pontuacaoRodada = pontuacaoRodada;
	}

	public int getPontucaoGeral() {
		return pontucaoGeral;
	}

	public void setPontucaoGeral(int pontucaoGeral) {
		this.pontucaoGeral = pontucaoGeral;
	}

	public ArrayList<Carta> getManilhas() {
		return manilhas;
	}

	public void setManilhas(ArrayList<Carta> manilhas) {
		this.manilhas = manilhas;
	}

}
