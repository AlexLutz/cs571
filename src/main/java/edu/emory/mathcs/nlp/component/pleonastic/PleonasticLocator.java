package edu.emory.mathcs.nlp.component.pleonastic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.emory.mathcs.nlp.component.dep.DEPNode;
import edu.emory.mathcs.nlp.component.dep.DEPState;
import edu.emory.mathcs.nlp.component.util.NLPComponent;
import edu.emory.mathcs.nlp.learn.model.StringModel;
import edu.emory.mathcs.nlp.learn.util.StringInstance;
import edu.emory.mathcs.nlp.learn.util.StringPrediction;
import edu.emory.mathcs.nlp.learn.vector.StringVector;

public class PleonasticLocator<N extends DEPNode> extends NLPComponent<N,PleonasticState<N>>
{

	public PleonasticLocator(StringModel model)
	{
		super(new StringModel[]{model});
	}

	@Override
	protected void readLexicons(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		//dont need this as of yet 
	}

	@Override
	protected void writeLexicons(ObjectOutputStream out) throws IOException
	{
		//dont need this as of yet
	}

	@Override
	protected PleonasticState<N> createState(N[] nodes)
	{
		return new PleonasticState<N>(nodes, true);	
	}

	@Override
	protected void addInstance(String label, StringVector vector)
	{
		models[0].addInstance(new StringInstance(label, vector));
	}

	@Override
	protected StringPrediction getModelPrediction(PleonasticState<N> state, StringVector vector) {
		return models[0].predictBest(vector);
	}

}
