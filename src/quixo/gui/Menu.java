package quixo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar implements ActionListener{

	private static final long serialVersionUID = 4188805846572520473L;
	private QuixoGui gui;

	public Menu(QuixoGui gui){
        super();
        this.gui = gui;
        createMenu(Labels.game);
		createMenu(Labels.back);
		createMenu(Labels.moves);
		createMenu(Labels.rules);
    }
       
    private void createMenu(String name){
        JButton menu;
        menu = new JButton(name);
        menu.addActionListener(this);
        this.add(menu);
       
    }
 
	public void actionPerformed(ActionEvent arg0) {
	//	System.out.println("A következõ menüt meghívta a felhasználó:" + arg0.getActionCommand());
		if(arg0.getActionCommand().equals(Labels.game)){
			QuixoTableView play=new QuixoTableView();
			play.display(gui.getWindow());		
			gui.getWindow().setVisible(true);
		}
	}
}
