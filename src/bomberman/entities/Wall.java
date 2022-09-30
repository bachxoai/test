package bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends TileEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        collision = true; //Tường không cho người chơi đi qua
    }

    @Override
    public void update() {

    }
}
