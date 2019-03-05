/**
 * 
 */
package googlehash2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
/**
 * @author oguz
 *
 */
public class Process {
	private IOoperation data;
	Map<String, List<Integer>> tags = new TreeMap<String, List<Integer>>();
	List<String> slides=new ArrayList<String>();
	private int piI=0;
	private String[] temp;
	Map<Integer, String[]> map = new TreeMap<Integer, String[]>();
	  public static boolean ASC = true;
	    public static boolean DESC = false;
	public Process() {
		String line;
		data=new IOoperation("e_shiny_selfies");
		//System.out.println(numberOfFoto);
		//System.out.println(numberOfRows+" "+numberOfColumns+" "+numberOfMinCells+" "+numberOfMaxCells);
		boolean isOk=true;
		while (isOk) {
			line=data.getNextLine();
			if(line!=null) {
				temp=line.split(" ");
					//for (int i = 0; i < temp.length; i++) {
						//System.out.print(temp[i]+" ");
						//thedata[piI][i]=temp[i];
						if(temp.length>1) {
							map.put(piI, temp);
							for (int i = 0; i < temp.length; i++) {
								if(i>=2) {
										addToList(temp[0]+temp[i], piI);
								}
							}
						}
						
					//}
						if(temp.length>1) {
					piI++;
						}
				
			}else {
				isOk=false;
			}
		}
		piI=0;
		//printTags();
		makeSlide();
		System.out.println(slides.size());
		for (String entry2 : slides)
        {
			System.out.println(entry2);
        }
		
		
		//editH();
		//printSlides();
		//printMap(map);
		
		//System.out.println(sortedMapAsc.size());
		//printTags(tags);
		//printMap(sortedMapAsc);
		/*for (Map.Entry<Integer, String[]> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " => " + entry.getValue()[0]);
		}*/
		
		
	}
	public synchronized void addToList(String mapKey, Integer myItem) {
	    List<Integer> itemsList = tags.get(mapKey);

	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<Integer>();
	         itemsList.add(myItem);
	         tags.put(mapKey, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(myItem)) itemsList.add(myItem);
	        
	    }
	}

	    public static void printMap(Map<Integer, String[]> map)
	    {
	        for (Entry<Integer, String[]> entry : map.entrySet())
	        {
	            //System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue()[1]);
	        	System.out.println(entry.getKey() );
	        }
	    }
	    public static void printTags(Map<String, List<Integer>> map)
	    {
	        for (Entry<String, List<Integer>> entry : map.entrySet())
	        {
	            //System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue()[1]);
	        	System.out.println(entry.getKey() );
	        	System.out.println(entry.getValue().toString());
	        	
	        }
	    }
	int notal=0;
	private void makeSlide() {
		List<Integer> tekrar=new ArrayList<Integer>();
		int s=0;
		boolean isV=false;
		for (Entry<Integer, String[]> entry : map.entrySet())
        {
            //System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue()[1]);
        	//System.out.println(entry.getKey() );
        	s=entry.getKey();
        	for (int i = 0; i < entry.getValue().length; i++) {
				if(entry.getValue()[0].equals("H")) {
					if(i>1) {
						List<Integer> temp =tags.get("H"+entry.getValue()[i]);
						for (Integer entry2 : temp)
				        {
							if(entry2!=s) {
								tekrar.add(entry2);
							}
				        }
					}
				}else {
					if(i>1) {
						isV=true;
						List<Integer> temp =tags.get("V"+entry.getValue()[i]);
						for (Integer entry2 : temp)
				        {
							if(entry2!=s) {
								tekrar.add(entry2);
							}
				        }
					}
				}
			}
        	/*if(mostCommon(tekrar)==null) {
        		System.out.println(s);
        	}*/
        	if(isV) {
        		int most=mostCommon(tekrar);
        		if(vSlidesControl(s, most)) {
            		slides.add(s+" "+mostCommon(tekrar));
            	
            	}
        		isV=false;
        	}else {
        		if(!slides.contains(String.valueOf(s))) {
            		slides.add(String.valueOf(s));
                	
            	}
            	if(!slides.contains(String.valueOf(mostCommon(tekrar)))) {
            		slides.add(String.valueOf(mostCommon(tekrar)));
            	}
        	}
        	
        	
        	tekrar.clear();
        	//System.out.println();
        }
			
	}
	public boolean vSlidesControl(int s, int tekrar) {
		for(String str: slides) {
		    if(str.startsWith(String.valueOf(s))) {
		    	return false;
		    }else if(str.startsWith(String.valueOf(tekrar))) {
		    	return false;
		    }else if(str.endsWith(String.valueOf(s))) {
		    	return false;
		    }else if(str.endsWith(String.valueOf(tekrar))) {
		    	return false;
		    }
		}
		return true;
	}
	public static <T> T mostCommon(List<T> list) {
	    Map<T, Integer> tmap = new HashMap<>();

	    for (T t : list) {
	        Integer val = tmap.get(t);
	        tmap.put(t, val == null ? 1 : val + 1);
	    }

	    Entry<T, Integer> max = null;

	    for (Entry<T, Integer> e : tmap.entrySet()) {
	        if (max == null || e.getValue() > max.getValue())
	            max = e;
	    }

	    return max == null ? null : max.getKey();
	}

	/**
	 * @param String Values, not allow input negative
	 * @return integer value
	 */
	public int convertStringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println("Error");
			return -1;
		}
	}
}
