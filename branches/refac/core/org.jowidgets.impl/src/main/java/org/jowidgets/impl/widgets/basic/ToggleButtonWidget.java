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

package org.jowidgets.impl.widgets.basic;

import org.jowidgets.api.validation.IValidator;
import org.jowidgets.api.validation.ValidationResult;
import org.jowidgets.api.widgets.IToggleButtonWidget;
import org.jowidgets.api.widgets.IWidget;
import org.jowidgets.api.widgets.descriptor.setup.IToggleButtonSetup;
import org.jowidgets.impl.widgets.basic.delegate.ChildWidgetDelegate;
import org.jowidgets.impl.widgets.basic.delegate.InputWidgetDelegate;
import org.jowidgets.impl.widgets.common.wrapper.ToggleButtonWidgetCommonWrapper;
import org.jowidgets.spi.widgets.IToggleButtonWidgetSpi;

public class ToggleButtonWidget extends ToggleButtonWidgetCommonWrapper implements IToggleButtonWidget {

	private final ChildWidgetDelegate childWidgetDelegate;
	private final InputWidgetDelegate<Boolean> inputWidgetDelegate;

	public ToggleButtonWidget(
		final IWidget parent,
		final IToggleButtonWidgetSpi toggleButtonWidgetSpi,
		final IToggleButtonSetup setup) {
		super(toggleButtonWidgetSpi);
		this.childWidgetDelegate = new ChildWidgetDelegate(parent);
		this.inputWidgetDelegate = new InputWidgetDelegate<Boolean>(toggleButtonWidgetSpi, setup);
	}

	@Override
	public IWidget getParent() {
		return childWidgetDelegate.getParent();
	}

	@Override
	public boolean isMandatory() {
		return inputWidgetDelegate.isMandatory();
	}

	@Override
	public void setMandatory(final boolean mandatory) {
		inputWidgetDelegate.setMandatory(mandatory);
	}

	@Override
	public boolean hasInput() {
		return inputWidgetDelegate.hasInput();
	}

	@Override
	public void addValidator(final IValidator<Boolean> validator) {
		inputWidgetDelegate.addValidator(validator);
	}

	@Override
	public ValidationResult validate() {
		return inputWidgetDelegate.validate();
	}

}
