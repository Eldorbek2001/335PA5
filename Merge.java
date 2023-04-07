public class Merge {
    public static void sort(Array array) {
        array.initExtra(array.length());
        int[] extra = new int[array.length()];
        for (int size = 1; size < array.length(); size *= 2) {
            for (int i = 0; i < array.length() - size; i += 2 * size) {
                int left = i;
                int mid = i + size - 1;
                int right = Math.min(i + 2 * size - 1, array.length() - 1);
                merge(array, extra, left, mid, right);
            }
        }
    }

    private static void merge(Array array, int[] extra, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (array.getVal(i) <= array.getVal(j)) {
                extra[k] = array.getVal(i);
                i++;
            } else {
                extra[k] = array.getVal(j);
                j++;
            }
            k++;
        }
        while (i <= mid) {
            extra[k] = array.getVal(i);
            i++;
            k++;
        }
        while (j <= right) {
            extra[k] = array.getVal(j);
            j++;
            k++;
        }
        for (k = left; k <= right; k++) {
            array.setVal(k, extra[k]);
        }
    }
}
