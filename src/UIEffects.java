import soot.jimple.internal.JInvokeStmt;
import soot.options.*;
import soot.toolkits.scalar.ArrayPackedSet;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.FlowSet;
import soot.toolkits.scalar.ForwardFlowAnalysis;
import soot.*;

import java.util.*;

import soot.toolkits.graph.*;


/**
 *   Analysis that provides an implementation of the LiveLocals  interface.
 */
public class UIEffects
{
    Map<Unit, List> unitToLocalsAfter;
    Map<Unit, List> unitToLocalsBefore;



    /**
     *   Computes the analysis given a UnitGraph computed from a
     *   method body.  It is recommended that a ExceptionalUnitGraph (or
     *   similar) be provided for correct results in the case of
     *   exceptional control flow.
     *
     *   @param g a graph on which to compute the analysis.
     *   
     *   @see ExceptionalUnitGraph
     */
    public UIEffects(UnitGraph graph)
    {
//        if(Options.v().time())
//            Timers.v().liveTimer.start();
//        
//        if(Options.v().verbose())
//            G.v().out.println("[" + graph.getBody().getMethod().getName() +
//                "]     Constructing SimpleLiveLocals...");
//
                        
        UIEffectsAnalysis analysis = new UIEffectsAnalysis(graph);

        if(Options.v().time())
                Timers.v().livePostTimer.start();

        // Build unitToLocals map
        {
            unitToLocalsAfter = new HashMap<Unit, List>(graph.size() * 2 + 1, 0.7f);
            unitToLocalsBefore = new HashMap<Unit, List>(graph.size() * 2 + 1, 0.7f);

            Iterator unitIt = graph.iterator();

            while(unitIt.hasNext())
            {
                Unit s = (Unit) unitIt.next();
 
                FlowSet set = (FlowSet) analysis.getFlowBefore(s);
                unitToLocalsBefore.put(s, Collections.unmodifiableList(set.toList()));
                
                set = (FlowSet) analysis.getFlowAfter(s);
                unitToLocalsAfter.put(s, Collections.unmodifiableList(set.toList()));
            }            
        }
        
        if(Options.v().time())
            Timers.v().livePostTimer.end();
        
        if(Options.v().time())
            Timers.v().liveTimer.end();
    }

    public List getLiveLocalsAfter(Unit s)
    {
        return unitToLocalsAfter.get(s);
    }
    
    public List getLiveLocalsBefore(Unit s)
    {
        return unitToLocalsBefore.get(s);
    }
}

class UIEffectsAnalysis extends ForwardFlowAnalysis
{
    FlowSet emptySet;
    
    UIEffectsAnalysis(UnitGraph g)
    {
        super(g);

        emptySet = new ArraySparseSet();
        
        if(Options.v().time())
            Timers.v().liveSetupTimer.start();

        /*Setup?**/

        if(Options.v().time())
            Timers.v().liveSetupTimer.end();

        if(Options.v().time())
            Timers.v().liveAnalysisTimer.start();

        doAnalysis();
        
        if(Options.v().time())
            Timers.v().liveAnalysisTimer.end();

    }

    protected Object newInitialFlow()
    {
        return emptySet.clone();
    }

    protected Object entryInitialFlow()
    {
        return emptySet.clone();
    }
        
    protected void flowThrough(Object inValue, Object unit, Object outValue)
    {
        FlowSet in = (FlowSet) inValue, out = (FlowSet) outValue;

        if(unit instanceof JInvokeStmt){
        	G.v().out.println("Invoke statement " + unit + " flowing...");
        	in.add(unit.toString(), out);
        }
        else
        	in.copy(out);
    }

    protected void merge(Object in1, Object in2, Object out)
    {
        FlowSet inSet1 = (FlowSet) in1,
            inSet2 = (FlowSet) in2;

        FlowSet outSet = (FlowSet) out;

        inSet1.union(inSet2, outSet);
    }
    
    protected void copy(Object source, Object dest)
    {
        FlowSet sourceSet = (FlowSet) source,
            destSet = (FlowSet) dest;
            
        sourceSet.copy(destSet);
    }
}
