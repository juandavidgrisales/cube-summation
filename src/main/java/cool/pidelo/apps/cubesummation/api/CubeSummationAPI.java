/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cool.pidelo.apps.cubesummation.api;

import cool.pidelo.apps.cubesummation.domain.TCase;
import cool.pidelo.apps.cubesummation.service.CubeSummationService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juan.grisales
 */
@RestController
@RequestMapping("cube-summation")
public class CubeSummationAPI {
    
    @Autowired
    private CubeSummationService cubeSummationService;
        
    /**
     *
     * @param input with a plain text with the input in order to calculate its results
     * @return results for each tCase
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Long> testCases (@RequestBody String input) throws IOException {
        
        //I could persist this information with dao layer in order to generate a trace
        TCase[] tCases = cubeSummationService.testCases(input);
        List<Long> results = new ArrayList<>();
        for (TCase tCase : tCases) {
            results.add(tCase.getResult());
        }
        return results;
    }
    
}
