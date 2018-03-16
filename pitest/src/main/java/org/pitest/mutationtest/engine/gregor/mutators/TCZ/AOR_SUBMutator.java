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

public enum AOR_SUBMutator implements MethodMutatorFactory {

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
		  MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,
			        "Replaced integer addition with subtraction"));
		  MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB,
			        "Replaced integer multiplication with substraction"));
		  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB,
			        "Replaced integer division with substraction"));
		  MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB,
			        "Replaced integer modulus with substraction"));
		  
		//longs
		  MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,
			        "Replaced long addition with subtraction"));
		  MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB,
		            "Replaced long multiplication with subtraction"));
		  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB,
		            "Replaced long division with subtraction"));
		  MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB,
		            "Replaced long modulus with subtraction"));
		  
		//double
		  MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,
			        "Replaced double addition with subtraction"));
		  MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB,
		            "Replaced double multiplication with substraction"));
		  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB,
		            "Replaced double division with substraction"));
		  MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB,
		            "Replaced double modulus with substraction"));
		  
		//float
		  MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,
			        "Replaced float addition with subtraction"));
		  MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB,
		            "Replaced float multiplication with substraction"));
		  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB,
		            "Replaced float division with substraction"));
		  MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB,
		            "Replaced float modulus with substraction"));
	  }

	  @Override
	  protected Map<Integer, ZeroOperandMutation> getMutations() {
	    return MUTATIONS;
	  }

	}