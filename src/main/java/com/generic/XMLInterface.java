package com.generic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("rawtypes")

public class XMLInterface
{
  public static Map<String, String> envDictionaryMap = new HashMap<String, String>();
  public static Map<String, String> orDictionaryMap  = new HashMap<String, String>();
 
  public static String   sobjBalanceText;
  public static String   sobjSpinBtn;
  public static String   sobjNoOfLines;
  public static String   sobjLinesPlusBtn;
  public static String   sobjLinesMinusBtn;
  public static String   sobjWinText; 
  public static String   sobjTotalStake;
  public static String   sobjNoofStake;
  public static String   sobjStakePlusBtn;
  public static String   sobjStakeMinusBtn;
  public static String   sadminGameLink;
  public static String   spayoutLines;
  public static String   sappId;
  public static String   sobjPlayForRealBtn;
  public static String   sCollectBtn;
  public static String   sgameTitle;
  public static String   sgameLabels;
  public static String   sSoundOn;
  public static String   sSoundOff;
  public static String   sQuality;
  public static String   sOptions;
  public static String 	 sHelp;
  public static String 	 sInsuffBalance;
  public static String	 sInsuffBalanceOKBtn;
  public static String	 sBonusBar;
 

  public String getOrVariables(String sobjectName, String childName)
  {

    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File("C:\\SQS_Automation_Trinity_Framework\\OR\\OR_MB.xml");
    //String sGameName=this.getTestCaseID();
   // System.out.println("sgamename Name --"+sGameName);
    try
    {
      Document document = (Document) builder.build(xmlFile);
      Element rootNode = document.getRootElement();
       sobjectName = sobjectName.replaceAll(" ","");
      List list = rootNode.getChildren(sobjectName);

      for (int icounter = 0; icounter < list.size();)
      {
        Element node = (Element) list.get(icounter);
        //return node.getChildText(sobjectName);
        return node.getChildText(childName);
      }

    }
    catch (IOException io)
    {
      System.out.println(io.getMessage());
    }
    catch (JDOMException jdomex)
    {
      System.out.println(jdomex.getMessage());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "";
  }

 
  public static void populateEnvironmentDictionary(String sfileName) throws Exception
  {
    try
    {
      envDictionaryMap.clear();
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();

      org.w3c.dom.Document doc = db.parse(sfileName);

      Node n = doc.getFirstChild();
      NodeList nl = n.getChildNodes();

      Node an;

      for (int icounter = 0; icounter < nl.getLength(); icounter++)
      {
        an = nl.item(icounter);
        envDictionaryMap.put(an.getNodeName(), an.getTextContent());
      }
    }
    catch (Exception e)
    {
    //  Logging.logEntry(e.getMessage());
    }
  }

  
  public static String getEnvKeyValue(String skeyName)
  {
    try
    {
      String svalue;
      svalue = envDictionaryMap.get(skeyName);
      return svalue;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "";
    }
  }

  
  public static void populateObjectRepository(String sfileName)
  {
    try
    {
      orDictionaryMap.clear();
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();

      org.w3c.dom.Document doc = db.parse(sfileName);

      Node n = doc.getFirstChild();
      NodeList nl = n.getChildNodes();

      Node an;

      for (int icounter = 0; icounter < nl.getLength(); icounter++)
      {
        an = nl.item(icounter);
        orDictionaryMap.put(an.getNodeName(), an.getTextContent());

      } 
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

 
  public static String getObjRepositoryKeyValue(String skeyName)
  {
    try
    {
      String svalue;
      svalue = orDictionaryMap.get(skeyName);
      return svalue;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "";
    }
  }
  
   
 /* public void getObjectId() throws Exception
  {
    try
    {
      XMLInterface objXML = new XMLInterface();
      sobjPlayForRealBtn = objXML.getOrVariables("objPlayForRealBtn");
      sobjBalanceText = objXML.getOrVariables("objBalanceText");
      sobjSpinBtn = objXML.getOrVariables("objSpinBtn");
      sobjNoOfLines = objXML.getOrVariables("objNoOfLines");
      sobjLinesPlusBtn = objXML.getOrVariables("objLinesPlusBtn");
      sobjLinesMinusBtn = objXML.getOrVariables("objLinesMinusBtn");
      sobjWinText = objXML.getOrVariables("objWinText");
      sobjTotalStake = objXML.getOrVariables("objTotalStake");
      sobjNoofStake = objXML.getOrVariables("objNoofStake");
      sobjStakePlusBtn = objXML.getOrVariables("objStakePlusBtn");
      sobjStakeMinusBtn = objXML.getOrVariables("objStakeMinusBtn");
      sappId = objXML.getOrVariables("AppId");
      sCollectBtn=objXML.getOrVariables("collect");
      sgameTitle=objXML.getOrVariables("GameTitle");
      sgameLabels=objXML.getOrVariables("GameLabel");
      sSoundOn=objXML.getOrVariables("SoundOn");
      sSoundOff=objXML.getOrVariables("SoundOff");
      sQuality=objXML.getOrVariables("Quality");
      sOptions=objXML.getOrVariables("Options");
      sHelp=objXML.getOrVariables("Help");
      sInsuffBalance=objXML.getOrVariables("InsufficientBal");
      sInsuffBalanceOKBtn=objXML.getOrVariables("InsBalOKBtn");
      sBonusBar=objXML.getOrVariables("BonusBar");
    }
    catch (Exception e)
    {
    //  Logging.logEntry("Fail - Unable to set Object id.");
    //  Logging.logEntry("Fail - " + e.getMessage());
    }*/
  
}