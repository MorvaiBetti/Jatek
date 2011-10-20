public class Move {
	
	//honnan veszem el a babut
	public final int x;
	public final int y;
	
	//hova teszem a babut
	public final int xn;
	public final int yn;
	
	public Move(int x, int y, int xn, int yn){
		this.x=x;
		this.y=y;
		this.xn=xn;
		this.yn=yn;
	}
	
	public String toString(){
		return x +" "+ y +" "+ xn +" "+ yn;
	};

}
