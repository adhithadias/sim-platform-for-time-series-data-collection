/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildingconstruction_v1_2;

import static buildingconstruction_v1_2.Arena.TILE_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author adhithadias27
 */
public class Piece extends StackPane{
    private PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);
        
        Shape background;
        Shape object;

        if(type == PieceType.CYLINDER){
            
            background = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);     // background eclipse
            background.setFill(Color.BLACK);    // background colour set to black

            background.setStroke(Color.BLACK);
            background.setStrokeWidth(TILE_SIZE * 0.03);

            background.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            background.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);      // slightly larger than the next eclipse

            object = new Ellipse(Arena.TILE_SIZE * 0.3125, Arena.TILE_SIZE * 0.26);
            object.setFill(Color.valueOf("#c40003"));

            object.setStroke(Color.BLACK);
            object.setStrokeWidth(TILE_SIZE * 0.03);

            object.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            object.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2); 
            
        }else if(type == PieceType.BOX){
            
            background = new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 0.4);
            background.setFill(Color.BLACK);

            background.setStroke(Color.BLACK);
            background.setStrokeWidth(TILE_SIZE * 0.03);

            background.setTranslateX((TILE_SIZE - TILE_SIZE * 0.4) / 2 - TILE_SIZE * 0.07);
            background.setTranslateY((TILE_SIZE - TILE_SIZE * 0.4) / 2 + TILE_SIZE * 0.07);

            object = new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 0.4);
            object.setFill(Color.valueOf("#fff9f4"));

            object.setStroke(Color.BLACK);
            object.setStrokeWidth(TILE_SIZE * 0.04);

            object.setTranslateX((TILE_SIZE - TILE_SIZE * 0.4) / 2);
            object.setTranslateY((TILE_SIZE - TILE_SIZE * 0.4) / 2); 
            
        }else if(type == PieceType.BRIDGE_HORIZONTAL){
            
            background = new Rectangle(TILE_SIZE * 2.4, TILE_SIZE * 0.4);
            background.setFill(Color.BLACK);

            background.setStroke(Color.BLACK);
            background.setStrokeWidth(TILE_SIZE * 0.03);

            background.setTranslateX((TILE_SIZE - TILE_SIZE * 2.4) / 2 - TILE_SIZE * 0.07);
            background.setTranslateY((TILE_SIZE - TILE_SIZE * 0.4) / 2 + TILE_SIZE * 0.07);

            object = new Rectangle(TILE_SIZE * 2.4, TILE_SIZE * 0.4);
            object.setFill(Color.valueOf("#fff9f4"));

            object.setStroke(Color.BLACK);
            object.setStrokeWidth(TILE_SIZE * 0.04);

            object.setTranslateX((TILE_SIZE - TILE_SIZE * 2.4) / 2);
            object.setTranslateY((TILE_SIZE - TILE_SIZE * 0.4) / 2);  
            
        }else {
            
            background = new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 2.4);
            background.setFill(Color.BLACK);

            background.setStroke(Color.BLACK);
            background.setStrokeWidth(TILE_SIZE * 0.03);

            background.setTranslateX((TILE_SIZE - TILE_SIZE * 0.4) / 2 - TILE_SIZE * 0.07);
            background.setTranslateY((TILE_SIZE - TILE_SIZE * 2.4) / 2 + TILE_SIZE * 0.07);

            object = new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 2.4);
            object.setFill(Color.valueOf("#fff9f4"));

            object.setStroke(Color.BLACK);
            object.setStrokeWidth(TILE_SIZE * 0.04);

            object.setTranslateX((TILE_SIZE - TILE_SIZE * 0.4) / 2);
            object.setTranslateY((TILE_SIZE - TILE_SIZE * 2.4) / 2); 
            
        }
        
        getChildren().addAll(background, object);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    public void move(int x, int y) {
        oldX = x * Arena.TILE_SIZE;
        oldY = y * Arena.TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }     
}
