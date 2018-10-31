/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildingconstruction_v1_2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author adhithadias27
 */
public class Tile extends Rectangle{
    private Piece piece;
    private int coordinateX, coordinateY;
    private TileType tileType;
    
    public double getXCoordinate() {
        return coordinateX;
    }
    
    public double getYCoordinate() {
        return coordinateY;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public TileType getTileType(){
        return tileType;
    }

    public Tile(boolean light, int x, int y, TileType type) {
        setWidth(Arena.TILE_SIZE);
        setHeight(Arena.TILE_SIZE);
        
        coordinateX = x;
        coordinateY = y;
        tileType = type;

        relocate(x * Arena.TILE_SIZE, y * Arena.TILE_SIZE);

        if(tileType != TileType.NONE){
            setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
        }else{
            setFill(Color.BLACK);
        }
        
    }    
}
