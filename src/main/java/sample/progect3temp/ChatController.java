package sample.progect3temp;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChatController  implements Initializable {

GroupController groupController=new GroupController();

ManagerBasic managerBasic=new ManagerBasic();
Main main=new Main();

    @FXML
    private Label Menu;

    @FXML
    private ScrollPane chatSpace;

    @FXML
    private TextField inputMessage;

    @FXML
    private Label menuBack;

    @FXML
    private VBox messageVbox;

    @FXML
    private Button sendButton;

    @FXML
    private  AnchorPane slider;

    @FXML
    private Label usernameField;

    @FXML
    private Button backButton;


    @FXML
    private Button enterSearchIDButton;

    @FXML
     private VBox groupAndPvList;

    @FXML
    private Button chatStartButton;

    @FXML
    private TextField enteredID;
    @FXML
     private ScrollPane GAndPVListSpace;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchResult;


    @FXML
    private Text errorInSearchID;

    @FXML
    private Button newGroupButton;

    @FXML
    private Button crateGButton;

    @FXML
    private TextField enteredGroupID;

    @FXML
    private TextField enteredGroupName;

    @FXML
    private Text groupMakingError;






    @FXML
    void send(ActionEvent event) {

        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                chatSpace.setVvalue((Double) newValue);

            }
        }));



        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sendMessageText=inputMessage.getText();

                if (!sendMessageText.isEmpty())
                {
                    HBox hBox=new HBox();

                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));


                    Text text=new Text(sendMessageText);

                    TextFlow textFlow=new TextFlow(text);


                    textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: blue;");

                    textFlow.setPadding(new Insets(5,5,5,10));

                    text.setFill(Color.color(0,0,0));


                    hBox.getChildren().addAll(textFlow);

                    messageVbox.getChildren().add(hBox);


                    inputMessage.clear();


                }


            }
        });




    }

    @FXML
    void back(ActionEvent event) throws IOException {

        main.changeScene("entered-firstPage.fxml");
    }

    @FXML
    void backSearch(ActionEvent event) throws IOException {

        main.changeScene("chat-page.fxml");

    }
    @FXML
    void backGroup(ActionEvent event) throws IOException {
        main.changeScene("chat-page.fxml");
    }

    @FXML
    void goToSearchID(ActionEvent event) throws IOException {

        main.changeScene("chat-searchIds.fxml");

    }

    boolean exist=false;
    String tempEnteredID;
    @FXML
    void checkUserExistence(ActionEvent event) {

      tempEnteredID  = enteredID.getText();



        for (int i = 0; i < managerBasic.getUsers().size(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(tempEnteredID))
            {
                searchResult.setText(tempEnteredID);
                exist=true;
                break;
            }
        }

        if (!exist)
            errorInSearchID.setText("there is no user with this ID");


    }



    @FXML
    void startChat(ActionEvent event) throws IOException {
//        groupAndPvList=new VBox();
        if(exist)
        {





            groupAndPvList.heightProperty().addListener((new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                    GAndPVListSpace.setVvalue((Double) newValue);

                }
            }));

            chatStartButton.setOnAction(new EventHandler<ActionEvent>(){

                                       @Override
                                       public void handle(ActionEvent actionEvent) {

                                           HBox hBox=new HBox();

                                           hBox.setAlignment(Pos.CENTER_LEFT);
                                           hBox.setPadding(new Insets(5,5,5,10));


                                           Text text=new Text(tempEnteredID);

                                           TextFlow textFlow=new TextFlow(text);


                                           textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: blue;");

                                           textFlow.setPadding(new Insets(5,5,5,10));

                                           text.setFill(Color.color(0,0,0));


                                           hBox.getChildren().addAll(text);

                                           groupAndPvList.getChildren().add(hBox);

                                       }
                                   });

            main.changeScene("chat-page.fxml");

        }







    }



    @FXML
    void goToCreateNG(ActionEvent event) throws IOException {
        main.changeScene("newGroup-page.fxml");


    }

    String groupName;
    String groupID;
    @FXML
    void createGroup(ActionEvent event) throws IOException {
        groupName=enteredGroupName.getText();
        groupID=enteredGroupID.getText();

        boolean repID=false,repName=false;
        repName= groupController.checkGroupNameExistence(groupName);
        repID= groupController.checkGroupIDExistence(groupID);

        if (repName)
        {
            groupMakingError.setText("this name is repetitive");
        }
        else if (repID)
        {
            groupMakingError.setText("this ID is repetitive");
        }

        else
        {
            makeGroupFile(groupName,managerBasic.getCurrentUser(),groupID);
            main.changeScene("chat-page.fxml");

        }


    }


    public void makeGroupFile(String fileName, String adminUserName,String groupID)
    {
        //file name is group name

        File file = new File(fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write("GroupID: "+groupID+ " \n");
            fileWriter.write("Admin: " + adminUserName+ " \n");
            fileWriter.write("Members: " + adminUserName +" \n");

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {


if (slider!=null)
{
    slider.setTranslateX(-113);
    Menu.setOnMouseClicked(event ->
            {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));

                slide.setNode(slider);

                slide.setToX(0);
                slide.play();

                slider.setTranslateX(-113);
                slide.setOnFinished((ActionEvent e )->{

                    Menu.setVisible(false);
                    menuBack.setVisible(true);




                });
            }

    );

    menuBack.setOnMouseClicked(event ->
            {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));

                slide.setNode(slider);

                slide.setToX(-113);
                slide.play();

                slider.setTranslateX(0);
                slide.setOnFinished((ActionEvent e )->{

                    Menu.setVisible(true);
                    menuBack.setVisible(false);


                });
            }

    );


    //Write username in menu slide

    String UserName=managerBasic.getCurrentUser();

    usernameField.setText(UserName);



    //
}





    }
    
    








    public boolean checkUser(String userName) {
        ArrayList<String> tempNameSaving = new ArrayList<>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String name;

            while (readUsers.hasNextLine()) {
                name = readUsers.nextLine();
                tempNameSaving.add(name.split("\\:")[0]);
            }
            readUsers.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if (tempNameSaving.contains(userName))
            return true;

        return false;
    }

    public boolean fileExistence(String fileName) {
        File file = new File(fileName + ".txt");
        if (file.exists() && !file.isDirectory())
            return true;


        return false;
    }

    public void chatFileMaker(String fileName) {








        File file = new File(fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void chatHistoryMaker(String fileName, String text, String messageWriter) {


//
//        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//
//                chatSpace.setVvalue((Double) newValue);
//
//            }
//        }));
//
//
//        sendButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                String sendMessageText=inputMessage.getText();
//
//                if (!sendMessageText.isEmpty())
//                {
//                    HBox hBox=new HBox();
//
//                    hBox.setAlignment(Pos.CENTER_RIGHT);
//                    hBox.setPadding(new Insets(5,5,5,10));
//
//
//                    Text text=new Text(sendMessageText);
//
//                    TextFlow textFlow=new TextFlow(text);
//
//                    textFlow.setStyle("-fx-color:rgb (239,242,255) "+"-fx-backGround-color:rgb(15,125,242)" + "-fx-backGround-radius:20px");
//
//
//                    textFlow.setPadding(new Insets(5,510,5,10));
//
//                    text.setFill(Color.color(0.934,0.945,0.996));
//
//                    hBox.getChildren().add(textFlow);
//                    messageVbox.getChildren().add(hBox);
//
//
//                    inputMessage.clear();
//
//
//                }
//
//
//            }
//        });






        File file = new File(fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(messageWriter + ":" + text + "\t");
            fileWriter.write("#" + messageWriter + "%" + getMessageID(fileName, messageWriter) + "\n");

            fileWriter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getMessageID(String fileName, String messageWriter) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));

        if (br.readLine() == null) {
            return 1;
        } else {


            try {
                File usersFile = new File(fileName + ".txt");
                String line;

                String[] ID = new String[2];
                Scanner readUsers = new Scanner(usersFile);
                while (readUsers.hasNextLine()) {

                    line = readUsers.nextLine();
                    if (line.startsWith(messageWriter)) {
                        ID = line.split("\\#");
                    }


                }


                readUsers.close();

                if (ID[1] == null) {
                    return 1;
                } else
                    return Integer.parseInt(ID[1].split("\\%")[1]) + 1;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        return 1;
    }


    public String findMessageAndTakeFP(String fileName, String contact, String messageID) {

        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            StringBuilder partOfMessage = new StringBuilder();


            StringBuilder textFirstWords=new StringBuilder();

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                ID = line.split("\\#");


                String tempText = ID[0].split("\\:")[1];





                if (line.startsWith(contact))
                {
                    if (messageID.equals(ID[1]))
                    {

                        int numOfWords = tempText.split("\\s").length;
                        String[] textWords = tempText.split("\\s");


                        int counter = 0;


                        for (int i = 0; i < numOfWords; i++) {

                            if (!textWords[i].equals("REPLY") && !textWords[i].equals("TO") && !textWords[i].equals("FORWARDED") && !textWords[i].equals("FROM") && !textWords[i].equals("")) {
                                textFirstWords.append(textWords[i] + " ");
                                counter++;
                            }

                            if (counter == 3) {
                                return  textFirstWords.toString() ;

                            }

                        }



                        return textFirstWords.toString() ;
                    }
                }




            }


            readUsers.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }


    public boolean findAndEditMessage(String fileName, String messageID, String messageEditor, String editedText) {

        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(fileName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();

            int check=0;

            boolean fiveLast=fiveLastMessage(fileName,messageEditor,messageID);

            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return false;


            if (!fiveLast)
                return false;

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();







                    ID = line.split("\\#");


                    String tempText = ID[0].split("\\:")[1];

                if (line.startsWith(messageEditor))
                    if (messageID.equals(ID[1]))

                    {
                        boolean replied = false;

                        if (tempText.startsWith("REPLY")) {

                            replied = true;

                        }
                        if (tempText.startsWith("FORWARDED")) {

                            return false;

                        }


                        String tempText1;

                        if (replied) {

                            line = messageEditor + ":"+tempText.split("\\)")[0]+") " + editedText+"\t" + "#"+messageID;

                        }

                        else {
                            line = messageEditor + ":"+editedText+"\t" + "#"+messageID;

                        }



                    }



                inputBuffer.append(line);
                inputBuffer.append('\n');


                check=1;

            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(fileName + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            if (check==1)
            return true;

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }




    public String findAndForward(String messageFile, String messageID) {
        try {
            File usersFile = new File(messageFile + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            int PVorGroup=checkGroupOrPV(messageFile);



            if (PVorGroup==1)
            {
                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();

                    ID = line.split("\\#");


                    if (ID[1].equals(messageID))
                    {
                        String tempText = ID[0].split("\\:")[1];
                        boolean replied = false;

                        if (tempText.startsWith("REPLY")) {
                            replied = true;

                        }

                        String tempText1;
                        if (replied) {
                            tempText1 = tempText.split("\\)")[1];
                        }
                        else {
                            tempText1 = ID[0].split("\\:")[1];

                        }


                        return tempText1;
                    }



                }
            }

            else if (PVorGroup==2)
            {
                int counter=0;

                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();

                    if (counter>=3)
                    {
                        ID = line.split("\\#");


                        if (ID[1].equals(messageID))
                        {
                            String tempText = ID[0].split("\\:")[1];
                            boolean replied = false;

                            if (tempText.startsWith("REPLY")) {
                                replied = true;

                            }

                            String tempText1;
                            if (replied) {
                                tempText1 = tempText.split("\\)")[1];
                            }
                            else {
                                tempText1 = ID[0].split("\\:")[1];

                            }


                            return tempText1;
                    }



                    }

                    counter++;

                }

            }





        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteMessage(String fileName,String messageID,String messageDeleter)
    {

        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(fileName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();
            int check=0;

            boolean fiveLast=fiveLastMessage(fileName,messageDeleter,messageID);

            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return false;

            if (!fiveLast)
                return false;

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();







                ID = line.split("\\#");


                String tempText = ID[0].split("\\:")[1];


                if (line.startsWith(messageDeleter))
                    if (messageID.equals(ID[1]))

                    {

                        if (tempText.startsWith("FORWARDED")) {

                            return false;

                        }

                        line="";
                        check=1;

                    }



                inputBuffer.append(line);

                    if (!line.equals(""))
                inputBuffer.append('\n');




            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(fileName + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            if (check==1)
            return true;

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }



    public boolean fiveLastMessage (String fileName, String messageEditor,String messageID )
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            StringBuilder partOfMessage = new StringBuilder();
            String lastMessageID=null;
            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                ID = line.split("\\#");



                if (line.startsWith(messageEditor)) {

                    lastMessageID=line.split("\\#")[1];

                }


            }


            readUsers.close();
            if (lastMessageID==null)

                return true;


            else if (Integer.parseInt(lastMessageID.split("\\%")[1])-Integer.parseInt(messageID.split("\\%")[1])<=5)
            {
                return true;
            }




        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }


    public int  blocking (String fileName ,String userThatBlocks,String userThatIsBlocked)
    {
        // 1 for user that was blocked before . 2 for successful block

        //structure is    s.b blocked s.b
        try {
            File file = new File(fileName + ".txt");
            String line=null;

            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine())
            {
                line=readUsers.nextLine();
                break;
            }


            if (line!=null)
            {
                if (line.split("\\s")[1].equals("BLOCKED"))
                {
                    return 1;
                }

                else
                {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print(userThatBlocks+" BLOCKED "+userThatIsBlocked);
                    writer.close();
                }
            }
                else
            {
                PrintWriter writer = new PrintWriter(file);
                writer.print(userThatBlocks+" BLOCKED "+userThatIsBlocked);
                writer.close();

            }




        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return 2;
    }


    public boolean checkBlock (String fileName)
    {
        try {
            File file = new File(fileName + ".txt");
            String line=null;

            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine())
            {
                line=readUsers.nextLine();
                break;
            }
            if (line!=null)
            if (line.split("\\s")[1].equals("BLOCKED"))
            {
         return true;
            }




        }

        catch (Exception e) {
            e.printStackTrace();
        }
return false;


    }


    public boolean unblock (String fileName,String userThatUnblocks)
    {
        try {
            File file = new File(fileName + ".txt");
            String line=null;

            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine())
            {
                line=readUsers.nextLine();
                break;
            }
            if (!line.split("\\s")[0].equals(userThatUnblocks))
            {
                return false;
            }

            else
            {
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();

            }




        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return true;



    }


    public String showHistory(String fileName)
    {
        try{
            File usersFile = new File(fileName + ".txt");
            String line=null;


            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(fileName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();





            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return null;




            while (readUsers.hasNextLine())
            {
                line=readUsers.nextLine();

                inputBuffer.append(line);
                inputBuffer.append('\n');

            }
            return inputBuffer.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }




    public String searchMessageID (String fileName, String messageID)
    {

        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);






            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return null;



            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();







                ID = line.split("\\#");


                String tempText = ID[0].split("\\:")[1];


                    if (messageID.equals(ID[1]))

                    {
                        return line;

                    }


            }

            readUsers.close();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;


    }



    public String searchText(String fileName , String text)

    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            StringBuilder partOfMessage = new StringBuilder();

            StringBuilder textFirstWords=new StringBuilder();

            StringBuilder textFirstWordsFinal=new StringBuilder();

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                ID = line.split("\\#");


                String tempText = line.split("\\:")[1];










                if (tempText.contains(text)) {

                    int numOfWords=tempText.split("\\s").length;
                   String [] textWords=tempText.split("\\s");




                   int counter=0;


                       for (int i = 0; i < numOfWords; i++) {

                           if (!textWords[i].equals("REPLY")&&!textWords[i].equals("TO")&&!textWords[i].equals("FORWARDED")&&!textWords[i].equals("FROM")&&!textWords[i].equals(""))
                           {
                               textFirstWords.append(textWords[i]+" ");
                               counter++;
                           }

                           if (counter==3)
                           {
                               textFirstWordsFinal.append(textFirstWords.toString() +"\t#"+ ID[1]+"\n") ;
                               break;
                           }

                       }


                    textFirstWords.replace(0,textFirstWords.length(),"");


                }


            }


            readUsers.close();

            return textFirstWordsFinal.toString();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String showSearchedMessage (String fileName , String messageID)
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);


            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return null;

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                ID = line.split("\\#");

                if (messageID.equals(ID[1]))

                    {

                    return line;
                    }


            }


            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }


    public String searchMessageIDGroup (String fileName, String messageID)
    {

        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);


            int counter=0;




            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return null;



            while (readUsers.hasNextLine()) {
                line = readUsers.nextLine();

if (counter>=3) {




    ID = line.split("\\#");


    String tempText = ID[0].split("\\:")[1];


    if (messageID.equals(ID[1])) {
        return line;

    }
}


                counter++;

            }

            readUsers.close();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;


    }



    public String searchTextGroup(String fileName , String text)

    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            StringBuilder partOfMessage = new StringBuilder();

            StringBuilder textFirstWords=new StringBuilder();

            StringBuilder textFirstWordsFinal=new StringBuilder();

            int counterTotal=0;

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();
                if (counterTotal>=3)
                {


                ID = line.split("\\#");


                String tempText = line.split("\\:")[1];


                if (tempText.contains(text)) {

                    int numOfWords = tempText.split("\\s").length;
                    String[] textWords = tempText.split("\\s");


                    int counter = 0;


                    for (int i = 0; i < numOfWords; i++) {

                        if (!textWords[i].equals("REPLY") && !textWords[i].equals("TO") && !textWords[i].equals("FORWARDED") && !textWords[i].equals("FROM") && !textWords[i].equals("")) {
                            textFirstWords.append(textWords[i] + " ");
                            counter++;
                        }

                        if (counter == 3) {
                            textFirstWordsFinal.append(textFirstWords.toString() + "\t#" + ID[1] + "\n");
                            break;
                        }

                    }


                    textFirstWords.replace(0, textFirstWords.length(), "");


                }

            }

                counterTotal++;

            }


            readUsers.close();

            return textFirstWordsFinal.toString();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String showSearchedMessageGroup (String fileName , String messageID)
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            int counter=0;

            boolean checkIfWasBlocked=checkBlock(fileName);

            if (checkIfWasBlocked)

                return null;

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                if (counter>=3)
                {


                    ID = line.split("\\#");

                    if (messageID.equals(ID[1]))

                    {

                        return line;
                    }
                }


                counter++;

            }


            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    public int checkGroupOrPV (String name)
    {
        // 1 is PV and 2 is group
        if(name.contains("&"))
        {
            return 1;

        }

        return 2;
    }



}

