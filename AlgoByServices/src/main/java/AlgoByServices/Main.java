/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AlgoByServices;

import java.io.IOException;
import com.mycompany.algobyservices.CoreAlgorithmPart1ReadingResultsAndAdaptingTraces;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**
 *
 * @author jarod
 */
public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException{
        //File directoryPath = new File("C:\\Users\\jarod\\Documents\\sourceCode\\Dernier\\loanapp\\all");
        File directoryPath = new File(args[0]);
      //List of all files and directories
      String contents[] = directoryPath.list();
      ArrayList<String> logFiles=new ArrayList<>();
      for(int i=0; i<contents.length; i++) {
         logFiles.add(directoryPath.getAbsolutePath()+"\\"+contents[i]);
      }

        CoreAlgorithmPart1ReadingResultsAndAdaptingTraces.mainAlgo(logFiles,args[1],args[2]);
    }
}
