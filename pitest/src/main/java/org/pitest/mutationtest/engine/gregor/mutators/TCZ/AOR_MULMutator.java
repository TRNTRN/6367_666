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

public enum AOR_MULMutator implements MethodMutatorFactory {

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
		  MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL,
			        "Replaced integer addition with multiplication"));
		  MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL,
			        "Replaced integer subtraction with multiplication"));
		  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,
			        "Replaced integer division with multiplication"));
		  MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL,
			        "Replaced integer modulus with multiplication"));
		  
		//longs
		  MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL,
		            "Replaced long addition with multiplication"));
		  MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL,
		            "Replaced long subtraction with multiplication"));
		  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,
			        "Replaced long division with multiplication"));
		  MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL,
			        "Replaced long modulus with multiplication"));
		  
		//double
		  MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL,
		            "Replaced double addition with multiplication"));
		  MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL,
		            "Replaced double subtraction with multiplication"));
		  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,
			        "Replaced double division with multiplication"));
		  MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL,
			        "Replaced double modulus with multiplication"));
		  
		//float
		  MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL,
		            "Replaced float addition with multiplication"));
		  MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL,
		            "Replaced float subtraction with multiplication"));
		  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,
			        "Replaced float division with multiplication"));
		  MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL,
			        "Replaced float modulus with multiplication"));
	  }

	  @Override
	  protected Map<Integer, ZeroOperandMutation> getMutations() {
	    return MUTATIONS;
	  }

	}