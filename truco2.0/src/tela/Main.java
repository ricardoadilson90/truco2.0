package tela;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import aI.AI;
import truco.Baralho;
import truco.Carta;
import truco.Jogador;
import truco.Partida;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Baralho baralho = new Baralho();
		final Jogador player1 = new Jogador();
		final AI player2 = new AI();
		final ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(player1);
		jogadores.add(player2);
		final Partida partida = new Partida(jogadores, baralho);
		partida.distribuir();
		frame = new JFrame();
		frame.setBounds(100, 100, 791, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JLabel lblmesa2 = new JLabel("");
		lblmesa2.setBounds(183, 29, 137, 218);
		frame.getContentPane().add(lblmesa2);

		final JLabel labelmesa1 = new JLabel("");
		labelmesa1.setBounds(33, 29, 138, 218);
		frame.getContentPane().add(labelmesa1);

		final JLabel coringa = new JLabel("");
		coringa.setBounds(381, 29, 137, 218);
		coringa.setIcon(partida.getManilha().getImage());
		frame.getContentPane().add(coringa);

		final JLabel carta1 = new JLabel("");
		carta1.setBounds(33, 303, 125, 192);
		carta1.setIcon(jogadores.get(0).getMao().get(0).getImage());
		frame.getContentPane().add(carta1);

		final JLabel carta2 = new JLabel("");
		carta2.setBounds(166, 303, 125, 192);
		carta2.setIcon(jogadores.get(0).getMao().get(1).getImage());
		frame.getContentPane().add(carta2);

		final JLabel carta3 = new JLabel("");
		carta3.setBounds(299, 303, 125, 192);
		carta3.setIcon(jogadores.get(0).getMao().get(2).getImage());
		frame.getContentPane().add(carta3);

		JLabel lblCartaoponente1 = new JLabel("");
		lblCartaoponente1.setBounds(636, 13, 125, 195);
		lblCartaoponente1.setIcon(jogadores.get(1).getMao().get(0).getImage());
		frame.getContentPane().add(lblCartaoponente1);

		JLabel lblCartaoponente2 = new JLabel("");
		lblCartaoponente2.setIcon(jogadores.get(1).getMao().get(1).getImage());
		lblCartaoponente2.setBounds(636, 212, 125, 192);
		frame.getContentPane().add(lblCartaoponente2);

		JLabel lblPlacar = new JLabel("Placar Geral");
		lblPlacar.setBounds(462, 287, 84, 16);
		frame.getContentPane().add(lblPlacar);

		JLabel lblEu = new JLabel("Eu");
		lblEu.setBounds(462, 316, 31, 16);
		frame.getContentPane().add(lblEu);

		JLabel lblAi = new JLabel("AI");
		lblAi.setBounds(462, 349, 31, 16);
		frame.getContentPane().add(lblAi);

		JLabel pontuacaoMinha = new JLabel(jogadores.get(0).getPontucaoGeral() + "");
		pontuacaoMinha.setBounds(504, 316, 42, 16);
		frame.getContentPane().add(pontuacaoMinha);

		JLabel pontuacaoAI = new JLabel(jogadores.get(1).getPontucaoGeral() + "");
		pontuacaoAI.setBounds(504, 349, 42, 16);
		frame.getContentPane().add(pontuacaoAI);

		JLabel lblPlacarDaRodada = new JLabel("Placar da Rodada");
		lblPlacarDaRodada.setBounds(462, 386, 108, 16);
		frame.getContentPane().add(lblPlacarDaRodada);

		JLabel placarRodadaEU = new JLabel("Eu");
		placarRodadaEU.setBounds(462, 415, 31, 16);
		frame.getContentPane().add(placarRodadaEU);

		JLabel valorRodadaEu = new JLabel(jogadores.get(0).getPontuacaoRodada() + "");
		valorRodadaEu.setBounds(504, 415, 42, 16);
		frame.getContentPane().add(valorRodadaEu);

		JLabel valorRodadaAI = new JLabel(jogadores.get(1).getPontuacaoRodada() + "");
		valorRodadaAI.setBounds(504, 448, 42, 16);
		frame.getContentPane().add(valorRodadaAI);

		JLabel placarRodadaAI = new JLabel("AI");
		placarRodadaAI.setBounds(462, 448, 31, 16);
		frame.getContentPane().add(placarRodadaAI);

		JLabel lblCartaoponente3 = new JLabel("");
		lblCartaoponente3.setIcon(jogadores.get(1).getMao().get(2).getImage());
		lblCartaoponente3.setBounds(636, 418, 125, 192);
		frame.getContentPane().add(lblCartaoponente3);
		final ArrayList<JLabel> cartasOponente = new ArrayList<JLabel>();
		cartasOponente.add(lblCartaoponente1);
		cartasOponente.add(lblCartaoponente2);
		cartasOponente.add(lblCartaoponente3);
		final ArrayList<JLabel> cartasMinhas = new ArrayList<JLabel>();
		cartasMinhas.add(carta1);
		cartasMinhas.add(carta2);
		cartasMinhas.add(carta3);
		final ArrayList<JLabel> mesa = new ArrayList<JLabel>();
		mesa.add(labelmesa1);
		mesa.add(lblmesa2);
		final ArrayList<JLabel> placar = new ArrayList<JLabel>();
		placar.add(pontuacaoMinha);
		placar.add(pontuacaoAI);
		placar.add(valorRodadaEu);
		placar.add(valorRodadaAI);

		carta1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				labelmesa1.setIcon(carta1.getIcon());
				lblmesa2.setIcon(jogarCarta(jogadores, 0, partida));// gambiarra
				imprimirCartas(player2);
				JOptionPane.showMessageDialog(null, "AI jogou a carta "+ partida.getMesa()[1].getCarta().getNome());
				atualizarIcones(mesa, cartasOponente, cartasMinhas, coringa, jogadores, partida, placar);


			}
		});
		carta2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				labelmesa1.setIcon(carta2.getIcon());
				lblmesa2.setIcon(jogarCarta(jogadores, 1, partida));// gambiarra
				imprimirCartas(player2);
				JOptionPane.showMessageDialog(null, "AI jogou a carta "+ partida.getMesa()[1].getCarta().getNome());
				atualizarIcones(mesa, cartasOponente, cartasMinhas, coringa, jogadores, partida, placar);

			}
		});

		carta3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				labelmesa1.setIcon(carta3.getIcon());
				lblmesa2.setIcon(jogarCarta(jogadores, 2, partida));// gambiarra
				imprimirCartas(player2);
				JOptionPane.showMessageDialog(null, "AI jogou a carta "+ partida.getMesa()[1].getCarta().getNome());
				atualizarIcones(mesa, cartasOponente, cartasMinhas, coringa, jogadores, partida, placar);

			}

		});

	}

	private Icon jogarCarta(ArrayList<Jogador> jogadores, int localizacaoDaCarta, Partida partida) {
		// fazendo a jogada
		Carta carta = jogadores.get(0).getMao().get(localizacaoDaCarta); // escolhe a carta clicada pelo usuário
		jogadores.get(0).jogarCarta(carta, partida); // informa a partida sobre a jogada feita

		// ----------------Vez das AIs jogar ----------------\
		Carta cartaAi = null;

		jogadores.get(1).jogarCarta(cartaAi, partida);

		// --------------------------------------------------
		return partida.getMesa()[1].getCarta().getImage();

	}

	private void imprimirCartas(Jogador jogador) {
		for (int i = 0; i < jogador.getMao().size(); i++) {

		}
	}

	private void atualizarIcones(ArrayList<JLabel> mesa, ArrayList<JLabel> cartasOponente,
			ArrayList<JLabel> cartasMinhas, JLabel coringa, ArrayList<Jogador> jogadores, Partida partida,
			ArrayList<JLabel> placar) {
		for (int i = 0; i < mesa.size(); i++) {
			mesa.get(i).setIcon(null);
		}
		for (int i = 0; i < jogadores.get(0).getMao().size(); i++) {
			cartasMinhas.get(i).setIcon(jogadores.get(0).getMao().get(i).getImage());
			cartasMinhas.get(i).setVisible(true);
			cartasOponente.get(i).setIcon(jogadores.get(1).getMao().get(i).getImage());
			cartasOponente.get(i).setVisible(true);
		}
		for (int i = jogadores.get(0).getMao().size(); i < cartasMinhas.size(); i++) {
			cartasMinhas.get(i).setIcon(null);
			cartasMinhas.get(i).setVisible(false);
			cartasOponente.get(i).setIcon(null);
			cartasOponente.get(i).setVisible(false);
		}
		coringa.setIcon(partida.getManilha().getImage());

		placar.get(0).setText(jogadores.get(0).getPontucaoGeral() + "");
		placar.get(1).setText(jogadores.get(1).getPontucaoGeral() + "");
		placar.get(2).setText(jogadores.get(0).getPontuacaoRodada() + "");
		placar.get(3).setText(jogadores.get(1).getPontuacaoRodada() + "");
		/*if(jogadores.get(0).getPontuacaoRodada()>jogadores.get(1).getPontuacaoRodada()) {
			JOptionPane.showMessageDialog(null, "Você venceu esta rodada");
		}else {
			JOptionPane.showMessageDialog(null, "Você perdeu esta rodada");
		}*/

	}
}
