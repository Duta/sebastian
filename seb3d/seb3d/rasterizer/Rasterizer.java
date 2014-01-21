package rasterizer;

import lejos.nxt.LCD;

import java.util.List;

public class Rasterizer {
    private int width, height;
    private int scaleFactor;
    private List<Vec3> vertices;
    private List<Face> faces;

    public Rasterizer(List<Vec3> vertices, List<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
        this.width = LCD.SCREEN_WIDTH;
        this.height = LCD.SCREEN_HEIGHT;
        this.scaleFactor = Math.min(width, height)/2;
    }

    public void render() {
        // Clear the screen
        LCD.clear();

        // Draw the faces
        outerLoop:
        for(Face face : faces) {
            // Get the indices of the vertices
            // that make up this face's points
            int[] points = face.points;

            //TODO: Optimization:
            // Instead of allocating new arrays
            // on each iteration (VERY SLOW),
            // have a hash table of common length
            // int arrays that can be re-used.
            int len = points.length;
            int[] x = new int[len];
            int[] y = new int[len];
            // For each index, get the vertex
            // referenced and calculate its
            // x-y position on screen.
            // 3D effect is done by dividing
            // the x-y values of each vertex
            // by their z-value. This means
            // that points further away will
            // become closer to the centre.
            for(int i = 0; i < len; i++) {
                Vec3 v = vertices.get(points[i]);
                double z = v.z;
                if(z <= 0.0) {
                    continue outerLoop;
                }
                x[i] = width/2  + (int)(scaleFactor*(v.x)/z);
                y[i] = height/2 - (int)(scaleFactor*(v.y)/z);
            }
            drawPolygon(x, y, len);
        }
    }

    public void rotateVertices(double xRot, double yRot, double zRot) {
        double x, y, z, mag, rot;
        for(Vec3 vertex : vertices) {
            // Get the vertex's position
            x = vertex.x;
            y = vertex.y;
            z = vertex.z;

            // Rotate around the z-axis
            mag = Math.sqrt(x*x + y*y);
            rot = Math.atan2(y, x) + Math.toRadians(zRot);
            x = Math.cos(rot) * mag;
            y = Math.sin(rot) * mag;

            // Rotate around the y-axis
            mag = Math.sqrt(x*x + z*z);
            rot = Math.atan2(z, x) + Math.toRadians(yRot);
            x = Math.cos(rot) * mag;
            z = Math.sin(rot) * mag;

            // Rotate around the x-axis
            mag = Math.sqrt(y*y + z*z);
            rot = Math.atan2(y, z) + Math.toRadians(xRot);
            z = Math.cos(rot) * mag;
            y = Math.sin(rot) * mag;

            // Update the vertex's position
            vertex.x = x;
            vertex.y = y;
            vertex.z = z;
        }
    }

    private void drawPolygon(int[] xs, int[] ys, int num) {
        // Simple polygon-drawing algorithm
        if(num <= 1) return;
        for(int i = 0; i < num; i++) {
            drawLine(
                    xs[i],
                    ys[i],
                    xs[(i+1)%num],
                    ys[(i+1)%num]);
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        // Simple line-drawing algorithm
        // Uses Bresenham's algorithm
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        if(steep) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
        }
        if(x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        int dx = x2 - x1;
        int dy = Math.abs(y2 - y1);
        int err = dx/2;
        int yStep = y1 < y2 ? 1 : -1;
        int y = y1;
        for(int x = x1; x <= x2; x++) {
            if(steep) {
                LCD.setPixel(y, x, 1);
            } else {
                LCD.setPixel(x, y, 1);
            }
            err -= dy;
            if(err < 0) {
                y += yStep;
                err += dx;
            }
        }
    }
}