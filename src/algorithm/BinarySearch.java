package algorithm;

public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] values = new int[]{1,2,3,4,5,6,7,8,9};
		
		System.out.println(binarySearch(values,0));
		System.out.println(binarySearch(values,1));
		System.out.println(binarySearch(values,2));
		System.out.println(binarySearch(values,3));
		System.out.println(binarySearch(values,4));
		System.out.println(binarySearch(values,5));
		System.out.println(binarySearch(values,6));
		System.out.println(binarySearch(values,7));
		System.out.println(binarySearch(values,8));
		System.out.println(binarySearch(values,9));
		
	}
	/*
	 * -1 表示查找的数字不在列表中
	 * 查找次数：O(logn)
	 * */
	public static int binarySearch(int[] values, int toBeFound){
		
		int low = 0;
		int high = values.length -1;
		
		int middle = 0;
		
		while(low <= high){
			middle = (low + high) / 2;
			if(values[middle] == toBeFound){
				return middle;
			}
			if(values[middle] > toBeFound){
				high = middle - 1;
			}else{
				low = middle + 1;
			}
		}
		
		return -1;
	}
	
}
