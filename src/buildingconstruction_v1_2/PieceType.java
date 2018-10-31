/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildingconstruction_v1_2;

/**
 *
 * @author adhithadias27
 */
public enum PieceType {
    BOX(1), CYLINDER(2), BRIDGE_VERTICAL(3), BRIDGE_HORIZONTAL(4);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }     
}
