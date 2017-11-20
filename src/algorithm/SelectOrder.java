package algorithm;

public class SelectOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list = new int[]{9,2,7,6,4,5,3,8,1};
		selectOrder(list);
		for(int value : list){
			System.out.print(value);
		}
	}
	
	/*
	 * 比较次数：n个元素
	 * (n-1)+(n-2)+(n-3)+........+1 = ((n-1)n)/2 ---->O(n*n)
	 * 交换次数，最多(n-1)次，可以忽略
	 * */
	
	
	public static void selectOrder(int[] list){
		if(list.length < 2){
			return;
		}
		int compareSteps = 0;
		int swapSteps = 0;
		int length = list.length;
		int tempLocation = 0;
		for(int i = 0;i<length;i++){
			tempLocation = i;
			for(int j = i+1;j<length;j++){
				compareSteps ++;
				if(list[j] < list[tempLocation]){
					tempLocation = j;
				}
			}
			if(tempLocation > i){
				int temp = list[i];
				list[i] = list[tempLocation];
				list[tempLocation] = temp;
				swapSteps ++;
			}
		}
		System.out.println("compare times = "+compareSteps);
		System.out.println("swap times = "+swapSteps);
	}
	
}
