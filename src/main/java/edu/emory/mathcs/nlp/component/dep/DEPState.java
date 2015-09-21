package edu.emory.mathcs.nlp.component.dep;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import edu.emory.mathcs.nlp.component.util.eval.Eval;
import edu.emory.mathcs.nlp.component.util.state.L2RState;

public class DEPState<N extends DEPNode> extends L2RState<N> 
{
	protected Deque<DEPNode> stack; 
	protected Deque<DEPNode> queue;
	protected final String DELIM = ":";
	
	public DEPState(N[] nodes)
	{
		super(nodes);
		stack = new ArrayDeque<>();
		stack.push(DEPNode.ROOT);
		queue = new ArrayDeque<>(Arrays.asList(nodes));
	}
	
	@Override
	public void clearGoldLabels()
	{
		gold = getGold(nodes);
	}
	
	protected String[] getGold(N[] node)
	{
		List<String> g = new ArrayList<>();
		
		
		return (String[]) g.toArray();
	}
	
	//could keep this and just have it start with a switch statement if label == null then find gold (as it is now) or can do the direction:dep label and then would want to jsut add the last half to the node[index]
	//need to set gold[] based on stack and queue
	//node is mimicking the queue here
	//want to store things at top so I only search once and set labels to label?
	@Override
	protected String setLabel(N node, String label)
	{
		int stackHeadId;
		if (stack.peek().getHead() == null){
			stackHeadId = -1;
		} else {
			stackHeadId = stack.peek().getHead().getID();
		}
		int stackId = stack.peek().getID();
		int queueId = node.getID();
		int queueHeadId = node.getHead().getID();
		//need to check these
		if (stackHeadId == queueId) {	//LeftArc:dep label
			node.setLabel(label);
			return "l"+DELIM+stack.pop().getLabel();
		} else if (queueHeadId == stackId) {	//RightArc:dep label
			node.setLabel(label);
			stack.push(queue.removeFirst());
			return "r"+DELIM+stack.peek().getLabel();
		} else if(queueHeadId == stackHeadId) {	
			node.setLabel(label);
			stack.pop();
			return "reduce";
		} else {
			node.setLabel(label);
			stack.push(queue.removeFirst());
			return "shift";
		}
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
