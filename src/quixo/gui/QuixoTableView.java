package quixo.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.players.Human;



public class QuixoTableView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame window;
	
	private QuixoBoard board;
	private Field[][] fields;
	private ArrayList<Field> selectedFields;
	private Human[] players;
	private int index;
    private Container contentPane = getContentPane();
    private GridLayout gridLayout = new GridLayout(5, 5);

    
	private Color selected=new Color(255, 0, 255);
	
	public QuixoTableView(){
		board=new QuixoBoard();
		fields=new Field[5][5];
		players=new Human[2];
		players[0]=new Human(0);
		players[1]=new Human(1);
		index=0;
		int k=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				fields[i][j]=new Field(i,j);
				fields[i][j].setId(k);
				k++;
			}
		}
		System.out.println(board.toString());
		selectedFields=new ArrayList<Field>();
		contentPane.setLayout(gridLayout);
	}
	
	public void display(JFrame w){
		 window=w;
	     for (int i=0; i<5; i++){
	     	for(int j=0; j<5; j++){
	       		fields[i][j].setColor(board.getField(i, j));
	       		System.out.println(board.getField(i, j));
	       		fields[i][j].addActionListener(this);
	       		contentPane.add(fields[i][j]);
	       	}
	     }
	     window.add(contentPane);
	}
	 
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public void actionPerformed(ActionEvent event){
		Field source=(Field) event.getSource();
		if(selectedFields.size()<2){
			source.setPrevColor(source.getColor());
			source.setBackground(selected);
			selectedFields.add(source);
		}
		if(selectedFields.size()==2){
			Move move=new Move(selectedFields.get(0).getM(), selectedFields.get(0).getN(), selectedFields.get(1).getM(), selectedFields.get(1).getN());
			if(board.legal(move.getX(), move.getY(), index%2, move.getNx(), move.getNy())){
				players[index%2].makeStep(move);
				board.makeStep(move, index%2);
				selectedFields.clear();
				for (int i=0; i<5; i++){
			     	for(int j=0; j<5; j++){
			       		fields[i][j].setColor(board.getField(i, j));
			       	}
			     }
				repaint();
				if(board.win(players[index%2].getColor())){
					System.out.println("Grat");
					for(int i=0; i<5; i++){
						for(int j=0; j<5; j++){
				       		fields[i][j].removeActionListener(this);
				       	}
					}
				}
				index++;
			}else {
				for(Field f:selectedFields){
					f.setBackground(f.getPrevColor());
				}
				selectedFields.clear();
			}	
		}
	}
	
	
	/*public static void main(String[] args){
		JFrame w=new JFrame();
		QuixoTableView q=new QuixoTableView();
		System.out.println(q.board.toString());
		q.display(w);
	}*/

}
