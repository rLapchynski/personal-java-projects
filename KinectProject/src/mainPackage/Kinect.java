package mainPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import edu.ufl.digitalworlds.j4k.J4KSDK;

public class Kinect extends J4KSDK{
	
	private ArrayList<ActionListener> actionListenerList;
	public short[] currentFrame;

	@Override
	public void onColorFrameEvent(byte[] arg0) {
	}

	@Override
	public void onDepthFrameEvent(short[] arg0, byte[] arg1, float[] arg2, float[] arg3) {
	}

	@Override
	public void onSkeletonFrameEvent(boolean[] arg0, float[] arg1, float[] arg2, byte[] arg3) {
	}
	
	public synchronized void addActionListener(ActionListener listener) {
		if(actionListenerList == null) {
			actionListenerList = new ArrayList<ActionListener>(2);
		}
		if(!actionListenerList.contains(listener)) {
			actionListenerList.add(listener);
		}
	}
	public synchronized void removeActionListener(ActionListener listener) {
		if(actionListenerList != null && actionListenerList.contains(listener)) {
			actionListenerList.remove(listener);
		}
	}
	private void processEvent(ActionEvent e) {
		ArrayList<?> list;
		synchronized(this) {
			if(actionListenerList == null) return;
			list = (ArrayList<?>)actionListenerList.clone();
		}
		for(int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener)list.get(i);
			listener.actionPerformed(e);
		}
	}

}
