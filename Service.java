
interface Service{
	boolean isRunning();
	void start();
	void stop();
	void process(ServiceTask task);
	boolean isIdle();
	String getAddress();
}