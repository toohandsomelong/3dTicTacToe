package Default;

public class DH23DTC_NguyenNhutLong_23130179
{
    static void test(int[][][] array, int Layer)
    {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][Layer][j]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) 
    {
        Game game = new Game();
        game.start(); //vertical not checked
    }
}