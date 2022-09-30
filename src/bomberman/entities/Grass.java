package bomberman.entities;

import javafx.scene.image.Image;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        collision = false; //Cỏ cho người chơi đi qua
    }

    @Override
    public void update() {

    }
}
