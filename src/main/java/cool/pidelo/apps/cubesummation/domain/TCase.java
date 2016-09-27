/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author juan.grisales
 */
public class TCase {
    
    private Operation[] operations;
    private Map<Byte, Map<Byte,Map<Byte, Long>>> cube = new TreeMap<>();
    private Byte dimension;
    private List<Long> queryResults;

    public TCase(final short mOperations) {
        this.queryResults = new ArrayList<>();
        operations = new Operation[mOperations];
    }
    
    public TCase() {
        this.queryResults = new ArrayList<>();
    }
    
    /**
     *
     * @return true if the cube dimension are valid
     */
    public boolean isCubeSizeValid() {
        return getDimension() > 0 && getDimension() <= 100;
    }
    
    /**
     *set conditions to tCase
     * @param caseConditions
     */
    public void setConditions(String[] caseConditions) {
        setOperations(new Operation[Short.parseShort(caseConditions[1])]);
        setDimension(Byte.parseByte(caseConditions[0]));
    }

    /**
     *
     * @return true if the value of operations is valid
     */
    public boolean isOperationsLengthValid() {
        return getOperations().length > 0 && getOperations().length <= 1000;
    }

    /**
     *
     * @param operation
     * @return true if is valid the dimension on update operation
     */
    public boolean areDimensionsValid (Operation operation) {
        return  0 < operation.getX1() && operation.getX1() <= getDimension() && 
                    0 < operation.getY1() && operation.getY1() <= getDimension() && 
                        0 < operation.getZ1() && operation.getZ1() <= getDimension();
                
    }
    
    /**
     *
     * @param operation
     * @return true if is valid the update value
     */
    public boolean isUpdateValueValid(Operation operation) {
        return  -1000000000 <= operation.getW() && operation.getW() <= 1000000000;
                
    }

    /**
     * @return the operations
     */
    public Operation[] getOperations() {
        return operations;
    }

    /**
     * @param operations the operations to set
     */
    public void setOperations(Operation[] operations) {
        this.operations = operations;
    }

    /**
     * @return the cube
     */
    public Map<Byte, Map<Byte,Map<Byte, Long>>> getCube() {
        if (cube == null)
            return new TreeMap<>();
        return cube;
    }

    /**
     * @param cube the cube to set
     */
    public void setCube(Map cube) {
        this.cube = cube;
    }

    
    
    void setDimension(Byte dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the dimension
     */
    public Byte getDimension() {
        return dimension;
    }
    
    /**
     *
     * @return the result
     */
    public List<Long> getQueryResults() {
        return this.queryResults;
    }

    /**
     *
     * @param xCoord
     * @return the x square on cube
     */
    public Map getCubeInX(Byte xCoord) {
        return cube.get(xCoord);
    }
    
    /**
     *
     * @param xCoord
     * @return true if x square exist on cube
     */
    public boolean existXOnCube (Byte xCoord) {
        return cube.containsKey(xCoord);
    }  
    
    /**
     *
     * @param xCoord
     * @param yCoord
     * @return get line on x,y
     */
    public Map getCubeInXY (Byte xCoord, Byte yCoord) {
        return (Map) (getCubeInX(xCoord)).get(yCoord);
    }
    
    /**
     *
     * @param xCoord
     * @param yCoord
     * @return true if the line exist on x,y
     */
    public boolean existXYOnCube (Byte xCoord, Byte yCoord) {
        return (getCubeInX(xCoord)).containsKey(yCoord);
    }
    
    /**
     *
     * @param xCoord
     * @param yCoord
     * @param zCoord
     * @return get scalar located in x,y,z
     */
    public Integer getCubeInXYZ (Byte xCoord, Byte yCoord, Byte zCoord) {
        return (Integer) (getCubeInXY(xCoord, yCoord)).get(zCoord);
    }
    
    /**
     *
     * @param xCoord
     * @param yCoord
     * @param zCoord
     * @return true if the scalar exist on x,y,z
     */
    public boolean existXYZOnCube (Byte xCoord, Byte yCoord, Byte zCoord) {
        return (getCubeInXY(xCoord, yCoord)).containsKey(zCoord);
    }
    
    /**
     * add x square on cube
     * @param xCoord
     */
    public void addXtoCube(Byte xCoord) {
        cube.put(xCoord, new TreeMap<>());
    }
    
    /**
     * add y line on cube
     * @param xCoord
     * @param yCoord
     */
    public void addYtoCube(Byte xCoord, Byte yCoord) {
        getCubeInX(xCoord).put(yCoord, new TreeMap<>());
    }
    
    /**
     * add z scalar on cube
     * @param xCoord
     * @param yCoord
     * @param zCoord
     * @param wValue
     */
    public void setValueToCube(Byte xCoord, Byte yCoord, Byte zCoord, Long wValue) {
        getCubeInXY(xCoord,yCoord).put(zCoord, wValue);
    }

    /**
     * replace scalar on cube
     * @param xCoord
     * @param yCoord
     * @param zCoord
     * @param wValue
     */
    public void replaceValueToCube(Byte xCoord, Byte yCoord, Byte zCoord, Long wValue) {
        getCubeInXY(xCoord, yCoord).replace(zCoord, wValue);
    }

    /**
     * increment result with value
     * @param sum
     */
}
