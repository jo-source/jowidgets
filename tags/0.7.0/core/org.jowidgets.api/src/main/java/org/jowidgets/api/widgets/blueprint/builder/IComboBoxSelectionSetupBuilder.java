/*
 * Copyright (c) 2010, Michael Grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.jowidgets.api.widgets.blueprint.builder;

import java.util.List;

import org.jowidgets.api.convert.IObjectStringConverter;
import org.jowidgets.api.types.AutoSelectionPolicy;
import org.jowidgets.api.widgets.blueprint.builder.convenience.IComboBoxSelectionSetupConvenience;
import org.jowidgets.common.widgets.builder.IComboBoxSelectionSetupBuilderCommon;

public interface IComboBoxSelectionSetupBuilder<INSTANCE_TYPE extends IComboBoxSelectionSetupBuilder<?, ?>, INPUT_TYPE> extends
		IInputComponentSetupBuilder<INSTANCE_TYPE, INPUT_TYPE>,
		IComboBoxSelectionSetupConvenience<INSTANCE_TYPE, INPUT_TYPE>,
		IComboBoxSelectionSetupBuilderCommon<INSTANCE_TYPE> {

	INSTANCE_TYPE setAutoSelectionPolicy(AutoSelectionPolicy autoSelectionPolicy);

	INSTANCE_TYPE setElements(List<? extends INPUT_TYPE> elements);

	INSTANCE_TYPE setObjectStringConverter(IObjectStringConverter<INPUT_TYPE> objectStringConverter);

	/**
	 * A lenient combobox allows to set values that are not contained in the elements list.
	 * If such a value is set, the elements list shows the new value at the end of the list.
	 * The method getValue() returns the (invalid) value. If a new value is set, the added value
	 * will be removed from the list
	 * 
	 * @param lenient true for lenient mode, false otherwise
	 * 
	 * @return this builder
	 */
	INSTANCE_TYPE setLenient(boolean lenient);
}
