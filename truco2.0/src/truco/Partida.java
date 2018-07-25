package truco;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Partida {
	private static Carta manilha;
	private int turno; // uma rodada é quando o jogador joga uma carta
	private ArrayList<Jogador> jogadores;
	private ArrayList<Carta> descartadas = new ArrayList<Carta>();
	private Jogada mesa[] = new Jogada[2];
	private Baralho baralho; // pilha de cartas
	private int rodada; // uma rodada é composta por dois turnos, que são a quantidade de cartas que os
	// jogadores jogarão

	public Partida(ArrayList<Jogador> jogadores, Baralho baralho) {
		this.jogadores = jogadores;
		this.baralho = baralho;

	}

	public void registrarJogada(Jogada jogada) {

		mesa[turno++] = jogada;
		descartadas.add(jogada.getCarta());
		if (turno == 2) {
			encerrarTurno();
		}

	}

	public void distribuir() {
		baralho.embaralhar();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				jogadores.get(i).getMao().add(baralho.distribuirUmaCarta());
			}
		}
		setManilha(baralho.distribuirUmaCarta());
		setTurno(0);
		setDescartadas(new ArrayList<Carta>());
	}

	// ----------------------sessão de métodos privados que auxiliarão o
	// funcionamento do método jogarCarta() -----------------
	private void encerrarTurno() {
		turno = 0;
		verificarVencedorTurno();

		rodada++;

		if (rodada != 0 && rodada % 3 == 0) {

			verificarVencedorRodada();
			distribuir();
		}

	}

	private void verificarVencedorRodada() {
		if (mesa[0].getDono().getPontuacaoRodada() > mesa[1].getDono().getPontuacaoRodada()) {
			atribuirPontuacaoGeral(mesa[0].getDono());
		} else {
			atribuirPontuacaoGeral(mesa[1].getDono());
		}
		mesa[0].getDono().setPontuacaoRodada(0);
		mesa[1].getDono().setPontuacaoRodada(0);

	}

	private void verificarVencedorTurno() {
		Carta maior;
		ArrayList<Carta> manilhas = new ArrayList<Carta>();
		for (int i = 0; i < mesa.length; i++) {
			if (isManilha(mesa[i].getCarta())) {
				manilhas.add(mesa[i].getCarta());

			}
		}
		Jogador.ordenarManilhas(manilhas);
		if (!manilhas.isEmpty()) {
			if (manilhas.size() == 1) {
				acharVencedor(mesa, manilhas.get(0));
			} else {
				maior = acharMaiorManilha(mesa[0].getCarta(), mesa[1].getCarta());
				acharVencedor(mesa, maior);
			}
		} else {
			maior = acharMaior(mesa[0].getCarta(), mesa[1].getCarta());
			if (maior != null) {
				acharVencedor(mesa, maior);
			} else {
				ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
				for (int i = 0; i < mesa.length; i++) {
					jogadores.add(mesa[i].getDono());
				}
				atribuirEmpateRodada(jogadores);
			}

		}
	}

	private Carta acharMaior(Carta carta, Carta maior) {
		if (carta.getValor() > maior.getValor()) {
			return carta;
		} else {
			if (carta.getValor() == maior.getValor()) {
				if (rodada % 3 == 2) {
					if (carta.getNaipe() > maior.getNaipe()) {
						return carta;
					} else {
						return maior;
					}
				} else {
					return null;
				}
			} else {
				return maior;
			}
		}
	}

	private Carta acharMaiorManilha(Carta carta, Carta maior) {
		if (carta.getNaipe() > maior.getNaipe()) {
			return carta;
		}
		return maior;
	}

	private void atribuirPontuacaoGeral(Jogador jogador) {
		jogador.setPontucaoGeral(jogador.getPontucaoGeral() + 1);
		if (jogador.getPontucaoGeral() == 12) {
			JOptionPane.showMessageDialog(null, "Houve um ganhador!\n" + jogador.getClass().toString() + " venceu!!!");
			System.exit(0);
		}
	}

	private void atribuirPontuacaoRodada(Jogador jogador) {
		jogador.setPontuacaoRodada(jogador.getPontuacaoRodada() + 1);
	}

	private void atribuirEmpateRodada(ArrayList<Jogador> jogadores) {
		for (int i = 0; i < jogadores.size(); i++) {
			atribuirPontuacaoRodada(jogadores.get(i));
		}
	}

	private void acharVencedor(Jogada[] jogada, Carta maior) {
		for (int i = 0; i < jogada.length; i++) {
			if (jogada[i].getCarta().equals(maior)) {
				Jogador vencedor = jogada[i].getDono();
				atribuirPontuacaoRodada(vencedor);
				break;
			}
		}

	}

	// ------------------------------sessão de getters e
	// setters----------------------------------------------

	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public ArrayList<Carta> getDescartadas() {
		return descartadas;
	}

	public void setDescartadas(ArrayList<Carta> descartadas) {
		this.descartadas = descartadas;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public Carta getManilha() {
		return manilha;
	}

	public void setManilha(Carta manilha) {
		Partida.manilha = manilha;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Jogada[] getMesa() {
		return mesa;
	}

	public void setMesa(Jogada[] mesa) {
		this.mesa = mesa;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public static boolean isManilha(Carta carta) {
		switch (manilha.getValor()) {
		case 7:
			if (carta.getValor() == 10) {
				return true;
			} else {
				return false;
			}
		case 15:
			if (carta.getValor() == 4) {
				return true;
			} else {
				return false;
			}
		default:
			if (manilha.getValor() + 1 == carta.getValor()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
