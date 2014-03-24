
import soot.*;
import soot.options.Options;
import soot.toolkits.graph.*;
import soot.toolkits.scalar.*;

import java.util.*;

public class RunLiveAnalysis
{
	public static void main(String[] args) {
		args = new String[] {"LiveVarsClass"};
		
		if (args.length == 0) {
			System.out.println("Usage: java RunLiveAnalysis class_to_analyse");
			System.exit(0);
		}
		
		Scene.v().setSootClassPath(Scene.v().defaultClassPath()+":./res");
		System.out.println(Scene.v().defaultClassPath());
		
		Options.v().set_src_prec(Options.src_prec_java);
		Options.v().set_output_format(Options.output_format_jimple);
		
		
		SootClass sClass = Scene.v().loadClassAndSupport(args[0]);		
		
		sClass.setApplicationClass();
		
		Scene.v().loadNecessaryClasses();
		
		Iterator methodIt = sClass.getMethods().iterator();
		while (methodIt.hasNext()) {
			SootMethod m = (SootMethod)methodIt.next();
			Body b = m.retrieveActiveBody();
			
			System.out.println("=======================================");			
			System.out.println(m.getName());
			
			UnitGraph graph = new ExceptionalUnitGraph(b);
			UIEffects sll = new UIEffects(graph);
			
			Iterator gIt = graph.iterator();
			while (gIt.hasNext()) {
				Unit u = (Unit)gIt.next();
				List before = sll.getLiveLocalsBefore(u);
				List after = sll.getLiveLocalsAfter(u);
				UnitPrinter up = new NormalUnitPrinter(b);
				up.setIndent("");
				
				System.out.println("---------------------------------------");			
				u.toString(up);			
				System.out.println(up.output());
				System.out.print("Live in: {");
				String sep = "";
				Iterator befIt = before.iterator();
				while (befIt.hasNext()) {
					String l = (String)befIt.next();
					System.out.print(sep);
					System.out.print(l);
					sep = ", ";
				}
				System.out.println("}");
				System.out.print("Live out: {");
				sep = "";
				Iterator aftIt = after.iterator();
				while (aftIt.hasNext()) {
					String l = (String)aftIt.next();
					System.out.print(sep);
					System.out.print(l);
					sep = ", ";
				}			
				System.out.println("}");			
				System.out.println("---------------------------------------");
			}
			System.out.println("=======================================");
		}
	}
}
