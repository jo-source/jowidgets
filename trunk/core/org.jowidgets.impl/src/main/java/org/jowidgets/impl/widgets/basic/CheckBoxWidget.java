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

import org.jowidgets.api.look.Markup;
import org.jowidgets.api.validation.IValidator;
import org.jowidgets.api.validation.ValidationResult;
import org.jowidgets.api.widgets.ICheckBoxWidget;
import org.jowidgets.api.widgets.IWidget;
import org.jowidgets.api.widgets.descriptor.setup.ICheckBoxSetup;
import org.jowidgets.impl.widgets.basic.delegate.ChildWidgetDelegate;
import org.jowidgets.impl.widgets.basic.delegate.InputWidgetDelegate;
import org.jowidgets.impl.widgets.common.wrapper.InputWidgetCommonWrapper;
import org.jowidgets.impl.widgets.common.wrapper.TextLabelWidgetCommonWrapper;
import org.jowidgets.spi.widgets.ICheckBoxWidgetSpi;
import org.jowidgets.util.Assert;

public class CheckBoxWidget extends InputWidgetCommonWrapper implements ICheckBoxWidget {

	private final ICheckBoxWidgetSpi checkBoxWidgetSpi;
	private final ChildWidgetDelegate childWidgetDelegate;
	private final InputWidgetDelegate<Boolean> inputWidgetDelegate;
	private final TextLabelWidgetCommonWrapper textLabelWidgetCommonWrapper;

	public CheckBoxWidget(final IWidget parent, final ICheckBoxWidgetSpi checkBoxWidgetSpi, final ICheckBoxSetup setup) {
		super(checkBoxWidgetSpi);
		this.checkBoxWidgetSpi = checkBoxWidgetSpi;
		this.childWidgetDelegate = new ChildWidgetDelegate(parent);
		this.textLabelWidgetCommonWrapper = new TextLabelWidgetCommonWrapper(checkBoxWidgetSpi);

		//this must be invoked last
		this.inputWidgetDelegate = new InputWidgetDelegate<Boolean>(this, setup);
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

	@Override
	public boolean isSelected() {
		return checkBoxWidgetSpi.isSelected();
	}

	@Override
	public void setSelected(final boolean selected) {
		checkBoxWidgetSpi.setSelected(selected);
	}

	@Override
	public void setValue(final Boolean value) {
		Assert.paramNotNull(value, "value");
		setSelected(value.booleanValue());
	}

	@Override
	public Boolean getValue() {
		return Boolean.valueOf(isSelected());
	}

	@Override
	public void setMarkup(final Markup markup) {
		textLabelWidgetCommonWrapper.setMarkup(markup);
	}

	@Override
	public void setText(final String text) {
		textLabelWidgetCommonWrapper.setText(text);
	}

	@Override
	public void setToolTipText(final String text) {
		textLabelWidgetCommonWrapper.setToolTipText(text);
	}

}
