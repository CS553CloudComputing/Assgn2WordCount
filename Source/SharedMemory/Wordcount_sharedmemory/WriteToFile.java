import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class WriteToFile {
	static ConcurrentHashMap<String,Integer> hashMap;
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		int MaxThreadNum = Integer.parseInt(args[0]);
		ExecutorService threads = Executors.newFixedThreadPool(16);
		int fileFrom = 0,fileTo = 0;
		hashMap = new ConcurrentHashMap<String,Integer>();
		
		FileSplitter fp = new FileSplitter(args[1]);
		fp.SplitFile();
		
		if(MaxThreadNum == 1)
		{
			for(int i = 0;i< 80 ;i++)
			{
				ReadFile rFile = new ReadFile();
				rFile.ReadFromFile("File_"+ i,hashMap);
			}
			
		}
		else
		{
			for(int i =0;i<MaxThreadNum;i++)
			{
				for(int j =i*(80/MaxThreadNum);j<(i+1)*(80/MaxThreadNum);j++)
				{
					fileTo = j;										
				}
				fileFrom = i*(80/MaxThreadNum);
				threads.submit(new ThreadRun(fileFrom,fileTo,hashMap)) ;
			}
			
		}
		
		threads.shutdown();
		threads.awaitTermination(2, TimeUnit.HOURS);
		WriteFile();
		
	}
	
	
	public static void WriteFile() throws IOException{
	{	
		Set<String> keys;
		PrintWriter oFile = null;
		
		try
		{
			oFile = new PrintWriter(new File("wordcount-java.txt"));
			hashMap.remove("");
			keys = hashMap.keySet();
			for(String key: keys)
			{
				oFile.println(key + "->" + hashMap.get(key));
	        }
		}
		finally
		{
			oFile.flush();
			oFile.close();			
		}
	}

}
}
