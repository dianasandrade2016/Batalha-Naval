package modelo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Jato {
	
	private JLabel jato=null;
	
	private int x = 125;
	private int y = 605;
	
	private String posicao;

	public Jato(){
		jato = new JLabel("Jato");
	}
	
	public void setJato(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public JLabel getJato() {
					
		jato.setBounds( x, y, 100, 50);
			
		ImageIcon icon = new ImageIcon("img/jato.png");
		icon.setImage(icon.getImage().getScaledInstance(100, 70, 100));
			
		jato.setIcon(icon);
		jato.setFocusable(true);

		
		return jato;
	}
	
	public void setPosicao(String p){
		posicao = p;
	}
	
	public String getPosicao() {
		return posicao;
	}

}
