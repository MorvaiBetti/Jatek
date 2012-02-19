package quixo.engine;
public class Move {
	
	/**honnan veszem el a babut*/
	/**@x a tabla x koordinataja, ahonnan a babut elveszem*/
	int x;
	/**@y a tabla y koordinataja, ahonnan a babut elveszem*/
	int y;
	
	/**hova teszem a babut*/
	/**@nx a tabla x koordinataja, ahova a babut teszem*/
	int nx;
	/**@ny a tabla y koordinataja, ahova a babut teszem*/
	int ny;
	
	int prevModel;
	
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
