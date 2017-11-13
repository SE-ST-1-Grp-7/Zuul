/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Highscore;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(highScore, comparator);
    }

    /**
     * Method to return our highScore as an string with the toString method.
     *
     * @return string text
     */
    public String toString() {
        String text = "";
        for (Score score : highScore) {
            text += score.getName() + score.getScore() + "\n";
        }
        return text;
    }

    /**
     * This method makes our highScore into an XML file.
     */
    public void createXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element highscore = doc.createElement("Highscore");
            doc.appendChild(highscore);

            for (Score score : highScore) {
                Element scoreNode = doc.createElement("score");
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


    public void printHighscore() {
        try {

            File fXmlFile = new File("highscore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("The " + doc.getDocumentElement().getNodeName() + " list for World of SDU");

            NodeList nList = doc.getElementsByTagName("Highscore");
            Node child = nList.item(0);
            NodeList nL = child.getChildNodes();
            System.out.println("----------------------------");
            System.out.printf("%-5s %-14s %-12s", "NO.", "NAME", "SCORE");
            System.out.println("");
            int i = 1;
            for (int temp = 0; temp < nL.getLength(); temp++) {

                Node nNode = nL.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.printf("%-1d%-4s %-14s %-12s\n", i, ".", eElement.getAttribute("name"), eElement.getAttribute("score"));
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
