import com.google.android.apps.common.testing.ui.espresso.Espresso;

public class LiveVarsClass {
	public static void main(String[] args) {
		int x, y, z;
		x = 10;
		System.out.print(x);
        Espresso e;
		System.out.println(x);
		while (x > 1) {
			y = x/2;
			if (y > 3) x = x - y;
			z = x - 4;
			if (z > 0) x = x/2;
			z = z - 1;
		}
		System.out.println(x);
	}
}
