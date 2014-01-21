package rasterizer;

import lejos.nxt.Button;
import lejos.util.Delay;
import lejos.util.Timer;
import lejos.util.TimerListener;

import java.util.ArrayList;
import java.util.List;

public class Demos {
    public static void rotatingCube() {
        List<Vec3> vertices = new ArrayList<Vec3>();
        List<Face> faces = new ArrayList<Face>();
        vertices.add(new Vec3( 1,  1,  1));
        vertices.add(new Vec3( 1,  1, -1));
        vertices.add(new Vec3( 1, -1,  1));
        vertices.add(new Vec3( 1, -1, -1));
        vertices.add(new Vec3(-1,  1,  1));
        vertices.add(new Vec3(-1,  1, -1));
        vertices.add(new Vec3(-1, -1,  1));
        vertices.add(new Vec3(-1, -1, -1));
        faces.add(new Face(new int[] {0, 1, 3, 2}));
        faces.add(new Face(new int[] {4, 5, 7, 6}));
        faces.add(new Face(new int[] {0, 2, 6, 4}));
        faces.add(new Face(new int[] {1, 3, 7, 5}));
        faces.add(new Face(new int[] {0, 1, 5, 4}));
        faces.add(new Face(new int[] {2, 3, 7, 6}));

        final Rasterizer rasterizer = new Rasterizer(vertices, faces);
        
        while(true) {
        	rasterizer.render();
        	rasterizer.rotateVertices(1, 2, 0);
        	Delay.msDelay(10);
        }/*

        Timer timer = new Timer(1000, new TimerListener() {
            public void timedOut() {
                rasterizer.rotateVertices(1, 2, 0);
                rasterizer.render();
            }
        });

        timer.start();
        Button.ESCAPE.waitForPress();
        timer.stop();*/
    }

    public static void rotatingS() {
        List<Vec3> vertices = new ArrayList<Vec3>();
        List<Face> faces = new ArrayList<Face>();
        vertices.add(new Vec3(-0.6, -1.0,  0.2)); // 0
        vertices.add(new Vec3(-0.6, -0.6,  0.2));
        vertices.add(new Vec3( 0.2, -0.6,  0.2));
        vertices.add(new Vec3( 0.2, -0.2,  0.2));
        vertices.add(new Vec3(-0.2, -0.2,  0.2));
        vertices.add(new Vec3(-0.6,  0.2,  0.2)); // 5
        vertices.add(new Vec3(-0.6,  0.6,  0.2));
        vertices.add(new Vec3(-0.2,  1.0,  0.2));
        vertices.add(new Vec3( 0.6,  1.0,  0.2));
        vertices.add(new Vec3( 0.6,  0.6,  0.2));
        vertices.add(new Vec3(-0.2,  0.6,  0.2)); //10
        vertices.add(new Vec3(-0.2,  0.2,  0.2));
        vertices.add(new Vec3( 0.2,  0.2,  0.2));
        vertices.add(new Vec3( 0.6, -0.2,  0.2));
        vertices.add(new Vec3( 0.6, -0.6,  0.2));
        vertices.add(new Vec3( 0.2, -1.0,  0.2)); //15
        vertices.add(new Vec3(-0.6, -1.0, -0.2));
        vertices.add(new Vec3(-0.6, -0.6, -0.2));
        vertices.add(new Vec3( 0.2, -0.6, -0.2));
        vertices.add(new Vec3( 0.2, -0.2, -0.2));
        vertices.add(new Vec3(-0.2, -0.2, -0.2)); //20
        vertices.add(new Vec3(-0.6,  0.2, -0.2));
        vertices.add(new Vec3(-0.6,  0.6, -0.2));
        vertices.add(new Vec3(-0.2,  1.0, -0.2));
        vertices.add(new Vec3( 0.6,  1.0, -0.2));
        vertices.add(new Vec3( 0.6,  0.6, -0.2)); //25
        vertices.add(new Vec3(-0.2,  0.6, -0.2));
        vertices.add(new Vec3(-0.2,  0.2, -0.2));
        vertices.add(new Vec3( 0.2,  0.2, -0.2));
        vertices.add(new Vec3( 0.6, -0.2, -0.2));
        vertices.add(new Vec3( 0.6, -0.6, -0.2)); //30
        vertices.add(new Vec3( 0.2, -1.0, -0.2));
        faces.add(new Face(new int[] {
                 0,  1,  2,  3,
                 4,  5,  6,  7,
                 8,  9, 10, 11,
                12, 13, 14, 15}));
        faces.add(new Face(new int[] {
                16, 17, 18, 19,
                20, 21, 22, 23,
                24, 25, 26, 27,
                28, 29, 30, 31}));
        for(int i = 0; i < 16; i++) {
            faces.add(new Face(new int[] {i, (i+1)%16, (i+17)%32, (i+16)%32}));
        }

        final Rasterizer rasterizer = new Rasterizer(vertices, faces);

        Timer timer = new Timer(17, new TimerListener() {
            public void timedOut() {
                rasterizer.rotateVertices(1, 2, 1);
                rasterizer.render();
            }
        });

        timer.start();
        Button.ESCAPE.waitForPress();
        timer.stop();
    }
}