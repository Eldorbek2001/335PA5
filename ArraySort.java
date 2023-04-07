
public class ArraySort {
	
	//1. The input array is in descending order.
	//2. The input array is in random order.
	//3. The input array is almost sorted, meaning that only a few items are out of order.
	//4. The input array is locality-aware. That means that every item in the input array is
	//   no more than d positions away from where it will end up in the sorted array.
	//5. The input array is mostly sorted with just a small number of unsorted elements at
	//   the end.
	//6. The input array is made up of a small number of sorted subarrays.
	//7. The input array is random, but the values in the array are relatively limited in
	//   range.

	public static void sort(Array array, int num, int d){
		switch(num) {
		  case 1:
			  reverse(array);
		    // code block
		    break;
		  case 2:
			  sortRandom(array);
		    break;
		  case 3:
			  sortAlmostSorted(array);

		    break;
		  case 4:
			  sortRandom(array);
		    break;
		  case 5:
			  sortMostlySorted(array);

		    break;
		  case 6:
			  sortRandom(array);

		    // code block
		    break;
		  case 7:
			  sortLimitedRange(array);
			  break;
		  default:
		    // code block
		}
	};
	
	static void reverse(Array x) {
		int size = x.length();
		if(size > 1) {
			int start = 0;
			int end = size-1;
			while(start<end) {
				x.swap(start, end);
				end--;
				start++;
			}		
		}
		
	}
	
	public static void sortRandom(Array array){
	    int left = 0;
	    int right = array.length()-1;
	    quickSort(array, left, right);
	}

	private static void quickSort(Array array, int left, int right){
	    if(left < right){
	        int pivot = partition(array, left, right);
	        quickSort(array, left, pivot-1);
	        quickSort(array, pivot+1, right);
	    }
	}

	private static int partition(Array array, int left, int right){
	    int pivot = array.getVal(right);
	    int i = left-1;
	    for(int j=left; j<right; j++){
	        if(array.getVal(j) <= pivot){
	            i++;
	            array.swap(i, j);
	        }
	    }
	    array.swap(i+1, right);
	    return i+1;
	}

	public static void sortAlmostSorted(Array array){
	    int n = array.length();
	    for(int i=1; i<n; i++){
	        int key = array.getVal(i);
	        int j = i-1;
	        while(j>=0 && array.getVal(j) > key){
	            array.setVal(j+1, array.getVal(j));
	            j--;
	        }
	        array.setVal(j+1, key);
	    }
	}

	
	
	public static void unsortedEnd(Array array) {
		//find
		System.out.println(array);
		int size = array.length();
		int mid = array.getVal(size/2);
		array.initExtra(size/2);
		int max = Integer.MIN_VALUE;
		int index = size-1;
		int extraIndex= 0;
		while(index > 0) {
			int x = array.getVal(index);
			if(x>max) {
				max = x;
			}
			if(x <mid) {
				array.setExtraVal(extraIndex, x);
				extraIndex ++;
			}
			else {
				break;
			}
			index --;
		}
		int diff = size-index;
		
		while(index>diff) {
			array.setVal(index+diff-1, array.getVal(index));
			index --;
		}
		int z = 0;
		while(true) {
			int y= array.getVal(z);
			if(y<max) {
				array.setExtraVal(extraIndex, y);
				extraIndex++;
			}
			break;
		}
		z =0;
		int q = Integer.MAX_VALUE;
		while(z<extraIndex) {
			int x = array.getExtraVal(z);
			if(x<q) {
				q = x;
			}
			z++;
		}
		next(array, extraIndex);
					
				
	}
	private static void next(Array array, int size) {
		int realIndex = 0;
		int z = Integer.MAX_VALUE;
		int minValIndex = 0;
		for(int b = 0; b<size; b++) {
			for(int i=0; i < size; i++) {
				int x = array.getExtraVal(i);
				if(x<z) {
					z = x; 
					minValIndex = i;
				}
		}
			array.setVal(b, z);
			array.setExtraVal(minValIndex, Integer.MAX_VALUE);
			z = Integer.MAX_VALUE;

		}
		
		
	}
	public static void sortMostlySorted(Array array) {
	    int n = array.length();
	    for (int i = 1; i < n; i++) {
	        int key = array.getVal(i);
	        int j = i - 1;
	        while (j >= 0 && array.getVal(j) > key) {
	            array.setVal(j + 1, array.getVal(j));
	            j--;
	        }
	        array.setVal(j + 1, key);
	    }
	}
	
	public static void sortLimitedRange(Array array) {
	    int n = array.length();
	    int min = array.getVal(0);
	    int max = array.getVal(0);
	    for (int i = 1; i < n; i++) {
	        if (array.getVal(i) < min) {
	            min = array.getVal(i);
	        }
	        if (array.getVal(i) > max) {
	            max = array.getVal(i);
	        }
	    }
	    int[] count = new int[max - min + 1];
	    for (int i = 0; i < n; i++) {
	        count[array.getVal(i) - min]++;
	    }
	    for (int i = 1; i < count.length; i++) {
	        count[i] += count[i - 1];
	    }
	    int[] aux = new int[n];
	    for (int i = n - 1; i >= 0; i--) {
	        aux[--count[array.getVal(i) - min]] = array.getVal(i);
	    }
	    for (int i = 0; i < n; i++) {
	        array.setVal(i, aux[i]);
	    }
	}





	
}
