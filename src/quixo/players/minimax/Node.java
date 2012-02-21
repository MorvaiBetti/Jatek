package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;

public class Node {
	/**@data a csucspont mintaja es tablaja*/
	protected int model;
	/**@table a csomopont tablaja*/
	protected QuixoBoard table;
	/**@value csucspont erteke*/
	protected double value;
	/**@parent csucspont apja*/
	public Node parent;
	/**@children csucspont fiai*/
	protected ArrayList<Node> children=new ArrayList<Node>();
	/**@leaf csucspont level-e. Level ha nincs fia, ha nyert valaki vagy ha n melyen van*/
	protected boolean leaf;
	/**@index csucspont melysege*/
	protected int index;
	/**@step csucspont lepese*/
	protected Move step;
	
	protected int ind;

	/**Node konstruktora
	 * @param t aktualis tabla
	 * @param p apa
	 * @param s az aktualis csomoponthoz vezeto lepes*/
	public Node(QuixoBoard t, Node p, Move s){
		table=(QuixoBoard) t.clone();
		parent=p;
		step=s;
		if(parent!=null){
			index=parent.getIndex()+1;
			model=(parent.getModel()+1)%2;
		} else {setIndex(0);}
		
		if(table.win(QuixoBoard.O) || table.win(QuixoBoard.X)){
			setLeaf(true);
		}else {setLeaf(false);}
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public QuixoBoard getTable() {
		return table;
	}

	protected void setTable(QuixoBoard table) {
		this.table = table;
	}

	public Move getStep() {
		return step;
	}
	protected void setStep(Move step) {
		this.step = step;
	}

	public Node getParent() {
		return parent;
	}
	
	protected void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	
	protected void setChildren(Node child) {
		this.children.add(child);
	}

	public boolean isLeaf() {
		return leaf;
	}
	protected void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public double getValue() {
		return value;
	}
	
	protected void setValue(double value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}
	
	protected void setIndex(int index) {
		this.index = index;
	}

	public String toString(){
		String s="";
		s+=this.getIndex()+"-"+this.getValue()+" "+this.getStep();
		if(this.children.size()>0){
			s+="(";
			for(Node n:this.children){
				s+=" " + n.toString();
			}
			s+=")";
		}
		return s;
	}
}