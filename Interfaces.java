package Default;

import java.util.Scanner;

public class Interfaces 
{
    Settings settings = new Settings();
    Board board = new Board(3);

    public void start()
    {
        mainMenu();
    }

    private void displayMainMenu()
    {
        System.out.println("1. Play");
        System.out.println("2. Tutorial");
        System.out.println("3. Settings");
        System.out.println("4. Exit");
    }

    private void displaySettings()
    {
        System.out.println("1. Change total player (Current: " + settings.getTotalPlayer() + ")");
        System.out.println("2. Custom marks (Current: " + settings.getMarks() + ")");
        System.out.println("3. Custom board size (Current: " + settings.getBoardSize() + ")");
        System.out.println("x. Back.");
    }
    
    public void displayTutorial()
    {
        System.out.println("Start number is 1.");
        System.out.println("Please do not try to destroy my system.");
        System.out.println("That's all.");
    }

    private void play()
    {
        board = new Board(settings.getBoardSize());
        Player player = new Player(settings);
        char currentPlayer = ' ';
        while (!board.isOver()) 
        {
            currentPlayer = player.currentPlayer();
            Utils.clearConsole();
            board.printBoard();
            System.out.println(currentPlayer + "'s turn: ");
            
            int[] coords = player.setMove();
            while (!board.setMove(currentPlayer, coords[0], coords[1], coords[2])) 
                coords = player.setMove();

            boolean winner = board.checkWinner(currentPlayer, coords[0], coords[1], coords[2]);
            if(!winner)
                continue;
            
            System.out.println("Player " + currentPlayer + " won!");
            board.setOver();
        }
    }

    private void mainMenu()
    {
        Scanner scanner = new Scanner(System.in);
        displayMainMenu();
        char input = Character.toLowerCase(scanner.next().charAt(0));

        while (true) 
        {
            Utils.clearConsole();
            switch (input) 
            {
                case '1':
                    play();
                    break;
                case '2':
                    displayTutorial();
                    break;
                case '3':
                    settings();
                    break;
                case '4':
                    exit();
                    break;
                default:
                    System.out.println("Invalid input! Please try again.");
                    break;
            }

            displayMainMenu();
            input = Character.toLowerCase(scanner.next().charAt(0));
        }
        
    }

    private void settings()
    {
        Scanner scanner = new Scanner(System.in);
        displaySettings();
        char input = Character.toLowerCase(scanner.next().charAt(0));

        while (true) 
        {
            Utils.clearConsole();
            switch (input) 
            {
                case '1':
                    settings.setTotalPlayer();
                    break;
                case '2':
                    settings.customMarks();
                    break;
                case '3':
                    settings.setBoardSize();
                    break;
                default:
                    break;
            }

            if (input == 'x')
                break;
            else
                System.out.println("Invalid input! Please try again.");
            
            displaySettings();
            input = Character.toLowerCase(scanner.next().charAt(0));
        }

        mainMenu();
    }
    
    private void exit()
    {
        if (!Utils.checkConfirm())
            return;
        
        Utils.exitGame();
    }
}
