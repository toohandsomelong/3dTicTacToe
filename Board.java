package Default;

public class Board 
{
    private char[][][] board;
    private boolean isOver = false;
    private int lengthToWin = 3;
    
    public Board(char[][][] board)
    {
        this.board = board;
    }

    public Board(int size)
    {
        board = new char[size][size][size];
        initBoard();
    }

    public void setOver()
    {
        isOver = true;
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
    
    public boolean checkWinner(char player, int x, int y, int z) {
        // 13 unique direction vectors in 3D (we’ll check both + and −)
        int[][] dirs = {
            {1,0,0}, {0,1,0}, {0,0,1},          // axes
            {1,1,0}, {1,-1,0}, {1,0,1}, {1,0,-1}, {0,1,1}, {0,1,-1},  // face diags
            {1,1,1}, {1,1,-1}, {1,-1,1}, {1,-1,-1} // space diags
        };

        for (int[] d : dirs)
        {
            for (int i = 0; i < d.length; i++) {
                System.out.print(d[i] +" ");
            }
            int count = 1
                + countDirection(x, y, z,  d[0],  d[1],  d[2], player) 
                + countDirection(x, y, z, -d[0], -d[1], -d[2], player);

            if (count >= lengthToWin) return true;
        }
        return false;
    }

    // count same‐player cells from (x,y,z) stepping by (dx,dy,dz)
    private int countDirection(int x, int y, int z, int dx, int dy, int dz, int player)
    {
        int size = board.length;
        int c = 0;
        int xx = x + dx, yy = y + dy, zz = z + dz;
        while (xx >= 0 && xx < size && yy >= 0 && yy < size && zz >= 0 && zz < size && board[xx][yy][zz] == player)
        {
            c++;
            xx += dx;
            yy += dy;
            zz += dz;
        }
        return c;
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
