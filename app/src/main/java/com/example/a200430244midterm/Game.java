package com.example.a200430244midterm;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Game {
    private String gameId;
    private String firstName;
    private String lastName;
    private String userChoice;
    private String androidChoice;
    private String winner;

    //Default Constructor
    public Game() {

    }

    //Constructor with all the properties
    public Game(String gameId, String firstName, String lastName, String userChoice, String androidChoice, String winner) {
        this.gameId = gameId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userChoice = userChoice;
        this.androidChoice = androidChoice;
        this.winner = winner;
    }

    //Getters
    public String getGameId() {
        return gameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public String getAndroidChoice() {
        return androidChoice;
    }

    public String getWinner() {
        return winner;
    }
}
