/*
 * Copyright (c) 2010, Michael Grossmann
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the jo-widgets.org nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
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
package org.jo.widgets.impl.swing.factory.internal;

import javax.swing.JTextField;

import org.jo.widgets.api.util.ColorSettingsInvoker;
import org.jo.widgets.api.validation.ITextInputValidator;
import org.jo.widgets.api.widgets.IWidget;
import org.jo.widgets.api.widgets.descriptor.base.IBaseTextFieldDescriptor;
import org.jo.widgets.impl.swing.factory.internal.util.ValidatedInputDocument;

public class TextFieldWidget extends AbstractSwingTextInputWidget<String> {

	private final boolean mandatory;

	public TextFieldWidget(final IWidget parent,
			final IBaseTextFieldDescriptor<?> descriptor) {
		super(parent, new JTextField(), descriptor.getTextInputValidator());

		this.mandatory = descriptor.isMandatory();

		final ITextInputValidator validator = descriptor
				.getTextInputValidator();

		getUiReference().setDocument(
				new ValidatedInputDocument(getUiReference(), validator));
		registerTextComponent(getUiReference());
		ColorSettingsInvoker.setColors(descriptor, this);
	}

	@Override
	public JTextField getUiReference() {
		return (JTextField) super.getUiReference();
	}

	@Override
	public String getValue() {
		return getUiReference().getText();
	}

	@Override
	public void setValue(final String content) {
		getUiReference().setText(content);
	}

	@Override
	public void setEditable(final boolean editable) {
		getUiReference().setEditable(editable);
	}

	@Override
	public boolean isMandatory() {
		return mandatory;
	}

	@Override
	public boolean hasInput() {
		return getUiReference().getText() != null
				&& !getUiReference().getText().isEmpty();
	}

}
