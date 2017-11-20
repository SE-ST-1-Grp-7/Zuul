package business;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class EntityManager {

    // containers for entities
    private ArrayList<Item> itemlist = new ArrayList<>();
    private Player player;
    private ArrayList<Student> studentlist = new ArrayList<>();
    private ArrayList<Furniture> furniturelist = new ArrayList<>();
    private RoomManager rm;

    public EntityManager(RoomManager rm) {
        this.rm = rm;
    }

    // ENTITY MANAGMENT METHODS
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
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Student> getStudentList() {
        return studentlist;
    }

    public void setStudentList(ArrayList<Student> studentlist) {
        this.studentlist = studentlist;
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }

    public void setFurnitureList(ArrayList<Furniture> furniturelist) {
        this.furniturelist = furniturelist;
    }

    public ArrayList<Item> getItemList() {
        return itemlist;
    }

    public void setItemList(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    public void saveGame() {
        saveItems();
        savePlayers();
        saveStudents();
        saveFurniture();
    }

    public void loadGame() {
        loadItems();
        loadPlayers();
        loadStudents();
//        loadFurniture();
    }

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

    public void saveInventory() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SaveInventoryTest.txt")));
            for (Item item : player.inventory().getInventory()) {
                fileWriter.append(item.getName());
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE INVENTORY... please check the save directory in the code.");
        }
    }

    public void savePlayers() {
        try {
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "\\Documents\\SavePlayersTest.txt")));
            fileWriter.append(player.getName());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(player.getX()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(player.getY()));
            fileWriter.append(",");
            fileWriter.append(player.getCurrentRoom().getName());
            fileWriter.append("\n");

            System.out.println("Saved Player");
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

    public void loadItems() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveItemsTest.txt")));
            itemlist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Adderal":
                            Adderall d = new Adderall(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]));
                            itemlist.add(d);
                            break;
                        case "Coffee":
                            Coffee c = new Coffee(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]));
                            itemlist.add(c);
                            break;
                        case "Assignment":
                            Assignment a = new Assignment(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "Assignment", "An assignment you can gr - calls the set name in Item super classade", 1);
                            itemlist.add(a);
                            break;
                        case "Key":
                            Key k = new Key(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "key", "used to open doors", 1);
                            itemlist.add(k);
                            break;
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().getExit(tokens[3]));
                            itemlist.add(e);
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD ITEMS... please check the save directory in the code.");
        }
    }

    public void loadInventory() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveInventoryTest.txt")));
            player.inventory().getInventory().clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Adderal":
                            Adderall d = new Adderall(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]));
                            player.inventory().addItem(d);
                            break;
                        case "Coffee":
                            Coffee c = new Coffee(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]));
                            player.inventory().addItem(c);
                            break;
                        case "Assignment":
                            Assignment a = new Assignment(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "Assignment", "An assignment you can gr - calls the set name in Item super classade", 1);
                            player.inventory().addItem(a);
                            break;
                        case "Key":
                            Key k = new Key(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "key", "used to open doors", 1);
                            player.inventory().addItem(k);
                            break;
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, rm.getCurrentRoom().getExit(tokens[3]));
                            player.inventory().addItem(e);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... please check the save directory in the code.");
        }
    }

    public void loadPlayers() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SavePlayersTest.txt")));
            player = null;
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    Player player = new Player(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], (Room) rm.getRoomlist().get(tokens[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... please check the save directory in the code.");
        }
    }

    public void loadStudents() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveStudentsTest.txt")));
            studentlist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    Student student = new Student(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64,
                            (Room) rm.getRoomlist().get(tokens[3]), Boolean.parseBoolean(tokens[4]));
                    studentlist.add(student);
                }

            }
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD STUDENTS... please check the save directory in the code.");

        }
    }

    public void loadFurniture() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.home") + ("\\Documents\\SaveItemsTest.txt")));
            furniturelist.clear();
            String line;
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "Chair":
                            Chair c = new Chair(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "Chair", "You can sit on this");
                            furniturelist.add(c);
                            break;
                        case "Table":
                            Table t = new Table(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 64, 64, (Room) rm.getRoomlist().get(tokens[3]), "Table", "It might contain items!", true, 2);

                            furniturelist.add(t);
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD FURNITURE... please check the save directory in the code.");
        }
    }
}
