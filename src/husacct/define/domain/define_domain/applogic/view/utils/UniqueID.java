package husacct.define.domain.define_domain.applogic.view.utils;

import java.util.Random;

public class UniqueID {
	static long current = System.currentTimeMillis();

	static public synchronized long get() {
		Random r = new Random();
		long nextLong = r.nextLong();

		return nextLong;
	}
}