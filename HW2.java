package hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.ui.RefineryUtilities;

public class HW2 {

	public static final double PH = 0.4;
	public static final double ITER_COUNT = 10000;

	public static void main(String[] args) {

		Algo algo = new Algo();
		int i = 0;
		while(i < ITER_COUNT){
			Map<Integer, Double> state2ValuesOfCurrentIter = new HashMap<Integer, Double>();
			for (int j = 0; j < algo.getStates().length; j++) {
				if(j ==0 || j == 100){
					state2ValuesOfCurrentIter.put(j, (double)0);
					continue;
				}
				List<Double> valueOfStateForAction = new ArrayList<Double>();
				int maxAction = getMaxAction(j);
				int a = 1;
				while(a <= maxAction ){
					double reward = 0;
					if (j + a == 100)
						reward = PH * 1 ;
					double stateValue = reward + (PH * (algo.getState2Values().get(j + a))) + ((1 - PH) * (algo.getState2Values().get(j - a)));
					valueOfStateForAction.add(stateValue);
					a++;
				}
				Collections.sort(valueOfStateForAction);
				double maxValue = valueOfStateForAction.get(valueOfStateForAction.size()-1);
				state2ValuesOfCurrentIter.put(j, maxValue);
			}
			i++;
			algo.setState2Values(state2ValuesOfCurrentIter);
		}
		for(Map.Entry<Integer, Double> e : algo.getState2Values().entrySet()){
			System.out.println(e.getKey() + ":" + e.getValue());
			System.out.println("\n");
		}
		
		final LineChart chart = new LineChart("State To Value", algo.getState2Values().entrySet());
		chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
	}

	private static int getMaxAction(int j) {
		int maxActionAvailable;
		if (100-j > j){
			maxActionAvailable = j;
		}else {
			maxActionAvailable = 100-j;
		}
		return maxActionAvailable;
	}

}
