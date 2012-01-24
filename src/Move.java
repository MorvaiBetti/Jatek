public class Move {
	
	/**honnan veszem el a babut*/
	/**@x a tabla x koordinataja, ahonnan a babut elveszem*/
	public final int x;
	/**@y a tabla y koordinataja, ahonnan a babut elveszem*/
	public final int y;
	
	/**hova teszem a babut*/
	/**@nx a tabla x koordinataja, ahova a babut teszem*/
	public final int nx;
	/**@ny a tabla y koordinataja, ahova a babut teszem*/
	public final int ny;
	
	public Move(int x, int y, int nx, int ny){
		this.x=x;
		this.y=y;
		this.nx=nx;
		this.ny=ny;
	}
	
	public String toString(){
		return x +" "+ y +" "+ nx +" "+ ny;
	};

}
