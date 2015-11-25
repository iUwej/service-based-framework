
public class ServiceTask<T implements Serializable> implements Serializable{

	private String serviceAddress;
	private T data;
	private ResultsCallback callback;

	public ServiceTask(String address){
		this.serviceAddress=address;
	}

	public ServiceTask(String service, T data, ResultsCallback c){
		this(service);
		this.data=data;
		this.callback=c;
	}

	public String getServiceAddress(){
			return serviceAddress;
	}

	public void setData(T data){
		this.data=data;
	}

	public void setCallback(ResultsCallback callback){
		this.callback=callback;
	}

	public void getCallBack(){
		return callback;
	}

	public T getData(){
		return data;
	}



}