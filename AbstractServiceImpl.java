import java.util.concurrent.BlockingQueue;

public abstract class AbstractServiceImpl implements Service,Runnable{

	protected  BlockingQueue<ServiceTask> queue;
	protected boolean isIdle;
	protected transient boolean isRunning;
	protected String address;
	protected String name;


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
			try{
				queue.put(task);
			}
			catch(InterruptedException exe){
				task.getCallback().onError(exe);
			}
			
		}
		

	}

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public String toString(){
		String.format("[%s@%s]",name,address);
	}

	 

	public void run(){

		while(isRunning){
				ServiceTask task=null;
			try{
				task=queue.take();
				ProcessResult result=execute(task);
				task.getCallback().onSuccess(result);


			}
			catch(InterruptedException exe){
				task.getCallback().onError(exe);
			}
			catch(ServiceTaskException error){
				task.getCallback().onError(error);
			}

		}

	}

}