import java.rmi.Remote;
import java.util.ArrayList;

public interface ServerInt extends Remote {

	public void addItem(Sensor sensor) throws Exception;
	public ArrayList<Sensor> getItems() throws Exception;
	public void updateItem(Sensor sensor) throws Exception;
	public void deleteItem(int id) throws Exception;
	public void checkSensor() throws Exception;
	
	
}
