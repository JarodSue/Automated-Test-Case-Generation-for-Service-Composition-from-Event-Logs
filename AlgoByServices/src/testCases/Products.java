

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
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
public class Products extends TestNGCitrusSpringSupport{
     private static ClientAndServer mockServer;


    @Test
    @CitrusTest
    public void testAlgo1() throws FileNotFoundException{
            startServer("{Host=127.0.0.1 Dest=172.17.190.226 method=GET token=Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWQiOjUsImV4cCI6MTY0Nzk1NjM3MiwiaWF0IjoxNjQ3ODY5OTcyfQ.wGykzr30FTIQ44Ink5Iy1diYhcTUKRbasEa6Pt6hwAjcPYgSFTSCLX9S1TkLeePOKsHRZbceOAv8qiUwL6gZyw, path=/Products, Anonymous=true, response status=200 }","/Products","200");
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
        
       

        @BeforeClass
        public static void startServer(String bodyOfReturn, String URL, String code) {
            mockServer = startClientAndServer(1080);
                mockServer.when(
                    request()
                      .withMethod("get")
                      .withPath(URL))
                        .respond(
                          response()
                            .withStatusCode(Integer.parseInt(code))
                            .withBody(bodyOfReturn)
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           

        }

        @AfterClass 
        public void stopServer() { 
            mockServer.stop();
        }
    } 
