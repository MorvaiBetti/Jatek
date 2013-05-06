package quixo.gui;

import javax.swing.JFrame;

public class QuixoGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame window;
	
	public QuixoGui(){
		window=new JFrame();		
	}
	public JFrame getWindow() {
		return window;
	}
	
	public void startGui(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
				 createAndShowGUI();
	         }
	     });
	}
	 
	 private void createAndShowGUI(){
		 window = new JFrame("Quixo");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	        Menu MenuBar = new Menu(this);
	        window.setJMenuBar(MenuBar);
	        
			window.setSize(600,600);
			window.setVisible(true);
	 }
	 
	public static void main(String[] args){
		 QuixoGui gui=new QuixoGui();
		 gui.startGui();
	}
}
