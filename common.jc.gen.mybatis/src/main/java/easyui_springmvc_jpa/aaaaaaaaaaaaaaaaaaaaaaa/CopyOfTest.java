package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

public class CopyOfTest {
	public static void main(String[] args) {
		Runnable r1 = new Runnable() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					System.out.print(i);
				}
			}
		};
		Runnable r2 = new Runnable() {
			public void run() {
				for (char i = 'a'; i <= 'z'; i++) {
					System.out.print(i);
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
