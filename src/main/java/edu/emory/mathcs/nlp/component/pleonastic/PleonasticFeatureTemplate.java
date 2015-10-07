package edu.emory.mathcs.nlp.component.pleonastic;

import java.util.Arrays;

import edu.emory.mathcs.nlp.common.util.StringUtils;
import edu.emory.mathcs.nlp.component.dep.DEPNode;
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
//		lemma
		add(new FeatureItem<>(-4, Field.lemma));
		add(new FeatureItem<>(-3, Field.lemma));
		add(new FeatureItem<>(-2, Field.lemma));
		add(new FeatureItem<>(-1, Field.lemma));
		add(new FeatureItem<>(0, Field.lemma));
		add(new FeatureItem<>(1, Field.lemma));
		add(new FeatureItem<>(2, Field.lemma));
		add(new FeatureItem<>(3, Field.lemma));
		add(new FeatureItem<>(4, Field.lemma));
		
		
//		word form
		add(new FeatureItem<>(0, Field.word_form));
		add(new FeatureItem<>(1, Field.word_form));
		add(new FeatureItem<>(3, Field.word_form));
		add(new FeatureItem<>(4, Field.word_form));
		
//		dependecy labels
		add(new FeatureItem<>(4, Field.dependency_label));
		add(new FeatureItem<>(3, Field.dependency_label));
		add(new FeatureItem<>(2, Field.dependency_label));
		add(new FeatureItem<>(0, Field.dependency_label));
		
		add(new FeatureItem<>(0, Field.prefix, 3));
		add(new FeatureItem<>(-1, Field.prefix, 2));
		add(new FeatureItem<>(1, Field.prefix, 3));
		add(new FeatureItem<>(2, Field.prefix, 3));
		add(new FeatureItem<>(3, Field.prefix, 4));
		add(new FeatureItem<>(-1, Field.prefix, 4));
		add(new FeatureItem<>(-2, Field.prefix, 2));
		add(new FeatureItem<>(0, Field.suffix, 2));
		add(new FeatureItem<>(0, Field.suffix, 3));
		
//		bigram
		add(new FeatureItem<>(0, Field.uncapitalized_simplified_word_form), new FeatureItem<>(1, Field.uncapitalized_simplified_word_form));
		add(new FeatureItem<>(2, Field.uncapitalized_simplified_word_form), new FeatureItem<>(3, Field.uncapitalized_simplified_word_form));
		add(new FeatureItem<>(3, Field.uncapitalized_simplified_word_form), new FeatureItem<>(4, Field.uncapitalized_simplified_word_form));
		
//		POS tags
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
		N node = state.getNode(state.index, item.window);	//double check this
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
		case prefix: return getPrefix(node, (Integer)item.value);
		case suffix: return getSuffix(node, (Integer)item.value);
		case dependency_label: return node.getLabel();
		default: throw new IllegalArgumentException("Unsupported feature: "+item.field);
		}
	}
	
	@Override
	protected String[] getFeatures(FeatureItem<?> item)
	{
		DEPNode node = state.getNode(state.index, item.window);
		if (node == null) return null;
		
		switch (item.field)
		{
//		case orthographic: return getOrthographicFeatures(node);
//		case binary: return getBinaryFeatures(node);
		default: throw new IllegalArgumentException("Unsupported feature: "+item.field);
		}
	}
	
	/** The prefix cannot be the entire word (e.g., getPrefix("abc", 3) -> null). */
	protected String getPrefix(DEPNode node, int n)
	{
		String s = node.getSimplifiedWordForm();
		return (n < s.length()) ? StringUtils.toLowerCase(s.substring(0, n)) : null;
	}
	
	/** The suffix cannot be the entire word (e.g., getSuffix("abc", 3) -> null). */
	protected String getSuffix(DEPNode node, int n)
	{
		String s = node.getSimplifiedWordForm();
		return (n < s.length()) ? StringUtils.toLowerCase(s.substring(s.length()-n)) : null;
	}
	
}
