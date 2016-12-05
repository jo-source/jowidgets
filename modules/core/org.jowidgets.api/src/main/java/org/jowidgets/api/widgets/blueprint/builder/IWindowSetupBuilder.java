/*
 * Copyright (c) 2010, Michael Grossmann, Nikolaus Moll
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

import org.jowidgets.api.types.AutoCenterPolicy;
import org.jowidgets.api.types.AutoPackPolicy;
import org.jowidgets.api.types.AutoPositionCorrectionPolicy;
import org.jowidgets.api.widgets.blueprint.builder.convenience.IWindowSetupConvenience;
import org.jowidgets.common.types.Dimension;
import org.jowidgets.common.types.Position;
import org.jowidgets.common.widgets.builder.IWindowSetupBuilderCommon;

public interface IWindowSetupBuilder<INSTANCE_TYPE extends IWindowSetupBuilder<?>> extends
        IComponentSetupBuilder<INSTANCE_TYPE>,
        IWindowSetupConvenience<INSTANCE_TYPE>,
        IWindowSetupBuilderCommon<INSTANCE_TYPE> {

    INSTANCE_TYPE setAutoPackPolicy(AutoPackPolicy autoPackPolicy);

    INSTANCE_TYPE setAutoCenterPolicy(AutoCenterPolicy autoCenterPolicy);

    INSTANCE_TYPE setAutoPositionCorrectionPolicy(AutoPositionCorrectionPolicy autoPositionCorrectionPolicy);

    INSTANCE_TYPE setMinPackSize(Dimension size);

    INSTANCE_TYPE setMaxPackSize(Dimension size);

    INSTANCE_TYPE setSize(Dimension size);

    INSTANCE_TYPE setPosition(Position position);

    /**
     * If auto dispose is set, the window will be disposed, when it was closed.
     * 
     * @param autoDispose True if window should be automatically disposed, false otherwise
     * 
     * @return This builder
     */
    INSTANCE_TYPE setAutoDispose(boolean autoDispose);

}
