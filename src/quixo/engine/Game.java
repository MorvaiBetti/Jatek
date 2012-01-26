package quixo.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import quixo.players.Human;

public class Game{
	/**@table jatektabla*/
	public static QuixoBoard table=new QuixoBoard();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	/**@move utoljara megtett lepes*/
	public static Move move;
	/**@name jatekos neve*/
	public static String name;
	public static Player[] p=new Player[2];
	public static PlayerThread[] pt=new PlayerThread[2];
	/**@ai ai-k szama
	 * @people emberek szama
	 * @ind aktualis jatekos szama
	 * @nextInd kovetkezo jatekos szama*/
	public static int j, ai, people, ind, nextInd;
	/**@maxTime jatekosok ideje*/
	public static long maxTime;
	public static int depth;
	
	public static void player(int i, long maxTime)throws Exception{
		try {
			System.out.println("Kerem a(z) "+(i+1)+" jatekos tipusat: ");
			name=reader.readLine();
			if(name.equalsIgnoreCase("1")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.RandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("2")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CheatRandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("3")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.DefendPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("4")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CollectorPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("5")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("6")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.DefendCalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("7")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.MohoCalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("8")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.minimax.Moho");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("9")){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.minimax.Tree");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				System.out.println("Melyseg??");
				depth=Integer.parseInt(reader.readLine());
				pt[ai].setDepth(depth);
				pt[ai].setTable(table);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("0")){
				p[people]=new Human();
				p[people].datas(i, maxTime);
				p[people].setTable(table);
				people++;
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**nyert-e valamelyik jatekos*/
	public static boolean winner(){
		if(table.win(QuixoBoard.X) && table.win(QuixoBoard.O)){
			System.out.println("Dontetlen!");
			return true;
		}
		if(table.win(QuixoBoard.X)){
			System.out.println("X nyert");
			return true;
		}
		if(table.win(QuixoBoard.O)){
			System.out.println("O nyert");
		}
		return false;
	}
	
	/**meghivja az ai nextMove()-jat, ellenorzi az ai lepeset es vegrehajtja azt*/
	public static boolean aiStep(int i, long oTime){
		move = pt[i].nextMove(move);
		System.out.println("\n"+j+". lepes "+ind+". jatekos lepett | "+ move +" ido: "+pt[i].getElapsedTime()+" szinem: "+pt[i].getColor()+" || "+pt[ind].sequence+" nevem "+pt[i].playerName);
		if (move != null) {
		   /** 'lejart-e az ideje?' ellenorzese*/
		   if (pt[i].getElapsedTime() > maxTime) {
			   if(pt[i].getColor()==QuixoBoard.X){
			            System.out.println("X ideje lejart, ezert O nyert!");
			            return false;
			          } else {
			            System.out.println("O ideje lejart, ezert X nyert!");
			            return false;
			          }
		   }
		   
		   /** 'csalt-e valaki?' ellenorzese*/
		   if(table.legal(move.x, move.y, pt[i].getColor(), move.nx, move.ny)){
			   table.makeStep(move.x, move.y, pt[i].getColor(), move.nx, move.ny);
		   } else {
			   if(pt[i].getColor()==QuixoBoard.X){
				   	System.out.println("X csalni probalt, azert O nyert!");
				   	return false;
			   } else {
				   System.out.println("O csalni probalt, azert X nyert!");
				   return false;
		   		}
		   }
		   System.out.println(table);

		} else {
			/** ind jatekos null-t lepett => lepes kenyszer miatt kikapott*/
			if(pt[i].getColor()==QuixoBoard.X){
				System.out.println("X nem lepett, ezert O nyert!");
				return false;
		    } else {
		          System.out.println("O nem lepett, ezert X nyert!");
		          return false;
		        }
			}
		return true;
	}
	
	/**meghivja a jatekos nextMove()-jat es vegrehajtja azt*/
	public static void humanStep(int i){
		move = p[i].nextMove(move);
		System.out.println("\n"+j+". lepes "+ind+". jatekos lepett | "+ move +" szinem: "+p[i].getColor());		   
		table.makeStep(move.x, move.y, p[i].getColor(), move.nx, move.ny);
		System.out.println(table);
		return;
	}
	
	/**Human vs Human*/
	public static void people(){
		while(!table.win(p[0].getColor()) && !table.win(p[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			humanStep(ind);
			winner();
			j++;
		}
	}
	
	/**AI vs AI*/
	public static void ai(){
		while(!table.win(pt[0].getColor()) && !table.win(pt[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			
			System.out.println("----------------------------------");
			if(!aiStep(ind, pt[nextInd].getElapsedTime())){
				return;
			}
			winner();
			j++;
		}
	}
	
	/**Human vs AI*/
	public static void humanvsAI(){
		if(pt[0].getColor()==QuixoBoard.X){
			while(!table.win(pt[0].getColor()) && !table.win(p[0].getColor())){
				System.out.println("----------------------------------");
				
				aiStep(0, 0);
				if(winner()){
					return;
				}
				j++;
				humanStep(0);
				winner();
				j++;
			}
		}else {
			while(!table.win(pt[0].getColor()) && !table.win(p[0].getColor())){
				System.out.println("----------------------------------");
				humanStep(0);
				if(winner()){
					return;
				}
				j++;
				aiStep(0, 0);
				if(winner()){
					return;
				}
				j++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		maxTime = Long.parseLong(args[0]);
		j=ai=people=0;
		move=null;
		
		System.out.println("Lehetseges jatekosok:");
		System.out.println("0 Human: Emberi jatekos");
		System.out.println("1 RandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest.");
		System.out.println("2 CheatRandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest, elofordulhat, hogy csalni probal.");
		System.out.println("3 DefendPlayer: Ha az ellenfelnek egy babu hianyzik, hogy nyerjen, akkor vedekezik. Egyebkent random lep.");
		System.out.println("4 CollectorPlayer: Ha van lehetosege, akkor egy uj babut tesz le, egyebkent random.");
	//	System.out.println("5 CalculatPlayer: Kiszamol egy tablat, hogy hova mennyire erdemes lepni. Kiirja a tablat, majd random lep.");
		System.out.println("6 DefendCalculatPlayer: A legveszelyesebb mezore probal lepni. Azaz probalja minel jobban gatolni az ellenfelet");
		System.out.println("7 MohoCalculatPlayer: A legjobb helyre probal lepni.");
		System.out.println("8 Moho: Egy melysegig nezi a fat.");
		System.out.println("9 Tree: parameterben megadott melysegig vizsgalja a jatekfat.");
		System.out.println();
		
		try {
			player(0, maxTime);
			player(1, maxTime);
			System.out.println(table);
			
			if(people==2){
				people();
				return;
			}else if(ai==2){
					ai();
					pt[0].exit();
					pt[1].exit();
					return;
				}else{humanvsAI();
						pt[0].exit();
						return;
					}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}