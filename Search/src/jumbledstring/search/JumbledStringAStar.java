package jumbledstring.search;

import jumbledstring.CharacterSwap;
import search.AStarFrontier;
import search.interfaces.Frontier;

public class JumbledStringAStar extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringAStar().search();
    }

    @Override
    protected Frontier<JumbledStringNode, CharacterSwap> getFrontier(JumbledStringNode goal) {
        return new AStarFrontier<JumbledStringNode, CharacterSwap>(goal);
    }
}
