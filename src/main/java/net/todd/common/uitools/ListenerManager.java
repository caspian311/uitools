package net.todd.common.uitools;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
	private final List<IListener> listeners;

	public ListenerManager() {
		listeners = new ArrayList<IListener>();
	}

	public void addListener(IListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void notifyListeners() {
		for (IListener listener : listeners) {
			listener.fireEvent();
		}
	}

	public void removeListener(IListener listener) {
		listeners.remove(listener);
	}
}
