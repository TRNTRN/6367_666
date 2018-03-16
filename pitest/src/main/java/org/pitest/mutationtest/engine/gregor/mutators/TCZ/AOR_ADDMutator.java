package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum AOR_ADDMutator implements MethodMutatorFactory {

	  MATH_MUTATOR;

	  @Override
	  public MethodVisitor create(final MutationContext context,
	      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
	    return new MathMethodVisitor(this, methodInfo, context, methodVisitor);
	  }

	  @Override
	  public String getGloballyUniqueId() {
	    return this.getClass().getName();
	  }

	  @Override
	  public String getName() {
	    return name();
	  }

	}

	class MathMethodVisitor extends AbstractInsnMutator {

	  MathMethodVisitor(final MethodMutatorFactory factory,
	      final MethodInfo methodInfo, final MutationContext context,
	      final MethodVisitor writer) {
	    super(factory, methodInfo, context, writer);
	  }

	  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

	  static {
		//int
		  MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,
			        "Replaced integer subtraction with addition"));
		  MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD,
			        "Replaced integer multiplication with addtion"));
		  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD,
			        "Replaced integer division with addition"));
		  MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD,
			        "Replaced integer modulus with addition"));
		  
		//longs
		  MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,
			        "Replaced long subtraction with addition"));
		  MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD,
		            "Replaced long multiplication with addition"));
		  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD,
		            "Replaced long division with addition"));
		  MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD,
		            "Replaced long modulus with addition"));
		  
		//double
		  MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,
			        "Replaced double subtraction with addition"));
		  MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD,
		            "Replaced double multiplication with addition"));
		  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD,
		            "Replaced double division with addition"));
		  MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD,
		            "Replaced double modulus with addition"));
		  
		//float
		  MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,
			        "Replaced float subtraction with addition"));
		  MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD,
		            "Replaced float multiplication with addition"));
		  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD,
		            "Replaced float division with addition"));
		  MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD,
		            "Replaced float modulus with addition"));
	  }

	  @Override
	  protected Map<Integer, ZeroOperandMutation> getMutations() {
	    return MUTATIONS;
	  }

	}