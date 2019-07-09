public class Test {

   /**
    * Mystery method that performs a function recursively.
    *
    * @param x an integer > 0
    * @param y an integer > 0 and < x
    * prints result
    */
    public static int euclid(int x, int y) {
        if (y == 0) { 
            return x;
        } else {
            int rem = x % y;
            return euclid(y, rem);
        }
    }

    public static void main(String[] args) {
        System.out.println(euclid(1440, 408));
    }
}