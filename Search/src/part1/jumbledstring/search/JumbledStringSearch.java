package part1.jumbledstring.search;

import part1.Part1SearchRunner;
import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.SearchRunner;

/**
 * Performs search on the jumbled string problem.
 * Can be extended to provide a frontier (search type).
 */
public abstract class JumbledStringSearch extends Part1SearchRunner<JumbledString, JumbledStringNode, CharacterSwap> {
    @Override
    protected JumbledStringNode createStart() {
        return new JumbledStringNode(new JumbledString("avja"));
    }

    @Override
    protected JumbledStringNode createGoal() {
        return new JumbledStringNode(new JumbledString("java"));
    }
}
