

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.ClusterGen.main.mainClusterGen;
import com.mycompany.fileReader.Split.Regex;
import com.mycompany.fileReader.Split.Trace;
import com.mycompany.mock.Mock;

import static com.mycompany.algobyservices.CoreAlgorithmPart1ReadingResultsAndAdaptingTraces.mainAlgo;
import com.mycompany.models.Event;
import com.mycompany.models.TestCase;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
public class TestReadFile extends TestNGCitrusSpringSupport{
    


    @Test
    @CitrusTest
    public void testAlgo1() throws FileNotFoundException{
        ArrayList<TestCase> tc= mainAlgo();
        for(TestCase test : tc){
            test.showTreeForShowing(0);
            System.out.println(test.getEventHere().getFrom());
        }
        for(TestCase test : tc){
            System.out.println(test.allRequestsAndWaitedResponses());
        }
        for(TestCase test : tc){
            ArrayList<Event> allRequestsAndWaitedResponses=test.allRequestsAndWaitedResponses();
        
            Mock.startServer(test.allRequestsAndWaitedResponses());
                Event ev = test.getEventHere();
                            HttpClient toClient = CitrusEndpoints
                                .http()
                                .client()
                                .requestUrl("http://localhost:1080/")
                                .build();
                            $(http()
                                .client(toClient)
                                .send()
                                .get("Products")
                                .message()
                                .accept(MediaType.TEXT_HTML_VALUE));
                            $(http()
                                .client(toClient)
                                .receive()
                                .response(HttpStatus.OK)
                                .message());
                            break;
                            
        }
    } 
    
}
