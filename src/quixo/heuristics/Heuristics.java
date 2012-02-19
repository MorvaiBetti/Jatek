package quixo.heuristics;

import quixo.players.minimax.Node;

public interface Heuristics {
	void init(int me, int you, int nobody);
	void empty();
	int sum();
	int calculation(Node node);
	int getValue();
}
