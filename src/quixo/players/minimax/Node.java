package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;

public class Node {
	public Pair data;
	public int value;
	public Node parent;
	public ArrayList<Node> children=new ArrayList<Node>(80);
	public boolean leaf;
	public int index;
	public Move step;
	public Node brother=null;

	public Node(Pair d, Node p, Move s){
		data=d;
		parent=p;
		step=s;
		if(parent!=null){index=parent.getIndex()+1;}
		if(data.table.win(QuixoBoard.O) || data.table.win(QuixoBoard.X)){
			setLeaf(true);
		}
	}

	/*public void addChild(Node child, Node parent, int v, Move s){
		if(child.data.getTable().win(parent.data.getModel())){
			parent.setLeaf(true);
			System.out.println("Level vagyok");
			return;
		}else parent.setLeaf(false);
		parent.children.add(child);
		child.setIndex(parent.getIndex()+1);	
	}*/


	public Node getBrother() {
		return brother;
	}

	public void setBrother(Node brother) {
		this.brother = brother;
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