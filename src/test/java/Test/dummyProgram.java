package Test;

import java.io.IOException;

public class dummyProgram {

	public static void main(String[] args) {
		int x = 0;

		if (x == 0) {
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}

}
