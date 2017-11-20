package algorithm;

public class QuickOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list = new int[]{1,2,3,4,5,6,7,8,9};
		quickOrder(list,0,8);
		System.out.println("compare steps = "+steps);
		System.out.println("swap steps = " + swaps);
		for(int value : list){
			System.out.print(value+",");
		}
	}
	
	public static void quickOrder(int[] list,int low, int high){
		if(low < high){
			int middle = getMiddle(list,low,high);
			quickOrder(list,low,middle -1);
			quickOrder(list,middle + 1,high);
		}
	}
	private static int steps = 0;
	private static int swaps = 0;
	public static int getMiddle(int[] list,int low,int high){
		int key = list[low];
		while (low < high){
			while(low < high && key <= list[high]){
				steps ++;
				high --;
			}
			if(low < high){
				swaps ++;
				int temp = list[low];
				list[low] = list[high];
				list[high] = temp;
			}
			
			while(low < high && key  >= list[low]){
				steps ++;
				low ++;
			}
			if(low < high){
				swaps ++;
				int temp = list[low];
				list[low] = list[high];
				list[high] = temp;
			}
		}
		return high;
	}
}
