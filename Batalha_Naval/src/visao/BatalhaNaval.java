package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Button;
import java.awt.Color;

import javax.swing.SwingConstants;

import modelo.Jato;
import modelo.Escolta;
import modelo.PortaAviao;
import modelo.Submarino;
import javax.swing.JRadioButton;

public class BatalhaNaval extends JFrame {

	private JPanel contentPane,panelJogador,panelComputador;
	
	private List<JButton> btnsJogador = new ArrayList<JButton>();
	private List<JButton> btnsComputador = new ArrayList<JButton>();
	private List<String> posicoesJogador = new ArrayList<String>();
	private List<String> posicoesComputador = new ArrayList<String>();
	private List<String> numAleatorios  = new ArrayList<String>();
	private List<String> jogadasComputador  = new ArrayList<String>();
	
	private int ptsJogador = 0;
	private int ptsComputador = 0;
	private int vezVeiculo =1;
	private int tiros =0;
	private int contDicas=3;
	
	private String veiculo = "";

	private JButton btnStart, btnGerar, btnDica,btnSair;
	
	private JLabel lblNomeJogador, lblnomeComputador, lblFundo, lblPtsJogador, lblPtsComputador, lblSelecioneOProximo;
	
	private boolean jogoDefinido=false;
	private boolean dica = false;
	
	boolean submarinoCriado = false;
	boolean aviaoCriado = false;
	boolean navioCriado = false;
	boolean portaAviaoCriado = false;
	
	private ImageIcon icoMira,iconErro,icoBoom,iconFundo; 
	
	private Submarino submarino = null;;
	private Jato jato = null;;
	private Escolta escolta = null;
	private PortaAviao portaAviao = null;

	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSubmarino;
	private JRadioButton rdbtnEscolta;
	private JRadioButton rdbtnCaa;
	
