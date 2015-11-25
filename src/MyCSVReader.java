import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCSVReader {
	private ArrayList<String> times;
	private ArrayList<String> xVals;
	private ArrayList<String> yVals;
	private ArrayList<String> zVals;

	MyCSVReader(String file) {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sc.useDelimiter("\\n|,");
		int sort = 0;
		this.xVals = new ArrayList<String>();
		this.yVals = new ArrayList<String>();
		this.zVals = new ArrayList<String>();
		this.times = new ArrayList<String>();
		while(sc.hasNext()) {
			if (sort == 0) { times.add(sc.next());}
			if (sort == 1) { xVals.add(sc.next());}
			if (sort == 2) { yVals.add(sc.next());}
			if (sort == 3) { zVals.add(sc.next());}
			sort++;
			if (sort >= 4) {sort=0;}
		}
		sc.close();
	}
	
	public ArrayList<String> GetTimes() {
		return times;
	}
	
	public ArrayList<String> GetXs() {
		return xVals;
	}
	
	public ArrayList<String> GetYs() {
		return yVals;
	}
	
	public ArrayList<String> GetZs() {
		return zVals;
	}
}
