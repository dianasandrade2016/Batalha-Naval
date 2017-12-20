package modelo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Escolta {
	
	private JLabel navio=null;
	
	private int x = 240;
	private int y = 605;
	
	private String posicao;

	public Escolta(){
		navio = new JLabel("Navio");
	}
	
	public void setNavio(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public JLabel getNavio() {
					
		navio.setBounds( x, y, 150, 50);
			
		ImageIcon icon = new ImageIcon("img/navio.png");
		icon.setImage(icon.getImage().getScaledInstance(150, 70, 100));
			
		navio.setIcon(icon);
		navio.setFocusable(true);

		
		return navio;
	}
	public void setPosicao(String p){
		posicao = p;
	}
	
	public String getPosicao() {
		return posicao;
	}

}
