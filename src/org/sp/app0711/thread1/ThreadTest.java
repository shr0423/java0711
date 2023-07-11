package org.sp.app0711.thread1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 1)쓰레드란?(Thread) 
 * -하나의 프로세스(실행중인 프로그램)내에서 독립적으로 실행될수있는
 * 세부 실행단위
 * 
 *   멀티태스킹대상                  OS                      jvm
 *    멀티태스킹대상               프로세스                  쓰레드
 *   병ㅇ행처리원리                 시분할                     시분할
 *   2)쓰레드의 생명주기(LifeCycle)
 *   -작성:로직은 run()메서드를 override해야한다
 *   -생성:개발자가 new한다
 *   -Runnable상태로 진입시킨다:start()호출
 *     jvm의 실행대상이 된다 즉 실행후보가 된다
 *   -jvm내부 알고리즘에 의해 랜덤하게 특정 Thread가 선택되고 이 선택된 
 *   쓰레드의 run()메서드를 실행하는 단계를 Running이라한다.
 *   -소멸:run()메서드의 닫는}브레이스를 만나면 쓰레드는 소멸됨 이때의
 *   생명주기 상태를 가리켜 Dead라 한다
 *   3)대부분의 프로그래밍 언어에서 는 쓰레드를 지원함
 *   
 * */
public class ThreadTest extends JFrame{
	JButton bt;//시작버튼
	JLabel la1;//1씩 증가하는 숫자를 보여줄 랄벨
	JLabel la2;//5씩 감소하는 숫자를 보여줄 라벨
	int n;
	
	public ThreadTest() {
		bt=new JButton("start");
	
		la1=new JLabel("0");
		la2=new JLabel("0");
		
		la1.setBackground(Color.YELLOW);
		la2.setBackground(Color.YELLOW);
		
		la1.setFont(new Font("Verdana",Font.BOLD,100));
		la2.setFont(new Font("Verdana",Font.BOLD,100));
		
		la1.setPreferredSize(new Dimension(270,170));
		la2.setPreferredSize(new Dimension(270,170));
		
		setLayout(new FlowLayout());
		
		add(bt);
		add(la1);
		add(la2);
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//이벤트 연결코드는 재사용성이 없기때문에 되도록이면 코드가 간소화될수록 좋다
		//따라서 이벤트 구현시 내부 익명 클래스를 지원함
		//java8 람다(Lambda)굳이 이벤트 연결 코드를 객체로 까지 갈필요있는가?
		//그냥 함수로 표현하는 방법을 지원함
		//ex)js에서의 화살표 함수랑 흡사
		//final int x=9;
		
		bt.addActionListener(new ActionListener(){
			//내부익명클래스내의 메서드에서는 바깥쪽 외부클래스(ThreadTest)의
			//멤버변수를 내것처럼 쓸수있다.
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("버튼눌렀어?");
					//x=9;내부익명클래스에서는 지역변수를 접근하려면 해당 지역변수는
					//final로 선언되어있어야 한다.
					createThread();
				}

			
			} );
			
			
		
	}
	//버튼을 누르면 2개의쓰레드를새 ㅇ성하여 jvm에게 맡기자
	//왜 맡겨야 하나? 시분할로 멀티쓰레드 구현을 위함
	//메인실행부는절대로, 대기상태나 무한루프에 빠뜨리면 안됨
	//왜?메인실행부는 1)gui에서 사용자의 이벤트처리, 그래픽처리
	public void createThread() {
		/*
		while(true) {
			System.out.println("호출함");
		}*/
		AddThread t1=new AddThread(this);
		t1.start();//Runnable로 진입
		
		//MinusThread t2=new MinusThread();
		Thread t2=new Thread() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					n-=5;
					la2.setText(Integer.toString(n));
				}
			}
		};
		
		t2.start();
		
		//쓰레드를 이용하는 코드는 비동기방식으로 동작되므로, 메인쓰레드는 개발자가 정의한
		//쓰레드의 실행완료를 기다리지 않는다
		//비동기 방식이란? 특정 코드가 끝날때까지 기다리지 않고 다른코드를 실행할수있는 방식
		System.out.println("메인쓰레드에 의해 실행");
		
	}
	public static void main(String[] args) {
		//아래의 코드를 실행하면 에러메시지에 main thread에서 에러가 발생했다는
		//메시지가 출력된다.결론)자바의실행부라 불렸던 그 녀석은 사실
		//메인 쓰레드였따 메인 쓰레드는 프로그램을 운영하는 실행부이므로 
		//반드시 대기상태나 루프에 빠트리지 말자(프로그램 먹통됨)
		/*
		int[] arr=new int[2];
		arr[0]=5;
		arr[1]=7;
		arr[2]=9;*/
		
		new ThreadTest();
	}
	
}