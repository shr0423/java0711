package org.sp.app0711.thread1;

//숫자감소용 쓰레드
public class MinusThread extends Thread{
	
	//개발자는 독립수행하고 싶은 코드가 있을때 run메서드에 코드를 작성해야한다.
	//jvm이 호출하는메서드이기때문에
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		System.out.println("감소쓰레드 run()호출");
	}
}
}
