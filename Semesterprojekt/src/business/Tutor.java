package business;

/**
 *
 * @author Robin
 */
public class Tutor extends Student {
    boolean hasGivenItem;
    private boolean tutorAsked;

    public Tutor(String id, int x, int y, Room currentRoom, String studentImage, EntityManager em) {
        super(id, x, y, currentRoom, false, studentImage, em);
        
        this.hasGivenItem = false;
    }

    @Override
    public void onInteract(Person p) {
        /* we set the variable to true. this variable will be sent trough the
           bussinesFacade to the UI where we write text for questions. */
        p.setPlayerAskedTutor(true);
        if (!hasGivenItem) {
            p.getEntityManager().getPlayer().inventory().addItem(
              new Coffee("ID90",
                         p.getEntityManager().getPlayer().getX(),
                         p.getEntityManager().getPlayer().getY(),
                         64,
                         64,
                         p.getEntityManager().getPlayer().getCurrentRoom()));
            this.hasGivenItem = true;
            this.tutorAsked = true;
        }
    }
}
