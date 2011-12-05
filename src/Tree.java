public abstract class Tree extends SimplePlayer{
	public int id;
	public Move[] steps;
	public int[] values;
	public TreeStructure tree;
	public int my=3;
	public int your=-3;
	public int nobody=1;
	public int[][] fields=new int[25][7];
	public QuixoBoard myTable;
	public int maxValue;
	public Move[] doSteps;
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}
	
/*	public int getColor() {
		return color;
	}
*/	
	public void datas(int sequence, long time) {
		steps=new Move[25*5];
		values=new int[25*5];
		super.datas(sequence, time);
		return;
	}
	

/*	public void makeTree(int depth, int color){
		id=0;
		Pair p=new Pair(color, myTable);
		tree = new TreeStructure(p);
		doSteps=new Move[depth];
		for(int k=0; k<depth; k++){
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					tree.nextStep(i, j, color);
					System.out.println("hol tartok 0/1 : "+color);
				}
			}
		}
		return;
	}
	*/

}
