package jumbledstring.search;

import jumbledstring.CharacterSwap;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

public class JumbledStringBreadthFirst extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringBreadthFirst().search();
    }

    @Override
    protected Frontier<JumbledStringNode, CharacterSwap> createFrontier(JumbledStringNode goal) {
        return new BreadthFirstFrontier<JumbledStringNode, CharacterSwap>();
    }
}
