package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Static class with a helper function for processing images.
 */
public class ImageHelper {
  /**
   * Overlays one image atop of another.
   *
   * @param starting the base image
   * @param fpath the path to the new image
   * @param offset what offset the new image should be rendered with
   * @return the new combined image
   * @throws IOException if the provided image path is invalid
   */
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
