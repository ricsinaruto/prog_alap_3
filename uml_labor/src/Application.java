//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Application.java
//  @ Date : 2018. 10. 15.
//  @ Author : 
//
//




public class Application {
	public static void main(String[] args) {
		PercentCounter pc = new PercentCounter();
		StdOutLogger sol = new StdOutLogger();
		FileLogger fl = new FileLogger("text.txt");
		
		pc.register(sol);
		pc.register(fl);
		
		pc.run();
	}
}