import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GMLTemplate {
	private Document d = new Document();

	GMLTemplate() {
		Element ROOT = new Element("GML");
		Element TAG = new Element("tag");
		Element HEADER = new Element("header");
		Element CLIENT = new Element("client");
		Element ENVIRONMENT = new Element("environment");
		Element OFFSET = new Element("offset");
		Element ROTATION = new Element("rotation");
		Element DRAWING = new Element("drawing");
		Element STROKE = new Element("stroke");
		Element NAME = new Element("name");
		Element XOFF = new Element("x");
		Element YOFF = new Element("y");
		Element ZOFF = new Element("z");
		Element XROT = new Element("x");
		Element YROT = new Element("y");
		Element ZROT = new Element("z");
		
		ROOT.addContent(TAG);
		
		TAG.addContent(HEADER);
		TAG.addContent(ENVIRONMENT);
		TAG.addContent(DRAWING);
		
		HEADER.addContent(CLIENT);
		CLIENT.addContent(NAME.setText("JarrodAndSam"));
		
		ENVIRONMENT.addContent(OFFSET);
		ENVIRONMENT.addContent(ROTATION);
		
		DRAWING.addContent(STROKE);
		
		OFFSET.addContent(XOFF.setText("0.000000"));
		OFFSET.addContent(YOFF.setText("0.000000"));
		OFFSET.addContent(ZOFF.setText("0.000000"));
		
		ROTATION.addContent(XROT.setText("0.000000"));
		ROTATION.addContent(YROT.setText("0.000000"));
		ROTATION.addContent(ZROT.setText("0.000000"));
		
		this.d.addContent(ROOT);
	}
	
	public void BuildTemplate() throws IOException {
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat().setOmitDeclaration(true));
		
		outputter.output(this.d, new FileWriter("template.gml"));
		System.out.println("Your document is ready.");
	}
	public void GeneratePoints(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z, ArrayList<Float> t) {
		Element root = this.d.getRootElement();
		Element stroke = root.getChild("tag").getChild("drawing").getChild("stroke");
		ArrayList<Element> points = new ArrayList<Element>();
		for (int i = 0; i < x.size(); i++) {
			Element point = new Element("pt");
			point.addContent(new Element ("x").setText(String.format("%.6f", x.get(i))));
			point.addContent(new Element ("y").setText(String.format("%.6f", y.get(i))));
			point.addContent(new Element ("z").setText(String.format("%.6f", z.get(i))));
			point.addContent(new Element ("time").setText(String.format("%.6f", t.get(i))));
			points.add(point);
		}
		stroke.addContent(points);
		try {
			this.BuildTemplate();
			System.out.println("Finished GML build successful!");
		}catch (IOException ioe) {
			System.out.println("File build failed!");
		}
	}
}
