package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML

    private Text loginError;

    @FXML
    private TextField passWord;

    @FXML
    private TextField username;



    @FXML
    private TextField answer;

    @FXML
    private Button loginButton2;

    @FXML
    private TextField username2;

 int condition=0;
    @FXML



   Main main= new Main();


    @FXML
    void goBack(ActionEvent event) throws IOException {

        main.changeScene("first-page.fxml");
    }

    @FXML
    void goBackP2(ActionEvent event) throws IOException {

        main.changeScene("login-page.fxml");
    }

    @FXML
    void login(ActionEvent event) throws IOException {


        condition=checkUser();

        loginErrorType(condition);
        if (condition==1)
        {
            main.changeScene("entered-firstPage.fxml");
        }

    }



    @FXML
    void forgotPass(ActionEvent event) throws IOException {

        main.changeScene("login-passForgot.fxml");

    }




    @FXML
    void login2(ActionEvent event) throws IOException {



        condition=checkAnswer();

        loginErrorType(condition);

        if (condition==1)
        {
            main.changeScene("entered-firstPage.fxml");
        }


    }


    private int checkUser() throws FileNotFoundException {

        String userName=username.getText();
         String password=passWord.getText();


        HashMap<String, String> usersAndPasswords = new HashMap<String, String>();
        try {
            File usersFile = new File("usersAndPasswords.txt");


            Scanner readUsers = new Scanner(usersFile);
            String nameAndPass;
            String[] splitNameAndPass;
            while (readUsers.hasNextLine()) {
                nameAndPass = readUsers.nextLine();
                splitNameAndPass = nameAndPass.split(":");
                usersAndPasswords.put(splitNameAndPass[0], splitNameAndPass[1]);
            }
            readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        if(usersAndPasswords.containsKey(userName) && usersAndPasswords.get(userName).equals(password)){

            return 1;
        }
        else if(!usersAndPasswords.containsKey(userName)){

            return -1;
        }
        else if(usersAndPasswords.containsKey(userName) && !usersAndPasswords.get(userName).equals(password)){

            return -2;
        }

        return 0;
    }

    private int checkAnswer(){

       String tempAnswer = answer.getText();
        String username=username2.getText();


        HashMap<String, String> usersAndAnswers = new HashMap<String, String>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String nameAndPass;
            String[] splitNameAndAnswer;
            while (readUsers.hasNextLine()) {
                nameAndPass = readUsers.nextLine();
                splitNameAndAnswer = nameAndPass.split(":");
                usersAndAnswers.put(splitNameAndAnswer[0], splitNameAndAnswer[2]);
            }
            readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        if(usersAndAnswers.containsKey(username) && usersAndAnswers.get(username).equals(tempAnswer)){
            return 1;

        }
        else if(!usersAndAnswers.containsKey(username)){

            return -1;

        }
        else if(usersAndAnswers.containsKey(username) && !usersAndAnswers.get(username).equals(tempAnswer))
        {
            return -3;

        }
        return 0;
    }


    public void loginErrorType(int type) throws IOException {
        if (type==-1)
            loginError.setText("Wrong username");

        else if (type==-2)
            loginError.setText("Wrong password");
        else if (type==-3)
            loginError.setText("Wrong answer");



    }





}
