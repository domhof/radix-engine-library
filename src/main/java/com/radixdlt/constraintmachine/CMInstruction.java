package com.radixdlt.constraintmachine;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.radixdlt.atoms.Particle;
import com.radixdlt.common.EUID;
import com.radixdlt.crypto.ECSignature;
import com.radixdlt.crypto.Hash;
import java.util.Set;

/**
 * An instruction to be validated by a Constraint Machine
 */
public final class CMInstruction {
	private final ImmutableSet<EUID> destinations;
	private final ImmutableList<CMParticle> cmParticles;
	private final ImmutableList<CMMicroInstruction> microInstructions;
	private final Hash witness;
	private final ImmutableMap<EUID, ECSignature> signatures;

	public CMInstruction(
		ImmutableList<CMParticle> cmParticles,
		ImmutableList<CMMicroInstruction> microInstructions,
		Hash witness,
		ImmutableMap<EUID, ECSignature> signatures
	) {
		this.witness = witness;
		this.cmParticles = cmParticles;
		this.microInstructions = microInstructions;
		this.signatures = signatures;
		this.destinations = cmParticles.stream()
			.map(CMParticle::getParticle)
			.map(Particle::getDestinations)
			.flatMap(Set::stream)
			.collect(ImmutableSet.toImmutableSet());
	}

	public ImmutableList<CMMicroInstruction> getMicroInstructions() {
		return microInstructions;
	}

	public Hash getWitness() {
		return witness;
	}

	public ImmutableList<CMParticle> getParticles() {
		return cmParticles;
	}

	public ImmutableMap<EUID, ECSignature> getSignatures() {
		return signatures;
	}

	public ImmutableSet<EUID> getDestinations() {
		return destinations;
	}
}
