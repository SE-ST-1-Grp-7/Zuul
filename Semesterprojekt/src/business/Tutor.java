package business;

/**
 *
 * @author Robin
 */
public class Tutor extends Student {

    boolean hasGivenItem;
    private boolean tutorAsked;

    public Tutor(int x, int y, Room currentRoom, String studentImage, EntityManager em) {
        super(x, y, currentRoom, false, studentImage, em);
        this.hasGivenItem = false;
    }

    @Override
    public void onInteract(Person p) {
        if (!hasGivenItem) {
            p.getEntityManager().getPlayer().inventory().addItem(new Coffee(p.getEntityManager().getPlayer().getX(), p.getEntityManager().getPlayer().getY(), 64, 64, p.getEntityManager().getPlayer().getCurrentRoom()));
            this.hasGivenItem = true;
            this.tutorAsked = true;
        }
    }

    public boolean getTutorAsked() {
        return this.tutorAsked;
    }

    public void setTutorAsked(boolean b) {
        this.tutorAsked = b;
    }
}
