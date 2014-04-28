/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Clase que dentro de la ventana principal representa el mapa que se muestra por
 * pantalla.
 */
package gui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;

public class Map extends Component {
          
	private static final long serialVersionUID = 1L;
	BufferedImage image; // mapa con puntos de coordenadas
    BufferedImage source_image; // mapa original sin puntos coordenadas
	double minlongitude, maxlongitude, minlatitude, maxlatitude;
	
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), // scaled dimension
        				   0, 0, image.getWidth(null), image.getHeight(null), // original dimension
        				 null);
    }

    public Map(String filename, double minlat, double minlon,
    		double maxlat, double maxlon) {
    	minlongitude = minlon; minlatitude = minlat;
    	maxlongitude = maxlon; maxlatitude = maxlat;
       try {
            image = ImageIO.read(new File(filename));
			// Hacemos una copia para cuando haya que actualizar el mapa
            source_image = deepCopy(image);
       } catch (IOException e) {
       }
    }   
       
    public Dimension getPreferredSize() {
        if (image == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(image.getWidth(), image.getHeight());
       }
    }

	public void reset() {
		image = deepCopy(source_image);
	}
	
	common.Position positionFromPixelCoordinates(int x, int y)
	{
		return new common.Position(
				maxlatitude-((double)y/(double)image.getHeight())
								*(maxlatitude-minlatitude),
				maxlongitude-((double)x/(double)image.getWidth())
								*(maxlongitude-minlongitude));
	}
	
	public void mark(common.Position position, Color color)
	{
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		
		int x = (int)(((maxlongitude-position.longitude)/
						(maxlongitude-minlongitude))*(double)image.getWidth());
		int y = (int)(((maxlatitude-position.latitude)/
				(maxlatitude-minlatitude))*(double)image.getHeight());
		g2d.draw(new Ellipse2D.Double(x-2,y-2,4,4));
	}

	static BufferedImage deepCopy(BufferedImage bi) {
 		ColorModel cm = bi.getColorModel();
 		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
 		WritableRaster raster = bi.copyData(null);
 		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
