package net.todd.common.uitools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ListenerManagerTest {
	private int firstListenerFireCount;
	private int secondListenerFireCount;

	@Before
	public void setUp() {
		firstListenerFireCount = 0;
		secondListenerFireCount = 0;
	}

	@Test
	public void testListenerManagerUsingOneListenerFiringOnce() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		assertEquals(1, firstListenerFireCount);
	}

	@Test
	public void testListenerManagerUsingOneListenerFiringMultipleTimes() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();
		assertEquals(3, firstListenerFireCount);
	}

	@Test
	public void testListenerManagerUsingMultipleListenerFiringMultipleTimes() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		assertEquals(1, firstListenerFireCount);

		manager.addListener(new IListener() {
			public void fireEvent() {
				secondListenerFireCount++;
			}
		});

		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();

		assertEquals(4, firstListenerFireCount);
		assertEquals(3, secondListenerFireCount);
	}

	@Test
	public void testListenerManagerUsingNoListenersDoesNotThrowExceptions() {
		ListenerManager manager = new ListenerManager();
		manager.notifyListeners();
	}

	@Test
	public void testListenerManagerAddingSameListenerMultipleTimesOnlyFiresOncePerNotifyCall() {
		ListenerManager manager = new ListenerManager();

		IListener listener1 = new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		};

		manager.addListener(listener1);
		manager.addListener(listener1);

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();
		assertEquals(3, firstListenerFireCount);
	}

	@Test
	public void testRemoveListeners() {
		ListenerManager manager = new ListenerManager();

		ListenerStub listener1 = new ListenerStub();
		ListenerStub listener2 = new ListenerStub();
		ListenerStub listener3 = new ListenerStub();

		manager.addListener(listener1);
		manager.addListener(listener2);
		manager.addListener(listener3);

		manager.notifyListeners();

		assertEquals(1, listener1.fireCount);
		assertEquals(1, listener2.fireCount);
		assertEquals(1, listener3.fireCount);

		manager.removeListener(listener2);
		manager.notifyListeners();

		assertEquals(2, listener1.fireCount);
		assertEquals(1, listener2.fireCount);
		assertEquals(2, listener3.fireCount);
	}

	@Test
	public void testRemovingNonExistingListenersDoesNotThrowException() {
		ListenerStub listener1 = new ListenerStub();
		ListenerManager manager = new ListenerManager();

		manager.removeListener(listener1);
	}

	@Test
	public void testRemovingExistingListenersMultipleTimesDoesNotThrowException() {
		ListenerStub listener1 = new ListenerStub();
		ListenerManager manager = new ListenerManager();

		manager.removeListener(listener1);
		manager.removeListener(listener1);
	}

	private static class ListenerStub implements IListener {
		int fireCount;

		public void fireEvent() {
			++fireCount;
		}
	}
}
