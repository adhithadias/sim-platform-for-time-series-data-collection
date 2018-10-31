/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildingconstruction_v1_2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author adhithadias27
 */

public class Arena extends Application {
    
    String pathNameAll = "";
    String pathNameAllSimple = "";
    String pathNameBuilding = "";
    String pathNameBuildingSimple = "";
    String pathNameBuildingMoveAll = "";
    String pathNameBuildingMoveSimple = "";
    int seqLength = 50;
    int seqBuildingLength = 45;
    int currSeqNum = 0;
    int currBuildSeqNum = 0;
    
    public static final int TILE_SIZE = 40;
    public static final int TOTAL_WIDTH = 13;
    public static final int TOTAL_HEIGHT = 23;
    
    public static final int BLOCK_AREA_HEIGHT = 8;
    public static final int BLOCK_AREA_WIDTH = 11;
    public static final int BUILDING_AREA_HEIGHT = 3;
    public static final int BUILDING_AREA_WIDTH = 5;
    
    // Define thses parameters correctly
    public int blockAreaStartingXCoordinate = 1;
    public int blockAreaStartingYCoordinate = 13;
    public int b1StartingXCoordinate = 4;
    public int b1StartingYCoordinate = 9;
    public int b2StartingXCoordinate = 4;
    public int b2StartingYCoordinate = 5;
    public int b3StartingXCoordinate = 4;
    public int b3StartingYCoordinate = 1;
    
    private Tile[][] board = new Tile[TOTAL_WIDTH][TOTAL_HEIGHT];
    
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    
    String whichButtonIsLastClicked = "NONE";
    boolean addBlocksButtonPressed = false;
    boolean beginConstructionButtonPressed = false;
    PieceType whichPieceButtonPressed = null;
    
