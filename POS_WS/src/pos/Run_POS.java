package pos;


import javax.xml.ws.Endpoint;
public class Run_POS {
public static void main(String[] args) {
	Endpoint.publish("http://localhost:8086/pos", new pos_tag());
	System.out.print("POS Web Service Started..!");
}
}
