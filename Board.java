package Default;

public class Board 
{
    private char[][][] board;
    private boolean isOver = false;
    private int lengthToWin = 3;
    
    public Board(int size)
    {
        board = new char[size][size][size];
        initBoard();
    }

    public boolean isOver() 
    {
        return isOver;
    }

    public void initBoard()
    {
        int size = board.length;
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                for (int z = 0; z < size; z++)
                    board[x][y][z] = ' ';
    }

    public boolean setMove(char player, int x, int y, int z)
    {
        if (!isValidMove(x, y, z))
        {
            System.out.println("It is NOT a valid move!");
            System.out.println("Try again!");
            return false;
        }

        board[x][y][z] = player;
        moveCount++;
        return true;
    }
    
    public boolean isValidMove(int x, int y, int z)
    {
        if (x < 0 || y < 0 || z < 0)
            return false;

        int size = board.length;
        if (x >= size || y >= size || z >= size)
            return false;

        if (board[x][y][z] != ' ')
            return false;

        return true;
    }
    
    public boolean checkWinner(char player, int x, int y, int z)
    {
        int[][] directions = {
            {0, 1},  // horizontal
            {1, 0},  // vertical
            {1, 1},  // diagonal
            {1, -1}  // anti-diagonal
        };

        for (int[] dir : directions) {
            int count = 1; // current cell

            // Check one direction
            count += countDirection(x, y, z, dir[0], dir[1], player);
            // Check the opposite direction
            count += countDirection(x, y, z, -dir[0], -dir[1], player);

            if (count >= lengthToWin)
                return true;
        }

        return false;
    }

    int countDirection(int x, int y, int z, int dr, int dc, char player)
    {
        int count = 0;
        int r = y + dr;
        int c = z + dc;

        while (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[x][r][c] == player)
        {
            count++;
            r += dr;
            c += dc;
        }

        return count;
    }


    public void printBoard()
    {
        int length = board.length;

        for (int x = length - 1; x >= 0; x--) 
        {
            for (int i = 0; i < x; i++) 
                System.out.print("\t ");

            System.out.println("Layer " + (x + 1));

            for (int y = 0; y < length; y++) 
            {
                for (int i = 0; i < x; i++)
                    System.out.print("\t");
                for (int z = 0; z < length; z++) 
                {
                    if (z == 0)
                        System.out.print("|");
                    
                    System.out.print(board[x][y][z] + "|");
                }
                System.out.println();
            }
        }
    }
}
