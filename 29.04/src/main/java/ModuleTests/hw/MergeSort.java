package ModuleTests.hw;

public class MergeSort {
    public void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    public void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i =left;
        int j = mid + 1;
        int k = 0; //текущая позиция в temp

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        //дописываем остатки из левой части
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int ind = 0; ind < temp.length; ind++) {
            array[ind + left] = temp[ind];
        }
    }
}

/*
2. Протестировать один из алгоритмов сортировки
* */