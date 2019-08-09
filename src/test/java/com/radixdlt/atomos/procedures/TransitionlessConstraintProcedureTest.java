package com.radixdlt.atomos.procedures;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.radixdlt.atomos.AtomOS.WitnessValidator;
import com.radixdlt.atomos.Result;
import java.util.Stack;

import org.junit.Test;

import com.radixdlt.serialization.SerializerId2;
import com.radixdlt.constraintmachine.AtomMetadata;
import com.radixdlt.atoms.Particle;

public class TransitionlessConstraintProcedureTest {

	@SerializerId2("custom.payload.particle")
	private static class CustomPayloadParticle extends Particle {
		@Override
		public String toString() {
			return "payload";
		}
	}

	@Test
	public void when_a_payload_constraint_procedure_validates_an_up_particle__then_output_should_succeed() {
		WitnessValidator<CustomPayloadParticle> witnessValidator = mock(WitnessValidator.class);
		when(witnessValidator.validate(any(), any())).thenReturn(Result.success());
		TransitionlessConstraintProcedure procedure = new TransitionlessConstraintProcedure.Builder()
			.add(CustomPayloadParticle.class, witnessValidator)
			.build();

		boolean success = procedure.getProcedures().get(CustomPayloadParticle.class)
			.outputExecute(new CustomPayloadParticle(), mock(AtomMetadata.class));
		assertThat(success).isTrue();
	}

	@Test
	public void when_a_payload_constraint_procedure_validates_a_downed_particle__then_an_error_should_be_returned() {
		WitnessValidator<CustomPayloadParticle> witnessValidator = mock(WitnessValidator.class);
		when(witnessValidator.validate(any(), any())).thenReturn(Result.success());

		TransitionlessConstraintProcedure procedure = new TransitionlessConstraintProcedure.Builder()
			.add(CustomPayloadParticle.class, witnessValidator)
			.build();

		boolean success = procedure.getProcedures().get(CustomPayloadParticle.class)
			.inputExecute(new CustomPayloadParticle(), mock(AtomMetadata.class), new Stack<>());
		assertThat(success).isFalse();
	}
}