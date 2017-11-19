import controller.Controller;
import model.Model;
import view.View;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}
