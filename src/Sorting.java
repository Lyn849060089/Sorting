import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class Sorting {
    private int[] array;
    private int[] temps;

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    @Before
    public void init() {
        array = new int[] {
                6, 13, 5, 12, 3, 1, 10, 8, 7, 11, 2, 4, 9
        };
        temps = new int[array.length];
        System.out.println("排序前：" + Arrays.toString(array));
    }

    @After
    public void last() {
        System.out.println("排序后：" + Arrays.toString(array));
    }

    /**
     * 冒泡排序
     */
    @Test
    public void BubbleSorting() {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0 ; j < array.length-1-i; j++) {
                if (array[j] > array[j+1]) {
                    swap(j, j+1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    @Test
    public void SelectionSorting() {
        for (int i = 0; i < array.length-1; i++) {
            int indexOfMin = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[indexOfMin]) {
                    indexOfMin = j;
                }
            }
            if (indexOfMin != i) {
                swap(indexOfMin, i);
            }
        }
    }

    /**
     * 插入排序
     */
    @Test
    public void InsertionSorting() {
        for (int i = 1; i < array.length; i++) {
            int get = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > get) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = get;
        }
    }

    /**
     * 快速排序
     */
    @Test
    public void QuickSorting() {
        quickSort(array, 0, array.length-1);
    }
    private void quickSort(int[] array, int low ,int high) {
        if(low >= high) {
            return;
        }
        int middle = partition(array, low, high);
        quickSort(array, low, middle-1);
        quickSort(array, middle+1, high);
    }
    private int partition(int []array, int low, int high) {
        int key = array[low];
        while (low < high) {
            while (array[high] >= key && high > low) {
                high--;
            }
            array[low] = array[high];
            while (array[low] <= key && high > low) {
                low++;
            }
            array[high] = array[low];
        }
        array[high] = key;
        return high;
    }

    /**
     * 堆排序
     */
    @Test
    public void HeapSorting() {
        //构建大顶堆
        for (int i = array.length/2-1; i >= 0; i--) {
            adjustHeap(i, array.length);
        }
        for (int i = array.length-1; i > 0; i--) {
            swap(0, i);
            adjustHeap(0, i);
        }
    }
    private void adjustHeap(int i, int length) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxIndex = i;
        if (left < length && array[left] > array[maxIndex]) {
            maxIndex = left;
        }
        if (right < length && array[right] > array[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != i) {
            swap(i, maxIndex);
            adjustHeap(maxIndex, length);
        }
    }

    /**
     * 希尔排序
     */
    @Test
    public void ShellSorting() {
        int gap = array.length / 2;
        while (gap > 0) {
            insertSort(gap);
            gap = gap / 2;
        }
    }
    private void insertSort(int gap) {
        for (int i = 1; i < array.length; i += gap) {
            int get = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > get) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = get;
        }
    }

    /**
     * 归并排序
     */
    @Test
    public void MergeSorting() {
        mergeSort(0, array.length-1);
    }
    private void mergeSort(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(left, middle);
            mergeSort(middle + 1, right);
            merge(left, middle, right);
        }
    }
    private void merge(int left, int middle, int right) {
        int p_left = left;
        int p_right = middle + 1;
        int p_temp = 0;
        while (p_left <= middle && p_right <= right) {
            if (array[p_left] <= array[p_right]) {
                temps[p_temp++] = array[p_left++];
            }
            else {
                temps[p_temp++] = array[p_right++];
            }
        }
        while (p_left <= middle) {
            temps[p_temp++] = array[p_left++];
        }
        while (p_right <= right) {
            temps[p_temp++] = array[p_right++];
        }
        p_temp = 0;
        while (left <= right) {
            array[left++] = temps[p_temp++];
        }
    }

}
