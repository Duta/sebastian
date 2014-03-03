package jumbledstring.search;

import jumbledstring.CharacterSwap;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

public class JumbledStringDepthFirst extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringDepthFirst().search();
    }

    @Override
    protected Frontier<JumbledStringNode, CharacterSwap> getFrontier(JumbledStringNode goal) {
        return new DepthFirstFrontier<JumbledStringNode, CharacterSwap>();
    }
}
