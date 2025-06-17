package Mai;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class bubblesort_gui extends JPanel {

    private static final int ARRAY_SIZE = 161;
    private static final int MAX_VALUE = 100;
    private int[] array;
    private int delay = 5; // ms Pause zwischen Schritten

    public bubblesort_gui() {
        array = new int[ARRAY_SIZE];
        Random rand = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(MAX_VALUE + 1);
        }

        // Thread zum Sortieren starten
        new Thread(this::bubbleSort).start();
    }

    private void bubbleSort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                repaint(); // GUI aktualisieren
                try {
                    Thread.sleep(delay); // kurz warten, damit Animation sichtbar ist
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / ARRAY_SIZE;

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((array[i] / (double) MAX_VALUE) * (height - 20));
            g.setColor(Color.BLUE);
            g.fillRect(i * barWidth, height - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(i * barWidth, height - barHeight, barWidth, barHeight);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BubbleSort Visualisierung");
        bubblesort_gui panel = new bubblesort_gui();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(panel);
        frame.setVisible(true);
    }
}
