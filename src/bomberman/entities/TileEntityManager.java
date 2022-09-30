package bomberman.entities;

import bomberman.ScreenController.Map1;
import bomberman.ScreenController.Screen;
import bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import java.io.*;
import java.util.ArrayList;

//Đây là một Class để đọc Map, quản lý các đối tượng trong Map. Class này sẽ cần đổi tên lại sau.
public class TileEntityManager {
    //Liên kết Class quản lý map với một map nào đó.
    private Map1 map;

    //Level, số hàng, cột được đọc vào từ File Level.txt
    private int level, row, col;

    //Các Entity được load vào map, các Entity này sẽ được khởi tạo trong phuong thức loadMap()
    private Entity tileMatrix[][];

    public TileEntityManager(Map1 map) {
        this.map = map;
    }

    public Entity[][] getTileMatrix() {
        return tileMatrix;
    }

    //Hàm đọc map từ File, sẽ được gọi trong Constructor của Map1. Hàm này sẽ cần xử lý lại các Exception.
    public void loadMap(String path) throws IOException {
        Reader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String firstLine = bufferedReader.readLine();
        String[] tokens = firstLine.split("\\s");
        level = Integer.parseInt(tokens[0]);
        row = Integer.parseInt(tokens[1]);
        col = Integer.parseInt(tokens[2]);

        //Thay đổi kích thước của map dựa theo dữ liệu trong file.
        map.setWidth(col);
        map.setHeight(row);

        //Khởi tạo mảng các đối tượng trong Map
        tileMatrix = new Entity[row][col];

        //Đọc file rồi tạo đối tượng trong Map, thêm các đối tượng vào các Array stillObject,...
        //Đang làm minh họa nên chỉ có Wall và Class.
        for (int i = 0; i < row; i++) {
            String rowText = bufferedReader.readLine();
            for (int j = 0; j < col; j++) {
                char x = rowText.charAt(j);
                if (x == '#') {
                    tileMatrix[i][j] = new Wall(j, i, Sprite.wall.getFxImage());
                    map.getStillObjects().add(tileMatrix[i][j]);
                } else {
                    tileMatrix[i][j] = new Grass(j, i, Sprite.grass.getFxImage());
                    map.getStillObjects().add(tileMatrix[i][j]);
                }
            }
        }
        reader.close();
        bufferedReader.close();
    }
}
