package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageHelper {
  public static BufferedImage overlay(
          BufferedImage starting, URL fpath, int offset) throws IOException {
    BufferedImage overlay = ImageIO.read(fpath);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }
}
