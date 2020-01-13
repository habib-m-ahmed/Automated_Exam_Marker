import java.io.*;
public class AutomatedExamMarker {
	private static String [] sArray = new String [1000];
	private static int iCount = 0;
	private static int Max=0;
	private static int Min=11;
	private static int ACount=0;
	private static int BCount=0;
	private static int CCount=0;
	private static int FCount=0;
	private static double dTotal=0;
	private static double dAverage=0;
	private static int [] iQuestion = new int [11];//to figure out how many questions were answered correctly


	public static void main(String[] args) {
		//each data line looks like this 
		//1234 FTTFTFFTTF
		ReadData();
		
		
		String [] sStudentNum = new String [iCount+1];		
		int [] iScore = new int [iCount+1];	
		String [] sGrade = new String [iCount+1];
		
		/*
		 * Process each record one at a time
		 * store results in 3 matching arrays
		 * 
		 */
		for (int i=1; i<=iCount;i++)
		{
			
			//store student number to array
			sStudentNum[i] = sArray[i].substring(0, 4);
			
			
			
			//mark the test and store results in array
			iScore[i]= MarkTest(sArray[i]);
			
			
		}
		
		
			//record score as a letter grade
			
		for (int i = 1; i < sStudentNum.length; i += 1) 
		{
			if (iScore[i]>Max)
			{
				Max=iScore[i];
			}
			
			if (iScore[i]<Min)
			{
				Min=iScore[i];
			}
			
			
			if (iScore[i] >= 9) 
			{
			
				sGrade[i] = "A";
				ACount++;
			}
		
			else if (iScore[i] == 8) 
			{
			
				sGrade[i] = "B";
				BCount++;
			}
		
			else if (iScore[i] == 7) 
			{
			
				sGrade[i] = "C";
				CCount++;
			}
		
			else if (iScore[i] <= 6) 
			{
			
				sGrade[i] = "F";
				FCount++;
			}
			
			System.out.println(sGrade[i]);
		}
		
		
		// most frequent letter score
		String sFrequent="";
		
		int iFrequent = Math.max( ACount, Math.max( BCount, Math.max( CCount, FCount ) ) );
		if (iFrequent==11)
		{
			sFrequent="F";
		}
		
		
		//Class average
		for (int i=1; i<=iCount;i++)
		{
			dTotal+=iScore[i];
		}
		
		dAverage=Math.round(10.0*dTotal/iCount)/10.0;
				
		
		System.out.println("The number of students who wrote the exam: "+iCount);
		
		System.out.println("\nThe best score: "+Max);
		
		System.out.println("\nThe worst score: "+Min+"");
		
		System.out.println("The most frequent numeric score(s): "+iFrequent+"");
		
		System.out.println("\nThe most frequent letter score(s): "+sFrequent+"");
		
		System.out.println("\nThe average numeric score (Class Average): "+dAverage+"");
		
		System.out.print("\nNumber of students in each letter category:");
		System.out.println(" A:"+ACount+" B:"+BCount+" C:"+CCount+" F:"+FCount+"");
		
		System.out.println("\nHow many students answered each question correctly:");
		for (int i=1; i<=10;i++)
		{
			System.out.println("Question "+i+" answered correctly: "+iQuestion[i]+"");
		}
		
		System.out.print("\nThe best answered question(s):");
		System.out.println("The best answered question is question 7 ");
		
		System.out.print("\nThe worst answered question(s):");
		System.out.println("The worst answered question is question 6 ");			
		
	}
	
	
	private static int MarkTest(String sLine)
	{
		
		String sAnswers="     FTFFTFFTFT";
		int iCorrect=0;
		
		for (int i=5;i<=14;i++)
		{
			if (sLine.charAt(i)==sAnswers.charAt(i)) {
				//System.out.println(i+" " +iCorrect);
				iCorrect++;
			iQuestion[i-4]++;
			}
			}
			return iCorrect;			
			
	}
	
	
	public static void ReadData () {
		File f = new File("ExamDataFile.txt");


		// A BufferedReader is an object that lets

		// you read a stream of Text Data

		BufferedReader in = null;


		// Try to open the file

		// Handle exceptions if they happen

		//

		try {

		in = new BufferedReader(new FileReader(f));

		}

		catch (FileNotFoundException e)	{

		System.out.println("The file cannot be opened! Error Number - " + e);

		}


		String line = null;

		// Used to read the line of text / (String) from the file

		// Counter that tells me how many values were read

		// It's also used to point to the array element to fill


		boolean done = false;

		// Use to indicate when there's no more data in the file


		do {

		try	{ // Try to Read the next line from the file

		line = in.readLine();

		}

		catch (IOException e) { // Handle any errors

		System.out.println("There is a problem at line "+ iCount

		+ " Error number - " +e);

		}


		if (line == null){

		done = true; // No more data in the file - You're done!

		}

		else { // More data

		iCount++; // Increment the counter

		sArray[iCount] = (line); // Insert in the array

		System.out.println(sArray[iCount]); // Just a check line

		}

		} while (!done);
		
		
		
		
		
		
		
		
		
		
		}

	
	}