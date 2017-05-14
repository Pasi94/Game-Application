
package Application;

import javax.swing.ImageIcon;

/**
 * Created by Pasindu on 12/11/2016.
 */

public class Symbol implements ISymbol{

    private ImageIcon image;
    private int value;

    public Symbol() {
    }

    public Symbol(ImageIcon image, int value) {
        this.image = image;
        this.value = value;
    }

    

    @Override
    public ImageIcon getImage() {
        return image;
    }

   
    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    
    @Override
    public int getValue() {
        return value;
    }

    
    @Override
    public void setValue(int value) {
        this.value = value;
    }
    

}
