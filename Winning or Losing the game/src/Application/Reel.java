package Application;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Created by Pasindu on 12/11/2016.
 */

public class Reel {

    private final Symbol seven = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/redseven.png"))), 7);
    private final Symbol bell = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/bell.png"))), 6);
    private final Symbol watermelon = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/watermelon.png"))), 5);
    private final Symbol plum = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/plum.png"))), 4);
    private final Symbol lemon = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/lemon.png"))), 3);
    private final Symbol cherry = new Symbol(resizeImage(new ImageIcon(getClass().getResource("/Application/images/cherry.png"))), 2);
 

    public Symbol[] spin() {
        int[] arrRandom = new int[6];
        Symbol[] arrSymbol = new Symbol[6];
        int count = 0;
        int num;
        Random r = new Random();

        while (count < arrRandom.length) {
            num = r.nextInt(6) + 2;
            boolean repeat = false;
            do {
                for (int i = 0; i < arrRandom.length; i++) {
                    if (num == arrRandom[i]) {
                        repeat = true;
                        break;
                    } else if (i == count) {
                        arrRandom[count] = (num);
                        count++;
                        repeat = true;
                        break;
                    }

                }
            } while (!repeat);

        }
        int j = 0;
        for (int i : arrRandom) {
            arrSymbol[j] = getSymbol(i);
            j++;
        }
        return arrSymbol;
    }

  

    public Symbol getSymbol(int num) {
        Symbol s = new Symbol();
        switch (num) {
            case 2:
                s = getSeven();

                break;
            case 3:
                s = getBell();

                break;
            case 4:
                s = getWatermelon();

                break;
            case 5:
                s = getPlum();

                break;
            case 6:
                s = getLemon();

                break;
            case 7:
                s = getCherry();

                break;
        }
        return s;
    }

    /**
     * @return the seven
     */
    public Symbol getSeven() {
        return seven;
    }

    /**
     * @return the bell
     */
    public Symbol getBell() {
        return bell;
    }

    /**
     * @return the watermelon
     */
    public Symbol getWatermelon() {
        return watermelon;
    }

    /**
     * @return the plum
     */
    public Symbol getPlum() {
        return plum;
    }

    /**
     * @return the lemon
     */
    public Symbol getLemon() {
        return lemon;
    }

    /**
     * @return the cherry
     */
    public Symbol getCherry() {
        return cherry;
    }

    private ImageIcon resizeImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
