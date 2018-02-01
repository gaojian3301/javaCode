package multiThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MyDefaultThreadPool <Job extends Runnable> implements MyThreadPool<Job> {
	
	private static final int MAX_WORKER_NUMBERS = 10;
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	private static final int MIN_WORKER_NUMBERS = 1;
	private LinkedList<Job> jobs = new LinkedList<Job>();
	private List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	private AtomicLong threadNum = new AtomicLong();
	
	class Worker implements Runnable{
		private volatile boolean running = true;
		@Override
		public void run() {
			while(running){
				Job job = null;
				synchronized(jobs){
					while(jobs.isEmpty()){
						try{
							jobs.wait();
						}catch(InterruptedException ex){
							Thread.currentThread().interrupt();
							return;
						}
					}
					job = jobs.removeFirst();
				}
				if(job != null){
					try{
						job.run();
					}catch (Exception e){
						//TODO
					}
				}
			}
		}
		public void shutdown(){
			running = false;
		}
	}
	
	public MyDefaultThreadPool(){
		initializeWorkers(DEFAULT_WORKER_NUMBERS);
	}
	
	public MyDefaultThreadPool(int num){
		workerNum = num > MAX_WORKER_NUMBERS? MAX_WORKER_NUMBERS : 
			num < MIN_WORKER_NUMBERS? MIN_WORKER_NUMBERS:num;
	}
	
	private void initializeWorkers(int num){
		for(int i = 0;i < num; i++){
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker,"ThreadPool-worker-"+threadNum.decrementAndGet());
			thread.start();
		}
	}
	
	@Override
	public void execute(Job job) {
		if(job != null){
			synchronized(jobs){
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		for(Worker worker : workers){
			worker.shutdown();
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized(jobs){
			if(workerNum + num > MAX_WORKER_NUMBERS){
				num = MAX_WORKER_NUMBERS - workerNum;
			}
			initializeWorkers(num);
			workerNum += num;
		}
	}
	
	@Override
	public void removeWork(int num) {
		synchronized(jobs){
			if(num > workerNum){
				throw new RuntimeException("beyond worker number");
			}else{
				for(int i = 0;i < workerNum;i++){
					Worker worker = workers.get(i);
					if(workers.remove(worker)){
						worker.shutdown();
					}
				}
			}
		}
	}
	
	@Override
	public int getJobSize() {
		return jobs.size();
	}

}
