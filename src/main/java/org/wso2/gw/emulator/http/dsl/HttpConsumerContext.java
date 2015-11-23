/*
 * *
 *  * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *  *
 *  * WSO2 Inc. licenses this file to you under the Apache License,
 *  * Version 2.0 (the "License"); you may not use this file except
 *  * in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

package org.wso2.gw.emulator.http.dsl;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpVersion;
import org.wso2.gw.emulator.core.AbstractEmulatorContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.dto.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.dto.OutgoingMessage;

import java.util.HashMap;
import java.util.Map;

public class HttpConsumerContext extends AbstractEmulatorContext {

    private static String context;
    private static int readingDelay;
    private static int writingDelay;
    private static boolean randomConnectionClose;
    private static HttpVersion httpVersion;
    private static IncomingMessage incoming;
    private static ChannelInboundHandlerAdapter logicHandler;
    private static Map<IncomingMessage, OutgoingMessage> inOutCorrelation = new HashMap<IncomingMessage, OutgoingMessage>();
    private HTTPProtocolEmulator httpProtocolEmulator;


    public HttpConsumerContext(HTTPProtocolEmulator httpProtocolEmulator) {
        this.httpProtocolEmulator = httpProtocolEmulator;
        this.httpVersion = HttpVersion.HTTP_1_1;
    }

    public HttpConsumerContext host(String host) {
        super.host(host);
        return this;
    }

    public HttpConsumerContext port(int port) {
        super.port(port);
        return this;
    }

    public HttpConsumerContext context(String context) {
        this.context = context;
        return this;
    }

    public HttpConsumerContext readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpConsumerContext writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpConsumerContext randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpConsumerContext httpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public HttpConsumerContext when(IncomingMessage incoming) {
        this.incoming = incoming;
        this.incoming.buildPathRegex(context);
        return this;
    }

    public HttpConsumerContext logic(ChannelInboundHandlerAdapter logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }

    public HttpConsumerContext respond(OutgoingMessage outgoing) {
        this.inOutCorrelation.put(incoming, outgoing);
        return this;
    }

    public AbstractProtocolEmulator operations() {
        return httpProtocolEmulator;
    }

    public static String getContext() {
        return context;
    }

    public static int getReadingDelay() {
        return readingDelay;
    }

    public static int getWritingDelay() {
        return writingDelay;
    }

    public static boolean isRandomConnectionClose() {
        return randomConnectionClose;
    }

    public static HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public static ChannelInboundHandlerAdapter getLogicHandler() {
        return logicHandler;
    }

    public static IncomingMessage getIncoming() {
        return incoming;
    }

    public static Map<IncomingMessage, OutgoingMessage> getInOutCorrelation() {
        return inOutCorrelation;
    }
}