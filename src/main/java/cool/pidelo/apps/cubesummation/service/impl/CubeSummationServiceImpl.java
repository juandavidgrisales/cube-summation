/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation.service.impl;

import cool.pidelo.apps.cubesummation.domain.Operation;
import cool.pidelo.apps.cubesummation.domain.TCase;
import cool.pidelo.apps.cubesummation.service.CubeSummationService;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan.grisales
 */
@Service()
public class CubeSummationServiceImpl implements CubeSummationService {

    @Override
    public TCase[] testCases(String input) throws FileNotFoundException, IOException {
        
        BufferedReader b = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        List<Long> response = new ArrayList<>();
        
        
        //byte tCases = ((Byte)Byte.parseByte(b.readLine()));
        TCase[] tCases = new TCase[((Byte)Byte.parseByte(b.readLine()))];
        
        if (isValidTCases(tCases.length)) {
            for (int i = 0; i < tCases.length; i++) {
                
                tCases[i] = new TCase();
                tCases[i].setConditions(b.readLine().split(" "));

                if(tCases[i].isCubeSizeValid() && tCases[i].isOperationsLengthValid())
                for (int j = 0; j < tCases[i].getOperations().length; j++) {
                    
                    tCases[i].getOperations()[j] = new Operation();
                    Operation operation = tCases[i].getOperations()[j];
                    operation.setValues(b.readLine().split(" "));
                    
                    switch (operation.getOperationType()) {
                        case UPDATE:
                            if(tCases[i].areDimensionsValid(operation) && tCases[i].isUpdateValueValid(operation)) {
                                
                                if (tCases[i].existXOnCube(operation.getX1())){
                                    if (tCases[i].existXYOnCube(operation.getX1(),operation.getY1())){
                                        if (tCases[i].existXYZOnCube(operation.getX1(), operation.getY1(),operation.getZ1())){
                                            tCases[i].replaceValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                        }else {
                                            tCases[i].setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                        }
                                    } else {
                                        tCases[i].addYtoCube(operation.getX1(), operation.getY1());
                                        tCases[i].setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                    }
                                } else {
                                    tCases[i].addXtoCube(operation.getX1());
                                    tCases[i].addYtoCube(operation.getX1(), operation.getY1());
                                    tCases[i].setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                }
                                
                            }
                            break;
                            
                        case QUERY:
                            long  sum = 0;
                            if (tCases[i].areDimensionsValid(operation)) {

                                for (Map.Entry<Byte, Map<Byte,Map<Byte, Long>>> xElement : tCases[i].getCube().entrySet()) {
                                    if (xElement.getKey().byteValue() >= operation.getX1() && xElement.getKey().byteValue() <= operation.getX2()){
                                        for (Map.Entry<Byte, Map<Byte, Long>> yElement : xElement.getValue().entrySet()) {
                                            if (yElement.getKey().byteValue() >= operation.getY1() && yElement.getKey().byteValue() <= operation.getY2()){
                                                for (Map.Entry<Byte, Long> zElement:  yElement.getValue().entrySet()) {
                                                    if (zElement.getKey().byteValue() >= operation.getZ1() && zElement.getKey().byteValue() <= operation.getZ2()) {
                                                        sum += zElement.getValue();
                                                    }
                                                }
                                            }
                                        }
                                    }                        
                                }
                                tCases[i].getQueryResults().add(sum);
                            }
                            break;
                            
                        default:
                            break;
                    }
                }
            }

            b.close();
        }
        
        return tCases;
    }

    
    public boolean isValidTCases (int dimension) {
        return (dimension > 0 && dimension <= 50);
    }
    
}
