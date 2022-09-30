package bomberman.ScreenController;

import bomberman.CollisionChecker;
import bomberman.entities.*;
import bomberman.graphics.Sprite;
import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Map1 extends Screen {
  //Chiều rộng của map
  private int width;

  //Chiều cao của map
  private int height;
  private GraphicsContext gc;
  private Canvas canvas;
  private ArrayList<Entity> entities = new ArrayList<>();
  private ArrayList<Entity> stillObjects = new ArrayList<>();

  private Label l;
  private Button b;

  //Nhân vật người chơi, khởi tạo trong Constructor
  private Bomber bomberman;

  //Quản lý các đối tượng trong map + đọc map
  private TileEntityManager tileEntityManager = new TileEntityManager(this);

  //Kiểm tra va chạm
  private CollisionChecker collisionChecker = new CollisionChecker(this);

  //Một biến đếm số frame của từng giây, sẽ sử dụng kết hợp với MovingEntity để tạo Animation
  public static int frameCount = 0;

  public Map1(String currentScreen) throws IOException {
    super(currentScreen);
    tileEntityManager.loadMap("res/levels/Level1.txt");
    createScene();
    bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), this);
    entities.add(bomberman);
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public ArrayList<Entity> getStillObjects() {
    return stillObjects;
  }

  public TileEntityManager getTileEntityManager() {
    return tileEntityManager;
  }

  public CollisionChecker getCollisionChecker() {
    return collisionChecker;
  }

  public void createLabel() {
    l = new javafx.scene.control.Label("Click to switch to Menu");
  }

  public void createButton() {
    b = new javafx.scene.control.Button("Back to Menu");
    b.setOnAction(actionEvent -> {
      System.out.println(currentScreen);
      currentScreen = "Menu";
      Stage s = (Stage) b.getScene().getWindow();
      s.setScene(allScreens.get(currentScreen).getScene());
    });
    b.setLayoutX(800);
    b.setLayoutY(100);
  }

  public void createScene() {
    canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
    gc = canvas.getGraphicsContext2D();
    Group root = new Group();
    root.getChildren().add(canvas);
    createButton();
    root.getChildren().add(b);
    scene = new Scene(root);

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        //Mỗi khung hình (frame) thì biến frameCount sẽ tăng lên 1 đơn vị
        //Nếu frameCount >=60 thì frameCount sẽ được gán lại = 0.
        frameCount = (frameCount+1)%60;
        handleEvent();
        render();
        update();
      }
    };
    timer.start();
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }

  //Phương thức handleEvent cho người chơi.
  public void handleEvent() {
    scene.setOnKeyPressed(keyEvent -> {
      bomberman.handleEvent(keyEvent);
    });

    scene.setOnKeyReleased(keyEvent -> {
      bomberman.handleReleasedEvent(keyEvent);
    });
  }

}
