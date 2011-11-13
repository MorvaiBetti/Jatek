import java.io.*;

public class Game{
	public static QuixoBoard t=new QuixoBoard();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	public static Move m;
	public static String name;
	public static Player[] p=new Player[2];
	public static PlayerThread[] pt=new PlayerThread[2];
	public static int j, ai, people, ind, nextInd;
	public static long maxTime;
	
	public static void player(int i, long maxTime)throws Exception{
		try {
			System.out.println("Kerem a(z) "+i+" jatekos tipusat: ");
			name=reader.readLine();
			if(name.equalsIgnoreCase("1")){
				pt[ai]=new PlayerThread(i, maxTime, "RandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("2")){
				pt[ai]=new PlayerThread(i, maxTime, "CheatRandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("3")){
				pt[ai]=new PlayerThread(i, maxTime, "DefendPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("4")){
				pt[ai]=new PlayerThread(i, maxTime, "CollectorPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("5")){
				pt[ai]=new PlayerThread(i, maxTime, "CalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("6")){
				pt[ai]=new PlayerThread(i, maxTime, "DefendCalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("7")){
				pt[ai]=new PlayerThread(i, maxTime, "MohoCalculatPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}
			if(name.equalsIgnoreCase("0")){
				p[people]=new Human();
				p[people].datas(i, maxTime);
				p[people].setTable(t);
				people++;
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean aiStep(int i, long oTime){
		m = pt[i].nextMove(m, oTime);
		System.out.println("\n"+j+". lepes "+ind+". jatekos lep | "+ m +" ido: "+pt[i].getElapsedTime()+" szinem: "+pt[i].getColor()+" || "+pt[ind].sequence+" nevem "+pt[i].playerName);
		if (m != null) {
		   // 'lej�rt-e az ideje?' ellen�rz�se
		   if (pt[i].getElapsedTime() > maxTime) {
			   if(pt[i].getColor()==QuixoBoard.X){
			            System.out.println("X ideje lej�rt, ez�rt O nyert!");
			            return false;
			          } else {
			            System.out.println("O ideje lej�rt, ez�rt X nyert!");
			            return false;
			          }
		   }
		   
		   // 'csalt-e valaki?' ellen�rz�se
		   if(t.legal(m.x, m.y, pt[i].getColor(), m.nx, m.ny)){
			   t.makeStep(m.x, m.y, pt[i].getColor(), m.nx, m.ny);
		   } else {
			   if(pt[i].getColor()==QuixoBoard.X){
				   	System.out.println("X csalni probalt, azert O nyert!");
				   	return false;
		   } else {
			   System.out.println("O csalni probalt, azert X nyert!");
			   return false;
		   		}
		   }
		   System.out.println(t);

		} else {
			// ind jat�kos null-t l�pett => l�p�s k�nyszer miatt kikapott
			if(pt[i].getColor()==QuixoBoard.X){
				System.out.println("X nem l�pett, ez�rt O nyert!");
				return false;
		    } else {
		          System.out.println("O nem l�pett, ez�rt X nyert!");
		          return false;
		        }
			}
		return true;
	}
	
	public static void humanStep(int i){
		m = p[i].nextMove(m, 0);
		System.out.println("\n"+j+". lepes "+ind+". jatekos lep | "+ m +" szinem: "+p[i].getColor());		   
		t.makeStep(m.x, m.y, p[i].getColor(), m.nx, m.ny);
		System.out.println(t);
		return;
	}
	
	//Human vs Human
	public static void people(){
		while(!t.win(p[0].getColor()) && !t.win(p[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			
		//	System.out.println("----------------------------------");
			humanStep(ind);
					   
			// 'nyert-e valaki?' ellen�rz�se
			if(t.win(p[ind].getColor()) && t.win(p[nextInd].getColor())){
				System.out.println("Dontetlen!");
				return;
			}
			if(t.win(p[ind].getColor())){
			   if(p[ind].getColor()==QuixoBoard.X){
				   System.out.println("X nyert!");
				   return;
			   } else {
				   System.out.println("O nyert!");
				   return;
			   }
			}else if(t.win(p[nextInd].getColor())){
			   		if(p[nextInd].getColor()==QuixoBoard.X){
			   			System.out.println("X nyert!");
			   			return;
			   		} else {
			   			System.out.println("O nyert!");
			   			return;
			   		}
			}
			j++;
		}
	}
	
	//AI vs AI
	public static void ai(){
		while(!t.win(pt[0].getColor()) && !t.win(pt[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			
			System.out.println("----------------------------------");
			if(!aiStep(ind, pt[nextInd].getElapsedTime())){
				return;
			}
			// 'nyert-e valaki?' ellen�rz�se
			if(t.win(pt[ind].getColor()) && t.win(pt[nextInd].getColor())){
				System.out.println("Dontetlen!!");
				return;
			}
			if(t.win(pt[ind].getColor())){
			   if(pt[ind].getColor()==QuixoBoard.X){
				   System.out.println("X nyert!");
				   return;
			   } else {
				   System.out.println("O nyert!");
				   return;
			   }
			}else if(t.win(pt[nextInd].getColor())){
			   		if(pt[nextInd].getColor()==QuixoBoard.X){
			   			System.out.println("X nyert!");
			   			return;
			   		} else {
			   			System.out.println("O nyert!");
			   			return;
			   		}
			}
			j++;
		}
	}
	
	//Human vs AI
	public static void humanvsAI(){
		if(pt[0].getColor()==QuixoBoard.X){
			while(!t.win(pt[0].getColor()) && !t.win(p[0].getColor())){
				System.out.println("----------------------------------");
				
				aiStep(0, 0);
				//nyert-e?
				if(t.win(pt[0].getColor()) && t.win(p[0].getColor())){
					System.out.println("Dontetlen!");
					return;
				}
				if(t.win(pt[0].getColor())){
				   if(pt[0].getColor()==QuixoBoard.X){
					   System.out.println("X nyert!");
					   return;
				   } else {
					   System.out.println("O nyert!");
					   return;
				   }
				}else if(t.win(p[0].getColor())){
						if(p[0].getColor()==QuixoBoard.X){
							System.out.println("X nyert!");
							return;
						} else {
							System.out.println("O nyert!");
							return;
						}
		   		}
				j++;
				humanStep(0);
				// 'nyert-e valaki?' ellen�rz�se
				if(t.win(pt[0].getColor()) && t.win(p[0].getColor())){
					System.out.println("Dontetlen!");
					return;
				}
				if(t.win(p[0].getColor())){
					if(p[0].getColor()==QuixoBoard.X){
						System.out.println("X nyert!");
						return;
					} else {
					   System.out.println("O nyert!");
					   return;
					}
				}else if(t.win(pt[0].getColor())){
						if(pt[0].getColor()==QuixoBoard.X){
							System.out.println("X nyert!");
							return;
						} else {
							System.out.println("O nyert!");
							return;
						}	
				}
				j++;
			}
		}else {
			while(!t.win(pt[0].getColor()) && !t.win(p[0].getColor())){
				System.out.println("----------------------------------");
				humanStep(0);
				
				if(t.win(pt[0].getColor())){
					if(pt[0].getColor()==QuixoBoard.X){
						System.out.println("X nyert!");
						return;
					 } else {
						 System.out.println("O nyert!");
						 return;
					 }
				}else if(t.win(p[0].getColor())){
						if(p[0].getColor()==QuixoBoard.X){
							System.out.println("X nyert!");
							return;
						} else {
							System.out.println("O nyert!");
							return;
						}
				}
				j++;
				aiStep(0, 0);
				if(t.win(p[0].getColor())){
			   		if(p[0].getColor()==QuixoBoard.X){
			   			System.out.println("X nyert!");
			   			return;
			   		} else {
			   			System.out.println("O nyert!");
			   			return;
			   		}
		   		}else if(t.win(pt[0].getColor())){
		   					if(pt[0].getColor()==QuixoBoard.X){
		   						System.out.println("X nyert!");
		   						return;
		   					} else {
		   						System.out.println("O nyert!");
		   						return;
					 }
				}
				j++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		maxTime = Long.parseLong(args[0]); // game time / player
		j=ai=people=0;
		m=null;
		
		System.out.println("Lehetseges jatekosok:");
		System.out.println("0 Human: Emberi jatekos");
		System.out.println("1 RandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest.");
		System.out.println("2 CheatRandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest, elofordulhat, hogy csalni probal.");
		System.out.println("3 DefendPlayer: Ha az ellenfelnek egy babu hianyzik, hogy nyerjen, akkor vedekezik. Egyebkent random lep.");
		System.out.println("4 CollectorPlayer: Ha van r� lehetosege, akkor egy uj babut tesz le, egyebkent random.");
		System.out.println("5 CalculatPlayer: Kiszamol egy tablat, hogy hova mennyire erdemes lepni. Kiirja a tablat, majd randomlep.");
		System.out.println("6 DefendCalculatPlayer: ");
		System.out.println("7 MohoCalculatPlayer: ");
		System.out.println();
		
		try {
			player(1, maxTime);
			player(2, maxTime);
			System.out.println(t);
			
			if(people==2){
				people();
				return;
			}else if(ai==2){
					ai();
					return;
				}else{humanvsAI();
						return;
					}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}