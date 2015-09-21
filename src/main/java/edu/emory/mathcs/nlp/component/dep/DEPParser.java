package edu.emory.mathcs.nlp.component.dep;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.emory.mathcs.nlp.component.util.NLPComponent;
import edu.emory.mathcs.nlp.learn.model.StringModel;
import edu.emory.mathcs.nlp.learn.vector.StringVector;

public class DEPParser<N extends DEPNode> extends NLPComponent<N,String,DEPState<N>>
{

	public DEPParser(StringModel models)
	{
		super(new StringModel[]{models});
	}

	@Override
	protected void readLexicons(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeLexicons(ObjectOutputStream out) throws IOException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected DEPState<N> createState(N[] nodes)
	{
		return new DEPState<>(nodes);
	}

	@Override
	protected String getLabel(DEPState<N> state, StringVector vector)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addInstance(String label, StringVector vector)
	{
		// TODO Auto-generated method stub
		
	}
	
}
