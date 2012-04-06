package test;

public class B {
	
	private C c;

	public C getC() {
		System.out.println("getC");
		return c;
	}

	public void setC(C c) {
		System.out.println("setC");
		this.c = c;
	}
	
	public void print(){
		System.out.println("goB");
	}
	
	
}
