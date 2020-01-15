package com.JackandKelly.hangman;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private char[] playerGuess;

    public static void main(String[] args) {
        Main hangmanGame = new Main();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] possibleWords = {"juice", "hello", "test"};

        boolean weArePlaying = true;
        while (weArePlaying == true) {
            System.out.println("---------------------------------------------------------------------------\nWelcome to Hangman. This is a game where we will test your wordy skills.\nYou will have to guess the letters to a mystery word.\n---------------------------------------------------------------------------");

            char[] randomWordToGuess = possibleWords[random.nextInt(possibleWords.length)].toCharArray();
            int amountOfGuesses = randomWordToGuess.length + 7;
            hangmanGame.playerGuess = new char[amountOfGuesses - 7];
            hangmanGame.fillArrayBlank(hangmanGame.playerGuess);
            ArrayList<Character> guessedLetters = new ArrayList<Character>();

            boolean wordIsGuessed = false;
            int tries = 0;
            boolean inGuessedLetters = false;
            while(!wordIsGuessed && tries < amountOfGuesses) {
                System.out.print("Current guesses: ");
                hangmanGame.printArray(hangmanGame.playerGuess);
                System.out.printf("You have %d lives left.\n", amountOfGuesses - tries);
                System.out.println("Enter a character... (lowercase)");
                char playerInput = scanner.nextLine().charAt(0);
                String playerInputInString = String.valueOf(playerInput).toLowerCase();
                playerInput = playerInputInString.charAt(0);


                if (!hangmanGame.guessWasCorrect(playerInput, randomWordToGuess, hangmanGame.playerGuess)) {
                    tries++;
                    for (int j = 0; j < guessedLetters.size(); j++) {
                        if(playerInput == guessedLetters.get(j)) {
                            inGuessedLetters = true;
                        }

                    }

                    if(!inGuessedLetters) {
                        guessedLetters.add(playerInput);
                    }

                }

                if (playerInput=='-'){
                    weArePlaying = false;
                    wordIsGuessed = true;
                } else {



                    for (int i = 0; i < randomWordToGuess.length; i++) {
                        if(playerInput==randomWordToGuess[i]){
                            hangmanGame.playerGuess[i] = playerInput;

                            for (int j = 0; j < guessedLetters.size(); j++) {
                                if(playerInput == guessedLetters.get(j)) {
                                    inGuessedLetters = true;
                                }

                            }

                            if(!inGuessedLetters) {
                                guessedLetters.add(playerInput);
                            }



                            System.out.println("You are correct.");

                        }

                    }



                    if(hangmanGame.ifTheWordGuessed(hangmanGame.playerGuess)) {
                        wordIsGuessed = true;
                        System.out.println("You Win!");

                    }
                }

                System.out.println("You have guessed: " + guessedLetters);




            }

            if (!wordIsGuessed){
                System.out.println("You ran out of guesses");
            }
            System.out.println("Do you want to play another game? (yes/no)");
            String anotherGame = scanner.nextLine();
            if(anotherGame.equals("yes")){
                weArePlaying = true;
            }else if(anotherGame.equals("no")){
                weArePlaying = false;
            }

        }

        System.out.println("Game Over...");


        // write your code here
    }

    private void fillArrayBlank(char[] array) {

        for(int i = 0; i < array.length; i++){
            array[i] = '_';
        }


    }

    private void printArray(char[] array) {

        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println();

    }

    private boolean ifTheWordGuessed(char[] playerGuess) {
        for (int i = 0; i < playerGuess.length; i++) {
            if (playerGuess[i]=='_') {
                return false;
            }
        }
        return true;
    }

    private boolean guessWasCorrect(char playerInput, char[] randomWordArray, char[] playerGuess){
        boolean result = false;
        for (int i = 0; i < randomWordArray.length; i++) {
             if(playerInput == randomWordArray[i]){
                 result = true;
                 playerGuess[i] = playerInput;
             }
        }
        return result;

    }



}