	public BatalhaNaval(Boolean jogoDefinido) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 720);
		//setBounds(100, 100, 1200, 720);
		contentPane = new JPanel();

		setTitle("Batalha Naval");

		this.jogoDefinido = jogoDefinido;
				
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		btnsJogador = new ArrayList<JButton>();
		btnsComputador = new ArrayList<JButton>();
		posicoesJogador = new ArrayList<String>();
		posicoesComputador = new ArrayList<String>();
		numAleatorios  = new ArrayList<String>();
		jogadasComputador  = new ArrayList<String>();
		
		iconFundo = new ImageIcon("img/mar.jpg");
		icoMira = new ImageIcon("img/mira.png");
		icoMira.setImage(icoMira.getImage().getScaledInstance(50, 50, 100));
		
		icoBoom = new ImageIcon("img/boom.png");
		icoBoom.setImage(icoBoom.getImage().getScaledInstance(50, 50, 100));
		iconErro = new ImageIcon("img/error.png");
		
		submarino = new Submarino();
		jato = new Jato();
		escolta = new Escolta();
		portaAviao = new PortaAviao();
		
		contentPane.add(submarino.getSubmarino());
		contentPane.add(jato.getJato());
		contentPane.add(escolta.getNavio());
		contentPane.add(portaAviao.getPortaAviao());
			
		contentPane.add(getPanelJogador());
		
		geraBotaoJogador();
				
		contentPane.add(getPanelComputador());
		contentPane.add(getBtnGerar());
		contentPane.add(getBtnStart());
		contentPane.add(getBtnSair());
		contentPane.add(getBtnDica());
		contentPane.add(getRdbtnCaa());
		contentPane.add(getRdbtnEscolta());
		contentPane.add(getRdbtnSubmarino());

		contentPane.add(getLblSelecioneOProximo());
		contentPane.add(getLblNomeJogador());
		contentPane.add(getLblNomeComputador());
		contentPane.add(getLblPtsJogador());
		contentPane.add(getLblPtsComputador());
		
		contentPane.add(getLblFundo());
		
		geraBotaoComputador();
	
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		submarino.getSubmarino().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				veiculo = submarino.getSubmarino().getText();
			}
		});
		jato.getJato().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				veiculo = jato.getJato().getText();
			}
		});
		escolta.getNavio().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				veiculo = escolta.getNavio().getText();
			}
		});
		portaAviao.getPortaAviao().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				veiculo = portaAviao.getPortaAviao().getText();
			}
		});
		
		buttonGroup.add( rdbtnSubmarino);
		buttonGroup.add( rdbtnEscolta);
		buttonGroup.add( rdbtnCaa);

	}
	
	private JPanel getPanelJogador() {
		
		if (panelJogador == null) {
			panelJogador = new JPanel();
			panelJogador.setBounds(60, 70, 500, 500);
			panelJogador.setLayout(new GridLayout(10, 0, 0, 0));
		}
		return panelJogador;
	}
	private JPanel getPanelComputador() {
		if (panelComputador == null) {
			panelComputador = new JPanel();
			panelComputador.setBounds(638, 70, 500, 500);
			panelComputador.setLayout(new GridLayout(10, 0, 0, 0));
			panelComputador.setVisible(false);
		}
		return panelComputador;
	}

	private JLabel getLblSelecioneOProximo() {
		if (lblSelecioneOProximo == null) {
			lblSelecioneOProximo = new JLabel("Selecione o proximo disparo:");
			lblSelecioneOProximo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSelecioneOProximo.setForeground(Color.WHITE);
			lblSelecioneOProximo.setBounds(638, 643, 500, 14);
			
		}
		return lblSelecioneOProximo;
	}
	
	private JLabel getLblNomeJogador() {
		if (lblNomeJogador == null) {
			lblNomeJogador = new JLabel("JOGADOR");
			lblNomeJogador.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNomeJogador.setForeground(Color.WHITE);
			lblNomeJogador.setHorizontalAlignment(SwingConstants.CENTER);
			lblNomeJogador.setBounds(60, 581, 500, 14);
			
		}
		return lblNomeJogador;
	}
	private JLabel getLblNomeComputador() {
		if (lblnomeComputador == null) {
			lblnomeComputador = new JLabel("COMPUTADOR");
			lblnomeComputador.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblnomeComputador.setForeground(Color.WHITE);
			lblnomeComputador.setHorizontalAlignment(SwingConstants.CENTER);
			lblnomeComputador.setBounds(638, 581, 500, 14);

		}
		return lblnomeComputador;
	}
	private JLabel getLblFundo() {
		if (lblFundo == null) {
			lblFundo = new JLabel("New label");
			lblFundo.setBounds(0, 0,1200, 800);

			iconFundo.setImage(iconFundo.getImage().getScaledInstance(1200, 800, 100));
			lblFundo.setIcon(iconFundo);
		}
		return lblFundo;
	}
	private JLabel getLblPtsJogador() {
		if (lblPtsJogador == null) {
			
			lblPtsJogador = new JLabel("Voc\u00EA 00");
			lblPtsJogador.setVisible(false);
			lblPtsJogador.setForeground(Color.WHITE);
			lblPtsJogador.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPtsJogador.setHorizontalAlignment(SwingConstants.CENTER);
			lblPtsJogador.setBounds(60, 22, 500, 23);
		}
		return lblPtsJogador;
	}
	private JLabel getLblPtsComputador() {
		if (lblPtsComputador == null) {
			lblPtsComputador = new JLabel("Inimigo 00");
			lblPtsComputador.setForeground(Color.WHITE);
			lblPtsComputador.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPtsComputador.setHorizontalAlignment(SwingConstants.CENTER);
			lblPtsComputador.setBounds(637, 22, 501, 23);
		}
		return lblPtsComputador;
	}
		
	private void geraBotaoJogador() {
		
		int p=1;
		for(int i=1;i<101;i++){
			JButton btn = new JButton();
			btn.setFont(new Font("Arial", Font.PLAIN, 9));
			
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setFocusable(false);
			btn.setRolloverEnabled(false);
						
			if(i<=10){
				btn.setText("A"+p);
			}
			if(i>10 && i<21){
				btn.setText("B"+p);
			}
			if(i>20 && i <31){
				btn.setText("C"+p);
			}
			if(i>30 && i<41){
				btn.setText("D"+p);
			}
			if(i>40 && i<51){
				btn.setText("E"+p);
			}
			if(i>50 && i<61){
				btn.setText("F"+p);
			}
			if(i>60 && i<71){
				btn.setText("G"+p);
			}
			if(i>70 && i<81){
				btn.setText("H"+p);
			}
			if(i>80 && i<91){
				btn.setText("I"+p);
			}
			if(i>90 && i<101){
				btn.setText("J"+p);
			}
			p++;
			if(p>10){
				p=1;
			}
			
			if(jogoDefinido==true){

				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(btn.getText().equals("A10") || btn.getText().equals("B10") || btn.getText().equals("C10") || 
								btn.getText().equals("D10") || btn.getText().equals("E10") || btn.getText().equals("F10") || 
								btn.getText().equals("G10") || btn.getText().equals("H10") || btn.getText().equals("I10") || btn.getText().equals("J10")){
							
							JOptionPane.showMessageDialog(null, "Posição invalida");
						
						}else{

							if(veiculo.equals("Submarino")){
								if(submarinoCriado==false){
									submarino.setSubmarino(btn.getLocation().x +60 , btn.getLocation().y +70);
									contentPane.add(submarino.getSubmarino());
									for(int i =0;i<btnsJogador.size();i++){
										if(btnsJogador.get(i).getText().equals(btn.getText())){
											posicoesJogador.add(btnsJogador.get(i).getText());
											posicoesJogador.add(btnsJogador.get(i+1).getText());
										}
									}
									contentPane.add(getPanelJogador());
									contentPane.add(getLblFundo());
									repaint();
									
									submarinoCriado=true;
								}
								veiculo = "";
							}
							if(veiculo.equals("Jato")){
								if(aviaoCriado==false){
									
									jato.setJato(btn.getLocation().x +60 , btn.getLocation().y +70);
									contentPane.add(jato.getJato());
									
									for(int i =0;i<btnsJogador.size();i++){
										if(btnsJogador.get(i).getText().equals(btn.getText())){
											posicoesJogador.add(btnsJogador.get(i).getText());
											posicoesJogador.add(btnsJogador.get(i+1).getText());
										}
									}
									contentPane.add(getPanelJogador());
									contentPane.add(getLblFundo());
									repaint();
									
									aviaoCriado=true;
								}
								veiculo = "";
							}
							if(veiculo.equals("Navio")){
								
								if(btn.getText().equals("A9") || btn.getText().equals("B9") || btn.getText().equals("C9") || 
										btn.getText().equals("D9") || btn.getText().equals("E9") || btn.getText().equals("F9") || 
										btn.getText().equals("G9") || btn.getText().equals("H9") || btn.getText().equals("I9") || btn.getText().equals("J9")){
									
									JOptionPane.showMessageDialog(null, "Posição invalida");
									
								}else{
				
								
									if(navioCriado==false){
										escolta.setNavio(btn.getLocation().x +60 , btn.getLocation().y +70);
										contentPane.add(escolta.getNavio());
										for(int i =0;i<btnsJogador.size();i++){
											if(btnsJogador.get(i).getText().equals(btn.getText())){
												posicoesJogador.add(btnsJogador.get(i).getText());
												posicoesJogador.add(btnsJogador.get(i+1).getText());
												posicoesJogador.add(btnsJogador.get(i+2).getText());
											}
										}
										contentPane.add(getPanelJogador());
										contentPane.add(getLblFundo());
										repaint();	
										navioCriado = true;
									}
									veiculo = "";
								}
							}
							if(veiculo.equals("PortaAviao")){
								
								if(btn.getText().equals("A9") || btn.getText().equals("B9") || btn.getText().equals("C9") || 
										btn.getText().equals("D9") || btn.getText().equals("E9") || btn.getText().equals("F9") || 
										btn.getText().equals("G9") || btn.getText().equals("H9") || btn.getText().equals("I9") || btn.getText().equals("J9") ||
										
										btn.getText().equals("A8") || btn.getText().equals("B8") || btn.getText().equals("C8") || 
										btn.getText().equals("D8") || btn.getText().equals("E8") || btn.getText().equals("F8") || 
										btn.getText().equals("G8") || btn.getText().equals("H8") || btn.getText().equals("I8") || btn.getText().equals("J8")){
									
									JOptionPane.showMessageDialog(null, "Posição invalida");
									
								}else{
								
									if(portaAviaoCriado==false){
										portaAviao.setPortaAviao(btn.getLocation().x +60 , btn.getLocation().y +70);
										contentPane.add(portaAviao.getPortaAviao());
										for(int i =0;i<btnsJogador.size();i++){
											if(btnsJogador.get(i).getText().equals(btn.getText())){
												posicoesJogador.add(btnsJogador.get(i).getText());
												posicoesJogador.add(btnsJogador.get(i+1).getText());
												posicoesJogador.add(btnsJogador.get(i+2).getText());
												posicoesJogador.add(btnsJogador.get(i+3).getText());
											}
										}
										contentPane.add(getPanelJogador());
										contentPane.add(getLblFundo());
										repaint();
										portaAviaoCriado=true;
									}
									veiculo = "";
								}
							}
						}
					}
				});
				
			}

			btnsJogador.add(btn);
			panelJogador.add(btn);
		}
		
	}

	public void gerarNumeros(){
		
		numAleatorios.clear();
		
		int p=1;
		for(int i=1;i<101;i++){
			if(i<=10){
				numAleatorios.add("A"+p);
			}
			if(i>10 && i<21){
				numAleatorios.add("B"+p);
			}
			if(i>20 && i <31){
				numAleatorios.add("C"+p);
			}
			if(i>30 && i<41){
				numAleatorios.add("D"+p);
			}
			if(i>40 && i<51){
				numAleatorios.add("E"+p);
			}
			if(i>50 && i<61){
				numAleatorios.add("F"+p);
			}
			if(i>60 && i<71){
				numAleatorios.add("G"+p);
			}
			if(i>70 && i<81){
				numAleatorios.add("H"+p);
			}
			if(i>80 && i<91){
				numAleatorios.add("I"+p);
			}
			if(i>90 && i<101){
				numAleatorios.add("J"+p);
			}
			p++;
			if(p>10){
				p=1;
			}
		}
	}
	
	public void gerarJogoJogador(){
		
		gerarSub();
		gerarAviao();
		gerarNavio();
		gerarPortaAviao();
		
		contentPane.add(getPanelJogador());
		contentPane.add(getLblFundo());
		
	}
	
	public void gerarJogadasComp(){
		int p=1;
		for(int i=1;i<101;i++){
			if(i<=10){
				jogadasComputador.add("A"+p);
			}
			if(i>10 && i<21){
				jogadasComputador.add("B"+p);
			}
			if(i>20 && i <31){
				jogadasComputador.add("C"+p);
			}
			if(i>30 && i<41){
				jogadasComputador.add("D"+p);
			}
			if(i>40 && i<51){
				jogadasComputador.add("E"+p);
			}
			if(i>50 && i<61){
				jogadasComputador.add("F"+p);
			}
			if(i>60 && i<71){
				jogadasComputador.add("G"+p);
			}
			if(i>70 && i<81){
				jogadasComputador.add("H"+p);
			}
			if(i>80 && i<91){
				jogadasComputador.add("I"+p);
			}
			if(i>90 && i<101){
				jogadasComputador.add("J"+p);
			}
			p++;
			if(p>10){
				p=1;
			}
		}
		
		Collections.shuffle((List<?>) jogadasComputador);
		
	}
	
	public void gerarSub(){
		
		posicoesJogador.clear();
		
		gerarNumeros();
	
		for(int i = 1;i<numAleatorios.size();i++){
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A10") || botao.equals("B10") || botao.equals("C10") || 
					botao.equals("D10") || botao.equals("E10") || botao.equals("F10") || 
					botao.equals("G10") || botao.equals("H10") || botao.equals("I10") || botao.equals("J10")){
			
				numAleatorios.remove(i);

			}
		}
		
		Collections.shuffle((List<?>) numAleatorios);
	
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicao)){
					
					if(!posicoesJogador.contains(pos) ){
						if(!posicoesJogador.contains(pos+1)){

							adicionado = true;
							
							submarino.setSubmarino(b.getLocation().x+60, b.getLocation().y+70);
							contentPane.add(submarino.getSubmarino());
					
							repaint();
								
							posicoesJogador.add(b.getText());
							posicoesJogador.add(btnsJogador.get(pos+1).getText());

							submarino.setPosicao(b.getText());
							
							break;
							
						}
					}
					
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
		}
	}
	public void gerarAviao(){
		
		Collections.shuffle((List<?>) numAleatorios);
	
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicao)){
					
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					
					System.out.println("posições aviao: "+p1+" -" +p2 );
					
					
					if(checagemPosicao(1,p1,p2,"","")==true){
													
						if(!posicoesJogador.contains(pos+1)){
					
							adicionado = true;
							
							jato.setJato(b.getLocation().x +60 , +b.getLocation().y+70);
							contentPane.add(jato.getJato());
					
							repaint();
								
							posicoesJogador.add(b.getText());
							posicoesJogador.add(btnsJogador.get(pos+1).getText());
					
							jato.setPosicao(b.getText());
							
							
							break;
							
						}
					}
					
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
		}
	}
	public void gerarNavio(){
		
		Collections.sort(numAleatorios);
	
		for(int i = 1;i<numAleatorios.size();i++){
			
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A9") || botao.equals("B9") || botao.equals("C9") || 
					botao.equals("D9") || botao.equals("E9") || botao.equals("F9") || 
					botao.equals("G9") || botao.equals("H9") || botao.equals("I9") || botao.equals("J9")){
			
				numAleatorios.remove(i);
			}
		}
		Collections.shuffle((List<?>) numAleatorios);
	
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);

			int pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicao)){
			
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					String p3 = btnsJogador.get(pos+2).getText();
				
					System.out.println("posições navio: "+p1+" -" +p2+" - "+p3 );
					
					if(checagemPosicao(1,p1,p2,p3,"")==true){
							
						adicionado = true;
								
						escolta.setNavio(b.getLocation().x +60 , b.getLocation().y +70);
						contentPane.add(escolta.getNavio());
									
						repaint();
									
						posicoesJogador.add(b.getText());
						posicoesJogador.add(btnsJogador.get(pos+1).getText());
						posicoesJogador.add(btnsJogador.get(pos+2).getText());
								
						escolta.setPosicao(b.getText());
								
						break;
							
					}
				}
			
				pos++;				
			}
			if(adicionado==true){
				break;
			}

		}

	}
	public void gerarPortaAviao(){
		
		Collections.sort(numAleatorios);
		
		for(int i = 1;i<numAleatorios.size();i++){
			
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A8") || botao.equals("B8") || botao.equals("C8") || 
					botao.equals("D8") || botao.equals("E8") || botao.equals("F8") || 
					botao.equals("G8") || botao.equals("H8") || botao.equals("I8") || botao.equals("J8")){
			
				numAleatorios.remove(i);
			}
		}
		
		Collections.shuffle((List<?>) numAleatorios);
		
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);

			int pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicao)){
					
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					String p3 = btnsJogador.get(pos+2).getText();
					String p4 = btnsJogador.get(pos+3).getText();
					
					System.out.println("posições barcao: "+p1+" -" +p2+" - "+p3+" - "+p4 );
					
					
					if(checagemPosicao(1,p1,p2,p3,p4)==true){
						
						adicionado = true;
						portaAviao.setPortaAviao(b.getLocation().x +60, +b.getLocation().y +70);
						contentPane.add(portaAviao.getPortaAviao());
						
						repaint();
												
						posicoesJogador.add(b.getText());
						posicoesJogador.add(btnsJogador.get(pos+1).getText());
						posicoesJogador.add(btnsJogador.get(pos+2).getText());
						posicoesJogador.add(btnsJogador.get(pos+3).getText());
					
						portaAviao.setPosicao(b.getText());										
						
						break;
					}
	
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
			
	
		}

		System.out.println("posicoes Jogador: " + posicoesJogador);
	}
	
	public boolean checagemPosicao(int vez,String p1,String p2,String p3,String p4){
		
		boolean criar =true;
		
		if(vez==0){
			if(posicoesComputador.contains(p1)){
				criar=false;
			}
			if(posicoesComputador.contains(p2)){
				criar=false;
			}
			if(posicoesComputador.contains(p3)){
				criar=false;
			}
			if(posicoesComputador.contains(p4)){
				criar=false;
			}
		}
		
		if(vez==1){
			if(posicoesJogador.contains(p1)){
				criar=false;
			}
			if(posicoesJogador.contains(p2)){
				criar=false;
			}
			if(posicoesJogador.contains(p3)){
				criar=false;
			}
			if(posicoesJogador.contains(p4)){
				criar=false;
			}
		}
		
		return criar;
	}

	private void geraBotaoComputador() {

		int p=1;
		for(int i=1;i<101;i++){
			JButton btn = new JButton();
			btn.setFont(new Font("Arial", Font.PLAIN, 9));
			
			btn.setOpaque(false);
			//btn.setContentAreaFilled(false);
			btn.setFocusable(false);
			btn.setRolloverEnabled(false);
						
			if(i<=10){
				btn.setText("A"+p);
			}
			if(i>10 && i<21){
				btn.setText("B"+p);
			}
			if(i>20 && i <31){
				btn.setText("C"+p);
			}
			if(i>30 && i<41){
				btn.setText("D"+p);
			}
			if(i>40 && i<51){
				btn.setText("E"+p);
			}
			if(i>50 && i<61){
				btn.setText("F"+p);
			}
			if(i>60 && i<71){
				btn.setText("G"+p);
			}
			if(i>70 && i<81){
				btn.setText("H"+p);
			}
			if(i>80 && i<91){
				btn.setText("I"+p);
			}
			if(i>90 && i<101){
				btn.setText("J"+p);
			}
			p++;
			if(p>10){
				p=1;
			}
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(dica==true){
						if(posicoesComputador.contains(btn.getText())){
							JOptionPane.showMessageDialog(null, "Algo Avistado Capitão!");
						}else{
							JOptionPane.showMessageDialog(null, "Nada encontrado Aqui!");
						}
						
						dica=false;
						
						if(contDicas>0){
							btnDica.setEnabled(true);
						}
							
					}else{

						if(!veiculo.equalsIgnoreCase("")){
							
							if(veiculo.equals("sub")){
								rdbtnSubmarino.setEnabled(false);
							}
							
							if(veiculo.equals("barco")){
								rdbtnEscolta.setEnabled(false);
							}
							if(veiculo.equals("caça")){
								rdbtnCaa.setEnabled(false);
							}
							
							String pos = btn.getText();
							
							tiroEmbarcacao(pos,btnsComputador,1);//---------------------------------------------------------------------------faz o disparo pelo tipo de embarcação
							
							VezComputador();
							
							tiros++;
							
							if(tiros==3){
								
								rdbtnSubmarino.setEnabled(true);
								rdbtnEscolta.setEnabled(true);
								rdbtnCaa.setEnabled(true);
								
								tiros=0;
							}
						
						}else{
							JOptionPane.showMessageDialog(null, "Selecione a Embarcação");
						}
						
					if(ptsJogador >= 11){
						sair("Parabéns, Você Venceu!");
					}
								
					if(ptsComputador == 11){
						sair("Que pena você perdeu!");
					}
				}
			}

		});

		btnsComputador.add(btn);
		panelComputador.add(btn);
		
		}
	}
	
	public void VezComputador(){
		
		//APOS O JOGADOR JOGAR O COMPUTADOR FAZ SUA JOGADA....
		System.out.println( "pc jogou: "+jogadasComputador.get(0));
					
		String vezComputador = jogadasComputador.get(0);
		
		if(vezVeiculo==3){
			veiculo = "sub";
		}
		if(vezVeiculo==1){
			veiculo = "barco";
		}
		if(vezVeiculo==2){
			veiculo = "caça";
		}
		vezVeiculo++;
		
		tiroEmbarcacao(vezComputador,btnsJogador,0);//----------------------------------faz o disparo pelo tipo de embarcação
						
		for(JButton b : btnsJogador){
						
			if(b.getText().equals(vezComputador)){
	
				if(posicoesJogador.contains(vezComputador)){
					System.out.println( "Computador acertou");
			
					ptsComputador++;
					lblPtsComputador.setText("Pontos: 0"+ptsComputador);
				}

				contentPane.add(jato.getJato());
				contentPane.add(submarino.getSubmarino());
							
				contentPane.add(escolta.getNavio());
				contentPane.add(portaAviao.getPortaAviao());
							
				contentPane.add(getPanelJogador());
							
				contentPane.add(getLblFundo());
							
				repaint();

			}
					
		}
				
		jogadasComputador.remove(0);

		if(vezVeiculo>3){
			vezVeiculo=1;
		}
		
	}

	public void gerarSubPc(){
		
		posicoesComputador.clear();
		
		gerarNumeros();
		
		for(int i = 1;i<numAleatorios.size();i++){
			
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A10") || botao.equals("B10") || botao.equals("C10") || 
					botao.equals("D10") || botao.equals("E10") || botao.equals("F10") || 
					botao.equals("G10") || botao.equals("H10") || botao.equals("I10") || botao.equals("J10")){
			
				numAleatorios.remove(i);
			}
		}
			
		Collections.shuffle((List<?>) numAleatorios);

		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsComputador){
				if(b.getText().equals(posicao)){
					
					if(!posicoesComputador.contains(pos) ){
						if(!posicoesComputador.contains(pos+1)){

							adicionado = true;
	
							posicoesComputador.add(b.getText());
							posicoesComputador.add(btnsJogador.get(pos+1).getText());
							
							break;
							
						}
					}
					
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
		}

	}
	
	public void gerarAviaoPc(){

		Collections.shuffle((List<?>) numAleatorios);

		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsComputador){
				if(b.getText().equals(posicao)){
					
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					
					System.out.println("posições aviao=PC: "+p1+" -" +p2 );
					
					
					if(checagemPosicao(1,p1,p2,"","")==true){
						
						adicionado = true;

						posicoesComputador.add(b.getText());
						posicoesComputador.add(btnsJogador.get(pos+1).getText());
							
						break;
						
					}
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
		}
	}
	
	public void gerarNavioPc(){
				
		Collections.sort(numAleatorios);
		
		for(int i = 1;i<numAleatorios.size();i++){
			
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A9") || botao.equals("B9") || botao.equals("C9") || 
					botao.equals("D9") || botao.equals("E9") || botao.equals("F9") || 
					botao.equals("G9") || botao.equals("H9") || botao.equals("I9") || botao.equals("J9")){
			
				numAleatorios.remove(i);
			}
		}

		Collections.shuffle((List<?>) numAleatorios);
	
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsComputador){
				if(b.getText().equals(posicao)){
					
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					String p3 = btnsJogador.get(pos+2).getText();

					
					System.out.println("posições navio-PC: "+p1+" -" +p2+" - "+p3 );
					
					
					if(checagemPosicao(1,p1,p2,p3,"")==true){

						adicionado = true;
															
						posicoesComputador.add(b.getText());
						posicoesComputador.add(btnsJogador.get(pos+1).getText());
						posicoesComputador.add(btnsJogador.get(pos+2).getText());
								
						break;
					}
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}

		}
	}
	
	public void gerarPortaAviaoPc(){
		
		Collections.sort(numAleatorios);
		
		for(int i = 1;i<numAleatorios.size();i++){
			
			String botao = numAleatorios.get(i);
		
			if(botao.equals("A8") || botao.equals("B8") || botao.equals("C8") || 
					botao.equals("D8") || botao.equals("E8") || botao.equals("F8") || 
					botao.equals("G8") || botao.equals("H8") || botao.equals("I8") || botao.equals("J8")){
			
				numAleatorios.remove(i);
			}
		}
		Collections.shuffle((List<?>) numAleatorios);
		
		boolean adicionado = false;
		for(int i =0;i<numAleatorios.size();i++){
			
			String posicao = numAleatorios.get(i);
			
			int pos = 0;
			for(JButton b : btnsComputador){
				if(b.getText().equals(posicao)){
					
					String p1 = b.getText();
					String p2 = btnsJogador.get(pos+1).getText();
					String p3 = btnsJogador.get(pos+2).getText();
					String p4 = btnsJogador.get(pos+3).getText();
					
					System.out.println("posições barcao-PC: "+p1+" -" +p2+" - "+p3+" - "+p4 );
					
					
					if(checagemPosicao(0,p1,p2,p3,p4)==true){
						
						adicionado = true;
						posicoesComputador.add(b.getText());
						posicoesComputador.add(btnsJogador.get(pos+1).getText());
						posicoesComputador.add(btnsJogador.get(pos+2).getText());
						posicoesComputador.add(btnsJogador.get(pos+3).getText());
									
						break;
					}
				}
				pos++;				
			}
			if(adicionado==true){
				break;
			}
		}
		
	}

	private JButton getBtnGerar() {
		if (btnGerar == null) {
			btnGerar = new JButton("Gerar");
			btnGerar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					gerarJogoJogador();

				
					System.out.println("numAleatorios: "+numAleatorios.size());
					System.out.println("JogadasComutador: "+jogadasComputador.size());
				}
			});
			btnGerar.setBounds(335, 668, 197, 23);
		}
		if(jogoDefinido==true){
			btnGerar.setVisible(false);
		}
		return btnGerar;
	}

	private JButton getBtnSair() {
		if (btnSair == null) {
			btnSair = new JButton("Sair");
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sair("Encerrar?");
				}
			});
			btnSair.setBounds(0, 668, 120, 23);
		}
		
		return btnSair;
	}
	

	private JButton getBtnDica() {
		if (btnDica == null) {
			btnDica = new JButton("Dica ("+contDicas+")");
			btnDica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					btnDica.setEnabled(false);
					dica=true;
					contDicas = contDicas-1;
					btnDica.setText("Dica ("+contDicas+")");

				}
			});
			btnDica.setBounds(1095, 668, 105, 23);
		}
		
		return btnDica;
	}

	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("Start");

			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println(portaAviao.getPortaAviao().getBounds().x);
					
					if(submarino.getSubmarino().getBounds().y == 605 || jato.getJato().getBounds().y == 605 || 
							escolta.getNavio().getBounds().y == 605 || portaAviao.getPortaAviao().getBounds().y == 605 ){
						
						JOptionPane.showMessageDialog(null,"Veiculo fora de Posição!");
					
					}else{
						
						lblPtsJogador.setVisible(true);
						
						setBounds(100, 100, 1200, 720);
						setLocationRelativeTo(null);
						
						getPanelComputador().setVisible(true);
						
						if(posicoesComputador.size()==0){
							gerarSubPc();
							gerarAviaoPc();
							gerarNavioPc();
							gerarPortaAviaoPc();
						}
						
						gerarJogadasComp();
						
						btnStart.setVisible(false);
						btnGerar.setVisible(false);
						
						gravarPartida();
					}

				
				}
			});
			btnStart.setBounds(130, 668, 197, 23);
		}
		return btnStart;
	}
	
	public void sair(String menssagem){
				
		Object[] options = { "Reiniciar jogo", "Novo Jogo", "Fechar"};
		int choice = JOptionPane.showOptionDialog(null,menssagem, "Batalha Naval?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
	      
	      if (choice == JOptionPane.YES_OPTION){
	    	  
	    	  System.out.println("Reiniciar");
	    	  BatalhaNaval batalha = new BatalhaNaval(true);
				//batalha.carregarPartida();
				batalha.ReiniciarJogo();
				dispose();
	    		
	      }
	      if (choice == JOptionPane.NO_OPTION){
	    	  System.out.println("Novo");
	    		dispose();
				new TelaBemVindo();
		  }
	      if (choice == JOptionPane.CANCEL_OPTION){
	    	  System.out.println("Fechar");
	    	  System.exit(0);
		  }
	}
	
	public void tiroEmbarcacao(String pos,List<JButton> btns,int vez){
	
			int index =0;
			
			for(JButton b : btns){
				index++;

				if(veiculo.equals("sub")){
					if(b.getText().equals(pos)){
						JButton but =null;	
						try{
							but = btns.get(index-1);
							verificaAcerto(but,vez);
	
						}catch (Exception e) {
							System.out.println("===err");
						}
							
					}
					
				}
				
				if(veiculo.equals("barco")){
					if(b.getText().equals(pos)){
						JButton but =null;	
						try{						
							but= btns.get(index-1);
							verificaAcerto(but,vez);

							if(but.getText().equals("A10") || but.getText().equals("B10") || but.getText().equals("C10") || 
									but.getText().equals("D10") || but.getText().equals("E10") || but.getText().equals("F10") || 
									but.getText().equals("G10") || but.getText().equals("H10") || but.getText().equals("I10") || 
									but.getText().equals("J10")){
								
							}else{
								try{
									but = btns.get(index);
									verificaAcerto(but,vez);
	
								}catch (Exception e) {
									System.out.println("===err");
								}
							}
						}catch (Exception e) {
							System.out.println("===err");
						}
							
					}
				
				}
				
				if(veiculo.equals("caça")){
					if(b.getText().equals(pos)){
						JButton but =null;	
						try{						
							but= btns.get(index-1);
							verificaAcerto(but,vez);
							
						
							if(but.getText().equals("A10") || but.getText().equals("B10") || but.getText().equals("C10") || 
									but.getText().equals("D10") || but.getText().equals("E10") || but.getText().equals("F10") || 
									but.getText().equals("G10") || but.getText().equals("H10") || but.getText().equals("I10") || 
									but.getText().equals("J10")){
								
									System.out.println("but: OP!!!!!!!!!!!!");
								
							}else{
								try{
									but = btns.get(index);
									verificaAcerto(but,vez);
									
									
	
								}catch (Exception e) {
									System.out.println("===err");
								}
							}
							
						}catch (Exception e) {
							System.out.println("===err");
						}
						try{						
							but= btns.get(index-11);
							verificaAcerto(but,vez);
						}catch (Exception e) {
							System.out.println("===err");
						}
						
						try{						
							but = btns.get(index+9);
							verificaAcerto(but,vez);
						}catch (Exception e) {
							System.out.println("===err");
						}
						try{						
							
							if(but.getText().equals("A1") || but.getText().equals("B1") || but.getText().equals("C1") || 
									but.getText().equals("D1") || but.getText().equals("E1") || but.getText().equals("F1") || 
									but.getText().equals("G1") || but.getText().equals("H1") || but.getText().equals("I1") || 
									but.getText().equals("J1") ){
								
							}else{
								but = btns.get(index-2);
								verificaAcerto(but,vez);
							}
						}catch (Exception e) {
							System.out.println("===err");
						}
											
					}
					
				}
					
			}
			veiculo="";

	}
	public void verificaAcerto(JButton btn,int vez){
		
		String posicao = btn.getText();
		
		if(vez==1){
			if(posicoesComputador.contains(posicao)){
				
				System.out.println( "Jogador Acertou");
				btn.setEnabled(false);
				
				iconErro.setImage(iconErro.getImage().getScaledInstance(50, 50, 100));
				
				btn.setIcon(iconErro);
				
				ptsJogador++;
				
				lblPtsJogador.setText("Você: 0"+ptsJogador);
				
				
				if(posicoesComputador.contains(btn.getText())) {//remove as possiçoes que ja foram atingidas do computador para nao acertala novamente....
					   int index = posicoesComputador.indexOf(btn.getText());
					   posicoesComputador.remove(index);
				}
												
			}else{
				System.out.println( "Jogador Errou");
				
				btn.setEnabled(false);
				
			}
		}
		if(vez==0){
			
			JLabel Boom = new JLabel("New label");
						
			Boom.setIcon(icoMira);
			Boom.setBounds(btn.getLocation().x+60, btn.getLocation().y+70, 50, 50);
					
			if(posicoesJogador.contains(btn.getText())){
				System.out.println( "Computador acertou");
		
				Boom.setIcon(icoBoom);
							
				ptsComputador++;
				lblPtsComputador.setText("Inimigo: 0"+ptsComputador);
				
				if(posicoesJogador.contains(btn.getText())) {
					   int index = posicoesJogador.indexOf(btn.getText());
					   posicoesJogador.remove(index);
					   
					   jogadasComputador.remove(index);//--------------------------------------------------------
					}
			}
						
			contentPane.add(Boom);
			contentPane.add(jato.getJato());
			contentPane.add(submarino.getSubmarino());
						
			contentPane.add(escolta.getNavio());
			contentPane.add(portaAviao.getPortaAviao());
						
			contentPane.add(getPanelJogador());
						
			contentPane.add(getLblFundo());
						
			repaint();
			
		}
		
	}

	public void gravarPartida(){
		
		try{
	
			File arq = new File("ultimaPartida.txt");
			arq.createNewFile();
			
			
			FileWriter fileWriter = new FileWriter(arq, false);
	        PrintWriter printWriter = new PrintWriter(fileWriter);
	        
	        for(int i=0;i<posicoesJogador.size();i++){
	        	printWriter.print( posicoesJogador.get(i)+";" );
	        }
	        printWriter.println();
	        for(int i=0;i<posicoesComputador.size();i++){
	        	printWriter.print( posicoesComputador.get(i)+";" );
	        }

	        printWriter.flush();
	        printWriter.close();
	        
	        System.out.println("GRAVADO!");
	        
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}
	public void ReiniciarJogo(){

		try{
			
			FileReader arq = new FileReader("ultimaPartida.txt"); 
			BufferedReader lerArq = new BufferedReader(arq); 
		    
		    String s = lerArq.readLine();
		    
		     while (s != null) {
		    	 String[] msg = s.split(";");
	              
		    	 if(posicoesJogador.size()<11){
		    		 posicoesJogador.add( msg[0] );
			    	 posicoesJogador.add( msg[1] );
			    	 posicoesJogador.add( msg[2] );
			    	 posicoesJogador.add( msg[3] );
			    	 posicoesJogador.add( msg[4] );
			    	 posicoesJogador.add( msg[5] );
			    	 posicoesJogador.add( msg[6] );
			    	 posicoesJogador.add( msg[7] );
			    	 posicoesJogador.add( msg[8] );
			    	 posicoesJogador.add( msg[9] );
			    	 posicoesJogador.add( msg[10] );
		    	 }else{
		    		 posicoesComputador.add( msg[0] );
		    		 posicoesComputador.add( msg[1] );
		    		 posicoesComputador.add( msg[2] );
		    		 posicoesComputador.add( msg[3] );
		    		 posicoesComputador.add( msg[4] );
		    		 posicoesComputador.add( msg[5] );
		    		 posicoesComputador.add( msg[6] );
		    		 posicoesComputador.add( msg[7] );
		    		 posicoesComputador.add( msg[8] );
		    		 posicoesComputador.add( msg[9] );
		    		 posicoesComputador.add( msg[10] );
		    	 }

		    	 s = lerArq.readLine();

		    	 System.out.println("P: "+posicoesJogador.size());
		    	 System.out.println("PC: "+posicoesComputador.size());
		    	
		     }
		     
		   //Gera submarino
			int pos = 0;
			for(JButton b : btnsJogador){
					
				if(b.getText().equals(posicoesJogador.get(0))){
						
					submarino.setSubmarino(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(submarino.getSubmarino());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
											
					submarinoCriado=true;
					veiculo = "";
												
					
					submarino.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
			//Gera jato
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicoesJogador.get(2))){
						
					jato.setJato(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(jato.getJato());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					aviaoCriado=true;
					veiculo = "";
				
					jato.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
		     
			//Gera NAvio
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicoesJogador.get(4))){
						
					escolta.setNavio(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(escolta.getNavio());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					navioCriado=true;
					veiculo = "";
								
					escolta.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
		     
		    //Gera portaAviao
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(posicoesJogador.get(7))){
						
					portaAviao.setPortaAviao(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(portaAviao.getPortaAviao());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					portaAviaoCriado=true;
					veiculo = "";

					portaAviao.setPosicao(b.getText());
					break;

				}
				pos++;				
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	
	}
	
	public List<String> carregarPartida(){
		
		List<String> lista = new ArrayList<String>();

		try{
			
			FileReader arq = new FileReader("MinhaPartida.txt"); 
			BufferedReader lerArq = new BufferedReader(arq); 
		    
		    String s = lerArq.readLine();
		    		   
		     while (s != null) {
		    	  String[] msg = s.split(" ");

                 String qtd = msg[0];
                 String posicao = msg[1];
                   
		    	 lista.add( posicao );

		    	 s = lerArq.readLine();	
		     }
		     
		   //Gera submarino
			int pos = 0;
			for(JButton b : btnsJogador){
					
				if(b.getText().equals(lista.get(0))){
						
					submarino.setSubmarino(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(submarino.getSubmarino());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
											
					submarinoCriado=true;
					veiculo = "";
												
					posicoesJogador.add(b.getText());
					posicoesJogador.add(btnsJogador.get(pos+1).getText());

					submarino.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
			
			//Gera jato
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(lista.get(1))){
						
					jato.setJato(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(jato.getJato());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					aviaoCriado=true;
					veiculo = "";
											
					posicoesJogador.add(b.getText());
					posicoesJogador.add(btnsJogador.get(pos+1).getText());
				
					System.out.println("arquivo: "+lista);
					System.out.println("posicoesJogador "+posicoesJogador.size()+ " - " +posicoesJogador);
				
					jato.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
		     
		     
			//Gera NAvio
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(lista.get(2))){
						
					escolta.setNavio(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(escolta.getNavio());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					navioCriado=true;
					veiculo = "";
											
					posicoesJogador.add(b.getText());
					posicoesJogador.add(btnsJogador.get(pos+1).getText());
					posicoesJogador.add(btnsJogador.get(pos+2).getText());
				
					System.out.println("arquivo: "+lista);
					System.out.println("posicoesJogador "+posicoesJogador.size()+ " - " +posicoesJogador);
				
					escolta.setPosicao(b.getText());
					
					break;

				}
				pos++;				
			}
		     
		    //Gera portaAviao
			pos = 0;
			for(JButton b : btnsJogador){
				if(b.getText().equals(lista.get(3))){
						
					portaAviao.setPortaAviao(b.getLocation().x +60, +b.getLocation().y +70);
					contentPane.add(portaAviao.getPortaAviao());
					contentPane.add(getPanelJogador());
					contentPane.add(getLblFundo());
					repaint();
										
					portaAviaoCriado=true;
					veiculo = "";
											
					posicoesJogador.add(b.getText());
					posicoesJogador.add(btnsJogador.get(pos+1).getText());
					posicoesJogador.add(btnsJogador.get(pos+2).getText());
					posicoesJogador.add(btnsJogador.get(pos+3).getText());
				
					System.out.println("arquivo: "+lista);
					System.out.println("posicoesJogador "+posicoesJogador.size()+ " - " +posicoesJogador);
				
					portaAviao.setPosicao(b.getText());
					break;

				}
				pos++;				
			}



		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Arquivo: MinhaPartida.txt não encontrado ou Invalido!");
			
			dispose();
			new TelaBemVindo();
		}
		return lista;
	}
	private JRadioButton getRdbtnSubmarino() {
		if (rdbtnSubmarino == null) {
			rdbtnSubmarino = new JRadioButton("Submarino");
			rdbtnSubmarino.setActionCommand("sub");
			rdbtnSubmarino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					veiculo = "sub";
					System.out.println(veiculo);
					
					
				}
			});
			rdbtnSubmarino.setBounds(638, 668, 109, 23);
		}
		return rdbtnSubmarino;
	}
	private JRadioButton getRdbtnEscolta() {
		if (rdbtnEscolta == null) {
			rdbtnEscolta = new JRadioButton("Escolta");
			rdbtnEscolta.setActionCommand("barco");
			rdbtnEscolta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					veiculo = "barco";
					System.out.println(veiculo);
				
				}
			});
			rdbtnEscolta.setBounds(744, 668, 109, 23);
		}
		return rdbtnEscolta;
	}
	private JRadioButton getRdbtnCaa() {
		if (rdbtnCaa == null) {
			rdbtnCaa = new JRadioButton("Ca\u00E7a");
			rdbtnCaa.setActionCommand("ca\u00E7a");
			rdbtnCaa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					veiculo = "caça";
					System.out.println(veiculo);
					
				}
			});
			rdbtnCaa.setBounds(851, 668, 109, 23);
		}
		return rdbtnCaa;
	}

}
