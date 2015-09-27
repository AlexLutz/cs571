package edu.emory.mathcs.nlp.component.pleonastic;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import edu.emory.mathcs.nlp.common.util.FileUtils;
import edu.emory.mathcs.nlp.common.util.IOUtils;
import edu.emory.mathcs.nlp.component.dep.DEPIndex;
import edu.emory.mathcs.nlp.component.dep.DEPNode;
import edu.emory.mathcs.nlp.component.pos.POSNode;
import edu.emory.mathcs.nlp.component.util.NLPFlag;
import edu.emory.mathcs.nlp.component.util.reader.TSVReader;
import edu.emory.mathcs.nlp.learn.model.StringModel;
import edu.emory.mathcs.nlp.learn.weight.BinomialWeightVector;

//biggest problem which I face is trying to move the index since I am only taking features of one thing in each sentence
public class PleonasticDevelop
{
	@Test
	public void develop() throws IOException
	{
		final String root = "/Users/alexlutz/ExtraneousStuffForProgramming/TextFiles/";
		final boolean average = false;
		final double  ambiguity_class_threshold = 0;
		final int     epochs = 100;
		final int     label_cutoff   = 0;
		final int     feature_cutoff = 0;
		
		TSVReader<DEPNode> reader = new TSVReader<>(new DEPIndex(1,2,3,4,5,6));
//		List<String> trainFiles   = FileUtils.getFileList(root+"trn", "trn");
		List<String> trainFiles   = FileUtils.getFileList(root+"tst/extracted", "txt");
		List<String> developFiles = FileUtils.getFileList(root+"dev/extracted", "txt");
		
		System.out.println("Collecting training instances.");
		StringModel model = new StringModel(new BinomialWeightVector());
		PleonasticLocator<DEPNode> pleonastic = new PleonasticLocator<>(model);
		pleonastic.setFlag(NLPFlag.TRAIN);
		pleonastic.setFeatureTemplate(new PleonasticFeatureTemplate<>());
		iterate(reader, trainFiles, nodes -> pleonastic.process(nodes));
		model.vectorize(label_cutoff, feature_cutoff, false);
		
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
