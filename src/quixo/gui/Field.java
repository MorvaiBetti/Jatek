package quixo.gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Field extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private int m;
    private int n;
	private Color color;
	private Color prevColor;
	private Color bgO = new Color(255,0,0); //red
    private Color bgX  = new Color(0,0,255); //blue
    private Color bgEmpty =new Color(255,255,255);

    
    public Field(int x, int y){
    	super();
    	this.setM(x);
    	this.setN(y);
    }
    
	public void setM(int m) {
		this.m = m;
	}

	public int getM() {
		return m;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
    
	public Color getColor() {
		return color;
	}
	
	public void setPrevColor(Color prevColor) {
		this.prevColor = prevColor;
	}

	public Color getPrevColor() {
		return prevColor;
	}

	public void setColor(int c){
		setOpaque(true);
		if(c==1){
			setBackground(bgO);
			color=bgO;
		}else{if(c==0){
					setBackground(bgX);
					color=bgX;
				}else {
					if(c==2){
						setBackground(bgEmpty);
						color=bgEmpty;
					}
				}
		}
		setHorizontalAlignment( SwingConstants.CENTER );
	}


	
}
