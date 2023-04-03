
package com.mycompany.algobyservices;


import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.algobyservices.AlgoCreatingClustersAndAbstractTraces;
import static com.mycompany.algobyservices.CoreAlgo3CreatingTestCasesFromClusters.creatingTestCasesFromClusters;
import com.mycompany.fileReader.Split.Regex;
import com.mycompany.fileReader.Split.Trace;
import com.mycompany.fileReader.main.Main;
import com.mycompany.fileReader.objects.EventSplit;
import com.mycompany.models.AbstractTraces;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import com.mycompany.models.TestCase;
import java.io.IOException;
import java.util.HashMap;
/**
 *
 * @author jarod
 */
public class CoreAlgorithmPart1ReadingResultsAndAdaptingTraces {
    public static void mainAlgo() throws FileNotFoundException, IOException, InterruptedException{
        int total=0;
        int nbTestCreated=0;
        ArrayList<File> listOfFiles=new ArrayList<File>();
        final long startTime = System.currentTimeMillis();
        //Path To Your Regex
        Regex reg = new Regex("C:\\Users\\jarod\\Documents\\tests\\logsprets\\regexLogMieux.txt");
        //Path To your logs
        listOfFiles.add(new File("C:\\Users\\jarod\\Documents\\tests\\logsDernier\\logs\\logs.txt"));
        ArrayList<Trace> allTraces= new ArrayList<Trace>();
        for(File soloFile : listOfFiles){
            Trace a = Main.mainFile(soloFile, reg );
            allTraces.add(a);
        }

        ArrayList<Conversation> arrayListFromTracesToConv=new ArrayList<Conversation>();
        for(Trace trace : allTraces){
            Conversation convToAddToTheListOfAllConv=new Conversation();
            for(EventSplit evSplit: trace.getSeq()){
                convToAddToTheListOfAllConv.addEvent(new Event(evSplit));
            };
            arrayListFromTracesToConv.add(convToAddToTheListOfAllConv);
        }

        ArrayList<String> PCO = new ArrayList<String>();
        ArrayList<String> PO = new ArrayList<String>();
        PCO.add("8080");
        HashMap<String, ArrayList<Cluster>> hashMapOfEveryPCOThatCanBeTestedWithTheirRespectiveAbstractTraces = AlgoCreatingClustersAndAbstractTraces.AlgoClusterisationAndCreatingAbstractTracesWithUserRequests(arrayListFromTracesToConv, PCO);
        for(String str : PCO){
                            
            ArrayList<TestCase> allTestCasesCreated=new ArrayList<TestCase>();
            ArrayList<Cluster> AllClustersChoosedForTheSpecificComponent=hashMapOfEveryPCOThatCanBeTestedWithTheirRespectiveAbstractTraces.get(str);
            
            ArrayList<TestCase> arrayTC=creatingTestCasesFromClusters(AllClustersChoosedForTheSpecificComponent, str);
            for(TestCase tc : arrayTC){
                
                allTestCasesCreated.add(tc);
            }
            
            nbTestCreated+=CreatingTestCaseFile.createFile(allTestCasesCreated, str);
        }
        final long endTime = System.currentTimeMillis();
        total+=(endTime-startTime);
        System.out.println(total);
        System.out.println(nbTestCreated);
         
        return;
    }
}
