package part1.jumbledstring;

public class CharacterSwap {
    private final int indexA, indexB;

    public CharacterSwap(int indexA, int indexB) {
        this.indexA = indexA;
        this.indexB = indexB;
    }

    public int getIndexA() {
        return indexA;
    }

    public int getIndexB() {
        return indexB;
    }

    @Override
    public String toString() {
        return "Swap index " + indexA + " with index " + indexB;
    }
}
