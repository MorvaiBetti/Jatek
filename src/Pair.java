public class Pair {
	public int model;
	public QuixoBoard table;
	
	public Pair(int model, QuixoBoard qt){
		setModel(model);
		setTable(qt);
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

	public String toString() {
		return "Pair [model=" + model + ", table=" + table + "]";
	}	
}