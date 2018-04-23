package TestCases;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

public class TestRunner {
	
	public static void main(String[] args) {
		JUnitCore.main("TestCases.Sanity");
	}

}
