
import java.util.*;
public class ServiceMonitor{

	private Map<String,Service> services;

	public ServiceMonitor(){
		services = new HashMap<String,Service>();
	}

	public void addTask(ServiceTask task) throws ServiceTaskException{
		String address=task.getServiceAddress();
		Service service= services.get(address);
		if(services ==null){
			throw new ServiceTaskException("No such service");
		} 
		else{
			service.processTask(task);
		}
	}

	public List<String> getAllServices(){

		Iterator<Service> items=services.values().iterator();
		ArrayList<String> allServices=new ArrayList<String>();
		while(items.hasNext()){

			allServices.add(items.next().toString());
		}

		return allServices;


	}

	public boolean isServiceAvailable(String address){
		return services.containsKey(address);
	}

	public void startService(String address){
		services.get(address).start();
	}

	public void stopService(String address){
		services.get(address).stop();
	}
}