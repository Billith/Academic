package ppj_8.ppj_wyklad_8;
public
	class MTNarzedzia{

	public static void wyswietl(int[] data){
		for(int i=0; i<data.length; i++){
			System.out.println("data["+i+"] = " + data[i]);
		}				
	}
	
	public static void sortuj(int[] data){
		for( int j = 0; j < data.length-1; j++){
			int minValIndex = j;
			for(int i=j+1; i<data.length; i++){
				if(data[i] < data[minValIndex])
					minValIndex = i;
			}
			System.out.println("minVal: "+data[minValIndex]);		
			{
			int tmp = data[j];
			data[j] = data[minValIndex];
			data[minValIndex] = tmp;
			}
		}		
	}	
	
}