package edu.emory.mathcs.nlp.component.pleonastic;

import edu.emory.mathcs.nlp.component.dep.DEPNode;
import edu.emory.mathcs.nlp.component.util.eval.AccuracyEval;
import edu.emory.mathcs.nlp.component.util.eval.Eval;
import edu.emory.mathcs.nlp.component.util.node.FeatMap;
import edu.emory.mathcs.nlp.component.util.state.NLPState;
import edu.emory.mathcs.nlp.learn.util.StringPrediction;

public class PleonasticState<N extends DEPNode> extends NLPState<N>
{
	String[] gold;
	int index = -1;
	boolean binary;

	public PleonasticState(N[] nodes)
	{
		super(nodes);
	}
	
	public PleonasticState(N[] nodes, boolean bin) {
		super(nodes);
		binary = bin;
	}

	@Override
	public void evaluate(Eval eval)
	{
		evaluateTokens((AccuracyEval)eval);
	}

	public void evaluateTokens(AccuracyEval eval)
	{
		int correct = 0, counter = 0;

		for (int i=0; i<nodes.length; i++) {
			if (nodes[i].getFeat("it") != null) {
				counter++;
				if (gold[i].equals(nodes[i].getFeat("it"))) {
					correct++;
				} else {
//					System.out.printf("Gold:\t %s \t %s Prediction:\t %s\n", gold[i], getSent(nodes), nodes[i].getFeat("it"));
				}
			}
		}
		
		eval.add(correct, counter);
	}
	
	@Override
	public void saveOracle() {
		gold = clearPleonastic();
	}
	
	private String[] clearPleonastic() {
		String[] gold = new String[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isLemma("it") && (nodes[i].getFeat("it") != null)) {
				if (index < 0) index = i;
				gold[i] = nodes[i].getFeat("it");
				if (binary && !gold[i].equals("0")) gold[i] = "1";
				nodes[i].setFeatMap(new FeatMap());
			}
		}
		return gold;
	}

	@Override
	public String getOraclePrediction() {
		return gold[index];
	}

	@Override
	public void next(StringPrediction prediction) {
		String label = prediction.getLabel();
		nodes[index].setFeatMap(new FeatMap("it="+label));
		for (int i = index; i < gold.length; i++) {
			if (gold[i] != null) index = i;
		}
		index = Integer.MAX_VALUE;
	}

	@Override
	public boolean isTerminate() {
		return index >= gold.length;
	}
	
	private String getSent(DEPNode[] nodes)
	{
		StringBuilder builder = new StringBuilder();
		for(DEPNode node : nodes) builder.append(node.getWordForm()+" ");
		return builder.toString();
	}
}
