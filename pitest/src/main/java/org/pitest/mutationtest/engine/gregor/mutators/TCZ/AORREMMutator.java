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

public enum AOR_REMMutator implements MethodMutatorFactory {

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
		  MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM,
			        "Replaced integer addition with modulus"));
		  MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM,
			        "Replaced integer subtraction with modulus"));
		  MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM,
			        "Replaced integer multiplication with modulus"));
		  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM,
			        "Replaced integer division with modulus"));
		  
		//longs
		  MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM,
		            "Replaced long addition with modulus"));
		  MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM,
		            "Replaced long subtraction with modulus"));
		  MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM,
		            "Replaced long multiplication with modulus"));
		  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM,
		            "Replaced long division with modulus"));
		  
		//double
		  MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM,
		            "Replaced double addition with modulus"));
		  MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM,
		            "Replaced double subtraction with modulus"));
		  MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM,
		            "Replaced double multiplication with modulus"));
		  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM,
		            "Replaced double division with modulus"));
		  
		//float
		  MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM,
		            "Replaced float addition with modulus"));
		  MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM,
		            "Replaced float subtraction with modulus"));
		  MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM,
		            "Replaced float multiplication with modulus"));
		  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM,
		            "Replaced float division with modulus"));
	  }

	  @Override
	  protected Map<Integer, ZeroOperandMutation> getMutations() {
	    return MUTATIONS;
	  }

	}