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
			if(name.equalsIgnoreCase("RandomPlayer")){
				pt[ai]=new PlayerThread(i, maxTime, "RandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime);
				pt[ai].setTable(t);
				ai++;
				return;
			}else if(name.equalsIgnoreCase("CheatRandomPlayer")){
					pt[ai]=new PlayerThread(i, maxTime, "CheatRandomPlayer");
					pt[ai].start();
					pt[ai].datas(i, maxTime);
					pt[ai].setTable(t);
					ai++;
					return;
				}else if(name.equalsIgnoreCase("Human")){
						p[people]=new Human();
						p[people].datas(i, maxTime);
						p[people].setTable(t);
						people++;
						return;
					}else player(i, maxTime);
							return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void aiStep(int i, long oTime){
		m = pt[i].nextMove(m, oTime);
		System.out.println("\n"+j+". lepes "+ind+". jatekos lep | "+ m +" ido: "+pt[i].getElapsedTime()+" szinem: "+pt[i].getColor()+" || "+pt[ind].sequence+" nevem "+pt[i].playerName);
		if (m != null) {
		   // 'lejárt-e az ideje?' ellenõrzése
		   if (pt[i].getElapsedTime() > maxTime) {
			   if(pt[i].getColor()==QuixoBoard.X){
			            System.out.println("X ideje lejárt, ezért O nyert!");
			            return;
			          } else {
			            System.out.println("O ideje lejárt, ezért X nyert!");
			            return;
			          }
		   }
		   
		   // 'csalt-e valaki?' ellenõrzése
		   if(t.legal(m.x, m.y, pt[i].getColor(), m.nx, m.ny)){
			   t.makeStep(m.x, m.y, pt[i].getColor(), m.nx, m.ny);
		   } else {
			   if(pt[i].getColor()==QuixoBoard.X){
				   	System.out.println("X csalni probalt, azert O nyert!");
				   	return;
		   } else {
			   System.out.println("O csalni probalt, azert X nyert!");
			   return;
		   		}
		   }
		   System.out.println(t);

		} else {
			// ind jatékos null-t lépett => lépés kényszer miatt kikapott
			if(pt[i].getColor()==QuixoBoard.X){
				System.out.println("X nem lépett, ezért O nyert!");
				return;
		    } else {
		          System.out.println("O nem lépett, ezért X nyert!");
		          return;
		        }
			}
		return;
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
			
			System.out.println("----------------------------------");
			humanStep(ind);
					   
			// 'nyert-e valaki?' ellenõrzése
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
			aiStep(ind, pt[nextInd].getElapsedTime());
			// 'nyert-e valaki?' ellenõrzése
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
	
	//good
	public static void humanvsAI(){
		if(pt[0].getColor()==QuixoBoard.X){
			while(!t.win(pt[0].getColor()) && !t.win(p[0].getColor())){
				System.out.println("----------------------------------");
				
				aiStep(0, 0);
				//nyert-e?
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
				// 'nyert-e valaki?' ellenõrzése
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
		System.out.println("Human: Emberi jatekos");
		System.out.println("RandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest.");
		System.out.println("CheatRandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest, elofordulhat, hogy csalni probal.");
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