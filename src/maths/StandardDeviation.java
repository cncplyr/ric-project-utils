package maths;

import java.util.List;

/**
 * 
 * @author Richard Jenkin
 * @version 1.0
 *
 */
public class StandardDeviation {

	public StandardDeviation() {

	}

	public Integer findSDInt(List<Integer> input) {
		AverageFinder af = new AverageFinder();
		Integer mean = af.findMean(input);

		Integer top = 0;
		Integer n = input.size();

		for (Integer i : input) {
			i += (i - mean) ^ 2;
		}
		Integer SD = (int) Math.sqrt(top / n);

		return SD;
	}

	public double findSDDouble(List<Double> input) {
		AverageFinder af = new AverageFinder();
		double mean = af.findMeanDouble(input);

		double top = 0.0f;
		double n = input.size();

		for (double i : input) {
			top += Math.pow(i - mean, 2.0d);
		}
		double SD = Math.sqrt(top / n);

		return SD;
	}
}