    private Parent createContent() {
        
        BorderPane root = new BorderPane();
        
        Pane blockPlacementArea = new Pane();      
        blockPlacementArea.setPrefSize(TOTAL_WIDTH * TILE_SIZE, TOTAL_HEIGHT * TILE_SIZE);
        makeArena();
        
        MenuBar menuBar = new MenuBar();
        // --- Menu File
        Menu menuFile = new Menu("File");
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        // --- Menu View
        Menu menuView = new Menu("View");
        // --- Menu View
        Menu menuHelp = new Menu("Help");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        

        VBox componentPane = new VBox();
        componentPane.setPadding(new Insets(15, 12, 15, 12));
        componentPane.setSpacing(10);
        componentPane.setStyle("-fx-background-color: #336699;");  //DAE6F3 #336699
        
        Button robot1Button = new Button();
        robot1Button.setText("MR 1");
        robot1Button.setPrefSize(80, 80);
        robot1Button.setOnAction((ActionEvent event) -> {
            System.out.println("You selected mobile robot 1");
        });
        
        Button robot2Button = new Button();
        robot2Button.setText("MR 2");
        robot2Button.setPrefSize(80, 80);
        robot2Button.setOnAction((ActionEvent event) -> {
            System.out.println("You selected mobile robot 2");
        });
        
        Button robot3Button = new Button();
        robot3Button.setText("SR 1");
        robot3Button.setPrefSize(80, 80);
        robot3Button.setOnAction((ActionEvent event) -> {
            System.out.println("You selected stationary robot 1");
        });
        
        Button boxButton = new Button();
        boxButton.setText("Box");
        boxButton.setPrefSize(80, 80);
        boxButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed box button");
            whichPieceButtonPressed = PieceType.BOX;
        });        

        Button cylinderButton = new Button();
        cylinderButton.setText("Cylinder");
        cylinderButton.setPrefSize(80, 80);
        cylinderButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed cylinder button");
            whichPieceButtonPressed = PieceType.CYLINDER;
        });

        Button horBridgeButton = new Button();
        horBridgeButton.setText("Horizontal \nBridge");
        horBridgeButton.setPrefSize(80, 80);
        horBridgeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You pressed horizontal bridge button");
                whichPieceButtonPressed = PieceType.BRIDGE_HORIZONTAL;
            }
        });
        
        Button verBridgeButton = new Button();
        verBridgeButton.setText("Vertical \nBridge");
        verBridgeButton.setPrefSize(80, 80);
        verBridgeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You pressed horizontal bridge button");             
                whichPieceButtonPressed = PieceType.BRIDGE_VERTICAL;
            }
        });
        
        componentPane.getChildren().addAll(robot1Button, robot2Button, robot3Button, boxButton, cylinderButton, horBridgeButton, verBridgeButton);
        
        VBox controlPane = new VBox();
        controlPane.setPadding(new Insets(15, 12, 15, 12));
        controlPane.setSpacing(10);
        controlPane.setStyle("-fx-background-color: #336699;");  //DAE6F3
        
        Button fastLayoutButton = new Button();
        fastLayoutButton.setText("Fast Layout");
        fastLayoutButton.setPrefSize(80, 80);
        fastLayoutButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed fast layout button");
            
            pieceGroup.getChildren().clear();
            autoGenerateInitialBlockPositions();
        });
        
        Button addBlocksButton = new Button();
        addBlocksButton.setText("Add Blocks");
        addBlocksButton.setPrefSize(80, 80);
        addBlocksButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed addBlocksButton");
            
            if(addBlocksButtonPressed){
                addBlocksButtonPressed = false;
                addBlocksButton.setStyle("-fx-background-color: #f8f8ff;");
            }else{
                addBlocksButtonPressed = true;
                addBlocksButton.setStyle("-fx-background-color: #ffdead;");
            }
        });
        
        Button clearArenaButton = new Button();
        clearArenaButton.setText("Clear Arena");
        clearArenaButton.setPrefSize(80, 80);
        clearArenaButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed clearArenaButton");
            
            pieceGroup.getChildren().clear();
            // clear grid
            for (int x=0; x<TOTAL_WIDTH; x++){
                for (int y=0; y<TOTAL_HEIGHT; y++){
                    if (board[x][y].hasPiece()){
                        board[x][y].setPiece(null);
                    }
                }
            }
            
            beginConstructionButtonPressed = false;
            addBlocksButton.setDisable(false);
            fastLayoutButton.setDisable(false);
            boxButton.setDisable(false);
            cylinderButton.setDisable(false);
            horBridgeButton.setDisable(false);
            verBridgeButton.setDisable(false);
        });
        
        Button beginConstructionButton = new Button();
        beginConstructionButton.setText("Begin \nConstruction");
        beginConstructionButton.setPrefSize(80, 80);
        beginConstructionButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed beginConstructionButton");
            
            beginConstructionButtonPressed = true;
            addBlocksButtonPressed = false;
            whichPieceButtonPressed = null;
            addBlocksButton.setDisable(true);
            fastLayoutButton.setDisable(true);
            boxButton.setDisable(true);
            cylinderButton.setDisable(true);
            horBridgeButton.setDisable(true);
            verBridgeButton.setDisable(true);
            
            currSeqNum = 0;
            currBuildSeqNum = 0;
            
            // create file to save time series data
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss") ;
            pathNameAll = "D:\\FYP\\Data\\AllData\\data_" + dateFormat.format(date) + ".txt";
            pathNameAllSimple = "D:\\FYP\\Data\\AllDataSimple\\dataSimple_" + dateFormat.format(date) + ".txt";
            pathNameBuilding = "D:\\FYP\\Data\\BuildingData\\building_" + dateFormat.format(date) + ".txt";
            pathNameBuildingSimple = "D:\\FYP\\Data\\BuildingDataSimple\\buildingSimple_" + dateFormat.format(date) + ".txt";
            pathNameBuildingMoveSimple = "D:\\FYP\\Data\\BuildingMoves\\buildingMoveSimple_" + dateFormat.format(date) + ".txt";
            pathNameBuildingMoveAll = "D:\\FYP\\Data\\BuildingMovesComplex\\buildingMoveAll_" + dateFormat.format(date) + ".txt";
            
            // write initial data to file
            WriteToFile w = new WriteToFile();
            w.writeToFile(board, pathNameAll, WriteType.ALL); 
            w.writeToFile(board, pathNameAllSimple, WriteType.ALL_SIMPLE);
            w.writeToFile(board, pathNameBuilding, WriteType.BUILDING);
            w.writeToFile(board, pathNameBuildingSimple, WriteType.BUILDING_SIMPLE);
