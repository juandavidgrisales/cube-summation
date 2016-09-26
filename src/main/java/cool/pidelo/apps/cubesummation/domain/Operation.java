/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation.domain;

import cool.pidelo.apps.cubesummation.enumerations.OperationType;

/**
 *This class is the abstraction for Operation on cube summation
 * @author juan.grisales
 */
public class Operation {
    
    private OperationType operationType;
    private byte x1;
    private byte y1;
    private byte z1;
    private byte x2;
    private byte y2;
    private byte z2;
    private long  w;
    
    public Operation () {
    }
    
    /**
     *This constructor is used when you need create an Update Operation
     * 
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public Operation (byte x, byte y, byte z, int w) {
        this.operationType = OperationType.UPDATE;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
        this.w  = w;
    }
    
    /**
     *This constructor is used when you need create an Query Operation
     * 
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     */
    public Operation (byte x1, byte y1, byte z1, byte x2, byte y2, byte z2) {
        this.operationType = OperationType.QUERY;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    /**
     * @return the operationType
     */
    public OperationType getOperationType() {
        return operationType;
    }

    /**
     * @param operationType the operationType to set
     */
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    /**
     * @return the x1
     */
    public byte getX1() {
        return x1;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(byte x1) {
        this.x1 = x1;
    }

    /**
     * @return the y1
     */
    public byte getY1() {
        return y1;
    }

    /**
     * @param y1 the y1 to set
     */
    public void setY1(byte y1) {
        this.y1 = y1;
    }

    /**
     * @return the z1
     */
    public byte getZ1() {
        return z1;
    }

    /**
     * @param z1 the z1 to set
     */
    public void setZ1(byte z1) {
        this.z1 = z1;
    }

    /**
     * @return the x2
     */
    public byte getX2() {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(byte x2) {
        this.x2 = x2;
    }

    /**
     * @return the y2
     */
    public byte getY2() {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(byte y2) {
        this.y2 = y2;
    }

    /**
     * @return the z2
     */
    public byte getZ2() {
        return z2;
    }

    /**
     * @param z2 the z2 to set
     */
    public void setZ2(byte z2) {
        this.z2 = z2;
    }

    /**
     * @return the w
     */
    public long getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(int w) {
        this.w = w;
    }

    public void setValues(String[] operation) {
        if (operation[0].equals("UPDATE")) {
            this.operationType = OperationType.UPDATE;
            this.x1 = Byte.parseByte(operation[1]);
            this.y1 = Byte.parseByte(operation[2]);
            this.z1 = Byte.parseByte(operation[3]);
            this.w = Integer.parseInt(operation[4]);
        } else {
            this.operationType = OperationType.QUERY;
            this.x1 = Byte.parseByte(operation[1]);
            this.y1 = Byte.parseByte(operation[2]);
            this.z1 = Byte.parseByte(operation[3]);
            this.x2 = Byte.parseByte(operation[4]);
            this.y2 = Byte.parseByte(operation[5]);
            this.z2 = Byte.parseByte(operation[6]);
        }
    }
    
}
