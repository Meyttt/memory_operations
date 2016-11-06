package XmlReader;
import Logic.*;

import Logic.Arm;
import Memories.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;

/**
 * Created by master on 31.10.2016.
 */
public class AlgorithmReader {
    public Map<String,Arm> readAlgorithm() throws IOException, SAXException, ParserConfigurationException {
        Map<String,Arm> arms = new HashMap<>();
        Map<String, Memory> memoriesMap = new HashMap<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(new File("templateStrorage.xml"));

        NodeList armsMemory = document.getElementsByTagName("memory");
        for(int j=0;j<armsMemory.getLength();j++){
            NamedNodeMap atrs=armsMemory.item(j).getAttributes();
            switch (atrs.getNamedItem("type").getNodeValue()){
                case("Register"):{
                    String name = atrs.getNamedItem("name").getNodeValue();
                    memoriesMap.put(name,new Register(name,null));
                    break;
                }
                case("Counter"):{
                    String name = atrs.getNamedItem("name").getNodeValue();
                    memoriesMap.put(name,new Counter(name,null));
                    break;
                }
                case("Wagon"):{
                    String name = atrs.getNamedItem("leftName").getNodeValue()+"*"+atrs.getNamedItem("rightName").getNodeValue();
                    memoriesMap.put(name,new Wagon(atrs.getNamedItem("leftName").getNodeValue(),atrs.getNamedItem("rightName").getNodeValue(),null));
                    break;
                }
                case("Table"):{
                    String name = atrs.getNamedItem("name").getNodeValue();
                    ArrayList<String> colnames = new ArrayList<>();
                    NodeList tableChildren = armsMemory.item(j).getChildNodes();
                    for(int k=0;k<tableChildren.getLength();k++){
                        if(tableChildren.item(k).hasChildNodes()){
                            NodeList columnNames = tableChildren.item(k).getChildNodes();
                            for(int m=0;m<columnNames.getLength();m++){
                                if(columnNames.item(m).hasChildNodes()){
                                    colnames.add(columnNames.item(m).getFirstChild().getNodeValue());
                                }
                            }
                        }
                    }
                    memoriesMap.put(name,new Table(name,null,colnames));
                    break;
                }
            }
        }
        //ПРОВЕРКА!
//        Set<String> keys = memoriesMap.keySet();
//        for(String key:keys){
//            System.out.println(key+"-"+memoriesMap.get(key).getClass().getSimpleName());
//        }


        NodeList nodeList = document.getElementsByTagName("arm");
        for(int i=0;i<nodeList.getLength();i++){
            Node armNode= nodeList.item(i);
            String nodeNumber = armNode.getAttributes().getNamedItem("begin").getNodeValue();
            NodeList edges = armNode.getChildNodes();
            for(int j=0;j<edges.getLength();j++){
                Node edge=edges.item(j);
                if(edge.hasChildNodes()){
                    String endNumber=edge.getAttributes().getNamedItem("end").getNodeValue();
                    NodeList allLines=edge.getChildNodes();
                        for(int k=0;k<allLines.getLength();k++){
                            Logic.Condition condition;
                            Node line = allLines.item(k);
                            ArrayList<Statement> statements = new ArrayList<>();
                            switch(line.getNodeName()){
                                case("predicate"):
                                    NodeList lineNodes = line.getChildNodes();
                                    for(int l=0;l<lineNodes.getLength();l++){
                                        if(lineNodes.item(l).hasChildNodes()) {
                                            switch (lineNodes.item(l).getNodeName()){
                                                case ("alphabet"):{
                                                    condition = new Logic.Condition(null,new Alphabet(lineNodes.item(l).getFirstChild().getNodeValue()),endNumber);
                                                    break;
                                                }
                                                case("predicateText"):{
                                                    condition = new Logic.Condition(lineNodes.item(l).getFirstChild().getNodeValue(),null,endNumber);
                                                    break;
                                                }
                                                case("const"):{
                                                    //TODO:Реализуй меня полностью, но сначала в Condition
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                break;
                                case("operation"):{
                                    NamedNodeMap atributes=line.getAttributes();

                                }
                            }

                           // System.out.println(line.getNodeName());
                            if(line.hasChildNodes()){

                            }
                        }

                }
            }
        }

    return arms;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        AlgorithmReader algorithmReader= new AlgorithmReader();
        algorithmReader.readAlgorithm();
    }

}
