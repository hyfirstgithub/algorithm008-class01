学习笔记


# 位运算基本知识

位运算符

含义|运算符|示例
---|:--:|---:
|左移|<< | 0011 => 0110|
|右移| >> |0110 => 0011 

异或操作要点

- x ^ 0 = x
- x ^ 1s = ~x  //注意1d = ~0
- x ^ x = 0
- c = a ^ b => a ^ c = b , b ^ c = a //交换两个数
- a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c


指定位置的位运算

1. 将最右边的n位清零 ： x & (~0 << n) 
2. 获取x的第n位值（0 或者 1） ： (x>>n) & 1
3. 获取x的第n位的幂值： x & (1 << n)
4. 仅将第n位置为1： x | (1 << n)
5. 仅将第n为置为0： x & (~(1 << n))
6. 将x的最高位至n位清零：x & ((1 << n) - 1)
7. 将第n位至第0位清零：x & (~((1 << (n+1))-1))

实战位运算要点：

- 判断奇偶：
    x % 2 == 1 => (x & 1) == 1
    x % 2 == 0 => (x & 1) == 0
    
- x >> 1 -> x/2

- x = x & (x- 1) 清零最低位的1
- x & -x => 得到最低位的1
- x & ~x = 0 


# 初级排序算法

```java
public class SortMethod {

    public static void main(String[] args) {
        SortMethod method = new SortMethod();
        int[] nums = {2,1,3,5,4,10,6};
//        method.bubbleSort(nums);
//        method.selectionSort(nums);
//        method.insertionSort(nums);
//        method.shellSort(nums);
//        method.mergeSort(nums);
//        method.heapif(nums);
        System.out.println(Arrays.toString(nums));

    }

    /**
     *  冒泡排序
     * @param nums int数组
     */
    public void bubbleSort(int[] nums) {
        if (nums.length == 1) return;
        for(int i = nums.length - 1; i >= 0 ; i--) {
            boolean flag = false;
            for(int j =0 ; j < i; j  ++) {
                if (nums[j] > nums[j+1]){
                    int tmp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = tmp;
                    flag = true;
                }
            }
            if (flag)break;
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selectionSort(int[] nums) {
        if (nums.length == 1) return;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                if (current > nums[j]) {
                    int tmp = current;
                    current = nums[j];
                    nums[j] = tmp;
                }
            }
            nums[i] = current;
        }
    }

    /**
     *  插入排序
     */
    public void insertionSort(int[] nums) {
        if (nums.length == 1) return;
        for(int i = 1 ; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i -1;
            for(; j >= 0; j--) {
                if (nums[j] > tmp){
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j+1] = tmp;
        }
    }

    /**
     * 希尔排序
     * @param nums
     */
    public void shellSort(int[] nums) {
        if (nums.length == 1) return;
        for(int i = (nums.length - 1) >> 1 ; i > 0 ; i--) {
            for(int j = i; j < nums.length; j++) {
                int h = j;
                int current = nums[j];
                for( ; h - i>=0 && h < nums.length; h -= i) {
                    if (nums[h - i] > current) {
                        nums[h] = nums[h - i];
                    } else {
                        break;
                    }
                }
                nums[h] = current;
            }
        }
    }

    /**
     *  归并排序
     * @param nums
     */
    public void mergeSort(int[] nums) {
        if (nums.length == 1) return;
        mergeSort(nums, 0 ,nums.length - 1);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid+1, right);

        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1;
        int pos =0;
        for( ;i <= mid && j <= right; pos++) {
            if (nums[i] <= nums[j]) {
                tmp[pos] = nums[i];
                i++;
            } else {
                tmp[pos] = nums[j];
                j++;
            }
        }
        if (i > mid) {
            while (j <= right){
                tmp[pos] = nums[j];
                j++;
                pos++;
            }
        } else {
            while (i <= mid) {
                tmp[pos] = nums[i];
                pos++;
                i++;
            }
        }

        for(int n = 0 ; n < tmp.length; n++) {
            nums[left+n] = tmp[n];
        }
    }

    public void quickSort(int[] nums) {
        quickSor(nums, 0 , nums.length-1);
    }

    private void quickSor(int[] arr, int left, int right) {
        if (left >= right) return;

        int povt = left;
        int index = left + 1;
        for (int i = left; i <= right; i++){
            if(arr[i] < arr[povt]) {
                swap(arr, i, index);
                index++;
            }
        }
        index--;
        swap(arr, povt, index);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    private void swap(int[] arr, int pos1, int pos2) {
        int tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }

    /**
     * 堆排序
     * @param nums
     */
    public void heapSort(int[] nums) {
        for(int i = (nums.length >> 1) -1 ; i >= 0; i--) {
            heapif(nums, nums.length, i);
        }

        for(int i = nums.length - 1; i >= 0; i--) {
            int tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;
            heapif(nums, i, 0);
        }

    }

    private void heapif(int[] nums, int length , int i) {
        int left = (i << 1) + 1, right = (i << 1) + 2;
        int largestPos = i;
        if (left < length && nums[left] > nums[largestPos] ) {
            largestPos = left;
        }
        if (right < length && nums[right] > nums[largestPos]) {
            largestPos = right;
        }
        if (largestPos != i) {
            int tmp = nums[i];
            nums[i] = nums[largestPos];
            nums[largestPos] = tmp;
            heapif(nums, length, largestPos);
        }
    }

    /**
     * 计数排序
     */
    public void countingSort(int[] nums, int maxValue) {
        int[] countMark = new int[maxValue + 1];
        for(int i: nums) {
            countMark[i]++;
        }
        int pos = 0;
        for(int i = 0; i < countMark.length; i++) {
            while (countMark[i] > 0) {
                nums[pos] = i;
                countMark[i]--;
                pos++;
            }
        }
    }

    /**
     *  桶排序
     * @param nums
     */
    public void bucketSort(int[] nums) {
        int min = nums[0];
        int max= nums[0];
        for(int i =1; i < nums.length ; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = min[i];
            }
        }

        int bucketSize = max - min  + 1;
        int[][] = new int[bucketSize][nums.length];

        for(int i = 0; i < nums.length ; i++) {

        }
    }

}
```