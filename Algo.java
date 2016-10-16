package hw2;

import java.util.HashMap;
import java.util.Map;

public class Algo {

	private int[] states = new int [101];
	public int[] getStates() {
		return states;
	}

	public Map<Integer, Double> getState2Values() {
		return state2Values;
	}

	public void setState2Values(Map<Integer, Double> state2Values) {
		this.state2Values = state2Values;
	}

	private Map<Integer, Double> state2Values = new HashMap<Integer, Double>();
	
	public Algo() {
		//Initialize states
		int i = 0;
		while(i<=100){
			states[i] = i;
			i++;
		}
		//Initialize Map to 0 for iteration 1
		for (int j = 0; j < states.length; j++) {
			state2Values.put(states[j], new Double(0)); 
		}
	}
	
}
