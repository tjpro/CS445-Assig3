//Tyler Protivnak
//CS445 - Assig2
//Assig2B.java driver program (time test)

import java.io.*;

public class Assig3B
{
	public static void main(String [] args) throws IOException
	{
		for(int run = 0; run < 4; run++){
			if(run==0){						//StringBuilder run
				System.out.println("Testing Stringbuilder");
				
				double end;
				double start;
				
				StringBuilder s = new StringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				start = System.nanoTime();
				while ((currChar = br.read()) != -1) {
					s.append((char)currChar);
					charCount++;
				}
				end = System.nanoTime();
				br.close();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Append Operation: " +((end-start)/charCount));
				
				start = System.nanoTime();
				while (s.length()!=0) {
					s.delete(0,1);
				}
				end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Delete Operation: " +((end-start)/charCount));
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				start = System.nanoTime();
				while ((currChar = br2.read()) != -1) {
					s.insert((s.length()/2),(char)currChar);
				}
				end = System.nanoTime();
				br2.close();
				
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Insert Operation: " + ((end-start)/charCount));
				
			}
			
			
			else if(run==1){				//MyStringBuilder run
				System.out.println("\nTesting MyStringbuilder");
				
				double end;
				double start;
				
				MyStringBuilder s = new MyStringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				start = System.nanoTime();
				while ((currChar = br.read()) != -1) {
					s.append((char)currChar);
					charCount++;
				}
				end = System.nanoTime();
				br.close();
				
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Append Operation: " +((end-start)/charCount));
				
				start = System.nanoTime();
				while (s.length()!=0) {
					s.delete(0,1);
				}
				end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Delete Operation: " +((end-start)/charCount));
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				start = System.nanoTime();
				while ((currChar = br2.read()) != -1) {
					s.insert((s.length()/2),(char)currChar);
				}
				end = System.nanoTime();
				br2.close();
				
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Insert Operation: " +((end-start)/charCount));
				
			}
			
			else if(run==2){				//MyStringBuilder run
				System.out.println("\nTesting MyStringbuilder2");
				
				double end;
				double start;
				
				MyStringBuilder2 s = new MyStringBuilder2();
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				start = System.nanoTime();
				while ((currChar = br.read()) != -1) {
					s.append((char)currChar);
					charCount++;
				}
				end = System.nanoTime();
				br.close();
				
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Append Operation: " +((end-start)/charCount));
				
				start = System.nanoTime();
				while (s.length()!=0) {
					s.delete(0,1);
				}
				end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Delete Operation: " +((end-start)/charCount));
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				start = System.nanoTime();
				while ((currChar = br2.read()) != -1) {
					s.insert((s.length()/2),(char)currChar);
				}
				end = System.nanoTime();
				br2.close();
				
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Insert Operation: " +((end-start)/charCount));
				
			}
			
			
			else {							//String run
				System.out.println("\nTesting String");
				
				double start;
				double end;
				
				String s = "";
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				start = System.nanoTime();
				while ((currChar = br.read()) != -1) {
					s+=String.valueOf((char)currChar);
					charCount++;
				}
				end = System.nanoTime();
				br.close();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per pseudo-Append Operation: " +((end-start)/charCount));
				
				start = System.nanoTime();
				while (s.length()!=0) {
					s = s.substring(0, 0) + s.substring(1);
				}
				end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per pseudo-Delete Operation: " +((end-start)/charCount));
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				start = System.nanoTime();
				while ((currChar = br2.read()) != -1) {
					s = s.substring(0, (s.length()/2)) + String.valueOf((char)currChar) + s.substring((s.length()/2));
				}			
				end = System.nanoTime();
				br2.close();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per pseudo-Insert Operation: " +((end-start)/charCount));
				
			}
		}
	}
}