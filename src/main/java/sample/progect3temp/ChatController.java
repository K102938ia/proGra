package sample.progect3temp;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChatController  implements Initializable {

    GroupController groupController=new GroupController();

    ManagerBasic managerBasic=new ManagerBasic();
    Main main=new Main();

    boolean nowUCanChat=false;
    static String currentChatFileName=null;

    @FXML
    private VBox chatOptionVbox;
    @FXML
    private Label Menu;

    @FXML
    private Label errorMOP;

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
    private AnchorPane messageOptionsSlider;

    @FXML
    private Label usernameField;

    @FXML
    private Button backButton;


    @FXML
    private Button enterSearchIDButton;

    @FXML

    private VBox groupAndPvList;

    @FXML
    private Button ReplyButton;
    @FXML
    private Button ForwardButton;
    @FXML
    private Button deleteButton;

    @FXML
    private AnchorPane chatOptions;

    @FXML
    private Button editButton;
    @FXML
    private VBox messageOptions;

    @FXML
    private VBox forwardChoices;

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
    private HBox contactInf;

    @FXML
    private Button messageOpSlideClose;

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
    private Button showChatListButton;

    @FXML
    private Button forwardBack;

    @FXML
    private TextField inputMessageID;

    @FXML
    private Button searchIDButton;

    @FXML
    private TextField searchIDResult;

    @FXML
    private Text searchIDError;


    @FXML
    private Button searchTextButton;

    @FXML
    private Text searchTextError;

    @FXML
    private TextField searchTextResult;



    @FXML
    private TextField inputText;

    @FXML
    private Button changeNameApplyButton;

    @FXML
    private Text changeNameError;

    @FXML
    private TextField inputNewName;


    @FXML
    private Button changeIDApplyButton;

    @FXML
    private Text changeIDError;

    @FXML
    private TextField inputNewID;

    @FXML
    private TextField IDField;

    @FXML
    private TextField adminField;

    @FXML
    private TextField membersField;

    @FXML
    private TextField nameField;



    @FXML
    private Button addButton;

    @FXML
    private TextField followingResult;

    @FXML
    private TextField inputID;

    @FXML
    private Button showButton;

    @FXML
    private Text addError;

    @FXML
    private Text showFollowingError;

    @FXML
    private TextField inputIDRemove;

    @FXML
    private Text removeError;

    @FXML
    private Text showMemberError;

    @FXML
    private TextField membersResult;

    @FXML
    private AnchorPane imageSendSlide;
    @FXML
    private TextField inputImageName;
    @FXML
    private Button imageSildeButton;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void back(ActionEvent event) throws IOException {

        //main.changeScene("entered-firstPage.fxml");
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")) {
            root = FXMLLoader.load (getClass ().getResource ("PersonalHomePage.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            root = FXMLLoader.load (getClass ().getResource ("BusinessHomePage.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }
    }


    @FXML
    void backForward(ActionEvent event) throws IOException {
        nowUCanChat=false;

        main.changeScene("chat-page.fxml");
    }

    @FXML
    void backSearch(ActionEvent event) throws IOException {

        nowUCanChat=false;
        currentChatFileName=null;
        main.changeScene("chat-page.fxml");

    }
    @FXML
    void backGroup(ActionEvent event) throws IOException {
        nowUCanChat=false;
        currentChatFileName=null;
        main.changeScene("chat-page.fxml");
    }

    @FXML
    void goToSearchID(ActionEvent event) throws IOException {

        main.changeScene("chat-searchIds.fxml");

    }
    @FXML
    void goToCreateNG(ActionEvent event) throws IOException {
        main.changeScene("newGroup-page.fxml");


    }















    boolean imageSendMenu=false;

    @FXML
    void imageSlide(ActionEvent event) {

        inputImageName.setText("");


        if (!imageSendMenu) {


            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));

            slide.setNode(imageSendSlide);

            slide.setToX(0);
            slide.play();

            imageSendSlide.setTranslateX(210);

            imageSendMenu = true;
            imageSildeButton.setVisible(false);



        }
        else {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));

            slide.setNode(imageSendSlide);

            slide.setToX(210);
            slide.play();

            imageSendSlide.setTranslateX(0);


            imageSendMenu = false;

            imageSildeButton.setVisible(true);
        }





    }



    @FXML
    void sendImage(ActionEvent event) {

        String tempImageName=inputImageName.getText();
        imageSildeButton.setVisible(true);
        imageSendSlide.setTranslateX(210);


        boolean blockedChat=false;


        if (checkGroupOrPV(currentChatFileName)==1)
        {
            if (checkBlock(currentChatFileName))
                blockedChat=true;

        }

        if (!blockedChat)

            if (nowUCanChat)

            {






                if (!tempImageName.isEmpty())
                {
                    messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                            chatSpace.setVvalue((Double) newValue);

                        }
                    }));




                    ImageView imageView=new ImageView();

                    Image image=new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/"+tempImageName)));

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);

                    imageView.setImage(image);



                    String messageID= null;
                    try {
                        messageID = "#"+managerBasic.getCurrentUser()+"%"+getMessageID(currentChatFileName,managerBasic.getCurrentUser());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    HBox hBox=new HBox();
                    hBox.setId(messageID);

                    hBox.setOnMouseClicked(this:: clickedOnMessage);



                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));


                    //filling the file

                    if (currentChatFileName!=null)
                        chatHistoryMaker(currentChatFileName,"IMAGE "+ tempImageName ,managerBasic.getCurrentUser());



                    hBox.getChildren().add(imageView);

                    messageVbox.getChildren().add(hBox);


                    inputImageName.clear();




                }






            }









    }




    static  String fileNameForward=null,messageIDForward=null;




    boolean editing=false;
    static HBox editingHBox =null;

    @FXML
    void send(ActionEvent event) {

        boolean blockedChat=false;

        if (checkGroupOrPV(currentChatFileName)==1)
        {
            if (checkBlock(currentChatFileName))
                blockedChat=true;

        }

        if (!blockedChat)

            if (nowUCanChat)
            {
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


                        if (!sendMessageText.startsWith("TYPE EDITED VERSION"))

                        {
                            if (!sendMessageText.isEmpty())
                            {


                                String messageID= null;
                                try {
                                    messageID = "#"+managerBasic.getCurrentUser()+"%"+getMessageID(currentChatFileName,managerBasic.getCurrentUser());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                HBox hBox=new HBox();
                                hBox.setId(messageID);
                                hBox.setOnMouseClicked(this:: clickedOnMessage);



                                hBox.setAlignment(Pos.CENTER_RIGHT);
                                hBox.setPadding(new Insets(5,5,5,10));


                                //filling the file

                                if (currentChatFileName!=null)
                                    chatHistoryMaker(currentChatFileName,sendMessageText,managerBasic.getCurrentUser());

                                //





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

                        else
                        {

                            StringBuilder tempEdited=new StringBuilder();

                            tempEdited.append(sendMessageText);

                            tempEdited.replace(0,20,"");

                            Text tempText = new Text(tempEdited.toString());


                            boolean editionPossibility=findAndEditMessage(currentChatFileName, editingHBox.getId().split("\\#")[1], managerBasic.getCurrentUser(), tempEdited.toString());

                            if (editionPossibility)
                            {

                                inputMessage.clear();



                                editingHBox.getChildren().clear();


//                            editingHBox.getChildren().add(tempText);



                                editingHBox.setAlignment(Pos.CENTER_RIGHT);
                                editingHBox.setPadding(new Insets(5,5,5,10));









                                TextFlow textFlow=new TextFlow(tempText);


                                textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: blue;");

                                textFlow.setPadding(new Insets(5,5,5,10));

                                tempText.setFill(Color.color(0,0,0));


                                editingHBox.getChildren().addAll(textFlow);

                                messageVbox.getChildren().add(editingHBox);

                                editing=false;
                                editingHBox=null;





                            }
                            else {

                                errorMOP.setText("you can't edit this message");

                                editing=false;
                                editingHBox=null;
                                inputMessage.clear();

                            }



                        }

                    }

                    private void clickedOnMessage( MouseEvent mouseEvent) {



                        errorMOP.setText("");
                        HBox tempHBox=(HBox) mouseEvent.getSource();

//        System.out.println(mouseEvent.getSource());

                        String clickedMessageID= tempHBox.getId().split("\\#")[1];
                        String messageOwner=tempHBox.getId().split("\\#")[1].split("\\%")[0];




                        messageOptionsSlider.setTranslateX(-155);


                        TranslateTransition slide = new TranslateTransition();
                        slide.setDuration(Duration.seconds(0.5));

                        slide.setNode(messageOptionsSlider);

                        slide.setToX(0);
                        slide.play();

                        messageOptionsSlider.setTranslateX(0);


                        ReplyButton.setOnMouseClicked(event ->
                                {
                                    inputMessage.clear();


                                    String messageFirstPart=findMessageAndTakeFP(currentChatFileName,messageOwner,clickedMessageID);

                                    inputMessage.setText("REPLY TO ( " + messageFirstPart+ " ) ");


                                    slide.setDuration(Duration.seconds(0.5));

                                    slide.setNode(messageOptionsSlider);

                                    slide.setToX(-155);
                                    slide.play();

                                    messageOptionsSlider.setTranslateX(0);

                                }

                        );


                        deleteButton.setOnMouseClicked(event ->
                                {
                                    if (deleteMessage(currentChatFileName,clickedMessageID,managerBasic.getCurrentUser()))
                                    {
                                        tempHBox.getChildren().clear();


                                        slide.setDuration(Duration.seconds(0.5));

                                        slide.setNode(messageOptionsSlider);

                                        slide.setToX(-155);
                                        slide.play();

                                        messageOptionsSlider.setTranslateX(0);
                                    }

                                    else
                                    {
                                        errorMOP.setText("you can't delete this message");
                                    }


                                }

                        );

                        editButton.setOnMouseClicked(event ->
                                {

                                    inputMessage.setText("TYPE EDITED VERSION ");

                                    editingHBox=tempHBox;


//                                    slide.setDuration(Duration.seconds(0.5));
//
//                                    slide.setNode(messageOptionsSlider);
//
//                                    slide.setToX(-155);
//                                    slide.play();
//
//                                    messageOptionsSlider.setTranslateX(0);


                                }

                        );


                        ForwardButton.setOnMouseClicked(event ->
                                {


                                    try {
                                        main.changeScene("forward-page.fxml");



                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }






                                }

                        );

                        messageIDForward=clickedMessageID;



                    }
                });






            }





    }



    boolean exist=false;
    static String tempEnteredID;
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



        if (exist) {


            if (!fileExistence(managerBasic.getCurrentUser()+"PGNames"))
            {
                chatFileMaker(managerBasic.getCurrentUser()+"PGNames");
            }


            for (int i = 0; i < managerBasic.getUsers().size(); i++)
            {

                if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
                {

                    if (!checkGRAndPVNameExistenceFile(managerBasic.getCurrentUser(),managerBasic.getCurrentUser()+"&"+tempEnteredID)&&!
                            checkGRAndPVNameExistenceFile(managerBasic.getCurrentUser(),tempEnteredID+"&"+managerBasic.getCurrentUser()))


                    {
                        GRAndPVNameAddNew(managerBasic.getCurrentUser(),managerBasic.getCurrentUser()+"&"+tempEnteredID);
                    }
                    break;

                }

            }


            for (int i = 0; i < managerBasic.getUsers().size(); i++)
            {

                if (managerBasic.getUsers().get(i).userName.equals(tempEnteredID))
                {
                    if (!checkGRAndPVNameExistenceFile(tempEnteredID,managerBasic.getCurrentUser()+"&"+tempEnteredID)&&!
                            checkGRAndPVNameExistenceFile(tempEnteredID,tempEnteredID+"&"+managerBasic.getCurrentUser()))
                    {
                        GRAndPVNameAddNew(tempEnteredID,managerBasic.getCurrentUser()+"&"+tempEnteredID);
                    }
                    break;

                }

            }


            boolean PVFileExistence=false;

            if (fileExistence(managerBasic.getCurrentUser()+"&"+tempEnteredID)||
                    fileExistence(tempEnteredID+"&"+managerBasic.getCurrentUser()))

            {
                PVFileExistence=true;

            }

            if (!PVFileExistence)
            {
                chatFileMaker(managerBasic.getCurrentUser()+"&"+tempEnteredID);
            }



            nowUCanChat=false;
            currentChatFileName=null;
            main.changeScene("chat-page.fxml");



        }

        else
        {

            errorInSearchID.setText("user not found so you cant chat with this user");
        }



    }



    static   String groupName;
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


            for (int i = 0; i < managerBasic.getUsers().size(); i++) {
                if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
                {
                    if (!fileExistence(managerBasic.getCurrentUser()+"PGNames"))
                    {
                        chatFileMaker(managerBasic.getCurrentUser()+"PGNames");
                    }


                    GRAndPVNameAddNew(managerBasic.getCurrentUser(),groupName);
                    break;

                }

            }


            groupNamesAdd(groupName);
            groupIDsAdd(groupID);

            makeGroupFile(groupName,managerBasic.getCurrentUser(),groupID);

            main.changeScene("chat-page.fxml");

        }


    }


    public void makeGroupFile(String fileName, String adminUserName,String groupID) throws IOException {
        File file = new File(fileName + ".txt");




        FileOutputStream fileOut = new FileOutputStream(fileName + ".txt");
        String temp="";
        fileOut.write(temp.getBytes());
        fileOut.close();


        //file name is group name



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



    @FXML
    void showChatList(ActionEvent event) throws IOException {
        groupAndPvList.getChildren().clear();

//if (groupAndPvList.getChildren().isEmpty())

        PVAndGRListMaker();
    }

    @FXML
    void showGroupList(ActionEvent event) throws IOException {
        groupAndPvList.getChildren().clear();

        GRListMaker();

    }



    @FXML
    void showPVList(ActionEvent event) throws IOException {
        groupAndPvList.getChildren().clear();

        PVListMaker();
    }


    public void GRListMaker() throws IOException {


        User tempUser=null;
        for (int i = 0; i < managerBasic.getUsers().size(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
            {
                tempUser=managerBasic.getUsers().get(i);
                break;
            }

        }

        if (!fileExistence(tempUser.userName+"PGNames"))
        {
            chatFileMaker(tempUser.userName+"PGNames");
        }



        try {
            File file = new File(tempUser.userName + "PGNames.txt");

            String line;



            Scanner readUsers = new Scanner(file);




            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                groupAndPvList.heightProperty().addListener((new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                        GAndPVListSpace.setVvalue((Double) newValue);

                    }
                }));


                if (!line.equals("")) {

                    if (!line.contains("&")) {
                        Button button = new Button();


                        button.setOnAction(this::chatListClicked);


                        button.setId(line);

                        button.setAlignment(Pos.CENTER_LEFT);

                        button.setPadding(new Insets(5, 5, 5, 10));

                        button.setPrefSize(200, 50);



                        button.setText(line);

                        groupAndPvList.getChildren().add(button);
                    }




                }


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }







    }



    public void PVAndGRListMaker() throws IOException {


        User tempUser=null;
        for (int i = 0; i < managerBasic.getUsers().size(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
            {
                tempUser=managerBasic.getUsers().get(i);
                break;
            }

        }

        if (!fileExistence(tempUser.userName+"PGNames"))
        {
            chatFileMaker(tempUser.userName+"PGNames");
        }



        try {
            File file = new File(tempUser.userName + "PGNames.txt");

            String line;



            Scanner readUsers = new Scanner(file);




            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                groupAndPvList.heightProperty().addListener((new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                        GAndPVListSpace.setVvalue((Double) newValue);

                    }
                }));


                if (!line.equals("")) {
                    Button button = new Button();


                    button.setOnAction(this::chatListClicked);


                    button.setId(line);

                    button.setAlignment(Pos.CENTER_LEFT);

                    button.setPadding(new Insets(5, 5, 5, 10));

                    button.setPrefSize(200, 50);


                    if (line.contains("&")) {
                        if (line.split("\\&")[0].equals(managerBasic.getCurrentUser())) {
                            button.setText(line.split("\\&")[1]);
                        } else {
                            button.setText(line.split("\\&")[0]);
                        }
                    } else {
                        button.setText(line);
                    }


                    groupAndPvList.getChildren().add(button);

                }


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }







    }

    public void PVListMaker() throws IOException {


        User tempUser=null;
        for (int i = 0; i < managerBasic.getUsers().size(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
            {
                tempUser=managerBasic.getUsers().get(i);
                break;
            }

        }

        if (!fileExistence(tempUser.userName+"PGNames"))
        {
            chatFileMaker(tempUser.userName+"PGNames");
        }



        try {
            File file = new File(tempUser.userName + "PGNames.txt");

            String line;



            Scanner readUsers = new Scanner(file);




            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                groupAndPvList.heightProperty().addListener((new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                        GAndPVListSpace.setVvalue((Double) newValue);

                    }
                }));


                if (!line.equals("")) {
                    if (line.contains("&")) {
                        Button button = new Button();


                        button.setOnAction(this::chatListClicked);


                        button.setId(line);

                        button.setAlignment(Pos.CENTER_LEFT);

                        button.setPadding(new Insets(5, 5, 5, 10));

                        button.setPrefSize(200, 50);



                        if (line.split("\\&")[0].equals(managerBasic.getCurrentUser())) {
                            button.setText(line.split("\\&")[1]);
                        } else {
                            button.setText(line.split("\\&")[0]);
                        }


                        groupAndPvList.getChildren().add(button);

                    }



                }


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }







    }





    boolean menuOpen=false;

    static  String contact=null;

    static Button button1 =null;
    static  Button button2 =null;
    static Button button3=null;

    static Button button4 =null;
    static  Button button5 =null;
    static Button button6=null;
    static Button button7=null;


    @FXML
    void smallMenuOfChatOptions(ActionEvent event) {

        contact=null;


        button1 =null;
        button2 =null;
        button3=null;

        button4 =null;
        button5 =null;
        button6=null;
        button7=null;

        chatOptionVbox.getChildren().clear();

        if (currentChatFileName!=null) {


            if (!menuOpen) {


                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));

                slide.setNode(chatOptions);

                slide.setToY(0);
                slide.play();

                chatOptions.setTranslateY(0);

                menuOpen = true;


            }
            else {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));

                slide.setNode(chatOptions);

                slide.setToY(-255);
                slide.play();

                chatOptions.setTranslateY(0);


                menuOpen = false;
            }

            //PV

            if (checkGroupOrPV(currentChatFileName)==1)
            {
                button1 =new Button();
                button2 =new Button();
                button3 =new Button();

                if (!checkBlock(currentChatFileName))
                {
                    button1.setText("Block");
                    button1.setId("blockButton");

                }
                else
                {
                    button1.setText("Unblock");
                    button1.setId("UnblockButton");
                }



                button2.setText("Search MID");
                button2.setId("searchMessageID");


                button3.setText("Search Text");
                button3.setId("searchText");

                chatOptionVbox.getChildren().add(button1);
                chatOptionVbox.getChildren().add(button2);
                chatOptionVbox.getChildren().add(button3);





                button1.setOnMouseClicked(this::blockAndUnblockClicked);

                button2.setOnMouseClicked(this::searchMessageIDClicked );

                button3.setOnMouseClicked(this::searchTextClicked );





                if (currentChatFileName.split("\\&")[0].equals(managerBasic.getCurrentUser()))
                {
                    contact=currentChatFileName.split("\\&")[1];
                }
                else
                {
                    contact=currentChatFileName.split("\\&")[0];
                }



            }




            else if (checkGroupOrPV(currentChatFileName)==2)
            {

                button1 =new Button();
                button2 =new Button();
                button3 =new Button();
                button4 =new Button();
                button5 =new Button();
                button6 =new Button();
                button7 =new Button();


                button1.setText("add member");
                button1.setId("addMemberButton");
                button1.setOnMouseClicked(this::addMemberClicked);

                button2.setText("remove member");
                button2.setId("removeMemberButton");
                button2.setOnMouseClicked(this::removeMemberClicked);


                button3.setText("change group name");
                button3.setId("changeGNameButton");
                button3.setOnMouseClicked(this::changeGNameClicked);

                button4.setText("change group ID");
                button4.setId("changeGIDButton");
                button4.setOnMouseClicked(this::changeGIDClicked);

                button5.setText("Search MID");
                button5.setId("searchMessageID");
                button5.setOnMouseClicked(this::searchMessageIDClicked );

                button6.setText("Search Text");
                button6.setId("searchText");
                button6.setOnMouseClicked(this::searchTextClicked );

                button7.setText("group information");
                button7.setId("groupInf");
                button7.setOnMouseClicked(this::groupInfClicked );

                chatOptionVbox.getChildren().add(button1);
                chatOptionVbox.getChildren().add(button2);
                chatOptionVbox.getChildren().add(button3);
                chatOptionVbox.getChildren().add(button4);
                chatOptionVbox.getChildren().add(button5);
                chatOptionVbox.getChildren().add(button6);
                chatOptionVbox.getChildren().add(button7);







            }





        }








    }




    private void groupInfClicked(MouseEvent mouseEvent) {


        try {
            main.changeScene("group-inf.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void showButton(ActionEvent event) {

        nameField.setText(currentChatFileName);

        IDField.setText(groupController.getGroupID(currentChatFileName));

        adminField.setText(groupController.getAdmin(currentChatFileName));

        membersField.setText(groupController.getMembers(currentChatFileName));

    }


    private void changeGIDClicked(MouseEvent mouseEvent) {
        // only admin access this button

        if (groupController.checkAdmin(currentChatFileName,managerBasic.getCurrentUser()))
        {
            try {
                main.changeScene("change-groupID.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @FXML
    void changeIDApply(ActionEvent event) {

        changeIDError.setText("");

        String tempID=inputNewID.getText();

        if(!tempID.isEmpty())
        {
            if (!groupController.checkGroupIDExistence(tempID))
            {
                groupController.changeGroupID(groupController.getGroupID(currentChatFileName),tempID,currentChatFileName);


                changeIDError.setText("group ID changed");
            }
            else
            {
                changeIDError.setText("this ID is repetitive");
            }
        }



    }




    private void changeGNameClicked(MouseEvent mouseEvent) {


        // only admin access this button

        if (groupController.checkAdmin(currentChatFileName,managerBasic.getCurrentUser()))
        {
            try {
                main.changeScene("change-groupName.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }





    private void removeMemberClicked(MouseEvent mouseEvent) {

        // only admin access this button

        if (groupController.checkAdmin(currentChatFileName,managerBasic.getCurrentUser()))
        {
            try {
                main.changeScene("group-remove.fxml");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void removeMember(ActionEvent event) {
        removeError.setText("");

        String tempID=inputIDRemove.getText();

        if (!tempID.isEmpty())
        {
            if (!checkUser(tempID)) {
                addError.setText("there is no user with this ID");
            }
            else
            {
                int result=groupController.removeMember(currentChatFileName,tempID);

                if (result==1)
                {
                    removeError.setText("this user is not a member of this group");
                }
                else if (result==2)
                {
                    GRAndPVNameDelete(tempID,currentChatFileName);
                    removeError.setText("removed successfully");
                }

            }

        }




    }


    @FXML
    void showMember(ActionEvent event) {

        showMemberError.setText("");
        membersResult.setText("");



        StringBuilder temp=new StringBuilder();


        for (int i = 2; i < groupController.getMembers(currentChatFileName).split("\\s").length; i++) {

            temp.append(groupController.getMembers(currentChatFileName).split("\\s")[i]+"  ");

        }


        if (temp.toString().equals(""))
        {
            showMemberError.setText("there isn't any other user in group except you");
        }

        else {

            membersResult.setText(temp.toString());
        }

    }




    private void addMemberClicked(MouseEvent mouseEvent) {

        // only admin access this button

        if (groupController.checkAdmin(currentChatFileName,managerBasic.getCurrentUser()))
        {
            try {
                main.changeScene("group-addMember.fxml");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void addUser(ActionEvent event) {

        addError.setText("");

        String tempID= inputID.getText();

        if (!tempID.isEmpty()) {


            if (!checkUser(tempID)) {
                addError.setText("there is no user with this ID");
            }
            else {
                // now we check if this contact is blocked or has blocked u


                String chatFileNameWithUser = managerBasic.getCurrentUser() + "&" + tempID;
                String reverseName = tempID + "&" + managerBasic.getCurrentUser();
                boolean blocked = false;
                if (fileExistence(chatFileNameWithUser)) {
                    if (checkBlock(chatFileNameWithUser))
                        blocked = true;
                } else if (fileExistence(reverseName)) {
                    if (checkBlock(reverseName))
                        blocked = true;
                }

                if (blocked) {
                    addError.setText("you can't add this user due to being blocked");
                } else {

                    int result = groupController.addNewMember(currentChatFileName, tempID);
                    if (result == 2)
                    {
                        GRAndPVNameAddNew(tempID,currentChatFileName);

                        addError.setText("added successfully");
                    }
                    else if (result == 1) {
                        addError.setText("this user is already a member of this group");
                    }

                }


            }

        }

    }



    @FXML
    void showFollowings(ActionEvent event) {

        showFollowingError.setText("");

        User tempUser=null;
        for (int i = 0; i < managerBasic.getUsers().size(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
            {
                tempUser=managerBasic.getUsers().get(i);
            }
        }


        StringBuilder tempResult=new StringBuilder();

//        for (int i = 0; i < tempUser.getNumberOfFollowing(); i++) {
//
//            tempResult.append(tempUser.getFollowings().get(i)+"  ");
//
//        }

        try {
            File file = new File(tempUser.userName+"following.txt");

            String line;


            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();


                tempResult.append(line+"  ");


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        if (tempResult.toString().equals(""))
        {
            showFollowingError.setText("you don't have any following");
        }

        else {
            followingResult.setText(tempResult.toString());
        }



    }


    @FXML
    void changeNameApply(ActionEvent event) throws IOException {

        changeNameError.setText("");

        String tempName=inputNewName.getText();

        if (!tempName.isEmpty())
        {
            if (!groupController.checkGroupNameExistence(tempName))
            {
                nowUCanChat=false;
                groupController.changeGroupName(currentChatFileName,tempName);

//                currentChatFileName=tempName;
                for (int i = 0; i < managerBasic.getUsers().size(); i++) {

                    if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
                    {
                        managerBasic.getUsers().get(i).PVAndGRNames.remove(currentChatFileName);
                        managerBasic.getUsers().get(i).setPVAndGRNames(tempName);
                    }
                }

                changeNameError.setText("group name changed");
            }
            else
            {
                changeNameError.setText("this name is repetitive");
            }
        }


    }





    public void changeGroupName(String groupNamePre, String groupNameNew)
    {
//        managerBasic.getGroupNames().remove(groupNamePre);
//
//        managerBasic.setGroupNames(groupNameNew);

//        File file = new File(groupNamePre+".txt");
//
//
//        File rename = new File(groupNameNew+".txt");




        Path f = Paths.get(groupNamePre+".txt");
        Path rF = Paths.get(groupNameNew+".txt");


        try {
            Files.move(f, rF, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File was successfully renamed");
        }

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to rename file");
        }




//        boolean flag = file.renameTo(rename);
//
//
//        System.out.println(flag);



    }



    @FXML
    void searchMID(ActionEvent event) {

        searchIDError.setText("");



        // ID should be without #
        String tempID=inputMessageID.getText();


        if (!tempID.isEmpty())
        {

            String  result=null;
            //PV
            if (checkGroupOrPV(currentChatFileName)==1)
            {

                result=searchMessageID(currentChatFileName,tempID);
                if (result==null)
                {
                    searchIDError.setText("there is no message with this ID");
                }
                else
                {
                    searchIDResult.setText(result);
                }


            }


            //group
            else if (checkGroupOrPV(currentChatFileName)==2)
            {

                result=searchMessageIDGroup(currentChatFileName,tempID);

                if (result==null)
                {
                    searchIDError.setText("there is no message with this ID");
                }
                else
                {
                    searchIDResult.setText(result);
                }


            }




        }


    }



    @FXML
    void searchText(ActionEvent event) {

        searchTextError.setText("");
        searchIDError.setText("");

        String  tempText=inputText.getText();

        if (!tempText.isEmpty())
        {

            String  result=null;
            //PV
            if (checkGroupOrPV(currentChatFileName)==1)
            {

                result=searchText(currentChatFileName,tempText);
                if (result.equals(""))
                {
                    searchTextError.setText("there is no message including this text");
                }
                else
                {
                    searchTextResult.setText(result);
                }


            }


            //group
            else if (checkGroupOrPV(currentChatFileName)==2)
            {

                result=searchTextGroup(currentChatFileName,tempText);

                if (result.equals(""))
                {
                    searchTextError.setText("there is no message including this text");
                }
                else
                {
                    searchTextResult.setText(result);
                }


            }


        }



    }



    private void searchTextClicked(MouseEvent mouseEvent) {


        if (!checkBlock(currentChatFileName))
        {
            try {
                main.changeScene("search-Text.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void searchMessageIDClicked(MouseEvent mouseEvent)  {

// when chat is blocked u can't search

        if (!checkBlock(currentChatFileName))
        {
            try {
                main.changeScene("search-MID.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    private void blockAndUnblockClicked(MouseEvent event) {

        Button tempButton=(Button) event.getSource();

        if (tempButton.getId().equals("blockButton"))
        {
            blocking(currentChatFileName,managerBasic.getCurrentUser(),contact);

            button1.setText("Unblock");
            button1.setId("UnblockButton");

        }

        else if (tempButton.getId().equals("UnblockButton"))
        {


            boolean uCanUnblock=false;

            try {

                File usersFile = new File(currentChatFileName+".txt");
                String line;


                Scanner readUsers = new Scanner(usersFile);

                StringBuffer inputBuffer = new StringBuffer();



                StringBuilder tempLine=new StringBuilder();




                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();




                    if (line.split("\\s")[0].equals(managerBasic.getCurrentUser()))
                    {
                        uCanUnblock=true;
                    }



                }


                readUsers.close();





            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }





            if (uCanUnblock)
            {
                unblock(currentChatFileName,managerBasic.getCurrentUser());
                button1.setText("Block");
                button1.setId("blockButton");
            }


        }



    }









    Text tempText=new Text();
    @FXML
    void chatListClicked(ActionEvent event)
    {




//        tempText.setSi


        contactInf.getChildren().remove(tempText);


        // delete previous history when clicked
        messageVbox.getChildren().clear();






        Button clickedButton=(Button) event.getSource();


        // determining if it is a group or PV
        String chatFileName=clickedButton.getId();

        tempText.setText(clickedButton.getText());
//        tempText.setSi
        tempText.setFont(Font.font(null, FontWeight.BOLD, 20));

        contactInf.getChildren().add(tempText);



//        contactInf.setAlignment(Pos.CENTER_LEFT);

        if (chatFileName.contains("&"))
        {

            String reverseName=chatFileName.split("\\&")[1]+"&"+chatFileName.split("\\&")[0];
            // this is PV
            if (fileExistence(chatFileName))
            {
                currentChatFileName=chatFileName;
                nowUCanChat=true;

                showHistoryPV(chatFileName);

            }


            else if(fileExistence(reverseName))
            {
                currentChatFileName=reverseName;
                nowUCanChat=true;
                showHistoryPV(reverseName);

            }



        }
        else
        {


            // this is a group

            nowUCanChat=true;
            currentChatFileName=chatFileName;
            showHistoryGR(chatFileName);

        }





    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        if (!fileExistence("groupIDs"))
        {
            chatFileMaker("groupIDs");
        }

        if (!fileExistence("groupNames"))
        {
            chatFileMaker("groupNames");
        }





        if (messageOptionsSlider!=null)
        {
            messageOptionsSlider.setTranslateX(-155);
        }

        if (chatOptions!=null)
        {
            chatOptions.setTranslateY(-155);
        }

        if(imageSendSlide!=null)
        {

            imageSendSlide.setTranslateX(700);
        }

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

            //Chat list







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



    public void showHistoryPV(String fileName)
    {
        try{
            File usersFile = new File(fileName + ".txt");
            String line=null;


            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(fileName + ".txt"));






            boolean checkIfWasBlocked=checkBlock(fileName);

            if (!checkIfWasBlocked)

                while (readUsers.hasNextLine())
                {

                    line=readUsers.nextLine();

                    String tempMessage=line.split("\\:")[1].split("\\#")[0];
                    String messageID="#"+line.split("\\:")[1].split("\\#")[1];


                    if (line.split("\\:")[0].equals(managerBasic.getCurrentUser()))
                    {
                        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                                chatSpace.setVvalue((Double) newValue);

                            }
                        }));


                        {

                            if (tempMessage.contains("IMAGE"))
                            {
                                HBox hBox = new HBox();

                                String tempMessage2=tempMessage.split("\\s")[1];

                                hBox.setOnMouseClicked(this::clickedOnMessage);

                                hBox.setId(messageID);

                                hBox.setAlignment(Pos.CENTER_RIGHT);
                                hBox.setPadding(new Insets(5, 5, 5, 10));

                                ImageView imageView=new ImageView();

                                Image image=new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/"+tempMessage2)));

                                imageView.setFitHeight(150);
                                imageView.setFitWidth(150);

                                imageView.setImage(image);


                                hBox.getChildren().addAll(imageView);

                                messageVbox.getChildren().add(hBox);


                            }

                            else {


                                HBox hBox = new HBox();


                                hBox.setOnMouseClicked(this::clickedOnMessage);

                                hBox.setId(messageID);


                                hBox.setAlignment(Pos.CENTER_RIGHT);
                                hBox.setPadding(new Insets(5, 5, 5, 10));


                                Text text = new Text(tempMessage);

                                TextFlow textFlow = new TextFlow(text);


                                textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: blue;");

                                textFlow.setPadding(new Insets(5, 5, 5, 10));

                                text.setFill(Color.color(0, 0, 0));


                                hBox.getChildren().addAll(textFlow);

                                messageVbox.getChildren().add(hBox);


                                inputMessage.clear();


                            }



                        }
                    }

                    else
                    {

                        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                                chatSpace.setVvalue((Double) newValue);

                            }
                        }));



                        if (tempMessage.contains("IMAGE"))
                        {

                            String tempMessage2=tempMessage.split("\\s")[1];


                            HBox hBox = new HBox();


                            hBox.setOnMouseClicked(this::clickedOnMessage);

                            hBox.setId(messageID);

                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));

                            ImageView imageView=new ImageView();

                            Image image=new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/"+tempMessage2)));

                            imageView.setFitHeight(150);
                            imageView.setFitWidth(150);

                            imageView.setImage(image);


                            hBox.getChildren().addAll(imageView);

                            messageVbox.getChildren().add(hBox);


                        }

                        else {


                            HBox hBox = new HBox();

                            hBox.setOnMouseClicked(this::clickedOnMessage);

                            hBox.setId(messageID);

                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));


                            Text text = new Text(tempMessage);

                            TextFlow textFlow = new TextFlow(text);


                            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: gray;");

                            textFlow.setPadding(new Insets(5, 5, 5, 10));

                            text.setFill(Color.color(0, 0, 0));


                            hBox.getChildren().addAll(textFlow);

                            messageVbox.getChildren().add(hBox);


                            inputMessage.clear();


                        }



                    }
                }






//            return inputBuffer.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }






    public void showHistoryGR(String fileName)
    {
        try{
            File usersFile = new File(fileName + ".txt");
            String line=null;


            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(fileName + ".txt"));







            int counter=0;

            while (readUsers.hasNextLine())
            {

                line=readUsers.nextLine();
                if (counter>=3)
                {


                    String tempMessage=line.split("\\:")[1].split("\\#")[0];

                    String messageID="#"+line.split("\\:")[1].split("\\#")[1];

                    if (line.split("\\:")[0].equals(managerBasic.getCurrentUser()))
                    {
                        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                                chatSpace.setVvalue((Double) newValue);

                            }
                        }));



                        if (tempMessage.contains("IMAGE"))
                        {
                            HBox hBox = new HBox();
                            String tempMessage2=tempMessage.split("\\s")[1];

                            hBox.setOnMouseClicked(this::clickedOnMessage);

                            hBox.setId(messageID);

                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));

                            ImageView imageView=new ImageView();

                            Image image=new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/"+tempMessage2)));

                            imageView.setFitHeight(150);
                            imageView.setFitWidth(150);

                            imageView.setImage(image);


                            hBox.getChildren().addAll(imageView);

                            messageVbox.getChildren().add(hBox);


                        }

                        else

                        {



                            HBox hBox=new HBox();

                            hBox.setOnMouseClicked(this:: clickedOnMessage);

                            hBox.setId(messageID);


                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.setPadding(new Insets(5,5,5,10));


                            Text text=new Text(tempMessage);

                            TextFlow textFlow=new TextFlow(text);


                            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: blue;");

                            textFlow.setPadding(new Insets(5,5,5,10));

                            text.setFill(Color.color(0,0,0));


                            hBox.getChildren().addAll(textFlow);

                            messageVbox.getChildren().add(hBox);


                            inputMessage.clear();



                        }
                    }

                    else
                    {

                        messageVbox.heightProperty().addListener((new ChangeListener<Number>() {

                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                                chatSpace.setVvalue((Double) newValue);

                            }
                        }));



                        if (tempMessage.contains("IMAGE"))
                        {
                            HBox hBox = new HBox();

                            String tempMessage2=tempMessage.split("\\s")[1];

                            hBox.setOnMouseClicked(this::clickedOnMessage);

                            hBox.setId(messageID);

                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));

                            ImageView imageView=new ImageView();

                            Image image=new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/"+tempMessage2)));

                            imageView.setFitHeight(150);
                            imageView.setFitWidth(150);

                            imageView.setImage(image);


                            hBox.getChildren().addAll(imageView);

                            messageVbox.getChildren().add(hBox);


                        }


                        else
                        {


                            HBox hBox = new HBox();

                            hBox.setOnMouseClicked(this::clickedOnMessage);

                            hBox.setId(messageID);


                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));


                            Text text = new Text(line.split("\\:")[0] + "\n" + tempMessage);

                            TextFlow textFlow = new TextFlow(text);


                            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: gray;");

                            textFlow.setPadding(new Insets(5, 5, 5, 10));

                            text.setFill(Color.color(0, 0, 0));


                            hBox.getChildren().addAll(textFlow);

                            messageVbox.getChildren().add(hBox);


                            inputMessage.clear();


                        }



                    }
                }

                counter++;

            }






//            return inputBuffer.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }



    private void clickedOnMessage(MouseEvent mouseEvent) {

        HBox tempHBox=(HBox) mouseEvent.getSource();

        errorMOP.setText("");

//        System.out.println(mouseEvent.getSource());

        String clickedMessageID= tempHBox.getId().split("\\#")[1];
        String messageOwner=tempHBox.getId().split("\\#")[1].split("\\%")[0];




        messageOptionsSlider.setTranslateX(-155);


        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));

        slide.setNode(messageOptionsSlider);

        slide.setToX(0);
        slide.play();

        messageOptionsSlider.setTranslateX(0);



        ReplyButton.setOnMouseClicked(event ->
                {
                    inputMessage.clear();


                    String messageFirstPart=findMessageAndTakeFP(currentChatFileName,messageOwner,clickedMessageID);

                    inputMessage.setText("REPLY TO (" + messageFirstPart+ ") ");


                    slide.setDuration(Duration.seconds(0.5));

                    slide.setNode(messageOptionsSlider);

                    slide.setToX(-155);
                    slide.play();

                    messageOptionsSlider.setTranslateX(0);

                }

        );


        deleteButton.setOnMouseClicked(event ->
                {



                    if (deleteMessage(currentChatFileName,clickedMessageID,managerBasic.getCurrentUser()))
                    {
                        tempHBox.getChildren().clear();

                        slide.setDuration(Duration.seconds(0.5));

                        slide.setNode(messageOptionsSlider);

                        slide.setToX(-155);
                        slide.play();

                        messageOptionsSlider.setTranslateX(0);

                    }

                    else
                    {
                        errorMOP.setText("you can't delete this message");
                    }


                }

        );


        editButton.setOnMouseClicked(event ->
                {

                    inputMessage.setText("TYPE EDITED VERSION ");

                    editingHBox=tempHBox;



                }

        );

        ForwardButton.setOnMouseClicked(event ->
                {


                    try {
                        main.changeScene("forward-page.fxml");



                    } catch (IOException e) {
                        e.printStackTrace();
                    }






                }

        );


        messageIDForward=clickedMessageID;

    }

    private void forwardMessage(ActionEvent event) {

        Button clickedButton= (Button) event.getSource();



        if (clickedButton.getId().contains("&"))
        {
            String reverseName=clickedButton.getId().split("\\&")[1]+"&"+clickedButton.getId().split("\\&")[0];

            if (fileExistence(clickedButton.getId()))
            {
                fileNameForward=clickedButton.getId();
            }
            else
            {
                fileNameForward=reverseName;
            }


        }

        else
        {
            fileNameForward=clickedButton.getId();

        }

        chatHistoryMaker(fileNameForward, "FORWARDED FROM " + messageIDForward.split("\\%")[0] + " " + findAndForward(currentChatFileName,messageIDForward), managerBasic.getCurrentUser());

        messageIDForward=null;
        fileNameForward=null;


    }


    @FXML
    void showForwardChoiceResult(ActionEvent event) {

        User tempUser=null;
        for (int i = 0; i < managerBasic.getUserSize(); i++) {

            if (managerBasic.getUsers().get(i).userName.equals(managerBasic.getCurrentUser()))
            {
                tempUser= managerBasic.getUsers().get(i);
            }
        }




        try {
            File file = new File(tempUser.userName+"PGNames.txt");

            String line;


            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();
                Button button=new Button();

                button.setOnAction(this::forwardMessage);

                button.setId(line);



                if (line.contains("&"))
                {


                    if (line.split("\\&")[0].equals(managerBasic.getCurrentUser()))
                    {
                        button.setText(line.split("\\&")[1]);
                    }
                    else
                    {
                        button.setText(line.split("\\&")[0]);
                    }
                }

                else
                {

                    button.setText(line);
                }


                forwardChoices.getChildren().add(button);




            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }




//        for (int i = 0; i < tempUser.PVAndGRNames.size(); i++) {
//
//            Button button=new Button();
//
//            button.setOnAction(this::forwardMessage);
//
//            button.setId(tempUser.PVAndGRNames.get(i));
//
//
//
//            if (tempUser.PVAndGRNames.get(i).contains("&"))
//            {
//
//
//                if (tempUser.PVAndGRNames.get(i).split("\\&")[0].equals(managerBasic.getCurrentUser()))
//                {
//                    button.setText(tempUser.PVAndGRNames.get(i).split("\\&")[1]);
//                }
//                else
//                {
//                    button.setText(tempUser.PVAndGRNames.get(i).split("\\&")[0]);
//                }
//            }
//
//            else
//            {
//
//                button.setText(tempUser.PVAndGRNames.get(i));
//            }
//
//
//            forwardChoices.getChildren().add(button);
//
//
//        }

    }

    @FXML
    void closemessageOpSlide(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));

        slide.setNode(messageOptionsSlider);

        slide.setToX(-155);
        slide.play();

        messageOptionsSlider.setTranslateX(0);


    }




    public void chatHistoryMaker(String fileName, String text, String messageWriter) {


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

            }
            catch (FileNotFoundException e) {
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
                            if (tempText.startsWith("FORWARDED FROM"))
                            {
                                StringBuilder temp=new StringBuilder();

                                temp.append(tempText);



                                temp.replace(0,15+  temp.toString().split("\\s")[2].length(),"");

                                tempText1=temp.toString();
                            }


                            else {
                                tempText1 = ID[0].split("\\:")[1];

                            }


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


    public void   blocking (String fileName ,String userThatBlocks,String userThatIsBlocked)
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


                PrintWriter writer = new PrintWriter(file);
                writer.print(userThatBlocks+" BLOCKED "+userThatIsBlocked);
                writer.close();

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


                String tempText = line.split("\\:")[1].split("\\#")[0];










                if (tempText.contains(text)) {

                    int numOfWords=tempText.split("\\s").length;
                    String [] textWords=tempText.split("\\s");




                    int counter=0;

                    boolean made=false;

                    for (int i = 0; i < numOfWords; i++) {


                        if (!textWords[i].equals("REPLY")&&!textWords[i].equals("TO")&&!textWords[i].equals("FORWARDED")&&!textWords[i].equals("FROM")&&!textWords[i].equals(""))
                        {
                            textFirstWords.append(textWords[i]+" ");
                            counter++;
                        }

                        if (counter==3)
                        {
                            textFirstWordsFinal.append(textFirstWords.toString() +"\t#"+ ID[1]+"\n") ;
                            made=true;
                            break;
                        }




                    }

                    if (!made)
                    {
                        textFirstWordsFinal.append(textFirstWords.toString() +"\t#"+ ID[1]+"\n") ;

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


                    String tempText = line.split("\\:")[1].split("\\#")[0];


                    if (tempText.contains(text)) {

                        int numOfWords = tempText.split("\\s").length;

                        String[] textWords = tempText.split("\\s");


                        int counter = 0;

                        boolean made=false;

                        for (int i = 0; i < numOfWords; i++) {

                            if (!textWords[i].equals("REPLY") && !textWords[i].equals("TO") && !textWords[i].equals("FORWARDED") && !textWords[i].equals("FROM") && !textWords[i].equals("")) {
                                textFirstWords.append(textWords[i] + " ");
                                counter++;
                            }

                            if (counter == 3) {
                                textFirstWordsFinal.append(textFirstWords.toString() + "\t#" + ID[1] + "\n");
                                made=true;
                                break;
                            }

                        }

                        if (!made)
                        {
                            textFirstWordsFinal.append(textFirstWords.toString() + "\t#" + ID[1] + "\n");
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




    // new methods relating to file



// make if it doesen't exist and add

    public void GRAndPVNameAddNew(String currentUser,String NewGROrPVName)
    {

        // name of the files are in the form : username+PGNames

        if (!fileExistence(currentUser+"PGNames"))
        {
            chatFileMaker(currentUser+"PGNames");
        }


        File file = new File(currentUser+"PGNames.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(NewGROrPVName+"\n");
            fileWriter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void GRAndPVNameDelete(String currentUser,String GROrPVName)
    {

        // name of the files are in the form : username+PGNames

        if (!fileExistence(currentUser+"PGNames"))
        {
            chatFileMaker(currentUser+"PGNames");
        }




        try {

            File usersFile = new File(currentUser+"PGNames.txt");
            String line;


            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(currentUser+"PGNames.txt"));
            StringBuffer inputBuffer = new StringBuffer();



            StringBuilder tempLine=new StringBuilder();




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();




                if (line.startsWith(GROrPVName))
                {

                    line="";

                }



                inputBuffer.append(line);
                inputBuffer.append('\n');



            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(currentUser+"PGNames.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean checkGRAndPVNameExistenceFile(String currentUser, String GROrPVName)
    {

        if (!fileExistence(currentUser+"PGNames"))
        {
            chatFileMaker(currentUser+"PGNames");
        }




        File file = new File(currentUser+"PGNames.txt");
        try {


            String line;


            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();



                if (line.startsWith(GROrPVName))

                    return true;


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return false;

    }


    public  void groupNamesAdd (String newGName)
    {
        File file = new File("groupNames.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(newGName+"\n");
            fileWriter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public  void groupIDsAdd (String newID)
    {
        File file = new File("groupIDs.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(newID +"\n");
            fileWriter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

