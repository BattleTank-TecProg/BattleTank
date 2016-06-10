package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ticker implements Runnable {

	ActionListener actionListen;
	Thread thread;
	int delay;
	private boolean isTicking;
	public void name() {
		
	}
	
	public Ticker(int i, ActionListener actionlistener) {
		actionListen = actionlistener;
		initingTicker(i);
	}

	public Ticker(int i) {
		initingTicker(i);
	}
	
	public void initingTicker(int i) {
		delay = i;
		thread = new Thread(this);
		thread.start();
		isTicking = false;
	}
	
	public void addActionListener(ActionListener actionlistener) {
		if (actionListen == null) {
			actionListen = actionlistener;
		} else {
			System.out.println("WARNING: ActionListener already added to Ticker.");
		}
	}

	public boolean isRunning() {
		return isTicking;
	}

	public void start() {
		isTicking = true;
	}

	public void stop() {
		isTicking = false;
	}

	public void setDelay(int i) {
		delay = i;
	}

	public int getDelay() {
		return delay;
	}

	public void run() {
		do {
			fireActionPerformed();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException interruptedexception) {
				System.out.println("WARNING: Ticker thread interrupted.");
			}
		} while (true);
	}
	
	private void fireActionPerformed() {
		if (actionListen == null || !isTicking) {
			// Nothing to do.
		} else {
			ActionEvent actionevent = new ActionEvent(this, 0, null);
			actionListen.actionPerformed(actionevent);
		}
	}
}
