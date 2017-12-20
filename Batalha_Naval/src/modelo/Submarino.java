package modelo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Submarino {
	
	private JLabel submarino=null;
	
	private int x = 10;
	private int y = 605;
	
	private String posicao;

	public Submarino(){
		submarino = new JLabel("Submarino");
	}
	
	public void setSubmarino(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public JLabel getSubmarino() {
					
			submarino.setBounds( x, y, 100, 50);
			
			ImageIcon icon = new ImageIcon("img/submarino.jpg");
			icon.setImage(icon.getImage().getScaledInstance(100, 70, 100));
			
			submarino.setIcon(icon);
			submarino.setFocusable(true);

		
		return submarino;
	}
	public void setPosicao(String p){
		posicao = p;
	}
	
	public String getPosicao() {
		return posicao;
	}

}
