import ai.QLearning;
import graphics.GameWindow;

public class Main {
    public static void main(String[] args) {
//        GameWindow game = new GameWindow();

        //        AIGame game = new AIGame();
        //        game.run();

        //        TerminalGame game = new TerminalGame();
        //        game.run();

                QLearning game = new QLearning();
                game.train();
                try {
                    game.play();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
    }
}
