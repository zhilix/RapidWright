/* 
 * Copyright (c) 2022 Xilinx, Inc. 
 * All rights reserved.
 *
 * Author: Eddie Hung, Xilinx Research Labs.
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
 
package com.xilinx.rapidwright.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestBEL {
    @ParameterizedTest
    @CsvSource({
            "LAGUNA_X0Y598,TX_OPTINV_SR",
            "LAGUNA_X0Y598,RX_OPTINV_SR",
            "DSP48E2_X0Y358,CLKINV",
    })
    public void testCanInvert(String siteName, String belName) {
        Device d = Device.getDevice(Device.AWS_F1);
        Site s = d.getSite(siteName);
        BEL b = s.getBEL(belName);
        Assertions.assertTrue(b.canInvert());

    }
}