/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildingconstruction_v1_2;

import static buildingconstruction_v1_2.Arena.TOTAL_HEIGHT;
import static buildingconstruction_v1_2.Arena.TOTAL_WIDTH;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author adhithadias27
 */
public class WriteToFile {
    
    public void writeToFile(Tile[][] board, String pathName, WriteType type){
        String pathname = "D:\\FYP\\Data\\example.txt";
//        String data = "[";
        String data = "";
        boolean writeEnable = false;
        
        for (int y=0; y<TOTAL_HEIGHT; y++){
            for (int x=0; x<TOTAL_WIDTH; x++){
                if(board[x][y].getTileType()!=TileType.NONE && type == WriteType.ALL){
                    if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BOX){
//                        String cell = "[1, 0, 0, 0, 0],\n";
                        String cell = "1, 0, 0, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.CYLINDER){
//                        String cell = "[0, 1, 0, 0, 0],\n";
                        String cell = "0, 1, 0, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL){
//                        String cell = "[0, 0, 1, 0, 0],\n";
                        String cell = "0, 0, 1, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_VERTICAL){
//                        String cell = "[0, 0, 0, 1, 0],\n";
                        String cell = "0, 0, 0, 1, 0|";
                        data = data + cell;    
                    }else{
//                        String cell = "[0, 0, 0, 0, 1],\n";
                        String cell = "0, 0, 0, 0, 1|";
                        data = data + cell;    
                    }
                }else if(board[x][y].getTileType()!=TileType.NONE && type == WriteType.ALL_SIMPLE){
                    if(board[x][y].hasPiece()){
                        data = data + " 1,";
                    }else{
                        data = data + " 0,";
                    }
                }else if(board[x][y].getTileType()!=TileType.NONE && board[x][y].getTileType()!=TileType.BLOCK && type == WriteType.BUILDING){
                    if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BOX){
//                        String cell = "[1, 0, 0, 0, 0],\n";
                        String cell = "1, 0, 0, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.CYLINDER){
//                        String cell = "[0, 1, 0, 0, 0],\n";
                        String cell = "0, 1, 0, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL){
//                        String cell = "[0, 0, 1, 0, 0],\n";
                        String cell = "0, 0, 1, 0, 0|";
                        data = data + cell;    
                    }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_VERTICAL){
//                        String cell = "[0, 0, 0, 1, 0],\n";
                        String cell = "0, 0, 0, 1, 0|";
                        data = data + cell;    
                    }else{
//                        String cell = "[0, 0, 0, 0, 1],\n";
                        String cell = "0, 0, 0, 0, 1|";
                        data = data + cell;    
                    }
                }else if(board[x][y].getTileType()!=TileType.NONE && board[x][y].getTileType()!=TileType.BLOCK && type == WriteType.BUILDING_SIMPLE){
                    if(board[x][y].hasPiece()){
                        data = data + " 1,";
                    }else{
                        data = data + " 0,";
                    }
                }
            }
        }
        
        data = data.substring(0, data.length()-1);
//        System.out.println(data);
//        data = data + "]\n";
        
        try(
                FileWriter fw = new FileWriter(pathName, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
        {
            
            out.println(data);
            //more code
//            out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }
    }

    void completeWriteToFile(Tile[][] board, String pathName, WriteType type, int seqLength, int currSeqNum) {
        for (int i=currSeqNum; i < seqLength; i++){
            writeToFile(board, pathName, type);
        }   
    } 

    void writeMoveToFile(Tile[][] board, String pathName, WriteType type, int newX, int newY) {
        String data = "";
        
        for (int y=0; y<TOTAL_HEIGHT; y++){
            for (int x=0; x<TOTAL_WIDTH; x++){
                if(board[x][y].getTileType()!=TileType.NONE && type == WriteType.ALL){
                    if(x==newX && y==newY){
                        if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BOX){
                            String cell = "1, 0, 0, 0, 0|";
                            data = data + cell;    
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.CYLINDER){
                            String cell = "0, 1, 0, 0, 0|";
                            data = data + cell;    
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL){
                            String cell = "0, 0, 1, 0, 0|";
                            data = data + cell;    
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_VERTICAL){
                            String cell = "0, 0, 0, 1, 0|";
                            data = data + cell;    
                        }else{
                            String cell = "0, 0, 0, 0, 1|";
                            data = data + cell;    
                        }                        
                    }else{
                            String cell = "0, 0, 0, 0, 1|";
                            data = data + cell;                         
                    }

                }else if(board[x][y].getTileType()!=TileType.NONE && type == WriteType.ALL_SIMPLE){
                    if(x==newX && y==newY){
                        if(board[x][y].hasPiece()){
                            data = data + " 1,";
                        }else{
                            data = data + " 0,";
                        }
                    }else{
                        data = data + " 0,";
                    }
                }else if(board[x][y].getTileType()!=TileType.NONE && board[x][y].getTileType()!=TileType.BLOCK && type == WriteType.BUILDING){
                    if(x==newX && y==newY){
                        if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BOX){
                            String cell = "1, 0, 0, 0, 0|";
                            data = data + cell;    
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.CYLINDER){
                            String cell = "0, 1, 0, 0, 0|";
                            data = data + cell;
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_HORIZONTAL){
                            String cell = "0, 0, 1, 0, 0|";
                            data = data + cell;
                        }else if(board[x][y].hasPiece() && board[x][y].getPiece().getType()==PieceType.BRIDGE_VERTICAL){
                            String cell = "0, 0, 0, 1, 0|";
                            data = data + cell;
                        }else{
                            String cell = "0, 0, 0, 0, 1|";
                            data = data + cell;
                        }
                    }else{
                            String cell = "0, 0, 0, 0, 1|";
                            data = data + cell;                        
                    }
                }else if(board[x][y].getTileType()!=TileType.NONE && board[x][y].getTileType()!=TileType.BLOCK && type == WriteType.BUILDING_SIMPLE){
                    if(x==newX && y==newY){
                        if(board[x][y].hasPiece()){
                            data = data + " 1,";
                        }else{
                            data = data + " 0,";
                        }
                    }else{
                        data = data + " 0,";
                    }
                }
            }
        }
        
        data = data.substring(0, data.length()-1);
        System.out.println(data);
//        data = data + "]\n";
        
        try(
                FileWriter fw = new FileWriter(pathName, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
        {
            
            out.println(data);
            //more code
//            out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }        
    }

    void completeWriteMovesToFile(Tile[][] board, String pathName, WriteType type, int seqLength, int currSeqNum) {
        for (int i=currSeqNum; i < seqLength; i++){
            writeMoveToFile(board, pathName, type, -1, -1);
        }         
    }
}
