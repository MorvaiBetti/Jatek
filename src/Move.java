public class Move {
	
	//honnan veszem el a babut
	public final int x;
	public final int y;
	
	//hova teszem a babut
	public final int nx;
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
