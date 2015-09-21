package edu.emory.mathcs.nlp.component.dep;

import edu.emory.mathcs.nlp.common.util.StringUtils;
import edu.emory.mathcs.nlp.component.util.feature.FeatureItem;
import edu.emory.mathcs.nlp.component.util.feature.FeatureTemplate;
import edu.emory.mathcs.nlp.component.util.feature.Field;

//need to experiment with all the different types of word forms
//look at the public fields in Feature Item in order to influence how to extract features
public class DEPFeatureTemplate<N extends DEPNode> extends FeatureTemplate<N, DEPState<N>>
{
	private static final long	serialVersionUID	= 253593468153133308L;

	public DEPFeatureTemplate()
	{
		init();
	}
	
	
	public void init()
	{
		
		add(new FeatureItem<>(0, Field.word_form));
		add(new FeatureItem<>(0, Field.pos_tag));
		add(new FeatureItem<>(0, Field.dependency_label));
	}

	
	@Override
	protected String getFeature(FeatureItem<?> item)
	{
		N node = state.getNode(item.window);	//need to change this to give both stack and queue elements instead of just array
		if (node == null) return null;
		
		switch (item.field)
		{
		case word_form: return node.getWordForm();
		case simplified_word_form: return node.getSimplifiedWordForm();
		case uncapitalized_simplified_word_form: return StringUtils.toLowerCase(node.getSimplifiedWordForm());
		case word_shape: return node.getWordShape((Integer)item.value);
		case lemma: return node.getLemma();
		case feats: return node.getFeat((String)item.value);
		case pos_tag: return node.getPOSTag();
//		case prefix: return getPrefix(node, (Integer)item.value);
//		case suffix: return getSuffix(node, (Integer)item.value);
		default: throw new IllegalArgumentException("Unsupported feature: "+item.field);
		}
	}
	
	@Override
	protected String[] getFeatures(FeatureItem<?> item)
	{
		N node = state.getNode(item.window);
		if (node == null) return null;
		
		switch (item.field)
		{
		case orthographic: return getOrthographicFeatures(node);
		case binary: return getBinaryFeatures(node);
		default: throw new IllegalArgumentException("Unsupported feature: "+item.field);
		}
	}

}
