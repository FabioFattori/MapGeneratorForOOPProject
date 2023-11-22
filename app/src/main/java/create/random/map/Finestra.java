package create.random.map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Finestra {
    private JFrame frame;
    private JButton button;
    private Generator generatore = new Generator();

    public Finestra() throws Exception {
        frame = new JFrame("Map Generator");
        showMap mapPanel = new showMap();
        button = new JButton("Generate Map");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatore.generateMap();
                frame.setSize((generatore.nCol+3) * Configurations.pixelForImage, (generatore.nRow+3) * Configurations.pixelForImage);
                frame.setPreferredSize(frame.getSize());
                frame.repaint();
            }
        });
        frame.setSize((generatore.nCol+3) * Configurations.pixelForImage, (generatore.nRow+3) * Configurations.pixelForImage);
        frame.setPreferredSize(frame.getSize());
        
        frame.add(mapPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JFrame buttonContainer = new JFrame("Map Generator");
        buttonContainer.setSize(300, 200);
        buttonContainer.add(button);
        buttonContainer.setLocation(frame.getWidth(), 0);
        buttonContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonContainer.setVisible(true);

    }

    public class showMap extends JPanel {
        private final HashMap<Integer,BufferedImage> images = new HashMap<Integer,BufferedImage>();

        public showMap() throws Exception {
            try {
                images.put(0,ImageIO.read(getClass().getResource("/images/earth.png")));    
                images.put(1,ImageIO.read(getClass().getResource("/images/grass.png")));    
                images.put(2,ImageIO.read(getClass().getResource("/images/sand.png")));    
                images.put(3,ImageIO.read(getClass().getResource("/images/tree.png")));    
                images.put(4,ImageIO.read(getClass().getResource("/images/wall.png")));    
                images.put(5,ImageIO.read(getClass().getResource("/images/water.png")));    
            } catch (Exception e) {
                throw e;
            }
            
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);
            int[][] map = generatore.map;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                        g2.setColor(java.awt.Color.BLACK);
                        if (map[i][j] == 0) {
                            g2.drawImage(images.get(0), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        } else if (map[i][j] == 1) {
                            g2.drawImage(images.get(1), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        } else if (map[i][j] == 2) {
                            g2.drawImage(images.get(2), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        }else if (map[i][j] == 3) {
                            g2.drawImage(images.get(3), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        }else if (map[i][j] == 4) {
                            g2.drawImage(images.get(4), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        }else if (map[i][j] == 5) {
                            g2.drawImage(images.get(5), j * Configurations.pixelForImage, i * Configurations.pixelForImage,Configurations.pixelForImage,Configurations.pixelForImage,null);
                        }
                }
            }
        }
    
        
    }
}
