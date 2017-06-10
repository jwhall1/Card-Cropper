/**
 *	removes the border of images and shrinks size of file by half
 *	made to be used with open source card gaming applications
 *	author: john hall
 */
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cropper{
	static BufferedImage src = null;

	/**
	 *	searches current working directory for jpg files ONLY!
	 */
	public static void main(String[] args) throws IOException{
		File dir = new File(".");		
		File[] list = dir.listFiles();
		for(File temp : list){
			String card = temp.getName();
			if(card.contains(".jpg")){
				crop(card);
			}
		}
	}
	/**
	 *	crops off border and compresses size by half!
	 */
	public static void crop(String name) throws IOException{		
		try{
			src = ImageIO.read(new File(name));
			int w = src.getWidth(), h = src.getHeight();	
			BufferedImage card = new BufferedImage(w-20,h-20,BufferedImage.TYPE_INT_RGB);
			card.getGraphics().drawImage(src ,0 ,0 ,w-10 ,h-10 ,10 ,10 ,w ,h ,null);
			String n = name.replace(".jpg","");
			ImageIO.write(card, "jpg", new File(n + ".full.jpg"));
			System.out.println(name + " successfully modified!");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("-error occured modifying card: "+ name);
		}
	}
}
