package edu.emory.mathcs.nlp.component.pleonastic;

import edu.emory.mathcs.nlp.common.util.StringUtils;
import edu.emory.mathcs.nlp.component.dep.DEPNode;
import edu.emory.mathcs.nlp.component.dep.DEPState;
import edu.emory.mathcs.nlp.component.util.feature.FeatureItem;
import edu.emory.mathcs.nlp.component.util.feature.FeatureTemplate;
import edu.emory.mathcs.nlp.component.util.feature.Field;

public class PleonasticFeatureTemplate<N extends DEPNode> extends FeatureTemplate<N,PleonasticState<N>>
{
	public PleonasticFeatureTemplate()
	{
		init();
	}

	private void init() 
	{
		//lemma
		add(new FeatureItem<>(-4, Field.lemma));
		add(new FeatureItem<>(-3, Field.lemma));
		add(new FeatureItem<>(-2, Field.lemma));
		add(new FeatureItem<>(-1, Field.lemma));
		add(new FeatureItem<>(0, Field.lemma));
		add(new FeatureItem<>(1, Field.lemma));
		add(new FeatureItem<>(2, Field.lemma));
		add(new FeatureItem<>(3, Field.lemma));
		add(new FeatureItem<>(4, Field.lemma));
		
		//POS tags
		add(new FeatureItem<>(-4, Field.pos_tag));
		add(new FeatureItem<>(-3, Field.pos_tag));
		add(new FeatureItem<>(-2, Field.pos_tag));
		add(new FeatureItem<>(-1, Field.pos_tag));
		add(new FeatureItem<>(0, Field.pos_tag));
		add(new FeatureItem<>(1, Field.pos_tag));
		add(new FeatureItem<>(2, Field.pos_tag));
		add(new FeatureItem<>(3, Field.pos_tag));
		add(new FeatureItem<>(4, Field.pos_tag));
		
	}
	
	@Override
	protected String getFeature(FeatureItem<?> item)
	{
		N node = state.getNode(item.window);
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
	
	//dont currently use will have to change if otherwise
	@Override
	protected String[] getFeatures(FeatureItem<?> item)
	{
		return null;
	}

}
