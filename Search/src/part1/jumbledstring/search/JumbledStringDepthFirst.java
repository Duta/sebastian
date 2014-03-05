package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

public class JumbledStringDepthFirst extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringDepthFirst().search();
    }

    @Override
    protected Frontier<JumbledStringNode, CharacterSwap> createFrontier(JumbledStringNode goal) {
        return new DepthFirstFrontier<JumbledStringNode, CharacterSwap>();
    }
}
