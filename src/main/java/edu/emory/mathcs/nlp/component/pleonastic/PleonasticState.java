package edu.emory.mathcs.nlp.component.pleonastic;

import java.util.Arrays;

import edu.emory.mathcs.nlp.component.dep.DEPNode;
import edu.emory.mathcs.nlp.component.util.eval.Eval;
import edu.emory.mathcs.nlp.component.util.node.FeatMap;
import edu.emory.mathcs.nlp.component.util.state.L2RState;

public class PleonasticState<N extends DEPNode> extends L2RState<N>
{

	public PleonasticState(N[] nodes)
	{
		super(nodes);
	}

	@Override
	public void clearGoldLabels()
	{
		
//		gold = Arrays.stream(nodes).filter(x -> x.isLemma("it")).map(n -> setLabel(n, "_")).toArray(String[]::new);
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isLemma("it")) {
				gold[i] = setLabel(nodes[i], "_");
			}
		}
	}
	
	//need to create alternate method that then sets the feature map to it=#
	protected String setFeatMap(N node, FeatMap map) {
		String answer = node.getFeat("it");
		node.setFeatMap(map);
		return answer;
	}
	
	//check that this is not used anywhere
	@Override
	protected String setLabel(N node, String label)
	{
		return setFeatMap(node, new FeatMap(label));
	}

	@Override
	protected String getLabel(N node)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evaluate(Eval eval)
	{
		// TODO Auto-generated method stub
		
	}
	
}
