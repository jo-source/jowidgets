/*
 * Copyright (c) 2011, grossmann
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

package org.jowidgets.common.widgets;

import java.util.ArrayList;
import java.util.List;

import org.jowidgets.common.types.Dimension;
import org.jowidgets.common.types.Position;
import org.jowidgets.common.types.TableColumnPackPolicy;
import org.jowidgets.common.widgets.controler.ITableCellEditorObservable;
import org.jowidgets.common.widgets.controler.ITableCellObservable;
import org.jowidgets.common.widgets.controler.ITableCellPopupDetectionObservable;
import org.jowidgets.common.widgets.controler.ITableColumnObservable;
import org.jowidgets.common.widgets.controler.ITableColumnPopupDetectionObservable;
import org.jowidgets.common.widgets.controler.ITableSelectionObservable;

public interface ITableCommon extends
		ITableSelectionObservable,
		ITableCellObservable,
		ITableCellPopupDetectionObservable,
		ITableCellEditorObservable,
		ITableColumnObservable,
		ITableColumnPopupDetectionObservable,
		IControlCommon {

	void initialize();

	Position getCellPosition(int rowIndex, int columnIndex);

	Dimension getCellSize(int rowIndex, int columnIndex);

	ArrayList<Integer> getColumnPermutation();

	void setColumnPermutation(List<Integer> permutation);

	ArrayList<Integer> getSelection();

	void setSelection(List<Integer> selection);

	void pack(TableColumnPackPolicy policy);

	void pack(int columnIndex, TableColumnPackPolicy policy);

}
