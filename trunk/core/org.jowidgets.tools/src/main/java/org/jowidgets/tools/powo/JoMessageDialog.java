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

package org.jowidgets.tools.powo;

import org.jowidgets.api.toolkit.Toolkit;
import org.jowidgets.api.widgets.IMessageDialogWidget;
import org.jowidgets.api.widgets.blueprint.IMessageDialogBluePrint;
import org.jowidgets.api.widgets.descriptor.IMessageDialogDescriptor;
import org.jowidgets.common.image.IImageConstant;
import org.jowidgets.common.widgets.IWindowWidgetCommon;

public class JoMessageDialog extends ChildWidget<IMessageDialogWidget, IMessageDialogBluePrint> implements IMessageDialogWidget {

	public JoMessageDialog(final IWindowWidgetCommon parent, final IImageConstant icon, final String message) {
		this(parent, bluePrint().setIcon(icon).setText(message));
	}

	public JoMessageDialog(final IWindowWidgetCommon parent, final String title, final IImageConstant icon, final String message) {
		this(parent, bluePrint().setTitle(title).setIcon(icon).setText(message));
	}

	public JoMessageDialog(
		final IWindowWidgetCommon parent,
		final IImageConstant titleIcon,
		final String title,
		final IImageConstant icon,
		final String message) {
		this(parent, bluePrint().setTitleIcon(titleIcon).setTitle(title).setIcon(icon).setText(message));
	}

	public JoMessageDialog(final IWindowWidgetCommon parent, final IMessageDialogDescriptor descriptor) {
		super(bluePrint().setSetup(descriptor));
		initialize(Toolkit.getWidgetFactory().create(parent, descriptor));
	}

	public static IMessageDialogBluePrint bluePrint() {
		return Toolkit.getBluePrintFactory().messageDialog();
	}

	public static IMessageDialogBluePrint bluePrintInfo() {
		return Toolkit.getBluePrintFactory().infoDialog();
	}

	public static IMessageDialogBluePrint bluePrintWarning() {
		return Toolkit.getBluePrintFactory().warningDialog();
	}

	public static IMessageDialogBluePrint bluePrintError() {
		return Toolkit.getBluePrintFactory().errorDialog();
	}

	public static IMessageDialogBluePrint bluePrintInfo(final String message) {
		return Toolkit.getBluePrintFactory().infoDialog(message);
	}

	public static IMessageDialogBluePrint bluePrintWarning(final String message) {
		return Toolkit.getBluePrintFactory().warningDialog(message);
	}

	public static IMessageDialogBluePrint bluePrintError(final String message) {
		return Toolkit.getBluePrintFactory().errorDialog(message);
	}

	@Override
	public void showMessage() {
		getWidget().showMessage();
	}

}
