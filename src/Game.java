import java.io.*;

public class Game{
	public static QuixoBoard t=new QuixoBoard();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	public static int[] m;
	public static int i;
	public static String name;
	public static Player p1;
	public static Player p2;
	public static PlayerThread[] pt=new PlayerThread[2];
	
	public static void player(int i, long maxTime)throws Exception{
		try {
			System.out.println("Kerem a(z) "+i+" jatekos tipusat: ");
			name=reader.readLine();
			if(name.equalsIgnoreCase("RandomPlayer")){
				pt[i-1]=new PlayerThread(i, maxTime, "RandomPlayer");
			}else if(name.equalsIgnoreCase("CheatRandomPlayer")){
					pt[i-1]=new PlayerThread(i, maxTime, "CheatRandomPlayer");
				}else if(name.equalsIgnoreCase("Human")){
						pt[i-1]=new PlayerThread(i, maxTime, "Human");
					}else {player(i, maxTime);
						return;
					}
			pt[i-1].start();
			pt[i-1].datas(i, maxTime);
			pt[i-1].setTable(t);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		final long maxTime = Long.parseLong(args[0]); // game time / player
		int ind, next_ind;
		int i=0;
		Move m=null;
		
		System.out.println("Lehetseges jatekosok:");
		System.out.println("Human: Emberi jatekos");
		System.out.println("RandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest.");
		System.out.println("CheatRandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest, elofordulhat, hogy csalni probal.");
		System.out.println();
		
		try {
			player(1, maxTime);
			player(2, maxTime);
			System.out.println(t);
			while(!t.win(pt[0].getColor()) && !t.win(pt[1].getColor())){
				System.out.println("----------------------------------");
				
				ind=i%2;
				next_ind=(i+1)%2;
	
				m = pt[ind].nextMove(m, pt[next_ind].getElapsedTime());
				System.out.println("\n"+i+". lepes "+ind+". jatekos lep | "+ m +" ido: "+pt[ind].getElapsedTime()+" szinem: "+pt[ind].getColor()+" || "+pt[ind].sequence+" nevem "+pt[ind].playerName);
				if (m != null) {
				   // 'lejárt-e valaki ideje?' ellenõrzése
				   if (pt[ind].getElapsedTime() > maxTime) {
					   if(pt[ind].getColor()==QuixoBoard.X){
					            System.out.println("X ideje lejárt, ezért O nyert!");
					            break;
					          } else {
					            System.out.println("O ideje lejárt, ezért X nyert!");
					            break;
					          }
				   }
				   
				   // 'csalt-e valaki?' ellenõrzése
				   if(t.legal(m.x, m.y, pt[ind].getColor(), m.nx, m.ny)){
					   t.makeStep(m.x, m.y, pt[ind].getColor(), m.nx, m.ny);
				   } else {
					   if(pt[ind].getColor()==QuixoBoard.X){
						   	System.out.println("X csalni probalt, azert O nyert!");
						   	break;
				   } else {
					   System.out.println("O csalni probalt, azert X nyert!");
					   break;
				   		}
				   }
				   System.out.println(t);
				   
				  // 'nyert-e valaki?' ellenõrzése
				   if(t.win(pt[ind].getColor())){
					   if(pt[ind].getColor()==QuixoBoard.X){
						   System.out.println("X nyert!");
						   break;
					   } else {
						   System.out.println("O nyert!");
						   break;
					   }
				   }else if(t.win(pt[next_ind].getColor())){
					   		if(pt[next_ind].getColor()==QuixoBoard.X){
					   			System.out.println("X nyert!");
					   			break;
					   		} else {
					   			System.out.println("O nyert!");
					   			break;
					   		}
				   		}
				} else {
					// ind jatékos null-t lépett => lépés kényszer miatt kikapott
					if(pt[ind].getColor()==QuixoBoard.X){
						System.out.println("X nem lépett, ezért O nyert!");
				        break;
				    } else {
				          System.out.println("O nem lépett, ezért X nyert!");
				          break;
				        }
					}
				i++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
