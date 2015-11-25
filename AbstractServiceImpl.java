
public abstract class AbstractServiceImpl implements ServiceTask,Runnable{

	protected final BlockingQueue<ServiceTask> queue;
	protected boolean isIdle;
	protected transient boolean isRunning;
	protected String address;


	abstract ProcessResult execute(ServiceTask task) throws ServiceTaskException;

	public void start(){
		synchronized(this){
			isRunning=true;
		}
		
	}
	public void stop(){
		synchronized(this){
			isRunning=false;
		}
		
	}
	public void processTask(ServiceTask task){
		if(!isRunning){
			throw new IllegalStateException("This service is no longer running");
		}
		else{
			queue.put(task);
		}
		

	}

	public String getAddress(){
		return address;
	}

	 

	public void run(){

		while(isRunning){

			try{
				ServiceTask task=queue.take();
				ProcessResult result=execute(task);
				task.getCallback().onSuccess(result);


			}
			catch(ServiceTaskException error){
				task.getCallback().onError(error);
			}

		}

	}

}