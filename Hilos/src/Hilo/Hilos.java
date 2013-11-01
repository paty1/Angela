package Hilo;

public class Hilos {
	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;

	public long getSpeed() {
		return this.speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public Hilos() {
		pause = false;
		stop = false;
		thread = new Thread(this);
		speed = 0;
	}

	@Override
	public void run() {
		while (!stop) {
			
			System.out.println("HW");

			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				e.printStackTrace();
			}

			synchronized (this) {
				while (pause)

					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if (stop)
					break;
			}
		}
	}

	public void start() {
		thread.start();
	}

	synchronized void stop() {
		stop = true;
		pause = false;
		notify();
	}
	synchronized void suspend() {
		pause = true;

	}

	synchronized void resume() {
		pause = false;
		notify();
	}

	public static void main(String args[]){
		ThreadExample th = new ThreadExample();
		th.start();
		th.suspend();
	}
}



