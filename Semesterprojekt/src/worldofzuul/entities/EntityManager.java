package worldofzuul.entities;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import worldofzuul.Furniture.Furniture;
import worldofzuul.People.Player;
import worldofzuul.People.Student;
import worldofzuul.items.Item;
import worldofzuul.mapAndRooms.RoomManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import worldofzuul.items.Adderall;
import worldofzuul.items.Assignment;
import worldofzuul.items.Coffee;
import worldofzuul.items.EnergyDrink;
import worldofzuul.items.Key;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class EntityManager {

    // containers for entities
    private ArrayList<Item> itemlist = new ArrayList<>();
    private ArrayList<Player> playerlist = new ArrayList<>();
    private ArrayList<Student> studentlist = new ArrayList<>();
    private ArrayList<Furniture> furniturelist = new ArrayList<>();
    private RoomManager rm = new RoomManager();

    // ENTITY MANAGMENT METHODS
    public void addPlayer(Player p) {
        playerlist.add(p);
    }

    public void removePlayer(Player p) {
        playerlist.remove(p);
    }

    public void addStudent(Student s) {
        studentlist.add(s);
    }

    public void removeStudent(Student s) {
        studentlist.remove(s);
    }

    public void addFurniture(Furniture f) {
        furniturelist.add(f);
    }

    public void removeFurniture(Furniture f) {
        furniturelist.remove(f);
    }

    public void addItem(Item i) {
        itemlist.add(i);
    }

    public void removeItem(Item i) {
        itemlist.remove(i);
    }

    // SETTERS & GETTERS
    public ArrayList<Player> getPlayer() {
        return playerlist;
    }

    public void setPlayer(ArrayList<Player> player) {
        this.playerlist = player;
    }

    public ArrayList<Student> getStudent() {
        return studentlist;
    }

    public void setStudent(ArrayList<Student> student) {
        this.studentlist = student;
    }

    public ArrayList<Furniture> getFurniture() {
        return furniturelist;
    }

    public void setFurniture(ArrayList<Furniture> furniture) {
        this.furniturelist = furniture;
    }

    public ArrayList<Item> getItem() {
        return itemlist;
    }

    public void setItem(ArrayList<Item> item) {
        this.itemlist = item;
    }

    public void saveGame() {
        saveItems();
        savePlayers();
        saveStudents();
        saveFurniture();
    }

//    public void loadGame() {
//        loadItems();
//        loadPlayers();
//        loadStudents();
//        loadFurniture();
//    }
    public void saveItems() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SaveItemsTest.txt")));
            for (Item item : itemlist) {
                fileWriter.append(item.getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getY()));
                fileWriter.append(",");
                fileWriter.append(item.getCurrentRoom().getName());
                fileWriter.append("\n");

                System.out.println("Saved items");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE ITEMS... please check the save directory in the code.");
        }
    }

    public void savePlayers() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SavePlayersTest.txt")));
            for (Player player : playerlist) {
                fileWriter.append(player.getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(player.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(player.getY()));
                fileWriter.append(",");
                fileWriter.append(player.getCurrentRoom().getName());
                fileWriter.append("\n");

                System.out.println("Saved Player");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE PLAYERS... please check the save directory in the code.");
        }
    }

    public void saveStudents() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SaveStudentTest.txt")));
            for (Student student : studentlist) {
                fileWriter.append(String.valueOf(student.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(student.getY()));
                fileWriter.append(",");
                fileWriter.append(student.getCurrentRoom().getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(student.getHasQuestionToPlayer()));
                fileWriter.append(",");
                fileWriter.append(student.getCurrentRoom().getName());
                fileWriter.append("\n");

                System.out.println("Saved Students");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE STUDENTS... please check the save directory in the code.");
        }
    }

    public void saveFurniture() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SaveFurnitureTest.txt")));
            for (Furniture furniture : furniturelist) {

                fileWriter.append(String.valueOf(furniture.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(furniture.getY()));
                fileWriter.append(",");
                fileWriter.append(furniture.getFurnitureName());
                fileWriter.append(",");
                fileWriter.append(furniture.getCurrentRoom().getName());
                fileWriter.append("\n");
                System.out.println("Saved Furniture");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE FURNITURE... please check the save directory in the code.");
        }
    }
//
//    public void loadItems() {
//        try {
//            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveItemsTest.txt")));
//            itemlist.clear();
//            String line;
//            while ((line = fileReader.readLine()) != null) {
//                //Get all tokens available in line
//                String[] tokens = line.split(",");
//                if (tokens.length > 0) {
//                    switch (tokens[0]) {
//                        case "Adderal":
//                            Adderall d = new Adderall(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]),  ?  ?  ?);
//                            itemlist.add(d);
//                            break;
//                        case "Coffee":
//                            Coffee c = new Coffee(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().get(tokens[3]),  ?);
//                            itemlist.add(c);
//                            break;
//                        case "Assignment":
//                            Assignment a = new Assignment(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().getExit(tokens[3]),  ?, "Assignment", "An assignment you can gr - calls the set name in Item super classade", 1);
//                            itemlist.add(a);
//                            break;
//                        case "Key":
//                            Key k = new Key(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().get(tokens[3]),  ?, "key", "used to open doors", 1);
//                            itemlist.add(k);
//                            break;
//                        case "EnergyDrink":
//                            EnergyDrink e = new EnergyDrink(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().getExit(tokens[3]),  ?);
//                            itemlist.add(e);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            System.err.println("BEEP BOOP, COULDNT LOAD ITEMS... please check the save directory in the code.");
//        }
//    }
//
//    public void loadPlayers() {
//        try {
//            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SavePlayersTest.txt")));
//            playerlist.clear();
//            String line;
//            while ((line = fileReader.readLine()) != null) {
//                //Get all tokens available in line
//                String[] tokens = line.split(",");
//                if (tokens.length > 0) {
//                    Player player = new Player(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], rm.getCurrentRoom().get(tokens[4]),  ?);
//                    playerlist.add(player);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... please check the save directory in the code.");
//        }
//    }
//
//    public void loadStudents() {
//        try {
//            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveStudentsTest.txt")));
//            studentlist.clear();
//            String line;
//            while ((line = fileReader.readLine()) != null) {
//                //Get all tokens available in line
//                String[] tokens = line.split(",");
//                if (tokens.length > 0) {
//                    Student student = new Student(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().get(tokens[3]),  ?, String.valueOf(tokens[4]));
//                    studentlist.add(student);
//                }
//
//            }
//        } catch (IOException e) {
//            System.err.println("BEEP BOOP, COULDNT LOAD STUDENTS... please check the save directory in the code.");
//
//        }
//    }
//
//    public void loadFurniture() {
//        try {
//            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveFurnitureTest.txt")));
//            furniturelist.clear();
//            String line;
//            while ((line = fileReader.readLine()) != null) {
//                //Get all tokens available in line
//                String[] tokens = line.split(",");
//                if (tokens.length > 0) {
//                    Furniture furniture = new Furniture(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().get(tokens[3]),  ?, String.valueOf(tokens[4]));
//                    furniturelist.add(furniture);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("BEEP BOOP, COULDNT LOAD FURNITURE... please check the save directory in the code.");
//        }
//    }
}
