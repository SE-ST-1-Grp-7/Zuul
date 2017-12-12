package business;

/**
 * Tutor class, friendly NPC based on student.
 * 
 * @author Robin & Rasmus Willer
 */
public class Tutor extends Student {
    // Attribute for if player have interacted with tutor previously.
    boolean hasGivenItem;

    /**
     * Constructor; pass arguments to super class and sets an attribute that
     * tutor haven't interacted with player previously.
     * 
     * @param id                String, id of instantiation.
     * @param x                 int, X coordinate of room grid.
     * @param y                 int, Y coordinate of room grid.
     * @param currentRoom       Room, located in this room.
     * @param studentImage      String, file path of texture.
     * @param em                EntityManager, used to place coffee upon
     *                          interaction from player.
     */
    public Tutor(String id,
            int x,
            int y,
            Room currentRoom,
            String studentImage,
            EntityManager em) {
        // Pass arguments to super class.
        super(id, x, y, currentRoom, false, studentImage, em);
        // Set attribute to player haven't interacted yet.
        this.hasGivenItem = false;
    }

    /**
     * Upon interaction with person, give coffee to player.
     * 
     * @param p     Person, character whom interacted with tutor.
     */
    @Override
    public void onInteract(Person p) {
        /* we set the variable to true. this variable will be sent trough the
           bussinesFacade to the UI where we write text for questions. */
        ((Player)p).setPlayerAskedTutor(true);
        // If haven't given item before.
        if (!hasGivenItem) {
            // Instantiate coffee to player's inventory.
            p.getEntityManager().getPlayer().inventory().addItem(
              new Coffee("ID90",
                         p.getEntityManager().getPlayer().getX(),
                         p.getEntityManager().getPlayer().getY(),
                         p.getEntityManager().getPlayer().getCurrentRoom()));
            // Set tutor to have been asked before.
            this.hasGivenItem = true;
        }
    }
}