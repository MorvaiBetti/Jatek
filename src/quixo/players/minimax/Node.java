package quixo.players.minimax;

import java.util.*;

import quixo.engine.Move;

public class Node {
	/**@model csomopont mintaja, amivel lepni kell*/
	public int model;
	/**@value csomopont erteke*/
	public int value;
	/**@parent csomopont apja*/
	public Node parent;
	/**@children csomopont fiai*/
	public ArrayList<Node> children=new ArrayList<Node>();
	/**@leaf csomopont level-e*/
	public boolean leaf;
	/**@end a csomopont az aktualis n melysegu reszfaban n melyen van-e*/
	public boolean end;
	/**@index csomopont indexe*/
	public int index;
	/**@step csomoponthoz vezeto lepes*/
	public Move step;
	public int ind;
	
	public Node(int color, Node p, int v, Move s){
		model=color;
		parent=p;
		value=v;
		step=s;
	}
	
	public boolean equals(Move m){
		if(step.x==m.x && step.y==m.y && step.nx==m.nx && step.ny==m.y){
			return true;
		}
		return false;
	}

	public void addChild(Node child, Node parent, int v, Move s){
		parent.children.add(child);
		child.setIndex(parent.getIndex()+1);	
	}
	
	public Move getStep() {
		return step;
	}
	public void setStep(Move step) {
		this.step = step;
	}
	
	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
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
	
	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
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

	/*public String toString() {
		return "Node [" +
				"step="+step+
				" value=" + value + 
				", \nparent=" + parent + 
			//	", children="+ children + 
				", model="+ model+
				", index=" + index + "]";
	}*/
	
	public String toString(){
		String s="";
		s+=this.ind+"-"+this.value;
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