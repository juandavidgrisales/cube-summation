/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation.service;

import cool.pidelo.apps.cubesummation.domain.TCase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author juan.grisales
 */
public interface CubeSummationService  {

    TCase[] testCases(String input) throws FileNotFoundException, IOException;
    
}
