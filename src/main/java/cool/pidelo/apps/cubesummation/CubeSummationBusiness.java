/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author juan.grisales
 */
public class CubeSummationBusiness {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader b = new BufferedReader(new FileReader("test1.txt"));
        
        
        byte tCases = ((Byte)Byte.parseByte(b.readLine()));
        
        if (tCases > 0 && tCases <= 50)
        while (tCases > 0) {
            
            
            String[] caseConditions = b.readLine().split(" ");
            byte nCubeSize = Byte.parseByte(caseConditions[0]);
            short mOperations = Short.parseShort(caseConditions[1]);
            
            //Add x dimension
            Map<Byte, Map<Byte,Map<Byte, Long>>> xDimension = new TreeMap<>();
            
            if (nCubeSize > 0 && nCubeSize <= 100 && mOperations > 0 && mOperations <= 1000)
            while (mOperations > 0) {
                String[] operation = b.readLine().split(" ");
                if (operation[0].equals("UPDATE")) {
                    
                    byte xCoord = Byte.parseByte(operation[1]);
                    byte yCoord = Byte.parseByte(operation[2]);
                    byte zCoord = Byte.parseByte(operation[3]);
                    long  wValue = Integer.parseInt(operation[4]);
                    
                    if( 0 < xCoord && xCoord <= nCubeSize && 
                            0 < yCoord && yCoord <= nCubeSize && 
                                0 < zCoord && zCoord <= nCubeSize &&
                                    -1000000000 <= wValue && wValue <= 1000000000) {
                        if (xDimension.containsKey(xCoord)){
                            if (((Map)xDimension.get(xCoord)).containsKey(yCoord)){
                                if (((Map)((Map)xDimension.get(xCoord)).get(yCoord)).containsKey(zCoord)){
                                    ((Map)((Map)xDimension.get(xCoord)).get(yCoord)).replace(zCoord, wValue);
                                }else {
                                    ((Map)((Map)xDimension.get(xCoord)).get(yCoord)).put(zCoord, wValue);
                                }
                            } else {
                                ((Map)xDimension.get(xCoord)).put(yCoord, new TreeMap<>());
                                ((Map)((Map)xDimension.get(xCoord)).get(yCoord)).put(zCoord, wValue);
                            }
                        } else {
                            xDimension.put(xCoord, new TreeMap<>());
                            ((Map)xDimension.get(xCoord)).put(yCoord, new TreeMap<>());
                            ((Map)((Map)xDimension.get(xCoord)).get(yCoord)).put(zCoord, wValue);
                        }
                    }
                } else {
                    long  sum = 0;
                    byte x1Coord = Byte.parseByte(operation[1]);
                    byte y1Coord = Byte.parseByte(operation[2]);
                    byte z1Coord = Byte.parseByte(operation[3]);
                    byte x2Coord = Byte.parseByte(operation[4]);
                    byte y2Coord = Byte.parseByte(operation[5]);
                    byte z2Coord = Byte.parseByte(operation[6]);
                    
                    
                    if (0 < x1Coord && x1Coord <= x2Coord && x2Coord <= nCubeSize && 
                            0 < y1Coord && y1Coord <= y2Coord && y2Coord <= nCubeSize && 
                                0 < z1Coord && z1Coord <= z2Coord && z2Coord <= nCubeSize) {
                        
                        for (Map.Entry<Byte, Map<Byte,Map<Byte, Long>>> xElement : xDimension.entrySet()) {
                            if (xElement.getKey().byteValue() >= x1Coord && xElement.getKey().byteValue() <= x2Coord){
                                for (Map.Entry<Byte, Map<Byte, Long>> yElement : xElement.getValue().entrySet()) {
                                    if (yElement.getKey().byteValue() >= y1Coord && yElement.getKey().byteValue() <= y2Coord){
                                        for (Map.Entry<Byte, Long> zElement:  yElement.getValue().entrySet()) {
                                            if (zElement.getKey().byteValue() >= z1Coord && zElement.getKey().byteValue() <= z2Coord) {
                                                sum += zElement.getValue();
                                            }
                                        }
                                    }
                                }
                            }                        
                        }
                        System.out.println(sum);
                    }
                }
                mOperations--;
            }
            tCases--;
        }
        
        b.close();
    }
    
}
