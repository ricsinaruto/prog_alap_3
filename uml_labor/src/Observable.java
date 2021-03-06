import java.util.ArrayList;
import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Observable.java
//  @ Date : 2018. 10. 15.
//  @ Author : 
//
//




public class Observable {
	List<Observer> observers = new ArrayList<>();
	public void register(Observer observer) {
		observers.add(observer);
	}
	
	public void unregister(Observer observer) {
		observers.remove(observer);
	}
	
	public void reportToObservers() {
		for (Observer o: observers) {
			o.report(this);
		}
	}
}
