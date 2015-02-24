import java.io.FileInputStream;
import java.io.IOException;
//import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ReadFile {
	FileInputStream iStrm = null;
	Scanner scObj = null;
	String fPath;
	String line;
	String[] words;
	ConcurrentHashMap<String,Integer> hashMap;
	
	public /*Hashtable<String,Integer>*/ void ReadFromFile(String _filePath,ConcurrentHashMap<String,Integer> _hashMap) throws IOException
	{
		try
		{
			this.hashMap = _hashMap;
			fPath = _filePath;
			iStrm = new FileInputStream(fPath); 
			scObj = new Scanner(iStrm);
			
			while(scObj.hasNextLine())
			{
				 line = scObj.nextLine();
				 //words = line.split("[^a-zA-Z']+");
				 words = line.split(" ");
				 
					for(int i =0;i<words.length;i++)
					{
						if((hashMap != null) && hashMap.containsKey(words[i]))
						{
							hashMap.put(words[i], (hashMap.get(words[i]) +1 ));
						}
						else
						{
							hashMap.put(words[i], 1);						
						}	
					}
				 
			}
			if(scObj.ioException() != null)
			{
				throw scObj.ioException();
			}
			//return hashTable;
			
		} finally
		{
			System.out.println("Hashing complete for "+iStrm +" at " + new Date());
			if(iStrm != null)
			{
				iStrm.close();				
			}
			if(scObj != null)
			{
				scObj.close();
			}

		}
		//return null;
		
	}

}
