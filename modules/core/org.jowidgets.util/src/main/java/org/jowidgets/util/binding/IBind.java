/*
 * Copyright (c) 2014, grossmann
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

package org.jowidgets.util.binding;

import org.jowidgets.util.IObservableValue;

/**
 * Creates a binding for observable values
 */
public interface IBind {

    /**
     * Binds two observable values with same type bidirectional.
     * 
     * If the source and destination value differs when bound,
     * the destination value becomes the source value.
     * 
     * If the source value changes later, the destination value will be changed to the source.
     * If the destination value changes later, the source value will be changed to the destination.
     * 
     * @param source The source value to bind, must not be null
     * @param destination The destination value to bind, must not be null
     * 
     * @return The binding reference for the created binding
     */
    <VALUE_TYPE> IBinding bind(IObservableValue<VALUE_TYPE> source, IObservableValue<VALUE_TYPE> destination);

    /**
     * Binds two observable values with potentially different type bidirectional using a
     * binding converter.
     * 
     * If the source and destination value differs when bound,
     * the destination value becomes the source value.
     * 
     * If the source value changes later, the destination value will be changed to the source.
     * If the destination value changes later, the source value will be changed to the destination.
     * 
     * @param source The source value to bind, must not be null
     * @param destination The destination value to bind, must not be null
     * @param converter The binding converter to use, must not be null
     * 
     * @return The binding reference for the created binding
     */
    <SOURCE_TYPE, DESTINATION_TYPE> IBinding bind(
        IObservableValue<SOURCE_TYPE> source,
        IObservableValue<DESTINATION_TYPE> destination,
        IBindingConverter<SOURCE_TYPE, DESTINATION_TYPE> converter);

}
