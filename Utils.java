package Default;

import java.util.Scanner;

public class Utils 
{
    public static void clearConsole()
    {
        System.out.print("\033\143");
    }
    
    public static boolean checkConfirm()
    {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Confirm?" + " (y/n): ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes"))
                return true;
            else if (input.equals("n") || input.equals("no"))
                return false;
            else
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
        }
    }
    
    public static void exitGame()
    {
        System.exit(0);
    }

    public static int checkInputInt(Scanner scanner) 
    {
        int temp;
        try 
        {
            temp = scanner.nextInt();
        } 
        catch (Exception e) 
        {
            scanner.next(); // Clear the invalid token
            temp = -1;
        }
        return temp;
    }

    public static int tryParseInt(String value) 
    {
        int temp;
        try 
        {
            temp = Integer.parseInt(value);
        } 
        catch (Exception e) 
        {
            temp = -1;
        }
        return temp;
    }
    
    public int[][][] rotateUP(int[][][] board)
    {
        int length = board.length;
        int[][][] newB = new int[length][length][length];

        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < length; x++)
            {
                for (int z = 0; z < length; z++)
                {
                    //aka x y z
                    newB[y][x][z] = board[x][y][z];
                }
            }
        }

        return newB;
    }

    
    public int[][][] rotateSide(int[][][] board)
    {
        int length = board.length;
        int[][][] newB = new int[length][length][length];

        for (int z = 0; z < length; z++) 
            for (int y = 0; y < length; y++) 
                for (int x = 0; x < length; x++) 
                {
                    //aka x y z
                    newB[z][y][x] = board[x][y][z];
                }


        return newB;
    }
}
