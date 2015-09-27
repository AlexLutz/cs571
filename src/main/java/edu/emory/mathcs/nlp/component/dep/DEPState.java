import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import edu.emory.mathcs.nlp.component.util.eval.Eval;
import edu.emory.mathcs.nlp.component.util.state.NLPState;

public class DEPState<N extends DEPNode> extends NLPState<N, DEPArc> 
{
	protected Deque<DEPNode> stack;
	protected Deque<DEPNode> queue;
	protected DEPArc[] gold;
	protected int index = 0;
	
	public DEPState(N[] nodes)
	{
		super(nodes);
		stack = new ArrayDeque<>();
		stack.push(DEPNode.ROOT);
		queue = new ArrayDeque<>(Arrays.asList(nodes));
		gold = new DEPArc[2 * nodes.length];
	}

	@Override
	public void clearGoldLabels()
	{
		gold = simulate();
	}
	
	//need to review what I am adding as gold label since there might be labels without any node to correspond to features
	private DEPArc[] simulate()
	{
		DEPNode node;
		int stackId, queueId;
		DEPNode stackHead, queueHead;
		
		while (queue.size() > 0) {
			stackHead = stack.peek().getHead();
			queueHead = queue.peekFirst();
			stackId = stack.peek().getID();
			queueId = queue.peekFirst().getID();
			
			if(stackHead.getID() == queueId) {	//Left Arc + Reduce
				node = stack.pop();
				gold[node.getHead().getID()] = new DEPArc(node, "Left Arc-"+node.getLabel());
			} else if (queueHead.getID() == stackId) {	//Right Arc + shift
				node = stack.peek();
				gold[node.getID()] = new DEPArc(queue.peekFirst(), "Right Arc-"+node.getLabel());
				stack.push(queue.removeFirst());
			} else if (inArc(stack.peek())) {
				stack.pop();
				
			}
		}
		
	}
	
	private boolean inArc(DEPNode node) {
		for (DEPArc arc : gold) {
			if (arc.getNode() == node) return true;
		}
		return false;
	}

	@Override
	public void next()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminate()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DEPArc getGoldLabel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DEPArc setLabel(DEPArc label)
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