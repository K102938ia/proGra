package sample.progect3temp;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInController {

Main main=new Main();


 static String tempUsername,tempPass;




    @FXML
    private TextField confirmedPass;

    @FXML
    private Button nextButton;

    @FXML
    private TextField passWord;

    @FXML
    private Text signInError;

    @FXML
    private TextField username;


    @FXML
    void goBack(ActionEvent event) throws IOException {

        main.changeScene("first-page.fxml");
    }

    @FXML
    void goBackP2(ActionEvent event) throws IOException {

        main.changeScene("signin-page.fxml");
    }

    @FXML


    void next(ActionEvent event) throws IOException {

        signInUserName();

        signInPassW();

        signInPassCo();

       boolean continueCheck= signInErrorType(isValidUN,isValidPa,isValidCoPa,password);

       if (continueCheck)
       {
           main.changeScene("signin-page2.fxml");

           tempUsername=userName;

           tempPass  =password;
       }
    }

    @FXML
    private TextField Question;

    @FXML
    private Button finishButton;

    @FXML
    private Text signInError2;

    @FXML
    private TextField type;
    @FXML
    void finishSignIn(ActionEvent event) throws IOException {

        setSecurityQuestion( );

        signInUserType();

        boolean continueCheck=signInErrorType2(isValidUT);

        if (continueCheck)
        {
            signInFin();

            main.changeScene("entered-firstPage.fxml");
        }




    }




    String userName;
    String password;
    String passwordConfirm;
    String securityQuestion;
    String UType;


    ManagerBasic managerBasic;


    public SignInController() {
       this.managerBasic =new ManagerBasic();
    }





    boolean isValidUN =false;
    boolean isValidPa =false;
    boolean isValidCoPa =false;
    boolean isValidUT =false;


    int passIsValid=-5;




        public void signInUserName()
    {
        userName=username.getText();

            if(userName!=null)

            {
                boolean nameIsValid = checkUsername(userName);


                if(nameIsValid )
                isValidUN =true;

            }
        }


        public void signInPassW()
        {
           password= passWord.getText();
            if(password!=null)
            {
                passIsValid = checkPassword(password);


                if(passIsValid==1){
                    isValidPa =true;

                }


            }
        }

        public void signInPassCo()
        {
            passwordConfirm=confirmedPass.getText();

            if(passwordConfirm!=null&&password!=null)
                if(passwordConfirm.equals(password)){

                    isValidCoPa=true;
                }
        }



        public void signInUserType()
        {
            UType=type.getText();
            if(UType!=null)
            {
if(UType.equalsIgnoreCase("personal")||UType.equalsIgnoreCase("business"))
{
    isValidUT=true;
}
            }
        }

    public void setSecurityQuestion( ) {
        securityQuestion = Question.getText();
    }



        public void signInFin(){

            if(tempUsername!=null&&tempPass!=null&&securityQuestion!=null&&UType!=null)
            {


                User user1 = new User(tempUsername,0,0);

                managerBasic.setUsers(user1);

                managerBasic.setCurrentUser(tempUsername);


                addUser(tempUsername, tempPass, securityQuestion,UType);
            }


        }


        public boolean signInErrorType(boolean isValidUN ,boolean isValidPa,boolean isValidCoPa ,String password )
        {

            if (!isValidUN)
            {
                signInError.setText("this username is already taken");
                return false;
            }

            else if (!isValidPa)
            {
                int temp=checkPassword(password);
                if (temp==-1)
                signInError.setText("password must contain at least 8 characters");
                else if (temp==-2)
                    signInError.setText("password must contain both letters and numbers");

                return false;
            }

            else if(!isValidCoPa)
            {
                signInError.setText("second password is not equal to first one !");

                return false;
            }

return true;
        }

        public boolean signInErrorType2(boolean isValidUT)
        {
            if (!isValidUT)
            {
                signInError2.setText("invalid input for account type ");
                return false;
            }


            return true;
        }





    private boolean checkUsername( String username ) {
        ArrayList<String> usernames = new ArrayList<>();
        String name;
        String[] splitName;
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            while (readUsers.hasNextLine()) {
                name = readUsers.nextLine();
                splitName = name.split(":");
                usernames.add(splitName[0]);
            }
            readUsers.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(usernames.contains(username)){

            return false;
        }
        else{
            return true;
        }
    }


    private int checkPassword(String password){
        boolean isValid = false;
        boolean letter = false;
        boolean num = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if((int)c > 48 && (int)c <57){
                num = true;
            }
           else if(((int)c > 65 && (int)c < 90) || ((int)c > 97 && (int)c < 122)){
               letter = true;
            }
           if(letter == true && num == true){
               break;
           }
        }

        if(num == true && letter == true){
            isValid = true;
        }

        if(password.length() < 8){
            return -1;
        }
        else if(!isValid && password.length() >= 8){
            return -2;
        }
        else if( isValid && password.length() >= 8)
            return 1;


        return 0;


    }


//    private int checkPassword(String password){
//        boolean isValid = false;
//        /*
//        boolean letter = false;
//        boolean num = false;
//        for (int i = 0; i < password.length(); i++) {
//            char c = password.charAt(i);
//            if((int)c > 48 && (int)c <57){
//                num = true;
//            }
//           else if(((int)c > 65 && (int)c < 90) || ((int)c > 97 && (int)c < 122)){
//               letter = true;
//            }
//           if(letter == true && num == true){
//               break;
//           }
//        }
//         */
//        String regex = "^[A-Za-z0-9]+$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(password);
//        if(matcher.matches ()){
//            isValid = true;
//        }
///*
//        if(num == true && letter == true){
//            isValid = true;
//        }
// */
//
//        if(password.length() < 8){
//            return -1;
//        }
//        else if(!isValid && password.length() >= 8){
//            return -2;
//        }
//        else if( isValid && password.length() >= 8)
//            return 1;
//
//
//        return 0;
//
//
//    }


    private void addUser(String userName, String password, String securityQuestion, String UType){
        File file = new File("usersAndPasswords.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write( userName+ ":" +password+ ":" + securityQuestion +":"+ UType + "\n");
            fileWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}

