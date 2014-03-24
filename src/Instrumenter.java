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

public class Instrumenter {
	
	public static void main(String[] args) {
//
//		//Input classes
//		Options.v().set_src_prec(Options.src_prec_java);
//
//		//output classes
//		Options.v().set_output_format(Options.output_format_jimple);
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
//				//important to use snapshotIterator here
//				for(Iterator<Unit> iter = units.snapshotIterator(); iter.hasNext();) {
//					final Unit u = iter.next();
//					u.apply(new AbstractStmtSwitch() {
//
//						public void caseInvokeStmt(InvokeStmt stmt) {
//							InvokeExpr invokeExpr = stmt.getInvokeExpr();
//							if(invokeExpr.getMethod().getName().equals("perform")) {
//
//								Local tmpRef = addTmpRef(b);
//								Local tmpString = addTmpString(b);
//
//								  // insert "tmpRef = java.lang.System.out;"
//						        units.insertBefore(Jimple.v().newAssignStmt(
//						                      tmpRef, Jimple.v().newStaticFieldRef(
//						                      Scene.v().getField("<java.lang.System: java.io.PrintStream out>").makeRef())), u);
//
//						        // insert "tmpLong = 'HELLO';"
//						        units.insertBefore(Jimple.v().newAssignStmt(tmpString,
//						                      StringConstant.v("HELLO")), u);
//
//						        // insert "tmpRef.println(tmpString);"
//						        SootMethod toCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void println(java.lang.String)");
//						        units.insertBefore(Jimple.v().newInvokeStmt(
//						                      Jimple.v().newVirtualInvokeExpr(tmpRef, toCall.makeRef(), tmpString)), u);
//
//						        //check that we did not mess up the Jimple
//						        b.validate();
//							}
//						}
//
//					});
//				}
//			}
//
//
//		}));
//
//		soot.Main.main(args);
	}
}
