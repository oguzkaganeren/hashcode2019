/**
 * 
 */
package googlehash2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.util.Map.Entry;
import java.util.Set;
/**
 * @author oguz
 *
 */
public class Process {
	private IOoperation data;
	Map<String, List<Integer>> tags = new TreeMap<String, List<Integer>>();
	Map<String, List<String>> slides = new LinkedHashMap<String, List<String>>();
	private int piI=0;
	private String[] temp;
	private List<String> empty;
	Map<Integer, List<String>> map = new TreeMap<Integer, List<String>>();
	  public static boolean ASC = true;
	    public static boolean DESC = false;
	public Process() {
		String line;
		data=new IOoperation("c_memorable_moments");
		//System.out.println(numberOfFoto);
		//System.out.println(numberOfRows+" "+numberOfColumns+" "+numberOfMinCells+" "+numberOfMaxCells);
		boolean isOk=true;
		while (isOk) {
			line=data.getNextLine();
			if(line!=null) {
				temp=line.split(" ");
						if(temp.length>1) {
							for (int i = 0; i < temp.length; i++) {
								addToListMap(piI, temp[i]);
								
								if(i>=2) {
									addToListTags(tags,temp[0]+temp[i], piI);
								}
							}
							piI++;
						}
						if(temp.length>1) {
					
						}
				
			}else {
				isOk=false;
			}
		}
		piI=0;
		//printTags();
		makeSlide();
		//printMap(map);
		System.out.println(slides.size());
		printSlides(slides);
		
		
		
	}
	public synchronized void addToListTags(Map<String, List<Integer>> theMap,String mapKey, Integer myItem) {
	    List<Integer> itemsList = theMap.get(mapKey);

	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<Integer>();
	         itemsList.add(myItem);
	         theMap.put(mapKey, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(myItem)) itemsList.add(myItem);
	        
	    }
	}
	public synchronized void addToListMap(int mapKey, String myItem) {
		List<String> itemsList = map.get(mapKey);

	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<String>();
	         itemsList.add(myItem);
	         map.put(mapKey, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(myItem)) itemsList.add(myItem);
	        
	    }
	}
	

	    public static void printMap(Map<Integer, List<String>> map)
	    {
	    	
	        for (Entry<Integer, List<String>> entry : map.entrySet())
	        {
	        	System.out.print(entry.getKey()+" ");
	        	List<String> temp =entry.getValue();
				for (String entry2 : temp)
		        {
					System.out.print(entry2+" ");
		        }
				System.out.println();
	        }
	    }
	    public static void printSlides(Map<String, List<String>> map)
	    {
	        for (Entry<String, List<String>> entry : map.entrySet())
	        {
	        	/*List<Integer> temp =entry.getKey();
				for (int i = 0; i < temp.size(); i++) {
					if(i==1) {
						System.out.print(" ");
					}
					System.out.print(temp.get(i));
					
				}*/
				System.out.print(entry.getKey());
				/*for (String entry2 : entry.getValue())
		        {
					System.out.print(entry2+" ");
		        }*/
				System.out.println();
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
	    public <T> List<T> union(List<T> list1, List<T> list2) {
	        Set<T> set = new HashSet<T>();

	        set.addAll(list1);
	        set.addAll(list2);

	        return new ArrayList<T>(set);
	    }
	private void makeSlide() {
		List<Integer> tekrar=new ArrayList<Integer>();
		List<String> newtgs=new ArrayList<String>();
		int s=0;
		boolean isV=false;
		for (Entry<Integer, List<String>> entry : map.entrySet())
        {
            //System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue()[1]);
        	
			if(entry.getValue()!=null) {
				
				s=entry.getKey();
				List<String> entryValue =entry.getValue();
	        	for (int i = 0; i < entryValue.size(); i++) {
					if(entryValue.get(0).equals("H")) {
						if(i>1) {
							List<Integer> temp =tags.get("H"+entryValue.get(i));
							for (Integer entry2 : temp)
					        {
								if(entry2!=s&&map.get(entry2)!=null) {
									tekrar.add(entry2);
								}
					        }
						}
					}else {
						if(i>1) {
							isV=true;
							List<Integer> temp =tags.get("V"+entryValue.get(i));
							for (Integer entry2 : temp)
					        {
								if(entry2!=s&&map.get(entry2)!=null) {
									tekrar.add(entry2);
								}
					        }
						}
					}
				}
	        	int most;
	        	if(tekrar.size()>0) {
	        		most=mostCommon(tekrar);
	        	}else {
	        		most=-1;
	        	}
	        	
	        	if(isV) {
	        		
	            		if(most>=0) {
	            			slides.put(s+" "+most, union(map.get(s),map.get(most)));
	            			
	            			map.put(s, empty);
		            		map.put(most, empty);
	            		}
	        		isV=false;
	        	}else {
	            		//slides.add(String.valueOf(s));
	        			slides.put(String.valueOf(s), map.get(s));
	            		map.put(s, empty);
	            		if(most>=0) {
	            			slides.put(String.valueOf(most), map.get(most));
	            			
		            		map.put(most, empty);
	            		}
	        	}
	        	
	        	tekrar.clear();
			}
        }
			
	}
	
	public void processSlides() {
		for (Entry<String, List<String>> entry : slides.entrySet())
        {
			
        }
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
}