//            w.writeMoveToFile(board, pathNameBuildingMoveSimple, WriteType.BUILDING_SIMPLE);
//            w.writeMoveToFile(board, pathNameBuildingMoveAll, WriteType.BUILDING);
            currSeqNum++;
            currBuildSeqNum++;
        });
        
        Button endConstructionButton = new Button();
        endConstructionButton.setText("End \nConstruction");
        endConstructionButton.setPrefSize(80, 80);
        endConstructionButton.setOnAction((ActionEvent event) -> {
            System.out.println("You pressed endConstructionButton");
            
            beginConstructionButtonPressed = false;
            addBlocksButtonPressed = false;
            whichPieceButtonPressed = null;
            addBlocksButton.setDisable(false);
            fastLayoutButton.setDisable(false);
            boxButton.setDisable(false);
            cylinderButton.setDisable(false);
            horBridgeButton.setDisable(false);
            verBridgeButton.setDisable(false);
            
            // write initial data to file
            WriteToFile w = new WriteToFile();
            w.completeWriteToFile(board, pathNameAll, WriteType.ALL, seqLength, currSeqNum); 
            w.completeWriteToFile(board, pathNameAllSimple, WriteType.ALL_SIMPLE, seqLength, currSeqNum);
            w.completeWriteToFile(board, pathNameBuilding, WriteType.BUILDING, seqBuildingLength, currBuildSeqNum);
            w.completeWriteToFile(board, pathNameBuildingSimple, WriteType.BUILDING_SIMPLE, seqBuildingLength, currBuildSeqNum);
            
            w.completeWriteMovesToFile(board, pathNameBuildingMoveSimple, WriteType.BUILDING_SIMPLE, seqBuildingLength, currBuildSeqNum);
            w.completeWriteMovesToFile(board, pathNameBuildingMoveAll, WriteType.BUILDING, seqBuildingLength, currBuildSeqNum);
            
            pieceGroup.getChildren().clear();
            // clear grid
            for (int x=0; x<TOTAL_WIDTH; x++){
                for (int y=0; y<TOTAL_HEIGHT; y++){
                    if (board[x][y].hasPiece()){
                        board[x][y].setPiece(null);
                    }
                }
            }
        });
        
        controlPane.getChildren().addAll(fastLayoutButton, addBlocksButton, clearArenaButton, beginConstructionButton, endConstructionButton);  
      
        blockPlacementArea.getChildren().addAll(tileGroup, pieceGroup);  
        
        root.setTop(menuBar);
        root.setLeft(controlPane);
        root.setRight(componentPane);
        root.setCenter(blockPlacementArea);
        
        return root;
    }
    
    private void autoGenerateInitialBlockPositions(){
        Piece piece;
        
        int boxPlacementY = blockAreaStartingYCoordinate+BLOCK_AREA_HEIGHT/2+1;
        int boxPlacementX = blockAreaStartingXCoordinate+BLOCK_AREA_WIDTH/2+1;
        int cylinderPlacementY = blockAreaStartingYCoordinate+BLOCK_AREA_HEIGHT/2+2;
        int cylinderPlacementX = blockAreaStartingXCoordinate+1;
        int verticalBridgePlacementY = blockAreaStartingYCoordinate;
        int verticalBridgePlacementX = blockAreaStartingXCoordinate;
        System.out.println(boxPlacementY);
        System.out.println(boxPlacementX);
        System.out.println(cylinderPlacementY);
        System.out.println(cylinderPlacementX);
        
        // Place boxes on the bottom right of the arena
        for (int y = boxPlacementY; y < boxPlacementY+3; y++){
            for (int x = boxPlacementX; x < boxPlacementX+5; x++){
                piece = makePiece(PieceType.BOX, x, y);
                board[x][y].setPiece(piece);
                pieceGroup.getChildren().add(piece);               
            }
        }
                
        // Place cylinders on the bottom left of the arena
        for (int y = cylinderPlacementY; y < cylinderPlacementY+2; y++){
            for (int x = cylinderPlacementX; x < cylinderPlacementX+3; x++){
                piece = makePiece(PieceType.CYLINDER, x, y);
                board[x][y].setPiece(piece);
                pieceGroup.getChildren().add(piece);              
            }
        }
        
//        piece = makePiece(PieceType.BRIDGE_VERTICAL, verticalBridgePlacementX, verticalBridgePlacementY);
//        board[verticalBridgePlacementX][verticalBridgePlacementY].setPiece(piece);
//        pieceGroup.getChildren().add(piece);
        
        // place the horizontal bridge structure on left side of the arena
        piece = makePiece(PieceType.BRIDGE_HORIZONTAL, verticalBridgePlacementX+1, verticalBridgePlacementY+3);
        board[verticalBridgePlacementX+1][verticalBridgePlacementY+3].setPiece(piece);
        pieceGroup.getChildren().add(piece);        
    }
    
    private boolean isInBlockPlacementArea(int x, int y){
        if(x >= blockAreaStartingXCoordinate & y >= blockAreaStartingYCoordinate & 
                        x < blockAreaStartingXCoordinate+BLOCK_AREA_WIDTH & y < blockAreaStartingYCoordinate+BLOCK_AREA_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isInBuildingLevel1Area(int x, int y){
        if(x >= b1StartingXCoordinate & y >= b1StartingYCoordinate & 
                        x < b1StartingXCoordinate+BUILDING_AREA_WIDTH & y < b1StartingYCoordinate+BUILDING_AREA_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isInBuildingLevel2Area(int x, int y){
        if(x >= b2StartingXCoordinate & y >= b2StartingYCoordinate & 
                        x < b2StartingXCoordinate+BUILDING_AREA_WIDTH & y < b2StartingYCoordinate+BUILDING_AREA_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isInBuildingLevel3Area(int x, int y){
        if(x >= b3StartingXCoordinate & y >= b3StartingYCoordinate & 
                        x < b3StartingXCoordinate+BUILDING_AREA_WIDTH & y < b3StartingYCoordinate+BUILDING_AREA_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isInBuildingArea(int x, int y){
        if(isInBuildingLevel1Area(x, y) ||  isInBuildingLevel2Area(x, y) || isInBuildingLevel3Area(x, y)){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isNearStationaryRobot(int x, int y){
        if(y >= blockAreaStartingYCoordinate && y < blockAreaStartingYCoordinate+2 && 
                x >= blockAreaStartingXCoordinate+(BLOCK_AREA_WIDTH-BUILDING_AREA_WIDTH)/2 && x < blockAreaStartingXCoordinate+(BLOCK_AREA_WIDTH+BUILDING_AREA_WIDTH)/2){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isOccupied(Piece piece, int x, int y){
        //System.out.println("is occupied");
        if(board[x][y].hasPiece()){
            return true;
        }else{
            if( (board[x-1][y].hasPiece() && board[x-1][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x+1][y].hasPiece() && board[x+1][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x][y-1].hasPiece() && board[x][y-1].getPiece().getType()==PieceType.BRIDGE_VERTICAL) ||
                (board[x][y+1].hasPiece() && board[x][y+1].getPiece().getType()==PieceType.BRIDGE_VERTICAL) ||
                (board[x-1][y].hasPiece() && piece.getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x+1][y].hasPiece() && piece.getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x][y-1].hasPiece() && piece.getType()==PieceType.BRIDGE_VERTICAL) ||
                (board[x][y+1].hasPiece() && piece.getType()==PieceType.BRIDGE_VERTICAL)    
                    ){
                return true;
            }else{
                return false;
            }
        }
    }
    
    private boolean belowPositionOccupied(int x, int y){
        if(board[x][y].hasPiece()){
            return true;
        }else if(
                (board[x-1][y].hasPiece() && board[x-1][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x+1][y].hasPiece() && board[x+1][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL) ||
                (board[x][y-1].hasPiece() && board[x][y-1].getPiece().getType()==PieceType.BRIDGE_VERTICAL) ||
                (board[x][y+1].hasPiece() && board[x][y+1].getPiece().getType()==PieceType.BRIDGE_VERTICAL)
                ){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isValidPlacementInBuildingArea(Piece piece, int x, int y){
        if(isInBuildingLevel1Area(x, y)){
            return true;
        }else if(isInBuildingLevel2Area(x, y) || isInBuildingLevel3Area(x, y)){
            int belowX = 0;
            int belowY = 0;
            
            if(isInBuildingLevel2Area(x, y)){
                belowX = x-b2StartingXCoordinate+b1StartingXCoordinate;
                belowY = y-b2StartingYCoordinate+b1StartingYCoordinate;    
            }else if(isInBuildingLevel3Area(x, y)){
                belowX = x-b3StartingXCoordinate+b2StartingXCoordinate;
                belowY = y-b3StartingYCoordinate+b2StartingYCoordinate;   
            }
            
            if(piece.getType()==PieceType.BOX || piece.getType()==PieceType.CYLINDER){
                return belowPositionOccupied(belowX, belowY);
            }else if(piece.getType()==PieceType.BRIDGE_HORIZONTAL){
                if(belowPositionOccupied(belowX-1, belowY) && belowPositionOccupied(belowX+1, belowY)){
                    return true;
                }else{
                    return false;
                }
            }else if(piece.getType()==PieceType.BRIDGE_VERTICAL){
                if(belowPositionOccupied(belowX, belowY-1) && belowPositionOccupied(belowX, belowY+1)){
                    return true;
                }else{
                    return false;
                }                
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    private void makeArena(){
        for (int y = 0; y < TOTAL_HEIGHT; y++) {
            for (int x = 0; x < TOTAL_WIDTH; x++) {
                
                if(isInBlockPlacementArea(x, y)){
                    Tile tile = makeTile((x + y) % 2 == 0, x, y, TileType.BLOCK);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);     
                }else if(isInBuildingLevel3Area(x, y)){
                    Tile tile = makeTile((x + y) % 2 == 0, x, y, TileType.B3);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);                     
                }else if(isInBuildingLevel2Area(x, y)){
                    Tile tile = makeTile((x + y) % 2 == 0, x, y, TileType.B2);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);                    
                }else if(isInBuildingLevel1Area(x, y)){
                    Tile tile = makeTile((x + y) % 2 == 0, x, y, TileType.B1);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);                        
                }else{
                    Tile tile = makeTile((x + y) % 2 == 0, x, y, TileType.NONE);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);
                }
            }
        }    
    }
    
    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }
    
    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());
            
            System.out.print(x0);
            System.out.println(y0);
            System.out.print(newX);
            System.out.println(newY);

            MoveResult result;

            result = tryMove(piece, newX, newY);

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case VALID:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    
                    // write data to file
                    WriteToFile w = new WriteToFile();
                    w.writeToFile(board, pathNameAll, WriteType.ALL); 
                    w.writeToFile(board, pathNameAllSimple, WriteType.ALL_SIMPLE);
                    currSeqNum++;
                    
                    if(board[newX][newY].getTileType()!= TileType.BLOCK){
                        w.writeToFile(board, pathNameBuilding, WriteType.BUILDING);
                        w.writeToFile(board, pathNameBuildingSimple, WriteType.BUILDING_SIMPLE);
                        w.writeMoveToFile(board, pathNameBuildingMoveSimple, WriteType.BUILDING_SIMPLE, newX, newY);
                        w.writeMoveToFile(board, pathNameBuildingMoveAll, WriteType.BUILDING, newX, newY);
                        currBuildSeqNum++;
                    }
                    break;
            }
        });

        return piece;
    }
    
    private MoveResult tryMove(Piece piece, int newX, int newY) {
            
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());    
            
        if(newX < 0 || newY < 0 || newX >= TOTAL_WIDTH || newY >= TOTAL_HEIGHT){        // out of grid area -> not valid
            return new MoveResult(MoveType.NONE);
        }else if (isOccupied(piece, newX, newY) || board[newX][newY].getTileType()==TileType.NONE) {  // out of grid area or tile already occupied -> not valid
            return new MoveResult(MoveType.NONE);
        }else if(isInBuildingArea(x0, y0) && (isInBuildingArea(newX, newY) || isInBlockPlacementArea(newX, newY))){          // building area to building area -> not valid
            return new MoveResult(MoveType.NONE);
        }else if(isInBlockPlacementArea(x0, y0) && isNearStationaryRobot(newX, newY)){
            return new MoveResult(MoveType.VALID);
        }else if(isNearStationaryRobot(x0, y0) && isInBuildingArea(newX, newY) && isValidPlacementInBuildingArea(piece, newX, newY)){
            return new MoveResult(MoveType.VALID);
        }else{
            return new MoveResult(MoveType.NONE);
        }
    }
    
    private Tile makeTile(boolean light, int x, int y, TileType type) {
        Tile tile = new Tile(light, x, y, type);
        
        tile.setOnMouseClicked(e -> {
            System.out.println("["+tile.getXCoordinate()+", "+tile.getYCoordinate()+"]");
            
            if(addBlocksButtonPressed){
                int x0 = (int)tile.getXCoordinate();
                int y0 = (int)tile.getYCoordinate();
                
                Piece piece = null;
                
                if(whichPieceButtonPressed==PieceType.BOX){
                    piece = makePiece(PieceType.BOX, x0, y0);
                }else if(whichPieceButtonPressed==PieceType.CYLINDER){
                    piece = makePiece(PieceType.CYLINDER, x0, y0);
                }else if(whichPieceButtonPressed==PieceType.BRIDGE_HORIZONTAL){
                    piece = makePiece(PieceType.BRIDGE_HORIZONTAL, x0, y0);
                }else if(whichPieceButtonPressed==PieceType.BRIDGE_VERTICAL){
                    piece = makePiece(PieceType.BRIDGE_VERTICAL, x0, y0);
                }
                board[x0][y0].setPiece(piece);
                pieceGroup.getChildren().add(piece);
            }
        });        
     
        return tile;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("BuildingConstruction");
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
