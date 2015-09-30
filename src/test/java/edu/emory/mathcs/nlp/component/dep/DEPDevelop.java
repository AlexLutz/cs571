package edu.emory.mathcs.nlp.component.dep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import edu.emory.mathcs.nlp.common.util.FileUtils;
import edu.emory.mathcs.nlp.common.util.IOUtils;
import edu.emory.mathcs.nlp.component.dep.feature.DEPFeatureTemplate0;
import edu.emory.mathcs.nlp.component.util.NLPFlag;
import edu.emory.mathcs.nlp.component.util.reader.TSVReader;
import edu.emory.mathcs.nlp.learn.model.StringModel;
import edu.emory.mathcs.nlp.learn.weight.MultinomialWeightVector;

public class DEPDevelop
{
	@Test
	public void develop() throws IOException 
	{
		final String root = "/Users/alexlutz/Documents/Data/experiments/wsj/dep/";
		
		List<String> testFile = new ArrayList<>();
		testFile.add("/Users/alexlutz/Documents/Test/test.txt");
		
		TSVReader<DEPNode> reader = new TSVReader<>(new DEPIndex(1,2,3,4,5,6));
		List<String> trainFiles   = FileUtils.getFileList(root+"trn/", "dep");
		List<String> developFiles = FileUtils.getFileList(root+"dev/", "dep");
		
		System.out.println("Collecting training instances");
		StringModel model = new StringModel(new MultinomialWeightVector());
		DEPParser<DEPNode> parse = new DEPParser<DEPNode>(model);
		parse.setFlag(NLPFlag.TRAIN);
		parse.setFeatureTemplate(new DEPFeatureTemplate0());
//		iterate(reader, trainFiles, nodes -> parse.process(nodes));	
		iterate(reader, testFile, nodes -> parse.process(nodes));
	}
	
	void iterate(TSVReader<DEPNode> reader, List<String> filenames, Consumer<DEPNode[]> f) throws IOException
	{
		for (String filename : filenames)
		{
			reader.open(IOUtils.createFileInputStream(filename));
			DEPNode[] nodes;
			
			while ((nodes = reader.next()) != null)
				f.accept(nodes);
			
			reader.close();	
		}
	}
}
