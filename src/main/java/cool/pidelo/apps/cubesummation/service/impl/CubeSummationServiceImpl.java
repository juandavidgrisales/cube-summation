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
            for (TCase tCase : tCases) {
                
                tCase = new TCase();
                tCase.setConditions(b.readLine().split(" "));

                if(tCase.isCubeSizeValid() && tCase.isOperationsLengthValid())
                for (Operation operation : tCase.getOperations()) {
                    
                    operation = new Operation();
                    operation.setValues(b.readLine().split(" "));
                    
                    switch (operation.getOperationType()) {
                        case UPDATE:
                            if(tCase.areDimensionsValid(operation) && tCase.isUpdateValueValid(operation)) {
                                
                                if (tCase.existXOnCube(operation.getX1())){
                                    if (tCase.existXYOnCube(operation.getX1(),operation.getY1())){
                                        if (tCase.existXYZOnCube(operation.getX1(), operation.getY1(),operation.getZ1())){
                                            tCase.replaceValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                        }else {
                                            tCase.setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                        }
                                    } else {
                                        tCase.addYtoCube(operation.getX1(), operation.getY1());
                                        tCase.setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                    }
                                } else {
                                    tCase.addXtoCube(operation.getX1());
                                    tCase.addYtoCube(operation.getX1(), operation.getY1());
                                    tCase.setValueToCube(operation.getX1(), operation.getY1(), operation.getZ1(), operation.getW());
                                }
                                
                            }
                            break;
                            
                        case QUERY:
                            long  sum = 0;
                            if (tCase.areDimensionsValid(operation)) {

                                for (Map.Entry<Byte, Map<Byte,Map<Byte, Long>>> xElement : tCase.getCube().entrySet()) {
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
                                tCase.plusToResult(sum);
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
