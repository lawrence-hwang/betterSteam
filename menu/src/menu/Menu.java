package menu;

import bejeweled.Bejeweled;
import tfe.tfe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner in;

    boolean loggedIn;
    boolean RUNNING;
    private String currentUser;
//    private ArrayList<String> users;
    private HashMap<String, int[]> users;

    public Menu() {
        this.RUNNING = true;
        this.in = new Scanner(System.in);
        this.currentUser = null;
        this.loggedIn = false;
        this.users = new HashMap<String, int[]>();
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
                    String name = getStringInput("Enter username: ");
                    login(name);
                    break;
                }
                case 2: {
                    displayHighScores();
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
            int input = getIntInput("What would you like to do?\n"+"1) Play 2048\n" +
                    "2) Play Bejeweled\n3) Settings\n4) High Scores\n5) Logout\nEnter option as integer: ");

            switch (input) {
                case 1: {
                    // PLAY 2048
                    tfe tfe = new tfe();
                    tfe.startGame();
                    break;
                }
                case 2: {
                    // PLAY BEJEWELED
                    input = getIntInput("Single or Multiplayer?\n"+"1) Single\n" + "2) Multiplayer\nEnter option as integer: ");
                    switch(input){
                        case 1:{
                            Bejeweled game = new Bejeweled();
                            game.startGame();
                            int highScore = game.quit();
                            int[] updatedScores = {users.get(currentUser)[0], highScore};
                            users.put(currentUser, updatedScores);
                            break;
                        }
                        case 2:{
                            // Get other player name
                            String name = getStringInput("Enter player 2's name: ");
                            // Add other player to list of users
                            if (!users.containsKey(name)) {
                                users.put(name, new int[2]);
                            }
                            // Begin player 1 game
                            Bejeweled game = new Bejeweled();
                            game.startGame();
                            int highScore = game.quit();
                            int[] updatedScores = {users.get(currentUser)[0], highScore};
                            users.put(currentUser, updatedScores);

                            // Begin player 2 game
                            System.out.println("It's now " + name + "'s turn to play!");
                            game = new Bejeweled();
                            game.startGame();
                            int highScore2 = game.quit();
                            int[] updatedScores2 = {users.get(name)[0], highScore2};
                            users.put(name, updatedScores2);

                            //Print winner
                            if(highScore > highScore2){
                                System.out.println(currentUser + " wins with a score of " + highScore + " over " + name + "'s score of " + highScore2 + "!");
                            }
                            else if(highScore2 > highScore){
                                System.out.println(name + " wins with a score of " + highScore2 + " over " + currentUser + "'s score of " + highScore + "!");
                            }
                            else {
                                System.out.println("Wow, you both tied! Nice job!");
                            }
                            break;
                        }
                    }
                }
                case 3: {

                    // SETTINGS - User should be able to change their name and clear high score
                    input = getIntInput("Settings\n"+"1) Change name\n" + "2) Clear high score\nEnter option as integer: ");
                    switch (input){
                        case 1:{
                            //Here we change user name

                            break;
                        }
                        case 2:{
                            //Here we clear high score...
                            System.out.println("score cleared");

                            
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    // SHOW HIGH SCORES
                    displayHighScores();
                    break;
                }
                case 5: {
                    currentUser = null;
                    loggedIn = false;
                    break;
                }
            }
    }

    private void displayHighScores(){
        System.out.println("HIGH SCORES\n--------------------------------");
        System.out.println("2040\n----------------");
        users.forEach((k,v) -> System.out.println(String.format("%s: %d",k,v[0])));
        System.out.println("BEJEWELED\n----------------");
        users.forEach((k,v) -> System.out.println(String.format("%s: %d\n",k,v[1])));
    }
    private void login(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new int[2]);
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