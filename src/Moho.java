public class Moho extends Tree{
	public int maxValue;
	
	public Move nextMove(Move prevStep, long oTime) {
		emptyAll();
		id=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				nextStep(i, j);
			}
		}
		for(int i=0; i<id; i++){
			System.out.println(i+". lepes: "+steps[i]+" ertek: "+values[i]);
		}
		System.out.println(" id "+id);
		return max();
	}
	
	public Move max(){
		maxValue=-1000;
		for(int i=0; i<25*5; i++){
			if(values[i]>maxValue){
				step=steps[i];
				maxValue=values[i];
			}
		}
		System.out.println("max: "+maxValue+" lepes: "+step);
		return step;
	}
	

}
