package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner in;

    boolean loggedIn;
    boolean RUNNING;
    private String currentUser;
    private ArrayList<String> users;

    public Menu() {
        this.RUNNING = true;
        this.in = new Scanner(System.in);
        this.currentUser = null;
        this.loggedIn = false;
        this.users = new ArrayList<String>();
    }

    public void mainLoop() {
        while (RUNNING) {
            if (loggedIn) {
                menuLoop();
            } else {
                loginLoop();
            }
        }
    }

    public void loginLoop() {
            System.out.println("Welcome to BetterSteam!\n--------------------------------");
            int input = getIntInput("What would you like to do?\n"+"1) Login\n" +
                    "2) See High Scores\n3) Quit\nEnter option as integer: ");

            switch (input) {
                case 1: {
                    String name = getStringInput("Enter username");
                    login(name);
                    break;
                }
                case 2: {
                    showHighScores();
                    break;
                }
                case 3: {
                    System.out.println("Goodbye!");
                    this.RUNNING = false;
                    break;
                }
            }
    }

    public void menuLoop() {
            System.out.println("MAIN MENU\n--------------------------------");
            int input = getIntInput("What would you like to do?\n"+"1) Play Tetris\n" +
                    "2) Play Bejeweled\n3) Settings\n4) High Scores\n5) Logout\nEnter option as integer: ");

            switch (input) {
                case 1: {
                    // PLAY TETRIS
                    break;
                }
                case 2: {
                    // PLAY BEJEWELED
                    break;
                }
                case 3: {
                    // SETTINGS
                    break;
                }
                case 4: {
                    // SHOW HIGH SCORES
                    break;
                }
                case 5: {
                    currentUser = null;
                    loggedIn = false;
                    break;
                }
            }
    }

    private void login(String username) {
        if (!users.contains(username)) {
            users.add(username);
        }
        loggedIn = true;
        currentUser = username;
    }

    private void showHighScores() {
        // FILL OUT LATER
    }

    private String getStringInput(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        String getInput = in.next();
        System.out.println();
        return getInput;
    }

    private int getIntInput(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        int getInput = 1;
        try {
            getInput = in.nextInt();
        } catch(InputMismatchException e) {
            getInput = 0;
        }
        System.out.println();
        return getInput;
    }

    public static void main(String args[]) {
        Menu menu = new Menu();
        menu.mainLoop();
    }
}