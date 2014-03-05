package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.SearchRunner;

public abstract class JumbledStringSearch extends SearchRunner<JumbledStringNode, CharacterSwap> {
    @Override
    protected JumbledStringNode createStart() {
        return new JumbledStringNode(new JumbledString("avja"));
    }

    @Override
    protected JumbledStringNode createGoal() {
        return new JumbledStringNode(new JumbledString("java"));
    }
}
