package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ClassLoaderApp {
	
	public ClassLoaderApp() {
		//ㅈ바의 여러클래스중 클래스에대한 정보를 가진 클래스를Class
		//
		URL url=ClassLoader.getSystemResource("res/hero/bg2.png");
		
		//기존 Image객체보다 크기나, 사이즈 조절이 가능한 이밈지를 말함
		//따라서 Image보다 더 활용도가 높다
		try {
			BufferedImage buffImg=ImageIO.read(url);
			System.out.println(buffImg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new ClassLoaderApp();
	}
}
