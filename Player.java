package Default;

import java.util.Iterator;
import java.util.Scanner;

public class Player 
{
    private Iterator<Character> markIterator;
    private Settings settings = new Settings();

    public Player(Settings settings)
    {
        this.settings = settings;
    }

    public char currentPlayer()
    {
        if (markIterator == null || !markIterator.hasNext())
            markIterator = settings.getMarks().iterator();
        return markIterator.next();
    }

    public int[] setMove()
    {
        int[] coords = {-2, -2, -2};
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            String line;
            String[] tokens;

            System.out.println("x y z:");
            line = scanner.nextLine();
            // \\s+ is a regular expression (regex) pattern used to match one or more whitespace characters.
            // \\s -> matches any whitespace character
            // + -> means "one or more"

            tokens = line.trim().split("\\s+");


            for (int i = 0; i < tokens.length; i++)
            {
                if (i > 3)
                    continue;
                
                coords[i] = Utils.tryParseInt(tokens[i]) - 1;
            }
            System.out.println("Your input will be x: " + (coords[0] + 1) + ", y: " + (coords[1] + 1) + ", z: " + (coords[2] + 1));
            
            if (Utils.checkConfirm())
                break;
        }
        
        return coords;
    }
}
