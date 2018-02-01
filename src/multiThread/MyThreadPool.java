package multiThread;

public interface MyThreadPool <Job extends Runnable>{
	void execute(Job job);
	void shutdown();
	void addWorkers(int num);
	void removeWork(int num);
	int getJobSize();
}
