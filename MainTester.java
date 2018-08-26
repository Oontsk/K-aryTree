import java.util.Random;

public class MainTester {
    public static void main(String[] args) {
    	Random random = new Random();
        BinarySearchTree<Integer> b1 = new BinarySearchTree<Integer>(null),
        		b2 = new BinarySearchTree<>(null);
        for (int i = 12; i > 0; --i) {
            b1.insert(random.nextInt(5));
            b2.insert(i);
        }
        //System.out.print(b1.toString());
        System.out.println(b2.toString());
        for (int i = 12; i > 0; i -= 3) {
        	b2.remove(i);
        	System.out.println(b2.toString());
        }
        
    }
}
