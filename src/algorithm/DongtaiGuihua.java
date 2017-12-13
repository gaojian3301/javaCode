package algorithm;

import java.util.ArrayList;
import java.util.List;

public class DongtaiGuihua {
	
	private static class Product{
		private String name;
		private int weight;
		private int value;
		public Product(String name,int weight, int value){
			this.name = name;
			this.weight = weight;
			this.value = value;
		}
		
		public String getName(){
			return name;
		}
		
		public int getWeight(){
			return weight;
		}
		public int getValue(){
			return value;
		}
	}
	
	/**
	 * 动态规划思想：从小问题入手，逐步解决大问题，是分治思想的一种。
	 * 全局最优解包涵局部最优解
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalWeight = 6;
		List<Product> products = new ArrayList<Product>();
		Product water = new Product("water",3,10);
		Product book = new Product("book",1,3);
		Product food = new Product("food",2,9);
		Product jacket = new Product("jacket",2,5);
		Product camera = new Product("camera",1,6);
		products.add(water);
		products.add(book);
		products.add(food);
		products.add(jacket);
		products.add(camera);
		
		int[][] shell = new int[6][7];
		for(int i = 0 ;i < 7;i++){
			shell[0][i] = 0;
		}
		for(int j = 0;j<6;j++){
			shell[j][0] = 0;
		}
		
		for(int i = 1;i<6;i++){
			for(int j = 1;j<7;j++){
				if(products.get(i-1).getWeight() > j){
					shell[i][j] = shell[i-1][j];
				}else{
					int newValue = products.get(i-1).getValue() +shell[i - 1][j - products.get(i-1).getWeight()];
					shell[i][j] = newValue > shell[i-1][j] ? newValue:shell[i-1][j];
				}
			}
		}
		
		System.out.println(shell[5][6]);
		
	}

}
