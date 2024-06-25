package ai;

import static constant.Constants.*;
import static constant.Constants.WINDOW_SIZE;

import game.Board;
import graphics.GameComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import util.Move;

public class QLearning extends JFrame {
    private final Map<String, Double> q_map;
    private final Board board;
    private final GameComponent game;

    public QLearning() {
        super(GAME_TITLE);
        q_map = new HashMap<>();

        board = new Board(AI_GAME_SIZE, AI_GAME_SIZE);

        game = new GameComponent(board);
        game.setVisible(true);
        this.add(game);
        this.setSize(WINDOW_SIZE, WINDOW_SIZE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void train() {
        Board trainBoard;
        for (int i = 0; i < TRAIN_NUM_GAMES; i++) {
            System.out.println(i);
            trainBoard = new Board(AI_GAME_SIZE, AI_GAME_SIZE);
            trainBoard.addRandomValue();
            trainBoard.addRandomValue();
            Move lastMove = Move.UP;
            while (!trainBoard.isGameOver()) {
                lastMove = determineMove(trainBoard, lastMove);
            }
        }
    }

    private Move determineMove(Board trainBoard, Move lastMove) {
        double random = Math.random();
        if (random <= DISCOUNT_FACTOR) {
            Move move = null;
            while (move == null) {
                move = Move.values()[(int) (Math.random() * 4)];
                if (!trainBoard.isValidMove(move)) move = null;
            }
            return move;
        }

        double max = -100000000;
        List<Move> prospects = new ArrayList<>();
        for (int i = 0; i < Move.values().length; i++) {
            Board prospect = trainBoard.cloneBoard();
            Move move = Move.values()[i];
            prospect.move(move);
            double prospectValue = getQValue(prospect + "_" + move);
            if (prospectValue == max) {
                prospects.add(move);
            } else if (prospectValue > max) {
                prospects.clear();
                prospects.add(move);
                max = prospectValue;
            }
        }

        Move picked = prospects.get((int) (Math.random() * prospects.size()));
        updateQMap(trainBoard, picked, lastMove);
        return picked;
    }

    private void updateQMap(Board trainBoard, Move picked, Move lastMove) {
        double reward;
        String prevKey = trainBoard.toString() + "_" + lastMove.toString();
        boolean isValidMove = trainBoard.isValidMove(picked);
        if (!isValidMove) reward = INVALID_MOVE_REWARD;
        else {
            reward = trainBoard.move(picked);
            if (trainBoard.getEmptyCells().size() == 1) reward = reward - ONE_EMPTY_REWARD;
        }

        String newKey = trainBoard + "_" + picked.toString();
        double oldQValue = getQValue(prevKey);
        double newQValue = (1 - LEARNING_RATE) * oldQValue;
        newQValue += LEARNING_RATE * (reward + oldQValue);

        q_map.put(newKey, newQValue);

        if (isValidMove) trainBoard.addRandomValue();
    }

    private Double getQValue(String key) {
        if (q_map.containsKey(key)) return q_map.get(key);
        else {
            q_map.put(key, INITIAL_Q_VALUE);
            return INITIAL_Q_VALUE;
        }
    }

    public void play() throws InterruptedException {
        board.addRandomValue();
        board.addRandomValue();

        game.setVisible(true);
        this.setVisible(true);
        while (!board.isGameOver()) {
            switch (getMove()) {
                case Move.UP -> {
                    if (board.isValidMove(UP_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(UP_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case Move.DOWN -> {
                    if (board.isValidMove(DOWN_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(DOWN_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case Move.LEFT -> {
                    if (board.isValidMove(LEFT_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(LEFT_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case Move.RIGHT -> {
                    if (board.isValidMove(RIGHT_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(RIGHT_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                default -> throw new IllegalStateException("Unexpected value");
            }
            Thread.sleep(50);
        }
    }

    private Move getMove() {
        double max = -10000000;
        List<Move> prospects = new ArrayList<>();
        for (int i = 0; i < Move.values().length; i++) {
            Move move = Move.values()[i];
            double prospectValue = getQValue(board.toString() + "_" + move.toString());

            if (prospectValue == max) {
                prospects.add(move);
            } else if (prospectValue > max) {
                prospects.clear();
                prospects.add(move);
                max = prospectValue;
            }
        }
        Move move = prospects.get((int) (Math.random() * prospects.size()));
        System.out.println("MOVE: " + move.toString());
        return move;
    }
}
