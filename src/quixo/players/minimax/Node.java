package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;

public class Node {
	/**@data a csucspont mintaja es tablaja*/
	public Pair data;
	/**@value csucspont erteke*/
	public int value;
	/**@parent csucspont apja*/
	public Node parent;
	/**@children csucspont fiai*/
	public ArrayList<Node> children=new ArrayList<Node>();
	/**@leaf csucspont level-e. Level ha nincs fia, ha nyert valaki vagy ha n melyen van*/
	public boolean leaf;
	/**@index csucspont melysege*/
	public int index;
	/**@step csucspont lepese*/
	public Move step;

	public Node(Pair d, Node p, Move s){
		data=d;
		parent=p;
		step=s;
		if(parent!=null){index=parent.getIndex()+1;}
		if(data.table.win(QuixoBoard.O) || data.table.win(QuixoBoard.X)){
			setLeaf(true);
		}
	}

	public Move getStep() {
		return step;
	}
	public void setStep(Move step) {
		this.step = step;
	}
	public Pair getData() {
		return data;
	}
	public void setData(Pair data) {
		this.data = data;
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(Node child) {
		this.children.add(child);
	}

	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public String toString(){
		String s="";
		s+=this.index+"-"+this.value+"\n"+this.data.table+"\n\n";
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