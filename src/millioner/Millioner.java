
package millioner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Millioner extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        
        Media sound = new Media(new File("C:\\Users\\Bagdat\\Desktop\\alemresearch\\theme.mp3").toURI().toString());
        MediaPlayer mediaPlayerTheme = new MediaPlayer(sound);
        mediaPlayerTheme.play();
        FileReader fr = new FileReader("C:\\Users\\Bagdat\\Documents\\NetBeansProjects\\Millioner\\src\\files\\questions.txt");
        BufferedReader reader = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> corAns= new ArrayList<>();
        ArrayList<String> fakeAns = new ArrayList<>();
        String line;
        while((line=reader.readLine())!=null){
            lines.add(line);
        }
        
        for(int i=0;i<lines.size();i+=3){
            questions.add(lines.get(i));
        }
        for(int i=1;i<lines.size();i+=3){
            corAns.add(lines.get(i));
        }
        for(int i=2;i<lines.size();i+=3){
            fakeAns.add(lines.get(i));
        }
        System.out.println(lines);
        primaryStage.setResizable(false);
        Button btn = new Button();
        TextField txt = new TextField();
        Text t = new Text("Welcome to our game!!!");
        t.setStyle(" -fx-font-size: 30;");
        t.setFill(Color.DEEPSKYBLUE);
        t.setStroke(Color.BLACK);
        t.setTranslateX(50);
        t.setTranslateY(100);
        ImageView imgv = new ImageView();
        Image back = new Image(new FileInputStream("C:\\Users\\Bagdat\\Desktop\\alemresearch\\back.jpg"));
        imgv.setImage(back);
        
        btn.setText("START");
        btn.setTranslateX(150);
        btn.setTranslateY(240);
        txt.setTranslateX(100);
        txt.setTranslateY(200);
        txt.setMinWidth(200);
        btn.setMinWidth(100);
        btn.setMaxHeight(100);
        Thread thrr;
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            private final Integer STARTTIME = 10;
            Timeline tl;
            private Integer sec = STARTTIME;  
            int COUNTER_FOR_QUESTIONS = 0;
            int score =0;
            boolean BUTTON_CLICKED=false;
            int q_num=1;
            
            Media sound = new Media(new File("C:\\Users\\Bagdat\\Desktop\\alemresearch\\incorrect.wav").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            Media sound1 = new Media(new File("C:\\Users\\Bagdat\\Desktop\\alemresearch\\correct.mp3").toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
            @Override
            public void handle(ActionEvent event) {
                mediaPlayerTheme.stop();
                Stage stage2 = new Stage();
                Group root2 = new Group();
                ImageView imgv2 = new ImageView();
                 
                Image back = null;
                try {
                    back = new Image(new FileInputStream("C:\\Users\\Bagdat\\Desktop\\alemresearch\\back.jpg"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Millioner.class.getName()).log(Level.SEVERE, null, ex);
                }
                imgv2.setImage(back);
                
                Text timesec = new Text();
                timesec.setText(" ");
                timesec.setTranslateX(455);
                timesec.setTranslateY(50);
                
                timesec.setStyle(" -fx-font-size: 20;");
                timesec.setFill(Color.DEEPSKYBLUE);
                timesec.setStroke(Color.BLACK);
//                if (tl != null) {
//                    tl.stop();
//                }
//                sec = STARTTIME;
//                timesec.setText(sec.toString());
//                tl = new Timeline();
//                tl.setCycleCount(Timeline.INDEFINITE);
//                tl.getKeyFrames().add(
//                new KeyFrame(Duration.seconds(1),
//                    new EventHandler() {       
//                    @Override
//                    public void handle(Event event) {
//                        sec--;
//                        timesec.setText(sec.toString());
//                        if (sec <= 0) {
//                            tl.stop();
//                        }
//                    }
//                }));
                stage2.setTitle("MILLIONAIRE!!!");
                stage2.setScene(new Scene(root2, 600, 400));
                stage2.setResizable(false);
                String name = txt.getText();
                
                Text name_score = new Text(name+" : "+score);
                Text quest = new Text("Question: "+q_num);
                name_score.setTranslateX(50);
                name_score.setTranslateY(50);
                name_score.setStyle(" -fx-font-size: 20;");
                Text time = new Text("Time :");
                time.setTranslateX(400);
                time.setTranslateY(50);
                time.setStyle(" -fx-font-size: 20;");
                time.setFill(Color.DEEPSKYBLUE);
                time.setStroke(Color.BLACK);
                quest.setTranslateX(200);
                quest.setTranslateY(50);
                quest.setStyle(" -fx-font-size: 20;");
                quest.setFill(Color.DEEPSKYBLUE);
                quest.setStroke(Color.BLACK);
                Text question = new Text("Test");
                question.setText(questions.get(COUNTER_FOR_QUESTIONS));
                
                question.setStyle(" -fx-font-size: 15;");
                question.setFill(Color.DEEPSKYBLUE);
                question.setStroke(Color.BLACK);
                question.setTranslateX(80);
                question.setTranslateY(150);
                
                Rectangle rec = new Rectangle(520, 100);
                rec.setArcHeight(15);
                rec.setArcWidth(15);

                rec.setFill(Color.WHITE);
                rec.setStroke(Color.BLACK);
                rec.setTranslateX(50);
                rec.setTranslateY(100);
                
                
                Button ans1 = new Button();
                
                ans1.setMinWidth(200);
                ans1.setMinHeight(50);
                ans1.setTranslateX(50);
                ans1.setTranslateY(250);
                ans1.setStyle("-fx-font-size:15;");
                
                
                Button ans2 = new Button();
                
                ans2.setMinWidth(200);
                ans2.setMinHeight(50);
                ans2.setTranslateX(280);
                ans2.setTranslateY(250);
                
                ans2.setStyle("-fx-font-size:15;");
                
                ans2.setStyle("-fx-font-size:15");
                Button ans3 = new Button();
                ans3.setMinWidth(200);
                ans3.setMinHeight(50);
                ans3.setTranslateX(50);
                ans3.setTranslateY(320);
                
                ans3.setStyle("-fx-font-size:15;");
                
                ans3.setStyle("-fx-font-size:15");
                Button ans4 = new Button();
                ans4.setMinWidth(200);
                ans4.setMinHeight(50);
                ans4.setTranslateX(280);
                ans4.setTranslateY(320);
                
                ans4.setStyle("-fx-font-size:15;");
                
                ans1.setStyle("-fx-font-size:15");
                Button next = new Button();
                next.setTranslateX(500);
                next.setTranslateY(280);
                next.setVisible(false);
                next.setStyle("-fx-background-radius: 20 20 20 20;-fx-font-size: 25;");
                next.setText("➤");
                if (tl != null) {
                    tl.stop();
                }
                sec = STARTTIME;
                timesec.setText(sec.toString());
                tl = new Timeline();
                tl.setCycleCount(Timeline.INDEFINITE);
                tl.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                    new EventHandler() {       
                    @Override
                    public void handle(Event event) {
                        sec--;
                        timesec.setText(sec.toString());
                        if (sec <= 0) {
                            tl.stop();
                            BUTTON_CLICKED=true;
                            next.setVisible(true);
                        }
                    }
                }));
                
                tl.playFromStart();
                Random rand = new Random();
                int CORRECT_ANSWER_POSITION = rand.nextInt(3);
                if(CORRECT_ANSWER_POSITION==0){
                    ans1.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                }
                if(CORRECT_ANSWER_POSITION==1){
                    ans2.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                }
                if(CORRECT_ANSWER_POSITION==2){
                    ans3.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                }
                if(CORRECT_ANSWER_POSITION==3){
                    ans4.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                }
                String[] FakeArr = fakeAns.get(COUNTER_FOR_QUESTIONS).split(";");
                ArrayList<String> FakeArr2 = new ArrayList<>(Arrays.asList(FakeArr));
                System.out.println(FakeArr2.size());
                Collections.shuffle(FakeArr2);
                if(ans1.getText()==""){
                    ans1.setText(FakeArr2.get(0));
                }
                if(ans2.getText()==""){
                    ans2.setText(FakeArr2.get(1));
                }
                if(ans3.getText()==""){
                    ans3.setText(FakeArr2.get(2));
                }
                if(ans4.getText()==""){
                    ans4.setText(FakeArr2.get(3));
                }
                EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tl.stop();
                        int ans =0;
                        if(CORRECT_ANSWER_POSITION==ans && !BUTTON_CLICKED ){
                            ans1.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                            next.setVisible(true);
                            score+=100;
                            
                            name_score.setText(name+":"+score);
                            BUTTON_CLICKED = true;
                            mediaPlayer1.play();
                        }
                        if(CORRECT_ANSWER_POSITION!=ans && !BUTTON_CLICKED){
                             ans1.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                             
                             next.setVisible(true);
                             BUTTON_CLICKED=true;
                             mediaPlayer.play();
                        }
                             
                    }
                };
                ans1.setOnAction(ev); 
                EventHandler<ActionEvent> ev2 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int ans=1;
                        tl.stop();
                        if(CORRECT_ANSWER_POSITION==ans && !BUTTON_CLICKED){
                            ans2.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                            
                            next.setVisible(true);
                            score+=100;
                            
                             BUTTON_CLICKED=true;
                            
                             mediaPlayer1.play();
                             name_score.setText(name+":"+score);
                        }
                        if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                             ans2.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                             
                             next.setVisible(true);
                             
                             BUTTON_CLICKED=true;
                             
                             mediaPlayer.play();
                        }
                             
                        
                        
                             
                    }
                };
                ans2.setOnAction(ev2); 
                EventHandler<ActionEvent> ev3 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int ans=2;
                        tl.stop();
                        if(CORRECT_ANSWER_POSITION==ans&& !BUTTON_CLICKED){
                            ans3.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                            next.setVisible(true);
                            score+=100;
                            mediaPlayer1.play();
                            name_score.setText(name+":"+score);
                            
                             BUTTON_CLICKED=true;
                        }
                        if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                             ans3.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                             
                             next.setVisible(true);
                             
                             BUTTON_CLICKED=true;
                             
                             mediaPlayer.play();
                        }
                             
                    }
                };
                ans3.setOnAction(ev3); 
                EventHandler<ActionEvent> ev4 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int ans =3;
                        tl.stop();
                        if(CORRECT_ANSWER_POSITION==ans&& !BUTTON_CLICKED){
                            ans4.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                            next.setVisible(true);
                            score+=100;
                            mediaPlayer1.play();
                            name_score.setText(name+":"+score);
                            
                             BUTTON_CLICKED=true;
                        }
                        if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                             ans4.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                             
                             next.setVisible(true);
                             
                             BUTTON_CLICKED=true;
                             
                             mediaPlayer.play();
                        }
                             
                    }
                };
                
                ans4.setOnAction(ev4); 
                EventHandler<ActionEvent> ev5 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        COUNTER_FOR_QUESTIONS++;
                        q_num++;
                        sec=STARTTIME;
                        
                        timesec.setText(sec.toString());
                        tl.playFromStart();
                        question.setText(questions.get(COUNTER_FOR_QUESTIONS));
                        quest.setText("Question: "+q_num);
                        ans1.setText("");
                        ans2.setText("");
                        ans3.setText("");
                        ans4.setText("");
                        BUTTON_CLICKED=false;
                        Random rand = new Random();
                        
                        ans1.setStyle("-fx-font-size:15;");
                        ans2.setStyle("-fx-font-size:15;");
                        ans3.setStyle("-fx-font-size:15;");
                        ans4.setStyle("-fx-font-size:15;");
                            int CORRECT_ANSWER_POSITION = rand.nextInt(3);
                            if(CORRECT_ANSWER_POSITION==0){
                                ans1.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                            }
                            if(CORRECT_ANSWER_POSITION==1){
                                ans2.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                            }
                            if(CORRECT_ANSWER_POSITION==2){
                                ans3.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                            }
                            if(CORRECT_ANSWER_POSITION==3){
                                ans4.setText(corAns.get(COUNTER_FOR_QUESTIONS));
                            }
                            String[] FakeArr = fakeAns.get(COUNTER_FOR_QUESTIONS).split(";");
                            ArrayList<String> FakeArr2 = new ArrayList<>(Arrays.asList(FakeArr));
                            System.out.println(FakeArr2.size());
                            Collections.shuffle(FakeArr2);
                            if(ans1.getText()==""){
                                ans1.setText(FakeArr2.get(0));
                            }
                            if(ans2.getText()==""){
                                ans2.setText(FakeArr2.get(1));
                            }
                            if(ans3.getText()==""){
                                ans3.setText(FakeArr2.get(2));
                            }
                            if(ans4.getText()==""){
                                ans4.setText(FakeArr2.get(3));
                            }
                            EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    tl.stop();
                                    int ans =0;
                                    if(CORRECT_ANSWER_POSITION==ans && !BUTTON_CLICKED){
                                        mediaPlayer1.stop();
                                        ans1.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                                        next.setVisible(true);
                                        score+=100;
                                        mediaPlayer1.play();    
                                        name_score.setText(name+":"+score);
                                        BUTTON_CLICKED = true;

                                    }
                                    if(CORRECT_ANSWER_POSITION!=ans && !BUTTON_CLICKED){
                                         
                                        mediaPlayer.stop();
                                        ans1.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                                         timesec.setText("0");
                                         next.setVisible(true);
                                         BUTTON_CLICKED=true;
                                         mediaPlayer.play();
                                    }

                                }
                            };
                            ans1.setOnAction(ev); 
                            EventHandler<ActionEvent> ev2 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    int ans=1;
                                    tl.stop();
                                    if(CORRECT_ANSWER_POSITION==ans && !BUTTON_CLICKED){
                                        ans2.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                                        mediaPlayer1.stop();
                                        next.setVisible(true);
                                        score+=100;
                                        mediaPlayer1.play();
                                         BUTTON_CLICKED=true;
                                        name_score.setText(name+":"+score);
                                    }
                                    if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                                        mediaPlayer.stop(); 
                                        ans2.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                                         timesec.setText("0");
                                         next.setVisible(true);
                                         mediaPlayer.play();
                                         BUTTON_CLICKED=true;
                                    }


                                }
                            };
                            ans2.setOnAction(ev2); 
                            EventHandler<ActionEvent> ev3 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    int ans=2;
                                    tl.stop();
                                    if(CORRECT_ANSWER_POSITION==ans&& !BUTTON_CLICKED){
                                        mediaPlayer1.stop();
                                        ans3.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                                        next.setVisible(true);
                                        score+=100;
                                        mediaPlayer1.play();
                                        name_score.setText(name+":"+score);

                                         BUTTON_CLICKED=true;
                                    }
                                    if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                                        mediaPlayer.stop(); 
                                        ans3.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                                         timesec.setText("0");
                                         next.setVisible(true);
                                         mediaPlayer.play();
                                         BUTTON_CLICKED=true;
                                    }

                                }
                            };
                            ans3.setOnAction(ev3); 
                            EventHandler<ActionEvent> ev4 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    int ans =3;
                                    tl.stop();
                                    if(CORRECT_ANSWER_POSITION==ans&& !BUTTON_CLICKED){
                                        mediaPlayer1.stop();
                                        ans4.setStyle("-fx-font-size:15;-fx-border-color: green;-fx-border-width:3px;-fx-border-radius:4;");
                                        next.setVisible(true);
                                        score+=100;
                                        mediaPlayer1.play();
                                        name_score.setText(name+":"+score);

                                         BUTTON_CLICKED=true;
                                    }
                                    if(CORRECT_ANSWER_POSITION!=ans&& !BUTTON_CLICKED){
                                        mediaPlayer.stop(); 
                                        ans4.setStyle("-fx-font-size:15;-fx-border-color: red;-fx-border-width:3px;-fx-border-radius:4;");
                                         timesec.setText("0");
                                         next.setVisible(true);
                                         mediaPlayer.play();
                                         BUTTON_CLICKED=true;
                                    }

                                }
                            };
                            ans4.setOnAction(ev4);
                            if(COUNTER_FOR_QUESTIONS==10){
                                stage2.close();
                                Stage stage3 = new Stage();
                                Group root3 = new Group();
                                DateFormat datef = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date= new Date();
                                Text text = new Text(name+":"+score+" "+datef.format(date));
                                text.setTranslateX(130);
                                text.setTranslateY(150);
                                stage3.setScene(new Scene(root3, 300, 300));
                                root3.getChildren().add(text);
                                stage3.show();
                            }
                    }
                
                };
                next.setOnAction(ev5);
                root2.getChildren().add(imgv2);
                root2.getChildren().add(name_score);
                root2.getChildren().add(ans1);
                root2.getChildren().add(ans3);
                root2.getChildren().add(ans4);
                root2.getChildren().add(ans2);
                root2.getChildren().add(rec);
                root2.getChildren().add(quest);
                root2.getChildren().add(time);
                root2.getChildren().add(timesec);
                root2.getChildren().add(question);
                root2.getChildren().add(next);
                
                stage2.show();
                primaryStage.close();
            }
            
        };
        
       
        btn.setOnAction(event);
        
        Group root = new Group();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
             @Override
            public void handle(KeyEvent eventq) {
                if(eventq.getCode().equals(KeyCode.ENTER)) {
                    
                    ActionEvent act = (ActionEvent) event;
                    act.consume();
                }
            }
        });
        
        
        
        root.getChildren().add(imgv);
        root.getChildren().add(btn);
        root.getChildren().add(txt);
        root.getChildren().add(t);
        
        
        Scene scene = new Scene(root, 400 , 350);
        
        primaryStage.setTitle("Who wants to be a MILLIONAIRE?");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
