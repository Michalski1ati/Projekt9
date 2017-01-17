package profilowanie1;

import java.util.Map;

public class MemLeak {
	
	public final String key;
	
	public MemLeak(String key){
		this.key = key;
	}
	
	public static void main(String[] args){
		try{
			Map map = System.getProperties();
			
			//limit variable is needed
			int size = 100;
			
			for(int i = 0; i < size; i++){
				
				map.put(new MemLeak("key"), "value");
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
