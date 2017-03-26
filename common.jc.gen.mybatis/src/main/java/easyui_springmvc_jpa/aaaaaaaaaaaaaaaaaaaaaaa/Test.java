package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

public class Test {
	public static void main(String[] args) {
		Printer p = new Printer();
		Thread t1 = new NumberPrinter(p);
		Thread t2 = new LetterPrinter(p);
		t1.start();
		t2.start();
	}
}

class Printer {
	private int index = 1;

	/*
	 * synchronized:代表这个方法加锁, 相当于不管哪一个线程（例如线程A）， 运行到这个方法时,都要检查有没有其它线程B（或者C、 D等）正在用这个方法， 有的话要等正在使用synchronized方法的线程B（或者C 、D） 运行完这个方法后再运行此线程A,没有的话,直接运行
	 */
	public synchronized void print(int i) {// 打印数字的构造方法，每打印两个数字，等待打印一个字母
		while (index % 3 == 0) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		System.out.print("" + i);
		index++;
		notifyAll();
	}

	public synchronized void print(char c) {// 打印字母，每打印一个字母，等待打印两个数字
		while (index % 3 != 0) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		System.out.print("" + c);
		index++;
		notifyAll();
	}
}

class NumberPrinter extends Thread {// 打印数字的线程
	private Printer p;

	public NumberPrinter(Printer p) {
		this.p = p;
	}

	public void run() {
		for (int i = 1; i < 53; i++) {
			p.print(i);
		}
	}
}

class LetterPrinter extends Thread {// 打印字母的线程
	private Printer p;

	public LetterPrinter(Printer p) {
		this.p = p;
	}

	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			p.print(c);
		}
	}
}