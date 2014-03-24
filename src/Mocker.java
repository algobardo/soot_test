import java.util.Iterator;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.Local;
import soot.PackManager;
import soot.PatchingChain;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.AbstractStmtSwitch;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;
import soot.jimple.StringConstant;
import soot.options.Options;

public class Mocker {
	
	public static void main(String[] args) {
		
//		//Input classes
//		Options.v().set_src_prec(Options.src_prec_class);
//
//		//output classes
//		Options.v().set_output_format(Options.output_format_dava);
//
//        // resolve the PrintStream and System soot-classes
//		Scene.v().addBasicClass("java.lang.Exception",SootClass.SIGNATURES);
//
//
//        PackManager.v().getPack("jtp").add(new Transform("jtp.myInstrumenter", new BodyTransformer() {
//
//			@Override
//			protected void internalTransform(final Body b, String phaseName, @SuppressWarnings("rawtypes") Map options) {
//				final PatchingChain<Unit> units = b.getUnits();
//
//				units.clear();
//
//				if(soot.VoidType.v().equals(b.getMethod().getReturnType()))
//				{
//					units.addLast((Jimple.v().newReturnVoidStmt()));
//				}
//				else if (b.getMethod().getReturnType() instanceof soot.PrimType)
//				{
//					Jimple.v().newReturnStmt(op) ;
//				}
//				else
//				{
//					units.addLast((Jimple.v().newReturnStmt(NullConstant.v())));
//				}
//				b.validate();
//			}
//
//
//		}));
//
//		soot.Main.main(args);
	}
}
