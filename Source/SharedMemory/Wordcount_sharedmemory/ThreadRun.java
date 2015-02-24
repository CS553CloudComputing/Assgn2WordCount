import java.io.IOException;
//import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;


public class ThreadRun implements Runnable {
	
	int fileTo,fileFrom;
	String[] filenames;
	ConcurrentHashMap<String,Integer> hashMap;
	
	public ThreadRun(int _fileFrom,int _fileTo,ConcurrentHashMap<String,Integer> _hashMap)
	{
		fileFrom = _fileFrom;
		fileTo = _fileTo;
		hashMap = _hashMap;
		
	}

	public void run() {
		ReadFile rFile = new ReadFile();
		
		try {
			for(int i = fileFrom;i< fileTo ;i++)
			{
				rFile.ReadFromFile("File_"+ i,hashMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
