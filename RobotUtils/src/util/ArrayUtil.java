package util;

public class ArrayUtil {
	public static <T> int randomIndex(T[] array) {
		return Util.RGEN.nextInt(array.length);
	}
	
	public static <T> T randomChoice(T[] array) {
		return array[randomIndex(array)];
	}

    public static <T> void randomize(T[] array) {
        randomize(array, array.length * 2);
    }

    public static <T> void randomize(T[] array, int numSwaps) {
        int len = array.length;
        for(int i = 0; i < numSwaps; i++) {
            swap(array, Util.RGEN.nextInt(len), Util.RGEN.nextInt(len));
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
