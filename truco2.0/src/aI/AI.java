package aI;

import truco.Carta;
import truco.Jogador;
import truco.Partida;

public class AI extends Jogador {

	@Override
	public void jogarCarta(Carta carta, Partida partida) {

		super.ordenarMao();

		analisarEJogar(partida);

	}

	private void analisarEJogar(Partida partida) {
		if (Partida.isManilha(partida.getMesa()[0].getCarta())) {
			for (int i = getMao().size() - 1; i >= 0; i--) {
				if (Partida.isManilha(getMao().get(i))) {
					if (minhaManilhaEhMaior(getMao().get(i), partida.getMesa()[0].getCarta())) {
						super.jogarCarta(getMao().remove(i), partida);
						break;
					}
				} else {
					if (i == 0) {
						super.jogarCarta(super.getMao().remove(super.getMao().size() - 1), partida);
					}
				}
			}
		} else {
			analisarCartas(getMao().size(), partida);

		}
	}

	private void analisarCartas(int qtdeDeCartasNaMao, Partida partida) {
		if (minhaCartaEhMaior(getMao().get(qtdeDeCartasNaMao - 1), partida.getMesa()[0].getCarta())) {
			super.jogarCarta(super.getMao().remove(qtdeDeCartasNaMao - 1), partida);
		} else {
			qtdeDeCartasNaMao--;
			if (qtdeDeCartasNaMao == 0) {
				for (int i = getMao().size() - 1; i >= 0; i--) {
					if (Partida.isManilha(getMao().get(i))) {
						if (minhaManilhaEhMaior(getMao().get(i), partida.getMesa()[0].getCarta())) {
							super.jogarCarta(getMao().remove(i), partida);
							break;
						}
					} else {
						if (i == 0) {
							super.jogarCarta(super.getMao().remove(super.getMao().size() - 1), partida);
						}
					}
				}
			} else {
				analisarCartas(qtdeDeCartasNaMao, partida);
			}
		}
	}

	private boolean minhaCartaEhMaior(Carta minhaCarta, Carta cartaDaMesa) {
		return minhaCarta.getValor() > cartaDaMesa.getValor();
	}

	private boolean minhaManilhaEhMaior(Carta minhaManilha, Carta manilhaDaMesa) {
		return minhaManilha.getNaipe() > manilhaDaMesa.getNaipe();
	}

}
