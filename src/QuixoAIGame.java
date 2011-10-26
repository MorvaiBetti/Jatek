public class QuixoAIGame {

/*Mesterseges intelligenciak egymas elleni jateka. Ha valamelyik csal, akkor a masik nyeri a jatekot*/
public QuixoAIGame(){}
	
	public static void main(String[] args) throws Exception{
	final long maxTime = Long.parseLong(args[0]); // game time / player
	QuixoBoard t=new QuixoBoard();
	
	Move m = null; //elozo lepes lementese
	int i=0; //hanyadik lepesnel tart
	int ind, next_ind;
	
	//ket random jatekos
	PlayerThread pt[]=new PlayerThread[2];
	pt[1]=new PlayerThread(QuixoBoard.X, maxTime, "RandomPlayer");
	pt[0]=new PlayerThread(QuixoBoard.O, maxTime, "CheatRandomPlayer");
	
	pt[0].start();
	pt[0].datas(QuixoBoard.O, maxTime);
	pt[0].setTable(t);
	
	
	pt[1].start();
	pt[1].datas(QuixoBoard.X, maxTime);
	pt[1].setTable(t);
	
	System.out.println(t);
	System.out.println("Idõk: " + pt[0].getElapsedTime() + "\t" + pt[1].getElapsedTime());
	while(maxTime > pt[0].getElapsedTime() && maxTime > pt[1].getElapsedTime()){
		
		i ++;
		ind = i % 2;
		next_ind = (i+1) % 2;
		
		System.out.println("\n----------------------------------");
		//System.out.println(ind + " játékos lép, idõk: " + pt[0].getElapsedTime() + "\t" + pt[1].getElapsedTime());
		//System.out.println(ind + " játékos lép, állapotok: " + pt[0].allapot + "\t" + pt[1].allapot);
		m = pt[ind].nextMove(m, pt[next_ind].getElapsedTime());
		System.out.println("\n"+i+". lepes ("+ind+") | "+ m +" ido: "+pt[ind].getElapsedTime()+" szinem "+pt[ind].getColor()+" || "+pt[ind].sequence+" nevem "+pt[ind].playerName);
	
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
			   t.makeStep(m.x, m.y, pt[ind].sequence, m.nx, m.ny);
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
	}
	
	// exit
	pt[0].exit();
	pt[1].exit();
	
	// wait for closing threads
	pt[0].join();
	pt[1].join();
	}
}