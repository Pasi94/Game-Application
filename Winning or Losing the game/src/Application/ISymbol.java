
package Application;

import javax.swing.ImageIcon;

/**
 * Created by Pasindu on 12/11/2016.
 */

public interface ISymbol {

    void setImage(ImageIcon image);

    ImageIcon getImage();

    void setValue(int v);

    int getValue();

}
