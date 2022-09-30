package bomberman;

import bomberman.ScreenController.Map1;
import bomberman.ScreenController.Screen;
import bomberman.entities.Entity;
import bomberman.entities.MovingEntity;
import bomberman.entities.TileEntity;
import bomberman.graphics.Sprite;
import javafx.scene.Scene;

//Class kiểm tra va chạm
public class CollisionChecker {

    //Liên kết với một map cụ thể
    private Map1 map;
    public CollisionChecker(Map1 map) {
        this.map = map;
    }

    public void checkTile(MovingEntity entity) {

        //Xác định tọa độ của hitbox của entity
        int entityLeftX = entity.getX() + entity.getSolidArea().x;
        int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopY = entity.getY() + entity.getSolidArea().y;
        int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY/Sprite.SCALED_SIZE;

        //Tạo hai hai Entity phụ để kiểm tra va chạm
        TileEntity tileEntity1, tileEntity2;

        //Kiểm tra va chạm theo từng hướng
        switch (entity.getDirection()) {
            case "up": {
                entityTopRow = (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityLeftCol][entityTopRow];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityRightCol][entityTopRow];
                if (tileEntity1.isCollision() || tileEntity2.isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            }
            case "down": {
                entityBottomRow = (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityLeftCol][entityBottomRow];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityRightCol][entityBottomRow];
                if (tileEntity1.isCollision() || tileEntity2.isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            }
            case "left": {
                entityLeftCol = (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityLeftCol][entityTopRow];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityLeftCol][entityBottomRow];
                if (tileEntity1.isCollision() || tileEntity2.isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            }
            case "right": {
                entityRightCol = (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityRightCol][entityTopRow];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getTileMatrix()[entityRightCol][entityBottomRow];
                if (tileEntity1.isCollision() || tileEntity2.isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            }
        }
    }
}
