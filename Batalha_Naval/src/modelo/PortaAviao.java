package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PortaAviao {
	
	private JLabel portaAviao=null;
	
	private int x = 404;
	private int y = 605;
	
	private String posicao;

	public PortaAviao(){
		portaAviao = new JLabel("PortaAviao");
	}
	
	public void setPortaAviao(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public JLabel getPortaAviao() {
					
		portaAviao.setBounds( x, y, 200, 50);
			
		ImageIcon icon = new ImageIcon("img/portaaviao.png");
		icon.setImage(icon.getImage().getScaledInstance(200, 70, 100));
			
		portaAviao.setIcon(icon);
		portaAviao.setFocusable(true);

		
		return portaAviao;
	}
	
	public void setPosicao(String p){
		posicao = p;
	}
	
	public String getPosicao() {
		return posicao;
	}

}
