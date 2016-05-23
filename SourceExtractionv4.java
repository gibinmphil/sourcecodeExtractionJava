import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SourceExtractionv4 {
	
	
	/*
	 * 
	 * read library and intialize array
	 * 
	 */
	public static int readLib(String pathList)
	{
		String content="",head="";
		String[] listlib={}; 
		try {
			FileInputStream fipread=new FileInputStream(new File(pathList));
			while(fipread.available()>0)
			{
				content +=(char)fipread.read();
				
			}
			//System.out.println("Library content"+content);
			listlib=content.split("\n");
			
			for(int i=0;i<listlib.length;i++)
			{
				if(i==listlib.length-1)
				{
					head += listlib[i].replaceAll("\\r\\n|\\r|\\n", " ");
				}
				else
				{
					head += listlib[i]+",".replaceAll("\\r\\n|\\r|\\n", " ");
				}
			}
			System.out.println(" head ::"+head);
			//System.out.println(" heading :"+head);
//			FileWriter fw=new FileWriter("./src/dataset.csv", false);
//			fw.write(head.replaceAll("\n", ""));
//			fw.close();
			FileInputStream fip_dataset=new FileInputStream(new File("./src/dataset.csv"));
			if(fip_dataset.available()<=0)
			{
				fip_dataset.close();
				head=head+",types\n";
				FileOutputStream fop=new FileOutputStream(new File("./src/dataset.csv"));
				fop.write(head.getBytes());
				fop.flush();
				fop.close();
			}
			
			
			System.out.println("heading completed");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Library file not available");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Library file can not read");
		}
			
		return listlib.length;
	}
	
	/*
	 * 
	 * select all files
	 */
	
	public static void listfile(String dicn,ArrayList<File> files)
	{
		
		System.out.println("=======Searching File in Directory=======");
		File directory = new File(dicn);
		
		File[] fList= directory.listFiles();
		
		if(fList.length<=0)
		{
			System.out.println("No files in the current Directory");
		}
		
		for(File file:fList)
		{
			if(file.isFile() && file.toString().contains(".java"))
			{
				System.out.println("Selecting java source :"+file);
					files.add(file);
			}
			else if(file.isDirectory())
			{
				listfile (file.getAbsolutePath(),files);
			}
		}
		
	}
	/*
	 * 
	 * combine patterens
	 */
	
public static String patToString(String[] j)
	
	{
	
	System.out.println("Combine Patterens");
		String pat="(";
		for(int i=0;i<j.length;i++)
		{
			pat += " "+j[i];
		}
		
		
	System.out.println(pat);	
		return pat;
	}
	/*
	 * 
	 * 
	 * patternmarker
	 */
public static void patternMarker(File fp)
{	
	System.out.println("===================================================================");
	try
	{
	
		String content_file="",source_l="";
		String[] ser;
		/*
		 * 
		 * list of terms tested
		 * C:/Users/netbook/workspace/SourceExtraction/src/list.txt
		 * C:/Users/iiitmk/workspacejee/SourceExtractionv3/src/list.txt
		 */
		FileInputStream f_source=new FileInputStream(new File("C:/Users/iiitmk/workspacejee/SourceExtractionv4/src/list.txt"));
		while(f_source.available() > 0)
		{
			source_l += (char)f_source.read();
		}
		ser=source_l.split("\n");
		
		//target file
		FileInputStream fip_target=new FileInputStream(fp);
		while(fip_target.available() > 0)
		{
			content_file += (char)fip_target.read();
		}
//		System.out.println(content_file);
		/**
		 * 
		 * comparison starts here
		 */
		
		String[] g=content_file.split(";"); 
		System.out.println("Length :"+g.length);
		for(int i=0;i<g.length;i++)
		{
			
			for(int j=0;j<ser.length;j++)
			{
				if(g[i].contains(ser[j].trim()))
				{
					System.out.println(" "+g[i]+" contain patteren  "+ser[j]);
					//liblist[j]="1";
					v.set(j, "1");
					//System.out.println("Patteren "+patToString(liblist));
				}
				//System.out.println(""+i+" "+j);
			}
		}
	}
	catch(IOException e)
	{
		System.out.print("File Error "+e.getLocalizedMessage());
	}
		
}
	

	static String[] liblist={};
	static ArrayList<String> v=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("=====================================================================");
		int a=readLib("C:/Users/iiitmk/workspacejee/SourceExtractionv4/src/list.txt");
		System.out.println("Term size "+a);
		//liblist[a]=new String();
		for(int i=0;i<a;i++)
		{
			v.add("0");
		}
		
		System.out.print(""+v);
		ArrayList<File> f=new ArrayList<>();
		
		
		/*tested known malwares
		 * 1.D:/AndroidSecurity/Thegot2run_v2/Thegot2run/src/com/thelikes/thegot2run
		 * 2.D:/gibinm/two/javacode
		 * 3.D:/gibinm/three/javacode
		 * 4.D:/gibinm/four/javacode
		 * 5.D:/gibinm/five/javacode
		 * 6.D:/gibinm/six/javacode
		 * 7.D:/gibinm/seven/javacode
		 * 8.D:/gibinm/one/javacode/QueiD9ej/ezahS1gi
		 * 9.E:/Dataset/malset/two/javacode
		 * 10.E:/Dataset/malset/four
		 * 11.E:/Dataset/malset/five
		 * 12.E:/Dataset/goodware/one
		 * 13.E:/Dataset/goodware/two
		 * 14.E:/Dataset/goodware/three
		 * 15.E:/Dataset/goodware/four
		 * 16.E:/Dataset/goodware/five
		 * 17.E:/Dataset/goodware/four
		 * 18.E:/Dataset/goodware/five
		 * 19.E:/Dataset/goodware/six
		 * 20.E:/Dataset/goodware/seven
		 * 21.E:/Dataset/goodware/eight
		 * 22.E:/Dataset/goodware/nine
		 * 23.E:/Dataset/goodware/ten
		 * E:/Dataset/testdata/test1m
		 */
						
		listfile("E:/Dataset/testdata/test10m", f);
		
		
		for(File g:f)
		{
			System.out.println("Reading File "+g.toString());
			patternMarker(g);
		}
		
		
		
		System.out.println("Please Enter 1 for known malware and 0 for good ware");
		Scanner sn=new Scanner(System.in);
		int ty=sn.nextInt();
		v.add(""+ty);
		String result="";
		System.out.println(""+v.get(2));
		for(int i=0;i<v.size();i++)
		{
			if(i==v.size()-1)
			{
				result += v.get(i);
			}
			else
			{
				result += v.get(i)+",";
			}
		
		}
		System.out.println(""+result);
		
		FileWriter fw;
		try {
			fw = new FileWriter("./src/dataset.csv", true);
			fw.write(result+"\n");
			fw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
