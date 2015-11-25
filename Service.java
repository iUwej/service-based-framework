
interface Service{
	boolean isRunning();
	void start();
	void stop();
	void processTask(ServiceTask task);
	boolean isIdle();
	String getAddress();
	String getName();
}