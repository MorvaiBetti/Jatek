package quixo.engine;
public class Move {
	
	/**honnan veszem el a babut*/
	/**@x a tabla x koordinataja, ahonnan a babut elveszem*/
	private int x;
	/**@y a tabla y koordinataja, ahonnan a babut elveszem*/
	private int y;
	
	/**hova teszem a babut*/
	/**@nx a tabla x koordinataja, ahova a babut teszem*/
	private int nx;
	/**@ny a tabla y koordinataja, ahova a babut teszem*/
	private int ny;
	
	private int prevModel;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getNy() {
		return ny;
	}

	public void setNy(int ny) {
		this.ny = ny;
	}

	public Move(int x, int y, int nx, int ny){
		this.x=x;
		this.y=y;
		this.nx=nx;
		this.ny=ny;
	}
	
	public boolean equals(Move m){
		if(x==m.x && y==m.y && nx==m.nx && ny==m.ny){
			return true;
		}
		return false;
	}

	public int getPrevModel() {
		return prevModel;
	}

	public void setPrevModel(int prevModel) {
		this.prevModel = prevModel;
	}
	
	public String toString(){
		return "("+x +" "+ y +" "+ nx +" "+ ny+")";
	};
	/**Tesztelesre*/
/*	public static void main(String[] args) {
		Move m1=new Move(5, 2, 3, 1);
		Move m2=new Move(5, 2, 4, 1);
		System.out.println(m1.as(m2));
	}*/
}
