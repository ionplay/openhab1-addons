/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.openpaths.internal;

import org.openhab.core.binding.BindingConfig;

/**
 * This represents the configuration of an openHAB item that is bound to an
 * OpenPaths user/account.
 *
 * @author Ben Jones
 * @since 1.4.0
 */
public class OpenPathsBindingConfig implements BindingConfig {
    private final String name;

    public OpenPathsBindingConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
