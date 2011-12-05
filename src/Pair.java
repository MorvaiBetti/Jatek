public class Pair {
	public Move step;
	public int model;
	public QuixoBoard table;
	
	public Pair(int model, QuixoBoard qt){
		setModel(model);
		setTable(qt);
	}
	
	public Move getStep() {
		return step;
	}
	public void setStep(Move step) {
		this.step = step;
	}
	
	public QuixoBoard getTable() {
		return table;
	}
	public void setTable(QuixoBoard table) {
		this.table = table;
	}
	
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Pair [model=" + model + ", table=" + table + "]";
	}
	
	
	
}
