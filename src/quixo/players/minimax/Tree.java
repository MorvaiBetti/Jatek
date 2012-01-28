package quixo.players.minimax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.players.SimplePlayer;

public class Tree extends SimplePlayer{
		public TreeStructure tree=null;
		public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		/**@depth milyen melysegig vizsgalja a jatekfat*/
		public int depth;
		/**@maxNode kovetkezo lepes*/
		public Node maxNode=null;
		
		public void setDepth(int d){
			depth=d;
		}
		public Tree() throws NumberFormatException, IOException{
			System.out.println("Melyseg??");
			setDepth(Integer.parseInt(reader.readLine()));
		}
		
		public Move nextMove(Move prevStep) {

			if(prevStep!=null){
				table.makeStep(prevStep, (color+1)%2);
			}
				
			Pair p=new Pair(getColor(), (QuixoBoard)table.clone());
			tree = new TreeStructure(p, depth, null, getColor());
			maxNode=tree.maxNode;
			
			table.makeStep(maxNode.step, color);
			return maxNode.step;
		}
}