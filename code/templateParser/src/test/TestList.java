package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class TestList extends TestCase {
	public void testlist(){
		List<String> ls = new ArrayList<String>();
		ls.add("goo");
		ls.add("go1");
		ls.add("go2");
		ls.add("go3");
				
		for(int i = 0 ; i < ls.size(); i++){
			System.out.println(">>"+i +",>>"+ ls.get(i));
		}
		
	}
}
