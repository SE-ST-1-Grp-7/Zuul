/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author niclasjohansen
 */
public class Highscore {

    /**
     * An ArrayList to store the players highScore.
     */
    ArrayList<Score> highScore = new ArrayList();

    /**
     * An add method to add a name and a score to the ArrayList.
     *
     * @param name
     * @param score
     */
    public void add(String name, int score) {
        highScore.add(new Score(name, score));
        this.sort();
    }

    /**
     * A method to sort the highScore so the one with the highest score is
     * first.
     */
    private void sort() {
        Score comparator = new Score();
        Collections.sort(highScore, comparator);
    }

    /**
     * Method to return our highScore as an string with the toString method.
     *
     * @return string text
     */
    public String displayHighscore() {
        String text = "";
        int i = 1;
        for (Score score : highScore) {
            text += String.format("%-14d%-20.6s%-4d\n",i ,score.getName() ,score.getScore() );
            i++;
        }
        return text;
    }

    /**
     * This method creates a XML file with our highScores and place it in the
     * root dir.
     */
    public void createXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element highscore = doc.createElement("Highscore");
            doc.appendChild(highscore);

            for (Score score : highScore) {
                Element scoreNode = doc.createElement("person");
                scoreNode.setAttribute("name", score.getName());
                scoreNode.setAttribute("score", Integer.toString(score.getScore()));
                highscore.appendChild(scoreNode);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(new File("highscore.xml"));
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method loads the XML file highscore.xml.
     */
    public void loadXML() {
        highScore.clear();

        try {
            File fXmlFile = new File("highscore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("person");

            for (int i = 0; i < list.getLength(); i++) {

                Node nNode = list.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    this.add(eElement.getAttribute("name"), Integer.parseInt(eElement.getAttribute("score")));
                }
            }
        } catch (Exception e) {

        }
    }

    public void saveHighscore(String playerName, int seconds) {
        loadXML();
        add(playerName, seconds);
        createXML();
        displayHighscore();

    }

    public void printHighscore() {

        System.out.println("The highscore list for World of SDU");
        System.out.println("----------------------------");
        System.out.println("NO.\tNAME\t SCORE");
        System.out.println(this.toString());
    }

}
