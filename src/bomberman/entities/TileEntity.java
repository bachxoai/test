package bomberman.entities;

import javafx.scene.image.Image;

//Đây là Class chung cho các Entity đứng yên.
public class TileEntity extends Entity{
    //Đây là biến boolean cho biết Entity này có chặn người chơi đi qua hay không?
    protected boolean collision = false;
    public TileEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean isCollision() {
        return collision;
    }

    @Override
    public void update() {

    }
}
