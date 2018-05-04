package com.imadp.core.validation;

import java.util.ArrayList;
import java.util.List;


/**
 * ChainValidator
 *
 * The chain validator executes a series of Validators in sequence, and stops after the first
 * invalid result is found.
 *
 * The ChainValidator cannot be instantiated manually, and requires the use of the ChainValidator.Builder.
 *
 * @note ValidationFailures are identified via the failure keys of the first validator to fail.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ChainValidator extends AbstractValidator<List<Validator>> {

	// constructor
	private ChainValidator(List<Validator> object) {
		super(object);
	}

	@Override
	protected void validate(String objectName, List<Validator> validators, List<ValidationFailure> failures) {
		// execute validators and stop the validation chain if an invalid result was found
		for(Validator validator : validators)
			if(!addValidationResult(validator))
				return;
	}

	/**
	 * Builder
	 *
	 * The Builder helps instantiate a ChainValidator.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class Builder {
		private List<Validator> validators;

		// constructor
		public Builder() {
			validators = new ArrayList<>(4);
		}

		/**
		 * Builds the ChainValidator.
		 *
		 * @return ChainValidator
		 */
		public ChainValidator build() {
			return new ChainValidator(validators);
		}

		/**
		 * Adds a Validator to the chain.
		 *
		 * @param validator
		 * @return Builder
		 */
		public Builder add(Validator validator) {
			validators.add(validator);
			return this;
		}

	}

}