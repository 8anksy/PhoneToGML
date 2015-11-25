import java.io.*;
import java.util.*;

public class PhoneGML {

	public static void main(String[] args) {
		
		String fileName = args[0];
		
		MyCSVReader csv = new MyCSVReader(fileName);
		
		
		GMLTemplate base = new GMLTemplate();
		try {
			base.BuildTemplate();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		System.out.println(csv.GetXs().size());
		System.out.println(csv.GetYs().size());
		System.out.println(csv.GetZs().size());
		System.out.println(csv.GetTimes().size());
		ArrayList<Float> xFloats = NormalizeDataToFloats(csv.GetXs());
		ArrayList<Float> yFloats = NormalizeDataToFloats(csv.GetYs());
		ArrayList<Float> zFloats = NormalizeDataToFloats(csv.GetZs());
		ArrayList<Float> tFloats = new ArrayList<Float>();
		
		for (String s : csv.GetTimes()) {
			tFloats.add(Float.valueOf(s));
		}
		
		base.GeneratePoints(xFloats, yFloats, zFloats, tFloats);
	}	 	
	
	public static ArrayList<Float> NormalizeDataToFloats(ArrayList<String> data) {
		ArrayList<Float> fData = new ArrayList<Float>();
		for (String s : data) {
			fData.add(Float.valueOf(s));
		}
		float max = Collections.max(fData);
		float min = Collections.min(fData);
		System.out.println("Max val = " + max);
		System.out.println("Min val = " + min);
		
		for(int i = 0; i < fData.size(); i ++) {
			fData.set(i, (fData.get(i) - min)/(max - min));
		}
		return fData;
		
	}
}
