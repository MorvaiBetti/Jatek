public class Moho extends Tree{
	
	public Move nextMove(Move prevStep, long oTime) {
		Pair p=new Pair(color, table);
		tree = new TreeStructure(p, 1);
		
		
		return null;
	}
	
	public Move max(){
		int i;
		maxValue=-1000;
		
		for(i=tree.root.children.size(); i>=0; i--){
		
		}
		
		/*for(int i=0; i<25*5; i++){
			if(values[i]>maxValue){
				step=steps[i];
				maxValue=values[i];
			}
		}
		System.out.println("max: "+maxValue+" lepes: "+step);*/
		return step;
	}
	

}
