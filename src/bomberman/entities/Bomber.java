package bomberman.entities;

import bomberman.ScreenController.Map1;
import bomberman.ScreenController.Screen;
import bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

public class Bomber extends MovingEntity {
    //Liên kết Bomber với Map mà tạo ra Bomber đó.
    private Map1 map;
    //Kiểm tra xem nút Up có đang được bấm hay không? Các nút còn lại tương tự.
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;


    public Bomber(int x, int y, Image img, Map1 map) {
        super( x, y, img);
        //Thêm các Spite animation cho Bomber
        setSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, Sprite.player_down,
                Sprite.player_down_1, Sprite.player_down_2, Sprite.player_left, Sprite.player_left_1,
                Sprite.player_left_2, Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2);
        solidArea = new Rectangle(0,12,20,20); //Cài đặt thông số cho hitbox của Bomber
        this.map = map;
        Velocity = 2; //Vận tốc của Bomber = 2 pixel/frame
    }

    @Override
    public void update() {
        //Nếu có phím được bấm thì thay đổi hướng + nhân vật thực hiện animation, lúc này nhân vật chưa thay đổi vị trí
        //vì nhân vật có thể bị kẹt tường
        if (upPressed || downPressed || leftPressed || rightPressed) {
            if (upPressed) {
                direction = "up";
                animatedUp();
            }
            if (downPressed) {
                direction = "down";
                animatedDown();
            }

            if (leftPressed) {
                direction = "left";
                animatedLeft();
            }

            if (rightPressed) {
                direction = "right";
                animatedRight();
            }
        }

        //Kiểm tra xem nhân vật có bị kẹt tường không.
        collisionOn = false;
        map.getCollisionChecker().checkTile(this);

        //Nếu nhân vật không bị kẹt tường thì thay đổi vị trí theo hướng (direction)
        if (!collisionOn) {
            switch (direction) {
                case "up" -> {
                    y -= Velocity;
                    break;
                }
                case "down" -> {
                    y += Velocity;
                    break;
                }
                case "left" -> {
                    x -= Velocity;
                    break;
                }
                case "right" -> {
                    x += Velocity;
                    break;
                }
            }
        }
    }

    public void handleEvent(KeyEvent event) {
        //Handle Event nhận vào, bấm W thì đi lên, S đi xuống, A sang trái, D sang phải
        //Nhân vật chỉ có thể được đi một hướng duy nhất
        switch (event.getCode()) {
            case W ->  {
                upPressed = true;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;
                break;
            }
            case S -> {
                upPressed = false;
                downPressed = true;
                leftPressed = false;
                rightPressed = false;
                break;
            }
            case A -> {
                upPressed = false;
                downPressed = false;
                leftPressed = true;
                rightPressed = false;
                break;
            }
            case D -> {
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = true;
                break;
            }
        }
    }

    public void handleReleasedEvent(KeyEvent event) {
        //Khi thả nút ra thì hướng di chuyển sẽ = none
        direction = "none";

        //Handle sự kiện thả phím, thả phím nào thì sẽ thay đổi img đứng yên theo hướng đó.
        switch (event.getCode()) {
            case W ->  {
                upPressed = false;
                img = Sprite.player_up.getFxImage();
                break;
            }
            case S -> {
                downPressed = false;
                img = Sprite.player_down.getFxImage();
                break;
            }
            case A -> {
                leftPressed = false;
                img = Sprite.player_left.getFxImage();
                break;
            }
            case D -> {
                rightPressed = false;
                img = Sprite.player_right.getFxImage();
                break;
            }
        }
    }
}


