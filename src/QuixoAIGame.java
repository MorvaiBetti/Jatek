public class QuixoAIGame {
	
	/*Mesterseges intelligenciak egymas elleni jateka. Ha valamelyik csal, akkor a masik nyeri a jatekot*/
	public QuixoAIGame(String a, String b){}
	
	public static void main(String[] args) throws Exception{
		final long maxIdo = Long.parseLong(args[0]); // game time / player
		QuixoBoard t=new QuixoBoard();	

		Move m = null; 								//elozo lepes lementese
		int i=1; 											//hanyadik lepesnel tart
		int ind, next_ind;
			
		//ket random jatekos
		PlayerThread pt[]=new PlayerThread[2];
		pt[0]=new PlayerThread(1, maxIdo, "RandomPlayer");
		pt[1]=new PlayerThread(2, maxIdo, "CheatRandomPlayer");
		
		pt[0].start();
		pt[0].adatok(1, maxIdo);
		pt[0].beallitTablat(t);
			
		
		pt[1].start();
		pt[1].adatok(2, maxIdo);
		pt[1].beallitTablat(t);
		
		t.kiir();
		System.out.println("Idők: " + pt[0].getElteltIdo() + "\t" + pt[1].getElteltIdo());
		while(maxIdo > pt[0].getElteltIdo() && maxIdo > pt[1].getElteltIdo()){
			i ++;
			ind = i % 2;
			next_ind = (i+1) % 2;
			
			System.out.println("\n----------------------------------");
	    System.out.println(ind + " játékos lép, idők: " + pt[0].getElteltIdo() + "\t" + pt[1].getElteltIdo());
	    System.out.println(ind + " játékos lép, állapotok: " + pt[0].allapot + "\t" + pt[1].allapot);
	    
			m = pt[ind].kovLepes(m, pt[next_ind].getElteltIdo());
			//System.out.println("\n"+i+". lepes ("+ind+") | "+ m +" ido: "+pt[ind].getElteltIdo()+" szinem "+pt[ind].getSzin()+" || "+pt[ind].sorrend+" nevem "+pt[ind].jatekosNeve);
			
			if (m != null) {
  			// 'lejárt-e valaki ideje?' ellenőrzése
  			if (pt[ind].getElteltIdo() > maxIdo) {
  			  if(pt[ind].getSzin()==QuixoBoard.X){
            System.out.println("X ideje lejárt, ezért O nyert!");
            break;
          } else {
            System.out.println("O ideje lejárt, ezért X nyert!");
            break;
          }
  			}
  			
  			// 'csalt-e valaki?' ellenőrzése
  			if(t.szabalyos(m.x, m.y, pt[ind].getSzin(), m.xn, m.yn)){ 			
  				t.lepestVegrehajt(m.x, m.y, pt[ind].sorrend, m.xn, m.yn);
  			} else {
  				if(pt[ind].getSzin()==QuixoBoard.X){
  					System.out.println("X csalni pr�b�lt, az�rt O nyert!");
  					break;
  				} else {
  					System.out.println("O csalni pr�b�lt, az�rt X nyert!");
  					break;
  				}
  		  }
  			t.kiir();
  			// 'nyert-e valaki?' ellenőrzése
  			if(t.nyert(pt[ind].getSzin())){
  				if(pt[ind].getSzin()==1){
  					System.out.println("X nyert!");
  					break;
  				} else {
  					System.out.println("O nyert!");
  					break;
  				}
  			}else if(t.nyert(pt[next_ind].getSzin())){
  				if(pt[next_ind].getSzin()==1){
  					System.out.println("X nyert!");
  					break;
  				} else {
  					System.out.println("O nyert!");
  					break;
  				}
  			}
			} else {
			  // ind jatékos null-t lépett => lépés kényszer miatt kikapott
			  if(pt[ind].getSzin()==QuixoBoard.X){
          System.out.println("X nem lépett, ezért O nyert!");
          break;
        } else {
          System.out.println("O nem lépett, ezért X nyert!");
          break;
        }
			}
		}
			
		// exit
		pt[0].kilep();
		pt[1].kilep();
		    
	    // wait for closing threads
		pt[0].join();
	  pt[1].join();
	}
}