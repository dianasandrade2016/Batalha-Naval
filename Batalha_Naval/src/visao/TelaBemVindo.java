package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class TelaBemVindo extends JFrame {

	private JPanel contentPane;
	private JLabel lblBemVindoAo;
	private JLabel lblEstiloDeJogo;
	private JButton btnAleatorio;
	private JButton btnDefinido;

	private BatalhaNaval batalha = null;
	
	private JLabel lblFundo;
	
	public TelaBemVindo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 385);
		contentPane = new JPanel();

		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		contentPane.add(getLblBemVindoAo());
		contentPane.add(getLblEstiloDeJogo());
		contentPane.add(getBtnAleatorio());
		contentPane.add(getBtnDefinido());
		contentPane.add(getLblFundo());

		 
		 this.setLocationRelativeTo(null);
		 this.setResizable(false);
		 this.setVisible(true);
		 
	}

	private JLabel getLblBemVindoAo() {
		if (lblBemVindoAo == null) {
			lblBemVindoAo = new JLabel("BATALHA NAVAL");
			lblBemVindoAo.setForeground(Color.WHITE);
			lblBemVindoAo.setFont(new Font("Aharoni", Font.PLAIN, 35));
			lblBemVindoAo.setHorizontalAlignment(SwingConstants.CENTER);
			lblBemVindoAo.setBounds(0, 0, 594, 89);
		}
		return lblBemVindoAo;
	}
	private JLabel getLblEstiloDeJogo() {
		if (lblEstiloDeJogo == null) {
			lblEstiloDeJogo = new JLabel("Estilo de jogo:");
			lblEstiloDeJogo.setFont(new Font("Aharoni", Font.PLAIN, 17));
			lblEstiloDeJogo.setForeground(Color.WHITE);
			lblEstiloDeJogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstiloDeJogo.setBounds(0, 287, 594, 31);
		}
		return lblEstiloDeJogo;
	}
	private JButton getBtnAleatorio() {
		if (btnAleatorio == null) {
			btnAleatorio = new JButton("Aleatorio");
			btnAleatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					BatalhaNaval batalha = new BatalhaNaval(false);
					batalha.gerarJogoJogador();
					
					//batalha.gerarJogoJogador();
					//batalha.gerarJogoComputador();
					dispose();
				}
			});
			btnAleatorio.setBounds(140, 322, 111, 23);
		}
		return btnAleatorio;
	}
	private JButton getBtnDefinido() {
		if (btnDefinido == null) {
			btnDefinido = new JButton("Definir");
			btnDefinido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
										
					Object[] options = { "Definir", "Carregar", "Fechar"};
					int choice = JOptionPane.showOptionDialog(null,"Novo Jogo?", "Batalha Naval",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
				      
				      if (choice == JOptionPane.YES_OPTION){
				    	  
				    	  batalha = new BatalhaNaval(true);
				    	  dispose();
				    	  							
				      }
				      if (choice == JOptionPane.NO_OPTION){
				    								
							BatalhaNaval batalha = new BatalhaNaval(true);
							batalha.carregarPartida();
							dispose();
														
					  }
				      if (choice == JOptionPane.CANCEL_OPTION){
				    	  //System.out.println("Fechar");
				    	  //System.exit(0);
					  }
				}
			});
			btnDefinido.setBounds(338, 322, 111, 23);
		}
		return btnDefinido;
	}
	private JLabel getLblFundo() {
		if (lblFundo == null) {
			lblFundo = new JLabel("New label");
			lblFundo.setBounds(0, 0, 600, 385);
			
			ImageIcon iconFundo = new ImageIcon("img/batalhanaval.jpg");
			iconFundo.setImage(iconFundo.getImage().getScaledInstance(600, 385, 100));
			lblFundo.setIcon(iconFundo);
		}
		return lblFundo;
	}
}
