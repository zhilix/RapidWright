/*
 * Copyright (c) 2021 Xilinx, Inc.
 * All rights reserved.
 *
 * Author: Jakob Wenzel, Xilinx Research Labs.
 *
 * This file is part of RapidWright.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.xilinx.rapidwright.placer.blockplacer;

import java.util.Collection;

import com.xilinx.rapidwright.design.AbstractModuleInst;

/**
 * Naive implementation of overlap checking. Always checks against all other instances
 */
public class ExhaustiveOverlapCache<PlacementT, ModuleInstT extends AbstractModuleInst<?,PlacementT,ModuleInstT>>  extends AbstractOverlapCache<PlacementT, ModuleInstT> {
    private final Collection<ModuleInstT> instances;

    public ExhaustiveOverlapCache(Collection<ModuleInstT> instances) {
        this.instances = instances;
    }

    @Override
    public void unplace(ModuleInstT mii) {
        //Everything gets recalculated, nothing to do
    }

    @Override
    public void place(ModuleInstT mii) {
        //Everything gets recalculated, nothing to do
    }

    @Override
    public boolean isValidPlacement(ModuleInstT mii) {
        return doesNotOverlapAny(mii, instances);
    }

    @Override
    public ModuleInstT getSingularOverlap(ModuleInstT mii) {
        if (mii.getPlacement() == null) {
            throw new RuntimeException(mii+" is not placed!");
        }
        ModuleInstT overlap = null;
        for (ModuleInstT other : instances) {
            if (other == mii) {
                continue;
            }
            if (other.getPlacement() == null) {
                continue;
            }
            if (mii.overlaps(other)){
                if (overlap != null) {
                    return null;
                }
                overlap = other;
            }
        }
        return overlap;
    }

    @Override
    public void printStats() {

    }
}
