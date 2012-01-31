package quixo.players.minimax;

import quixo.engine.QuixoBoard;

public class Pair {
	/**@model a kovetkezo lepes szine*/
	public int model;
	/**az aktualis tabla, ami mar tartalmazza az aktualis csucshoz vezeto lepest*/
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