import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class App extends Application{
    private GraphicsContext gc;
    private final double WIDTH=600;
    private final double HEIGHT=600;
    private int count=0;
    private int PLAYER=0,COMPUTER=0;
    Rectangle2D screenBounds=Screen.getPrimary().getBounds();
    EventHandler<MouseEvent> handler=MouseEvent::consume;

    private boolean spaceKeyPressed=false;

    private final double startX=(screenBounds.getWidth()/2-WIDTH/2);
    private final double startY=(screenBounds.getHeight()/2-HEIGHT/2);

    boolean checkArray[][]=new boolean[3][3]; // All false values

    int boardArray[][]={{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Tic-Tac-Toe");
        Group root=new Group();
        Canvas canvas=new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        root.getChildren().add(canvas);
        Scene scene=new Scene(root,Color.web("#008080"));

        gc=canvas.getGraphicsContext2D();

        gc.setFill(Color.web("#000223"));
        gc.setFont(Font.font("Verdana", FontWeight.BOLD,130));
        gc.fillText("Tic-Tac-Toe",screenBounds.getWidth()/4.6,screenBounds.getHeight()/4);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Verdana",70));
        gc.fillText("Enter SPACE to Start the Game",screenBounds.getWidth()/7.3,screenBounds.getHeight()/1.3);

        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code=event.getCode();
            if(code==KeyCode.ESCAPE){
                System.exit(0);
            }
            else if(code==KeyCode.SPACE && !spaceKeyPressed){
                spaceKeyPressed=true;
                run(gc,scene,stage);
            }
            else{
                event.consume();
            }
        });

        // To close the window
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event){
                System.exit(0);
            }
        });

    }

    public void run(GraphicsContext gc,Scene scene,Stage stage){
        drawBoard(gc);
        playGame(gc,scene,stage);
    }

    public void drawBoard(GraphicsContext gc){
        gc.setFill(Color.web("#008080"));
        gc.fillRect(0, 0, screenBounds.getWidth(), screenBounds.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(startX, startY, WIDTH, HEIGHT);

        gc.setFill(Color.web("#152238"));
        gc.fillRect(startX+WIDTH/3-3, startY, 6, HEIGHT);
        gc.fillRect(startX+2*WIDTH/3-3, startY, 6, HEIGHT);
        gc.fillRect(startX, startY+HEIGHT/3-3, WIDTH, 6);
        gc.fillRect(startX, startY+2*HEIGHT/3-3, WIDTH, 6);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Verdana",90));
        gc.fillText(PLAYER+" : "+COMPUTER,screenBounds.getWidth()/2.32,screenBounds.getHeight()/9);
    }

    public void playGame(GraphicsContext gc,Scene scene,Stage stage){

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            
            @Override
            public void handle(MouseEvent event) {
                scene.removeEventFilter(MouseEvent.ANY, handler); // To enable Mouse Events
                double y=event.getY();
                double x=event.getX();
                
                if(!checkArray[0][0] && x>=startX && x<=startX+WIDTH/3-3 && y>=startY && y<=startY+HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX, startY, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[0][0]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX, startY, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[0][0]=1;
                    }
                    checkArray[0][0]=true;
                    count++;
                }
                else if(!checkArray[0][1] && x>=startX+WIDTH/3+3 && x<=startX+2*WIDTH/3-3 && y>=startY && y<=startY+HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+WIDTH/3+3, startY, WIDTH/3-6, HEIGHT/3-3);
                        boardArray[0][1]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+WIDTH/3+3, startY, WIDTH/3-6, HEIGHT/3-3);
                        boardArray[0][1]=1;
                    }
                    checkArray[0][1]=true;
                    count++;
                }
                else if(!checkArray[0][2] && x>=startX+2*WIDTH/3+3 && x<=startX+WIDTH && y>=startY && y<=startY+HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+2*WIDTH/3+3, startY, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[0][2]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+2*WIDTH/3+3, startY, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[0][2]=1;
                    }
                    checkArray[0][2]=true;
                    count++;
                }
                else if(!checkArray[1][0] && x>=startX && x<=startX+WIDTH/3-3 && y>=startY+HEIGHT/3+3 && y<=startY+2*HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX, startY+HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-6);
                        boardArray[1][0]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX, startY+HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-6);
                        boardArray[1][0]=1;
                    }
                    checkArray[1][0]=true;
                    count++;
                }
                else if(!checkArray[1][1] && x>=startX+WIDTH/3+3 && x<=startX+2*WIDTH/3-3 && y>=startY+HEIGHT/3+3 && y<=startY+2*HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+WIDTH/3+3, startY+HEIGHT/3+3, WIDTH/3-6, HEIGHT/3-6);
                        boardArray[1][1]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+WIDTH/3+3, startY+HEIGHT/3+3, WIDTH/3-6, HEIGHT/3-6);
                        boardArray[1][1]=1;
                    }
                    checkArray[1][1]=true;
                    count++;
                }
                else if(!checkArray[1][2] && x>=startX+2*WIDTH/3+3 && x<=startX+WIDTH && y>=startY+HEIGHT/3+3 && y<=startY+2*HEIGHT/3-3){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+2*WIDTH/3+3, startY+HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-6);
                        boardArray[1][2]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+2*WIDTH/3+3, startY+HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-6);
                        boardArray[1][2]=1;
                    }
                    checkArray[1][2]=true;
                    count++;
                }
                else if(!checkArray[2][0] && x>=startX && x<=startX+WIDTH/3-3 && y>=startY+2*HEIGHT/3+3 && y<=startY+HEIGHT){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX, startY+2*HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[2][0]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX, startY+2*HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[2][0]=1;
                    }
                    checkArray[2][0]=true;
                    count++;
                }
                else if(!checkArray[2][1] && x>=startX+WIDTH/3+3 && x<=startX+2*WIDTH/3-3 && y>=startY+2*HEIGHT/3+3 && y<=startY+HEIGHT){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+WIDTH/3+3, startY+2*HEIGHT/3+3, WIDTH/3-6, HEIGHT/3-3);
                        boardArray[2][1]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+WIDTH/3+3, startY+2*HEIGHT/3+3, WIDTH/3-6, HEIGHT/3-3);
                        boardArray[2][1]=1;
                    }
                    checkArray[2][1]=true;
                    count++;
                }
                else if(!checkArray[2][2] && x>=startX+2*WIDTH/3+3 && x<=startX+WIDTH && y>=startY+2*HEIGHT/3+3 && y<=startY+HEIGHT){
                    if(count%2==0){
                        gc.drawImage(new Image("pink.png"), startX+2*WIDTH/3+3, startY+2*HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[2][2]=0;
                    }
                    else{
                        gc.drawImage(new Image("yellow.jpg"), startX+2*WIDTH/3+3, startY+2*HEIGHT/3+3, WIDTH/3-3, HEIGHT/3-3);
                        boardArray[2][2]=1;
                    }
                    checkArray[2][2]=true;
                    count++;
                }

                if(checkWin(1)){
                    COMPUTER++;
                    drawScoreBoard(gc,scene,stage,1);
                }
                else if(checkWin(0)){
                    PLAYER++;
                    drawScoreBoard(gc,scene,stage,0);
                }
                else if(checkDraw()){
                    drawScoreBoard(gc,scene,stage,-1);
                }
            }
        });

    }

    public boolean checkWin(int x){
        // For Rows:
        for(int i=0;i<3;i++){
            if(boardArray[i][0]==x && boardArray[i][1]==x && boardArray[i][2]==x) return true;
        }
        // For Columns:
        for(int i=0;i<3;i++){
            if(boardArray[0][i]==x && boardArray[1][i]==x && boardArray[2][i]==x) return true;
        }
        // For Diagonals:
        if(boardArray[0][0]==x && boardArray[1][1]==x && boardArray[2][2]==x) return true;
        if(boardArray[0][2]==x && boardArray[1][1]==x && boardArray[2][0]==x) return true;

        return false;
    }

    public boolean checkDraw(){
        if(count==9) return true;
        return false;
    }

    public void drawScoreBoard(GraphicsContext gc,Scene scene,Stage stage,int x){

        scene.addEventFilter(MouseEvent.ANY, handler); // To disable Mouse Events

        PauseTransition pause=new PauseTransition(Duration.seconds(1)); // To pause for x seconds

        pause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                gc.setFill(Color.web("#008080"));
                gc.fillRect(0, 0, screenBounds.getWidth(), screenBounds.getHeight());
                gc.setFill(Color.WHITE);
                gc.setFont(Font.font("Verdana", FontWeight.BOLD,200));

                if(x==1){
                    gc.fillText("X", screenBounds.getWidth()/2.23, screenBounds.getHeight()/3);
                    gc.setFont(Font.font("Verdana", FontWeight.BOLD,100));
                    gc.fillText("WINNER!", screenBounds.getWidth()/3, screenBounds.getHeight()/2);
                }
                else if(x==0){
                    gc.fillText("O", screenBounds.getWidth()/2.23, screenBounds.getHeight()/3);
                    gc.setFont(Font.font("Verdana", FontWeight.BOLD,100));
                    gc.fillText("WINNER!", screenBounds.getWidth()/3, screenBounds.getHeight()/2);
                }
                else if(x==-1){
                    gc.setFont(Font.font("Verdana", FontWeight.BOLD,100));
                    gc.fillText("DRAW!", screenBounds.getWidth()/3, screenBounds.getHeight()/2);
                }

                spaceKeyPressed=false; // To enable SPACE Key event

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        KeyCode code=event.getCode();
                        if(code==KeyCode.SPACE){
                            scene.removeEventFilter(MouseEvent.ANY, handler); // To enable Mouse Events

                            for(int i=0;i<boardArray.length;i++){
                                for(int j=0;j<boardArray[i].length;j++){
                                    boardArray[i][j]=-1;
                                }
                            }
                            for(int i=0;i<checkArray.length;i++){
                                for(int j=0;j<checkArray[i].length;j++){
                                    checkArray[i][j]=false;
                                }
                            }
                            count=0;
                            run(gc,scene,stage);
                        }
                        else if(code==KeyCode.ESCAPE){
                            System.exit(0);
                        }
                    }
                });

            }
        });
        pause.play();

    }
    

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
