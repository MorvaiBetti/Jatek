import java.util.*;

public class Node {
	public int model;
	public int value;
	public Node parent;
	public ArrayList<Node> children=new ArrayList<Node>();
	public boolean leaf;
	public int index;
	public Move step;
	public Node brother=null;
	
	public Node(int color, Node p, int v, Move s){
		model=color;
		parent=p;
		value=v;
		step=s;
	}

	public void addChild(Node child, Node parent, int v, Move s){
		parent.children.add(child);
		child.setIndex(parent.getIndex()+1);	
	}
	
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

	public String toString() {
		return "Node [" +
				"step="+step+
				" value=" + value + 
			//	", \nparent=" + parent + 
			//	", children="+ children + 
				", model="+ model+
				", index=" + index + "]";
	}
}