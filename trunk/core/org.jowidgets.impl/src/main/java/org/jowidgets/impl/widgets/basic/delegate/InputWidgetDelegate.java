/*
 * Copyright (c) 2010, grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 *   names of its contributors may be used to endorse or promote products
 *   derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL jo-widgets.org BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package org.jowidgets.impl.widgets.basic.delegate;

import java.util.LinkedList;
import java.util.List;

import org.jowidgets.api.validation.IValidator;
import org.jowidgets.api.validation.ValidationResult;
import org.jowidgets.api.widgets.IInputWidgetLegacyCommon;
import org.jowidgets.api.widgets.controler.IInputListener;
import org.jowidgets.api.widgets.descriptor.setup.IInputWidgetSetup;
import org.jowidgets.util.EmptyCheck;

public class InputWidgetDelegate<VALUE_TYPE> {

	private final List<IValidator<VALUE_TYPE>> validators;
	private final IInputWidgetLegacyCommon<VALUE_TYPE> inputWidgetCi;

	private boolean mandatory;
	private boolean hasInput;

	public InputWidgetDelegate(final IInputWidgetLegacyCommon<VALUE_TYPE> inputWidgetCi, final IInputWidgetSetup<VALUE_TYPE> setup) {
		super();
		this.validators = new LinkedList<IValidator<VALUE_TYPE>>();

		this.inputWidgetCi = inputWidgetCi;
		this.mandatory = setup.isMandatory();
		this.hasInput = false;

		this.inputWidgetCi.addInputListener(new IInputListener() {
			@Override
			public void inputChanged(final Object source) {
				hasInput = true;
			}
		});
	}

	public ValidationResult validate() {
		final ValidationResult result = new ValidationResult();
		for (final IValidator<VALUE_TYPE> validator : validators) {
			result.addValidationResult(validator.validate(inputWidgetCi.getValue()));
		}
		return result;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(final boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean hasInput() {
		return hasInput && !EmptyCheck.isEmpty(inputWidgetCi.getValue());
	}

	public void addValidator(final IValidator<VALUE_TYPE> validator) {
		validators.add(validator);
	}

}
