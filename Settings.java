package Default;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Settings 
{
    //default player count
    private int totalPlayer = 2;
    private int boardSize = 3; //default value
    //faster than list
    private Set<Character> marks = new LinkedHashSet<>(Arrays.asList('X', 'O'));

    public int getTotalPlayer() 
    {
        return totalPlayer;
    }

    public int getBoardSize() 
    {
        return boardSize;
    }

    public Set<Character> getMarks() 
    {
        return marks;
    }

    private boolean trySetTotalPlayer(int size)
    {
        if (size <= 0) {
            System.out.println("What do you mean?");
            System.out.println("Try again!");
            return false;
        }

        if (size == 1) {
            System.out.println("I'm not capable to create bot yet, fund required");
            System.out.println("Sorry but, Try again!");
            return false;
        }

        totalPlayer = size;
        return true;
    }
    
    public void setTotalPlayer()
    {
        System.out.println("Player size MUST greater than 1");
        Scanner scanner = new Scanner(System.in);
        int tempSize;

        tempSize = Utils.checkInputInt(scanner);

        while (!trySetTotalPlayer(tempSize)) 
        {
            tempSize = Utils.checkInputInt(scanner);
        }
        
        if (totalPlayer > marks.size())
            customMarks();
        
        Utils.clearConsole();
    }
    
    public void customMarks()
    {
        Set<Character> tempMarks = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < totalPlayer; i++) {
            boolean isConfirm = false;
            char newMark = ' ';
            while (!isConfirm) {
                System.out.println("Player " + (i + 1) + "'s mark will be?");
                newMark = scanner.next().charAt(0);
                System.out.println("Player " + (i + 1) + "'s mark will be " + newMark + "?");
                isConfirm = Utils.checkConfirm();
            }
            tempMarks.add(newMark);
        }

        marks = new LinkedHashSet<>(tempMarks);
    }
    
    public void setBoardSize()
    {
        System.out.println("Please enter NUMBER that GREATER or EQUAL 3");
        Scanner scanner = new Scanner(System.in);
        int tempSize = 0;

        while (true) {
            tempSize = Utils.checkInputInt(scanner);
            if (tempSize < 3) {
                System.out.println("GREATER or EQUAL 3");
                System.out.println("Please try again");
                continue;
            }

            boardSize = tempSize;
            break;
        }
    }
}
