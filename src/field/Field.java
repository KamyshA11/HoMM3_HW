package field;

import field.*;

import java.util.ArrayList;

public class Field {
    private boolean isShown = true;
    int[] size = new int[2];
    protected BaseCeil[][] data;

    public Field(int n, int m) {
        size[0] = n;
        size[1] = m;
        data = new BaseCeil[n][m];
        int[][] numbericField = new NumbericField(n, m,
                "C:\\Users\\Admin\\IdeaProjects\\HoMM3 Lab1\\src\\field\\layer1.txt").getNumbericField();

        boolean flag = false;

        for (int y = 0; y < size[0]; y++) {
            for (int x = 0; x < size[1]; x++) {
                if (numbericField[y][x] == 5) {
                    data[y][x] = new GrossCeil();
                } else if (numbericField[y][x] == 7) {
                    data[y][x] = new TreeCeil();
                } else if (numbericField[y][x] == 0) {
                    String loyalty = "Игрок";
                    if (flag) {
                        loyalty = "Враг";
                    }
                    BaseCeil castleCeil = new CastleCeil(loyalty);
                    if (flag) {
                        castleCeil.setHasEnemy(true);
                    }
                    data[y][x] = castleCeil;
                    flag = true;
                } else if (numbericField[y][x] == 1) {
                    data[y][x] = new PathCeil();
                } else if (numbericField[y][x] == 4) {
                    data[y][x] = new BoxCeil();
                } else if (numbericField[y][x] == 3) {
                    data[y][x] = new ObeliscCeil();
                } else if (numbericField[y][x] == 2) {
                    data[y][x] = new GateCeil();
                } else {
                    data[y][x] = new WaterCeil();
                }
            }
        }
    }

    // Проверить, что лежит в size[]
    public boolean isAvailableToGo(int i, int j) {
        return (i > 0) && (i < size[1]) && (j > 0) && (j < size[0]);
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int[] size) {
        this.size = size;
    }

    public BaseCeil[][] getData() {
        return data;
    }

    public void setData(BaseCeil[][] data) {
        this.data = data;
    }

    private boolean isInField(int x, int y) {
        return !((x < 0) || (y < 0) || (x >= size[1]) || (y >= size[0]));
    }

    private void DFS(int[][] map_dist, int _x, int _y, int step) {
        // Проверка на выход за пределы карты
        if (!isInField(_x, _y)) {
            return;
        }

        map_dist[_y][_x] = Math.min(step, map_dist[_y][_x]);
        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                if (!isInField(_x + dx, _y + dy)) {
                    continue;
                }
                if (map_dist[_y + dy][_x + dx] > step) {
                    DFS(map_dist, _x + dx, _y + dy, step + 1);
                }
            }
        }
    }

    private void movePoint(Point current, int[][] map_dist) {
        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                if (!isInField(current.x + dx, current.y + dy)) {
                    continue;
                }
                if (map_dist[current.y + dy][current.x + dx] == map_dist[current.y][current.x] - 1) {
                    current.x += dx;
                    current.y += dy;
                    return;
                }
            }
        }
    }

    public int[][] getDistanceMap(int start_x, int start_y) {
        int[][] mapDist = new int[size[0]][size[1]];
        for (int y = 0; y < size[0]; y++) {
            for (int x = 0; x < size[1]; x++) {
                mapDist[y][x] = 10000;
            }
        }
        for (int y = 0; y < size[0]; y++) {
            for (int x = 0; x < size[1]; x++) {
                if (!data[y][x].canThrough) {
                    mapDist[y][x] = -1;
                }
            }
        }
        DFS(mapDist, start_x, start_y, 0);
        return mapDist;
    }


    public ArrayList<Point> getPath(int x1, int y1, int x2, int y2) {
        ArrayList<Point> path = new ArrayList<Point>();
        int[][] map_dist = getDistanceMap(x1, y1);
        if (map_dist[y2][x2] == -1) {
            return path;
        }
        Point current = new Point(x2, y2);
        Point finish = new Point(x1, y1);

        path.add(new Point(current));
        while (!current.equals(finish)) {
            movePoint(current, map_dist);
            path.add(new Point(current));
        }

        return path;
    }

    public void draw() {
        if (isShown) {
            for (int y = 0; y < size[0]; y++) {
                for (int x = 0; x < size[1]; x++) {
                    boolean status = data[y][x].isHasHero();
                    if (status) {
                        System.out.print(data[y][x].getHero().getConsoleSymbol());
                    }
                    else if (data[y][x].isHasEnemy()) {
                        System.out.print("\u001B[40;31m" + " H ");
                    }
                    else {
                        data[y][x].draw();
                    }
                }
                System.out.println("\u001B[0m");
            }
        }
    }
}
