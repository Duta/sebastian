package eightpuzzle.search;

import java.util.Stack;

import eightpuzzle.EightPuzzle;
import eightpuzzle.PuzzleMove;
import search.PathNotFoundException;
import search.Search;
import search.interfaces.Frontier;

public abstract class EightPuzzleSearch {
	protected EightPuzzleSearch() {}
	
	public void search() {
		EightPuzzleNode start = new EightPuzzleNode(EightPuzzle.randomEightPuzzle());
		EightPuzzleNode goal = new EightPuzzleNode(EightPuzzle.orderedEightPuzzle());
		
		System.out.println("Start State:");
		System.out.println(start);
		System.out.println("Goal State:");
		System.out.println(goal);
		
		Stack<EightPuzzleNode> path = null;
		try {
			path = Search.search(start, goal, getFrontier(goal));
		} catch (PathNotFoundException e) {
			System.out.println("No path found.");
			return;
		}
		
		path.pop();
		System.out.println("Path (Length " + path.size() + "):");
		while(!path.empty()) {
			EightPuzzleNode state = path.pop();
			System.out.println(state.getAction());
		}
	}
	
	protected abstract Frontier<EightPuzzleNode, PuzzleMove> getFrontier(EightPuzzleNode goal);
}
