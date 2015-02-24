import java.io.IOException;
import java.io.RandomAccessFile;
//import java.math.BigInteger;


public class FileSplitter {
	static String filePath = "small-dataset.txt";
	int bufferSize ;//= 1024*1024*125;//100 MB buffer size 
	int fileCount = 0;
	
	public FileSplitter(String _fileName)
	{
		filePath = _fileName;		
	}
	
	public void SplitFile() throws IOException
	{
		long spacePos = 0;
		int charCount = 0;
		
		String fileOutPath = "File_";
		RandomAccessFile rFile = null,wFile = null; 
		
		
		try 
		{
			rFile = new RandomAccessFile(filePath, "r");
			bufferSize = (int)(rFile.length()/80);
			byte[] bytes = new byte[bufferSize];
			byte[] bytesW = new byte[bufferSize];
			//System.out.println(" "+rFile.length());
			//System.out.println(" "+rFile.length()/80);
			//System.out.println(" "+bufferSize);
			for(fileCount=0; fileCount<80;fileCount++)
			{
				//bytes = new byte[bufferSize];
				
				rFile.seek(spacePos);
				rFile.read(bytes);
				fileOutPath = "File_";
				for(int j = bufferSize-1;j>0;j--)
				{
					//bytes[j] = 0;
					charCount++;
					if( bytes[j] == 32)
					{
						//bytes[j] = 0;
						//spacePos = j;
						break;					
					}
				}
				fileOutPath = fileOutPath + fileCount;
				wFile = new RandomAccessFile(fileOutPath,"rw");
				bytesW = java.util.Arrays.copyOf(bytes, bufferSize-charCount);
				wFile.write(bytesW);
				wFile.close();
				spacePos = rFile.getFilePointer()-charCount;
				charCount = 0;
				//bytes = null;
			}
			if(fileCount == 80)
			{
				if(rFile.length() != rFile.getFilePointer())
				{
					byte[] lastBytes = new byte[(int)(rFile.length() - rFile.getFilePointer())];
					rFile.read(lastBytes);
					wFile = new RandomAccessFile(fileOutPath,"rw");
					wFile.seek(wFile.length());
					wFile.write(lastBytes);
					wFile.close();					
				}				
			}
			
		}finally
		{
			rFile.close();		
			
		}
	}

}
