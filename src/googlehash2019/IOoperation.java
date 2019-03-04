/**
 * 
 */
package googlehash2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author oguz
 *
 */

public class IOoperation {
	
	private final File theFile;
	private Scanner readableFile;
	/**
	 * @param just name of file
	 */
	public IOoperation(String nameOfFile) {
		theFile=new File(nameOfFile+".txt");
		scanTheFile();
	}
	/**
	 * create readableFile
	 */
	private void scanTheFile(){
		try {
			readableFile=new Scanner(theFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return String or NULL and get us next word according to space
	 */
	public String getNextLine(){
		while(readableFile.hasNextLine())
		{
			String tempStr=readableFile.nextLine();
			if(!tempStr.isEmpty()){
				return tempStr;
			}
			
		}
		return null;
	}
	public int getLen(){
		int i=0;
		while(readableFile.hasNextLine())
		{
			readableFile.nextLine();
			/*if(!tempStr.isEmpty()){
				return tempStr;
			}*/
			i++;
			
		}
		return i;
	}
	/**
	 * will add output op.
	 */
}
